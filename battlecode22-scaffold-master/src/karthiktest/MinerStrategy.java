package karthiktest;

import battlecode.common.*;
import java.util.Random;

strictfp class MinerStrategy {
    /**
     * Run a single turn for a Miner.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void runMiner(RobotController rc) throws GameActionException {
        // Try to mine on squares around us.
        MapLocation me = rc.getLocation();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                MapLocation mineLocation = new MapLocation(me.x + dx, me.y + dy);
                // Notice that the Miner's action cooldown is very low.
                // You can mine multiple times per turn!
                while (rc.canMineGold(mineLocation)) {
                    rc.mineGold(mineLocation);
                }
                while (rc.canMineLead(mineLocation)) {
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
            if (rc.senseLead(tryLocation) > 0 || rc.senseGold(tryLocation) > 0) {
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
            Direction toMove = me.directionTo(targetLocation);
            if (rc.canMove(toMove)) {
                rc.move(toMove);
            }
        }
        // Also try to move randomly.
        int directionIndex = RobotPlayer.rng.nextInt(RobotPlayer.directions.length);
        Direction dir = RobotPlayer.directions[directionIndex];
        if (rc.canMove(dir)) {
            rc.move(dir);
            System.out.println("I moved!");
        }
    }
}