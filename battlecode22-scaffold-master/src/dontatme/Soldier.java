package dontatme;

import battlecode.common.*;

import java.awt.*;
import java.util.Map;

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

    static int commandIteration;

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
            case 3:
                prepForAnomaly(AnomalyType.ABYSS);
                break;

        }

        writeComms();

    }

    static void readComms(){

    }

    static void writeComms(){

    }


    static void prepForAnomaly(AnomalyType anomalyType)
    {

    }
    static void offense(MapLocation target, int attackType) throws GameActionException {
        if(!pathfinder.targetWithinRadius(target, 2)){
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

    static void defense(MapLocation target) throws GameActionException {
        if(!pathfinder.targetWithinRadius(target, 2)){
            move(pathfinder.pathToTarget(target, false));
        } else{
            MapLocation ml = attack(1);
            if(ml== null){
                state = 2;
            }
        }
    }


    static void explore() throws GameActionException {
        curTarget = null;
        move(pathfinder.pathToExplore());
        attack(1);
    }

    static MapLocation attack(int attackType) throws GameActionException {

        if(rc.isActionReady()){
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

            if(attackLoc != null){
                rc.attack(attackLoc.getLocation());
                return attackLoc.getLocation();
            }
        }
        return null;
    }

    static RobotInfo getAttack(int prio1, int prio2, int prio3, int prio4){
        RobotInfo[] ml = new RobotInfo[4];
        int[] minHealth = new int[4];

        for(RobotInfo robot : sensedInfo){
            RobotType type = robot.getType();
            int id = robot.getHealth();
            if(type.isBuilding()){
                if(type.equals(RobotType.ARCHON)){
                    if(id < minHealth[0]){
                        ml[0] = robot;
                        minHealth[0] = id;
                    }
                } else{
                    if(id < minHealth[1]){
                        ml[1] = robot;
                        minHealth[1] = id;
                    }
                }
            } else{
                if(type.equals(RobotType.MINER)){
                    if(id < minHealth[2]){
                        ml[2] = robot;
                        minHealth[2] = id;
                    }
                } else {
                    if(id < minHealth[3]){
                        ml[3] = robot;
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
