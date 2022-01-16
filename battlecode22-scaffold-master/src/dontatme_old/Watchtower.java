package dontatme_old;

import battlecode.common.*;

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
        int[] maxID = {0,0,0,0};

        for(RobotInfo robot : enemies){
            RobotType type = robot.getType();
            int health = robot.getHealth();
            int id = robot.getID();
            if(rc.getLocation().distanceSquaredTo(robot.location) <= 13){
                if(type.isBuilding()){
                    if(type.equals(RobotType.ARCHON)){ // Archon prio
                        if(health < minHealth[0]){ // focus low health targets
                            ml[0] = robot;
                            minHealth[0] = health;
                        } else if(health == minHealth[0] && id > maxID[0]){ // ties broken by max  ID
                            ml[0] = robot;
                            maxID[0] = id;
                        }
                    } else{ // Building prio
                        if(health < minHealth[1]){
                            ml[1] = robot;
                            minHealth[1] = health;
                        }else if(health == minHealth[1] && id > maxID[1]){
                            ml[1] = robot;
                            maxID[1] = id;
                        }
                    }
                } else{
                    if(type.equals(RobotType.MINER)){// Miner Prio
                        if(health < minHealth[2]){
                            ml[2] = robot;
                            minHealth[2] = health;
                        }else if(health == minHealth[2] && id > maxID[2]){
                            ml[2] = robot;
                            maxID[2] = id;
                        }
                    } else {
                        if(health < minHealth[3]){// droid Prio
                            ml[3] = robot;
                            minHealth[3] = health;
                        }else if(health == minHealth[3] && id > maxID[3]){
                            ml[3] = robot;
                            maxID[3] = id;
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
