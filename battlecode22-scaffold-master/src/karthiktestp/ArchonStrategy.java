package karthiktestp;

import battlecode.common.*;

import java.util.Arrays;

public class ArchonStrategy {

    static int miners = 0, soldiers  = 0, builders = 0;
    /**
     * Run a single turn for an Archon.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static boolean placedLocation = false;
    static void runArchon(RobotController rc) throws GameActionException {
        //write the x-location of each archon
        int index = 0;
        while (!placedLocation && rc.readSharedArray(index) != 0)
            index += 2;
        //write all the archon locations
        rc.writeSharedArray(63, 0); //increment the shared array
        if (!placedLocation) {
            rc.writeSharedArray(index, rc.getLocation().x);
            rc.writeSharedArray(index + 1, rc.getLocation().y);
            placedLocation = true;
            //update the number of archons
            rc.writeSharedArray(63, rc.readSharedArray(63) + 1);
        }
        if (miners < 5) {
            buildTowardsLowRubble(rc, RobotType.MINER);
        }
        else if (soldiers < 30) {
            buildTowardsLowRubble(rc, RobotType.SOLDIER);
        }
        /*else if (builders < 1) {
            buildTowardsLowRubble(rc, RobotType.BUILDER);
        }*/
        else if (miners < soldiers / 2 && rc.getTeamLeadAmount(rc.getTeam()) < 5000){
            buildTowardsLowRubble(rc, RobotType.MINER);
        }
        /*else if (builders < soldiers / 10) {
            buildTowardsLowRubble(rc, RobotType.BUILDER);
        }*/
        else {
            buildTowardsLowRubble(rc, RobotType.SOLDIER);
        }
    }
    static void buildTowardsLowRubble(RobotController rc, RobotType type) throws GameActionException {
        Direction [] dirs = Arrays.copyOf(RobotPlayer.directions, RobotPlayer.directions.length);
        Arrays.sort(dirs, (a, b) -> getRubble(rc, a) - getRubble(rc, b));
        for (Direction d: dirs) {
            if (rc.canBuildRobot(type, d)) {
                rc.buildRobot(type, d);
                switch(type) {
                    case MINER: miners++; break;
                    case SOLDIER: soldiers++; break;
                    case BUILDER: builders++; break;
                    default: break;
                }
            }
        }
    }
    static int getRubble(RobotController rc, Direction d) {
        try {
            MapLocation loc = rc.getLocation().add(d);
            return rc.senseRubble(loc);
        }
        catch (GameActionException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
