package dontatme;

import battlecode.common.*;

public strictfp class Soldier {
    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    static Pathfinder pathfinder;

    static int state;
    static RobotInfo[] sensedInfo;

    static MapLocation[] targets;


    public static void run(RobotController rc) throws GameActionException {
        if(pathfinder == null){
            pathfinder = new BFPathing20(rc);
        }
        readComms();
        sensedInfo = rc.senseNearbyRobots();

        switch (state){
            case 0: // Attacking
                break;
            case 1: //Defending
                break;
            case 2: //Exploring
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

    static void attack(int attackType){

    }

    static void defend(){

    }

    static void explore(){

    }
}
