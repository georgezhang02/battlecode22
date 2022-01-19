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
    static Pathfinder pathfinder;
    static RobotController rc;
    public static void run(RobotController rc) throws GameActionException {
        //part 1: see if there are any nearby buildings that are in prototype mode
        //searchNonMoveActions(rc);

        //now just explore
        if (pathfinder == null){
            pathfinder = new BFPathing20(rc);
        }
        Direction dir = pathfinder.pathToExplore();

        while (rc.canMove(dir)) {
            buildTowersNearBase(rc);
            rc.move(dir);
            //searchNonMoveActions(rc); //error here
        }
    }
    static void buildTowersNearBase(RobotController rc) throws GameActionException {

        RobotInfo [] allies = getAllies(rc);
        int minAllyCount = 4;
        Direction buildPlace = null;

        //number of allies near me implies that I am relatively inside safe space, so build there
        if (allies.length >= minAllyCount && rc.isActionReady()) {
            buildPlace = findPlaceToBuildTower(rc);
        }

        if (buildPlace != null)
            rc.buildRobot(RobotType.WATCHTOWER, buildPlace);
    }

    static void searchNonMoveActions(RobotController rc) throws GameActionException {
        RobotInfo [] towers = getNearbyTowers(rc);
        RobotInfo [] protoTowers = findPrototypeTowers(rc, towers);

        //that means that there are prototype towers, and see if we can prototype
        if (protoTowers != null) {
            int counter = 0;
            repairBuild(rc, protoTowers);
        }
        //check if there's a level 1 tower that can be mutated
        if (towers[0] != null) {
            seeTowerMutation(rc, towers);
        }
        //means that there is a nearby tower
        //check if it can be repaired
        if (towers[0] != null) {
            repairBuild(rc, towers);
        }
    }
    static void seeTowerMutation(RobotController rc, RobotInfo [] towers) throws GameActionException {
        int counter = 0;
        while (counter < towers.length && towers[counter] != null ) {
            if (towers[counter].getLevel() == 1) {
                if (rc.canMutate(towers[counter].getLocation()))//also implement watchtower analysis
                    rc.mutate(towers[counter].getLocation());
            }
            counter++;
        }
    }
    static void repairBuild(RobotController rc, RobotInfo [] towers) throws GameActionException{
        int counter = 0;
        while (counter < towers.length && towers[counter] != null) {
            if (rc.canRepair(towers[counter].getLocation())) {
                rc.repair(towers[counter].getLocation());
            }
            counter++;
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
        RobotInfo [] towers = new RobotInfo [allies.length];
        int buildingCounter = 0;

        for (int i = 0; i < allies.length; i++) {
            if (allies[i].getType().equals(RobotType.WATCHTOWER)) {
                towers[buildingCounter] = allies[i];
                buildingCounter++;
            }
        }
        return towers;
    }
    static RobotInfo [] findPrototypeTowers(RobotController rc, RobotInfo [] towers)
            throws GameActionException {

        RobotInfo [] protoTowers = new RobotInfo[towers.length];
        if (towers == null)
            return null;
        else {
            int counter = 0;
            int protoCounter = 0;
            while (towers[counter] != null && counter < towers.length) {
                if (towers[counter].getMode() == RobotMode.PROTOTYPE) {
                    protoTowers[protoCounter] = towers[counter];
                    protoCounter++;
                }
                counter++;
            }
        }
        return protoTowers;
    }
}