package karthiktestp;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

strictfp class MinerStrategy {
    static Direction exploreDir = null;
    /**
     * Run a single turn for a Miner.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void runMiner(RobotController rc) throws GameActionException {
        // Try to mine on squares around us.
        if(exploreDir == null) {
            RobotPlayer.rng.setSeed(rc.getID());
            exploreDir = RobotPlayer.directions[RobotPlayer.rng.nextInt(RobotPlayer.directions.length)];
        }
        rc.setIndicatorString(exploreDir.toString());
        MapLocation me = rc.getLocation();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                MapLocation mineLocation = new MapLocation(me.x + dx, me.y + dy);
                // Notice that the Miner's action cooldown is very low.
                // You can mine multiple times per turn!
                while (rc.canMineGold(mineLocation)) {
                    rc.mineGold(mineLocation);
                }
                while (rc.canMineLead(mineLocation) && rc.senseLead(mineLocation) > 1) {
                    rc.mineLead(mineLocation);
                }
            }
        }

        int visionRadius = rc.getType().visionRadiusSquared;
        MapLocation[] nearbyLocations = rc.getAllLocationsWithinRadiusSquared(me, visionRadius);
        MapLocation targetLocation = null;
        int distanceToTarget = Integer.MAX_VALUE;
        //for each location is there a resource in that location
        for (MapLocation tryLocation: nearbyLocations) {
            if (rc.senseLead(tryLocation) > 1 || rc.senseGold(tryLocation) > 0) {
                //yes go there
                int distanceTo = me.distanceSquaredTo(tryLocation);
                if (distanceTo < distanceToTarget) {
                    targetLocation = tryLocation;
                    distanceToTarget = distanceTo;
                }
            }
        }
        //to see enemies
        // if you see a target location with resources on it, move towards it
        if (targetLocation != null) {
            Direction toMove = Pathing.pathTo(rc, targetLocation);
            if (rc.canMove(toMove)) {
                rc.move(toMove);
            }
        }
        else {
            if (rc.canMove(exploreDir)) {
                rc.move(exploreDir);
            }
            else if (!rc.onTheMap(rc.getLocation().add(exploreDir))) {
                exploreDir = exploreDir.opposite();
            }
        }
        // Also try to move randomly.
    }
}