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
    public static void run(RobotController rc) throws GameActionException {
        //part 1: see if there are any nearby buildings that are in prototype mode
        RobotInfo [] buildings = getNearbyTowers(rc);
        RobotInfo [] protoBuildings = findPrototypeTowers(rc, buildings);

        //that means that there are prototype buildings
        if (protoBuildings != null) {
            int counter = 0;
            while (counter < protoBuildings.length && protoBuildings[counter] != null
                    && rc.canRepair(protoBuildings[counter].getLocation())) {
                rc.repair(protoBuildings[counter].getLocation());
                counter++;
            }
        }
        //Basic goal:
        //2 states: attack, defend
        //state 1: attack
        //state 2: defend

    }
    static void advance(RobotController rc) throws GameActionException {

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
        Direction dir = Helper.directions[0];
        int counter = 1;
        boolean canBuildTower = rc.canBuildRobot(RobotType.WATCHTOWER, dir);
        while (!canBuildTower && counter < Helper.directions.length) {
            dir = Helper.directions[counter];
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
