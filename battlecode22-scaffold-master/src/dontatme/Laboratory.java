package dontatme;

import battlecode.common.*;

public strictfp class Laboratory {


    /**
     * Run a single turn for a Laboratory.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    static boolean flying = false;
    static MapLocation curTarget;
    public static void run(RobotController rc) throws GameActionException {
        Communications.runStart(rc);
        if(flying){

        } else{
            if(rc.canTransmute()){
                rc.transmute();
            }
        }

    }
}
    