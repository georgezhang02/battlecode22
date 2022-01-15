package dontatme;

import battlecode.common.*;

import java.awt.*;

public strictfp class Builder {
    /**
     * Run a single turn for a Builder.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    //checks if the builder has moved towards the center already
    static boolean movedTowardsCenter = false;
    static boolean hasBuiltTower = false;
    static Pathfinder pathfinder;
    public static void run(RobotController rc) throws GameActionException {
        //part 1: see if there are any nearby buildings that are in prototype mode
        searchNonMoveActions(rc);

        //now just explore
        Direction dir = pathfinder.pathToExplore();
        while (rc.canMove(dir)) {
            rc.move(dir);
            searchNonMoveActions(rc);
        }
    }
    static void searchNonMoveActions(RobotController rc) throws GameActionException {
        RobotInfo [] buildings = getNearbyTowers(rc);
        RobotInfo [] protoBuildings = findPrototypeTowers(rc, buildings);

        //that means that there are prototype buildings, and see if we can prototype
        if (protoBuildings != null) {
            int counter = 0;
            repairBuild(rc, protoBuildings);
        }
        //check if there's a level 1 building that can be mutated
        if (buildings[0] != null ) {
            seeBuildingMutation(rc, buildings);
        }
        //means that there is a nearby building
        //check if it can be repaired
        if (buildings[0] != null) {
            repairBuild(rc, buildings);
        }
    }
    static void seeBuildingMutation(RobotController rc, RobotInfo [] buildings) throws GameActionException {
        int counter = 0;
        while (counter < buildings.length && buildings[counter] != null ) {
            if (buildings[counter].getLevel() == 1 && buildings[counter].getType().equals(RobotType.WATCHTOWER)) {
                if (rc.canMutate(buildings[counter].getLocation()))//also implement watchtower analysis
                    rc.mutate(buildings[counter].getLocation());
            }
            counter++;
        }
    }
    static void repairBuild(RobotController rc, RobotInfo [] buildings) throws GameActionException{
        int counter = 0;
        while (counter < buildings.length && buildings[counter] != null) {
            if (rc.canRepair(buildings[counter].getLocation())) {
                rc.repair(buildings[counter].getLocation());
            }
            counter++;
        }
    }

    static void advance(RobotController rc) throws GameActionException {
        //nub -> in development
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

    static Direction findPlaceToBuildTower(RobotController rc) throws GameActionException {
        Direction dir = Helper.directions[0];
        int counter = 1;
        boolean canBuildTower = rc.canBuildRobot(RobotType.WATCHTOWER, dir);
        int lowestRubble = Integer.MAX_VALUE;
        int bestIndex = Integer.MAX_VALUE;

        while (counter < Helper.directions.length) {
            dir = Helper.directions[counter];
            //find the rubble count on all squares that a watchtower can be built
            if (rc.canBuildRobot(RobotType.WATCHTOWER, dir)) {
                //finds new position
                MapLocation location = new MapLocation(rc.getLocation().x +
                        Helper.directions[counter].dx, rc.getLocation().y +
                        Helper.directions[counter].dy);
                int rubbleCount = rc.senseRubble(location);
                if (rubbleCount < lowestRubble) {
                    lowestRubble = rubbleCount;
                    bestIndex = counter;
                }
            }
            counter++;
        }

        //if there were no towers that could be built
        if (bestIndex == Integer.MAX_VALUE) {
            dir = null;
        }
        else {
            dir = Helper.directions[bestIndex];
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
    static RobotInfo [] getAllies(RobotController rc) throws GameActionException {
        Team ally = rc.getTeam();
        int radius = rc.getType().actionRadiusSquared;
        return rc.senseNearbyRobots(radius, ally);
    }
    static RobotInfo [] getNearbyTowers(RobotController rc) throws GameActionException {
        RobotInfo [] allies = getAllies(rc);

        //an ArrayList could be used here, but I believe it will use much more Bytecode
        RobotInfo [] buildings = new RobotInfo [allies.length];
        int buildingCounter = 0;

        for (int i = 0; i < allies.length; i++) {
            if (allies[i].getType().isBuilding()) {
                buildings[buildingCounter] = allies[i];
                buildingCounter++;
            }
        }
        return buildings;
    }
    static RobotInfo [] findPrototypeTowers(RobotController rc, RobotInfo [] buildings)
            throws GameActionException {

        RobotInfo [] protoBuildings = new RobotInfo[buildings.length];
        if (buildings == null)
            return null;
        else {
            int counter = 0;
            int protoCounter = 0;
            while (buildings[counter] != null && counter < buildings.length) {
                if (buildings[counter].getMode() == RobotMode.PROTOTYPE) {
                    protoBuildings[protoCounter] = buildings[counter];
                    protoCounter++;
                }
                counter++;
            }
        }
        return protoBuildings;
    }
}
