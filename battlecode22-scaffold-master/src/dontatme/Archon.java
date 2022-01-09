package dontatme;

import battlecode.common.*;

public strictfp class Archon {

    static int archonIndex = -1;

    /**
     * Run a single turn for an Archon.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {

        // Set the index where the archon will write the lead location if not yet done
        if (archonIndex == -1) {
            archonIndex = (rc.getID() - 2) / 2;
        }
        rc.setIndicatorString(Integer.toString(archonIndex));

        // If there is lead left, save the first available lead location in the shared array
        if (rc.readSharedArray(archonIndex) != 61) {
            MapLocation[] allLoc = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(), 34);
            boolean found = false;
            for (MapLocation loc : allLoc) {
                if (rc.senseLead(loc) > 0) {
                    rc.writeSharedArray(archonIndex, loc.x * 64 + loc.y);
                    found = true;
                    break;
                }
            }
           if (!found) {
               rc.writeSharedArray(archonIndex, 61);
           }
        }

        // If no lead locations left
        else {

        }

        // Build a miner in any direction
        for (Direction dir : Helper.directions) {
            if (rc.canBuildRobot(RobotType.MINER, dir)) {
                rc.buildRobot(RobotType.MINER, dir);
                break;
            }
        }
    }
}
