package karthiktestp;

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
            Direction dir = cur.directionTo(target);
            int cost = (!rc.onTheMap(cur) || rc.isLocationOccupied(cur)) ? 2000: rc.senseRubble(cur) + 10;
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

    static Direction bfPathTo(RobotController rc, MapLocation target) throws GameActionException {

        MapLocation curPos = rc.getLocation();
        int x = curPos.x;
        int y = curPos.y;

        int[]xAdd = {0, 1, 1, 1, 0, -1, -1, -1, 0, 2, 2, 2, 2, 2, 1, 0, -1, -2, -2, -2, -2, -2, -1, 0, 1, 3, 3, 3, 3, 3, 3, 3, 2, 1, 0, -1, -2, -3, -3, -3, -3, -3, -3, -3, -2, -1, 0, 1, 2, 4, 4, 4, 4, 4, 2, 1, 0, -1, -2, -4, -4, -4, -4, -4, -2, -1, 0, 1, 2};
        int[]yAdd = {0, -1, 0, 1, 1, 1, 0, -1, -1, -2, -1, 0, 1, 2, 2, 2, 2, 2, 1, 0, -1, -2, -2, -2, -2, -3, -2, -1, 0, 1, 2, 3, 3, 3, 3, 3, 3, 3, 2, 1, 0, -1, -2, -3, -3, -3, -3, -3, -3, -2, -1, 0, 1, 2, 4, 4, 4, 4, 4, 2, 1, 0, -1, -2, -4, -4, -4, -4, -4};


        MapLocation ml;
        int[]dist = new int[69];
        Direction[] dir = new Direction[69];

        dir[1] = Direction.SOUTHEAST;
        dir[2] = Direction.EAST;
        dir[3] = Direction.NORTHEAST;
        dir[4] = Direction.NORTH;
        dir[5] = Direction.NORTHWEST;
        dir[6] = Direction.WEST;
        dir[7] = Direction.SOUTHWEST;
        dir[8] = Direction.SOUTH;

        dist[0] = 0;

        int cost;

        dist[1] = 7200000;
        dist[2] = 7200000;
        dist[3] = 7200000;
        dist[4] = 7200000;
        dist[5] = 7200000;
        dist[6] = 7200000;
        dist[7] = 7200000;
        dist[8] = 7200000;
        dist[9] = 7200000;
        dist[10] = 7200000;
        dist[11] = 7200000;
        dist[12] = 7200000;
        dist[13] = 7200000;
        dist[14] = 7200000;
        dist[15] = 7200000;
        dist[16] = 7200000;
        dist[17] = 7200000;
        dist[18] = 7200000;
        dist[19] = 7200000;
        dist[20] = 7200000;
        dist[21] = 7200000;
        dist[22] = 7200000;
        dist[23] = 7200000;
        dist[24] = 7200000;
        dist[25] = 7200000;
        dist[26] = 7200000;
        dist[27] = 7200000;
        dist[28] = 7200000;
        dist[29] = 7200000;
        dist[30] = 7200000;
        dist[31] = 7200000;
        dist[32] = 7200000;
        dist[33] = 7200000;
        dist[34] = 7200000;
        dist[35] = 7200000;
        dist[36] = 7200000;
        dist[37] = 7200000;
        dist[38] = 7200000;
        dist[39] = 7200000;
        dist[40] = 7200000;
        dist[41] = 7200000;
        dist[42] = 7200000;
        dist[43] = 7200000;
        dist[44] = 7200000;
        dist[45] = 7200000;
        dist[46] = 7200000;
        dist[47] = 7200000;
        dist[48] = 7200000;
        dist[49] = 7200000;
        dist[50] = 7200000;
        dist[51] = 7200000;
        dist[52] = 7200000;
        dist[53] = 7200000;
        dist[54] = 7200000;
        dist[55] = 7200000;
        dist[56] = 7200000;
        dist[57] = 7200000;
        dist[58] = 7200000;
        dist[59] = 7200000;
        dist[60] = 7200000;
        dist[61] = 7200000;
        dist[62] = 7200000;
        dist[63] = 7200000;
        dist[64] = 7200000;
        dist[65] = 7200000;
        dist[66] = 7200000;
        dist[67] = 7200000;
        dist[68] = 7200000;

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[1], y+ yAdd[1]))){
            if(!rc.isLocationOccupied(ml)){
                dist[1] = rc.senseRubble(ml) + 10;
            }
        }
        if(rc.onTheMap(ml = new MapLocation(x + xAdd[2], y+ yAdd[2]))){
            if(!rc.isLocationOccupied(ml)){
                dist[2] = rc.senseRubble(ml) + 10;
            }
        }
        if(rc.onTheMap(ml = new MapLocation(x + xAdd[3], y+ yAdd[3]))){
            if(!rc.isLocationOccupied(ml)){
                dist[3] = rc.senseRubble(ml) + 10;
            }
        }
        if(rc.onTheMap(ml = new MapLocation(x + xAdd[4], y+ yAdd[4]))){
            if(!rc.isLocationOccupied(ml)){
                dist[4] = rc.senseRubble(ml) + 10;
            }
        }
        if(rc.onTheMap(ml = new MapLocation(x + xAdd[5], y+ yAdd[5]))){
            if(!rc.isLocationOccupied(ml)){
                dist[5] = rc.senseRubble(ml) + 10;
            }
        }
        if(rc.onTheMap(ml = new MapLocation(x + xAdd[6], y+ yAdd[6]))){
            if(!rc.isLocationOccupied(ml)){
                dist[6] = rc.senseRubble(ml) + 10;
            }
        }
        if(rc.onTheMap(ml = new MapLocation(x + xAdd[7], y+ yAdd[7]))){
            if(!rc.isLocationOccupied(ml)){
                dist[7] = rc.senseRubble(ml) + 10;
            }
        }
        if(rc.onTheMap(ml = new MapLocation(x + xAdd[8], y+ yAdd[8]))) {
            if (!rc.isLocationOccupied(ml)) {
                dist[8] = rc.senseRubble(ml) + 10;
            }
        }
        if(rc.onTheMap(ml = new MapLocation(x + xAdd[9], y+ yAdd[9]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[1] < dist[9]){
                dist[9] = cost + dist[1];
                dir[9] = dir[1];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[10], y+ yAdd[10]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[2] < dist[10]){
                dist[10] = cost + dist[2];
                dir[10] = dir[2];
            }

            if(cost + dist[1] < dist[10]){
                dist[10] = cost + dist[1];
                dir[10] = dir[1];
            }

            if(cost + dist[9] < dist[10]){
                dist[10] = cost + dist[9];
                dir[10] = dir[9];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[11], y+ yAdd[11]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[3] < dist[11]){
                dist[11] = cost + dist[3];
                dir[11] = dir[3];
            }

            if(cost + dist[2] < dist[11]){
                dist[11] = cost + dist[2];
                dir[11] = dir[2];
            }

            if(cost + dist[1] < dist[11]){
                dist[11] = cost + dist[1];
                dir[11] = dir[1];
            }

            if(cost + dist[10] < dist[11]){
                dist[11] = cost + dist[10];
                dir[11] = dir[10];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[12], y+ yAdd[12]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[3] < dist[12]){
                dist[12] = cost + dist[3];
                dir[12] = dir[3];
            }

            if(cost + dist[2] < dist[12]){
                dist[12] = cost + dist[2];
                dir[12] = dir[2];
            }

            if(cost + dist[11] < dist[12]){
                dist[12] = cost + dist[11];
                dir[12] = dir[11];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[13], y+ yAdd[13]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[3] < dist[13]){
                dist[13] = cost + dist[3];
                dir[13] = dir[3];
            }

            if(cost + dist[12] < dist[13]){
                dist[13] = cost + dist[12];
                dir[13] = dir[12];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[14], y+ yAdd[14]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[12] < dist[14]){
                dist[14] = cost + dist[12];
                dir[14] = dir[12];
            }

            if(cost + dist[13] < dist[14]){
                dist[14] = cost + dist[13];
                dir[14] = dir[13];
            }

            if(cost + dist[4] < dist[14]){
                dist[14] = cost + dist[4];
                dir[14] = dir[4];
            }

            if(cost + dist[3] < dist[14]){
                dist[14] = cost + dist[3];
                dir[14] = dir[3];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[15], y+ yAdd[15]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[3] < dist[15]){
                dist[15] = cost + dist[3];
                dir[15] = dir[3];
            }

            if(cost + dist[14] < dist[15]){
                dist[15] = cost + dist[14];
                dir[15] = dir[14];
            }

            if(cost + dist[5] < dist[15]){
                dist[15] = cost + dist[5];
                dir[15] = dir[5];
            }

            if(cost + dist[4] < dist[15]){
                dist[15] = cost + dist[4];
                dir[15] = dir[4];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[16], y+ yAdd[16]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[4] < dist[16]){
                dist[16] = cost + dist[4];
                dir[16] = dir[4];
            }

            if(cost + dist[15] < dist[16]){
                dist[16] = cost + dist[15];
                dir[16] = dir[15];
            }

            if(cost + dist[5] < dist[16]){
                dist[16] = cost + dist[5];
                dir[16] = dir[5];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[17], y+ yAdd[17]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[5] < dist[17]){
                dist[17] = cost + dist[5];
                dir[17] = dir[5];
            }

            if(cost + dist[16] < dist[17]){
                dist[17] = cost + dist[16];
                dir[17] = dir[16];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[18], y+ yAdd[18]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[6] < dist[18]){
                dist[18] = cost + dist[6];
                dir[18] = dir[6];
            }

            if(cost + dist[5] < dist[18]){
                dist[18] = cost + dist[5];
                dir[18] = dir[5];
            }

            if(cost + dist[16] < dist[18]){
                dist[18] = cost + dist[16];
                dir[18] = dir[16];
            }

            if(cost + dist[17] < dist[18]){
                dist[18] = cost + dist[17];
                dir[18] = dir[17];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[19], y+ yAdd[19]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[7] < dist[19]){
                dist[19] = cost + dist[7];
                dir[19] = dir[7];
            }

            if(cost + dist[6] < dist[19]){
                dist[19] = cost + dist[6];
                dir[19] = dir[6];
            }

            if(cost + dist[5] < dist[19]){
                dist[19] = cost + dist[5];
                dir[19] = dir[5];
            }

            if(cost + dist[18] < dist[19]){
                dist[19] = cost + dist[18];
                dir[19] = dir[18];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[20], y+ yAdd[20]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[7] < dist[20]){
                dist[20] = cost + dist[7];
                dir[20] = dir[7];
            }

            if(cost + dist[6] < dist[20]){
                dist[20] = cost + dist[6];
                dir[20] = dir[6];
            }

            if(cost + dist[19] < dist[20]){
                dist[20] = cost + dist[19];
                dir[20] = dir[19];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[21], y+ yAdd[21]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[7] < dist[21]){
                dist[21] = cost + dist[7];
                dir[21] = dir[7];
            }

            if(cost + dist[20] < dist[21]){
                dist[21] = cost + dist[20];
                dir[21] = dir[20];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[22], y+ yAdd[22]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[8] < dist[22]){
                dist[22] = cost + dist[8];
                dir[22] = dir[8];
            }

            if(cost + dist[7] < dist[22]){
                dist[22] = cost + dist[7];
                dir[22] = dir[7];
            }

            if(cost + dist[20] < dist[22]){
                dist[22] = cost + dist[20];
                dir[22] = dir[20];
            }

            if(cost + dist[21] < dist[22]){
                dist[22] = cost + dist[21];
                dir[22] = dir[21];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[23], y+ yAdd[23]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[1] < dist[23]){
                dist[23] = cost + dist[1];
                dir[23] = dir[1];
            }

            if(cost + dist[8] < dist[23]){
                dist[23] = cost + dist[8];
                dir[23] = dir[8];
            }

            if(cost + dist[7] < dist[23]){
                dist[23] = cost + dist[7];
                dir[23] = dir[7];
            }

            if(cost + dist[22] < dist[23]){
                dist[23] = cost + dist[22];
                dir[23] = dir[22];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[24], y+ yAdd[24]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[9] < dist[24]){
                dist[24] = cost + dist[9];
                dir[24] = dir[9];
            }

            if(cost + dist[10] < dist[24]){
                dist[24] = cost + dist[10];
                dir[24] = dir[10];
            }

            if(cost + dist[1] < dist[24]){
                dist[24] = cost + dist[1];
                dir[24] = dir[1];
            }

            if(cost + dist[8] < dist[24]){
                dist[24] = cost + dist[8];
                dir[24] = dir[8];
            }

            if(cost + dist[23] < dist[24]){
                dist[24] = cost + dist[23];
                dir[24] = dir[23];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[25], y+ yAdd[25]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[9] < dist[25]){
                dist[25] = cost + dist[9];
                dir[25] = dir[9];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[26], y+ yAdd[26]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[10] < dist[26]){
                dist[26] = cost + dist[10];
                dir[26] = dir[10];
            }

            if(cost + dist[9] < dist[26]){
                dist[26] = cost + dist[9];
                dir[26] = dir[9];
            }

            if(cost + dist[25] < dist[26]){
                dist[26] = cost + dist[25];
                dir[26] = dir[25];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[27], y+ yAdd[27]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[11] < dist[27]){
                dist[27] = cost + dist[11];
                dir[27] = dir[11];
            }

            if(cost + dist[10] < dist[27]){
                dist[27] = cost + dist[10];
                dir[27] = dir[10];
            }

            if(cost + dist[9] < dist[27]){
                dist[27] = cost + dist[9];
                dir[27] = dir[9];
            }

            if(cost + dist[26] < dist[27]){
                dist[27] = cost + dist[26];
                dir[27] = dir[26];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[28], y+ yAdd[28]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[12] < dist[28]){
                dist[28] = cost + dist[12];
                dir[28] = dir[12];
            }

            if(cost + dist[11] < dist[28]){
                dist[28] = cost + dist[11];
                dir[28] = dir[11];
            }

            if(cost + dist[10] < dist[28]){
                dist[28] = cost + dist[10];
                dir[28] = dir[10];
            }

            if(cost + dist[27] < dist[28]){
                dist[28] = cost + dist[27];
                dir[28] = dir[27];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[29], y+ yAdd[29]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[13] < dist[29]){
                dist[29] = cost + dist[13];
                dir[29] = dir[13];
            }

            if(cost + dist[12] < dist[29]){
                dist[29] = cost + dist[12];
                dir[29] = dir[12];
            }

            if(cost + dist[11] < dist[29]){
                dist[29] = cost + dist[11];
                dir[29] = dir[11];
            }

            if(cost + dist[28] < dist[29]){
                dist[29] = cost + dist[28];
                dir[29] = dir[28];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[30], y+ yAdd[30]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[13] < dist[30]){
                dist[30] = cost + dist[13];
                dir[30] = dir[13];
            }

            if(cost + dist[12] < dist[30]){
                dist[30] = cost + dist[12];
                dir[30] = dir[12];
            }

            if(cost + dist[29] < dist[30]){
                dist[30] = cost + dist[29];
                dir[30] = dir[29];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[31], y+ yAdd[31]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[13] < dist[31]){
                dist[31] = cost + dist[13];
                dir[31] = dir[13];
            }

            if(cost + dist[30] < dist[31]){
                dist[31] = cost + dist[30];
                dir[31] = dir[30];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[32], y+ yAdd[32]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[30] < dist[32]){
                dist[32] = cost + dist[30];
                dir[32] = dir[30];
            }

            if(cost + dist[31] < dist[32]){
                dist[32] = cost + dist[31];
                dir[32] = dir[31];
            }

            if(cost + dist[14] < dist[32]){
                dist[32] = cost + dist[14];
                dir[32] = dir[14];
            }

            if(cost + dist[13] < dist[32]){
                dist[32] = cost + dist[13];
                dir[32] = dir[13];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[33], y+ yAdd[33]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[13] < dist[33]){
                dist[33] = cost + dist[13];
                dir[33] = dir[13];
            }

            if(cost + dist[32] < dist[33]){
                dist[33] = cost + dist[32];
                dir[33] = dir[32];
            }

            if(cost + dist[15] < dist[33]){
                dist[33] = cost + dist[15];
                dir[33] = dir[15];
            }

            if(cost + dist[14] < dist[33]){
                dist[33] = cost + dist[14];
                dir[33] = dir[14];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[34], y+ yAdd[34]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[14] < dist[34]){
                dist[34] = cost + dist[14];
                dir[34] = dir[14];
            }

            if(cost + dist[33] < dist[34]){
                dist[34] = cost + dist[33];
                dir[34] = dir[33];
            }

            if(cost + dist[16] < dist[34]){
                dist[34] = cost + dist[16];
                dir[34] = dir[16];
            }

            if(cost + dist[15] < dist[34]){
                dist[34] = cost + dist[15];
                dir[34] = dir[15];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[35], y+ yAdd[35]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[15] < dist[35]){
                dist[35] = cost + dist[15];
                dir[35] = dir[15];
            }

            if(cost + dist[34] < dist[35]){
                dist[35] = cost + dist[34];
                dir[35] = dir[34];
            }

            if(cost + dist[17] < dist[35]){
                dist[35] = cost + dist[17];
                dir[35] = dir[17];
            }

            if(cost + dist[16] < dist[35]){
                dist[35] = cost + dist[16];
                dir[35] = dir[16];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[36], y+ yAdd[36]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[16] < dist[36]){
                dist[36] = cost + dist[16];
                dir[36] = dir[16];
            }

            if(cost + dist[35] < dist[36]){
                dist[36] = cost + dist[35];
                dir[36] = dir[35];
            }

            if(cost + dist[17] < dist[36]){
                dist[36] = cost + dist[17];
                dir[36] = dir[17];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[37], y+ yAdd[37]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[17] < dist[37]){
                dist[37] = cost + dist[17];
                dir[37] = dir[17];
            }

            if(cost + dist[36] < dist[37]){
                dist[37] = cost + dist[36];
                dir[37] = dir[36];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[38], y+ yAdd[38]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[18] < dist[38]){
                dist[38] = cost + dist[18];
                dir[38] = dir[18];
            }

            if(cost + dist[17] < dist[38]){
                dist[38] = cost + dist[17];
                dir[38] = dir[17];
            }

            if(cost + dist[36] < dist[38]){
                dist[38] = cost + dist[36];
                dir[38] = dir[36];
            }

            if(cost + dist[37] < dist[38]){
                dist[38] = cost + dist[37];
                dir[38] = dir[37];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[39], y+ yAdd[39]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[19] < dist[39]){
                dist[39] = cost + dist[19];
                dir[39] = dir[19];
            }

            if(cost + dist[18] < dist[39]){
                dist[39] = cost + dist[18];
                dir[39] = dir[18];
            }

            if(cost + dist[17] < dist[39]){
                dist[39] = cost + dist[17];
                dir[39] = dir[17];
            }

            if(cost + dist[38] < dist[39]){
                dist[39] = cost + dist[38];
                dir[39] = dir[38];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[40], y+ yAdd[40]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[20] < dist[40]){
                dist[40] = cost + dist[20];
                dir[40] = dir[20];
            }

            if(cost + dist[19] < dist[40]){
                dist[40] = cost + dist[19];
                dir[40] = dir[19];
            }

            if(cost + dist[18] < dist[40]){
                dist[40] = cost + dist[18];
                dir[40] = dir[18];
            }

            if(cost + dist[39] < dist[40]){
                dist[40] = cost + dist[39];
                dir[40] = dir[39];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[41], y+ yAdd[41]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[21] < dist[41]){
                dist[41] = cost + dist[21];
                dir[41] = dir[21];
            }

            if(cost + dist[20] < dist[41]){
                dist[41] = cost + dist[20];
                dir[41] = dir[20];
            }

            if(cost + dist[19] < dist[41]){
                dist[41] = cost + dist[19];
                dir[41] = dir[19];
            }

            if(cost + dist[40] < dist[41]){
                dist[41] = cost + dist[40];
                dir[41] = dir[40];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[42], y+ yAdd[42]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[21] < dist[42]){
                dist[42] = cost + dist[21];
                dir[42] = dir[21];
            }

            if(cost + dist[20] < dist[42]){
                dist[42] = cost + dist[20];
                dir[42] = dir[20];
            }

            if(cost + dist[41] < dist[42]){
                dist[42] = cost + dist[41];
                dir[42] = dir[41];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[43], y+ yAdd[43]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[21] < dist[43]){
                dist[43] = cost + dist[21];
                dir[43] = dir[21];
            }

            if(cost + dist[42] < dist[43]){
                dist[43] = cost + dist[42];
                dir[43] = dir[42];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[44], y+ yAdd[44]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[22] < dist[44]){
                dist[44] = cost + dist[22];
                dir[44] = dir[22];
            }

            if(cost + dist[21] < dist[44]){
                dist[44] = cost + dist[21];
                dir[44] = dir[21];
            }

            if(cost + dist[42] < dist[44]){
                dist[44] = cost + dist[42];
                dir[44] = dir[42];
            }

            if(cost + dist[43] < dist[44]){
                dist[44] = cost + dist[43];
                dir[44] = dir[43];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[45], y+ yAdd[45]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[23] < dist[45]){
                dist[45] = cost + dist[23];
                dir[45] = dir[23];
            }

            if(cost + dist[22] < dist[45]){
                dist[45] = cost + dist[22];
                dir[45] = dir[22];
            }

            if(cost + dist[21] < dist[45]){
                dist[45] = cost + dist[21];
                dir[45] = dir[21];
            }

            if(cost + dist[44] < dist[45]){
                dist[45] = cost + dist[44];
                dir[45] = dir[44];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[46], y+ yAdd[46]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[24] < dist[46]){
                dist[46] = cost + dist[24];
                dir[46] = dir[24];
            }

            if(cost + dist[23] < dist[46]){
                dist[46] = cost + dist[23];
                dir[46] = dir[23];
            }

            if(cost + dist[22] < dist[46]){
                dist[46] = cost + dist[22];
                dir[46] = dir[22];
            }

            if(cost + dist[45] < dist[46]){
                dist[46] = cost + dist[45];
                dir[46] = dir[45];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[47], y+ yAdd[47]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[9] < dist[47]){
                dist[47] = cost + dist[9];
                dir[47] = dir[9];
            }

            if(cost + dist[24] < dist[47]){
                dist[47] = cost + dist[24];
                dir[47] = dir[24];
            }

            if(cost + dist[23] < dist[47]){
                dist[47] = cost + dist[23];
                dir[47] = dir[23];
            }

            if(cost + dist[46] < dist[47]){
                dist[47] = cost + dist[46];
                dir[47] = dir[46];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[48], y+ yAdd[48]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[25] < dist[48]){
                dist[48] = cost + dist[25];
                dir[48] = dir[25];
            }

            if(cost + dist[26] < dist[48]){
                dist[48] = cost + dist[26];
                dir[48] = dir[26];
            }

            if(cost + dist[9] < dist[48]){
                dist[48] = cost + dist[9];
                dir[48] = dir[9];
            }

            if(cost + dist[24] < dist[48]){
                dist[48] = cost + dist[24];
                dir[48] = dir[24];
            }

            if(cost + dist[47] < dist[48]){
                dist[48] = cost + dist[47];
                dir[48] = dir[47];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[49], y+ yAdd[49]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[27] < dist[49]){
                dist[49] = cost + dist[27];
                dir[49] = dir[27];
            }

            if(cost + dist[26] < dist[49]){
                dist[49] = cost + dist[26];
                dir[49] = dir[26];
            }

            if(cost + dist[25] < dist[49]){
                dist[49] = cost + dist[25];
                dir[49] = dir[25];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[50], y+ yAdd[50]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[28] < dist[50]){
                dist[50] = cost + dist[28];
                dir[50] = dir[28];
            }

            if(cost + dist[27] < dist[50]){
                dist[50] = cost + dist[27];
                dir[50] = dir[27];
            }

            if(cost + dist[26] < dist[50]){
                dist[50] = cost + dist[26];
                dir[50] = dir[26];
            }

            if(cost + dist[49] < dist[50]){
                dist[50] = cost + dist[49];
                dir[50] = dir[49];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[51], y+ yAdd[51]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[29] < dist[51]){
                dist[51] = cost + dist[29];
                dir[51] = dir[29];
            }

            if(cost + dist[28] < dist[51]){
                dist[51] = cost + dist[28];
                dir[51] = dir[28];
            }

            if(cost + dist[27] < dist[51]){
                dist[51] = cost + dist[27];
                dir[51] = dir[27];
            }

            if(cost + dist[50] < dist[51]){
                dist[51] = cost + dist[50];
                dir[51] = dir[50];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[52], y+ yAdd[52]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[30] < dist[52]){
                dist[52] = cost + dist[30];
                dir[52] = dir[30];
            }

            if(cost + dist[29] < dist[52]){
                dist[52] = cost + dist[29];
                dir[52] = dir[29];
            }

            if(cost + dist[28] < dist[52]){
                dist[52] = cost + dist[28];
                dir[52] = dir[28];
            }

            if(cost + dist[51] < dist[52]){
                dist[52] = cost + dist[51];
                dir[52] = dir[51];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[53], y+ yAdd[53]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[31] < dist[53]){
                dist[53] = cost + dist[31];
                dir[53] = dir[31];
            }

            if(cost + dist[30] < dist[53]){
                dist[53] = cost + dist[30];
                dir[53] = dir[30];
            }

            if(cost + dist[29] < dist[53]){
                dist[53] = cost + dist[29];
                dir[53] = dir[29];
            }

            if(cost + dist[52] < dist[53]){
                dist[53] = cost + dist[52];
                dir[53] = dir[52];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[54], y+ yAdd[54]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[31] < dist[54]){
                dist[54] = cost + dist[31];
                dir[54] = dir[31];
            }

            if(cost + dist[33] < dist[54]){
                dist[54] = cost + dist[33];
                dir[54] = dir[33];
            }

            if(cost + dist[32] < dist[54]){
                dist[54] = cost + dist[32];
                dir[54] = dir[32];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[55], y+ yAdd[55]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[32] < dist[55]){
                dist[55] = cost + dist[32];
                dir[55] = dir[32];
            }

            if(cost + dist[54] < dist[55]){
                dist[55] = cost + dist[54];
                dir[55] = dir[54];
            }

            if(cost + dist[34] < dist[55]){
                dist[55] = cost + dist[34];
                dir[55] = dir[34];
            }

            if(cost + dist[33] < dist[55]){
                dist[55] = cost + dist[33];
                dir[55] = dir[33];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[56], y+ yAdd[56]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[33] < dist[56]){
                dist[56] = cost + dist[33];
                dir[56] = dir[33];
            }

            if(cost + dist[55] < dist[56]){
                dist[56] = cost + dist[55];
                dir[56] = dir[55];
            }

            if(cost + dist[35] < dist[56]){
                dist[56] = cost + dist[35];
                dir[56] = dir[35];
            }

            if(cost + dist[34] < dist[56]){
                dist[56] = cost + dist[34];
                dir[56] = dir[34];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[57], y+ yAdd[57]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[34] < dist[57]){
                dist[57] = cost + dist[34];
                dir[57] = dir[34];
            }

            if(cost + dist[56] < dist[57]){
                dist[57] = cost + dist[56];
                dir[57] = dir[56];
            }

            if(cost + dist[36] < dist[57]){
                dist[57] = cost + dist[36];
                dir[57] = dir[36];
            }

            if(cost + dist[35] < dist[57]){
                dist[57] = cost + dist[35];
                dir[57] = dir[35];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[58], y+ yAdd[58]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[35] < dist[58]){
                dist[58] = cost + dist[35];
                dir[58] = dir[35];
            }

            if(cost + dist[57] < dist[58]){
                dist[58] = cost + dist[57];
                dir[58] = dir[57];
            }

            if(cost + dist[37] < dist[58]){
                dist[58] = cost + dist[37];
                dir[58] = dir[37];
            }

            if(cost + dist[36] < dist[58]){
                dist[58] = cost + dist[36];
                dir[58] = dir[36];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[59], y+ yAdd[59]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[39] < dist[59]){
                dist[59] = cost + dist[39];
                dir[59] = dir[39];
            }

            if(cost + dist[38] < dist[59]){
                dist[59] = cost + dist[38];
                dir[59] = dir[38];
            }

            if(cost + dist[37] < dist[59]){
                dist[59] = cost + dist[37];
                dir[59] = dir[37];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[60], y+ yAdd[60]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[40] < dist[60]){
                dist[60] = cost + dist[40];
                dir[60] = dir[40];
            }

            if(cost + dist[39] < dist[60]){
                dist[60] = cost + dist[39];
                dir[60] = dir[39];
            }

            if(cost + dist[38] < dist[60]){
                dist[60] = cost + dist[38];
                dir[60] = dir[38];
            }

            if(cost + dist[59] < dist[60]){
                dist[60] = cost + dist[59];
                dir[60] = dir[59];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[61], y+ yAdd[61]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[41] < dist[61]){
                dist[61] = cost + dist[41];
                dir[61] = dir[41];
            }

            if(cost + dist[40] < dist[61]){
                dist[61] = cost + dist[40];
                dir[61] = dir[40];
            }

            if(cost + dist[39] < dist[61]){
                dist[61] = cost + dist[39];
                dir[61] = dir[39];
            }

            if(cost + dist[60] < dist[61]){
                dist[61] = cost + dist[60];
                dir[61] = dir[60];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[62], y+ yAdd[62]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[42] < dist[62]){
                dist[62] = cost + dist[42];
                dir[62] = dir[42];
            }

            if(cost + dist[41] < dist[62]){
                dist[62] = cost + dist[41];
                dir[62] = dir[41];
            }

            if(cost + dist[40] < dist[62]){
                dist[62] = cost + dist[40];
                dir[62] = dir[40];
            }

            if(cost + dist[61] < dist[62]){
                dist[62] = cost + dist[61];
                dir[62] = dir[61];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[63], y+ yAdd[63]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[43] < dist[63]){
                dist[63] = cost + dist[43];
                dir[63] = dir[43];
            }

            if(cost + dist[42] < dist[63]){
                dist[63] = cost + dist[42];
                dir[63] = dir[42];
            }

            if(cost + dist[41] < dist[63]){
                dist[63] = cost + dist[41];
                dir[63] = dir[41];
            }

            if(cost + dist[62] < dist[63]){
                dist[63] = cost + dist[62];
                dir[63] = dir[62];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[64], y+ yAdd[64]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[45] < dist[64]){
                dist[64] = cost + dist[45];
                dir[64] = dir[45];
            }

            if(cost + dist[44] < dist[64]){
                dist[64] = cost + dist[44];
                dir[64] = dir[44];
            }

            if(cost + dist[43] < dist[64]){
                dist[64] = cost + dist[43];
                dir[64] = dir[43];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[65], y+ yAdd[65]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[46] < dist[65]){
                dist[65] = cost + dist[46];
                dir[65] = dir[46];
            }

            if(cost + dist[45] < dist[65]){
                dist[65] = cost + dist[45];
                dir[65] = dir[45];
            }

            if(cost + dist[44] < dist[65]){
                dist[65] = cost + dist[44];
                dir[65] = dir[44];
            }

            if(cost + dist[64] < dist[65]){
                dist[65] = cost + dist[64];
                dir[65] = dir[64];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[66], y+ yAdd[66]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[47] < dist[66]){
                dist[66] = cost + dist[47];
                dir[66] = dir[47];
            }

            if(cost + dist[46] < dist[66]){
                dist[66] = cost + dist[46];
                dir[66] = dir[46];
            }

            if(cost + dist[45] < dist[66]){
                dist[66] = cost + dist[45];
                dir[66] = dir[45];
            }

            if(cost + dist[65] < dist[66]){
                dist[66] = cost + dist[65];
                dir[66] = dir[65];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[67], y+ yAdd[67]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[48] < dist[67]){
                dist[67] = cost + dist[48];
                dir[67] = dir[48];
            }

            if(cost + dist[47] < dist[67]){
                dist[67] = cost + dist[47];
                dir[67] = dir[47];
            }

            if(cost + dist[46] < dist[67]){
                dist[67] = cost + dist[46];
                dir[67] = dir[46];
            }

            if(cost + dist[66] < dist[67]){
                dist[67] = cost + dist[66];
                dir[67] = dir[66];
            }
        }

        if(rc.onTheMap(ml = new MapLocation(x + xAdd[68], y+ yAdd[68]))){
            cost = rc.senseRubble(ml) + 10;



            if(cost + dist[25] < dist[68]){
                dist[68] = cost + dist[25];
                dir[68] = dir[25];
            }

            if(cost + dist[48] < dist[68]){
                dist[68] = cost + dist[48];
                dir[68] = dir[48];
            }

            if(cost + dist[47] < dist[68]){
                dist[68] = cost + dist[47];
                dir[68] = dir[47];
            }

            if(cost + dist[67] < dist[68]){
                dist[68] = cost + dist[67];
                dir[68] = dir[67];
            }
        }

        int xDiff = target.x - x;
        int yDiff = target.y - y;





        switch (xDiff) {

            case -4:
                switch (yDiff) {
                    case -2:
                        return dir[63];
                    case -1:
                        return dir[62];
                    case 0:
                        return dir[61];
                    case 1:
                        return dir[60];
                    case 2:
                        return dir[59];
                }
                break;
            case -3:
                switch (yDiff) {
                    case -3:
                        return dir[43];
                    case -2:
                        return dir[42];
                    case -1:
                        return dir[41];
                    case 0:
                        return dir[40];
                    case 1:
                        return dir[39];
                    case 2:
                        return dir[38];
                    case 3:
                        return dir[37];
                }
                break;
            case -2:
                switch (yDiff) {
                    case -4:
                        return dir[64];
                    case -3:
                        return dir[44];
                    case -2:
                        return dir[21];
                    case -1:
                        return dir[20];
                    case 0:
                        return dir[19];
                    case 1:
                        return dir[18];
                    case 2:
                        return dir[17];
                    case 3:
                        return dir[36];
                    case 4:
                        return dir[58];
                }
                break;
            case -1:
                switch (yDiff) {
                    case -4:
                        return dir[65];
                    case -3:
                        return dir[45];
                    case -2:
                        return dir[22];
                    case -1:
                        return dir[7];
                    case 0:
                        return dir[6];
                    case 1:
                        return dir[5];
                    case 2:
                        return dir[16];
                    case 3:
                        return dir[35];
                    case 4:
                        return dir[57];
                }
                break;
            case 0:
                switch (yDiff) {
                    case -4:
                        return dir[66];
                    case -3:
                        return dir[46];
                    case -2:
                        return dir[23];
                    case -1:
                        return dir[8];
                    case 0:
                        return dir[0];
                    case 1:
                        return dir[4];
                    case 2:
                        return dir[15];
                    case 3:
                        return dir[34];
                    case 4:
                        return dir[56];
                }
                break;
            case 1:
                switch (yDiff) {
                    case -4:
                        return dir[67];
                    case -3:
                        return dir[47];
                    case -2:
                        return dir[24];
                    case -1:
                        return dir[1];
                    case 0:
                        return dir[2];
                    case 1:
                        return dir[3];
                    case 2:
                        return dir[14];
                    case 3:
                        return dir[33];
                    case 4:
                        return dir[54];
                }
                break;
            case 2:
                switch (yDiff) {
                    case -4:
                        return dir[68];
                    case -3:
                        return dir[48];
                    case -2:
                        return dir[9];
                    case -1:
                        return dir[10];
                    case 0:
                        return dir[11];
                    case 1:
                        return dir[12];
                    case 2:
                        return dir[13];
                    case 3:
                        return dir[32];
                    case 4:
                        return dir[54];
                }
                break;
            case 3:
                switch (yDiff) {
                    case -3:
                        return dir[25];
                    case -2:
                        return dir[26];
                    case -1:
                        return dir[27];
                    case 0:
                        return dir[28];
                    case 1:
                        return dir[29];
                    case 2:
                        return dir[30];
                    case 3:
                        return dir[31];
                }
                break;
            case 4:
                switch (yDiff) {
                    case -2:
                        return dir[49];
                    case -1:
                        return dir[50];
                    case 0:
                        return dir[51];
                    case 1:
                        return dir[52];
                    case 2:
                        return dir[53];
                }
                break;

        }



        double gain;

        Direction ans = null;
        double initDist = Math.sqrt(curPos.distanceSquaredTo(target));
        double maxGainPerCost = 0;

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[49], y + yAdd[49]).distanceSquaredTo(target))) / dist[49];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[49];
            rc.setIndicatorString(49+""+dist[49]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[50], y + yAdd[50]).distanceSquaredTo(target))) / dist[50];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[50];
            rc.setIndicatorString(50+""+dist[50]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[51], y + yAdd[51]).distanceSquaredTo(target))) / dist[51];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[51];
            rc.setIndicatorString(51+""+dist[51]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[52], y + yAdd[52]).distanceSquaredTo(target))) / dist[52];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[52];
            rc.setIndicatorString(52+""+dist[52]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[53], y + yAdd[53]).distanceSquaredTo(target))) / dist[53];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[53];
            rc.setIndicatorString(53+""+dist[53]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[54], y + yAdd[54]).distanceSquaredTo(target))) / dist[54];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[54];
            rc.setIndicatorString(54+""+dist[54]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[55], y + yAdd[55]).distanceSquaredTo(target))) / dist[55];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[55];
            rc.setIndicatorString(55+""+dist[55]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[56], y + yAdd[56]).distanceSquaredTo(target))) / dist[56];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[56];
            rc.setIndicatorString(56+""+dist[56]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[57], y + yAdd[57]).distanceSquaredTo(target))) / dist[57];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[57];
            rc.setIndicatorString(57+""+dist[57]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[58], y + yAdd[58]).distanceSquaredTo(target))) / dist[58];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[58];
            rc.setIndicatorString(58+""+dist[58]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[59], y + yAdd[59]).distanceSquaredTo(target))) / dist[59];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[59];
            rc.setIndicatorString(59+""+dist[59]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[60], y + yAdd[60]).distanceSquaredTo(target))) / dist[60];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[60];
            rc.setIndicatorString(60+""+dist[60]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[61], y + yAdd[61]).distanceSquaredTo(target))) / dist[61];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[61];
            rc.setIndicatorString(61+""+dist[61]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[62], y + yAdd[62]).distanceSquaredTo(target))) / dist[62];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[62];
            rc.setIndicatorString(62+""+dist[62]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[63], y + yAdd[63]).distanceSquaredTo(target))) / dist[63];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[63];
            rc.setIndicatorString(63+""+dist[63]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[64], y + yAdd[64]).distanceSquaredTo(target))) / dist[64];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[64];
            rc.setIndicatorString(64+""+dist[64]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[65], y + yAdd[65]).distanceSquaredTo(target))) / dist[65];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[65];
            rc.setIndicatorString(65+""+dist[65]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[66], y + yAdd[66]).distanceSquaredTo(target))) / dist[66];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[66];
            rc.setIndicatorString(66+""+dist[66]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[67], y + yAdd[67]).distanceSquaredTo(target))) / dist[67];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[67];
            rc.setIndicatorString(67+""+dist[67]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[68], y + yAdd[68]).distanceSquaredTo(target))) / dist[68];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[68];
            rc.setIndicatorString(68+""+dist[68]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[31], y + yAdd[31]).distanceSquaredTo(target))) / dist[31];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[31];
            rc.setIndicatorString(31+""+dist[31]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[37], y + yAdd[37]).distanceSquaredTo(target))) / dist[37];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[37];
            rc.setIndicatorString(37+""+dist[37]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[43], y + yAdd[43]).distanceSquaredTo(target))) / dist[43];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[43];
            rc.setIndicatorString(43+""+dist[43]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[25], y + yAdd[25]).distanceSquaredTo(target))) / dist[25];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[25];
            rc.setIndicatorString(25+""+dist[25]);
        }

        rc.setIndicatorString(8+" "+dist[8]+" "+1+" "+dist[1]+" "+48+" "+dist[48]+" ");

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[30], y + yAdd[30]).distanceSquaredTo(target))) / dist[30];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[30];
            rc.setIndicatorString(30+""+dist[30]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[32], y + yAdd[32]).distanceSquaredTo(target))) / dist[32];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[32];
            rc.setIndicatorString(32+""+dist[32]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[36], y + yAdd[36]).distanceSquaredTo(target))) / dist[36];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[36];
            rc.setIndicatorString(36+""+dist[36]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[38], y + yAdd[38]).distanceSquaredTo(target))) / dist[38];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[38];
            rc.setIndicatorString(38+""+dist[38]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[42], y + yAdd[42]).distanceSquaredTo(target))) / dist[42];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[42];
            rc.setIndicatorString(42+""+dist[42]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[44], y + yAdd[44]).distanceSquaredTo(target))) / dist[44];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[44];
            rc.setIndicatorString(44+""+dist[44]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[48], y + yAdd[48]).distanceSquaredTo(target))) / dist[48];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[48];
            rc.setIndicatorString(48+""+dist[48]);
        }

        gain = (initDist - Math.sqrt(new MapLocation(x + xAdd[26], y + yAdd[26]).distanceSquaredTo(target))) / dist[26];
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir[26];
            rc.setIndicatorString(26+""+dist[26]);
        }

        return ans;

    }
}
