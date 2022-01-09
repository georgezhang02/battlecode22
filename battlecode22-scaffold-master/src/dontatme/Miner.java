package dontatme;

import battlecode.common.*;

public strictfp class Miner {

    static int archonIndex = -1;

    /**
     * Run a single turn for a Miner.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {

        // Save the index of the archon it spawned from
        if (archonIndex == -1) {
            for (RobotInfo info : rc.senseNearbyRobots()) {
                if (info.getType() == RobotType.ARCHON) {
                    archonIndex = (info.getID() - 2) / 2;
                }
            }
        }

        // Find the given lead location
        MapLocation me = rc.getLocation();
        int leadLocation = rc.readSharedArray(archonIndex);

        // If there is lead left
        if (leadLocation != 61) {

            // Mine around if possible
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    MapLocation mineLocation = new MapLocation(me.x + dx, me.y + dy);
                    while (rc.canMineGold(mineLocation)) {
                        rc.mineGold(mineLocation);
                    }
                    while (rc.canMineLead(mineLocation)) {
                        rc.mineLead(mineLocation);
                    }
                }
            }

            // If not on lead
            if (rc.senseLead(me) == 0) {

                // Move towards lead within miner vision if possible
                boolean found = false;
                MapLocation[] allLoc = rc.getAllLocationsWithinRadiusSquared(rc.getLocation(), 20);
                for (MapLocation loc : allLoc) {
                    if (rc.senseLead(loc) > 0) {
                        Direction dir = Pathing.pathTo(rc, new MapLocation(loc.x, loc.y));
                        if (rc.canMove(dir)) {
                            rc.move(dir);
                            found = true;
                            break;
                        }
                    }
                }

                // Otherwise, move towards lead location given by archon
                if (!found) {
                    int leadX = leadLocation / 64;
                    int leadY = leadLocation % 64;
                    Direction dir = Pathing.pathTo(rc, new MapLocation(leadX, leadY));
                    if (rc.canMove(dir)) {
                        rc.move(dir);
                    }
                }
            }
        }

        // If there is no lead left around archon,
        else {

        }
    }
}
