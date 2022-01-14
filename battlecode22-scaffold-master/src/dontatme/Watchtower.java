package dontatme;

import battlecode.common.*;

import java.awt.*;

public strictfp class Watchtower {
    /**
     * Run a single turn for a Watchtower.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    //number of droids killed by watchtower
    static RobotController rc;
    static int attackCount = 0;
    static int turnCount = 0;
    static RobotInfo[] enemies;

    public static void run(RobotController robotController) throws GameActionException {
        if(rc == null) rc = robotController;
        Team opponent = rc.getTeam().opponent();
        enemies = rc.senseNearbyRobots(34, opponent);

        //attack
        if (rc.isActionReady()) {
            RobotInfo ri= getAttack(3, 1, 0, 2); //droids, buildings, archons miner prio
            if (ri != null) {
                attackCount++;
                rc.attack(ri.location);
            }
        }

        turnCount++;
    }

    static RobotInfo getAttack(int prio1, int prio2, int prio3, int prio4) throws GameActionException {
        RobotInfo[] ml = new RobotInfo[4];
        int[] minHealth = {2000,2000,2000,2000};

        for(RobotInfo robot : enemies){
            RobotType type = robot.getType();
            int id = robot.getHealth();
            if(rc.getLocation().distanceSquaredTo(robot.location) <= 20){
                if(type.isBuilding()){
                    if(type.equals(RobotType.ARCHON)){ // archons
                        if(id < minHealth[0]){
                            ml[0] = robot;
                            minHealth[0] = id;
                        }
                    } else{
                        if(id < minHealth[1]){ // other buildings
                            ml[1] = robot;
                            minHealth[1] = id;
                        }
                    }
                } else{
                    if(type.equals(RobotType.MINER)){ // miners
                        if(id < minHealth[2]){
                            ml[2] = robot;
                            minHealth[2] = id;
                        }
                    } else {
                        if(id < minHealth[3]){ // other droids
                            ml[3] = robot;
                            minHealth[3] = id;
                        }
                    }
                }
            }

        }

        if(ml[prio1] != null){
            return ml[prio1];
        }
        if(ml[prio2] != null){
            return ml[prio2];
        }
        if(ml[prio3] != null){
            return ml[prio3];
        }
        return ml[prio4];
    }
}
