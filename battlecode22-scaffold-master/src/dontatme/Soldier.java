package dontatme;

import battlecode.common.*;

import java.awt.*;

public strictfp class Soldier {
    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    static Pathfinder pathfinder;

    static int state;
    static RobotInfo[] sensedInfo;

    static MapLocation curTarget;

    static RobotController rc;

    public Soldier (RobotController rc) throws GameActionException {
        this.rc = rc;
        pathfinder = new BFPathing20(rc);
    }


    public void run() throws GameActionException {
        readComms();
        sensedInfo = rc.senseNearbyRobots();

        switch (state){
            case 0: // Attacking
                offense(curTarget, 4);
                break;
            case 1: //Defending
                defense(curTarget);
                break;
            case 2: //Exploring
                explore();
                break;

        }

        writeComms();

    }

    static void readComms(){

    }

    static void writeComms(){

    }

    static void baseAction(){

    }

    static void offense(MapLocation target, int attackType){

    }

    static void defense(MapLocation target) throws GameActionException {
        if(!pathfinder.targetWithinRadius(target, 2)){
            move(pathfinder.pathToTarget(target, false));
        }
        attack(1);
    }

    static void explore() throws GameActionException {
        move(pathfinder.pathToExplore());
        attack(1);
    }

    static void attack(int attackType) throws GameActionException {

        if(rc.isActionReady()){
            MapLocation attackLoc;
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

            if(attackLoc != null){
                rc.attack(attackLoc);
            }
        }
    }

    static MapLocation getAttack(int prio1, int prio2, int prio3, int prio4){
        MapLocation[] ml = new MapLocation[4];
        int[] minHealth = new int[4];

        for(RobotInfo robot : sensedInfo){
            RobotType type = robot.getType();
            int id = robot.getHealth();
            if(type.isBuilding()){
                if(type.equals(RobotType.ARCHON)){
                    if(id < minHealth[0]){
                        ml[0] = robot.getLocation();
                        minHealth[0] = id;
                    }
                } else{
                    if(id < minHealth[1]){
                        ml[1] = robot.getLocation();
                        minHealth[1] = id;
                    }
                }
            } else{
                if(type.equals(RobotType.MINER)){
                    if(id < minHealth[2]){
                        ml[2] = robot.getLocation();
                        minHealth[2] = id;
                    }
                } else {
                    if(id < minHealth[3]){
                        ml[3] = robot.getLocation();
                        minHealth[3] = id;
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
        if(rc.canMove(dir)){
            rc.move(dir);
        }
    }


}
