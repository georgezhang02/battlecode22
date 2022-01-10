package dontatme;

import java.util.Arrays;

import battlecode.common.*;

public strictfp class Archon {

    static int archonIndex = -1;
    static int miners = 0, soldiers = 0;
    static int comms = 0;

    /**
     * Run a single turn for an Archon.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {

        // Set the index where the archon will write the lead location if not yet done
        // Also write the archon location
        if (archonIndex == -1) {
            archonIndex = rc.getID() / 2;
            rc.writeSharedArray(archonIndex + 4, rc.getLocation().x*64+rc.getLocation().y);
        }

        // Read current array value
        int turn = rc.readSharedArray(0);
        int arrayVal = rc.readSharedArray(archonIndex);
        
        // If there is lead left, save the first open lead location in the shared array
        if (arrayVal % 64 != 61) {
            MapLocation[] allLoc = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(), 34);
            boolean found = false;
            for (MapLocation loc : allLoc) {
                if (rc.senseLead(loc) > 0 && rc.senseRobotAtLocation(loc) == null) {
                    comms = miners * 4096 + loc.x * 64 + loc.y;
                    found = true;
                    break;
                }
            }
            if (!found) {
                comms = miners * 4096 + 61;
            }
        }

        
        // If there are less than 4 miners per archon
        // Build a miner in any direction (but take turns)
        if (turn + 1 == archonIndex) {
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

        rc.writeSharedArray(archonIndex, comms);
    }

    static void buildTowardsLowRubble(RobotController rc, RobotType type, int turn) throws GameActionException {
        Direction [] dirs = Arrays.copyOf(Helper.directions, Helper.directions.length);
        Arrays.sort(dirs, (a, b) -> getRubble(rc, a) - getRubble(rc, b));
        for (Direction d: dirs) {
            if (rc.canBuildRobot(type, d)) {
                rc.buildRobot(type, d);
                switch(type) {
                    case MINER:
                        miners++;
                        if (comms / 4096 < 15)
                            comms += 4096;
                        rc.writeSharedArray(0, (turn + 1) % rc.getArchonCount());
                        break;
                    case SOLDIER:
                        soldiers++;
                        rc.writeSharedArray(0, (turn + 1) % rc.getArchonCount());
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
