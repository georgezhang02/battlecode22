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

        int cost;

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


        Clock.getBytecodesLeft();

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
            cost = rc.senseRubble(ml9) + 10;

            if(cost + dist8 < dist9){
                dist9 = cost + dist8;
                dir9 = dir8;
            }

            if(cost + dist1 < dist9){
                dist9 = cost + dist1;
                dir9 = dir1;
            }
        }

        if(rc.onTheMap(ml10)){
            cost = rc.senseRubble(ml10) + 10;

            if(cost + dist9 < dist10){
                dist10 = cost + dist9;
                dir10 = dir9;
            }

            if(cost + dist8 < dist10){
                dist10 = cost + dist8;
                dir10 = dir8;
            }

            if(cost + dist1 < dist10){
                dist10 = cost + dist1;
                dir10 = dir1;
            }

            if(cost + dist2 < dist10){
                dist10 = cost + dist2;
                dir10 = dir2;
            }
        }

        if(rc.onTheMap(ml11)){
            cost = rc.senseRubble(ml11) + 10;

            if(cost + dist10 < dist11){
                dist11 = cost + dist10;
                dir11 = dir10;
            }

            if(cost + dist1 < dist11){
                dist11 = cost + dist1;
                dir11 = dir1;
            }

            if(cost + dist2 < dist11){
                dist11 = cost + dist2;
                dir11 = dir2;
            }
        }

        if(rc.onTheMap(ml12)){
            cost = rc.senseRubble(ml12) + 10;

            if(cost + dist11 < dist12){
                dist12 = cost + dist11;
                dir12 = dir11;
            }

            if(cost + dist2 < dist12){
                dist12 = cost + dist2;
                dir12 = dir2;
            }
        }

        if(rc.onTheMap(ml13)){
            cost = rc.senseRubble(ml13) + 10;

            if(cost + dist11 < dist13){
                dist13 = cost + dist11;
                dir13 = dir11;
            }

            if(cost + dist2 < dist13){
                dist13 = cost + dist2;
                dir13 = dir2;
            }

            if(cost + dist3 < dist13){
                dist13 = cost + dist3;
                dir13 = dir3;
            }

            if(cost + dist12 < dist13){
                dist13 = cost + dist12;
                dir13 = dir12;
            }
        }

        if(rc.onTheMap(ml14)){
            cost = rc.senseRubble(ml14) + 10;

            if(cost + dist2 < dist14){
                dist14 = cost + dist2;
                dir14 = dir2;
            }

            if(cost + dist3 < dist14){
                dist14 = cost + dist3;
                dir14 = dir3;
            }

            if(cost + dist4 < dist14){
                dist14 = cost + dist4;
                dir14 = dir4;
            }

            if(cost + dist13 < dist14){
                dist14 = cost + dist13;
                dir14 = dir13;
            }
        }

        if(rc.onTheMap(ml15)){
            cost = rc.senseRubble(ml15) + 10;

            if(cost + dist3 < dist15){
                dist15 = cost + dist3;
                dir15 = dir3;
            }

            if(cost + dist4 < dist15){
                dist15 = cost + dist4;
                dir15 = dir4;
            }

            if(cost + dist14 < dist15){
                dist15 = cost + dist14;
                dir15 = dir14;
            }
        }

        if(rc.onTheMap(ml16)){
            cost = rc.senseRubble(ml16) + 10;

            if(cost + dist4 < dist16){
                dist16 = cost + dist4;
                dir16 = dir4;
            }

            if(cost + dist15 < dist16){
                dist16 = cost + dist15;
                dir16 = dir15;
            }
        }

        if(rc.onTheMap(ml17)){
            cost = rc.senseRubble(ml17) + 10;

            if(cost + dist5 < dist17){
                dist17 = cost + dist5;
                dir17 = dir5;
            }

            if(cost + dist4 < dist17){
                dist17 = cost + dist4;
                dir17 = dir4;
            }

            if(cost + dist15 < dist17){
                dist17 = cost + dist15;
                dir17 = dir15;
            }

            if(cost + dist16 < dist17){
                dist17 = cost + dist16;
                dir17 = dir16;
            }
        }

        if(rc.onTheMap(ml18)){
            cost = rc.senseRubble(ml18) + 10;

            if(cost + dist6 < dist18){
                dist18 = cost + dist6;
                dir18 = dir6;
            }

            if(cost + dist5 < dist18){
                dist18 = cost + dist5;
                dir18 = dir5;
            }

            if(cost + dist4 < dist18){
                dist18 = cost + dist4;
                dir18 = dir4;
            }

            if(cost + dist17 < dist18){
                dist18 = cost + dist17;
                dir18 = dir17;
            }
        }

        if(rc.onTheMap(ml19)){
            cost = rc.senseRubble(ml19) + 10;

            if(cost + dist6 < dist19){
                dist19 = cost + dist6;
                dir19 = dir6;
            }

            if(cost + dist5 < dist19){
                dist19 = cost + dist5;
                dir19 = dir5;
            }

            if(cost + dist18 < dist19){
                dist19 = cost + dist18;
                dir19 = dir18;
            }
        }

        if(rc.onTheMap(ml20)){
            cost = rc.senseRubble(ml20) + 10;

            if(cost + dist6 < dist20){
                dist20 = cost + dist6;
                dir20 = dir6;
            }

            if(cost + dist19 < dist20){
                dist20 = cost + dist19;
                dir20 = dir19;
            }
        }

        if(rc.onTheMap(ml21)){
            cost = rc.senseRubble(ml21) + 10;

            if(cost + dist20 < dist21){
                dist21 = cost + dist20;
                dir21 = dir20;
            }

            if(cost + dist7 < dist21){
                dist21 = cost + dist7;
                dir21 = dir7;
            }

            if(cost + dist6 < dist21){
                dist21 = cost + dist6;
                dir21 = dir6;
            }

            if(cost + dist19 < dist21){
                dist21 = cost + dist19;
                dir21 = dir19;
            }
        }

        if(rc.onTheMap(ml22)){
            cost = rc.senseRubble(ml22) + 10;

            if(cost + dist21 < dist22){
                dist22 = cost + dist21;
                dir22 = dir21;
            }

            if(cost + dist8 < dist22){
                dist22 = cost + dist8;
                dir22 = dir8;
            }

            if(cost + dist7 < dist22){
                dist22 = cost + dist7;
                dir22 = dir7;
            }

            if(cost + dist6 < dist22){
                dist22 = cost + dist6;
                dir22 = dir6;
            }
        }

        if(rc.onTheMap(ml23)){
            cost = rc.senseRubble(ml23) + 10;

            if(cost + dist22 < dist23){
                dist23 = cost + dist22;
                dir23 = dir22;
            }

            if(cost + dist9 < dist23){
                dist23 = cost + dist9;
                dir23 = dir9;
            }

            if(cost + dist8 < dist23){
                dist23 = cost + dist8;
                dir23 = dir8;
            }

            if(cost + dist7 < dist23){
                dist23 = cost + dist7;
                dir23 = dir7;
            }
        }

        if(rc.onTheMap(ml24)){
            cost = rc.senseRubble(ml24) + 10;

            if(cost + dist23 < dist24){
                dist24 = cost + dist23;
                dir24 = dir23;
            }

            if(cost + dist9 < dist24){
                dist24 = cost + dist9;
                dir24 = dir9;
            }

            if(cost + dist8 < dist24){
                dist24 = cost + dist8;
                dir24 = dir8;
            }
        }

        if(rc.onTheMap(ml25)){
            cost = rc.senseRubble(ml25) + 10;

            if(cost + dist24 < dist25){
                dist25 = cost + dist24;
                dir25 = dir24;
            }

            if(cost + dist9 < dist25){
                dist25 = cost + dist9;
                dir25 = dir9;
            }
        }

        if(rc.onTheMap(ml26)){
            cost = rc.senseRubble(ml26) + 10;

            if(cost + dist25 < dist26){
                dist26 = cost + dist25;
                dir26 = dir25;
            }

            if(cost + dist24 < dist26){
                dist26 = cost + dist24;
                dir26 = dir24;
            }

            if(cost + dist9 < dist26){
                dist26 = cost + dist9;
                dir26 = dir9;
            }

            if(cost + dist10 < dist26){
                dist26 = cost + dist10;
                dir26 = dir10;
            }
        }

        if(rc.onTheMap(ml27)){
            cost = rc.senseRubble(ml27) + 10;

            if(cost + dist26 < dist27){
                dist27 = cost + dist26;
                dir27 = dir26;
            }

            if(cost + dist9 < dist27){
                dist27 = cost + dist9;
                dir27 = dir9;
            }

            if(cost + dist10 < dist27){
                dist27 = cost + dist10;
                dir27 = dir10;
            }

            if(cost + dist11 < dist27){
                dist27 = cost + dist11;
                dir27 = dir11;
            }
        }

        if(rc.onTheMap(ml28)){
            cost = rc.senseRubble(ml28) + 10;

            if(cost + dist27 < dist28){
                dist28 = cost + dist27;
                dir28 = dir27;
            }

            if(cost + dist10 < dist28){
                dist28 = cost + dist10;
                dir28 = dir10;
            }

            if(cost + dist11 < dist28){
                dist28 = cost + dist11;
                dir28 = dir11;
            }

            if(cost + dist12 < dist28){
                dist28 = cost + dist12;
                dir28 = dir12;
            }
        }

        if(rc.onTheMap(ml29)){
            cost = rc.senseRubble(ml29) + 10;

            if(cost + dist28 < dist29){
                dist29 = cost + dist28;
                dir29 = dir28;
            }

            if(cost + dist11 < dist29){
                dist29 = cost + dist11;
                dir29 = dir11;
            }

            if(cost + dist12 < dist29){
                dist29 = cost + dist12;
                dir29 = dir12;
            }
        }

        if(rc.onTheMap(ml30)){
            cost = rc.senseRubble(ml30) + 10;

            if(cost + dist29 < dist30){
                dist30 = cost + dist29;
                dir30 = dir29;
            }

            if(cost + dist12 < dist30){
                dist30 = cost + dist12;
                dir30 = dir12;
            }

            if(cost + dist13 < dist30){
                dist30 = cost + dist13;
                dir30 = dir13;
            }
        }

        if(rc.onTheMap(ml31)){
            cost = rc.senseRubble(ml31) + 10;

            if(cost + dist12 < dist31){
                dist31 = cost + dist12;
                dir31 = dir12;
            }

            if(cost + dist13 < dist31){
                dist31 = cost + dist13;
                dir31 = dir13;
            }

            if(cost + dist14 < dist31){
                dist31 = cost + dist14;
                dir31 = dir14;
            }

            if(cost + dist30 < dist31){
                dist31 = cost + dist30;
                dir31 = dir30;
            }
        }

        if(rc.onTheMap(ml32)){
            cost = rc.senseRubble(ml32) + 10;

            if(cost + dist13 < dist32){
                dist32 = cost + dist13;
                dir32 = dir13;
            }

            if(cost + dist14 < dist32){
                dist32 = cost + dist14;
                dir32 = dir14;
            }

            if(cost + dist15 < dist32){
                dist32 = cost + dist15;
                dir32 = dir15;
            }

            if(cost + dist31 < dist32){
                dist32 = cost + dist31;
                dir32 = dir31;
            }
        }

        if(rc.onTheMap(ml33)){
            cost = rc.senseRubble(ml33) + 10;

            if(cost + dist14 < dist33){
                dist33 = cost + dist14;
                dir33 = dir14;
            }

            if(cost + dist15 < dist33){
                dist33 = cost + dist15;
                dir33 = dir15;
            }

            if(cost + dist16 < dist33){
                dist33 = cost + dist16;
                dir33 = dir16;
            }

            if(cost + dist32 < dist33){
                dist33 = cost + dist32;
                dir33 = dir32;
            }
        }

        if(rc.onTheMap(ml34)){
            cost = rc.senseRubble(ml34) + 10;

            if(cost + dist15 < dist34){
                dist34 = cost + dist15;
                dir34 = dir15;
            }

            if(cost + dist16 < dist34){
                dist34 = cost + dist16;
                dir34 = dir16;
            }

            if(cost + dist33 < dist34){
                dist34 = cost + dist33;
                dir34 = dir33;
            }
        }

        if(rc.onTheMap(ml35)){
            cost = rc.senseRubble(ml35) + 10;

            if(cost + dist17 < dist35){
                dist35 = cost + dist17;
                dir35 = dir17;
            }

            if(cost + dist16 < dist35){
                dist35 = cost + dist16;
                dir35 = dir16;
            }

            if(cost + dist34 < dist35){
                dist35 = cost + dist34;
                dir35 = dir34;
            }
        }

        if(rc.onTheMap(ml36)){
            cost = rc.senseRubble(ml36) + 10;

            if(cost + dist18 < dist36){
                dist36 = cost + dist18;
                dir36 = dir18;
            }

            if(cost + dist17 < dist36){
                dist36 = cost + dist17;
                dir36 = dir17;
            }

            if(cost + dist16 < dist36){
                dist36 = cost + dist16;
                dir36 = dir16;
            }

            if(cost + dist35 < dist36){
                dist36 = cost + dist35;
                dir36 = dir35;
            }
        }

        if(rc.onTheMap(ml37)){
            cost = rc.senseRubble(ml37) + 10;

            if(cost + dist19 < dist37){
                dist37 = cost + dist19;
                dir37 = dir19;
            }

            if(cost + dist18 < dist37){
                dist37 = cost + dist18;
                dir37 = dir18;
            }

            if(cost + dist17 < dist37){
                dist37 = cost + dist17;
                dir37 = dir17;
            }

            if(cost + dist36 < dist37){
                dist37 = cost + dist36;
                dir37 = dir36;
            }
        }

        if(rc.onTheMap(ml38)){
            cost = rc.senseRubble(ml38) + 10;

            if(cost + dist20 < dist38){
                dist38 = cost + dist20;
                dir38 = dir20;
            }

            if(cost + dist19 < dist38){
                dist38 = cost + dist19;
                dir38 = dir19;
            }

            if(cost + dist18 < dist38){
                dist38 = cost + dist18;
                dir38 = dir18;
            }

            if(cost + dist37 < dist38){
                dist38 = cost + dist37;
                dir38 = dir37;
            }
        }

        if(rc.onTheMap(ml39)){
            cost = rc.senseRubble(ml39) + 10;

            if(cost + dist20 < dist39){
                dist39 = cost + dist20;
                dir39 = dir20;
            }

            if(cost + dist19 < dist39){
                dist39 = cost + dist19;
                dir39 = dir19;
            }

            if(cost + dist38 < dist39){
                dist39 = cost + dist38;
                dir39 = dir38;
            }
        }

        if(rc.onTheMap(ml40)){
            cost = rc.senseRubble(ml40) + 10;

            if(cost + dist21 < dist40){
                dist40 = cost + dist21;
                dir40 = dir21;
            }

            if(cost + dist20 < dist40){
                dist40 = cost + dist20;
                dir40 = dir20;
            }

            if(cost + dist39 < dist40){
                dist40 = cost + dist39;
                dir40 = dir39;
            }
        }

        if(rc.onTheMap(ml41)){
            cost = rc.senseRubble(ml41) + 10;

            if(cost + dist40 < dist41){
                dist41 = cost + dist40;
                dir41 = dir40;
            }

            if(cost + dist22 < dist41){
                dist41 = cost + dist22;
                dir41 = dir22;
            }

            if(cost + dist21 < dist41){
                dist41 = cost + dist21;
                dir41 = dir21;
            }

            if(cost + dist20 < dist41){
                dist41 = cost + dist20;
                dir41 = dir20;
            }
        }

        if(rc.onTheMap(ml42)){
            cost = rc.senseRubble(ml42) + 10;

            if(cost + dist41 < dist42){
                dist42 = cost + dist41;
                dir42 = dir41;
            }

            if(cost + dist23 < dist42){
                dist42 = cost + dist23;
                dir42 = dir23;
            }

            if(cost + dist22 < dist42){
                dist42 = cost + dist22;
                dir42 = dir22;
            }

            if(cost + dist21 < dist42){
                dist42 = cost + dist21;
                dir42 = dir21;
            }
        }

        if(rc.onTheMap(ml43)){
            cost = rc.senseRubble(ml43) + 10;

            if(cost + dist42 < dist43){
                dist43 = cost + dist42;
                dir43 = dir42;
            }

            if(cost + dist24 < dist43){
                dist43 = cost + dist24;
                dir43 = dir24;
            }

            if(cost + dist23 < dist43){
                dist43 = cost + dist23;
                dir43 = dir23;
            }

            if(cost + dist22 < dist43){
                dist43 = cost + dist22;
                dir43 = dir22;
            }
        }

        if(rc.onTheMap(ml44)){
            cost = rc.senseRubble(ml44) + 10;

            if(cost + dist43 < dist44){
                dist44 = cost + dist43;
                dir44 = dir43;
            }

            if(cost + dist25 < dist44){
                dist44 = cost + dist25;
                dir44 = dir25;
            }

            if(cost + dist24 < dist44){
                dist44 = cost + dist24;
                dir44 = dir24;
            }

            if(cost + dist23 < dist44){
                dist44 = cost + dist23;
                dir44 = dir23;
            }
        }

        if(rc.onTheMap(ml45)){
            cost = rc.senseRubble(ml45) + 10;

            if(cost + dist44 < dist45){
                dist45 = cost + dist44;
                dir45 = dir44;
            }

            if(cost + dist25 < dist45){
                dist45 = cost + dist25;
                dir45 = dir25;
            }

            if(cost + dist24 < dist45){
                dist45 = cost + dist24;
                dir45 = dir24;
            }
        }

        if(rc.onTheMap(ml46)){
            cost = rc.senseRubble(ml46) + 10;

            if(cost + dist45 < dist46){
                dist46 = cost + dist45;
                dir46 = dir45;
            }

            if(cost + dist25 < dist46){
                dist46 = cost + dist25;
                dir46 = dir25;
            }

            if(cost + dist26 < dist46){
                dist46 = cost + dist26;
                dir46 = dir26;
            }
        }

        if(rc.onTheMap(ml47)){
            cost = rc.senseRubble(ml47) + 10;

            if(cost + dist46 < dist47){
                dist47 = cost + dist46;
                dir47 = dir46;
            }

            if(cost + dist25 < dist47){
                dist47 = cost + dist25;
                dir47 = dir25;
            }

            if(cost + dist26 < dist47){
                dist47 = cost + dist26;
                dir47 = dir26;
            }

            if(cost + dist27 < dist47){
                dist47 = cost + dist27;
                dir47 = dir27;
            }
        }

        if(rc.onTheMap(ml48)){
            cost = rc.senseRubble(ml48) + 10;

            if(cost + dist47 < dist48){
                dist48 = cost + dist47;
                dir48 = dir47;
            }

            if(cost + dist26 < dist48){
                dist48 = cost + dist26;
                dir48 = dir26;
            }

            if(cost + dist27 < dist48){
                dist48 = cost + dist27;
                dir48 = dir27;
            }

            if(cost + dist28 < dist48){
                dist48 = cost + dist28;
                dir48 = dir28;
            }
        }

        if(rc.onTheMap(ml49)){
            cost = rc.senseRubble(ml49) + 10;

            if(cost + dist48 < dist49){
                dist49 = cost + dist48;
                dir49 = dir48;
            }

            if(cost + dist27 < dist49){
                dist49 = cost + dist27;
                dir49 = dir27;
            }

            if(cost + dist28 < dist49){
                dist49 = cost + dist28;
                dir49 = dir28;
            }

            if(cost + dist29 < dist49){
                dist49 = cost + dist29;
                dir49 = dir29;
            }
        }

        if(rc.onTheMap(ml50)){
            cost = rc.senseRubble(ml50) + 10;

            if(cost + dist49 < dist50){
                dist50 = cost + dist49;
                dir50 = dir49;
            }

            if(cost + dist28 < dist50){
                dist50 = cost + dist28;
                dir50 = dir28;
            }

            if(cost + dist29 < dist50){
                dist50 = cost + dist29;
                dir50 = dir29;
            }
        }

        if(rc.onTheMap(ml51)){
            cost = rc.senseRubble(ml51) + 10;

            if(cost + dist50 < dist51){
                dist51 = cost + dist50;
                dir51 = dir50;
            }

            if(cost + dist29 < dist51){
                dist51 = cost + dist29;
                dir51 = dir29;
            }

            if(cost + dist12 < dist51){
                dist51 = cost + dist12;
                dir51 = dir12;
            }

            if(cost + dist30 < dist51){
                dist51 = cost + dist30;
                dir51 = dir30;
            }
        }

        if(rc.onTheMap(ml52)){
            cost = rc.senseRubble(ml52) + 10;

            if(cost + dist51 < dist52){
                dist52 = cost + dist51;
                dir52 = dir51;
            }

            if(cost + dist30 < dist52){
                dist52 = cost + dist30;
                dir52 = dir30;
            }

            if(cost + dist31 < dist52){
                dist52 = cost + dist31;
                dir52 = dir31;
            }
        }

        if(rc.onTheMap(ml53)){
            cost = rc.senseRubble(ml53) + 10;

            if(cost + dist30 < dist53){
                dist53 = cost + dist30;
                dir53 = dir30;
            }

            if(cost + dist31 < dist53){
                dist53 = cost + dist31;
                dir53 = dir31;
            }

            if(cost + dist32 < dist53){
                dist53 = cost + dist32;
                dir53 = dir32;
            }

            if(cost + dist52 < dist53){
                dist53 = cost + dist52;
                dir53 = dir52;
            }
        }

        if(rc.onTheMap(ml54)){
            cost = rc.senseRubble(ml54) + 10;

            if(cost + dist31 < dist54){
                dist54 = cost + dist31;
                dir54 = dir31;
            }

            if(cost + dist32 < dist54){
                dist54 = cost + dist32;
                dir54 = dir32;
            }

            if(cost + dist33 < dist54){
                dist54 = cost + dist33;
                dir54 = dir33;
            }

            if(cost + dist53 < dist54){
                dist54 = cost + dist53;
                dir54 = dir53;
            }
        }

        if(rc.onTheMap(ml55)){
            cost = rc.senseRubble(ml55) + 10;

            if(cost + dist32 < dist55){
                dist55 = cost + dist32;
                dir55 = dir32;
            }

            if(cost + dist33 < dist55){
                dist55 = cost + dist33;
                dir55 = dir33;
            }

            if(cost + dist34 < dist55){
                dist55 = cost + dist34;
                dir55 = dir34;
            }

            if(cost + dist54 < dist55){
                dist55 = cost + dist54;
                dir55 = dir54;
            }
        }

        if(rc.onTheMap(ml56)){
            cost = rc.senseRubble(ml56) + 10;

            if(cost + dist33 < dist56){
                dist56 = cost + dist33;
                dir56 = dir33;
            }

            if(cost + dist34 < dist56){
                dist56 = cost + dist34;
                dir56 = dir34;
            }

            if(cost + dist55 < dist56){
                dist56 = cost + dist55;
                dir56 = dir55;
            }
        }

        if(rc.onTheMap(ml57)){
            cost = rc.senseRubble(ml57) + 10;

            if(cost + dist16 < dist57){
                dist57 = cost + dist16;
                dir57 = dir16;
            }

            if(cost + dist35 < dist57){
                dist57 = cost + dist35;
                dir57 = dir35;
            }

            if(cost + dist34 < dist57){
                dist57 = cost + dist34;
                dir57 = dir34;
            }

            if(cost + dist56 < dist57){
                dist57 = cost + dist56;
                dir57 = dir56;
            }
        }

        if(rc.onTheMap(ml58)){
            cost = rc.senseRubble(ml58) + 10;

            if(cost + dist36 < dist58){
                dist58 = cost + dist36;
                dir58 = dir36;
            }

            if(cost + dist35 < dist58){
                dist58 = cost + dist35;
                dir58 = dir35;
            }

            if(cost + dist57 < dist58){
                dist58 = cost + dist57;
                dir58 = dir57;
            }
        }

        if(rc.onTheMap(ml59)){
            cost = rc.senseRubble(ml59) + 10;

            if(cost + dist37 < dist59){
                dist59 = cost + dist37;
                dir59 = dir37;
            }

            if(cost + dist36 < dist59){
                dist59 = cost + dist36;
                dir59 = dir36;
            }

            if(cost + dist35 < dist59){
                dist59 = cost + dist35;
                dir59 = dir35;
            }

            if(cost + dist58 < dist59){
                dist59 = cost + dist58;
                dir59 = dir58;
            }
        }

        if(rc.onTheMap(ml60)){
            cost = rc.senseRubble(ml60) + 10;

            if(cost + dist38 < dist60){
                dist60 = cost + dist38;
                dir60 = dir38;
            }

            if(cost + dist37 < dist60){
                dist60 = cost + dist37;
                dir60 = dir37;
            }

            if(cost + dist36 < dist60){
                dist60 = cost + dist36;
                dir60 = dir36;
            }

            if(cost + dist59 < dist60){
                dist60 = cost + dist59;
                dir60 = dir59;
            }
        }

        if(rc.onTheMap(ml61)){
            cost = rc.senseRubble(ml61) + 10;

            if(cost + dist39 < dist61){
                dist61 = cost + dist39;
                dir61 = dir39;
            }

            if(cost + dist38 < dist61){
                dist61 = cost + dist38;
                dir61 = dir38;
            }

            if(cost + dist37 < dist61){
                dist61 = cost + dist37;
                dir61 = dir37;
            }

            if(cost + dist60 < dist61){
                dist61 = cost + dist60;
                dir61 = dir60;
            }
        }

        if(rc.onTheMap(ml62)){
            cost = rc.senseRubble(ml62) + 10;

            if(cost + dist39 < dist62){
                dist62 = cost + dist39;
                dir62 = dir39;
            }

            if(cost + dist38 < dist62){
                dist62 = cost + dist38;
                dir62 = dir38;
            }

            if(cost + dist61 < dist62){
                dist62 = cost + dist61;
                dir62 = dir61;
            }
        }

        if(rc.onTheMap(ml63)){
            cost = rc.senseRubble(ml63) + 10;

            if(cost + dist40 < dist63){
                dist63 = cost + dist40;
                dir63 = dir40;
            }

            if(cost + dist20 < dist63){
                dist63 = cost + dist20;
                dir63 = dir20;
            }

            if(cost + dist39 < dist63){
                dist63 = cost + dist39;
                dir63 = dir39;
            }

            if(cost + dist62 < dist63){
                dist63 = cost + dist62;
                dir63 = dir62;
            }
        }

        if(rc.onTheMap(ml64)){
            cost = rc.senseRubble(ml64) + 10;

            if(cost + dist41 < dist64){
                dist64 = cost + dist41;
                dir64 = dir41;
            }

            if(cost + dist40 < dist64){
                dist64 = cost + dist40;
                dir64 = dir40;
            }

            if(cost + dist63 < dist64){
                dist64 = cost + dist63;
                dir64 = dir63;
            }
        }

        if(rc.onTheMap(ml65)){
            cost = rc.senseRubble(ml65) + 10;

            if(cost + dist64 < dist65){
                dist65 = cost + dist64;
                dir65 = dir64;
            }

            if(cost + dist42 < dist65){
                dist65 = cost + dist42;
                dir65 = dir42;
            }

            if(cost + dist41 < dist65){
                dist65 = cost + dist41;
                dir65 = dir41;
            }

            if(cost + dist40 < dist65){
                dist65 = cost + dist40;
                dir65 = dir40;
            }
        }

        if(rc.onTheMap(ml66)){
            cost = rc.senseRubble(ml66) + 10;

            if(cost + dist65 < dist66){
                dist66 = cost + dist65;
                dir66 = dir65;
            }

            if(cost + dist43 < dist66){
                dist66 = cost + dist43;
                dir66 = dir43;
            }

            if(cost + dist42 < dist66){
                dist66 = cost + dist42;
                dir66 = dir42;
            }

            if(cost + dist41 < dist66){
                dist66 = cost + dist41;
                dir66 = dir41;
            }
        }

        if(rc.onTheMap(ml67)){
            cost = rc.senseRubble(ml67) + 10;

            if(cost + dist66 < dist67){
                dist67 = cost + dist66;
                dir67 = dir66;
            }

            if(cost + dist44 < dist67){
                dist67 = cost + dist44;
                dir67 = dir44;
            }

            if(cost + dist43 < dist67){
                dist67 = cost + dist43;
                dir67 = dir43;
            }

            if(cost + dist42 < dist67){
                dist67 = cost + dist42;
                dir67 = dir42;
            }
        }

        if(rc.onTheMap(ml68)){
            cost = rc.senseRubble(ml68) + 10;

            if(cost + dist67 < dist68){
                dist68 = cost + dist67;
                dir68 = dir67;
            }

            if(cost + dist45 < dist68){
                dist68 = cost + dist45;
                dir68 = dir45;
            }

            if(cost + dist44 < dist68){
                dist68 = cost + dist44;
                dir68 = dir44;
            }

            if(cost + dist43 < dist68){
                dist68 = cost + dist43;
                dir68 = dir43;
            }
        }


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
            rc.setIndicatorString(45+""+dist45);
        }

        gain = (initDist - Math.sqrt(ml46.distanceSquaredTo(target))) / dist46;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir46;
            rc.setIndicatorString(46+""+dist46);
        }

        gain = (initDist - Math.sqrt(ml47.distanceSquaredTo(target))) / dist47;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir47;
            rc.setIndicatorString(47+""+dist47);
        }

        gain = (initDist - Math.sqrt(ml48.distanceSquaredTo(target))) / dist48;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir48;
            rc.setIndicatorString(48+""+dist48);
        }

        gain = (initDist - Math.sqrt(ml49.distanceSquaredTo(target))) / dist49;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir49;
            rc.setIndicatorString(49+""+dist49);
        }

        gain = (initDist - Math.sqrt(ml50.distanceSquaredTo(target))) / dist50;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir50;
            rc.setIndicatorString(50+""+dist50);
        }

        gain = (initDist - Math.sqrt(ml51.distanceSquaredTo(target))) / dist51;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir51;
            rc.setIndicatorString(51+""+dist51);
        }

        gain = (initDist - Math.sqrt(ml52.distanceSquaredTo(target))) / dist52;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir52;
            rc.setIndicatorString(52+""+dist52);
        }

        gain = (initDist - Math.sqrt(ml53.distanceSquaredTo(target))) / dist53;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir53;
            rc.setIndicatorString(53+""+dist53);
        }

        gain = (initDist - Math.sqrt(ml54.distanceSquaredTo(target))) / dist54;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir54;
            rc.setIndicatorString(54+""+dist54);
        }

        gain = (initDist - Math.sqrt(ml55.distanceSquaredTo(target))) / dist55;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir55;
            rc.setIndicatorString(55+""+dist55);
        }

        gain = (initDist - Math.sqrt(ml56.distanceSquaredTo(target))) / dist56;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir56;
            rc.setIndicatorString(56+""+dist56);
        }

        gain = (initDist - Math.sqrt(ml57.distanceSquaredTo(target))) / dist57;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir57;
            rc.setIndicatorString(57+""+dist57);
        }

        gain = (initDist - Math.sqrt(ml58.distanceSquaredTo(target))) / dist58;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir58;
            rc.setIndicatorString(58+""+dist58);
        }

        gain = (initDist - Math.sqrt(ml59.distanceSquaredTo(target))) / dist59;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir59;
            rc.setIndicatorString(59+""+dist59);
        }

        gain = (initDist - Math.sqrt(ml60.distanceSquaredTo(target))) / dist60;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir60;
            rc.setIndicatorString(60+""+dist60);
        }

        gain = (initDist - Math.sqrt(ml61.distanceSquaredTo(target))) / dist61;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir61;
            rc.setIndicatorString(61+""+dist61);
        }

        gain = (initDist - Math.sqrt(ml62.distanceSquaredTo(target))) / dist62;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir62;
            rc.setIndicatorString(62+""+dist62);
        }

        gain = (initDist - Math.sqrt(ml63.distanceSquaredTo(target))) / dist63;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir63;
            rc.setIndicatorString(63+""+dist63);
        }

        gain = (initDist - Math.sqrt(ml64.distanceSquaredTo(target))) / dist64;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir64;
            rc.setIndicatorString(64+""+dist64);
        }

        gain = (initDist - Math.sqrt(ml65.distanceSquaredTo(target))) / dist65;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir65;
            rc.setIndicatorString(65+""+dist65);
        }

        gain = (initDist - Math.sqrt(ml66.distanceSquaredTo(target))) / dist66;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir66;
            rc.setIndicatorString(66+""+dist66);
        }

        gain = (initDist - Math.sqrt(ml67.distanceSquaredTo(target))) / dist67;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir67;
            rc.setIndicatorString(67+""+dist67);
        }

        gain = (initDist - Math.sqrt(ml68.distanceSquaredTo(target))) / dist68;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir68;
            rc.setIndicatorString(68+""+dist68);
        }

        gain = (initDist - Math.sqrt(ml44.distanceSquaredTo(target))) / dist44;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir44;
            rc.setIndicatorString(44+""+dist44);
        }

        gain = (initDist - Math.sqrt(ml25.distanceSquaredTo(target))) / dist25;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir25;
            rc.setIndicatorString(25+""+dist25);
        }

        gain = (initDist - Math.sqrt(ml29.distanceSquaredTo(target))) / dist29;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir29;
            rc.setIndicatorString(29+""+dist29);
        }

        gain = (initDist - Math.sqrt(ml30.distanceSquaredTo(target))) / dist30;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir30;
            rc.setIndicatorString(30+""+dist30);
        }

        gain = (initDist - Math.sqrt(ml34.distanceSquaredTo(target))) / dist34;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir34;
            rc.setIndicatorString(34+""+dist34);
        }

        gain = (initDist - Math.sqrt(ml35.distanceSquaredTo(target))) / dist35;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir35;
            rc.setIndicatorString(35+""+dist35);
        }

        gain = (initDist - Math.sqrt(ml39.distanceSquaredTo(target))) / dist39;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir39;
            rc.setIndicatorString(39+""+dist39);
        }

        gain = (initDist - Math.sqrt(ml40.distanceSquaredTo(target))) / dist40;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir40;
            rc.setIndicatorString(40+""+dist40);
        }





        return ans;

    }
}
