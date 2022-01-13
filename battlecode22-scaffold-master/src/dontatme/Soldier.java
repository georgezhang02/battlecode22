package dontatme;

import battlecode.common.*;

public strictfp class Soldier {
    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    static Pathfinder pathfinder;

    static int state;
    public static void run(RobotController rc) throws GameActionException {
        pathfinder = new BFPathing20(rc);

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

        // Also try to move randomly.
        Direction dir = Helper.directions[Helper.rng.nextInt(Helper.directions.length)];
        if (rc.canMove(dir)) {
            rc.move(dir);
            System.out.println("I moved!");
        }
    }

    void attack(int attackType){

    }

    void defend(){

    }

    void explore(){

    }
}
