package karthiktestp;

import battlecode.common.*;

import java.util.Map;

strictfp class RushSoldierStrategy {
    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static int addY = 0;
    static void runSoldier(RobotController rc) throws GameActionException {
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
        Direction dir = Pathing.pathTo(rc, PossibleEnemyLocations[rc.getID() % 2]);
        if (rc.canMove(dir)) {
            rc.move(dir);
            //System.out.println("I moved!");
        }
    }
    static MapLocation [] findAreasToAttack(RobotController rc) throws GameActionException {
        //use decision-making to identify two potential areas of enemy archons
        int archonCount = rc.getArchonCount();

        //find if point diagonal to an archon has an ally on it
        int firstPointX = rc.readSharedArray(0);
        int firstPointY = rc.readSharedArray(1);
        int diagPointX = GameConstants.MAP_MAX_WIDTH - firstPointX;
        int diagPointY = GameConstants.MAP_MAX_HEIGHT - firstPointY;

        //check if that point is a current archon location
        int counter = 0;
        boolean foundPoint = false;
        while (counter <= 2 * archonCount) {
            int xVal = rc.readSharedArray(counter);
            int yVal = rc.readSharedArray(counter + 1);
            if (diagPointX == xVal && diagPointY == yVal) {
                diagPointX = rc.readSharedArray(0); //set it to be the other hypotenuse location
                foundPoint = true;
            }
            counter = counter + 2;
        }

        int secondDiagPointX;
        int secondDiagPointY;
        if (foundPoint) {
            secondDiagPointX = GameConstants.MAP_MAX_WIDTH - rc.readSharedArray(0);
            secondDiagPointY = rc.readSharedArray(1);
        }
        else {
            secondDiagPointX = rc.readSharedArray(0);
            secondDiagPointY = diagPointY;
        }

        MapLocation [] PossibleEnemyLocations = new MapLocation[2];
        PossibleEnemyLocations[0] = new MapLocation(diagPointX, diagPointY);
        PossibleEnemyLocations[1] = new MapLocation(secondDiagPointX, secondDiagPointY);
        return PossibleEnemyLocations;
    }
}
