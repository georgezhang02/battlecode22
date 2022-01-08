package karthiktestp;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class Pathing {
    static Direction pathTo(RobotController rc, MapLocation target)throws GameActionException {
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
            Direction dir = cur.directionTo(target);
            int cost = (rc.isLocationOccupied(cur)) ? 2000: rc.senseRubble(cur) + 10;
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

        for(int i = 0; i< size; i++){
            dist[i/height][i%height] = 7200000;
        }
        int sum = 0;
        int count = 0;
        for(MapLocation ml: rc.getAllLocationsWithinRadiusSquared(curPos, 100)){
            sum+= rc.senseRubble(ml)+10;
            count++;
        }

        sum/= count;

        dist[curPos.y][curPos.x] = 0;

        int cost = sum;




        //for(int i = 0; i<size; i++) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {


                MapLocation ml = new MapLocation(x, y);
                if(rc.canSenseLocation(ml)){
                    cost = rc.isLocationOccupied(ml) ? 2000 : rc.senseRubble(ml) + 10;
                }
                if (inMap(x-1, y-1, width, height)) {
                    if(dist[y-1][x-1] + cost < dist[y][x]){
                        dist[y][x] = dist[y-1][x-1]+cost;
                        pred[y][x] = (y-1)*height + (x-1);
                    }
                }

                if (inMap(x-1, y, width, height)) {
                    if(dist[y][x-1] + cost < dist[y][x]){
                        dist[y][x] = dist[y][x-1]+cost;
                        pred[y][x] = (y)*height + (x-1);
                    }
                }
                if (inMap(x, y-1, width, height)) {
                    if(dist[y-1][x] + cost < dist[y][x]){
                        dist[y][x] = dist[y-1][x]+cost;
                        pred[y][x] = (y-1)*height+x;
                    }
                }
                if (inMap(x+1, y+1, width, height)) {
                    if(dist[y+1][x+1] + cost < dist[y][x]){
                        dist[y][x] = dist[y+1][x+1]+cost;
                        pred[y][x] = (y+1)*height + (x+1);
                    }
                }
                if (inMap(x, y+1, width, height)) {
                    if(dist[y+1][x] + cost < dist[y][x]){
                        dist[y][x] = dist[y+1][x]+cost;
                        pred[y][x] = (y+1)*height + x;
                    }
                }
                if (inMap(x+1, y, width, height)) {
                    if(dist[y][x+1] + cost < dist[y][x]){
                        dist[y][x] = dist[y][x+1]+cost;
                        pred[y][x] = y*height + (x+1);
                    }
                }
                if (inMap(x-1, y+1, width, height)) {
                    if(dist[y+1][x-1] + cost < dist[y][x]){
                        dist[y][x] = dist[y+1][x-1]+cost;
                        pred[y][x] = (y+1)*height + (x-1);
                    }
                }
                if (inMap(x+1, y-1, width, height)) {
                    if(dist[y-1][x+1] + cost < dist[y][x]){
                        dist[y][x] = dist[y-1][x+1]+cost;
                        pred[y][x] = (y-1)*height + x+1;
                    }
                }

            }
        }
        // }

        int cur = target.y * height + target.x;
        int truePos = curPos.y *height + curPos.x;

        while(pred[cur/height][cur%height] != truePos){
            cur = pred[cur/height][cur%height];
        }
        MapLocation prev = new MapLocation(cur % height, cur/height);
        Direction fin = curPos.directionTo(prev);

        System.out.println(fin);

        if(rc.canMove(fin)){
            rc.move(fin);
        }

        /*
        int left = curPos.x - width;
        int right = width - curPos.x - 1;
        int bottom = curPos.y - height;
        int top = height - curPos.y -1;

        int maxX = (-left < right) ? right : -left;
        int maxY = (-bottom < top) ? top : -bottom;

        for(int i = 0; i< size; i++){
            dist[i] = 7200000;
        }
        dist[curPos.y * height + curPos.x] = 0;

        int sum = 0;
        int count=0;
        for(MapLocation ml: rc.getAllLocationsWithinRadiusSquared(curPos, 100)){
            sum+= rc.senseRubble(ml)+10;
            count ++;
        }

        sum/= count;

        for(int i = 1; i <= maxX+maxY; i++){
            for(int xdiff = 0; xdiff <= i; xdiff++){
                int ydiff = i - xdiff;
                int x;
                int y;
                int x2;
                int y2;
                int cost = sum;
                if(inMap((x = curPos.x + xdiff), (y = curPos.y + ydiff), width, height)){
                    MapLocation ml =new MapLocation(x, y);
                    if(rc.canSenseLocation(ml)){
                        cost = rc.isLocationOccupied(ml) ? 2000 : rc.senseRubble(ml) + 10;
                    }
                    if(inMap((x2 = x -1), (y2 = y), width, height)){
                        if(dist[y2*height + x2]  + cost < dist[y * height + x]){
                            dist[y * height + x] =  dist[y2*height + x2] + cost;
                            pred[y * height + x] = y2 * height + x2;
                        }
                    }
                    if(inMap((x2 = x), (y2 = y-1), width, height)){
                        if(dist[y2*height + x2]  + cost < dist[y * height + x]){
                            dist[y * height + x] =  dist[y2*height + x2] + cost;
                            pred[y * height + x] = y2 * height + x2;
                        }
                    }
                    if(inMap((x2 = x-1), (y2 = y-1), width, height)){
                        if(dist[y2*height + x2]  + cost < dist[y * height + x]){
                            dist[y * height + x] =  dist[y2*height + x2] + cost;
                            pred[y * height + x] = y2 * height + x2;
                        }
                    }
                }
                if(inMap((x = curPos.x + xdiff), (y = curPos.y - ydiff), width, height)){

                }
                if(inMap((x = curPos.x - xdiff), (y = curPos.y + ydiff), width, height)){

                }
                if(inMap((x = curPos.x - xdiff), (y = curPos.y - ydiff), width, height)){

                }



            }
        }
        int toTop = (curPos.y+1) *height + curPos.x;
        int toRight = curPos.y *height + curPos.x+1;

        int cur = target.y * height + target.x;
        int truePos = curPos.y *height + curPos.x;

        while(pred[cur] != truePos){
            cur = pred[cur];
        }
        MapLocation prev = new MapLocation(cur % height, cur/height);
        Direction fin = curPos.directionTo(prev);

        for(int i = 0; i<height; i++){
            for(int j= 0; j<width; j++){
                System.out.print(dist[i*height+j]);
            }
            System.out.println();
        }
        */


    }

    static boolean inMap(int x, int y,  int width, int height) throws GameActionException{
        return x>=0 && y>=0 && x< width && y< height;
    }
}
