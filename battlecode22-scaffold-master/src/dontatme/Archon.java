package dontatme;

import battlecode.common.*;

public strictfp class Archon {

    static int archonIndex;

    public Archon() {
        archonIndex = -1;
    }

    /**
     * Run a single turn for an Archon.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {

        // Set the index where the archon will write the lead location
        if (archonIndex == -1) {
            archonIndex = (rc.getID() - 2) / 2;
        }

        // Save the first available lead location
        if (rc.readSharedArray(archonIndex) == 0) {
            MapLocation me = rc.getLocation();
            for (int dx = -5; dx <= 5; dx++) {
                for (int dy = -5; dy <= 5; dy++) {
                    MapLocation leadLocation = new MapLocation(me.x + dx, me.y + dy);
                    if (rc.canSenseLocation(leadLocation) && rc.senseLead(leadLocation) > 0) {
                        rc.writeSharedArray(archonIndex, leadLocation.x * 64 + leadLocation.y);
                        break;
                    }
                }
            }
        }

        // Build a miner in any direction
        for (Direction dir : Helper.directions) {
            if (rc.canBuildRobot(RobotType.MINER, dir)) {
                rc.buildRobot(RobotType.MINER, dir);
                rc.setIndicatorString("built miner");
                break;
            }
        }
    }
}
