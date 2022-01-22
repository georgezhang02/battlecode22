package dontatme_rushing;

import battlecode.common.*;


public abstract class Pathfinder {
    static RobotController rc;

    static MapLocation lastPos;

    static Explorer explorer;
    static boolean exploring;

    public Pathfinder(RobotController rc) throws GameActionException{
        this.rc = rc;
        this.explorer = new Explorer(rc, rc.getType().equals(RobotType.MINER));
        lastPos = rc.getLocation();
    }



    boolean targetWithinRadius(MapLocation target, int minDistToTargetSquared){
        return rc.getLocation().distanceSquaredTo(target) <= minDistToTargetSquared;
    }

    //randomly choose unvisited locations to path to
    Direction pathToExplore() throws GameActionException{
        if(!exploring || rc.getLocation().distanceSquaredTo(explorer.target) <= 4){
            int width = rc.getMapWidth();
            int height =rc.getMapHeight();
            explorer.getExploreTarget(10, width, height);
        }
        Direction dir = pathToTarget(explorer.target, true);
        exploring = true;
        return dir;

    }

    // Runs away from given mapLocations, with nearer locations weighted higher
    Direction pathAwayFrom(MapLocation[]mapLocations) throws GameActionException {
        exploring = false;
        MapLocation curPos = rc.getLocation();
        int x = curPos.x;
        int y = curPos.y;
        for(MapLocation ml : mapLocations){
            if(ml != null){
                double vect = 10/Math.sqrt(curPos.distanceSquaredTo(ml));
                int xdiff = curPos.x - ml.x;
                int ydiff = curPos.y - ml.y;

                x += (int)(xdiff * vect);
                y += (int)(ydiff * vect);
            }

        }
        MapLocation target = new MapLocation(x, y);
        return pathToTargetGreedy(target);
    }

    Direction pathAwayFrom(MapLocation[]mapLocations, int depth) throws GameActionException {
        exploring = false;
        MapLocation curPos = rc.getLocation();
        int x = curPos.x;
        int y = curPos.y;
        for(MapLocation ml : mapLocations){
            if(ml != null){
                double vect = 10/Math.sqrt(curPos.distanceSquaredTo(ml));
                int xdiff = curPos.x - ml.x;
                int ydiff = curPos.y - ml.y;

                x += (int)(xdiff * vect);
                y += (int)(ydiff * vect);
            }

        }
        MapLocation target = new MapLocation(x, y);
        return pathToTargetGreedy(target, depth);
    }

    //Sets last location visited with bf so that you can't go there, then paths to location with chosen alg
    Direction pathToTarget(MapLocation target, boolean useGreedy) throws GameActionException {
        exploring = false;
        if(rc.isMovementReady()){
            if(useGreedy){
                lastPos = null;
                return pathToTargetGreedy(target);

            } else{
                Direction dir = bfPathToTarget(target);


                if(lastPos != null && dir != null && rc.getLocation().add(dir).equals(lastPos)) {

                    dir = pathToTargetGreedy(target, 0);
                    lastPos = null;
                }
                lastPos = rc.getLocation();



                return dir;


            }
        }
        return Direction.CENTER;

    }

    //Path using recursive depth 1
    Direction pathToTargetGreedy(MapLocation target)
            throws GameActionException {
        return pathToTargetGreedy(target, 1);

    }

    // Path to target using given recursive depth
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

    // Helper recursive method for greedy
    int getCost( MapLocation target, MapLocation cur, int depth) throws GameActionException{
        if(cur.equals(target)){
            return 0;
        }
        else {
            int curCost = (!rc.onTheMap(cur) || rc.isLocationOccupied(cur)) ? 2000: rc.senseRubble(cur) + 10;
            if(depth<=0){
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

    //Uses Bellman-ford pathing towards target
    abstract Direction bfPathToTarget(MapLocation target) throws GameActionException;
}
