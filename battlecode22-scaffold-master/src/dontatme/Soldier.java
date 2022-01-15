package dontatme;

import battlecode.common.*;

import java.awt.*;

public strictfp class Soldier {
    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    static Pathfinder pathfinder;

    static int state = -1;
    static RobotInfo[] enemies;
    static RobotInfo[] allies;

    static MapLocation curTarget;
    static MapLocation archonDef;

    static RobotController rc;

    static int commandID = -1;
    static boolean rusher;

    static Direction[] directions = {
            Direction.NORTH,
            Direction.WEST,
            Direction.EAST,
            Direction.SOUTH,
            Direction.NORTHWEST,
            Direction.NORTHEAST,
            Direction.SOUTHWEST,
            Direction.SOUTHEAST

    };


    public Soldier (RobotController rc) throws GameActionException {
        this.rc = rc;
        pathfinder = new BFPathing20(rc);

        if(Math.random() > .5){
            rusher = false;
            state = 2;
        } else{
            rusher = true;
            int x = (int) (2 * Math.random()) ;
            int y = (int) (2 * Math.random()) ;

            if(2 * Math.random() >= 1){
                x = -x;
            }
            if(2 * Math.random() >= 1){
                y = -y;
            }
            x = rc.getLocation().x + x;
            y = rc.getLocation().y + y;

            x = Math.max(0, Math.min(x, rc.getMapWidth()-1));
            y = Math.max(0, Math.min(y, rc.getMapHeight()-1));

            curTarget = new MapLocation(x, y);

            state = 5;
        }
    }

    public void run() throws GameActionException {
        //readComms();
        Team opponent = rc.getTeam().opponent();
        enemies = rc.senseNearbyRobots(20, opponent);
        allies = rc.senseNearbyRobots(20, rc.getTeam());



        switch (state){
            case 0: // Attacking
                offense(curTarget, 4);
                break;
            case 1: //Defending
                defense(curTarget, 6);
                break;
            case 2: //Exploring
                explore();
                break;
            case 3: //Pursuing
                offense(curTarget, 1);
                break;
            case 4: // Rushing Enemy Archon
                break;
            case 5: // Defending Own Archon
                defense(curTarget, 8);
                break;
            case 6: //Prep for anomaly
                prepForAnomaly(AnomalyType.ABYSS);
                break;

        }



    }

    static void readComms() throws GameActionException {
        for(int i = 0; i<rc.getArchonCount(); i++){
            MapLocation archonLoc = Communications.getEnemyArchonLocationByIndex(rc, i);

            if(archonLoc.x != -1){
                rc.setIndicatorString(i+" "+archonLoc.x + " "+ archonLoc.y);
                if(state != 0 && (int)( rc.getArchonCount() * Math.random())==1){
                    curTarget = archonLoc;
                    state = 0;
                }
                else{
                    state = 2;
                }
            }
        }
    }

    static void writeComms(RobotInfo ri) throws GameActionException {
        if(ri.getType().equals(RobotType.ARCHON) ){
            Communications.setEnemyArchonLocation(rc, ri.getID(), ri.getLocation());
        }
    }


    static void prepForAnomaly(AnomalyType anomalyType)
    {

    }
    static void offense(MapLocation target, int attackType) throws GameActionException {
        rc.setIndicatorString("offense"+Clock.getBytecodesLeft());
        if(target != null && !pathfinder.targetWithinRadius(target, 6)){
            move(pathfinder.pathToTarget(target, false));
            attack(attackType);

        }  else{

            MapLocation ml = attack(attackType);
            if(ml!= null){

                curTarget = ml;
            } else{
                state = 2;
            }
        }


    }

    static void defense(MapLocation target, int minDistance) throws GameActionException {
        rc.setIndicatorString("defense "+Clock.getBytecodesLeft());
        if(target != null && !pathfinder.targetWithinRadius(target, minDistance)){
            move(pathfinder.pathToTarget(target, false));
        } else{
            Direction dir = directions[(int)(Math.random() * 8)];
            if(rc.canMove(dir)){
                rc.move(dir);
            }
            MapLocation ml = attack(1);
            if(ml== null && !rusher){
                state = 2;
            }
        }
    }


    static void explore() throws GameActionException {
        rc.setIndicatorString("explore "+Clock.getBytecodesLeft());

        if(!pathfinder.exploring){
            curTarget = null;
        }


        move(pathfinder.pathToExplore());
        rc.setIndicatorLine(rc.getLocation(), pathfinder.explorer.target, 255,255,0);

        MapLocation ml = attack(1);

        if(ml != null){
            curTarget = ml;
            state = 3;
        }
    }

    static MapLocation attack(int attackType) throws GameActionException {


        RobotInfo attackLoc;

        if(attackType == 0){ // attack all
            attackLoc = getAttack(3, 1, 0, 2);
        } else if(attackType == 1){ //attack droids
            attackLoc = getAttack(3, 2, 1, 0);
        }  else if (attackType == 3){// attack army (soldiers/sages/builders)
            attackLoc = getAttack(3, 1, 2, 0);
        } else if (attackType == 4){ // attack archon
            attackLoc = getAttack(0, 3, 1, 2);
        } else { // attack eco
            attackLoc = getAttack(2, 1, 3, 0);
        }
        if(attackLoc!= null && rc.canAttack(attackLoc.getLocation())){
            rc.attack(attackLoc.getLocation());


        }
        if(attackLoc != null){
            return attackLoc.getLocation();
        }
        return null;
    }

    static RobotInfo getAttack(int prio1, int prio2, int prio3, int prio4) throws GameActionException {
        RobotInfo[] ml = new RobotInfo[4];
        int[] minHealth = {2000,2000,2000,2000};
        int[] maxID = {0,0,0,0};

        for(RobotInfo robot : enemies){
            RobotType type = robot.getType();
            int health = robot.getHealth();
            int id = robot.getID();
            if(rc.getLocation().distanceSquaredTo(robot.getLocation()) <= 13){
                if(type.isBuilding()){
                    if(type.equals(RobotType.ARCHON)){
                        if(health < minHealth[0]){
                            ml[0] = robot;
                            minHealth[0] = health;
                        } else if(health == minHealth[0] && id > maxID[0]){
                            ml[0] = robot;
                            maxID[0] = id;
                        }
                    } else{
                        if(health < minHealth[1]){
                            ml[1] = robot;
                            minHealth[1] = health;
                        }else if(health == minHealth[1] && id > maxID[1]){
                            ml[1] = robot;
                            maxID[1] = id;
                        }
                    }
                } else{
                    if(type.equals(RobotType.MINER)){
                        if(health < minHealth[2]){
                            ml[2] = robot;
                            minHealth[2] = health;
                        }else if(health == minHealth[2] && id > maxID[2]){
                            ml[2] = robot;
                            maxID[2] = id;
                        }
                    } else {
                        if(health < minHealth[3]){
                            ml[3] = robot;
                            minHealth[3] = health;
                        }else if(health == minHealth[3] && id > maxID[3]){
                            ml[3] = robot;
                            maxID[3] = id;
                        }
                    }
                }
            }
        }

        if(ml[prio1] != null){
            return ml[prio1];
        }
        if(ml[prio2] != null){
            return ml[prio2];
        }
        if(ml[prio3] != null){
            return ml[prio3];
        }
        return ml[prio4];
    }

    static void move(Direction dir) throws GameActionException {
        if(dir != null && rc.canMove(dir)){
            rc.move(dir);
            pathfinder.explorer.updateVisited();;
        }
    }


}
