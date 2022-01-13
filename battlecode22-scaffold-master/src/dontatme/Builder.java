package dontatme;

import battlecode.common.*;

public strictfp class Builder {
    /**
     * Run a single turn for a Builder.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    //checks if the builder has moved towards the center already
    static boolean movedTowardsCenter = false;
    static boolean hasBuiltTower = false;
    public static void run(RobotController rc) throws GameActionException {
        //Basic goal:
        //2 states: attack, defend
        //state 1: attack
        //state 2: defend

    }
    static void advance(RobotController rc) throws GameActionException {
        //part 1: see if there are any buildings in prototype form to upgrade them


        //part 2:
        //attack state
        //find nearest archon
        //path away from that archon
        //if it encounters a certain number of enemy troops, build a watchtower

        MapLocation nearestArchon = findNearestArchon(rc);
        int activationNumber = 4; //number of nearby enemies to trigger tower building
        int numberNearbyEnemies = getNumberOfNearbyEnemies(rc);
        if (rc.isActionReady() && numberNearbyEnemies >= activationNumber) {
            Direction dir = findPlaceToBuildTower(rc);
            if (dir != null)
                rc.buildRobot(RobotType.WATCHTOWER, dir);
            else {
                //fill in with path planning code
                //if it can't build a tower, it needs to continue advancing
            }
        }

        else {
           //fill in with path planning code
        }
    }

    static Direction findPlaceToBuildTower(RobotController rc) {
        Direction dir = RobotPlayer.directions[0];
        int counter = 1;
        boolean canBuildTower = rc.canBuildRobot(RobotType.WATCHTOWER, dir);
        while (!canBuildTower && counter < RobotPlayer.directions.length) {
            dir = RobotPlayer.directions[counter];
            canBuildTower = rc.canBuildRobot(RobotType.WATCHTOWER, dir);
            counter++;
        }

        //if there were no towers that could be built
        if (!canBuildTower) {
            dir = null;
        }
        return dir;
    }

    static MapLocation findNearestArchon(RobotController rc) throws GameActionException {
        MapLocation nearestArchon = new MapLocation(0, 0); //placeholder
        return nearestArchon;
    }
    static int getNumberOfNearbyEnemies(RobotController rc) throws GameActionException {
        //find number of nearby enemy opponents
        Team opponent = rc.getTeam().opponent();
        int radius = rc.getType().actionRadiusSquared;
        RobotInfo [] enemyBots = rc.senseNearbyRobots(radius, opponent);
        return enemyBots.length;
    }
}
