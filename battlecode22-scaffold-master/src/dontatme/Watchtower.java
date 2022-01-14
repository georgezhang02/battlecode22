package dontatme;

import battlecode.common.*;

import java.awt.*;

public strictfp class Watchtower {
    /**
     * Run a single turn for a Watchtower.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    //number of droids killed by watchtower
    static int attackCount = 0;

    public static void run(RobotController rc) throws GameActionException {
        int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);

        //attack
        int position = 0;
        if (rc.isActionReady() && enemies.length > 0) {
            if (rc.canAttack(enemies[position].getLocation())) {
                attackCount++;
                rc.attack(enemies[position].getLocation());
            }
        }
    }
}
