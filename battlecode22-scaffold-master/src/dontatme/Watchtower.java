package dontatme;

import battlecode.common.*;

import java.awt.*;

public strictfp class Watchtower {
    /**
     * Run a single turn for a Watchtower.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    //number of droids killed by watchtower
    static int killCount = 0;

    public static void run(RobotController rc) throws GameActionException {
        int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);

        //attack
        int counter = 0;
        while (rc.isActionReady() && enemies.length > 0) {
            MapLocation toAttack = enemies[counter].location;
            boolean killed = false;
            if (rc.canAttack(toAttack)) {
                killed = checkAttackKilled(rc, enemies[0]);
                rc.attack(toAttack);
            }
            if (killed) {
                killCount++;
                counter++;
            }
            //don't increment counter if the enemy is not killed -> try and attack them again
        }
    }
    static boolean checkAttackKilled(RobotController rc, RobotInfo enemy)
            throws GameActionException {

        //does the damage of my attack kill the enemy I targeted
        return rc.getType().getDamage(rc.getLevel()) > enemy.getHealth();
    }
}
