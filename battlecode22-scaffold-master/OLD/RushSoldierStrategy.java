package dontatme;

import battlecode.common.*;

strictfp class RushSoldierStrategy {
    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static int addY = 0;
    static Pathfinder pathfinder;
    static void runSoldier(RobotController rc) throws GameActionException {

        if(pathfinder == null){
            pathfinder = new BFPathing20(rc);
        }

        // Try to attack someone
        int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);
        if (enemies.length > 0) {
            MapLocation toAttack = enemies[0].location;
            if (rc.canAttack(toAttack)) {
                rc.attack(toAttack);
            }
        }
        MapLocation[] PossibleEnemyLocations = findAreasToAttack(rc);
        Direction dir = pathfinder.pathToTarget(PossibleEnemyLocations[rc.getID() % 2], true);
        if (rc.canMove(dir)) {
            rc.move(dir);
            //System.out.println("I moved!");
        }
    }
    static MapLocation [] findAreasToAttack(RobotController rc) throws GameActionException {
        //use decision-making to identify two potential areas of enemy archons
        int archonCount = rc.getArchonCount();

        //find if point diagonal to an archon has an ally on it
        int firstPointX = rc.readSharedArray(archonCount + 1) / 64;
        int firstPointY = rc.readSharedArray(archonCount + 1) % 64;
        int diagPointX = GameConstants.MAP_MAX_WIDTH - firstPointX;
        int diagPointY = GameConstants.MAP_MAX_HEIGHT - firstPointY;

        //check if that point is a current archon location
        boolean foundPoint = false;
        for (int i = archonCount + 1; i < 2 * archonCount + 1; i++) {
            int xVal = rc.readSharedArray(i) / 64;
            int yVal = rc.readSharedArray(i) % 64;
            if (diagPointX == xVal && diagPointY == yVal) {
                diagPointX = rc.readSharedArray(archonCount + 1); //set it to be the other hypotenuse location
                foundPoint = true;
            }
        }

        int secondDiagPointX;
        int secondDiagPointY;
        if (foundPoint) {
            secondDiagPointX = GameConstants.MAP_MAX_WIDTH - rc.readSharedArray(0);
            secondDiagPointY = rc.readSharedArray(archonCount + 1) % 64;
        }
        else {
            secondDiagPointX = rc.readSharedArray(archonCount + 1) / 64;
            secondDiagPointY = diagPointY;
        }

        MapLocation [] PossibleEnemyLocations = new MapLocation[2];
        PossibleEnemyLocations[0] = new MapLocation(diagPointX, diagPointY);
        PossibleEnemyLocations[1] = new MapLocation(secondDiagPointX, secondDiagPointY);
        return PossibleEnemyLocations;
    }
}
