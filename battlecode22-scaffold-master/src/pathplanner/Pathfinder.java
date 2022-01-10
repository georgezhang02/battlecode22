package pathplanner;

import battlecode.common.*;

public abstract class Pathfinder {
    static MapLocation target;
    static RobotController rc;

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

    static Direction pathTo()
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
                    rc.setIndicatorString(cost+"");
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

    abstract Direction bfPathTo() throws GameActionException;
}
