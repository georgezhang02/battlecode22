package dontatme;

import battlecode.common.*;

import java.util.Map;

public abstract class Pathfinder {
    static MapLocation target;
    static RobotController rc;
    static int minDistToTarget = 0;

    static final Direction[] directions = {
            Direction.NORTH,
            Direction.NORTHEAST,
            Direction.EAST,
            Direction.SOUTHEAST,
            Direction.SOUTH,
            Direction.SOUTHWEST,
            Direction.WEST,
            Direction.NORTHWEST,
    };

    public Pathfinder(RobotController rc) throws GameActionException{
        this.rc = rc;
        target = null;
    }

    static boolean targetWithinRadius(){
        return rc.getLocation().distanceSquaredTo(target) < minDistToTarget * minDistToTarget;
    }

    static Direction pathAwayFrom(MapLocation[]mapLocations) throws GameActionException {
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
        target = new MapLocation(x, y);
        return pathToTarget();
    }

    static Direction pathToTarget()
            throws GameActionException {
        MapLocation ml;
        Direction ans = null;

        double initDist = Math.sqrt(rc.getLocation().distanceSquaredTo(target));
        int minCost = 720000;
        Direction baseDir = rc.getLocation().directionTo(target);
        Direction dir = rc.getLocation().directionTo(target);

        do {
            if(rc.onTheMap(ml = rc.getLocation().add(dir))){

                if(Math.sqrt(ml.distanceSquaredTo(target)) < initDist){

                    int cost = getCost(ml, 1);
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

    static int getCost( MapLocation cur, int depth) throws GameActionException{
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
                            int cost = getCost(ml,depth-1);
                            minCost = Math.min(cost, minCost);
                        }
                    }
                    dir = dir.rotateLeft();
                } while((!dir.equals(baseDir)));
                return curCost + minCost;
            }

        }
    }
    abstract Direction bfPathToTarget() throws GameActionException;
}
