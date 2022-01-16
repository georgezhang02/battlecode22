package dontatme_old;

import battlecode.common.*;

public class BFPathing20 extends Pathfinder {
    static Direction dir9;
    static Direction dir10;
    static Direction dir11;
    static Direction dir12;
    static Direction dir13;
    static Direction dir14;
    static Direction dir15;
    static Direction dir16;
    static Direction dir17;
    static Direction dir18;
    static Direction dir19;
    static Direction dir20;
    static Direction dir21;
    static Direction dir22;
    static Direction dir23;
    static Direction dir24;
    static Direction dir25;
    static Direction dir26;
    static Direction dir27;
    static Direction dir28;
    static Direction dir29;
    static Direction dir30;
    static Direction dir31;
    static Direction dir32;
    static Direction dir33;
    static Direction dir34;
    static Direction dir35;
    static Direction dir36;
    static Direction dir37;
    static Direction dir38;
    static Direction dir39;
    static Direction dir40;
    static Direction dir41;
    static Direction dir42;
    static Direction dir43;
    static Direction dir44;
    static Direction dir45;
    static Direction dir46;
    static Direction dir47;
    static Direction dir48;
    static Direction dir49;
    static Direction dir50;
    static Direction dir51;
    static Direction dir52;
    static Direction dir53;
    static Direction dir54;
    static Direction dir55;
    static Direction dir56;
    static Direction dir57;
    static Direction dir58;
    static Direction dir59;
    static Direction dir60;
    static Direction dir61;
    static Direction dir62;
    static Direction dir63;
    static Direction dir64;
    static Direction dir65;
    static Direction dir66;
    static Direction dir67;
    static Direction dir68;

    static int dist1;
    static int dist2;
    static int dist3;
    static int dist4;
    static int dist5;
    static int dist6;
    static int dist7;
    static int dist8;
    static int dist9;
    static int dist10;
    static int dist11;
    static int dist12;
    static int dist13;
    static int dist14;
    static int dist15;
    static int dist16;
    static int dist17;
    static int dist18;
    static int dist19;
    static int dist20;
    static int dist21;
    static int dist22;
    static int dist23;
    static int dist24;
    static int dist25;
    static int dist26;
    static int dist27;
    static int dist28;
    static int dist29;
    static int dist30;
    static int dist31;
    static int dist32;
    static int dist33;
    static int dist34;
    static int dist35;
    static int dist36;
    static int dist37;
    static int dist38;
    static int dist39;
    static int dist40;
    static int dist41;
    static int dist42;
    static int dist43;
    static int dist44;
    static int dist45;
    static int dist46;
    static int dist47;
    static int dist48;
    static int dist49;
    static int dist50;
    static int dist51;
    static int dist52;
    static int dist53;
    static int dist54;
    static int dist55;
    static int dist56;
    static int dist57;
    static int dist58;
    static int dist59;
    static int dist60;
    static int dist61;
    static int dist62;
    static int dist63;
    static int dist64;
    static int dist65;
    static int dist66;
    static int dist67;
    static int dist68;

    static int cost9;
    static int cost10;
    static int cost11;
    static int cost12;
    static int cost13;
    static int cost14;
    static int cost15;
    static int cost16;
    static int cost17;
    static int cost18;
    static int cost19;
    static int cost20;
    static int cost21;
    static int cost22;
    static int cost23;
    static int cost24;
    static int cost25;
    static int cost26;
    static int cost27;
    static int cost28;
    static int cost29;
    static int cost30;
    static int cost31;
    static int cost32;
    static int cost33;
    static int cost34;
    static int cost35;
    static int cost36;
    static int cost37;
    static int cost38;
    static int cost39;
    static int cost40;
    static int cost41;
    static int cost42;
    static int cost43;
    static int cost44;
    static int cost45;
    static int cost46;
    static int cost47;
    static int cost48;
    static int cost49;
    static int cost50;
    static int cost51;
    static int cost52;
    static int cost53;
    static int cost54;
    static int cost55;
    static int cost56;
    static int cost57;
    static int cost58;
    static int cost59;
    static int cost60;
    static int cost61;
    static int cost62;
    static int cost63;
    static int cost64;
    static int cost65;
    static int cost66;
    static int cost67;
    static int cost68;

    static MapLocation ml1;
    static MapLocation ml2;
    static MapLocation ml3;
    static MapLocation ml4;
    static MapLocation ml5;
    static MapLocation ml6;
    static MapLocation ml7;
    static MapLocation ml8;
    static MapLocation ml9;
    static MapLocation ml10;
    static MapLocation ml11;
    static MapLocation ml12;
    static MapLocation ml13;
    static MapLocation ml14;
    static MapLocation ml15;
    static MapLocation ml16;
    static MapLocation ml17;
    static MapLocation ml18;
    static MapLocation ml19;
    static MapLocation ml20;
    static MapLocation ml21;
    static MapLocation ml22;
    static MapLocation ml23;
    static MapLocation ml24;
    static MapLocation ml25;
    static MapLocation ml26;
    static MapLocation ml27;
    static MapLocation ml28;
    static MapLocation ml29;
    static MapLocation ml30;
    static MapLocation ml31;
    static MapLocation ml32;
    static MapLocation ml33;
    static MapLocation ml34;
    static MapLocation ml35;
    static MapLocation ml36;
    static MapLocation ml37;
    static MapLocation ml38;
    static MapLocation ml39;
    static MapLocation ml40;
    static MapLocation ml41;
    static MapLocation ml42;
    static MapLocation ml43;
    static MapLocation ml44;
    static MapLocation ml45;
    static MapLocation ml46;
    static MapLocation ml47;
    static MapLocation ml48;
    static MapLocation ml49;
    static MapLocation ml50;
    static MapLocation ml51;
    static MapLocation ml52;
    static MapLocation ml53;
    static MapLocation ml54;
    static MapLocation ml55;
    static MapLocation ml56;
    static MapLocation ml57;
    static MapLocation ml58;
    static MapLocation ml59;
    static MapLocation ml60;
    static MapLocation ml61;
    static MapLocation ml62;
    static MapLocation ml63;
    static MapLocation ml64;
    static MapLocation ml65;
    static MapLocation ml66;
    static MapLocation ml67;
    static MapLocation ml68;


    static Direction dir1 = Direction.SOUTH;
    static Direction dir2 = Direction.SOUTHEAST;
    static Direction dir3 = Direction.EAST;
    static Direction dir4 = Direction.NORTHEAST;
    static Direction dir5 = Direction.NORTH;
    static Direction dir6 = Direction.NORTHWEST;
    static Direction dir7 = Direction.WEST;
    static Direction dir8 = Direction.SOUTHWEST;

    public BFPathing20(RobotController rc) throws GameActionException {
        super(rc);
    }

    Direction bfPathToTarget(MapLocation target) throws GameActionException {

        MapLocation ml0 = rc.getLocation();

        if(ml0.equals(target)) return Direction.CENTER;


        dist1 = 10000;
        dist2 = 10000;
        dist3 = 10000;
        dist4 = 10000;
        dist5 = 10000;
        dist6 = 10000;
        dist7 = 10000;
        dist8 = 10000;
        dist9 = 10000;
        dist10 = 10000;
        dist11 = 10000;
        dist12 = 10000;
        dist13 = 10000;
        dist14 = 10000;
        dist15 = 10000;
        dist16 = 10000;
        dist17 = 10000;
        dist18 = 10000;
        dist19 = 10000;
        dist20 = 10000;
        dist21 = 10000;
        dist22 = 10000;
        dist23 = 10000;
        dist24 = 10000;
        dist25 = 10000;
        dist26 = 10000;
        dist27 = 10000;
        dist28 = 10000;
        dist29 = 10000;
        dist30 = 10000;
        dist31 = 10000;
        dist32 = 10000;
        dist33 = 10000;
        dist34 = 10000;
        dist35 = 10000;
        dist36 = 10000;
        dist37 = 10000;
        dist38 = 10000;
        dist39 = 10000;
        dist40 = 10000;
        dist41 = 10000;
        dist42 = 10000;
        dist43 = 10000;
        dist44 = 10000;
        dist45 = 10000;
        dist46 = 10000;
        dist47 = 10000;
        dist48 = 10000;
        dist49 = 10000;
        dist50 = 10000;
        dist51 = 10000;
        dist52 = 10000;
        dist53 = 10000;
        dist54 = 10000;
        dist55 = 10000;
        dist56 = 10000;
        dist57 = 10000;
        dist58 = 10000;
        dist59 = 10000;
        dist60 = 10000;
        dist61 = 10000;
        dist62 = 10000;
        dist63 = 10000;
        dist64 = 10000;
        dist65 = 10000;
        dist66 = 10000;
        dist67 = 10000;
        dist68 = 10000;

        ml1 = ml0.add(Direction.SOUTH);
        ml2 = ml1.add(Direction.EAST);
        ml3 = ml2.add(Direction.NORTH);
        ml4 = ml3.add(Direction.NORTH);
        ml5 = ml4.add(Direction.WEST);
        ml6 = ml5.add(Direction.WEST);
        ml7 = ml6.add(Direction.SOUTH);
        ml8 = ml7.add(Direction.SOUTH);
        ml9 = ml8.add(Direction.SOUTH);
        ml10 = ml9.add(Direction.EAST);
        ml11 = ml10.add(Direction.EAST);
        ml12 = ml11.add(Direction.EAST);
        ml13 = ml12.add(Direction.NORTH);
        ml14 = ml13.add(Direction.NORTH);
        ml15 = ml14.add(Direction.NORTH);
        ml16 = ml15.add(Direction.NORTH);
        ml17 = ml16.add(Direction.WEST);
        ml18 = ml17.add(Direction.WEST);
        ml19 = ml18.add(Direction.WEST);
        ml20 = ml19.add(Direction.WEST);
        ml21 = ml20.add(Direction.SOUTH);
        ml22 = ml21.add(Direction.SOUTH);
        ml23 = ml22.add(Direction.SOUTH);
        ml24 = ml23.add(Direction.SOUTH);
        ml25 = ml24.add(Direction.SOUTH);
        ml26 = ml25.add(Direction.EAST);
        ml27 = ml26.add(Direction.EAST);
        ml28 = ml27.add(Direction.EAST);
        ml29 = ml28.add(Direction.EAST);
        ml30 = ml29.add(Direction.NORTHEAST);
        ml31 = ml30.add(Direction.NORTH);
        ml32 = ml31.add(Direction.NORTH);
        ml33 = ml32.add(Direction.NORTH);
        ml34 = ml33.add(Direction.NORTH);
        ml35 = ml34.add(Direction.NORTHWEST);
        ml36 = ml35.add(Direction.WEST);
        ml37 = ml36.add(Direction.WEST);
        ml38 = ml37.add(Direction.WEST);
        ml39 = ml38.add(Direction.WEST);
        ml40 = ml39.add(Direction.SOUTHWEST);
        ml41 = ml40.add(Direction.SOUTH);
        ml42 = ml41.add(Direction.SOUTH);
        ml43 = ml42.add(Direction.SOUTH);
        ml44 = ml43.add(Direction.SOUTH);
        ml45 = ml44.add(Direction.SOUTH);
        ml46 = ml45.add(Direction.SOUTHEAST);
        ml47 = ml46.add(Direction.EAST);
        ml48 = ml47.add(Direction.EAST);
        ml49 = ml48.add(Direction.EAST);
        ml50 = ml49.add(Direction.EAST);
        ml51 = ml50.add(Direction.NORTHEAST);
        ml52 = ml51.add(Direction.NORTHEAST);
        ml53 = ml52.add(Direction.NORTH);
        ml54 = ml53.add(Direction.NORTH);
        ml55 = ml54.add(Direction.NORTH);
        ml56 = ml55.add(Direction.NORTH);
        ml57 = ml56.add(Direction.NORTHWEST);
        ml58 = ml57.add(Direction.NORTHWEST);
        ml59 = ml58.add(Direction.WEST);
        ml60 = ml59.add(Direction.WEST);
        ml61 = ml60.add(Direction.WEST);
        ml62 = ml61.add(Direction.WEST);
        ml63 = ml62.add(Direction.SOUTHWEST);
        ml64 = ml63.add(Direction.SOUTHWEST);
        ml65 = ml64.add(Direction.SOUTH);
        ml66 = ml65.add(Direction.SOUTH);
        ml67 = ml66.add(Direction.SOUTH);
        ml68 = ml67.add(Direction.SOUTH);


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

            if(cost12 + dist2 < dist12){
                dist12 = cost12 + dist2;
                dir12 = dir2;
            }
        }

        if(rc.onTheMap(ml13)){
            cost13 = rc.senseRubble(ml13) + 10;

            if(cost13 + dist2 < dist13){
                dist13 = cost13 + dist2;
                dir13 = dir2;
            }

            if(cost13 + dist3 < dist13){
                dist13 = cost13 + dist3;
                dir13 = dir3;
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
        }

        if(rc.onTheMap(ml16)){
            cost16 = rc.senseRubble(ml16) + 10;

            if(cost16 + dist4 < dist16){
                dist16 = cost16 + dist4;
                dir16 = dir4;
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
        }

        if(rc.onTheMap(ml20)){
            cost20 = rc.senseRubble(ml20) + 10;

            if(cost20 + dist6 < dist20){
                dist20 = cost20 + dist6;
                dir20 = dir6;
            }
        }

        if(rc.onTheMap(ml21)){
            cost21 = rc.senseRubble(ml21) + 10;

            if(cost21 + dist7 < dist21){
                dist21 = cost21 + dist7;
                dir21 = dir7;
            }

            if(cost21 + dist6 < dist21){
                dist21 = cost21 + dist6;
                dir21 = dir6;
            }
        }

        if(rc.onTheMap(ml22)){
            cost22 = rc.senseRubble(ml22) + 10;

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

            if(cost24 + dist8 < dist24){
                dist24 = cost24 + dist8;
                dir24 = dir8;
            }
        }

        if(dist9 < 10000){

            if(cost9 + dist10 < dist9){
                dist9 = cost9 + dist10;
                dir9 = dir10;
            }
        }

        if(dist11 < 10000){

            if(cost11 + dist10 < dist11){
                dist11 = cost11 + dist10;
                dir11 = dir10;
            }
        }

        if(dist12 < 10000){

            if(cost12 + dist11 < dist12){
                dist12 = cost12 + dist11;
                dir12 = dir11;
            }

            if(cost12 + dist13 < dist12){
                dist12 = cost12 + dist13;
                dir12 = dir13;
            }
        }

        if(dist13 < 10000){

            if(cost13 + dist14 < dist13){
                dist13 = cost13 + dist14;
                dir13 = dir14;
            }
        }

        if(dist15 < 10000){

            if(cost15 + dist14 < dist15){
                dist15 = cost15 + dist14;
                dir15 = dir14;
            }
        }

        if(dist16 < 10000){

            if(cost16 + dist17 < dist16){
                dist16 = cost16 + dist17;
                dir16 = dir17;
            }

            if(cost16 + dist15 < dist16){
                dist16 = cost16 + dist15;
                dir16 = dir15;
            }
        }

        if(dist17 < 10000){

            if(cost17 + dist18 < dist17){
                dist17 = cost17 + dist18;
                dir17 = dir18;
            }
        }

        if(dist19 < 10000){

            if(cost19 + dist18 < dist19){
                dist19 = cost19 + dist18;
                dir19 = dir18;
            }
        }

        if(dist20 < 10000){

            if(cost20 + dist21 < dist20){
                dist20 = cost20 + dist21;
                dir20 = dir21;
            }

            if(cost20 + dist19 < dist20){
                dist20 = cost20 + dist19;
                dir20 = dir19;
            }
        }

        if(dist21 < 10000){

            if(cost21 + dist22 < dist21){
                dist21 = cost21 + dist22;
                dir21 = dir22;
            }
        }

        if(dist23 < 10000){

            if(cost23 + dist22 < dist23){
                dist23 = cost23 + dist22;
                dir23 = dir22;
            }
        }

        if(dist24 < 10000){

            if(cost24 + dist23 < dist24){
                dist24 = cost24 + dist23;
                dir24 = dir23;
            }

            if(cost24 + dist9 < dist24){
                dist24 = cost24 + dist9;
                dir24 = dir9;
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
        }

        if(rc.onTheMap(ml41)){
            cost41 = rc.senseRubble(ml41) + 10;

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

            if(cost45 + dist24 < dist45){
                dist45 = cost45 + dist24;
                dir45 = dir24;
            }
        }

        if(dist25 < 10000){

            if(cost25 + dist26 < dist25){
                dist25 = cost25 + dist26;
                dir25 = dir26;
            }
        }

        if(dist26 < 10000){

            if(cost26 + dist27 < dist26){
                dist26 = cost26 + dist27;
                dir26 = dir27;
            }
        }

        if(dist28 < 10000){

            if(cost28 + dist27 < dist28){
                dist28 = cost28 + dist27;
                dir28 = dir27;
            }
        }

        if(dist29 < 10000){

            if(cost29 + dist28 < dist29){
                dist29 = cost29 + dist28;
                dir29 = dir28;
            }
        }

        if(dist30 < 10000){

            if(cost30 + dist31 < dist30){
                dist30 = cost30 + dist31;
                dir30 = dir31;
            }
        }

        if(dist31 < 10000){

            if(cost31 + dist32 < dist31){
                dist31 = cost31 + dist32;
                dir31 = dir32;
            }
        }

        if(dist33 < 10000){

            if(cost33 + dist32 < dist33){
                dist33 = cost33 + dist32;
                dir33 = dir32;
            }
        }

        if(dist34 < 10000){

            if(cost34 + dist33 < dist34){
                dist34 = cost34 + dist33;
                dir34 = dir33;
            }
        }

        if(dist35 < 10000){

            if(cost35 + dist36 < dist35){
                dist35 = cost35 + dist36;
                dir35 = dir36;
            }
        }

        if(dist36 < 10000){

            if(cost36 + dist37 < dist36){
                dist36 = cost36 + dist37;
                dir36 = dir37;
            }
        }

        if(dist38 < 10000){

            if(cost38 + dist37 < dist38){
                dist38 = cost38 + dist37;
                dir38 = dir37;
            }
        }

        if(dist39 < 10000){

            if(cost39 + dist38 < dist39){
                dist39 = cost39 + dist38;
                dir39 = dir38;
            }
        }

        if(dist40 < 10000){

            if(cost40 + dist41 < dist40){
                dist40 = cost40 + dist41;
                dir40 = dir41;
            }
        }

        if(dist41 < 10000){

            if(cost41 + dist42 < dist41){
                dist41 = cost41 + dist42;
                dir41 = dir42;
            }
        }

        if(dist43 < 10000){

            if(cost43 + dist42 < dist43){
                dist43 = cost43 + dist42;
                dir43 = dir42;
            }
        }

        if(dist44 < 10000){

            if(cost44 + dist43 < dist44){
                dist44 = cost44 + dist43;
                dir44 = dir43;
            }
        }

        if(dist45 < 10000){

            if(cost45 + dist44 < dist45){
                dist45 = cost45 + dist44;
                dir45 = dir44;
            }

            if(cost45 + dist25 < dist45){
                dist45 = cost45 + dist25;
                dir45 = dir25;
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
        }

        if(rc.onTheMap(ml65)){
            cost65 = rc.senseRubble(ml65) + 10;

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
        //rc.setIndicatorString(time1+" ");


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

        Direction ans = Direction.CENTER;
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

        int time8 = Clock.getBytecodesLeft();

        //rc.setIndicatorString(time1+" "+time8);

        return ans;

    }
}
