package dontatme;

import battlecode.common.*;

public strictfp class Sage extends Soldier {

    public static final int CHARGE_THRESHOLD = 2;
    public static final int SAGE_RANGE = 25;

    public Sage(RobotController rc) throws GameActionException {
        super(rc);
    }

    /**
     * Run a single turn for a Sage.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    public void run(RobotController rc) throws GameActionException {
        int enemyCount = 0;

        Team opponent = rc.getTeam().opponent();
        enemies = rc.senseNearbyRobots(SAGE_RANGE, opponent);

        for(RobotInfo robot:enemies){
            if(robot.getType() == RobotType.SOLDIER || robot.getType() == RobotType.WATCHTOWER){
                enemyCount++;
            }
        }

        if (enemyCount > CHARGE_THRESHOLD && rc.canEnvision(AnomalyType.CHARGE)) {
            rc.envision(AnomalyType.CHARGE);
        }
        
        // run soldier class
        super.run();
    }
}
