package dontatme;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;


public abstract class Pathfinder {
    static RobotController rc;
    static int minDistToTarget = 0;

    static MapLocation lastPos;

    static Explorer explorer;
    static boolean exploring;

    public Pathfinder(RobotController rc) throws GameActionException{
        this.rc = rc;
        this.explorer = new Explorer(rc);
        lastPos = rc.getLocation();
    }

    boolean targetWithinRadius(MapLocation target){
        return rc.getLocation().distanceSquaredTo(target) < minDistToTarget * minDistToTarget;
    }

    Direction pathToExplore() throws GameActionException{
        if(!exploring || rc.getLocation().equals(explorer.target)){
            int width = rc.getMapWidth();
            int height =rc.getMapHeight();
            explorer.getExploreTarget(10, width, height);
        }
        Direction dir = pathToTarget(explorer.target, true);
        exploring = true;
        return dir;

    }



    Direction pathAwayFrom(MapLocation[]mapLocations) throws GameActionException {
        exploring = false;
        MapLocation curPos = rc.getLocation();
        int x = curPos.x;
        int y = curPos.y;
        for(MapLocation ml : mapLocations){
            double vect = 4.5/Math.sqrt(curPos.distanceSquaredTo(ml));
            int xdiff = curPos.x - ml.x;
            int ydiff = curPos.y - ml.y;

            x += (int)(xdiff * vect);
            y += (int)(ydiff * vect);
        }
        MapLocation target = new MapLocation(x, y);
        return pathToTargetGreedy(target);
    }
    Direction pathToTarget(MapLocation target, boolean useGreedy) throws GameActionException {
        exploring = false;
        if(rc.isMovementReady()){
            if(useGreedy){
                lastPos = null;
                return pathToTargetGreedy(target);

            } else{
                Direction dir = bfPathToTarget(target);
                if(lastPos!= null){
                    MapLocation move = rc.getLocation().add(dir);
                    rc.setIndicatorString(lastPos.x+" "+lastPos.y+" "+move.x+" "+move.y);

                }
                lastPos = rc.getLocation();
                if(lastPos != null && rc.getLocation().add(dir).equals(lastPos)) {

                    dir = pathToTargetGreedy(target, 0);
                    lastPos = null;
                }



                return dir;


            }
        }
        return Direction.CENTER;

    }

    Direction pathToTargetGreedy(MapLocation target)
            throws GameActionException {
        return pathToTargetGreedy(target);

    }

    Direction pathToTargetGreedy(MapLocation target, int depth)
            throws GameActionException {
        exploring = false;
        MapLocation ml;
        Direction ans = Direction.CENTER;

        double initDist = Math.sqrt(rc.getLocation().distanceSquaredTo(target));
        int minCost = 720000;
        Direction baseDir = rc.getLocation().directionTo(target);
        Direction dir = rc.getLocation().directionTo(target);

        do {
            if(rc.onTheMap(ml = rc.getLocation().add(dir))){

                if(Math.sqrt(ml.distanceSquaredTo(target)) < initDist){

                    int cost = getCost(target, ml, depth);
                    //rc.setIndicatorString(cost+"");
                    if(cost < minCost){
                        minCost = cost;
                        ans = dir;
                    }
                }
            }
            dir = dir.rotateLeft();
        } while(!dir.equals(baseDir));

        return ans;

    }

    int getCost( MapLocation target, MapLocation cur, int depth) throws GameActionException{
        if(cur.equals(target)){
            return 0;
        }
        else {
            int curCost = (!rc.onTheMap(cur) || rc.isLocationOccupied(cur)) ? 2000: rc.senseRubble(cur) + 10;
            if(depth==0){
                return curCost;
            } else {
                MapLocation ml;
                int minCost = 720000;
                double initDist = Math.sqrt(cur.distanceSquaredTo(target));
                Direction baseDir = cur.directionTo(target);
                Direction dir = cur.directionTo(target);

                do{
                    if(rc.onTheMap(ml = cur.add(dir))){
                        if(Math.sqrt(ml.distanceSquaredTo(target)) < initDist){
                            int cost = getCost(target, ml,depth-1);
                            minCost = Math.min(cost, minCost);
                        }
                    }
                    dir = dir.rotateLeft();
                } while((!dir.equals(baseDir)));
                return curCost + minCost;
            }

        }
    }

    abstract Direction bfPathToTarget(MapLocation target) throws GameActionException;
}
