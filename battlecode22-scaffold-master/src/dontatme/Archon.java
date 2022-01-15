package dontatme;

import java.util.Arrays;

import battlecode.common.*;
import javafx.scene.shape.Arc;

public strictfp class Archon {

    static int id = -1;
    static MapLocation me = null;
    static int miners = 0, soldiers = 0;
    static int comms = 0;

    static int turn = 0;
    static int rushCoolDown;
    static int rushTurns;
    static int gameState = 0;
    // gamestate 0 for building soldiers and miners
    // gameState 1 for rushing
    static int[] commandCooldown = new int[2];
    static int attackingArchon = -1;

    static RobotInfo[] enemies;
    static RobotInfo[] allies;

    static int commandSentLastTurn = 0;
    // command cooldowns for attacking and defending

    /**
     * Run a single turn for an Archon.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {

        turn++;
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

        if(turn ==1){
            for(int i = 0; i< 4; i++){
                Communications.setEnemyArchonLocationByIndex(rc, -1, i, new MapLocation(-1, -1));
            }
        }

        // determining whether archon should run away
        /*
        int enemyCount = 0;
        int soldierCount = 0;

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
                soldierCount++;
            }
        }
        if(enemyCount >= 2 * soldierCount && enemyCount > 2){
            Direction dir = pathfinder.pathAwayFrom(enemyPos));
            attack(attackType);
        }
        */

        
        // If there is lead left, save the first open lead location in the shared array
        MapLocation leadLoc = Communications.getArchonVisionLead(rc, id);
        if (leadLoc.y != 61) {
            MapLocation[] allLoc = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(), 34);
            boolean found = false;
            for (MapLocation loc : allLoc) {
                if (rc.senseLead(loc) > 2 && rc.senseRobotAtLocation(loc) == null) {
                    Communications.setArchonVisionLead(rc, id, loc);
                    found = true;
                    break;
                }
            }
            if (!found) {
                Communications.setArchonVisionNoLead(rc, id);
            }
        }

        // deciding to rush
        int minerCount = 0;
        int soldierCount = 0;
        if(gameState == 0){
            if(commandCooldown[0] < 0 && soldierCount / rc.getArchonCount() >= 30){
                boolean archFound = false;
                for(int i = 0; i<4 && attackingArchon == -1; i++){
                    rushArchon(rc);
                }

            }
        } else if (gameState == 1){
            if(Communications.getEnemyArchonIDFromIndex(rc, attackingArchon) == -1){
                attackingArchon = -1;
                gameState = 0;
                if(soldierCount / rc.getArchonCount() >= 20){
                    rushArchon(rc);
                }
            } else if(commandCooldown[0] <= 0){
                gameState = 0;
                attackingArchon = -1;
            }
        }



        
        // If there are less than 4 miners per archon
        // Build a miner in any direction (but take turns)

        if(rc.isActionReady()){
            if(turn == 1){
                buildTowardsLowRubble(rc, RobotType.MINER, turn);
            }
            else if(turn < 5 * rc.getArchonCount()){
                if (miners < 4 && turn % rc.getArchonCount() == Communications.getTeamArchonIndexFromID(rc,rc.getID())) {
                    if (rc.getTeamLeadAmount(rc.getTeam()) >= 50) {
                        buildTowardsLowRubble(rc, RobotType.MINER, turn);
                    }
                }
            } else{
                if (miners < 4) {
                    if (rc.getTeamLeadAmount(rc.getTeam()) >= 50) {
                        buildTowardsLowRubble(rc, RobotType.MINER, turn);
                    }
                } else if (soldierCount / rc.getArchonCount() < 5 ){
                    if (rc.getTeamLeadAmount(rc.getTeam()) >= 75) {
                        buildTowardsLowRubble(rc, RobotType.SOLDIER, turn);


                    }
                } else if (minerCount / rc.getArchonCount() < 10 ){
                    if (rc.getTeamLeadAmount(rc.getTeam()) >= 50) {
                        buildTowardsLowRubble(rc, RobotType.MINER, turn);
                    }
                } else if (soldierCount/rc.getArchonCount() < 30 ){
                    if (rc.getTeamLeadAmount(rc.getTeam()) >= 75) {
                        buildTowardsLowRubble(rc, RobotType.SOLDIER, turn);


                    }
                }
            }


        }


    }

    static void rushArchon(RobotController rc) throws GameActionException {
        for(int i = 0; i<4 && attackingArchon == -1; i++){
            if(Communications.getEnemyArchonIDFromIndex(rc, i) != -1){ // found
                MapLocation attackLoc = Communications.getEnemyArchonLocationByIndex(rc, i);
                Communications.sendAttackCommand(rc, attackLoc, RobotType.ARCHON, rc.getID());
                attackingArchon = i;

                commandCooldown[0] = 100 * (rc.getMapHeight() + rc.getMapWidth()) / 120;
                gameState = 1;
            }
        }
    }

    static void buildTowardsLowRubble(RobotController rc, RobotType type, int turn) throws GameActionException {
        Direction [] dirs = Arrays.copyOf(Helper.directions, Helper.directions.length);
        Arrays.sort(dirs, (a, b) -> getRubble(rc, a) - getRubble(rc, b));
        for (Direction d: dirs) {
            if (rc.canBuildRobot(type, d)) {
                rc.buildRobot(type, d);
                switch(type) {
                    case MINER:
                        if (miners < 15) {
                            Communications.incrementArchonMinerCount(rc, id);
                        }
                        miners++;
                        Communications.incrementArchonTurn(rc);
                        break;
                    case SOLDIER:
                        soldiers++;
                        Communications.incrementArchonTurn(rc);
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
