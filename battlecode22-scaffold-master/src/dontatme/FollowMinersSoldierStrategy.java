package dontatme;

import battlecode.common.*;

import java.util.*;

strictfp class FollowMinersSoldierStrategy {
    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static int addY = 0;
    static void runSoldier(RobotController rc) throws GameActionException {

        Pathfinder.rc = rc;

        // Try to attack someone
        int radius = rc.getType().actionRadiusSquared;
        Team ally = rc.getTeam();
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);
        if (enemies.length > 0) {
            MapLocation toAttack = enemies[0].location;
            if (rc.canAttack(toAttack)) {
                rc.attack(toAttack);
            }
        }

        //set null variables so we can compare regular situations against them
        Direction dir = null;
        MapLocation TargetLocation = null;

        //generate array of all nearby arrays
        RobotInfo[] allies = rc.senseNearbyRobots(radius, ally);
        ArrayList<Integer> MinerLocs = new ArrayList<Integer>(); //stores miner locations
        int counter = 0;
        //if there are allies near me
        if (allies.length > 0) {
            boolean isMiner = false;


            while (counter < allies.length) {
                if (allies[counter].getType().equals(RobotType.MINER))
                    MinerLocs.add(counter);
                counter += 1;
            }
            //check if minerlocs has values in it, this means that there are nearby miners

            counter = 0; //iterator
        }
        boolean isGoodTarget = false;
        if (!MinerLocs.isEmpty()) {


            //a good target is a miner that is within the soldier's vision radius but not attack radius
            while (counter < MinerLocs.size() && !isGoodTarget) {
                MapLocation allyPos = allies[MinerLocs.get(counter)].getLocation();
                if (rc.canSenseLocation(allyPos) &&
                    !rc.getLocation().isWithinDistanceSquared(allyPos, 5)) {
                    isGoodTarget = true;
                }
                else {
                    counter++;
                }
            }

                //good target found

        }
        if (isGoodTarget) {
            int locX = allies[MinerLocs.get(counter)].getLocation().x;
            int locY = allies[MinerLocs.get(counter)].getLocation().y;
            Pathfinder.target = new MapLocation(locX, locY);
            dir = Pathfinder.pathToTarget();
        }

        if (rc.canMove(dir)) {
            rc.move(dir);
        }

    }



    static MapLocation getClosestArchon(MapLocation [] array) {
        /*In-progress, intended to route soldiers away from archons*/
        return null;
    }
}
