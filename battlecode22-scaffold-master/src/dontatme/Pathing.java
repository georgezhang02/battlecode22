package dontatme;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class Pathing {
    static Direction pathTo(RobotController rc, MapLocation target)
            throws GameActionException {
        MapLocation ml;
        Direction dir1 = rc.getLocation().directionTo(target);

        Direction dir = dir1;

        int minCost = getCost(rc, rc.getLocation().add(dir1), target,2);

        if(rc.onTheMap(ml = rc.getLocation().add(dir1.rotateLeft()))){
            int left = getCost(rc, ml, target,2);
            if(left < minCost){
                minCost = left;
                dir = dir1.rotateLeft();
            }
        }
        if(rc.onTheMap(ml = rc.getLocation().add(dir1.rotateRight()))){
            int right =getCost(rc, ml, target,2);
            if(right < minCost){
                minCost = right;
                dir = dir1.rotateRight();
            }
        }
        return dir;
    }

    static int getCost(RobotController rc, MapLocation cur, MapLocation target, int depth) throws GameActionException{
        if(cur.equals(target)){
            return 0;
        }
        else {
            System.out.println(target);
            Direction dir = cur.directionTo(target);
            int cost = (rc.onTheMap(cur) || rc.isLocationOccupied(cur)) ? 2000: rc.senseRubble(cur) + 10;
            if(depth==0){
                return cost;
            } else {
                MapLocation ml;
                int minCost = getCost(rc, rc.getLocation().add(dir), target,depth-1);

                if(rc.onTheMap(ml = rc.getLocation().add(dir.rotateLeft()))){
                    int left = getCost(rc, ml, target,depth - 1);
                    minCost = Math.min(left, minCost);
                }
                if(rc.onTheMap(ml = rc.getLocation().add(dir.rotateRight()))){
                    int right =getCost(rc, ml, target,depth - 1);
                    if(right < minCost){
                        minCost = right;

                    }
                    minCost = Math.min(right, minCost);
                }

                return cost + minCost;
            }

        }
    }

    static void bfPathTo(RobotController rc, MapLocation target) throws GameActionException {

        MapLocation curPos = rc.getLocation();
        int height = rc.getMapHeight();
        int width = rc.getMapWidth();
        int size = height * width;

        int[][]dist = new int[height][width];
        int[][]pred = new int[height][width];




    }

    static boolean inMap(int x, int y,  int width, int height) throws GameActionException{
        return x>=0 && y>=0 && x< width && y< height;
    }
}
