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

            MapLocation loc25 = loc9.add(Helper.directions[0]);
            MapLocation loc26 = loc10.add(Helper.directions[0]);
            MapLocation loc27 = loc11.add(Helper.directions[0]);
            MapLocation loc28 = loc11.add(Helper.directions[1]);
            MapLocation loc29 = loc11.add(Helper.directions[2]);
            MapLocation loc30 = loc12.add(Helper.directions[2]);
            MapLocation loc31 = loc13.add(Helper.directions[2]);
            MapLocation loc32 = loc14.add(Helper.directions[2]);
            MapLocation loc33 = loc15.add(Helper.directions[2]);
            MapLocation loc34 = loc15.add(Helper.directions[3]);
            MapLocation loc35 = loc15.add(Helper.directions[4]);
            MapLocation loc36 = loc16.add(Helper.directions[4]);
            MapLocation loc37 = loc17.add(Helper.directions[4]);
            MapLocation loc38 = loc18.add(Helper.directions[4]);
            MapLocation loc39 = loc19.add(Helper.directions[4]);
            MapLocation loc40 = loc19.add(Helper.directions[5]);
            MapLocation loc41 = loc19.add(Helper.directions[6]);
            MapLocation loc42 = loc20.add(Helper.directions[6]);
            MapLocation loc43 = loc21.add(Helper.directions[6]);
            MapLocation loc44 = loc22.add(Helper.directions[6]);
            MapLocation loc45 = loc23.add(Helper.directions[6]);
            MapLocation loc46 = loc23.add(Helper.directions[7]);
            MapLocation loc47 = loc23.add(Helper.directions[0]);
            MapLocation loc48 = loc24.add(Helper.directions[0]);

            //produces a sufficiently large search space

            bestCoord = checkIfLowRubble(rc, loc1, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc2, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc3, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc4, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc5, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc6, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc7, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc8, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc9, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc10, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc11, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc12, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc13, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc14, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc15, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc16, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc17, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc18, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc19, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc20, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc21, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc22, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc23, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc24, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc25, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc26, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc27, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc28, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc29, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc30, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc31, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc32, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc33, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc34, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc35, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc36, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc37, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc38, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc39, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc40, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc41, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc42, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc43, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc44, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc45, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc46, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc47, bestCoord);
            bestCoord = checkIfLowRubble(rc, loc48, bestCoord);



            int actCDCurr = calculateActionCooldownAtSquare(rc, rc.getLocation());
            int bestCD = calculateActionCooldownAtSquare(rc, bestCoord);
            if (bestCD * 3 < actCDCurr) {
                if (rc.canTransform()&& !rc.getMode().equals(RobotMode.PORTABLE))
                    rc.transform();
                pathfinder = new BFPathing20(rc);
                pathfinder.bfPathToTarget(bestCoord);
            }
            else {
                if (rc.canTransform() && !rc.getMode().equals(RobotMode.TURRET))
                    rc.transform();
            }
        }
    }

    static int calculateActionCooldownAtSquare(RobotController rc, MapLocation loc)
            throws GameActionException{
        int rubbleAmount = rc.senseRubble(loc);
        int transmuteC = 10;
        return ((1 + rubbleAmount / 10) * transmuteC);
    }

    static MapLocation checkIfLowRubble(RobotController rc, MapLocation loc, MapLocation bestLoc) throws GameActionException {
        if (loc.x < GameConstants.MAP_MAX_WIDTH && loc.x > 0 && loc.y < GameConstants.MAP_MAX_HEIGHT &&
                loc.y > 0 && rc.senseRubble(loc) < rc.senseRubble(bestLoc) &&
                !rc.canSenseRobotAtLocation(loc)) {
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
    