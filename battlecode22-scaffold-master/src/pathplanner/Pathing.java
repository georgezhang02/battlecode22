package pathplanner;

import battlecode.common.*;

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

        MapLocation ml0 = rc.getLocation();

        if(ml0.equals(target)) return null;

        Direction dir1 = Direction.SOUTH;
        Direction dir2 = Direction.SOUTHEAST;
        Direction dir3 = Direction.EAST;
        Direction dir4 = Direction.NORTHEAST;
        Direction dir5 = Direction.NORTH;
        Direction dir6 = Direction.NORTHWEST;
        Direction dir7 = Direction.WEST;
        Direction dir8 = Direction.SOUTHWEST;

        int dist0 = 0;

        Direction dir9 = null;
        Direction dir10 = null;
        Direction dir11 = null;
        Direction dir12 = null;
        Direction dir13 = null;
        Direction dir14 = null;
        Direction dir15 = null;
        Direction dir16 = null;
        Direction dir17 = null;
        Direction dir18 = null;
        Direction dir19 = null;
        Direction dir20 = null;
        Direction dir21 = null;
        Direction dir22 = null;
        Direction dir23 = null;
        Direction dir24 = null;
        Direction dir25 = null;
        Direction dir26 = null;
        Direction dir27 = null;
        Direction dir28 = null;
        Direction dir29 = null;
        Direction dir30 = null;
        Direction dir31 = null;
        Direction dir32 = null;
        Direction dir33 = null;
        Direction dir34 = null;
        Direction dir35 = null;
        Direction dir36 = null;
        Direction dir37 = null;
        Direction dir38 = null;
        Direction dir39 = null;
        Direction dir40 = null;
        Direction dir41 = null;
        Direction dir42 = null;
        Direction dir43 = null;
        Direction dir44 = null;
        Direction dir45 = null;
        Direction dir46 = null;
        Direction dir47 = null;
        Direction dir48 = null;
        Direction dir49 = null;
        Direction dir50 = null;
        Direction dir51 = null;
        Direction dir52 = null;
        Direction dir53 = null;
        Direction dir54 = null;
        Direction dir55 = null;
        Direction dir56 = null;
        Direction dir57 = null;
        Direction dir58 = null;
        Direction dir59 = null;
        Direction dir60 = null;
        Direction dir61 = null;
        Direction dir62 = null;
        Direction dir63 = null;
        Direction dir64 = null;
        Direction dir65 = null;
        Direction dir66 = null;
        Direction dir67 = null;
        Direction dir68 = null;

        int dist1 = 7200000;
        int dist2 = 7200000;
        int dist3 = 7200000;
        int dist4 = 7200000;
        int dist5 = 7200000;
        int dist6 = 7200000;
        int dist7 = 7200000;
        int dist8 = 7200000;
        int dist9 = 7200000;
        int dist10 = 7200000;
        int dist11 = 7200000;
        int dist12 = 7200000;
        int dist13 = 7200000;
        int dist14 = 7200000;
        int dist15 = 7200000;
        int dist16 = 7200000;
        int dist17 = 7200000;
        int dist18 = 7200000;
        int dist19 = 7200000;
        int dist20 = 7200000;
        int dist21 = 7200000;
        int dist22 = 7200000;
        int dist23 = 7200000;
        int dist24 = 7200000;
        int dist25 = 7200000;
        int dist26 = 7200000;
        int dist27 = 7200000;
        int dist28 = 7200000;
        int dist29 = 7200000;
        int dist30 = 7200000;
        int dist31 = 7200000;
        int dist32 = 7200000;
        int dist33 = 7200000;
        int dist34 = 7200000;
        int dist35 = 7200000;
        int dist36 = 7200000;
        int dist37 = 7200000;
        int dist38 = 7200000;
        int dist39 = 7200000;
        int dist40 = 7200000;
        int dist41 = 7200000;
        int dist42 = 7200000;
        int dist43 = 7200000;
        int dist44 = 7200000;
        int dist45 = 7200000;
        int dist46 = 7200000;
        int dist47 = 7200000;
        int dist48 = 7200000;
        int dist49 = 7200000;
        int dist50 = 7200000;
        int dist51 = 7200000;
        int dist52 = 7200000;
        int dist53 = 7200000;
        int dist54 = 7200000;
        int dist55 = 7200000;
        int dist56 = 7200000;
        int dist57 = 7200000;
        int dist58 = 7200000;
        int dist59 = 7200000;
        int dist60 = 7200000;
        int dist61 = 7200000;
        int dist62 = 7200000;
        int dist63 = 7200000;
        int dist64 = 7200000;
        int dist65 = 7200000;
        int dist66 = 7200000;
        int dist67 = 7200000;
        int dist68 = 7200000;

        int cost1 = 2000;
        int cost2 = 2000;
        int cost3 = 2000;
        int cost4 = 2000;
        int cost5 = 2000;
        int cost6 = 2000;
        int cost7 = 2000;
        int cost8 = 2000;
        int cost9 = 2000;
        int cost10 = 2000;
        int cost11 = 2000;
        int cost12 = 2000;
        int cost13 = 2000;
        int cost14 = 2000;
        int cost15 = 2000;
        int cost16 = 2000;
        int cost17 = 2000;
        int cost18 = 2000;
        int cost19 = 2000;
        int cost20 = 2000;
        int cost21 = 2000;
        int cost22 = 2000;
        int cost23 = 2000;
        int cost24 = 2000;
        int cost25 = 2000;
        int cost26 = 2000;
        int cost27 = 2000;
        int cost28 = 2000;
        int cost29 = 2000;
        int cost30 = 2000;
        int cost31 = 2000;
        int cost32 = 2000;
        int cost33 = 2000;
        int cost34 = 2000;
        int cost35 = 2000;
        int cost36 = 2000;
        int cost37 = 2000;
        int cost38 = 2000;
        int cost39 = 2000;
        int cost40 = 2000;
        int cost41 = 2000;
        int cost42 = 2000;
        int cost43 = 2000;
        int cost44 = 2000;
        int cost45 = 2000;
        int cost46 = 2000;
        int cost47 = 2000;
        int cost48 = 2000;
        int cost49 = 2000;
        int cost50 = 2000;
        int cost51 = 2000;
        int cost52 = 2000;
        int cost53 = 2000;
        int cost54 = 2000;
        int cost55 = 2000;
        int cost56 = 2000;
        int cost57 = 2000;
        int cost58 = 2000;
        int cost59 = 2000;
        int cost60 = 2000;
        int cost61 = 2000;
        int cost62 = 2000;
        int cost63 = 2000;
        int cost64 = 2000;
        int cost65 = 2000;
        int cost66 = 2000;
        int cost67 = 2000;
        int cost68 = 2000;

        MapLocation ml1 = ml0.add(Direction.SOUTH);
        MapLocation ml2 = ml1.add(Direction.EAST);
        MapLocation ml3 = ml2.add(Direction.NORTH);
        MapLocation ml4 = ml3.add(Direction.NORTH);
        MapLocation ml5 = ml4.add(Direction.WEST);
        MapLocation ml6 = ml5.add(Direction.WEST);
        MapLocation ml7 = ml6.add(Direction.SOUTH);
        MapLocation ml8 = ml7.add(Direction.SOUTH);
        MapLocation ml9 = ml8.add(Direction.SOUTH);
        MapLocation ml10 = ml9.add(Direction.EAST);
        MapLocation ml11 = ml10.add(Direction.EAST);
        MapLocation ml12 = ml11.add(Direction.EAST);
        MapLocation ml13 = ml12.add(Direction.NORTH);
        MapLocation ml14 = ml13.add(Direction.NORTH);
        MapLocation ml15 = ml14.add(Direction.NORTH);
        MapLocation ml16 = ml15.add(Direction.NORTH);
        MapLocation ml17 = ml16.add(Direction.WEST);
        MapLocation ml18 = ml17.add(Direction.WEST);
        MapLocation ml19 = ml18.add(Direction.WEST);
        MapLocation ml20 = ml19.add(Direction.WEST);
        MapLocation ml21 = ml20.add(Direction.SOUTH);
        MapLocation ml22 = ml21.add(Direction.SOUTH);
        MapLocation ml23 = ml22.add(Direction.SOUTH);
        MapLocation ml24 = ml23.add(Direction.SOUTH);
        MapLocation ml25 = ml24.add(Direction.SOUTH);
        MapLocation ml26 = ml25.add(Direction.EAST);
        MapLocation ml27 = ml26.add(Direction.EAST);
        MapLocation ml28 = ml27.add(Direction.EAST);
        MapLocation ml29 = ml28.add(Direction.EAST);
        MapLocation ml30 = ml29.add(Direction.NORTHEAST);
        MapLocation ml31 = ml30.add(Direction.NORTH);
        MapLocation ml32 = ml31.add(Direction.NORTH);
        MapLocation ml33 = ml32.add(Direction.NORTH);
        MapLocation ml34 = ml33.add(Direction.NORTH);
        MapLocation ml35 = ml34.add(Direction.NORTHWEST);
        MapLocation ml36 = ml35.add(Direction.WEST);
        MapLocation ml37 = ml36.add(Direction.WEST);
        MapLocation ml38 = ml37.add(Direction.WEST);
        MapLocation ml39 = ml38.add(Direction.WEST);
        MapLocation ml40 = ml39.add(Direction.SOUTHWEST);
        MapLocation ml41 = ml40.add(Direction.SOUTH);
        MapLocation ml42 = ml41.add(Direction.SOUTH);
        MapLocation ml43 = ml42.add(Direction.SOUTH);
        MapLocation ml44 = ml43.add(Direction.SOUTH);
        MapLocation ml45 = ml44.add(Direction.SOUTH);
        MapLocation ml46 = ml45.add(Direction.SOUTHEAST);
        MapLocation ml47 = ml46.add(Direction.EAST);
        MapLocation ml48 = ml47.add(Direction.EAST);
        MapLocation ml49 = ml48.add(Direction.EAST);
        MapLocation ml50 = ml49.add(Direction.EAST);
        MapLocation ml51 = ml50.add(Direction.NORTHEAST);
        MapLocation ml52 = ml51.add(Direction.NORTHEAST);
        MapLocation ml53 = ml52.add(Direction.NORTH);
        MapLocation ml54 = ml53.add(Direction.NORTH);
        MapLocation ml55 = ml54.add(Direction.NORTH);
        MapLocation ml56 = ml55.add(Direction.NORTH);
        MapLocation ml57 = ml56.add(Direction.NORTHWEST);
        MapLocation ml58 = ml57.add(Direction.NORTHWEST);
        MapLocation ml59 = ml58.add(Direction.WEST);
        MapLocation ml60 = ml59.add(Direction.WEST);
        MapLocation ml61 = ml60.add(Direction.WEST);
        MapLocation ml62 = ml61.add(Direction.WEST);
        MapLocation ml63 = ml62.add(Direction.SOUTHWEST);
        MapLocation ml64 = ml63.add(Direction.SOUTHWEST);
        MapLocation ml65 = ml64.add(Direction.SOUTH);
        MapLocation ml66 = ml65.add(Direction.SOUTH);
        MapLocation ml67 = ml66.add(Direction.SOUTH);
        MapLocation ml68 = ml67.add(Direction.SOUTH);
        MapLocation ml69 = ml68.add(Direction.SOUTH);

        int time1 = Clock.getBytecodesLeft();

        if(rc.onTheMap(ml1)){
            if(!rc.isLocationOccupied(ml1)){
                dist1 = rc.senseRubble(ml1) + 10;
            }
        }
        if(rc.onTheMap(ml2)){
            if(!rc.isLocationOccupied(ml2)){
                dist2 = rc.senseRubble(ml2) + 10;
            }
        }
        if(rc.onTheMap(ml3)){
            if(!rc.isLocationOccupied(ml3)){
                dist3 = rc.senseRubble(ml3) + 10;
            }
        }
        if(rc.onTheMap(ml4)){
            if(!rc.isLocationOccupied(ml4)){
                dist4 = rc.senseRubble(ml4) + 10;
            }
        }
        if(rc.onTheMap(ml5)){
            if(!rc.isLocationOccupied(ml5)){
                dist5 = rc.senseRubble(ml5) + 10;
            }
        }
        if(rc.onTheMap(ml6)){
            if(!rc.isLocationOccupied(ml6)){
                dist6 = rc.senseRubble(ml6) + 10;
            }
        }
        if(rc.onTheMap(ml7)){
            if(!rc.isLocationOccupied(ml7)){
                dist7 = rc.senseRubble(ml7) + 10;
            }
        }
        if(rc.onTheMap(ml8)){
            if(!rc.isLocationOccupied(ml8)){
                dist8 = rc.senseRubble(ml8) + 10;
            }
        }
        if(rc.onTheMap(ml9)){
            cost9 = rc.senseRubble(ml9) + 10;

            if(cost9 + dist8 < dist9){
                dist9 = cost9 + dist8;
                dir9 = dir8;
            }

            if(cost9 + dist1 < dist9){
                dist9 = cost9 + dist1;
                dir9 = dir1;
            }
        }

        if(rc.onTheMap(ml10)){
            cost10 = rc.senseRubble(ml10) + 10;

            if(cost10 + dist9 < dist10){
                dist10 = cost10 + dist9;
                dir10 = dir9;
            }

            if(cost10 + dist8 < dist10){
                dist10 = cost10 + dist8;
                dir10 = dir8;
            }

            if(cost10 + dist1 < dist10){
                dist10 = cost10 + dist1;
                dir10 = dir1;
            }

            if(cost10 + dist2 < dist10){
                dist10 = cost10 + dist2;
                dir10 = dir2;
            }
        }

        if(rc.onTheMap(ml11)){
            cost11 = rc.senseRubble(ml11) + 10;

            if(cost11 + dist10 < dist11){
                dist11 = cost11 + dist10;
                dir11 = dir10;
            }

            if(cost11 + dist1 < dist11){
                dist11 = cost11 + dist1;
                dir11 = dir1;
            }

            if(cost11 + dist2 < dist11){
                dist11 = cost11 + dist2;
                dir11 = dir2;
            }
        }

        if(rc.onTheMap(ml12)){
            cost12 = rc.senseRubble(ml12) + 10;

            if(cost12 + dist11 < dist12){
                dist12 = cost12 + dist11;
                dir12 = dir11;
            }

            if(cost12 + dist2 < dist12){
                dist12 = cost12 + dist2;
                dir12 = dir2;
            }
        }

        if(rc.onTheMap(ml13)){
            cost13 = rc.senseRubble(ml13) + 10;

            if(cost13 + dist11 < dist13){
                dist13 = cost13 + dist11;
                dir13 = dir11;
            }

            if(cost13 + dist2 < dist13){
                dist13 = cost13 + dist2;
                dir13 = dir2;
            }

            if(cost13 + dist3 < dist13){
                dist13 = cost13 + dist3;
                dir13 = dir3;
            }

            if(cost13 + dist12 < dist13){
                dist13 = cost13 + dist12;
                dir13 = dir12;
            }
        }

        if(rc.onTheMap(ml14)){
            cost14 = rc.senseRubble(ml14) + 10;

            if(cost14 + dist2 < dist14){
                dist14 = cost14 + dist2;
                dir14 = dir2;
            }

            if(cost14 + dist3 < dist14){
                dist14 = cost14 + dist3;
                dir14 = dir3;
            }

            if(cost14 + dist4 < dist14){
                dist14 = cost14 + dist4;
                dir14 = dir4;
            }

            if(cost14 + dist13 < dist14){
                dist14 = cost14 + dist13;
                dir14 = dir13;
            }
        }

        if(rc.onTheMap(ml15)){
            cost15 = rc.senseRubble(ml15) + 10;

            if(cost15 + dist3 < dist15){
                dist15 = cost15 + dist3;
                dir15 = dir3;
            }

            if(cost15 + dist4 < dist15){
                dist15 = cost15 + dist4;
                dir15 = dir4;
            }

            if(cost15 + dist14 < dist15){
                dist15 = cost15 + dist14;
                dir15 = dir14;
            }
        }

        if(rc.onTheMap(ml16)){
            cost16 = rc.senseRubble(ml16) + 10;

            if(cost16 + dist4 < dist16){
                dist16 = cost16 + dist4;
                dir16 = dir4;
            }

            if(cost16 + dist15 < dist16){
                dist16 = cost16 + dist15;
                dir16 = dir15;
            }
        }

        if(rc.onTheMap(ml17)){
            cost17 = rc.senseRubble(ml17) + 10;

            if(cost17 + dist5 < dist17){
                dist17 = cost17 + dist5;
                dir17 = dir5;
            }

            if(cost17 + dist4 < dist17){
                dist17 = cost17 + dist4;
                dir17 = dir4;
            }

            if(cost17 + dist15 < dist17){
                dist17 = cost17 + dist15;
                dir17 = dir15;
            }

            if(cost17 + dist16 < dist17){
                dist17 = cost17 + dist16;
                dir17 = dir16;
            }
        }

        if(rc.onTheMap(ml18)){
            cost18 = rc.senseRubble(ml18) + 10;

            if(cost18 + dist6 < dist18){
                dist18 = cost18 + dist6;
                dir18 = dir6;
            }

            if(cost18 + dist5 < dist18){
                dist18 = cost18 + dist5;
                dir18 = dir5;
            }

            if(cost18 + dist4 < dist18){
                dist18 = cost18 + dist4;
                dir18 = dir4;
            }

            if(cost18 + dist17 < dist18){
                dist18 = cost18 + dist17;
                dir18 = dir17;
            }
        }

        if(rc.onTheMap(ml19)){
            cost19 = rc.senseRubble(ml19) + 10;

            if(cost19 + dist6 < dist19){
                dist19 = cost19 + dist6;
                dir19 = dir6;
            }

            if(cost19 + dist5 < dist19){
                dist19 = cost19 + dist5;
                dir19 = dir5;
            }

            if(cost19 + dist18 < dist19){
                dist19 = cost19 + dist18;
                dir19 = dir18;
            }
        }

        if(rc.onTheMap(ml20)){
            cost20 = rc.senseRubble(ml20) + 10;

            if(cost20 + dist6 < dist20){
                dist20 = cost20 + dist6;
                dir20 = dir6;
            }

            if(cost20 + dist19 < dist20){
                dist20 = cost20 + dist19;
                dir20 = dir19;
            }
        }

        if(rc.onTheMap(ml21)){
            cost21 = rc.senseRubble(ml21) + 10;

            if(cost21 + dist20 < dist21){
                dist21 = cost21 + dist20;
                dir21 = dir20;
            }

            if(cost21 + dist7 < dist21){
                dist21 = cost21 + dist7;
                dir21 = dir7;
            }

            if(cost21 + dist6 < dist21){
                dist21 = cost21 + dist6;
                dir21 = dir6;
            }

            if(cost21 + dist19 < dist21){
                dist21 = cost21 + dist19;
                dir21 = dir19;
            }
        }

        if(rc.onTheMap(ml22)){
            cost22 = rc.senseRubble(ml22) + 10;

            if(cost22 + dist21 < dist22){
                dist22 = cost22 + dist21;
                dir22 = dir21;
            }

            if(cost22 + dist8 < dist22){
                dist22 = cost22 + dist8;
                dir22 = dir8;
            }

            if(cost22 + dist7 < dist22){
                dist22 = cost22 + dist7;
                dir22 = dir7;
            }

            if(cost22 + dist6 < dist22){
                dist22 = cost22 + dist6;
                dir22 = dir6;
            }
        }

        if(rc.onTheMap(ml23)){
            cost23 = rc.senseRubble(ml23) + 10;

            if(cost23 + dist22 < dist23){
                dist23 = cost23 + dist22;
                dir23 = dir22;
            }

            if(cost23 + dist9 < dist23){
                dist23 = cost23 + dist9;
                dir23 = dir9;
            }

            if(cost23 + dist8 < dist23){
                dist23 = cost23 + dist8;
                dir23 = dir8;
            }

            if(cost23 + dist7 < dist23){
                dist23 = cost23 + dist7;
                dir23 = dir7;
            }
        }

        if(rc.onTheMap(ml24)){
            cost24 = rc.senseRubble(ml24) + 10;

            if(cost24 + dist23 < dist24){
                dist24 = cost24 + dist23;
                dir24 = dir23;
            }

            if(cost24 + dist9 < dist24){
                dist24 = cost24 + dist9;
                dir24 = dir9;
            }

            if(cost24 + dist8 < dist24){
                dist24 = cost24 + dist8;
                dir24 = dir8;
            }
        }

        if(rc.onTheMap(ml25)){
            cost25 = rc.senseRubble(ml25) + 10;

            if(cost25 + dist24 < dist25){
                dist25 = cost25 + dist24;
                dir25 = dir24;
            }

            if(cost25 + dist9 < dist25){
                dist25 = cost25 + dist9;
                dir25 = dir9;
            }
        }

        if(rc.onTheMap(ml26)){
            cost26 = rc.senseRubble(ml26) + 10;

            if(cost26 + dist25 < dist26){
                dist26 = cost26 + dist25;
                dir26 = dir25;
            }

            if(cost26 + dist24 < dist26){
                dist26 = cost26 + dist24;
                dir26 = dir24;
            }

            if(cost26 + dist9 < dist26){
                dist26 = cost26 + dist9;
                dir26 = dir9;
            }

            if(cost26 + dist10 < dist26){
                dist26 = cost26 + dist10;
                dir26 = dir10;
            }
        }

        if(rc.onTheMap(ml27)){
            cost27 = rc.senseRubble(ml27) + 10;

            if(cost27 + dist26 < dist27){
                dist27 = cost27 + dist26;
                dir27 = dir26;
            }

            if(cost27 + dist9 < dist27){
                dist27 = cost27 + dist9;
                dir27 = dir9;
            }

            if(cost27 + dist10 < dist27){
                dist27 = cost27 + dist10;
                dir27 = dir10;
            }

            if(cost27 + dist11 < dist27){
                dist27 = cost27 + dist11;
                dir27 = dir11;
            }
        }

        if(rc.onTheMap(ml28)){
            cost28 = rc.senseRubble(ml28) + 10;

            if(cost28 + dist27 < dist28){
                dist28 = cost28 + dist27;
                dir28 = dir27;
            }

            if(cost28 + dist10 < dist28){
                dist28 = cost28 + dist10;
                dir28 = dir10;
            }

            if(cost28 + dist11 < dist28){
                dist28 = cost28 + dist11;
                dir28 = dir11;
            }

            if(cost28 + dist12 < dist28){
                dist28 = cost28 + dist12;
                dir28 = dir12;
            }
        }

        if(rc.onTheMap(ml29)){
            cost29 = rc.senseRubble(ml29) + 10;

            if(cost29 + dist28 < dist29){
                dist29 = cost29 + dist28;
                dir29 = dir28;
            }

            if(cost29 + dist11 < dist29){
                dist29 = cost29 + dist11;
                dir29 = dir11;
            }

            if(cost29 + dist12 < dist29){
                dist29 = cost29 + dist12;
                dir29 = dir12;
            }
        }

        if(rc.onTheMap(ml30)){
            cost30 = rc.senseRubble(ml30) + 10;

            if(cost30 + dist29 < dist30){
                dist30 = cost30 + dist29;
                dir30 = dir29;
            }

            if(cost30 + dist12 < dist30){
                dist30 = cost30 + dist12;
                dir30 = dir12;
            }

            if(cost30 + dist13 < dist30){
                dist30 = cost30 + dist13;
                dir30 = dir13;
            }
        }

        if(rc.onTheMap(ml31)){
            cost31 = rc.senseRubble(ml31) + 10;

            if(cost31 + dist12 < dist31){
                dist31 = cost31 + dist12;
                dir31 = dir12;
            }

            if(cost31 + dist13 < dist31){
                dist31 = cost31 + dist13;
                dir31 = dir13;
            }

            if(cost31 + dist14 < dist31){
                dist31 = cost31 + dist14;
                dir31 = dir14;
            }

            if(cost31 + dist30 < dist31){
                dist31 = cost31 + dist30;
                dir31 = dir30;
            }
        }

        if(rc.onTheMap(ml32)){
            cost32 = rc.senseRubble(ml32) + 10;

            if(cost32 + dist13 < dist32){
                dist32 = cost32 + dist13;
                dir32 = dir13;
            }

            if(cost32 + dist14 < dist32){
                dist32 = cost32 + dist14;
                dir32 = dir14;
            }

            if(cost32 + dist15 < dist32){
                dist32 = cost32 + dist15;
                dir32 = dir15;
            }

            if(cost32 + dist31 < dist32){
                dist32 = cost32 + dist31;
                dir32 = dir31;
            }
        }

        if(rc.onTheMap(ml33)){
            cost33 = rc.senseRubble(ml33) + 10;

            if(cost33 + dist14 < dist33){
                dist33 = cost33 + dist14;
                dir33 = dir14;
            }

            if(cost33 + dist15 < dist33){
                dist33 = cost33 + dist15;
                dir33 = dir15;
            }

            if(cost33 + dist16 < dist33){
                dist33 = cost33 + dist16;
                dir33 = dir16;
            }

            if(cost33 + dist32 < dist33){
                dist33 = cost33 + dist32;
                dir33 = dir32;
            }
        }

        if(rc.onTheMap(ml34)){
            cost34 = rc.senseRubble(ml34) + 10;

            if(cost34 + dist15 < dist34){
                dist34 = cost34 + dist15;
                dir34 = dir15;
            }

            if(cost34 + dist16 < dist34){
                dist34 = cost34 + dist16;
                dir34 = dir16;
            }

            if(cost34 + dist33 < dist34){
                dist34 = cost34 + dist33;
                dir34 = dir33;
            }
        }

        if(rc.onTheMap(ml35)){
            cost35 = rc.senseRubble(ml35) + 10;

            if(cost35 + dist17 < dist35){
                dist35 = cost35 + dist17;
                dir35 = dir17;
            }

            if(cost35 + dist16 < dist35){
                dist35 = cost35 + dist16;
                dir35 = dir16;
            }

            if(cost35 + dist34 < dist35){
                dist35 = cost35 + dist34;
                dir35 = dir34;
            }
        }

        if(rc.onTheMap(ml36)){
            cost36 = rc.senseRubble(ml36) + 10;

            if(cost36 + dist18 < dist36){
                dist36 = cost36 + dist18;
                dir36 = dir18;
            }

            if(cost36 + dist17 < dist36){
                dist36 = cost36 + dist17;
                dir36 = dir17;
            }

            if(cost36 + dist16 < dist36){
                dist36 = cost36 + dist16;
                dir36 = dir16;
            }

            if(cost36 + dist35 < dist36){
                dist36 = cost36 + dist35;
                dir36 = dir35;
            }
        }

        if(rc.onTheMap(ml37)){
            cost37 = rc.senseRubble(ml37) + 10;

            if(cost37 + dist19 < dist37){
                dist37 = cost37 + dist19;
                dir37 = dir19;
            }

            if(cost37 + dist18 < dist37){
                dist37 = cost37 + dist18;
                dir37 = dir18;
            }

            if(cost37 + dist17 < dist37){
                dist37 = cost37 + dist17;
                dir37 = dir17;
            }

            if(cost37 + dist36 < dist37){
                dist37 = cost37 + dist36;
                dir37 = dir36;
            }
        }

        if(rc.onTheMap(ml38)){
            cost38 = rc.senseRubble(ml38) + 10;

            if(cost38 + dist20 < dist38){
                dist38 = cost38 + dist20;
                dir38 = dir20;
            }

            if(cost38 + dist19 < dist38){
                dist38 = cost38 + dist19;
                dir38 = dir19;
            }

            if(cost38 + dist18 < dist38){
                dist38 = cost38 + dist18;
                dir38 = dir18;
            }

            if(cost38 + dist37 < dist38){
                dist38 = cost38 + dist37;
                dir38 = dir37;
            }
        }

        if(rc.onTheMap(ml39)){
            cost39 = rc.senseRubble(ml39) + 10;

            if(cost39 + dist20 < dist39){
                dist39 = cost39 + dist20;
                dir39 = dir20;
            }

            if(cost39 + dist19 < dist39){
                dist39 = cost39 + dist19;
                dir39 = dir19;
            }

            if(cost39 + dist38 < dist39){
                dist39 = cost39 + dist38;
                dir39 = dir38;
            }
        }

        if(rc.onTheMap(ml40)){
            cost40 = rc.senseRubble(ml40) + 10;

            if(cost40 + dist21 < dist40){
                dist40 = cost40 + dist21;
                dir40 = dir21;
            }

            if(cost40 + dist20 < dist40){
                dist40 = cost40 + dist20;
                dir40 = dir20;
            }

            if(cost40 + dist39 < dist40){
                dist40 = cost40 + dist39;
                dir40 = dir39;
            }
        }

        if(rc.onTheMap(ml41)){
            cost41 = rc.senseRubble(ml41) + 10;

            if(cost41 + dist40 < dist41){
                dist41 = cost41 + dist40;
                dir41 = dir40;
            }

            if(cost41 + dist22 < dist41){
                dist41 = cost41 + dist22;
                dir41 = dir22;
            }

            if(cost41 + dist21 < dist41){
                dist41 = cost41 + dist21;
                dir41 = dir21;
            }

            if(cost41 + dist20 < dist41){
                dist41 = cost41 + dist20;
                dir41 = dir20;
            }
        }

        if(rc.onTheMap(ml42)){
            cost42 = rc.senseRubble(ml42) + 10;

            if(cost42 + dist41 < dist42){
                dist42 = cost42 + dist41;
                dir42 = dir41;
            }

            if(cost42 + dist23 < dist42){
                dist42 = cost42 + dist23;
                dir42 = dir23;
            }

            if(cost42 + dist22 < dist42){
                dist42 = cost42 + dist22;
                dir42 = dir22;
            }

            if(cost42 + dist21 < dist42){
                dist42 = cost42 + dist21;
                dir42 = dir21;
            }
        }

        if(rc.onTheMap(ml43)){
            cost43 = rc.senseRubble(ml43) + 10;

            if(cost43 + dist42 < dist43){
                dist43 = cost43 + dist42;
                dir43 = dir42;
            }

            if(cost43 + dist24 < dist43){
                dist43 = cost43 + dist24;
                dir43 = dir24;
            }

            if(cost43 + dist23 < dist43){
                dist43 = cost43 + dist23;
                dir43 = dir23;
            }

            if(cost43 + dist22 < dist43){
                dist43 = cost43 + dist22;
                dir43 = dir22;
            }
        }

        if(rc.onTheMap(ml44)){
            cost44 = rc.senseRubble(ml44) + 10;

            if(cost44 + dist43 < dist44){
                dist44 = cost44 + dist43;
                dir44 = dir43;
            }

            if(cost44 + dist25 < dist44){
                dist44 = cost44 + dist25;
                dir44 = dir25;
            }

            if(cost44 + dist24 < dist44){
                dist44 = cost44 + dist24;
                dir44 = dir24;
            }

            if(cost44 + dist23 < dist44){
                dist44 = cost44 + dist23;
                dir44 = dir23;
            }
        }

        if(rc.onTheMap(ml45)){
            cost45 = rc.senseRubble(ml45) + 10;

            if(cost45 + dist44 < dist45){
                dist45 = cost45 + dist44;
                dir45 = dir44;
            }

            if(cost45 + dist25 < dist45){
                dist45 = cost45 + dist25;
                dir45 = dir25;
            }

            if(cost45 + dist24 < dist45){
                dist45 = cost45 + dist24;
                dir45 = dir24;
            }
        }

        if(rc.onTheMap(ml46)){
            cost46 = rc.senseRubble(ml46) + 10;

            if(cost46 + dist45 < dist46){
                dist46 = cost46 + dist45;
                dir46 = dir45;
            }

            if(cost46 + dist25 < dist46){
                dist46 = cost46 + dist25;
                dir46 = dir25;
            }

            if(cost46 + dist26 < dist46){
                dist46 = cost46 + dist26;
                dir46 = dir26;
            }
        }

        if(rc.onTheMap(ml47)){
            cost47 = rc.senseRubble(ml47) + 10;

            if(cost47 + dist46 < dist47){
                dist47 = cost47 + dist46;
                dir47 = dir46;
            }

            if(cost47 + dist25 < dist47){
                dist47 = cost47 + dist25;
                dir47 = dir25;
            }

            if(cost47 + dist26 < dist47){
                dist47 = cost47 + dist26;
                dir47 = dir26;
            }

            if(cost47 + dist27 < dist47){
                dist47 = cost47 + dist27;
                dir47 = dir27;
            }
        }

        if(rc.onTheMap(ml48)){
            cost48 = rc.senseRubble(ml48) + 10;

            if(cost48 + dist47 < dist48){
                dist48 = cost48 + dist47;
                dir48 = dir47;
            }

            if(cost48 + dist26 < dist48){
                dist48 = cost48 + dist26;
                dir48 = dir26;
            }

            if(cost48 + dist27 < dist48){
                dist48 = cost48 + dist27;
                dir48 = dir27;
            }

            if(cost48 + dist28 < dist48){
                dist48 = cost48 + dist28;
                dir48 = dir28;
            }
        }

        if(rc.onTheMap(ml49)){
            cost49 = rc.senseRubble(ml49) + 10;

            if(cost49 + dist48 < dist49){
                dist49 = cost49 + dist48;
                dir49 = dir48;
            }

            if(cost49 + dist27 < dist49){
                dist49 = cost49 + dist27;
                dir49 = dir27;
            }

            if(cost49 + dist28 < dist49){
                dist49 = cost49 + dist28;
                dir49 = dir28;
            }

            if(cost49 + dist29 < dist49){
                dist49 = cost49 + dist29;
                dir49 = dir29;
            }
        }

        if(rc.onTheMap(ml50)){
            cost50 = rc.senseRubble(ml50) + 10;

            if(cost50 + dist49 < dist50){
                dist50 = cost50 + dist49;
                dir50 = dir49;
            }

            if(cost50 + dist28 < dist50){
                dist50 = cost50 + dist28;
                dir50 = dir28;
            }

            if(cost50 + dist29 < dist50){
                dist50 = cost50 + dist29;
                dir50 = dir29;
            }
        }

        if(rc.onTheMap(ml51)){
            cost51 = rc.senseRubble(ml51) + 10;

            if(cost51 + dist50 < dist51){
                dist51 = cost51 + dist50;
                dir51 = dir50;
            }

            if(cost51 + dist29 < dist51){
                dist51 = cost51 + dist29;
                dir51 = dir29;
            }

            if(cost51 + dist12 < dist51){
                dist51 = cost51 + dist12;
                dir51 = dir12;
            }

            if(cost51 + dist30 < dist51){
                dist51 = cost51 + dist30;
                dir51 = dir30;
            }
        }

        if(rc.onTheMap(ml52)){
            cost52 = rc.senseRubble(ml52) + 10;

            if(cost52 + dist51 < dist52){
                dist52 = cost52 + dist51;
                dir52 = dir51;
            }

            if(cost52 + dist30 < dist52){
                dist52 = cost52 + dist30;
                dir52 = dir30;
            }

            if(cost52 + dist31 < dist52){
                dist52 = cost52 + dist31;
                dir52 = dir31;
            }
        }

        if(rc.onTheMap(ml53)){
            cost53 = rc.senseRubble(ml53) + 10;

            if(cost53 + dist30 < dist53){
                dist53 = cost53 + dist30;
                dir53 = dir30;
            }

            if(cost53 + dist31 < dist53){
                dist53 = cost53 + dist31;
                dir53 = dir31;
            }

            if(cost53 + dist32 < dist53){
                dist53 = cost53 + dist32;
                dir53 = dir32;
            }

            if(cost53 + dist52 < dist53){
                dist53 = cost53 + dist52;
                dir53 = dir52;
            }
        }

        if(rc.onTheMap(ml54)){
            cost54 = rc.senseRubble(ml54) + 10;

            if(cost54 + dist31 < dist54){
                dist54 = cost54 + dist31;
                dir54 = dir31;
            }

            if(cost54 + dist32 < dist54){
                dist54 = cost54 + dist32;
                dir54 = dir32;
            }

            if(cost54 + dist33 < dist54){
                dist54 = cost54 + dist33;
                dir54 = dir33;
            }

            if(cost54 + dist53 < dist54){
                dist54 = cost54 + dist53;
                dir54 = dir53;
            }
        }

        if(rc.onTheMap(ml55)){
            cost55 = rc.senseRubble(ml55) + 10;

            if(cost55 + dist32 < dist55){
                dist55 = cost55 + dist32;
                dir55 = dir32;
            }

            if(cost55 + dist33 < dist55){
                dist55 = cost55 + dist33;
                dir55 = dir33;
            }

            if(cost55 + dist34 < dist55){
                dist55 = cost55 + dist34;
                dir55 = dir34;
            }

            if(cost55 + dist54 < dist55){
                dist55 = cost55 + dist54;
                dir55 = dir54;
            }
        }

        if(rc.onTheMap(ml56)){
            cost56 = rc.senseRubble(ml56) + 10;

            if(cost56 + dist33 < dist56){
                dist56 = cost56 + dist33;
                dir56 = dir33;
            }

            if(cost56 + dist34 < dist56){
                dist56 = cost56 + dist34;
                dir56 = dir34;
            }

            if(cost56 + dist55 < dist56){
                dist56 = cost56 + dist55;
                dir56 = dir55;
            }
        }

        if(rc.onTheMap(ml57)){
            cost57 = rc.senseRubble(ml57) + 10;

            if(cost57 + dist16 < dist57){
                dist57 = cost57 + dist16;
                dir57 = dir16;
            }

            if(cost57 + dist35 < dist57){
                dist57 = cost57 + dist35;
                dir57 = dir35;
            }

            if(cost57 + dist34 < dist57){
                dist57 = cost57 + dist34;
                dir57 = dir34;
            }

            if(cost57 + dist56 < dist57){
                dist57 = cost57 + dist56;
                dir57 = dir56;
            }
        }

        if(rc.onTheMap(ml58)){
            cost58 = rc.senseRubble(ml58) + 10;

            if(cost58 + dist36 < dist58){
                dist58 = cost58 + dist36;
                dir58 = dir36;
            }

            if(cost58 + dist35 < dist58){
                dist58 = cost58 + dist35;
                dir58 = dir35;
            }

            if(cost58 + dist57 < dist58){
                dist58 = cost58 + dist57;
                dir58 = dir57;
            }
        }

        if(rc.onTheMap(ml59)){
            cost59 = rc.senseRubble(ml59) + 10;

            if(cost59 + dist37 < dist59){
                dist59 = cost59 + dist37;
                dir59 = dir37;
            }

            if(cost59 + dist36 < dist59){
                dist59 = cost59 + dist36;
                dir59 = dir36;
            }

            if(cost59 + dist35 < dist59){
                dist59 = cost59 + dist35;
                dir59 = dir35;
            }

            if(cost59 + dist58 < dist59){
                dist59 = cost59 + dist58;
                dir59 = dir58;
            }
        }

        if(rc.onTheMap(ml60)){
            cost60 = rc.senseRubble(ml60) + 10;

            if(cost60 + dist38 < dist60){
                dist60 = cost60 + dist38;
                dir60 = dir38;
            }

            if(cost60 + dist37 < dist60){
                dist60 = cost60 + dist37;
                dir60 = dir37;
            }

            if(cost60 + dist36 < dist60){
                dist60 = cost60 + dist36;
                dir60 = dir36;
            }

            if(cost60 + dist59 < dist60){
                dist60 = cost60 + dist59;
                dir60 = dir59;
            }
        }

        if(rc.onTheMap(ml61)){
            cost61 = rc.senseRubble(ml61) + 10;

            if(cost61 + dist39 < dist61){
                dist61 = cost61 + dist39;
                dir61 = dir39;
            }

            if(cost61 + dist38 < dist61){
                dist61 = cost61 + dist38;
                dir61 = dir38;
            }

            if(cost61 + dist37 < dist61){
                dist61 = cost61 + dist37;
                dir61 = dir37;
            }

            if(cost61 + dist60 < dist61){
                dist61 = cost61 + dist60;
                dir61 = dir60;
            }
        }

        if(rc.onTheMap(ml62)){
            cost62 = rc.senseRubble(ml62) + 10;

            if(cost62 + dist39 < dist62){
                dist62 = cost62 + dist39;
                dir62 = dir39;
            }

            if(cost62 + dist38 < dist62){
                dist62 = cost62 + dist38;
                dir62 = dir38;
            }

            if(cost62 + dist61 < dist62){
                dist62 = cost62 + dist61;
                dir62 = dir61;
            }
        }

        if(rc.onTheMap(ml63)){
            cost63 = rc.senseRubble(ml63) + 10;

            if(cost63 + dist40 < dist63){
                dist63 = cost63 + dist40;
                dir63 = dir40;
            }

            if(cost63 + dist20 < dist63){
                dist63 = cost63 + dist20;
                dir63 = dir20;
            }

            if(cost63 + dist39 < dist63){
                dist63 = cost63 + dist39;
                dir63 = dir39;
            }

            if(cost63 + dist62 < dist63){
                dist63 = cost63 + dist62;
                dir63 = dir62;
            }
        }

        if(rc.onTheMap(ml64)){
            cost64 = rc.senseRubble(ml64) + 10;

            if(cost64 + dist41 < dist64){
                dist64 = cost64 + dist41;
                dir64 = dir41;
            }

            if(cost64 + dist40 < dist64){
                dist64 = cost64 + dist40;
                dir64 = dir40;
            }

            if(cost64 + dist63 < dist64){
                dist64 = cost64 + dist63;
                dir64 = dir63;
            }
        }

        if(rc.onTheMap(ml65)){
            cost65 = rc.senseRubble(ml65) + 10;

            if(cost65 + dist64 < dist65){
                dist65 = cost65 + dist64;
                dir65 = dir64;
            }

            if(cost65 + dist42 < dist65){
                dist65 = cost65 + dist42;
                dir65 = dir42;
            }

            if(cost65 + dist41 < dist65){
                dist65 = cost65 + dist41;
                dir65 = dir41;
            }

            if(cost65 + dist40 < dist65){
                dist65 = cost65 + dist40;
                dir65 = dir40;
            }
        }

        if(rc.onTheMap(ml66)){
            cost66 = rc.senseRubble(ml66) + 10;

            if(cost66 + dist65 < dist66){
                dist66 = cost66 + dist65;
                dir66 = dir65;
            }

            if(cost66 + dist43 < dist66){
                dist66 = cost66 + dist43;
                dir66 = dir43;
            }

            if(cost66 + dist42 < dist66){
                dist66 = cost66 + dist42;
                dir66 = dir42;
            }

            if(cost66 + dist41 < dist66){
                dist66 = cost66 + dist41;
                dir66 = dir41;
            }
        }

        if(rc.onTheMap(ml67)){
            cost67 = rc.senseRubble(ml67) + 10;

            if(cost67 + dist66 < dist67){
                dist67 = cost67 + dist66;
                dir67 = dir66;
            }

            if(cost67 + dist44 < dist67){
                dist67 = cost67 + dist44;
                dir67 = dir44;
            }

            if(cost67 + dist43 < dist67){
                dist67 = cost67 + dist43;
                dir67 = dir43;
            }

            if(cost67 + dist42 < dist67){
                dist67 = cost67 + dist42;
                dir67 = dir42;
            }
        }

        if(rc.onTheMap(ml68)){
            cost68 = rc.senseRubble(ml68) + 10;

            if(cost68 + dist67 < dist68){
                dist68 = cost68 + dist67;
                dir68 = dir67;
            }

            if(cost68 + dist45 < dist68){
                dist68 = cost68 + dist45;
                dir68 = dir45;
            }

            if(cost68 + dist44 < dist68){
                dist68 = cost68 + dist44;
                dir68 = dir44;
            }

            if(cost68 + dist43 < dist68){
                dist68 = cost68 + dist43;
                dir68 = dir43;
            }
        }

        if(rc.onTheMap(ml9)){

            if(cost9 + dist24 < dist9){
                dist9 = cost9 + dist24;
                dir9 = dir24;
            }

            if(cost9 + dist23 < dist9){
                dist9 = cost9 + dist23;
                dir9 = dir23;
            }

            if(cost9 + dist10 < dist9){
                dist9 = cost9 + dist10;
                dir9 = dir10;
            }
        }

        if(rc.onTheMap(ml10)){

            if(cost10 + dist11 < dist10){
                dist10 = cost10 + dist11;
                dir10 = dir11;
            }
        }

        if(rc.onTheMap(ml11)){

            if(cost11 + dist12 < dist11){
                dist11 = cost11 + dist12;
                dir11 = dir12;
            }

            if(cost11 + dist13 < dist11){
                dist11 = cost11 + dist13;
                dir11 = dir13;
            }
        }

        if(rc.onTheMap(ml12)){

            if(cost12 + dist13 < dist12){
                dist12 = cost12 + dist13;
                dir12 = dir13;
            }
        }

        if(rc.onTheMap(ml13)){

            if(cost13 + dist14 < dist13){
                dist13 = cost13 + dist14;
                dir13 = dir14;
            }
        }

        if(rc.onTheMap(ml14)){

            if(cost14 + dist15 < dist14){
                dist14 = cost14 + dist15;
                dir14 = dir15;
            }
        }

        if(rc.onTheMap(ml15)){

            if(cost15 + dist17 < dist15){
                dist15 = cost15 + dist17;
                dir15 = dir17;
            }

            if(cost15 + dist16 < dist15){
                dist15 = cost15 + dist16;
                dir15 = dir16;
            }
        }

        if(rc.onTheMap(ml16)){

            if(cost16 + dist17 < dist16){
                dist16 = cost16 + dist17;
                dir16 = dir17;
            }
        }

        if(rc.onTheMap(ml17)){

            if(cost17 + dist18 < dist17){
                dist17 = cost17 + dist18;
                dir17 = dir18;
            }
        }

        if(rc.onTheMap(ml18)){

            if(cost18 + dist19 < dist18){
                dist18 = cost18 + dist19;
                dir18 = dir19;
            }
        }

        if(rc.onTheMap(ml19)){

            if(cost19 + dist21 < dist19){
                dist19 = cost19 + dist21;
                dir19 = dir21;
            }

            if(cost19 + dist20 < dist19){
                dist19 = cost19 + dist20;
                dir19 = dir20;
            }
        }

        if(rc.onTheMap(ml20)){

            if(cost20 + dist21 < dist20){
                dist20 = cost20 + dist21;
                dir20 = dir21;
            }
        }

        if(rc.onTheMap(ml21)){

            if(cost21 + dist22 < dist21){
                dist21 = cost21 + dist22;
                dir21 = dir22;
            }
        }

        if(rc.onTheMap(ml22)){

            if(cost22 + dist23 < dist22){
                dist22 = cost22 + dist23;
                dir22 = dir23;
            }
        }

        if(rc.onTheMap(ml23)){

            if(cost23 + dist24 < dist23){
                dist23 = cost23 + dist24;
                dir23 = dir24;
            }
        }

        if(rc.onTheMap(ml25)){

            if(cost25 + dist45 < dist25){
                dist25 = cost25 + dist45;
                dir25 = dir45;
            }

            if(cost25 + dist44 < dist25){
                dist25 = cost25 + dist44;
                dir25 = dir44;
            }

            if(cost25 + dist24 < dist25){
                dist25 = cost25 + dist24;
                dir25 = dir24;
            }

            if(cost25 + dist26 < dist25){
                dist25 = cost25 + dist26;
                dir25 = dir26;
            }

            if(cost25 + dist9 < dist25){
                dist25 = cost25 + dist9;
                dir25 = dir9;
            }
        }

        if(rc.onTheMap(ml26)){

            if(cost26 + dist25 < dist26){
                dist26 = cost26 + dist25;
                dir26 = dir25;
            }

            if(cost26 + dist24 < dist26){
                dist26 = cost26 + dist24;
                dir26 = dir24;
            }

            if(cost26 + dist9 < dist26){
                dist26 = cost26 + dist9;
                dir26 = dir9;
            }

            if(cost26 + dist27 < dist26){
                dist26 = cost26 + dist27;
                dir26 = dir27;
            }

            if(cost26 + dist10 < dist26){
                dist26 = cost26 + dist10;
                dir26 = dir10;
            }
        }

        if(rc.onTheMap(ml27)){

            if(cost27 + dist26 < dist27){
                dist27 = cost27 + dist26;
                dir27 = dir26;
            }

            if(cost27 + dist9 < dist27){
                dist27 = cost27 + dist9;
                dir27 = dir9;
            }

            if(cost27 + dist10 < dist27){
                dist27 = cost27 + dist10;
                dir27 = dir10;
            }

            if(cost27 + dist28 < dist27){
                dist27 = cost27 + dist28;
                dir27 = dir28;
            }

            if(cost27 + dist11 < dist27){
                dist27 = cost27 + dist11;
                dir27 = dir11;
            }
        }

        if(rc.onTheMap(ml28)){

            if(cost28 + dist27 < dist28){
                dist28 = cost28 + dist27;
                dir28 = dir27;
            }

            if(cost28 + dist10 < dist28){
                dist28 = cost28 + dist10;
                dir28 = dir10;
            }

            if(cost28 + dist11 < dist28){
                dist28 = cost28 + dist11;
                dir28 = dir11;
            }

            if(cost28 + dist29 < dist28){
                dist28 = cost28 + dist29;
                dir28 = dir29;
            }

            if(cost28 + dist12 < dist28){
                dist28 = cost28 + dist12;
                dir28 = dir12;
            }
        }

        if(rc.onTheMap(ml29)){

            if(cost29 + dist28 < dist29){
                dist29 = cost29 + dist28;
                dir29 = dir28;
            }

            if(cost29 + dist11 < dist29){
                dist29 = cost29 + dist11;
                dir29 = dir11;
            }

            if(cost29 + dist12 < dist29){
                dist29 = cost29 + dist12;
                dir29 = dir12;
            }

            if(cost29 + dist30 < dist29){
                dist29 = cost29 + dist30;
                dir29 = dir30;
            }
        }

        if(rc.onTheMap(ml30)){

            if(cost30 + dist29 < dist30){
                dist30 = cost30 + dist29;
                dir30 = dir29;
            }

            if(cost30 + dist12 < dist30){
                dist30 = cost30 + dist12;
                dir30 = dir12;
            }

            if(cost30 + dist13 < dist30){
                dist30 = cost30 + dist13;
                dir30 = dir13;
            }

            if(cost30 + dist31 < dist30){
                dist30 = cost30 + dist31;
                dir30 = dir31;
            }
        }

        if(rc.onTheMap(ml31)){

            if(cost31 + dist12 < dist31){
                dist31 = cost31 + dist12;
                dir31 = dir12;
            }

            if(cost31 + dist13 < dist31){
                dist31 = cost31 + dist13;
                dir31 = dir13;
            }

            if(cost31 + dist14 < dist31){
                dist31 = cost31 + dist14;
                dir31 = dir14;
            }

            if(cost31 + dist30 < dist31){
                dist31 = cost31 + dist30;
                dir31 = dir30;
            }

            if(cost31 + dist32 < dist31){
                dist31 = cost31 + dist32;
                dir31 = dir32;
            }
        }

        if(rc.onTheMap(ml32)){

            if(cost32 + dist13 < dist32){
                dist32 = cost32 + dist13;
                dir32 = dir13;
            }

            if(cost32 + dist14 < dist32){
                dist32 = cost32 + dist14;
                dir32 = dir14;
            }

            if(cost32 + dist15 < dist32){
                dist32 = cost32 + dist15;
                dir32 = dir15;
            }

            if(cost32 + dist31 < dist32){
                dist32 = cost32 + dist31;
                dir32 = dir31;
            }

            if(cost32 + dist33 < dist32){
                dist32 = cost32 + dist33;
                dir32 = dir33;
            }
        }

        if(rc.onTheMap(ml33)){

            if(cost33 + dist14 < dist33){
                dist33 = cost33 + dist14;
                dir33 = dir14;
            }

            if(cost33 + dist15 < dist33){
                dist33 = cost33 + dist15;
                dir33 = dir15;
            }

            if(cost33 + dist16 < dist33){
                dist33 = cost33 + dist16;
                dir33 = dir16;
            }

            if(cost33 + dist32 < dist33){
                dist33 = cost33 + dist32;
                dir33 = dir32;
            }

            if(cost33 + dist34 < dist33){
                dist33 = cost33 + dist34;
                dir33 = dir34;
            }
        }

        if(rc.onTheMap(ml34)){

            if(cost34 + dist15 < dist34){
                dist34 = cost34 + dist15;
                dir34 = dir15;
            }

            if(cost34 + dist16 < dist34){
                dist34 = cost34 + dist16;
                dir34 = dir16;
            }

            if(cost34 + dist35 < dist34){
                dist34 = cost34 + dist35;
                dir34 = dir35;
            }

            if(cost34 + dist33 < dist34){
                dist34 = cost34 + dist33;
                dir34 = dir33;
            }
        }

        if(rc.onTheMap(ml35)){

            if(cost35 + dist17 < dist35){
                dist35 = cost35 + dist17;
                dir35 = dir17;
            }

            if(cost35 + dist36 < dist35){
                dist35 = cost35 + dist36;
                dir35 = dir36;
            }

            if(cost35 + dist16 < dist35){
                dist35 = cost35 + dist16;
                dir35 = dir16;
            }

            if(cost35 + dist34 < dist35){
                dist35 = cost35 + dist34;
                dir35 = dir34;
            }
        }

        if(rc.onTheMap(ml36)){

            if(cost36 + dist18 < dist36){
                dist36 = cost36 + dist18;
                dir36 = dir18;
            }

            if(cost36 + dist37 < dist36){
                dist36 = cost36 + dist37;
                dir36 = dir37;
            }

            if(cost36 + dist17 < dist36){
                dist36 = cost36 + dist17;
                dir36 = dir17;
            }

            if(cost36 + dist16 < dist36){
                dist36 = cost36 + dist16;
                dir36 = dir16;
            }

            if(cost36 + dist35 < dist36){
                dist36 = cost36 + dist35;
                dir36 = dir35;
            }
        }

        if(rc.onTheMap(ml37)){

            if(cost37 + dist19 < dist37){
                dist37 = cost37 + dist19;
                dir37 = dir19;
            }

            if(cost37 + dist38 < dist37){
                dist37 = cost37 + dist38;
                dir37 = dir38;
            }

            if(cost37 + dist18 < dist37){
                dist37 = cost37 + dist18;
                dir37 = dir18;
            }

            if(cost37 + dist17 < dist37){
                dist37 = cost37 + dist17;
                dir37 = dir17;
            }

            if(cost37 + dist36 < dist37){
                dist37 = cost37 + dist36;
                dir37 = dir36;
            }
        }

        if(rc.onTheMap(ml38)){

            if(cost38 + dist20 < dist38){
                dist38 = cost38 + dist20;
                dir38 = dir20;
            }

            if(cost38 + dist39 < dist38){
                dist38 = cost38 + dist39;
                dir38 = dir39;
            }

            if(cost38 + dist19 < dist38){
                dist38 = cost38 + dist19;
                dir38 = dir19;
            }

            if(cost38 + dist18 < dist38){
                dist38 = cost38 + dist18;
                dir38 = dir18;
            }

            if(cost38 + dist37 < dist38){
                dist38 = cost38 + dist37;
                dir38 = dir37;
            }
        }

        if(rc.onTheMap(ml39)){

            if(cost39 + dist40 < dist39){
                dist39 = cost39 + dist40;
                dir39 = dir40;
            }

            if(cost39 + dist20 < dist39){
                dist39 = cost39 + dist20;
                dir39 = dir20;
            }

            if(cost39 + dist19 < dist39){
                dist39 = cost39 + dist19;
                dir39 = dir19;
            }

            if(cost39 + dist38 < dist39){
                dist39 = cost39 + dist38;
                dir39 = dir38;
            }
        }

        if(rc.onTheMap(ml40)){

            if(cost40 + dist41 < dist40){
                dist40 = cost40 + dist41;
                dir40 = dir41;
            }

            if(cost40 + dist21 < dist40){
                dist40 = cost40 + dist21;
                dir40 = dir21;
            }

            if(cost40 + dist20 < dist40){
                dist40 = cost40 + dist20;
                dir40 = dir20;
            }

            if(cost40 + dist39 < dist40){
                dist40 = cost40 + dist39;
                dir40 = dir39;
            }
        }

        if(rc.onTheMap(ml41)){

            if(cost41 + dist42 < dist41){
                dist41 = cost41 + dist42;
                dir41 = dir42;
            }

            if(cost41 + dist40 < dist41){
                dist41 = cost41 + dist40;
                dir41 = dir40;
            }

            if(cost41 + dist22 < dist41){
                dist41 = cost41 + dist22;
                dir41 = dir22;
            }

            if(cost41 + dist21 < dist41){
                dist41 = cost41 + dist21;
                dir41 = dir21;
            }

            if(cost41 + dist20 < dist41){
                dist41 = cost41 + dist20;
                dir41 = dir20;
            }
        }

        if(rc.onTheMap(ml42)){

            if(cost42 + dist43 < dist42){
                dist42 = cost42 + dist43;
                dir42 = dir43;
            }

            if(cost42 + dist41 < dist42){
                dist42 = cost42 + dist41;
                dir42 = dir41;
            }

            if(cost42 + dist23 < dist42){
                dist42 = cost42 + dist23;
                dir42 = dir23;
            }

            if(cost42 + dist22 < dist42){
                dist42 = cost42 + dist22;
                dir42 = dir22;
            }

            if(cost42 + dist21 < dist42){
                dist42 = cost42 + dist21;
                dir42 = dir21;
            }
        }

        if(rc.onTheMap(ml43)){

            if(cost43 + dist44 < dist43){
                dist43 = cost43 + dist44;
                dir43 = dir44;
            }

            if(cost43 + dist42 < dist43){
                dist43 = cost43 + dist42;
                dir43 = dir42;
            }

            if(cost43 + dist24 < dist43){
                dist43 = cost43 + dist24;
                dir43 = dir24;
            }

            if(cost43 + dist23 < dist43){
                dist43 = cost43 + dist23;
                dir43 = dir23;
            }

            if(cost43 + dist22 < dist43){
                dist43 = cost43 + dist22;
                dir43 = dir22;
            }
        }

        if(rc.onTheMap(ml44)){

            if(cost44 + dist45 < dist44){
                dist44 = cost44 + dist45;
                dir44 = dir45;
            }

            if(cost44 + dist43 < dist44){
                dist44 = cost44 + dist43;
                dir44 = dir43;
            }

            if(cost44 + dist25 < dist44){
                dist44 = cost44 + dist25;
                dir44 = dir25;
            }

            if(cost44 + dist24 < dist44){
                dist44 = cost44 + dist24;
                dir44 = dir24;
            }

            if(cost44 + dist23 < dist44){
                dist44 = cost44 + dist23;
                dir44 = dir23;
            }
        }

        if(rc.onTheMap(ml45)){

            if(cost45 + dist68 < dist45){
                dist45 = cost45 + dist68;
                dir45 = dir68;
            }

            if(cost45 + dist44 < dist45){
                dist45 = cost45 + dist44;
                dir45 = dir44;
            }

            if(cost45 + dist46 < dist45){
                dist45 = cost45 + dist46;
                dir45 = dir46;
            }

            if(cost45 + dist25 < dist45){
                dist45 = cost45 + dist25;
                dir45 = dir25;
            }

            if(cost45 + dist24 < dist45){
                dist45 = cost45 + dist24;
                dir45 = dir24;
            }
        }

        if(rc.onTheMap(ml46)){

            if(cost46 + dist45 < dist46){
                dist46 = cost46 + dist45;
                dir46 = dir45;
            }

            if(cost46 + dist25 < dist46){
                dist46 = cost46 + dist25;
                dir46 = dir25;
            }

            if(cost46 + dist47 < dist46){
                dist46 = cost46 + dist47;
                dir46 = dir47;
            }

            if(cost46 + dist26 < dist46){
                dist46 = cost46 + dist26;
                dir46 = dir26;
            }
        }

        if(rc.onTheMap(ml47)){

            if(cost47 + dist46 < dist47){
                dist47 = cost47 + dist46;
                dir47 = dir46;
            }

            if(cost47 + dist25 < dist47){
                dist47 = cost47 + dist25;
                dir47 = dir25;
            }

            if(cost47 + dist26 < dist47){
                dist47 = cost47 + dist26;
                dir47 = dir26;
            }

            if(cost47 + dist48 < dist47){
                dist47 = cost47 + dist48;
                dir47 = dir48;
            }

            if(cost47 + dist27 < dist47){
                dist47 = cost47 + dist27;
                dir47 = dir27;
            }
        }

        if(rc.onTheMap(ml48)){

            if(cost48 + dist47 < dist48){
                dist48 = cost48 + dist47;
                dir48 = dir47;
            }

            if(cost48 + dist26 < dist48){
                dist48 = cost48 + dist26;
                dir48 = dir26;
            }

            if(cost48 + dist27 < dist48){
                dist48 = cost48 + dist27;
                dir48 = dir27;
            }

            if(cost48 + dist49 < dist48){
                dist48 = cost48 + dist49;
                dir48 = dir49;
            }

            if(cost48 + dist28 < dist48){
                dist48 = cost48 + dist28;
                dir48 = dir28;
            }
        }

        if(rc.onTheMap(ml49)){

            if(cost49 + dist48 < dist49){
                dist49 = cost49 + dist48;
                dir49 = dir48;
            }

            if(cost49 + dist27 < dist49){
                dist49 = cost49 + dist27;
                dir49 = dir27;
            }

            if(cost49 + dist28 < dist49){
                dist49 = cost49 + dist28;
                dir49 = dir28;
            }

            if(cost49 + dist50 < dist49){
                dist49 = cost49 + dist50;
                dir49 = dir50;
            }

            if(cost49 + dist29 < dist49){
                dist49 = cost49 + dist29;
                dir49 = dir29;
            }
        }

        if(rc.onTheMap(ml50)){

            if(cost50 + dist49 < dist50){
                dist50 = cost50 + dist49;
                dir50 = dir49;
            }

            if(cost50 + dist28 < dist50){
                dist50 = cost50 + dist28;
                dir50 = dir28;
            }

            if(cost50 + dist29 < dist50){
                dist50 = cost50 + dist29;
                dir50 = dir29;
            }

            if(cost50 + dist51 < dist50){
                dist50 = cost50 + dist51;
                dir50 = dir51;
            }
        }

        if(rc.onTheMap(ml51)){

            if(cost51 + dist50 < dist51){
                dist51 = cost51 + dist50;
                dir51 = dir50;
            }

            if(cost51 + dist29 < dist51){
                dist51 = cost51 + dist29;
                dir51 = dir29;
            }

            if(cost51 + dist12 < dist51){
                dist51 = cost51 + dist12;
                dir51 = dir12;
            }

            if(cost51 + dist30 < dist51){
                dist51 = cost51 + dist30;
                dir51 = dir30;
            }

            if(cost51 + dist52 < dist51){
                dist51 = cost51 + dist52;
                dir51 = dir52;
            }
        }

        if(rc.onTheMap(ml52)){

            if(cost52 + dist51 < dist52){
                dist52 = cost52 + dist51;
                dir52 = dir51;
            }

            if(cost52 + dist30 < dist52){
                dist52 = cost52 + dist30;
                dir52 = dir30;
            }

            if(cost52 + dist31 < dist52){
                dist52 = cost52 + dist31;
                dir52 = dir31;
            }

            if(cost52 + dist53 < dist52){
                dist52 = cost52 + dist53;
                dir52 = dir53;
            }
        }

        if(rc.onTheMap(ml53)){

            if(cost53 + dist30 < dist53){
                dist53 = cost53 + dist30;
                dir53 = dir30;
            }

            if(cost53 + dist31 < dist53){
                dist53 = cost53 + dist31;
                dir53 = dir31;
            }

            if(cost53 + dist32 < dist53){
                dist53 = cost53 + dist32;
                dir53 = dir32;
            }

            if(cost53 + dist52 < dist53){
                dist53 = cost53 + dist52;
                dir53 = dir52;
            }

            if(cost53 + dist54 < dist53){
                dist53 = cost53 + dist54;
                dir53 = dir54;
            }
        }

        if(rc.onTheMap(ml54)){

            if(cost54 + dist31 < dist54){
                dist54 = cost54 + dist31;
                dir54 = dir31;
            }

            if(cost54 + dist32 < dist54){
                dist54 = cost54 + dist32;
                dir54 = dir32;
            }

            if(cost54 + dist33 < dist54){
                dist54 = cost54 + dist33;
                dir54 = dir33;
            }

            if(cost54 + dist53 < dist54){
                dist54 = cost54 + dist53;
                dir54 = dir53;
            }

            if(cost54 + dist55 < dist54){
                dist54 = cost54 + dist55;
                dir54 = dir55;
            }
        }

        if(rc.onTheMap(ml55)){

            if(cost55 + dist32 < dist55){
                dist55 = cost55 + dist32;
                dir55 = dir32;
            }

            if(cost55 + dist33 < dist55){
                dist55 = cost55 + dist33;
                dir55 = dir33;
            }

            if(cost55 + dist34 < dist55){
                dist55 = cost55 + dist34;
                dir55 = dir34;
            }

            if(cost55 + dist54 < dist55){
                dist55 = cost55 + dist54;
                dir55 = dir54;
            }

            if(cost55 + dist56 < dist55){
                dist55 = cost55 + dist56;
                dir55 = dir56;
            }
        }

        if(rc.onTheMap(ml56)){

            if(cost56 + dist33 < dist56){
                dist56 = cost56 + dist33;
                dir56 = dir33;
            }

            if(cost56 + dist34 < dist56){
                dist56 = cost56 + dist34;
                dir56 = dir34;
            }

            if(cost56 + dist57 < dist56){
                dist56 = cost56 + dist57;
                dir56 = dir57;
            }

            if(cost56 + dist55 < dist56){
                dist56 = cost56 + dist55;
                dir56 = dir55;
            }
        }

        if(rc.onTheMap(ml57)){

            if(cost57 + dist16 < dist57){
                dist57 = cost57 + dist16;
                dir57 = dir16;
            }

            if(cost57 + dist35 < dist57){
                dist57 = cost57 + dist35;
                dir57 = dir35;
            }

            if(cost57 + dist58 < dist57){
                dist57 = cost57 + dist58;
                dir57 = dir58;
            }

            if(cost57 + dist34 < dist57){
                dist57 = cost57 + dist34;
                dir57 = dir34;
            }

            if(cost57 + dist56 < dist57){
                dist57 = cost57 + dist56;
                dir57 = dir56;
            }
        }

        if(rc.onTheMap(ml58)){

            if(cost58 + dist36 < dist58){
                dist58 = cost58 + dist36;
                dir58 = dir36;
            }

            if(cost58 + dist59 < dist58){
                dist58 = cost58 + dist59;
                dir58 = dir59;
            }

            if(cost58 + dist35 < dist58){
                dist58 = cost58 + dist35;
                dir58 = dir35;
            }

            if(cost58 + dist57 < dist58){
                dist58 = cost58 + dist57;
                dir58 = dir57;
            }
        }

        if(rc.onTheMap(ml59)){

            if(cost59 + dist37 < dist59){
                dist59 = cost59 + dist37;
                dir59 = dir37;
            }

            if(cost59 + dist60 < dist59){
                dist59 = cost59 + dist60;
                dir59 = dir60;
            }

            if(cost59 + dist36 < dist59){
                dist59 = cost59 + dist36;
                dir59 = dir36;
            }

            if(cost59 + dist35 < dist59){
                dist59 = cost59 + dist35;
                dir59 = dir35;
            }

            if(cost59 + dist58 < dist59){
                dist59 = cost59 + dist58;
                dir59 = dir58;
            }
        }

        if(rc.onTheMap(ml60)){

            if(cost60 + dist38 < dist60){
                dist60 = cost60 + dist38;
                dir60 = dir38;
            }

            if(cost60 + dist61 < dist60){
                dist60 = cost60 + dist61;
                dir60 = dir61;
            }

            if(cost60 + dist37 < dist60){
                dist60 = cost60 + dist37;
                dir60 = dir37;
            }

            if(cost60 + dist36 < dist60){
                dist60 = cost60 + dist36;
                dir60 = dir36;
            }

            if(cost60 + dist59 < dist60){
                dist60 = cost60 + dist59;
                dir60 = dir59;
            }
        }

        if(rc.onTheMap(ml61)){

            if(cost61 + dist39 < dist61){
                dist61 = cost61 + dist39;
                dir61 = dir39;
            }

            if(cost61 + dist62 < dist61){
                dist61 = cost61 + dist62;
                dir61 = dir62;
            }

            if(cost61 + dist38 < dist61){
                dist61 = cost61 + dist38;
                dir61 = dir38;
            }

            if(cost61 + dist37 < dist61){
                dist61 = cost61 + dist37;
                dir61 = dir37;
            }

            if(cost61 + dist60 < dist61){
                dist61 = cost61 + dist60;
                dir61 = dir60;
            }
        }

        if(rc.onTheMap(ml62)){

            if(cost62 + dist63 < dist62){
                dist62 = cost62 + dist63;
                dir62 = dir63;
            }

            if(cost62 + dist39 < dist62){
                dist62 = cost62 + dist39;
                dir62 = dir39;
            }

            if(cost62 + dist38 < dist62){
                dist62 = cost62 + dist38;
                dir62 = dir38;
            }

            if(cost62 + dist61 < dist62){
                dist62 = cost62 + dist61;
                dir62 = dir61;
            }
        }

        if(rc.onTheMap(ml63)){

            if(cost63 + dist64 < dist63){
                dist63 = cost63 + dist64;
                dir63 = dir64;
            }

            if(cost63 + dist40 < dist63){
                dist63 = cost63 + dist40;
                dir63 = dir40;
            }

            if(cost63 + dist20 < dist63){
                dist63 = cost63 + dist20;
                dir63 = dir20;
            }

            if(cost63 + dist39 < dist63){
                dist63 = cost63 + dist39;
                dir63 = dir39;
            }

            if(cost63 + dist62 < dist63){
                dist63 = cost63 + dist62;
                dir63 = dir62;
            }
        }

        if(rc.onTheMap(ml64)){

            if(cost64 + dist65 < dist64){
                dist64 = cost64 + dist65;
                dir64 = dir65;
            }

            if(cost64 + dist41 < dist64){
                dist64 = cost64 + dist41;
                dir64 = dir41;
            }

            if(cost64 + dist40 < dist64){
                dist64 = cost64 + dist40;
                dir64 = dir40;
            }

            if(cost64 + dist63 < dist64){
                dist64 = cost64 + dist63;
                dir64 = dir63;
            }
        }

        if(rc.onTheMap(ml65)){

            if(cost65 + dist66 < dist65){
                dist65 = cost65 + dist66;
                dir65 = dir66;
            }

            if(cost65 + dist64 < dist65){
                dist65 = cost65 + dist64;
                dir65 = dir64;
            }

            if(cost65 + dist42 < dist65){
                dist65 = cost65 + dist42;
                dir65 = dir42;
            }

            if(cost65 + dist41 < dist65){
                dist65 = cost65 + dist41;
                dir65 = dir41;
            }

            if(cost65 + dist40 < dist65){
                dist65 = cost65 + dist40;
                dir65 = dir40;
            }
        }

        if(rc.onTheMap(ml66)){

            if(cost66 + dist67 < dist66){
                dist66 = cost66 + dist67;
                dir66 = dir67;
            }

            if(cost66 + dist65 < dist66){
                dist66 = cost66 + dist65;
                dir66 = dir65;
            }

            if(cost66 + dist43 < dist66){
                dist66 = cost66 + dist43;
                dir66 = dir43;
            }

            if(cost66 + dist42 < dist66){
                dist66 = cost66 + dist42;
                dir66 = dir42;
            }

            if(cost66 + dist41 < dist66){
                dist66 = cost66 + dist41;
                dir66 = dir41;
            }
        }

        if(rc.onTheMap(ml67)){

            if(cost67 + dist68 < dist67){
                dist67 = cost67 + dist68;
                dir67 = dir68;
            }

            if(cost67 + dist66 < dist67){
                dist67 = cost67 + dist66;
                dir67 = dir66;
            }

            if(cost67 + dist44 < dist67){
                dist67 = cost67 + dist44;
                dir67 = dir44;
            }

            if(cost67 + dist43 < dist67){
                dist67 = cost67 + dist43;
                dir67 = dir43;
            }

            if(cost67 + dist42 < dist67){
                dist67 = cost67 + dist42;
                dir67 = dir42;
            }
        }

        if(rc.onTheMap(ml68)){

            if(cost68 + dist67 < dist68){
                dist68 = cost68 + dist67;
                dir68 = dir67;
            }

            if(cost68 + dist45 < dist68){
                dist68 = cost68 + dist45;
                dir68 = dir45;
            }

            if(cost68 + dist44 < dist68){
                dist68 = cost68 + dist44;
                dir68 = dir44;
            }

            if(cost68 + dist43 < dist68){
                dist68 = cost68 + dist43;
                dir68 = dir43;
            }
        }

        int time2 = Clock.getBytecodesLeft();
        rc.setIndicatorString(time1+" "+time2);


        int xDiff = target.x - ml0.x;
        int yDiff = target.y - ml0.y;

        switch (xDiff){
            case -4:
                switch (yDiff){
                    case -2:
                        return dir68;
                    case -1:
                        return dir67;
                    case 0:
                        return dir66;
                    case 1:
                        return dir65;
                    case 2:
                        return dir64;
                }
                break;
            case -3:
                switch (yDiff){
                    case -3:
                        return dir45;
                    case -2:
                        return dir44;
                    case -1:
                        return dir43;
                    case 0:
                        return dir42;
                    case 1:
                        return dir41;
                    case 2:
                        return dir40;
                    case 3:
                        return dir63;
                }
                break;
            case -2:
                switch (yDiff){
                    case -4:
                        return dir46;
                    case -3:
                        return dir25;
                    case -2:
                        return dir24;
                    case -1:
                        return dir23;
                    case 0:
                        return dir22;
                    case 1:
                        return dir21;
                    case 2:
                        return dir20;
                    case 3:
                        return dir39;
                    case 4:
                        return dir62;
                }
                break;
            case -1:
                switch (yDiff){
                    case -4:
                        return dir47;
                    case -3:
                        return dir26;
                    case -2:
                        return dir9;
                    case -1:
                        return dir8;
                    case 0:
                        return dir7;
                    case 1:
                        return dir6;
                    case 2:
                        return dir19;
                    case 3:
                        return dir38;
                    case 4:
                        return dir61;
                }
                break;
            case 0:
                switch (yDiff){
                    case -4:
                        return dir48;
                    case -3:
                        return dir27;
                    case -2:
                        return dir10;
                    case -1:
                        return dir1;
                    case 1:
                        return dir5;
                    case 2:
                        return dir18;
                    case 3:
                        return dir37;
                    case 4:
                        return dir60;
                }
                break;
            case 1:
                switch (yDiff){
                    case -4:
                        return dir49;
                    case -3:
                        return dir28;
                    case -2:
                        return dir11;
                    case -1:
                        return dir2;
                    case 0:
                        return dir3;
                    case 1:
                        return dir4;
                    case 2:
                        return dir17;
                    case 3:
                        return dir36;
                    case 4:
                        return dir59;
                }
                break;
            case 2:
                switch (yDiff){
                    case -4:
                        return dir50;
                    case -3:
                        return dir29;
                    case -2:
                        return dir12;
                    case -1:
                        return dir13;
                    case 0:
                        return dir14;
                    case 1:
                        return dir15;
                    case 2:
                        return dir16;
                    case 3:
                        return dir35;
                    case 4:
                        return dir58;
                }
                break;
            case 3:
                switch (yDiff){
                    case -3:
                        return dir51;
                    case -2:
                        return dir30;
                    case -1:
                        return dir31;
                    case 0:
                        return dir32;
                    case 1:
                        return dir33;
                    case 2:
                        return dir34;
                    case 3:
                        return dir57;
                }
                break;
            case 4:
                switch (yDiff){
                    case -2:
                        return dir52;
                    case -1:
                        return dir53;
                    case 0:
                        return dir54;
                    case 1:
                        return dir55;
                    case 2:
                        return dir56;
                }
                break;
        }





        double gain;

        Direction ans = null;
        double initDist = Math.sqrt(ml0.distanceSquaredTo(target));
        double maxGainPerCost = 0;

        gain = (initDist - Math.sqrt(ml45.distanceSquaredTo(target))) / dist45;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir45;
        }

        gain = (initDist - Math.sqrt(ml46.distanceSquaredTo(target))) / dist46;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir46;
        }

        gain = (initDist - Math.sqrt(ml47.distanceSquaredTo(target))) / dist47;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir47;
        }

        gain = (initDist - Math.sqrt(ml48.distanceSquaredTo(target))) / dist48;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir48;
        }

        gain = (initDist - Math.sqrt(ml49.distanceSquaredTo(target))) / dist49;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir49;
        }

        gain = (initDist - Math.sqrt(ml50.distanceSquaredTo(target))) / dist50;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir50;
        }

        gain = (initDist - Math.sqrt(ml51.distanceSquaredTo(target))) / dist51;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir51;
        }

        gain = (initDist - Math.sqrt(ml52.distanceSquaredTo(target))) / dist52;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir52;
        }

        gain = (initDist - Math.sqrt(ml53.distanceSquaredTo(target))) / dist53;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir53;
        }

        gain = (initDist - Math.sqrt(ml54.distanceSquaredTo(target))) / dist54;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir54;
        }

        gain = (initDist - Math.sqrt(ml55.distanceSquaredTo(target))) / dist55;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir55;
        }

        gain = (initDist - Math.sqrt(ml56.distanceSquaredTo(target))) / dist56;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir56;
        }

        gain = (initDist - Math.sqrt(ml57.distanceSquaredTo(target))) / dist57;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir57;
        }

        gain = (initDist - Math.sqrt(ml58.distanceSquaredTo(target))) / dist58;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir58;
        }

        gain = (initDist - Math.sqrt(ml59.distanceSquaredTo(target))) / dist59;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir59;
        }

        gain = (initDist - Math.sqrt(ml60.distanceSquaredTo(target))) / dist60;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir60;
        }

        gain = (initDist - Math.sqrt(ml61.distanceSquaredTo(target))) / dist61;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir61;
        }

        gain = (initDist - Math.sqrt(ml62.distanceSquaredTo(target))) / dist62;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir62;
        }

        gain = (initDist - Math.sqrt(ml63.distanceSquaredTo(target))) / dist63;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir63;
        }

        gain = (initDist - Math.sqrt(ml64.distanceSquaredTo(target))) / dist64;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir64;
        }

        gain = (initDist - Math.sqrt(ml65.distanceSquaredTo(target))) / dist65;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir65;
        }

        gain = (initDist - Math.sqrt(ml66.distanceSquaredTo(target))) / dist66;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir66;
        }

        gain = (initDist - Math.sqrt(ml67.distanceSquaredTo(target))) / dist67;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir67;
        }

        gain = (initDist - Math.sqrt(ml68.distanceSquaredTo(target))) / dist68;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir68;
        }

        gain = (initDist - Math.sqrt(ml44.distanceSquaredTo(target))) / dist44;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir44;
        }

        gain = (initDist - Math.sqrt(ml25.distanceSquaredTo(target))) / dist25;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir25;
        }

        gain = (initDist - Math.sqrt(ml29.distanceSquaredTo(target))) / dist29;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir29;
        }

        gain = (initDist - Math.sqrt(ml30.distanceSquaredTo(target))) / dist30;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir30;
        }

        gain = (initDist - Math.sqrt(ml34.distanceSquaredTo(target))) / dist34;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir34;
        }

        gain = (initDist - Math.sqrt(ml35.distanceSquaredTo(target))) / dist35;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir35;
        }

        gain = (initDist - Math.sqrt(ml39.distanceSquaredTo(target))) / dist39;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir39;
        }

        gain = (initDist - Math.sqrt(ml40.distanceSquaredTo(target))) / dist40;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir40;
        }






        return ans;

    }
}
