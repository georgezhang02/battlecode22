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

        int[] units = Communications.getUnitCounts(rc);

        // deciding to rush
        int minerCount = units[0];
        int soldierCount = units[1];
        int builderCount = units[2];

        int labCount = units[3];
        int sageCount = units[4];

        if(flying){

        } else{
            rc.setIndicatorString(rc.getTransmutationRate()+" "+rc.canTransmute());
            if(rc.canTransmute()){
                rc.transmute();
            }



        }

    }
}
    