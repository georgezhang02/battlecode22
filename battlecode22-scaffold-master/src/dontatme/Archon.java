package dontatme;

import java.util.Arrays;

import battlecode.common.*;

public strictfp class Archon {

    static int id = -1;
    static MapLocation me = null;
    static int miners = 0, soldiers = 0;
    static int comms = 0;

    /**
     * Run a single turn for an Archon.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {

        // Get the archon ID if needed
        if (id == -1) {
            id = rc.getID();
        }

        // Write archon location if needed
        if (me == null || !rc.getLocation().equals(me)) {
            me = rc.getLocation();
            Communications.setTeamArchonLocation(rc, rc.getID(), me);
        }
        
        // If there is lead left, save the first open lead location in the shared array
        MapLocation leadLoc = Communications.getArchonVisionLead(rc, id);
        if (leadLoc.y != 61) {
            MapLocation[] allLoc = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(), 34);
            boolean found = false;
            for (MapLocation loc : allLoc) {
                if (rc.senseLead(loc) > 0 && rc.senseRobotAtLocation(loc) == null) {
                    Communications.setArchonVisionLead(rc, id, loc);
                    found = true;
                    break;
                }
            }
            if (!found) {
                Communications.setArchonVisionNoLead(rc, id);
            }
        }
        
        // If there are less than 4 miners per archon
        // Build a miner in any direction (but take turns)
        int turn = Communications.getArchonTurn(rc);
        if (turn + 1 == id / 2) {
            if (miners < 4) {
                if (rc.getTeamLeadAmount(rc.getTeam()) >= 50) {
                    buildTowardsLowRubble(rc, RobotType.MINER, turn);
                }
            } else {
                if (rc.getTeamLeadAmount(rc.getTeam()) >= 75) {
                    buildTowardsLowRubble(rc, RobotType.SOLDIER, turn);
                }
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
                            Communications.incrementMinerCount(rc, id);
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
