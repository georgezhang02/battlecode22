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

    static RobotController rc;

    static MapLocation curTarget;
    static int moves = 0;
    static int turn = 0;
    public static void run(RobotController robotController) throws GameActionException {

        Communications.runStart(rc);

        turn++;
        if(pathfinder == null){
            pathfinder = new BFPathing20(rc);
        }

        if(rc == null){
            rc = robotController;
        }

        RobotInfo[] robots = rc.senseNearbyRobots();

        if(turn < 5  && (curTarget== null || !pathfinder.targetWithinRadius(curTarget, 20))){
            curTarget = getClosestCorner(rc);
            move(pathfinder.pathToTarget(curTarget, false));

        }
        else if (robots.length > 1  && moves < 10){
            int index = 0;
            MapLocation[]nearby = new MapLocation[10];
            for(int i = 0; i< robots.length && index < 10; i++){

                nearby[index++] = robots[i].getLocation();
            }
            move(pathfinder.pathAwayFrom(nearby));
        } else{
            if(rc.isActionReady() && rc.getTeamLeadAmount(rc.getTeam()) >= 180){
                rc.buildRobot(RobotType.LABORATORY, findDirLowestRubble(rc, rc.getLocation()));
            }
        }





    }

    private static MapLocation getClosestCorner(RobotController rc){
        MapLocation ans = new MapLocation(61, 61);
        int dist = 3600;

        MapLocation curPos = rc.getLocation();
        MapLocation test = new MapLocation(0, 0);

        if(curPos.distanceSquaredTo(test) < dist){
            ans = test;
            dist = curPos.distanceSquaredTo(test);
        }
        test = new MapLocation(rc.getMapWidth()-1, 0);

        if(curPos.distanceSquaredTo(test) < dist){
            ans = test;
            dist = curPos.distanceSquaredTo(test);
        }
        test = new MapLocation(0, rc.getMapHeight()-1);

        if(curPos.distanceSquaredTo(test) < dist){
            ans = test;
            dist = curPos.distanceSquaredTo(test);
        }
        test = new MapLocation(rc.getMapWidth()-1, rc.getMapHeight()-1);

        if(curPos.distanceSquaredTo(test) < dist){
            ans = test;
            dist = curPos.distanceSquaredTo(test);
        }

        return ans;

    }

    static void move(Direction dir) throws GameActionException {
        if(rc.isMovementReady()){
            moves++;
        }
        if(dir != null && rc.canMove(dir)){
            rc.move(dir);
            pathfinder.explorer.updateVisited();;
        }
    }
    


    private static Direction findDirLowestRubble(RobotController rc, MapLocation myLoc)
        throws GameActionException{
        Direction dir = null;
        int minRubble = GameConstants.MAX_RUBBLE;

        for (int i = 0; i < Helper.directions.length; i++) {
            MapLocation tempLoc = myLoc.add(Helper.directions[i]);

            //checks if there is a robot on a square and if it has a lower rubble count
            //than what is currently stored
            if (rc.senseRobotAtLocation(tempLoc) == null && rc.senseRubble(tempLoc) < minRubble) {
                dir = Helper.directions[i];
                minRubble = rc.senseRubble(tempLoc);
            }
        }

        if (dir == null)
            return null;
        return dir;
    }
}
