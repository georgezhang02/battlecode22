package dontatme;

import battlecode.common.*;

import java.util.*;

strictfp class FollowMinersSoldierStrategy {
    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static int addY = 0;
    static Pathfinder pathfinder;
    static void runSoldier(RobotController rc) throws GameActionException {

        boolean followMiners = true;
        Direction dir = Direction.CENTER;

        if(pathfinder == null){
            pathfinder = new BFPathing20(rc);
        }
            for (int i = 9; i <= 12 && followMiners; i++) {
                int archonInfo = rc.readSharedArray(i);
                if (archonInfo / 4096 == 1) {

                    followMiners = false;
                    dir = pathfinder.pathToTarget(new MapLocation(archonInfo / 64, archonInfo % 64), true);
                }
            }


        MapLocation target = null;
        if(followMiners){
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

            RobotInfo[] allies = rc.senseNearbyRobots(radius, ally);
            ArrayList<Integer> MinerLocs = new ArrayList<Integer>(); //stores miner locations
            int counter = 0;
            //if there are allies near me
            if (allies.length > 0) {


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
                    if (!rc.getLocation().isWithinDistanceSquared(allyPos, 5)) {
                        target = allyPos;

                        isGoodTarget = true;
                    }
                    else {
                        counter++;
                    }
                }

                //good target found

            }

        }

            if(target == null || dir.equals(Direction.CENTER)){
                MapLocation[] ml = RushSoldierStrategy.findAreasToAttack(rc);
                dir = pathfinder.pathToTarget(target,true);

            }




        if (rc.canMove(dir)) {
            rc.move(dir);
        }

        // Sense for enemy archon
        EnemyArchonSensing.UpdateEnemyLocation(rc);
    }









    static MapLocation getClosestArchon(MapLocation [] array) {
        /*In-progress, intended to route soldiers away from archons*/
        return null;
    }
}
