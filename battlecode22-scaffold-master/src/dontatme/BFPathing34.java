package dontatme;

import battlecode.common.*;

public class BFPathing34 extends Pathfinder {
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
    static Direction dir69;
    static Direction dir70;
    static Direction dir71;
    static Direction dir72;
    static Direction dir73;
    static Direction dir74;
    static Direction dir75;
    static Direction dir76;
    static Direction dir77;
    static Direction dir78;
    static Direction dir79;
    static Direction dir80;
    static Direction dir81;
    static Direction dir82;
    static Direction dir83;
    static Direction dir84;
    static Direction dir85;
    static Direction dir86;
    static Direction dir87;
    static Direction dir88;
    static Direction dir89;
    static Direction dir90;
    static Direction dir91;
    static Direction dir92;
    static Direction dir93;
    static Direction dir94;
    static Direction dir95;
    static Direction dir96;
    static Direction dir97;
    static Direction dir98;
    static Direction dir99;
    static Direction dir100;
    static Direction dir101;
    static Direction dir102;
    static Direction dir103;
    static Direction dir104;
    static Direction dir105;
    static Direction dir106;
    static Direction dir107;
    static Direction dir108;

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
    static int dist69;
    static int dist70;
    static int dist71;
    static int dist72;
    static int dist73;
    static int dist74;
    static int dist75;
    static int dist76;
    static int dist77;
    static int dist78;
    static int dist79;
    static int dist80;
    static int dist81;
    static int dist82;
    static int dist83;
    static int dist84;
    static int dist85;
    static int dist86;
    static int dist87;
    static int dist88;
    static int dist89;
    static int dist90;
    static int dist91;
    static int dist92;
    static int dist93;
    static int dist94;
    static int dist95;
    static int dist96;
    static int dist97;
    static int dist98;
    static int dist99;
    static int dist100;
    static int dist101;
    static int dist102;
    static int dist103;
    static int dist104;
    static int dist105;
    static int dist106;
    static int dist107;
    static int dist108;

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
    static int cost69;
    static int cost70;
    static int cost71;
    static int cost72;
    static int cost73;
    static int cost74;
    static int cost75;
    static int cost76;
    static int cost77;
    static int cost78;
    static int cost79;
    static int cost80;
    static int cost81;
    static int cost82;
    static int cost83;
    static int cost84;
    static int cost85;
    static int cost86;
    static int cost87;
    static int cost88;
    static int cost89;
    static int cost90;
    static int cost91;
    static int cost92;
    static int cost93;
    static int cost94;
    static int cost95;
    static int cost96;
    static int cost97;
    static int cost98;
    static int cost99;
    static int cost100;
    static int cost101;
    static int cost102;
    static int cost103;
    static int cost104;
    static int cost105;
    static int cost106;
    static int cost107;
    static int cost108;

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
    static MapLocation ml69;
    static MapLocation ml70;
    static MapLocation ml71;
    static MapLocation ml72;
    static MapLocation ml73;
    static MapLocation ml74;
    static MapLocation ml75;
    static MapLocation ml76;
    static MapLocation ml77;
    static MapLocation ml78;
    static MapLocation ml79;
    static MapLocation ml80;
    static MapLocation ml81;
    static MapLocation ml82;
    static MapLocation ml83;
    static MapLocation ml84;
    static MapLocation ml85;
    static MapLocation ml86;
    static MapLocation ml87;
    static MapLocation ml88;
    static MapLocation ml89;
    static MapLocation ml90;
    static MapLocation ml91;
    static MapLocation ml92;
    static MapLocation ml93;
    static MapLocation ml94;
    static MapLocation ml95;
    static MapLocation ml96;
    static MapLocation ml97;
    static MapLocation ml98;
    static MapLocation ml99;
    static MapLocation ml100;
    static MapLocation ml101;
    static MapLocation ml102;
    static MapLocation ml103;
    static MapLocation ml104;
    static MapLocation ml105;
    static MapLocation ml106;
    static MapLocation ml107;
    static MapLocation ml108;



    static Direction dir1 = Direction.SOUTH;
    static Direction dir2 = Direction.SOUTHEAST;
    static Direction dir3 = Direction.EAST;
    static Direction dir4 = Direction.NORTHEAST;
    static Direction dir5 = Direction.NORTH;
    static Direction dir6 = Direction.NORTHWEST;
    static Direction dir7 = Direction.WEST;
    static Direction dir8 = Direction.SOUTHWEST;

    public BFPathing34(RobotController rc) throws GameActionException {
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
        dist69 = 10000;
        dist70 = 10000;
        dist71 = 10000;
        dist72 = 10000;
        dist73 = 10000;
        dist74 = 10000;
        dist75 = 10000;
        dist76 = 10000;
        dist77 = 10000;
        dist78 = 10000;
        dist79 = 10000;
        dist80 = 10000;
        dist81 = 10000;
        dist82 = 10000;
        dist83 = 10000;
        dist84 = 10000;
        dist85 = 10000;
        dist86 = 10000;
        dist87 = 10000;
        dist88 = 10000;
        dist89 = 10000;
        dist90 = 10000;
        dist91 = 10000;
        dist92 = 10000;
        dist93 = 10000;
        dist94 = 10000;
        dist95 = 10000;
        dist96 = 10000;
        dist97 = 10000;
        dist98 = 10000;
        dist99 = 10000;
        dist100 = 10000;
        dist101 = 10000;
        dist102 = 10000;
        dist103 = 10000;
        dist104 = 10000;
        dist105 = 10000;
        dist106 = 10000;
        dist107 = 10000;
        dist108 = 10000;
        cost9 = 0;
        cost10 = 0;
        cost11 = 0;
        cost12 = 0;
        cost13 = 0;
        cost14 = 0;
        cost15 = 0;
        cost16 = 0;
        cost17 = 0;
        cost18 = 0;
        cost19 = 0;
        cost20 = 0;
        cost21 = 0;
        cost22 = 0;
        cost23 = 0;
        cost24 = 0;
        cost25 = 0;
        cost26 = 0;
        cost27 = 0;
        cost28 = 0;
        cost29 = 0;
        cost30 = 0;
        cost31 = 0;
        cost32 = 0;
        cost33 = 0;
        cost34 = 0;
        cost35 = 0;
        cost36 = 0;
        cost37 = 0;
        cost38 = 0;
        cost39 = 0;
        cost40 = 0;
        cost41 = 0;
        cost42 = 0;
        cost43 = 0;
        cost44 = 0;
        cost45 = 0;
        cost46 = 0;
        cost47 = 0;
        cost48 = 0;
        cost49 = 0;
        cost50 = 0;
        cost51 = 0;
        cost52 = 0;
        cost53 = 0;
        cost54 = 0;
        cost55 = 0;
        cost56 = 0;
        cost57 = 0;
        cost58 = 0;
        cost59 = 0;
        cost60 = 0;
        cost61 = 0;
        cost62 = 0;
        cost63 = 0;
        cost64 = 0;
        cost65 = 0;
        cost66 = 0;
        cost67 = 0;
        cost68 = 0;
        cost69 = 0;
        cost70 = 0;
        cost71 = 0;
        cost72 = 0;
        cost73 = 0;
        cost74 = 0;
        cost75 = 0;
        cost76 = 0;
        cost77 = 0;
        cost78 = 0;
        cost79 = 0;
        cost80 = 0;
        cost81 = 0;
        cost82 = 0;
        cost83 = 0;
        cost84 = 0;
        cost85 = 0;
        cost86 = 0;
        cost87 = 0;
        cost88 = 0;
        cost89 = 0;
        cost90 = 0;
        cost91 = 0;
        cost92 = 0;
        cost93 = 0;
        cost94 = 0;
        cost95 = 0;
        cost96 = 0;
        cost97 = 0;
        cost98 = 0;
        cost99 = 0;
        cost100 = 0;
        cost101 = 0;
        cost102 = 0;
        cost103 = 0;
        cost104 = 0;
        cost105 = 0;
        cost106 = 0;
        cost107 = 0;
        cost108 = 0;

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
        ml30 = ml29.add(Direction.EAST);
        ml31 = ml30.add(Direction.NORTH);
        ml32 = ml31.add(Direction.NORTH);
        ml33 = ml32.add(Direction.NORTH);
        ml34 = ml33.add(Direction.NORTH);
        ml35 = ml34.add(Direction.NORTH);
        ml36 = ml35.add(Direction.NORTH);
        ml37 = ml36.add(Direction.WEST);
        ml38 = ml37.add(Direction.WEST);
        ml39 = ml38.add(Direction.WEST);
        ml40 = ml39.add(Direction.WEST);
        ml41 = ml40.add(Direction.WEST);
        ml42 = ml41.add(Direction.WEST);
        ml43 = ml42.add(Direction.SOUTH);
        ml44 = ml43.add(Direction.SOUTH);
        ml45 = ml44.add(Direction.SOUTH);
        ml46 = ml45.add(Direction.SOUTH);
        ml47 = ml46.add(Direction.SOUTH);
        ml48 = ml47.add(Direction.SOUTH);
        ml49 = ml48.add(Direction.SOUTH);
        ml50 = ml49.add(Direction.EAST);
        ml51 = ml50.add(Direction.EAST);
        ml52 = ml51.add(Direction.EAST);
        ml53 = ml52.add(Direction.EAST);
        ml54 = ml53.add(Direction.EAST);
        ml55 = ml54.add(Direction.EAST);
        ml56 = ml55.add(Direction.NORTHEAST);
        ml57 = ml56.add(Direction.NORTH);
        ml58 = ml57.add(Direction.NORTH);
        ml59 = ml58.add(Direction.NORTH);
        ml60 = ml59.add(Direction.NORTH);
        ml61 = ml60.add(Direction.NORTH);
        ml62 = ml61.add(Direction.NORTH);
        ml63 = ml62.add(Direction.NORTHWEST);
        ml64 = ml63.add(Direction.WEST);
        ml65 = ml64.add(Direction.WEST);
        ml66 = ml65.add(Direction.WEST);
        ml67 = ml66.add(Direction.WEST);
        ml68 = ml67.add(Direction.WEST);
        ml69 = ml68.add(Direction.WEST);
        ml70 = ml69.add(Direction.SOUTHWEST);
        ml71 = ml70.add(Direction.SOUTH);
        ml72 = ml71.add(Direction.SOUTH);
        ml73 = ml72.add(Direction.SOUTH);
        ml74 = ml73.add(Direction.SOUTH);
        ml75 = ml74.add(Direction.SOUTH);
        ml76 = ml75.add(Direction.SOUTH);
        ml77 = ml76.add(Direction.SOUTH);
        ml78 = ml77.add(Direction.SOUTHEAST);
        ml79 = ml78.add(Direction.EAST);
        ml80 = ml79.add(Direction.EAST);
        ml81 = ml80.add(Direction.EAST);
        ml82 = ml81.add(Direction.EAST);
        ml83 = ml82.add(Direction.EAST);
        ml84 = ml83.add(Direction.EAST);
        ml85 = ml84.add(Direction.NORTHEAST);
        ml86 = ml85.add(Direction.NORTHEAST);
        ml87 = ml86.add(Direction.NORTH);
        ml88 = ml87.add(Direction.NORTH);
        ml89 = ml88.add(Direction.NORTH);
        ml90 = ml89.add(Direction.NORTH);
        ml91 = ml90.add(Direction.NORTH);
        ml92 = ml91.add(Direction.NORTH);
        ml93 = ml92.add(Direction.NORTHWEST);
        ml94 = ml93.add(Direction.NORTHWEST);
        ml95 = ml94.add(Direction.WEST);
        ml96 = ml95.add(Direction.WEST);
        ml97 = ml96.add(Direction.WEST);
        ml98 = ml97.add(Direction.WEST);
        ml99 = ml98.add(Direction.WEST);
        ml100 = ml99.add(Direction.WEST);
        ml101 = ml100.add(Direction.SOUTHWEST);
        ml102 = ml101.add(Direction.SOUTHWEST);
        ml103 = ml102.add(Direction.SOUTH);
        ml104 = ml103.add(Direction.SOUTH);
        ml105 = ml104.add(Direction.SOUTH);
        ml106 = ml105.add(Direction.SOUTH);
        ml107 = ml106.add(Direction.SOUTH);
        ml108 = ml107.add(Direction.SOUTH);



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

        if(cost9 != 0){

            if(cost9 + dist10 < dist9){
                dist9 = cost9 + dist10;
                dir9 = dir10;
            }
        }

        if(cost11 != 0){

            if(cost11 + dist10 < dist11){
                dist11 = cost11 + dist10;
                dir11 = dir10;
            }
        }

        if(cost12 != 0){

            if(cost12 + dist11 < dist12){
                dist12 = cost12 + dist11;
                dir12 = dir11;
            }

            if(cost12 + dist13 < dist12){
                dist12 = cost12 + dist13;
                dir12 = dir13;
            }
        }

        if(cost13 != 0){

            if(cost13 + dist14 < dist13){
                dist13 = cost13 + dist14;
                dir13 = dir14;
            }
        }

        if(cost15 != 0){

            if(cost15 + dist14 < dist15){
                dist15 = cost15 + dist14;
                dir15 = dir14;
            }
        }

        if(cost16 != 0){

            if(cost16 + dist17 < dist16){
                dist16 = cost16 + dist17;
                dir16 = dir17;
            }

            if(cost16 + dist15 < dist16){
                dist16 = cost16 + dist15;
                dir16 = dir15;
            }
        }

        if(cost17 != 0){

            if(cost17 + dist18 < dist17){
                dist17 = cost17 + dist18;
                dir17 = dir18;
            }
        }

        if(cost19 != 0){

            if(cost19 + dist18 < dist19){
                dist19 = cost19 + dist18;
                dir19 = dir18;
            }
        }

        if(cost20 != 0){

            if(cost20 + dist21 < dist20){
                dist20 = cost20 + dist21;
                dir20 = dir21;
            }

            if(cost20 + dist19 < dist20){
                dist20 = cost20 + dist19;
                dir20 = dir19;
            }
        }

        if(cost21 != 0){

            if(cost21 + dist22 < dist21){
                dist21 = cost21 + dist22;
                dir21 = dir22;
            }
        }

        if(cost23 != 0){

            if(cost23 + dist22 < dist23){
                dist23 = cost23 + dist22;
                dir23 = dir22;
            }
        }

        if(cost24 != 0){

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
        }

        if(rc.onTheMap(ml32)){
            cost32 = rc.senseRubble(ml32) + 10;

            if(cost32 + dist12 < dist32){
                dist32 = cost32 + dist12;
                dir32 = dir12;
            }

            if(cost32 + dist13 < dist32){
                dist32 = cost32 + dist13;
                dir32 = dir13;
            }

            if(cost32 + dist14 < dist32){
                dist32 = cost32 + dist14;
                dir32 = dir14;
            }
        }

        if(rc.onTheMap(ml33)){
            cost33 = rc.senseRubble(ml33) + 10;

            if(cost33 + dist13 < dist33){
                dist33 = cost33 + dist13;
                dir33 = dir13;
            }

            if(cost33 + dist14 < dist33){
                dist33 = cost33 + dist14;
                dir33 = dir14;
            }

            if(cost33 + dist15 < dist33){
                dist33 = cost33 + dist15;
                dir33 = dir15;
            }
        }

        if(rc.onTheMap(ml34)){
            cost34 = rc.senseRubble(ml34) + 10;

            if(cost34 + dist14 < dist34){
                dist34 = cost34 + dist14;
                dir34 = dir14;
            }

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

            if(cost35 + dist15 < dist35){
                dist35 = cost35 + dist15;
                dir35 = dir15;
            }

            if(cost35 + dist16 < dist35){
                dist35 = cost35 + dist16;
                dir35 = dir16;
            }
        }

        if(rc.onTheMap(ml36)){
            cost36 = rc.senseRubble(ml36) + 10;

            if(cost36 + dist16 < dist36){
                dist36 = cost36 + dist16;
                dir36 = dir16;
            }
        }

        if(rc.onTheMap(ml37)){
            cost37 = rc.senseRubble(ml37) + 10;

            if(cost37 + dist17 < dist37){
                dist37 = cost37 + dist17;
                dir37 = dir17;
            }

            if(cost37 + dist16 < dist37){
                dist37 = cost37 + dist16;
                dir37 = dir16;
            }
        }

        if(rc.onTheMap(ml38)){
            cost38 = rc.senseRubble(ml38) + 10;

            if(cost38 + dist18 < dist38){
                dist38 = cost38 + dist18;
                dir38 = dir18;
            }

            if(cost38 + dist17 < dist38){
                dist38 = cost38 + dist17;
                dir38 = dir17;
            }

            if(cost38 + dist16 < dist38){
                dist38 = cost38 + dist16;
                dir38 = dir16;
            }
        }

        if(rc.onTheMap(ml39)){
            cost39 = rc.senseRubble(ml39) + 10;

            if(cost39 + dist19 < dist39){
                dist39 = cost39 + dist19;
                dir39 = dir19;
            }

            if(cost39 + dist18 < dist39){
                dist39 = cost39 + dist18;
                dir39 = dir18;
            }

            if(cost39 + dist17 < dist39){
                dist39 = cost39 + dist17;
                dir39 = dir17;
            }
        }

        if(rc.onTheMap(ml40)){
            cost40 = rc.senseRubble(ml40) + 10;

            if(cost40 + dist20 < dist40){
                dist40 = cost40 + dist20;
                dir40 = dir20;
            }

            if(cost40 + dist19 < dist40){
                dist40 = cost40 + dist19;
                dir40 = dir19;
            }

            if(cost40 + dist18 < dist40){
                dist40 = cost40 + dist18;
                dir40 = dir18;
            }
        }

        if(rc.onTheMap(ml41)){
            cost41 = rc.senseRubble(ml41) + 10;

            if(cost41 + dist20 < dist41){
                dist41 = cost41 + dist20;
                dir41 = dir20;
            }

            if(cost41 + dist19 < dist41){
                dist41 = cost41 + dist19;
                dir41 = dir19;
            }
        }

        if(rc.onTheMap(ml42)){
            cost42 = rc.senseRubble(ml42) + 10;

            if(cost42 + dist20 < dist42){
                dist42 = cost42 + dist20;
                dir42 = dir20;
            }
        }

        if(rc.onTheMap(ml43)){
            cost43 = rc.senseRubble(ml43) + 10;

            if(cost43 + dist21 < dist43){
                dist43 = cost43 + dist21;
                dir43 = dir21;
            }

            if(cost43 + dist20 < dist43){
                dist43 = cost43 + dist20;
                dir43 = dir20;
            }
        }

        if(rc.onTheMap(ml44)){
            cost44 = rc.senseRubble(ml44) + 10;

            if(cost44 + dist22 < dist44){
                dist44 = cost44 + dist22;
                dir44 = dir22;
            }

            if(cost44 + dist21 < dist44){
                dist44 = cost44 + dist21;
                dir44 = dir21;
            }

            if(cost44 + dist20 < dist44){
                dist44 = cost44 + dist20;
                dir44 = dir20;
            }
        }

        if(rc.onTheMap(ml45)){
            cost45 = rc.senseRubble(ml45) + 10;

            if(cost45 + dist23 < dist45){
                dist45 = cost45 + dist23;
                dir45 = dir23;
            }

            if(cost45 + dist22 < dist45){
                dist45 = cost45 + dist22;
                dir45 = dir22;
            }

            if(cost45 + dist21 < dist45){
                dist45 = cost45 + dist21;
                dir45 = dir21;
            }
        }

        if(rc.onTheMap(ml46)){
            cost46 = rc.senseRubble(ml46) + 10;

            if(cost46 + dist24 < dist46){
                dist46 = cost46 + dist24;
                dir46 = dir24;
            }

            if(cost46 + dist23 < dist46){
                dist46 = cost46 + dist23;
                dir46 = dir23;
            }

            if(cost46 + dist22 < dist46){
                dist46 = cost46 + dist22;
                dir46 = dir22;
            }
        }

        if(rc.onTheMap(ml47)){
            cost47 = rc.senseRubble(ml47) + 10;

            if(cost47 + dist24 < dist47){
                dist47 = cost47 + dist24;
                dir47 = dir24;
            }

            if(cost47 + dist23 < dist47){
                dist47 = cost47 + dist23;
                dir47 = dir23;
            }
        }

        if(rc.onTheMap(ml48)){
            cost48 = rc.senseRubble(ml48) + 10;

            if(cost48 + dist24 < dist48){
                dist48 = cost48 + dist24;
                dir48 = dir24;
            }
        }

        if(cost25 != 0){

            if(cost25 + dist26 < dist25){
                dist25 = cost25 + dist26;
                dir25 = dir26;
            }
        }

        if(cost26 != 0){

            if(cost26 + dist27 < dist26){
                dist26 = cost26 + dist27;
                dir26 = dir27;
            }
        }

        if(cost28 != 0){

            if(cost28 + dist27 < dist28){
                dist28 = cost28 + dist27;
                dir28 = dir27;
            }
        }

        if(cost29 != 0){

            if(cost29 + dist28 < dist29){
                dist29 = cost29 + dist28;
                dir29 = dir28;
            }
        }

        if(cost30 != 0){

            if(cost30 + dist29 < dist30){
                dist30 = cost30 + dist29;
                dir30 = dir29;
            }

            if(cost30 + dist31 < dist30){
                dist30 = cost30 + dist31;
                dir30 = dir31;
            }
        }

        if(cost31 != 0){

            if(cost31 + dist32 < dist31){
                dist31 = cost31 + dist32;
                dir31 = dir32;
            }
        }

        if(cost32 != 0){

            if(cost32 + dist33 < dist32){
                dist32 = cost32 + dist33;
                dir32 = dir33;
            }
        }

        if(cost34 != 0){

            if(cost34 + dist33 < dist34){
                dist34 = cost34 + dist33;
                dir34 = dir33;
            }
        }

        if(cost35 != 0){

            if(cost35 + dist34 < dist35){
                dist35 = cost35 + dist34;
                dir35 = dir34;
            }
        }

        if(cost36 != 0){

            if(cost36 + dist37 < dist36){
                dist36 = cost36 + dist37;
                dir36 = dir37;
            }

            if(cost36 + dist35 < dist36){
                dist36 = cost36 + dist35;
                dir36 = dir35;
            }
        }

        if(cost37 != 0){

            if(cost37 + dist38 < dist37){
                dist37 = cost37 + dist38;
                dir37 = dir38;
            }
        }

        if(cost38 != 0){

            if(cost38 + dist39 < dist38){
                dist38 = cost38 + dist39;
                dir38 = dir39;
            }
        }

        if(cost40 != 0){

            if(cost40 + dist39 < dist40){
                dist40 = cost40 + dist39;
                dir40 = dir39;
            }
        }

        if(cost41 != 0){

            if(cost41 + dist40 < dist41){
                dist41 = cost41 + dist40;
                dir41 = dir40;
            }
        }

        if(cost42 != 0){

            if(cost42 + dist43 < dist42){
                dist42 = cost42 + dist43;
                dir42 = dir43;
            }

            if(cost42 + dist41 < dist42){
                dist42 = cost42 + dist41;
                dir42 = dir41;
            }
        }

        if(cost43 != 0){

            if(cost43 + dist44 < dist43){
                dist43 = cost43 + dist44;
                dir43 = dir44;
            }
        }

        if(cost44 != 0){

            if(cost44 + dist45 < dist44){
                dist44 = cost44 + dist45;
                dir44 = dir45;
            }
        }

        if(cost46 != 0){

            if(cost46 + dist45 < dist46){
                dist46 = cost46 + dist45;
                dir46 = dir45;
            }
        }

        if(cost47 != 0){

            if(cost47 + dist46 < dist47){
                dist47 = cost47 + dist46;
                dir47 = dir46;
            }
        }

        if(cost48 != 0){

            if(cost48 + dist47 < dist48){
                dist48 = cost48 + dist47;
                dir48 = dir47;
            }

            if(cost48 + dist25 < dist48){
                dist48 = cost48 + dist25;
                dir48 = dir25;
            }
        }

        if(rc.onTheMap(ml49)){
            cost49 = rc.senseRubble(ml49) + 10;

            if(cost49 + dist48 < dist49){
                dist49 = cost49 + dist48;
                dir49 = dir48;
            }

            if(cost49 + dist25 < dist49){
                dist49 = cost49 + dist25;
                dir49 = dir25;
            }
        }

        if(rc.onTheMap(ml50)){
            cost50 = rc.senseRubble(ml50) + 10;

            if(cost50 + dist48 < dist50){
                dist50 = cost50 + dist48;
                dir50 = dir48;
            }

            if(cost50 + dist25 < dist50){
                dist50 = cost50 + dist25;
                dir50 = dir25;
            }

            if(cost50 + dist26 < dist50){
                dist50 = cost50 + dist26;
                dir50 = dir26;
            }
        }

        if(rc.onTheMap(ml51)){
            cost51 = rc.senseRubble(ml51) + 10;

            if(cost51 + dist25 < dist51){
                dist51 = cost51 + dist25;
                dir51 = dir25;
            }

            if(cost51 + dist26 < dist51){
                dist51 = cost51 + dist26;
                dir51 = dir26;
            }

            if(cost51 + dist27 < dist51){
                dist51 = cost51 + dist27;
                dir51 = dir27;
            }
        }

        if(rc.onTheMap(ml52)){
            cost52 = rc.senseRubble(ml52) + 10;

            if(cost52 + dist26 < dist52){
                dist52 = cost52 + dist26;
                dir52 = dir26;
            }

            if(cost52 + dist27 < dist52){
                dist52 = cost52 + dist27;
                dir52 = dir27;
            }

            if(cost52 + dist28 < dist52){
                dist52 = cost52 + dist28;
                dir52 = dir28;
            }
        }

        if(rc.onTheMap(ml53)){
            cost53 = rc.senseRubble(ml53) + 10;

            if(cost53 + dist27 < dist53){
                dist53 = cost53 + dist27;
                dir53 = dir27;
            }

            if(cost53 + dist28 < dist53){
                dist53 = cost53 + dist28;
                dir53 = dir28;
            }

            if(cost53 + dist29 < dist53){
                dist53 = cost53 + dist29;
                dir53 = dir29;
            }
        }

        if(rc.onTheMap(ml54)){
            cost54 = rc.senseRubble(ml54) + 10;

            if(cost54 + dist28 < dist54){
                dist54 = cost54 + dist28;
                dir54 = dir28;
            }

            if(cost54 + dist29 < dist54){
                dist54 = cost54 + dist29;
                dir54 = dir29;
            }

            if(cost54 + dist30 < dist54){
                dist54 = cost54 + dist30;
                dir54 = dir30;
            }
        }

        if(rc.onTheMap(ml55)){
            cost55 = rc.senseRubble(ml55) + 10;

            if(cost55 + dist29 < dist55){
                dist55 = cost55 + dist29;
                dir55 = dir29;
            }

            if(cost55 + dist30 < dist55){
                dist55 = cost55 + dist30;
                dir55 = dir30;
            }
        }

        if(rc.onTheMap(ml56)){
            cost56 = rc.senseRubble(ml56) + 10;

            if(cost56 + dist30 < dist56){
                dist56 = cost56 + dist30;
                dir56 = dir30;
            }

            if(cost56 + dist31 < dist56){
                dist56 = cost56 + dist31;
                dir56 = dir31;
            }
        }

        if(rc.onTheMap(ml57)){
            cost57 = rc.senseRubble(ml57) + 10;

            if(cost57 + dist30 < dist57){
                dist57 = cost57 + dist30;
                dir57 = dir30;
            }

            if(cost57 + dist31 < dist57){
                dist57 = cost57 + dist31;
                dir57 = dir31;
            }

            if(cost57 + dist32 < dist57){
                dist57 = cost57 + dist32;
                dir57 = dir32;
            }
        }

        if(rc.onTheMap(ml58)){
            cost58 = rc.senseRubble(ml58) + 10;

            if(cost58 + dist31 < dist58){
                dist58 = cost58 + dist31;
                dir58 = dir31;
            }

            if(cost58 + dist32 < dist58){
                dist58 = cost58 + dist32;
                dir58 = dir32;
            }

            if(cost58 + dist33 < dist58){
                dist58 = cost58 + dist33;
                dir58 = dir33;
            }
        }

        if(rc.onTheMap(ml59)){
            cost59 = rc.senseRubble(ml59) + 10;

            if(cost59 + dist32 < dist59){
                dist59 = cost59 + dist32;
                dir59 = dir32;
            }

            if(cost59 + dist33 < dist59){
                dist59 = cost59 + dist33;
                dir59 = dir33;
            }

            if(cost59 + dist34 < dist59){
                dist59 = cost59 + dist34;
                dir59 = dir34;
            }
        }

        if(rc.onTheMap(ml60)){
            cost60 = rc.senseRubble(ml60) + 10;

            if(cost60 + dist33 < dist60){
                dist60 = cost60 + dist33;
                dir60 = dir33;
            }

            if(cost60 + dist34 < dist60){
                dist60 = cost60 + dist34;
                dir60 = dir34;
            }

            if(cost60 + dist35 < dist60){
                dist60 = cost60 + dist35;
                dir60 = dir35;
            }
        }

        if(rc.onTheMap(ml61)){
            cost61 = rc.senseRubble(ml61) + 10;

            if(cost61 + dist34 < dist61){
                dist61 = cost61 + dist34;
                dir61 = dir34;
            }

            if(cost61 + dist35 < dist61){
                dist61 = cost61 + dist35;
                dir61 = dir35;
            }

            if(cost61 + dist36 < dist61){
                dist61 = cost61 + dist36;
                dir61 = dir36;
            }
        }

        if(rc.onTheMap(ml62)){
            cost62 = rc.senseRubble(ml62) + 10;

            if(cost62 + dist35 < dist62){
                dist62 = cost62 + dist35;
                dir62 = dir35;
            }

            if(cost62 + dist36 < dist62){
                dist62 = cost62 + dist36;
                dir62 = dir36;
            }
        }

        if(rc.onTheMap(ml63)){
            cost63 = rc.senseRubble(ml63) + 10;

            if(cost63 + dist37 < dist63){
                dist63 = cost63 + dist37;
                dir63 = dir37;
            }

            if(cost63 + dist36 < dist63){
                dist63 = cost63 + dist36;
                dir63 = dir36;
            }
        }

        if(rc.onTheMap(ml64)){
            cost64 = rc.senseRubble(ml64) + 10;

            if(cost64 + dist38 < dist64){
                dist64 = cost64 + dist38;
                dir64 = dir38;
            }

            if(cost64 + dist37 < dist64){
                dist64 = cost64 + dist37;
                dir64 = dir37;
            }

            if(cost64 + dist36 < dist64){
                dist64 = cost64 + dist36;
                dir64 = dir36;
            }
        }

        if(rc.onTheMap(ml65)){
            cost65 = rc.senseRubble(ml65) + 10;

            if(cost65 + dist39 < dist65){
                dist65 = cost65 + dist39;
                dir65 = dir39;
            }

            if(cost65 + dist38 < dist65){
                dist65 = cost65 + dist38;
                dir65 = dir38;
            }

            if(cost65 + dist37 < dist65){
                dist65 = cost65 + dist37;
                dir65 = dir37;
            }
        }

        if(rc.onTheMap(ml66)){
            cost66 = rc.senseRubble(ml66) + 10;

            if(cost66 + dist40 < dist66){
                dist66 = cost66 + dist40;
                dir66 = dir40;
            }

            if(cost66 + dist39 < dist66){
                dist66 = cost66 + dist39;
                dir66 = dir39;
            }

            if(cost66 + dist38 < dist66){
                dist66 = cost66 + dist38;
                dir66 = dir38;
            }
        }

        if(rc.onTheMap(ml67)){
            cost67 = rc.senseRubble(ml67) + 10;

            if(cost67 + dist41 < dist67){
                dist67 = cost67 + dist41;
                dir67 = dir41;
            }

            if(cost67 + dist40 < dist67){
                dist67 = cost67 + dist40;
                dir67 = dir40;
            }

            if(cost67 + dist39 < dist67){
                dist67 = cost67 + dist39;
                dir67 = dir39;
            }
        }

        if(rc.onTheMap(ml68)){
            cost68 = rc.senseRubble(ml68) + 10;

            if(cost68 + dist42 < dist68){
                dist68 = cost68 + dist42;
                dir68 = dir42;
            }

            if(cost68 + dist41 < dist68){
                dist68 = cost68 + dist41;
                dir68 = dir41;
            }

            if(cost68 + dist40 < dist68){
                dist68 = cost68 + dist40;
                dir68 = dir40;
            }
        }

        if(rc.onTheMap(ml69)){
            cost69 = rc.senseRubble(ml69) + 10;

            if(cost69 + dist42 < dist69){
                dist69 = cost69 + dist42;
                dir69 = dir42;
            }

            if(cost69 + dist41 < dist69){
                dist69 = cost69 + dist41;
                dir69 = dir41;
            }
        }

        if(rc.onTheMap(ml70)){
            cost70 = rc.senseRubble(ml70) + 10;

            if(cost70 + dist43 < dist70){
                dist70 = cost70 + dist43;
                dir70 = dir43;
            }

            if(cost70 + dist42 < dist70){
                dist70 = cost70 + dist42;
                dir70 = dir42;
            }
        }

        if(rc.onTheMap(ml71)){
            cost71 = rc.senseRubble(ml71) + 10;

            if(cost71 + dist44 < dist71){
                dist71 = cost71 + dist44;
                dir71 = dir44;
            }

            if(cost71 + dist43 < dist71){
                dist71 = cost71 + dist43;
                dir71 = dir43;
            }

            if(cost71 + dist42 < dist71){
                dist71 = cost71 + dist42;
                dir71 = dir42;
            }
        }

        if(rc.onTheMap(ml72)){
            cost72 = rc.senseRubble(ml72) + 10;

            if(cost72 + dist45 < dist72){
                dist72 = cost72 + dist45;
                dir72 = dir45;
            }

            if(cost72 + dist44 < dist72){
                dist72 = cost72 + dist44;
                dir72 = dir44;
            }

            if(cost72 + dist43 < dist72){
                dist72 = cost72 + dist43;
                dir72 = dir43;
            }
        }

        if(rc.onTheMap(ml73)){
            cost73 = rc.senseRubble(ml73) + 10;

            if(cost73 + dist46 < dist73){
                dist73 = cost73 + dist46;
                dir73 = dir46;
            }

            if(cost73 + dist45 < dist73){
                dist73 = cost73 + dist45;
                dir73 = dir45;
            }

            if(cost73 + dist44 < dist73){
                dist73 = cost73 + dist44;
                dir73 = dir44;
            }
        }

        if(rc.onTheMap(ml74)){
            cost74 = rc.senseRubble(ml74) + 10;

            if(cost74 + dist47 < dist74){
                dist74 = cost74 + dist47;
                dir74 = dir47;
            }

            if(cost74 + dist46 < dist74){
                dist74 = cost74 + dist46;
                dir74 = dir46;
            }

            if(cost74 + dist45 < dist74){
                dist74 = cost74 + dist45;
                dir74 = dir45;
            }
        }

        if(rc.onTheMap(ml75)){
            cost75 = rc.senseRubble(ml75) + 10;

            if(cost75 + dist48 < dist75){
                dist75 = cost75 + dist48;
                dir75 = dir48;
            }

            if(cost75 + dist47 < dist75){
                dist75 = cost75 + dist47;
                dir75 = dir47;
            }

            if(cost75 + dist46 < dist75){
                dist75 = cost75 + dist46;
                dir75 = dir46;
            }
        }

        if(rc.onTheMap(ml76)){
            cost76 = rc.senseRubble(ml76) + 10;

            if(cost76 + dist48 < dist76){
                dist76 = cost76 + dist48;
                dir76 = dir48;
            }

            if(cost76 + dist47 < dist76){
                dist76 = cost76 + dist47;
                dir76 = dir47;
            }
        }

        if(cost49 != 0){

            if(cost49 + dist50 < dist49){
                dist49 = cost49 + dist50;
                dir49 = dir50;
            }
        }

        if(cost50 != 0){

            if(cost50 + dist51 < dist50){
                dist50 = cost50 + dist51;
                dir50 = dir51;
            }
        }

        if(cost51 != 0){

            if(cost51 + dist52 < dist51){
                dist51 = cost51 + dist52;
                dir51 = dir52;
            }
        }

        if(cost53 != 0){

            if(cost53 + dist52 < dist53){
                dist53 = cost53 + dist52;
                dir53 = dir52;
            }
        }

        if(cost54 != 0){

            if(cost54 + dist53 < dist54){
                dist54 = cost54 + dist53;
                dir54 = dir53;
            }
        }

        if(cost55 != 0){

            if(cost55 + dist54 < dist55){
                dist55 = cost55 + dist54;
                dir55 = dir54;
            }
        }

        if(cost56 != 0){

            if(cost56 + dist57 < dist56){
                dist56 = cost56 + dist57;
                dir56 = dir57;
            }
        }

        if(cost57 != 0){

            if(cost57 + dist58 < dist57){
                dist57 = cost57 + dist58;
                dir57 = dir58;
            }
        }

        if(cost58 != 0){

            if(cost58 + dist59 < dist58){
                dist58 = cost58 + dist59;
                dir58 = dir59;
            }
        }

        if(cost60 != 0){

            if(cost60 + dist59 < dist60){
                dist60 = cost60 + dist59;
                dir60 = dir59;
            }
        }

        if(cost61 != 0){

            if(cost61 + dist60 < dist61){
                dist61 = cost61 + dist60;
                dir61 = dir60;
            }
        }

        if(cost62 != 0){

            if(cost62 + dist61 < dist62){
                dist62 = cost62 + dist61;
                dir62 = dir61;
            }
        }

        if(cost63 != 0){

            if(cost63 + dist64 < dist63){
                dist63 = cost63 + dist64;
                dir63 = dir64;
            }
        }

        if(cost64 != 0){

            if(cost64 + dist65 < dist64){
                dist64 = cost64 + dist65;
                dir64 = dir65;
            }
        }

        if(cost65 != 0){

            if(cost65 + dist66 < dist65){
                dist65 = cost65 + dist66;
                dir65 = dir66;
            }
        }

        if(cost67 != 0){

            if(cost67 + dist66 < dist67){
                dist67 = cost67 + dist66;
                dir67 = dir66;
            }
        }

        if(cost68 != 0){

            if(cost68 + dist67 < dist68){
                dist68 = cost68 + dist67;
                dir68 = dir67;
            }
        }

        if(cost69 != 0){

            if(cost69 + dist68 < dist69){
                dist69 = cost69 + dist68;
                dir69 = dir68;
            }
        }

        if(cost70 != 0){

            if(cost70 + dist71 < dist70){
                dist70 = cost70 + dist71;
                dir70 = dir71;
            }
        }

        if(cost71 != 0){

            if(cost71 + dist72 < dist71){
                dist71 = cost71 + dist72;
                dir71 = dir72;
            }
        }

        if(cost72 != 0){

            if(cost72 + dist73 < dist72){
                dist72 = cost72 + dist73;
                dir72 = dir73;
            }
        }

        if(cost74 != 0){

            if(cost74 + dist73 < dist74){
                dist74 = cost74 + dist73;
                dir74 = dir73;
            }
        }

        if(cost75 != 0){

            if(cost75 + dist74 < dist75){
                dist75 = cost75 + dist74;
                dir75 = dir74;
            }
        }

        if(cost76 != 0){

            if(cost76 + dist75 < dist76){
                dist76 = cost76 + dist75;
                dir76 = dir75;
            }
        }

        if(rc.onTheMap(ml77)){

            if(cost77 + dist76 < dist77){
                dist77 = cost77 + dist76;
                dir77 = dir76;
            }

            if(cost77 + dist49 < dist77){
                dist77 = cost77 + dist49;
                dir77 = dir49;
            }

            if(cost77 + dist48 < dist77){
                dist77 = cost77 + dist48;
                dir77 = dir48;
            }
        }

        if(rc.onTheMap(ml78)){

            if(cost78 + dist49 < dist78){
                dist78 = cost78 + dist49;
                dir78 = dir49;
            }

            if(cost78 + dist50 < dist78){
                dist78 = cost78 + dist50;
                dir78 = dir50;
            }
        }

        if(rc.onTheMap(ml79)){

            if(cost79 + dist49 < dist79){
                dist79 = cost79 + dist49;
                dir79 = dir49;
            }

            if(cost79 + dist50 < dist79){
                dist79 = cost79 + dist50;
                dir79 = dir50;
            }

            if(cost79 + dist51 < dist79){
                dist79 = cost79 + dist51;
                dir79 = dir51;
            }
        }

        if(rc.onTheMap(ml80)){

            if(cost80 + dist50 < dist80){
                dist80 = cost80 + dist50;
                dir80 = dir50;
            }

            if(cost80 + dist51 < dist80){
                dist80 = cost80 + dist51;
                dir80 = dir51;
            }

            if(cost80 + dist52 < dist80){
                dist80 = cost80 + dist52;
                dir80 = dir52;
            }
        }

        if(rc.onTheMap(ml81)){

            if(cost81 + dist51 < dist81){
                dist81 = cost81 + dist51;
                dir81 = dir51;
            }

            if(cost81 + dist52 < dist81){
                dist81 = cost81 + dist52;
                dir81 = dir52;
            }

            if(cost81 + dist53 < dist81){
                dist81 = cost81 + dist53;
                dir81 = dir53;
            }
        }

        if(rc.onTheMap(ml82)){

            if(cost82 + dist52 < dist82){
                dist82 = cost82 + dist52;
                dir82 = dir52;
            }

            if(cost82 + dist53 < dist82){
                dist82 = cost82 + dist53;
                dir82 = dir53;
            }

            if(cost82 + dist54 < dist82){
                dist82 = cost82 + dist54;
                dir82 = dir54;
            }
        }

        if(rc.onTheMap(ml83)){

            if(cost83 + dist53 < dist83){
                dist83 = cost83 + dist53;
                dir83 = dir53;
            }

            if(cost83 + dist54 < dist83){
                dist83 = cost83 + dist54;
                dir83 = dir54;
            }

            if(cost83 + dist55 < dist83){
                dist83 = cost83 + dist55;
                dir83 = dir55;
            }
        }

        if(rc.onTheMap(ml84)){

            if(cost84 + dist54 < dist84){
                dist84 = cost84 + dist54;
                dir84 = dir54;
            }

            if(cost84 + dist55 < dist84){
                dist84 = cost84 + dist55;
                dir84 = dir55;
            }
        }

        if(rc.onTheMap(ml85)){

            if(cost85 + dist55 < dist85){
                dist85 = cost85 + dist55;
                dir85 = dir55;
            }

            if(cost85 + dist30 < dist85){
                dist85 = cost85 + dist30;
                dir85 = dir30;
            }

            if(cost85 + dist56 < dist85){
                dist85 = cost85 + dist56;
                dir85 = dir56;
            }
        }

        if(rc.onTheMap(ml86)){

            if(cost86 + dist56 < dist86){
                dist86 = cost86 + dist56;
                dir86 = dir56;
            }

            if(cost86 + dist57 < dist86){
                dist86 = cost86 + dist57;
                dir86 = dir57;
            }
        }

        if(rc.onTheMap(ml87)){

            if(cost87 + dist56 < dist87){
                dist87 = cost87 + dist56;
                dir87 = dir56;
            }

            if(cost87 + dist57 < dist87){
                dist87 = cost87 + dist57;
                dir87 = dir57;
            }

            if(cost87 + dist58 < dist87){
                dist87 = cost87 + dist58;
                dir87 = dir58;
            }
        }

        if(rc.onTheMap(ml88)){

            if(cost88 + dist57 < dist88){
                dist88 = cost88 + dist57;
                dir88 = dir57;
            }

            if(cost88 + dist58 < dist88){
                dist88 = cost88 + dist58;
                dir88 = dir58;
            }

            if(cost88 + dist59 < dist88){
                dist88 = cost88 + dist59;
                dir88 = dir59;
            }
        }

        if(rc.onTheMap(ml89)){

            if(cost89 + dist58 < dist89){
                dist89 = cost89 + dist58;
                dir89 = dir58;
            }

            if(cost89 + dist59 < dist89){
                dist89 = cost89 + dist59;
                dir89 = dir59;
            }

            if(cost89 + dist60 < dist89){
                dist89 = cost89 + dist60;
                dir89 = dir60;
            }
        }

        if(rc.onTheMap(ml90)){

            if(cost90 + dist59 < dist90){
                dist90 = cost90 + dist59;
                dir90 = dir59;
            }

            if(cost90 + dist60 < dist90){
                dist90 = cost90 + dist60;
                dir90 = dir60;
            }

            if(cost90 + dist61 < dist90){
                dist90 = cost90 + dist61;
                dir90 = dir61;
            }
        }

        if(rc.onTheMap(ml91)){

            if(cost91 + dist60 < dist91){
                dist91 = cost91 + dist60;
                dir91 = dir60;
            }

            if(cost91 + dist61 < dist91){
                dist91 = cost91 + dist61;
                dir91 = dir61;
            }

            if(cost91 + dist62 < dist91){
                dist91 = cost91 + dist62;
                dir91 = dir62;
            }
        }

        if(rc.onTheMap(ml92)){

            if(cost92 + dist61 < dist92){
                dist92 = cost92 + dist61;
                dir92 = dir61;
            }

            if(cost92 + dist62 < dist92){
                dist92 = cost92 + dist62;
                dir92 = dir62;
            }
        }

        if(rc.onTheMap(ml93)){

            if(cost93 + dist36 < dist93){
                dist93 = cost93 + dist36;
                dir93 = dir36;
            }

            if(cost93 + dist63 < dist93){
                dist93 = cost93 + dist63;
                dir93 = dir63;
            }

            if(cost93 + dist62 < dist93){
                dist93 = cost93 + dist62;
                dir93 = dir62;
            }
        }

        if(rc.onTheMap(ml94)){

            if(cost94 + dist64 < dist94){
                dist94 = cost94 + dist64;
                dir94 = dir64;
            }

            if(cost94 + dist63 < dist94){
                dist94 = cost94 + dist63;
                dir94 = dir63;
            }
        }

        if(rc.onTheMap(ml95)){

            if(cost95 + dist65 < dist95){
                dist95 = cost95 + dist65;
                dir95 = dir65;
            }

            if(cost95 + dist64 < dist95){
                dist95 = cost95 + dist64;
                dir95 = dir64;
            }

            if(cost95 + dist63 < dist95){
                dist95 = cost95 + dist63;
                dir95 = dir63;
            }
        }

        if(rc.onTheMap(ml96)){

            if(cost96 + dist66 < dist96){
                dist96 = cost96 + dist66;
                dir96 = dir66;
            }

            if(cost96 + dist65 < dist96){
                dist96 = cost96 + dist65;
                dir96 = dir65;
            }

            if(cost96 + dist64 < dist96){
                dist96 = cost96 + dist64;
                dir96 = dir64;
            }
        }

        if(rc.onTheMap(ml97)){

            if(cost97 + dist67 < dist97){
                dist97 = cost97 + dist67;
                dir97 = dir67;
            }

            if(cost97 + dist66 < dist97){
                dist97 = cost97 + dist66;
                dir97 = dir66;
            }

            if(cost97 + dist65 < dist97){
                dist97 = cost97 + dist65;
                dir97 = dir65;
            }
        }

        if(rc.onTheMap(ml98)){

            if(cost98 + dist68 < dist98){
                dist98 = cost98 + dist68;
                dir98 = dir68;
            }

            if(cost98 + dist67 < dist98){
                dist98 = cost98 + dist67;
                dir98 = dir67;
            }

            if(cost98 + dist66 < dist98){
                dist98 = cost98 + dist66;
                dir98 = dir66;
            }
        }

        if(rc.onTheMap(ml99)){

            if(cost99 + dist69 < dist99){
                dist99 = cost99 + dist69;
                dir99 = dir69;
            }

            if(cost99 + dist68 < dist99){
                dist99 = cost99 + dist68;
                dir99 = dir68;
            }

            if(cost99 + dist67 < dist99){
                dist99 = cost99 + dist67;
                dir99 = dir67;
            }
        }

        if(rc.onTheMap(ml100)){

            if(cost100 + dist69 < dist100){
                dist100 = cost100 + dist69;
                dir100 = dir69;
            }

            if(cost100 + dist68 < dist100){
                dist100 = cost100 + dist68;
                dir100 = dir68;
            }
        }

        if(rc.onTheMap(ml101)){

            if(cost101 + dist70 < dist101){
                dist101 = cost101 + dist70;
                dir101 = dir70;
            }

            if(cost101 + dist42 < dist101){
                dist101 = cost101 + dist42;
                dir101 = dir42;
            }

            if(cost101 + dist69 < dist101){
                dist101 = cost101 + dist69;
                dir101 = dir69;
            }
        }

        if(rc.onTheMap(ml102)){

            if(cost102 + dist71 < dist102){
                dist102 = cost102 + dist71;
                dir102 = dir71;
            }

            if(cost102 + dist70 < dist102){
                dist102 = cost102 + dist70;
                dir102 = dir70;
            }
        }

        if(rc.onTheMap(ml103)){

            if(cost103 + dist72 < dist103){
                dist103 = cost103 + dist72;
                dir103 = dir72;
            }

            if(cost103 + dist71 < dist103){
                dist103 = cost103 + dist71;
                dir103 = dir71;
            }

            if(cost103 + dist70 < dist103){
                dist103 = cost103 + dist70;
                dir103 = dir70;
            }
        }

        if(rc.onTheMap(ml104)){

            if(cost104 + dist73 < dist104){
                dist104 = cost104 + dist73;
                dir104 = dir73;
            }

            if(cost104 + dist72 < dist104){
                dist104 = cost104 + dist72;
                dir104 = dir72;
            }

            if(cost104 + dist71 < dist104){
                dist104 = cost104 + dist71;
                dir104 = dir71;
            }
        }

        if(rc.onTheMap(ml105)){

            if(cost105 + dist74 < dist105){
                dist105 = cost105 + dist74;
                dir105 = dir74;
            }

            if(cost105 + dist73 < dist105){
                dist105 = cost105 + dist73;
                dir105 = dir73;
            }

            if(cost105 + dist72 < dist105){
                dist105 = cost105 + dist72;
                dir105 = dir72;
            }
        }

        if(rc.onTheMap(ml106)){

            if(cost106 + dist75 < dist106){
                dist106 = cost106 + dist75;
                dir106 = dir75;
            }

            if(cost106 + dist74 < dist106){
                dist106 = cost106 + dist74;
                dir106 = dir74;
            }

            if(cost106 + dist73 < dist106){
                dist106 = cost106 + dist73;
                dir106 = dir73;
            }
        }

        if(rc.onTheMap(ml107)){

            if(cost107 + dist76 < dist107){
                dist107 = cost107 + dist76;
                dir107 = dir76;
            }

            if(cost107 + dist75 < dist107){
                dist107 = cost107 + dist75;
                dir107 = dir75;
            }

            if(cost107 + dist74 < dist107){
                dist107 = cost107 + dist74;
                dir107 = dir74;
            }
        }

        if(rc.onTheMap(ml108)){

            if(cost108 + dist76 < dist108){
                dist108 = cost108 + dist76;
                dir108 = dir76;
            }

            if(cost108 + dist75 < dist108){
                dist108 = cost108 + dist75;
                dir108 = dir75;
            }
        }

        //rc.setIndicatorString(time1+" ");


        int xDiff = target.x - ml0.x;
        int yDiff = target.y - ml0.y;

        switch (xDiff){
            case -5:
                switch (yDiff){
                    case -3:
                        return dir108;
                    case -2:
                        return dir107;
                    case -1:
                        return dir106;
                    case 0:
                        return dir105;
                    case 1:
                        return dir104;
                    case 2:
                        return dir103;
                    case 3:
                        return dir102;
                }
                break;
            case -4:
                switch (yDiff){
                    case -3:
                        return dir76;
                    case -2:
                        return dir75;
                    case -1:
                        return dir74;
                    case 0:
                        return dir73;
                    case 1:
                        return dir72;
                    case 2:
                        return dir71;
                    case 3:
                        return dir70;
                }
                break;
            case -3:
                switch (yDiff){
                    case -3:
                        return dir48;
                    case -2:
                        return dir47;
                    case -1:
                        return dir46;
                    case 0:
                        return dir45;
                    case 1:
                        return dir44;
                    case 2:
                        return dir43;
                    case 3:
                        return dir42;
                }
                break;
            case -2:
                switch (yDiff){
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
                        return dir41;
                }
                break;
            case -1:
                switch (yDiff){
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
                        return dir40;
                }
                break;
            case 0:
                switch (yDiff){
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
                        return dir39;
                }
                break;
            case 1:
                switch (yDiff){
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
                        return dir38;
                }
                break;
            case 2:
                switch (yDiff){
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
                        return dir37;
                }
                break;
            case 3:
                switch (yDiff){
                    case -3:
                        return dir30;
                    case -2:
                        return dir31;
                    case -1:
                        return dir32;
                    case 0:
                        return dir33;
                    case 1:
                        return dir34;
                    case 2:
                        return dir35;
                    case 3:
                        return dir36;
                }
                break;
            case 4:
                switch (yDiff){
                    case -3:
                        return dir56;
                    case -2:
                        return dir57;
                    case -1:
                        return dir58;
                    case 0:
                        return dir59;
                    case 1:
                        return dir60;
                    case 2:
                        return dir61;
                    case 3:
                        return dir62;
                }
                break;
            case 5:
                switch (yDiff){
                    case -3:
                        return dir86;
                    case -2:
                        return dir87;
                    case -1:
                        return dir88;
                    case 0:
                        return dir89;
                    case 1:
                        return dir90;
                    case 2:
                        return dir91;
                    case 3:
                        return dir92;
                }
                break;
        }

        double gain;

        Direction ans = Direction.CENTER;
        double initDist = Math.sqrt(ml0.distanceSquaredTo(target));
        double maxGainPerCost = 0;

        gain = (initDist - Math.sqrt(ml77.distanceSquaredTo(target))) / dist77;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir77;
        }

        gain = (initDist - Math.sqrt(ml78.distanceSquaredTo(target))) / dist78;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir78;
        }

        gain = (initDist - Math.sqrt(ml79.distanceSquaredTo(target))) / dist79;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir79;
        }

        gain = (initDist - Math.sqrt(ml80.distanceSquaredTo(target))) / dist80;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir80;
        }

        gain = (initDist - Math.sqrt(ml81.distanceSquaredTo(target))) / dist81;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir81;
        }

        gain = (initDist - Math.sqrt(ml82.distanceSquaredTo(target))) / dist82;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir82;
        }

        gain = (initDist - Math.sqrt(ml83.distanceSquaredTo(target))) / dist83;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir83;
        }

        gain = (initDist - Math.sqrt(ml84.distanceSquaredTo(target))) / dist84;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir84;
        }

        gain = (initDist - Math.sqrt(ml85.distanceSquaredTo(target))) / dist85;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir85;
        }

        gain = (initDist - Math.sqrt(ml86.distanceSquaredTo(target))) / dist86;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir86;
        }

        gain = (initDist - Math.sqrt(ml87.distanceSquaredTo(target))) / dist87;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir87;
        }

        gain = (initDist - Math.sqrt(ml88.distanceSquaredTo(target))) / dist88;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir88;
        }

        gain = (initDist - Math.sqrt(ml89.distanceSquaredTo(target))) / dist89;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir89;
        }

        gain = (initDist - Math.sqrt(ml90.distanceSquaredTo(target))) / dist90;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir90;
        }

        gain = (initDist - Math.sqrt(ml91.distanceSquaredTo(target))) / dist91;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir91;
        }

        gain = (initDist - Math.sqrt(ml92.distanceSquaredTo(target))) / dist92;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir92;
        }

        gain = (initDist - Math.sqrt(ml93.distanceSquaredTo(target))) / dist93;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir93;
        }

        gain = (initDist - Math.sqrt(ml94.distanceSquaredTo(target))) / dist94;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir94;
        }

        gain = (initDist - Math.sqrt(ml95.distanceSquaredTo(target))) / dist95;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir95;
        }

        gain = (initDist - Math.sqrt(ml96.distanceSquaredTo(target))) / dist96;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir96;
        }

        gain = (initDist - Math.sqrt(ml97.distanceSquaredTo(target))) / dist97;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir97;
        }

        gain = (initDist - Math.sqrt(ml98.distanceSquaredTo(target))) / dist98;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir98;
        }

        gain = (initDist - Math.sqrt(ml99.distanceSquaredTo(target))) / dist99;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir99;
        }

        gain = (initDist - Math.sqrt(ml100.distanceSquaredTo(target))) / dist100;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir100;
        }

        gain = (initDist - Math.sqrt(ml101.distanceSquaredTo(target))) / dist101;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir101;
        }

        gain = (initDist - Math.sqrt(ml102.distanceSquaredTo(target))) / dist102;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir102;
        }

        gain = (initDist - Math.sqrt(ml103.distanceSquaredTo(target))) / dist103;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir103;
        }

        gain = (initDist - Math.sqrt(ml104.distanceSquaredTo(target))) / dist104;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir104;
        }

        gain = (initDist - Math.sqrt(ml105.distanceSquaredTo(target))) / dist105;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir105;
        }

        gain = (initDist - Math.sqrt(ml106.distanceSquaredTo(target))) / dist106;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir106;
        }

        gain = (initDist - Math.sqrt(ml107.distanceSquaredTo(target))) / dist107;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir107;
        }

        gain = (initDist - Math.sqrt(ml108.distanceSquaredTo(target))) / dist108;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir108;
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

        gain = (initDist - Math.sqrt(ml69.distanceSquaredTo(target))) / dist69;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir69;
        }

        gain = (initDist - Math.sqrt(ml70.distanceSquaredTo(target))) / dist70;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir70;
        }

        gain = (initDist - Math.sqrt(ml76.distanceSquaredTo(target))) / dist76;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir76;
        }

        gain = (initDist - Math.sqrt(ml49.distanceSquaredTo(target))) / dist49;
        if(gain > maxGainPerCost){
            maxGainPerCost = gain;
            ans = dir49;
        }

        int time8 = Clock.getBytecodesLeft();

        //rc.setIndicatorString(time1+" "+time8);

        return ans;

    }
}
