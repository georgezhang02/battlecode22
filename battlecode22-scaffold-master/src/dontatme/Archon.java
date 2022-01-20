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
    static int moveCooldown = 100;

    /**
     * Run a single turn for an Archon.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {

        if(initArchonCount == 0){
            initArchonCount = rc.getArchonCount();
        }

        if(MAP_SCALER == -1){
            MAP_SCALER = .4 + .6 * ((rc.getMapWidth() + rc.getMapHeight())/ 120.0);
        }

        if(pathfinder == null){
            pathfinder = new BFPathing34(rc);
        }

        if(Communications.getArchonMovingID(rc) == id && !moving){
            moving = true;
            landing = false;
            movesUntilLand = 2000;
        }

        Communications.runStart(rc);

        curArchonOrder = Communications.getArchonOrder(rc);

        commandCooldown[0]--;
        commandCooldown[1]--;
        moveCooldown--;
        Team opponent = rc.getTeam().opponent();
        enemies = rc.senseNearbyRobots(34, opponent);
        allies = rc.senseNearbyRobots(34, rc.getTeam());


        // Get the archon ID if needed
        if (id == -1) {
            id = rc.getID();
        }
        if(firstTurn){
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


        // Write archon location if needed
        me = rc.getLocation();
        Communications.setTeamArchonLocation(rc, id, me);
        Communications.setArchonVisionNoLead(rc, id);





        int enemyCount = 0;
        int allyCount = 0;
        int healingCount = 0;

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

        if(flying && Communications.getArchonTurn(rc)  == curArchonOrder){
            Communications.incrementArchonTurn(rc);
        }


        //rc.setIndicatorString(moving+" "+landing+" "+flying+""+movesUntilLand+" ");
        if(moving){
            if(landing && !flying){
                moving = false;
                Communications.setArchonMoving(rc, 0, false, 0);
                moveCooldown = 100;
            }
            else if(movesUntilLand<=0){
                if(rc.canTransform() && landing && flying){
                    rc.transform();
                    flying = false;
                    Communications.setArchonMoving(rc, 0, false, 0);
                }
            } else {


                if(curTarget == null || ((enemyCount >= 2 || healingCount >= 3) && !landing)){
                    landing = true;
                    movesUntilLand = 1;

                }  else if(landing){
                    Direction dir = lookForBetterSquare(rc);
                    if(dir == Direction.CENTER){
                        if(rc.canTransform() && flying){
                            rc.transform();
                            flying = false;
                            Communications.setArchonMoving(rc, 0, false, 0);
                        }
                    } else{
                        move(rc, dir);
                    }

                } else if(!pathfinder.targetWithinRadius(curTarget, 34)){
                    if(!flying){
                        if(rc.canTransform()){
                            rc.transform();
                            flying = true;
                        }
                    } else{
                        move(rc, pathfinder.pathToTarget(curTarget, false));
                    }
                } else if(!pathfinder.targetWithinRadius(curTarget, 20)){
                    if(!flying){
                        if(rc.canTransform()){
                            rc.transform();
                            flying = true;
                        }
                    } else{
                        move(rc, pathfinder.pathToTargetGreedy(curTarget, 0));
                    }

                } else {
                    landing = true;
                    movesUntilLand = 2;
                }
            }

        }


        if(!moving){
            if(enemyCount < 2 && healingCount < 2 && moveCooldown <= 0){

                readComms(rc);
            }

            //dicatorString(enemyCount + " "+ commandCooldown[1]);
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
            if(gameState == 0){
                //rc.setIndicatorString(5 * MAP_SCALER+" "+ (double)soldierCount / rc.getArchonCount());
                if(commandCooldown[0] < 0 && (double)soldierCount / initArchonCount >= 5 * MAP_SCALER){
                    rushArchon(rc);
                }
            } else if (gameState == 1){

                if(Communications.getEnemyArchonLocationByIndex(rc, attackingArchon).x < 60){
                    //rc.setIndicatorString("destroyed");
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

            // If there are less than 4 miners per archon
            // Build a miner in any direction (but take turns)

            if( rc.isActionReady() && Communications.getArchonTurn(rc)  == curArchonOrder){

                if (miners < 3) {
                    if (rc.getTeamLeadAmount(rc.getTeam()) >= 50) {
                        buildTowardsLowRubble(rc, RobotType.MINER);
                    }
                }
                else if (soldierCount / rc.getArchonCount() < 5 ){
                    if (rc.getTeamLeadAmount(rc.getTeam()) >= 75) {
                        buildTowardsLowRubble(rc, RobotType.SOLDIER);

                    }
                } else if (minerCount / rc.getArchonCount() < 5 *  MAP_SCALER){
                    if (rc.getTeamLeadAmount(rc.getTeam()) >= 50) {
                        buildTowardsLowRubble(rc, RobotType.MINER);

                    }
                } else {
                    if (rc.getTeamLeadAmount(rc.getTeam()) >= 75 * MAP_SCALER) {
                        buildTowardsLowRubble(rc, RobotType.SOLDIER);
                    }
                }

            }

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

    static void readComms(RobotController rc) throws GameActionException {
        MapLocation dest = Communications.getMoveToCommand(rc).location;
        double maxDist = Communications.getArchonMovingDistToTarget(rc);

        if(!Communications.isArchonMoving(rc) && dest.x < 60){

            double cross = (int) Math.sqrt(rc.getMapWidth() *rc.getMapHeight() + rc.getMapWidth() * rc.getMapWidth());


            double dist = Math.sqrt(rc.getLocation().distanceSquaredTo(dest));

            rc.setIndicatorString(dist+" "+maxDist);

            if(dist > 5 && (maxDist == 0 || dist < maxDist)){

                Communications.setArchonMoving(rc, (int) dist, false, id);
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
                if(robot.getType() == RobotType.SOLDIER && robot.getHealth() < 50){
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
