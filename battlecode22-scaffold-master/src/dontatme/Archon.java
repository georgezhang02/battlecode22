package dontatme;

import java.awt.*;
import java.util.Arrays;

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

    static boolean transformed = false;

    static Communications.Command commands[] = new Communications.Command[2];
    static int curArchonOrder = -1;

    /**
     * Run a single turn for an Archon.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {
        Communications.runStart(rc);

        curArchonOrder = Communications.getArchonOrder(rc);

        commandCooldown[0]--;
        commandCooldown[1]--;
        Team opponent = rc.getTeam().opponent();
        enemies = rc.senseNearbyRobots(20, opponent);
        allies = rc.senseNearbyRobots(20, rc.getTeam());

        // Get the archon ID if needed
        if (id == -1) {
            id = rc.getID();
        }



        // Write archon location if needed
        me = rc.getLocation();
        Communications.setTeamArchonLocation(rc, id, me);

        if(firstTurn){
            for(int i = 0; i < 4; i++){
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

        // determining whether archon should run away

        int enemyCount = 0;
        int allyCount = 0;

        MapLocation[] enemyPos = new MapLocation[5];

        for(RobotInfo robot:enemies){
            if(robot.getType() == RobotType.SOLDIER){
                enemyCount++;
                if(enemyCount < 5){
                    enemyPos[enemyCount] = robot.getLocation();
                }
            }
        }
        for(RobotInfo robot: allies){
            if(robot.getType() == RobotType.SOLDIER){
                allyCount++;
            }
        }
        if( (enemyCount >= allyCount && enemyCount > 1)  || (enemyCount > 2) && commandCooldown[1] <= 0){
            rc.setIndicatorString("HELP ME");
            Communications.sendDefenseCommand(rc, rc.getLocation(), RobotType.ARCHON);
            commandCooldown[1] = Communications.getCommandCooldown(rc, RobotType.ARCHON, false);
        }


        /*
        if(enemyCount >= 2 * soldierCount && enemyCount > 2){
            if(!transformed && rc.canTransform()){
                rc.transform();
                transformed = true;
            } else{
                Direction dir = pathfinder.pathAwayFrom(enemyPos));
                if(dir != null && rc.canMove(dir)){
                    rc.move(dir);
                }
            }
        } else {
            if(transformed && rc.canTransform()){
                rc.transform();
                transformed = false;
            }
        }
        */

        
        // If there is lead left, save the first open lead location in the shared array
        MapLocation leadLoc = Communications.getArchonVisionLead(rc, id);
        if (leadLoc.y != 61) {
            MapLocation[] allLoc = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(), 34);
            boolean found = false;
            for (MapLocation loc : allLoc) {
                if (rc.senseLead(loc) > 1 && rc.senseRobotAtLocation(loc) == null) {
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
        String str ="";

        for(int i = 0; i< 4; i++){
            str += Communications.getEnemyArchonLocationByIndex(rc, i).toString()+" ";

        }

        if(gameState == 0){
            if(commandCooldown[0] < 0 && soldierCount / rc.getArchonCount() >= 15){
                rushArchon(rc);
            }
        } else if (gameState == 1){

            if(Communications.getEnemyArchonLocationByIndex(rc, attackingArchon).x == 61){
                rc.setIndicatorString("destroyed");
                attackingArchon = -1;
                gameState = 0;
                if(soldierCount / rc.getArchonCount() >= 10){
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
            if (miners < 4) {
                if (rc.getTeamLeadAmount(rc.getTeam()) >= 50) {
                    buildTowardsLowRubble(rc, RobotType.MINER);
                }
            } else if (soldierCount / rc.getArchonCount() < 5 ){
                if (rc.getTeamLeadAmount(rc.getTeam()) >= 75) {
                    buildTowardsLowRubble(rc, RobotType.SOLDIER);

                }
            } else if (minerCount / rc.getArchonCount() < 10 ){
                if (rc.getTeamLeadAmount(rc.getTeam()) >= 50) {
                    buildTowardsLowRubble(rc, RobotType.MINER);

                }
            } else {
                if (rc.getTeamLeadAmount(rc.getTeam()) >= 75) {
                    buildTowardsLowRubble(rc, RobotType.SOLDIER);
                }
            }
        }

    }

    static void rushArchon(RobotController rc) throws GameActionException {

        for(int i = 0; i<4 && attackingArchon == -1; i++){
            if(Communications.getEnemyArchonLocationByIndex(rc, i).x < 60){ // found
                System.out.println("rush"+ Communications.getEnemyArchonLocationByIndex(rc, i));
                MapLocation attackLoc = Communications.getEnemyArchonLocationByIndex(rc, i);
                Communications.sendAttackCommand(rc, attackLoc, RobotType.ARCHON);
                attackingArchon = i;

                commandCooldown[0] = 100 * (rc.getMapHeight() + rc.getMapWidth()) / 120;
                commands[0] = new Communications.Command(Communications.getEnemyArchonLocationByIndex(rc, i), RobotType.ARCHON);
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
}
