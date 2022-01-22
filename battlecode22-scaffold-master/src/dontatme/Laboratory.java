package dontatme;

import battlecode.common.*;

import java.awt.*;
import java.nio.file.Path;

public strictfp class Laboratory {
    /**
     * Run a single turn for a Laboratory.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    static Pathfinder pathfinder;
    public static void run(RobotController rc) throws GameActionException {
        Communications.runStart(rc);
        moveIfHighRubble(rc);
        transmuteIfPossible(rc);
    }
    static void moveIfHighRubble(RobotController rc) throws GameActionException {
        //formula for cooldown = (1+r/10)*c
        MapLocation newLoc = null;
        if (rc.senseRubble(rc.getLocation()) > 10) {
            //sense rubble within the vision radius
            //first check the coordinate distances
            MapLocation bestCoord = rc.getLocation();

            MapLocation loc1 = rc.getLocation().add(Helper.directions[0]);
            MapLocation loc2 = rc.getLocation().add(Helper.directions[1]);
            MapLocation loc3 = rc.getLocation().add(Helper.directions[2]);
            MapLocation loc4 = rc.getLocation().add(Helper.directions[3]);
            MapLocation loc5 = rc.getLocation().add(Helper.directions[4]);
            MapLocation loc6 = rc.getLocation().add(Helper.directions[5]);
            MapLocation loc7 = rc.getLocation().add(Helper.directions[6]);
            MapLocation loc8 = rc.getLocation().add(Helper.directions[7]);

            MapLocation loc9 = loc1.add(Helper.directions[0]);
            MapLocation loc10 = loc2.add(Helper.directions[0]);
            MapLocation loc11 = loc2.add(Helper.directions[1]);
            MapLocation loc12 = loc2.add(Helper.directions[2]);
            MapLocation loc13 = loc3.add(Helper.directions[2]);
            MapLocation loc14 = loc4.add(Helper.directions[2]);
            MapLocation loc15 = loc4.add(Helper.directions[3]);
            MapLocation loc16 = loc4.add(Helper.directions[4]);
            MapLocation loc17 = loc5.add(Helper.directions[4]);
            MapLocation loc18 = loc6.add(Helper.directions[4]);
            MapLocation loc19 = loc6.add(Helper.directions[5]);
            MapLocation loc20 = loc6.add(Helper.directions[6]);
            MapLocation loc21 = loc7.add(Helper.directions[6]);
            MapLocation loc22 = loc8.add(Helper.directions[6]);
            MapLocation loc23 = loc8.add(Helper.directions[7]);
            MapLocation loc24 = loc8.add(Helper.directions[0]);


            bestCoord = checkIfLowRubble(rc, rc.getLocation().add(Helper.directions[0]),
                    bestCoord);
            bestCoord = checkIfLowRubble(rc, rc.getLocation().add(Helper.directions[1]),
                    bestCoord);
            bestCoord = checkIfLowRubble(rc, rc.getLocation().add(Helper.directions[2]),
                    bestCoord);
            bestCoord = checkIfLowRubble(rc, rc.getLocation().add(Helper.directions[3]),
                    bestCoord);
            bestCoord = checkIfLowRubble(rc, rc.getLocation().add(Helper.directions[4]),
                    bestCoord);
            bestCoord = checkIfLowRubble(rc, rc.getLocation().add(Helper.directions[5]),
                    bestCoord);
            bestCoord = checkIfLowRubble(rc, rc.getLocation().add(Helper.directions[6]),
                    bestCoord);
            bestCoord = checkIfLowRubble(rc, rc.getLocation().add(Helper.directions[7]),
                    bestCoord);

            bestCoord = checkIfLowRubble(rc, rc.getLocation() ;


            if (rc.senseRubble(bestCoord) <= rc.senseRubble(rc.getLocation()) - 10) {
                if (rc.canTransform()&& !rc.getMode().equals(RobotMode.PORTABLE))
                    rc.transform();
                pathfinder = new BFPathing20(rc);
                pathfinder.bfPathToTarget(bestCoord);
            }
        }
    }

    static MapLocation checkIfLowRubble(RobotController rc, MapLocation loc, MapLocation bestLoc) throws GameActionException {
        if (rc.senseRubble(loc) < rc.senseRubble(bestLoc)) {
            return loc;
        }
        else {
            return bestLoc;
        }
    }

    static void transmuteIfPossible(RobotController rc)  throws GameActionException {
        if (rc.canTransmute()) {
            rc.transmute();
        }
    }
}
    