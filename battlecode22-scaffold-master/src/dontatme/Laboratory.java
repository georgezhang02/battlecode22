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
    