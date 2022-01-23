package dontatme;

import java.util.Arrays;
import java.util.Map;

import battlecode.common.*;

public strictfp class Archon {

    static int id = -1;
    static MapLocation me = null;
    static int miners = 0, soldiers = 0;

    static int gameState = 0;
    // gamestate 0 for building soldiers and miners
    // gameState 1 for rushing
    static int[] commandCooldown = new int[2];
    static int attackingArchon = -1;
    static boolean firstTurn = true;

    static RobotInfo[] enemies;
    static RobotInfo[] allies;

    static Pathfinder pathfinder;

    static int curHealingID = -1;

    static Communications.Command commands[] = new Communications.Command[2];
    static int curArchonOrder = -1;

    static double MAP_SCALER = -1;
    static int movesUntilLand = 1;

    static boolean landing = false;
    static boolean moving = false;
    static MapLocation curTarget;
    static int initArchonCount = 0;
    static boolean flying = false;
    static int moveCooldown = 50;

    static int lastNumTeamArchons = 0;

    /**
     * Run a single turn for an Archon.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {

        //initialization
        if(initArchonCount == 0){
            initArchonCount = rc.getArchonCount();
        }

        if(MAP_SCALER == -1){
            MAP_SCALER = .5+ .5 * ((rc.getMapWidth() * rc.getMapHeight())/ 3600.0);
        }

        if(pathfinder == null){
            pathfinder = new BFPathing34(rc);
        }

        if (id == -1) {
            id = rc.getID();
        }


        // first turn locations wipes
        if(firstTurn){
            lastNumTeamArchons = rc.getArchonCount();
            Communications.setArchonMoving(rc, 0, 0, 0);
            for(int i = 0; i < 4; i++){
                Communications.setTeamArchonLocationByIndex(rc, 15, i, new MapLocation(60, 60));
                Communications.setEnemyArchonLocationByIndex(rc, 15, i, new MapLocation(60, 60));

            }
            for(int i = 0; i< 10; i++){
                rc.writeSharedArray(i + Communications.ATTACK_EVEN_OFFSET, 61);
                rc.writeSharedArray(i + Communications.DEFENSE_EVEN_OFFSET, 61);

            }

            for(int i = 0; i < 4; i++){
                rc.writeSharedArray(i + Communications.BUILD_EVEN_OFFSET, 61);
            }
            firstTurn = false;
        }

        if(rc.getArchonCount() < lastNumTeamArchons){
            Communications.setArchonMoving(rc, 0, Math.max(0, Communications.getNumArchonMoving(rc)-1), 0);
        }
        lastNumTeamArchons = rc.getArchonCount();


        // on round start
        rc.setIndicatorString(Communications.getNumArchonMoving(rc)+"");
        if( !moving && curTarget != null){
            moving = true;
            landing = false;
            movesUntilLand = 2000;

        } // check if archon should be moving


        Communications.runStart(rc);
        // wipe stuff

        curArchonOrder = Communications.getArchonOrder(rc);
        // get your token order

        commandCooldown[0]--;
        commandCooldown[1]--;
        moveCooldown--;
        // cooldowns (


        Team opponent = rc.getTeam().opponent();
        enemies = rc.senseNearbyRobots(34, opponent);
        allies = rc.senseNearbyRobots(34, rc.getTeam());
        // get number of opponents and enemies



        // Write archon location if needed
        me = rc.getLocation();
        Communications.setTeamArchonLocation(rc, id, me);
        Communications.setArchonVisionNoLead(rc, id);


        int enemyCount = 0;
        int allyCount = 0;
        int healingCount = 0;


        // get all enemy and ally positions
        MapLocation[] enemyPos = new MapLocation[5];

        for(RobotInfo robot:enemies){
            RobotType type = robot.getType();
            if(type == RobotType.SOLDIER || type == RobotType.WATCHTOWER || type == RobotType.SAGE){
                enemyCount++;
                if(enemyCount < 5){
                    enemyPos[enemyCount] = robot.getLocation();
                }
            }
        }
        for(RobotInfo robot: allies){
            RobotType type = robot.getType();
            if(type == RobotType.SOLDIER || type == RobotType.WATCHTOWER || type == RobotType.SAGE){
                allyCount++;
            }

            if(robot.getHealth() < 20){
                healingCount++;
            }
        }

        // If you are currently flying, skip your turn
        if(flying && Communications.getArchonTurn(rc)  == curArchonOrder){
            Communications.incrementArchonTurn(rc);
        }

        // flight code
        if(moving){
            // you have landed
            if(landing && !flying){
                moving = false;

                Communications.setArchonMoving(rc, 0, Math.max(0, Communications.getNumArchonMoving(rc)-1), 0);
                curTarget = null;
                moveCooldown = 100;
            }
            //You have to land this turn
            else if(movesUntilLand<=0){
                if(rc.canTransform() && landing && flying){
                    rc.transform();
                    flying = false;
                    Communications.setArchonMoving(rc, 0, Math.max(0,
                            Communications.getNumArchonMoving(rc)-1), 0);
                    curTarget = null;
                }
            } else {
                // if you see enemies, or need to heal, find a good spot to land and then land
                if(curTarget == null || ((enemyCount >= 2 || healingCount >= 3) && !landing)){
                    landing = true;
                    movesUntilLand = 1;

                }
                // find a good square to land on. If you are on the best square around you, land
                else if(landing){
                    Direction dir = lookForBetterSquare(rc);
                    if(dir == Direction.CENTER){
                        if(rc.canTransform() && flying){
                            rc.transform();
                            flying = false;
                            Communications.setArchonMoving(rc, 0, Math.max(0,
                                    Communications.getNumArchonMoving(rc)-1), 0);
                            curTarget = null;

                        }
                    } else{
                        move(rc, dir);
                    }

                }
                //path from far away with bf
                else if(!pathfinder.targetWithinRadius(curTarget, 52)){
                    //if you haven't started mvoing, start moving
                    if(!flying){
                        if(rc.canTransform()){
                            rc.transform();
                            flying = true;
                        }
                    } else{
                        move(rc, pathfinder.pathToTarget(curTarget, false));
                    }
                }
                //path close with greedy
                else if(!pathfinder.targetWithinRadius(curTarget, 52)){
                    //if you haven't started mvoing, start moving
                    if(!flying){
                        if(rc.canTransform()){
                            rc.transform();
                            flying = true;
                        }
                    } else{
                        move(rc, pathfinder.pathToTargetGreedy(curTarget, 0));
                    }

                } else {
                    //within 20 squares, start looking for a place to land
                    landing = true;
                    movesUntilLand = 2;
                }
            }

        }

        // not moving
        if(!moving){
            // see if we need to move


            if(moveCooldown <= 0){

                readCommsMove(rc);
            }

            //Send defense command to defend me, not currently used
            if( ((enemyCount >= allyCount && enemyCount >= 1)  || (enemyCount > 2)) && commandCooldown[1] <= 0){
                Communications.sendDefenseCommand(rc, rc.getLocation(), RobotType.ARCHON);
                commandCooldown[1] = Communications.getCommandCooldown(rc, RobotType.ARCHON, false);
            }


            // If there is lead left, save the first open lead location in the shared array
            MapLocation leadLoc = Communications.getArchonVisionLead(rc, id);
            if (leadLoc.y >= 60) {
                MapLocation[] allLoc = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(), 34);
                boolean found = false;
                for (MapLocation loc : allLoc) {
                    if (rc.senseLead(loc) > 1 && !rc.isLocationOccupied(loc)) {
                        Communications.setArchonVisionLead(rc, id, loc);

                        found = true;
                        break;
                    }
                }
                if (!found) {
                    Communications.setArchonVisionNoLead(rc, id);
                }
            }


            int[] units = Communications.getUnitCounts(rc);

            // deciding to rush
            int minerCount = units[0];
            int soldierCount = units[1];
            int builderCount = units[2];

            int labCount = units[3];
            int sageCount = units[4];
            if(gameState == 0){
                if(commandCooldown[0] < 0 && (double)soldierCount / initArchonCount >= 5 * MAP_SCALER){
                    rushArchon(rc);
                }
            } else if (gameState == 1){
                // get enemy archon location
                if(Communications.getEnemyArchonLocationByIndex(rc, attackingArchon).x < 60){
                    attackingArchon = -1;
                    gameState = 0;
                    if(soldierCount / rc.getArchonCount() >= 10 * MAP_SCALER){
                        rushArchon(rc);
                        if(attackingArchon == -1){
                            Communications.sendStopAttackCommand(rc, commands[0].location);
                            System.out.println("Stop Attacking");
                        }
                    } else{
                        Communications.sendStopAttackCommand(rc, commands[0].location);
                        System.out.println("Stop Attacking");
                    }


                } else if(commandCooldown[0] <= 0){
                    gameState = 0;
                    attackingArchon = -1;
                }
            }

            Communications.Command[]buildCommands = Communications.getBuildCommands(rc);

            // Build order and token passing

            if( rc.isActionReady() && Communications.getArchonTurn(rc)  == curArchonOrder){

                if (miners < 4 * MAP_SCALER) {
                    if (rc.getTeamLeadAmount(rc.getTeam()) >= 50) {
                        buildTowardsLowRubble(rc, RobotType.MINER);
                    }
                }

                else if (minerCount / rc.getArchonCount() < 1){
                    if (rc.getTeamLeadAmount(rc.getTeam()) >= 50) {
                        buildTowardsLowRubble(rc, RobotType.MINER);

                    }
                }
                else if (rc.getTeamGoldAmount(rc.getTeam()) >= 20) {
                    buildTowardsLowRubble(rc, RobotType.SAGE);

                }else if (soldiers < 4 * MAP_SCALER  ) {
                    if (rc.getTeamLeadAmount(rc.getTeam()) >= 75) {
                        buildTowardsLowRubble(rc, RobotType.SOLDIER);
                    }
                }else if(builderCount < 1){
                    if (rc.getTeamLeadAmount(rc.getTeam()) >= 40) {
                        buildTowardsLowRubble(rc, RobotType.BUILDER);
                    }
                }

                else if(labCount < 1 && buildCommands[0] != null){
                    // waiting on getting enough lead
                }else if (soldierCount / rc.getArchonCount() < 5  * MAP_SCALER) {
                    if (rc.getTeamLeadAmount(rc.getTeam()) >= 75) {
                        buildTowardsLowRubble(rc, RobotType.SOLDIER);
                    }
                }    else if (minerCount / rc.getArchonCount() < 5 *  MAP_SCALER){
                    int x = (int)(100000 * Math.random()) % 4;

                    if( x <=2){
                        if (rc.getTeamLeadAmount(rc.getTeam()) >=50) {
                            buildTowardsLowRubble(rc, RobotType.MINER);
                        }
                    } else{
                        if (rc.getTeamLeadAmount(rc.getTeam()) >= 75) {
                            buildTowardsLowRubble(rc, RobotType.SOLDIER);
                        }
                    }
                } else {
                    int x = (int)(100000 * Math.random()) % 10;
                    if( x == 0){
                        if (rc.getTeamLeadAmount(rc.getTeam()) >=50) {
                            buildTowardsLowRubble(rc, RobotType.MINER);
                        }
                    } else{
                        if (rc.getTeamLeadAmount(rc.getTeam()) >= 75) {
                            buildTowardsLowRubble(rc, RobotType.SOLDIER);
                        }
                    }
                }

            }

            // heal units around me
            if(rc.isActionReady()){
                MapLocation toHeal = healUnitsAround(rc, allies);
                if(toHeal != null){
                    rc.repair(toHeal);
                    if(Communications.getArchonTurn(rc)  == curArchonOrder){
                        Communications.incrementArchonTurn(rc);
                    }

                }
            }
        }
    }

    static void readCommsMove(RobotController rc) throws GameActionException {
        MapLocation dest = Communications.getMoveToCommand(rc).location;
        double maxDist = Communications.getArchonMovingDistToTarget(rc);
        int curMoving = Communications.getNumArchonMoving(rc);

        if(curMoving < (rc.getArchonCount() +1)/2 && dest.x < 60){

            double cross = (int) Math.sqrt(rc.getMapWidth() *rc.getMapHeight() + rc.getMapWidth() * rc.getMapWidth());

            double dist = Math.sqrt(rc.getLocation().distanceSquaredTo(dest));


            if(dist > 10 &&  (maxDist == 0 || dist < maxDist)){

                Communications.setArchonMoving(rc, (int)dist, Math.min(rc.getArchonCount(),
                        curMoving + 1), 0);
                curTarget = dest;
            }

        }
    }


    static Direction lookForBetterSquare(RobotController rc) throws GameActionException{
        for(Direction dir: Direction.allDirections()){
            if(rc.canMove(dir) && 1.5 * (rc.senseRubble(rc.getLocation().add(dir) ) + 10)
                    <= rc.senseRubble(rc.getLocation())+ 10){
                return dir;
            }
        }
        return Direction.CENTER;
    }



    static void rushArchon(RobotController rc) throws GameActionException {

        for(int i = 0; i<4 && attackingArchon == -1; i++){
            if(Communications.getEnemyArchonLocationByIndex(rc, i).x < 60){ // found
                System.out.println("rush"+ Communications.getEnemyArchonLocationByIndex(rc, i));
                MapLocation attackLoc = Communications.getEnemyArchonLocationByIndex(rc, i);
                Communications.sendAttackCommand(rc, attackLoc, RobotType.ARCHON);
                attackingArchon = i;

                commandCooldown[0] = 100 * (rc.getMapHeight() + rc.getMapWidth()) / 120;
                commands[0] = new Communications.Command(Communications.getEnemyArchonLocationByIndex(rc, i), RobotType.ARCHON, true);
                gameState = 1;
            }
        }

    }

    static void buildTowardsLowRubble(RobotController rc, RobotType type) throws GameActionException {
        Direction [] dirs = Arrays.copyOf(Helper.directions, Helper.directions.length);
        Arrays.sort(dirs, (a, b) -> getRubble(rc, a) - getRubble(rc, b));
        for (Direction d: dirs) {
            if (rc.canBuildRobot(type, d)) {
                rc.buildRobot(type, d);
                Communications.incrementArchonTurn(rc);
                switch(type) {
                    case MINER:
                        miners++;
                        break;
                    case SOLDIER:
                        soldiers++;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    static MapLocation healUnitsAround(RobotController rc, RobotInfo[]allies){
        int lowHealth = 51;
        boolean foundSoldier = false;
        int id = -1;
        MapLocation ans = null;
        for(RobotInfo robot:allies){
            if(rc.getLocation().distanceSquaredTo(robot.getLocation()) <= 20){
                if(robot.getType() == RobotType.SAGE && robot.getHealth() < RobotType.SAGE.getMaxHealth(1)){
                    if(robot.getID() == curHealingID ){
                        return robot.getLocation();
                    }
                    else if(robot.getHealth() < lowHealth  || ! foundSoldier){
                        lowHealth = robot.getHealth();
                        id = robot.getID();
                        foundSoldier = true;
                        ans = robot.getLocation();
                    }
                }
                else if(robot.getType() == RobotType.SOLDIER && robot.getHealth() < RobotType.SOLDIER.getMaxHealth(1)){
                    if(robot.getID() == curHealingID ){
                        return robot.getLocation();
                    }
                    else if(robot.getHealth() < lowHealth  || ! foundSoldier){
                        lowHealth = robot.getHealth();
                        id = robot.getID();
                        foundSoldier = true;
                        ans = robot.getLocation();
                    }
                } else if(robot.getType() == RobotType.MINER && robot.getHealth() < 40 && !foundSoldier){
                    if(robot.getID() == curHealingID ){
                        ans = robot.getLocation();
                    }
                    else if(robot.getHealth() < lowHealth){
                        lowHealth = robot.getHealth();
                        ans = robot.getLocation();
                    }
                }
            }

        }

        curHealingID = id;
        return ans;

    }

    static int getRubble(RobotController rc, Direction d) {
        try {
            MapLocation loc = rc.getLocation().add(d);
            return rc.senseRubble(loc);
        }
        catch (GameActionException e) {
            e.printStackTrace();
            return 0;
        }
    }

    static void move(RobotController rc, Direction dir) throws GameActionException {
        if(dir != null && rc.canMove(dir)){
            rc.move(dir);
            movesUntilLand--;
        }

        if(dir == Direction.CENTER){
            movesUntilLand--;
        }
    }
}
