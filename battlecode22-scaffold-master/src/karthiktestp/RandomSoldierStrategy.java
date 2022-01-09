package karthiktestp;

import battlecode.common.*;

import java.awt.*;

strictfp class RandomSoldierStrategy {
    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static int addY = 0;
    static void runSoldier(RobotController rc) throws GameActionException {
        // Try to attack someone
        int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);
        if (enemies.length > 0) {
            MapLocation toAttack = enemies[0].location;
            if (rc.canAttack(toAttack)) {
                rc.attack(toAttack);
            }
        }

        //use decision-making to identify two potential areas of enemy archons
        // Also try to move randomly.
        //sense the enemy archon positions (roughly), and make a beeline for there, given our positions
        //assumes reflections
        Direction dir = RobotPlayer.directions[RobotPlayer.rng.nextInt(RobotPlayer.directions.length)];
        if (rc.canMove(dir)) {
            rc.move(dir);
            //System.out.println("I moved!");
        }
    }
}
