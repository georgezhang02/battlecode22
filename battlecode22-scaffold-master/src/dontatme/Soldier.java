package dontatme;

import battlecode.common.*;

import java.awt.*;
import java.util.Random;

public strictfp class Soldier {
    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    static Pathfinder pathfinder;

    static int state = -1;
    static RobotInfo[] enemies;
    static RobotInfo[] allies;

    static MapLocation curTarget;
    static MapLocation archonDef;

    static RobotController rc;

    static boolean rusher;

    static Communications.Command command;
    static boolean attacking;
    static int commandTimer = 0;
    static int curPrio = -1;

    static Random random = new Random();



    static Direction[] directions = {
            Direction.NORTH,
            Direction.WEST,
            Direction.EAST,
            Direction.SOUTH,
            Direction.NORTHWEST,
            Direction.NORTHEAST,
            Direction.SOUTHWEST,
            Direction.SOUTHEAST

    };


    public Soldier (RobotController rc) throws GameActionException {
        this.rc = rc;
        pathfinder = new BFPathing20(rc);

        double d = Math.random();


        if( d> .3){
            rusher = false;
            state = 2;
        } else{
            rusher = true;
            int x = (int) (2 * Math.random()) ;
            int y = (int) (2 * Math.random()) ;

            if(2 * Math.random() >= 1){
                x = -x;
            }
            if(2 * Math.random() >= 1){
                y = -y;
            }
            x = rc.getLocation().x + x;
            y = rc.getLocation().y + y;

            x = Math.max(0, Math.min(x, rc.getMapWidth()-1));
            y = Math.max(0, Math.min(y, rc.getMapHeight()-1));

            curTarget = new MapLocation(x, y);
            rc.setIndicatorString(curTarget.toString());



            state = 5;
        }
    }

    public void run() throws GameActionException {
        Communications.runStart(rc);
        if(commandTimer <= 0){
            curPrio = -1;
            command = null;
        } else{
            commandTimer--;
        }

        readComms();
        Team opponent = rc.getTeam().opponent();
        enemies = rc.senseNearbyRobots(20, opponent);
        allies = rc.senseNearbyRobots(20, rc.getTeam());

        if(command != null && curPrio != -1){

            curTarget = command.location;
            if(command.type == RobotType.ARCHON){
                if(attacking){
                    state = 0;
                    rusher = false;
                } else{
                    state = 5;
                }
            } else {
                if(attacking){
                    state = 3;
                } else {
                    state = 1;
                }
            }




        }


        switch (state){
            case 0: // Rushing Enemy Archon
                offense(curTarget, 0);
                break;
            case 1: //Defending
                defense(curTarget, 6);
                break;
            case 2: //Exploring
                explore();
                break;
            case 3: //Pursuing
                offense(curTarget, 1);
                break;

            case 5: // Defending Own Archon
                defense(curTarget, 10);
                break;
            case 6: //Prep for anomaly
                prepForAnomaly(AnomalyType.ABYSS);
                break;

        }
    }

    static void readComms() throws GameActionException {
        Communications.Command[] attackCommands = Communications.getAttackCommands(rc);

        int count = 0;

        int maxPrio = curPrio;
        int curFollow = -1;
        int index;
        for(index = 0; index<attackCommands.length; index++){
            Communications.Command ac = attackCommands[index];
            if(ac.location.x < 60){


                if(ac.type == null && ac.location.equals(curTarget) && attacking){
                    curPrio = -1;
                    curFollow = -1;
                    attacking = false;
                    commandTimer = 0;
                    return;
                } else{

                    int priority = Communications.getCommandPrio(ac.type, true);
                    if(priority >= maxPrio && Communications.inCommandRadius(rc, ac.type, ac.location, true)){

                            maxPrio = priority;
                            curFollow = index;


                    }
                }
            }


        }

        Communications.Command[] defendCommands = Communications.getDefenseCommand(rc);

        for(; index<defendCommands.length + attackCommands.length; index++){
            Communications.Command dc = defendCommands[index - attackCommands.length];
            if(dc.location.x != 61) {
                count ++;
                if (dc.type == null && dc.location.equals(curTarget) && !attacking) {
                    curPrio = -1;
                    curFollow = -1;
                    attacking = false;
                    commandTimer = 0;
                    return;
                } else {
                    int priority = Communications.getCommandPrio(dc.type, false);
                    if(!rusher || dc.type != RobotType.MINER){
                        if (priority >= maxPrio && Communications.inCommandRadius(rc, dc.type, dc.location, false)) {
                            maxPrio = priority;
                            curFollow = index;
                        }
                    }

                }
            }

        }

        rc.setIndicatorString(count+" ");


        if(curFollow != -1 && curFollow < attackCommands.length){
            command = attackCommands[curFollow];
            attacking = true;
            commandTimer = Communications.getCommandCooldown(rc, command.type, true);
            curPrio = maxPrio;

        } else if (curFollow != -1){
            curFollow -= attackCommands.length;
            command = defendCommands[curFollow];
            attacking = false;
            commandTimer = Communications.getCommandCooldown(rc, command.type, false);
            curPrio = maxPrio;
        }

    }





    static void prepForAnomaly(AnomalyType anomalyType)
    {

    }
    static void offense(MapLocation target, int attackType) throws GameActionException {
        int enemyCount = 0;
        int allyCount = 0;

        MapLocation[] enemyPos = new MapLocation[5];

        for(RobotInfo robot:enemies){
            if(robot.getType() == RobotType.SOLDIER || robot.getType() == RobotType.WATCHTOWER){
                enemyCount++;
                if(enemyCount < 5){
                    enemyPos[enemyCount] = robot.getLocation();
                }
            }
        }
        for(RobotInfo robot: allies){
            if(robot.getType() == RobotType.SOLDIER){
                allyCount++;
            }
        }
        if(enemyCount >= 2 * (allyCount + 1) && enemyCount > 1){
            move(pathfinder.pathAwayFrom(enemyPos));
            attack(attackType);
        }
        else if(target != null && !pathfinder.targetWithinRadius(target, 6)){
            move(pathfinder.pathToTarget(target, false));
            attack(attackType);

        }  else{

            MapLocation ml = attack(attackType);
            if(ml!= null){

                curTarget = ml;
            } else{
                attacking = false;
                state = 2;
            }
        }


    }

    static void defense(MapLocation target, int minDistance) throws GameActionException {
        //rc.setIndicatorString(state + " defense "+ curTarget.toString());
        if(target != null && !pathfinder.targetWithinRadius(target, minDistance)){
            move(pathfinder.pathToTarget(target, false));
        } else{
            int start = (int)(8 * Math.random());
            Direction dir = Direction.CENTER;
            for(int i = 0 ; i < 8 && dir == Direction.CENTER; i++){
                if(rc.canMove(directions[(start+i) % 8])) {
                    if (rc.senseRubble(rc.getLocation().add(directions[(start+i) % 8])) -
                            rc.senseRubble(rc.getLocation()) < 10) {
                        dir = directions[(start+i) % 8];
                    }
                }
            }
            move(dir);

            MapLocation ml = attack(1);
            if(ml== null && !rusher){
                state = 2;
            }
        }
    }


    static void explore() throws GameActionException {


        if(!pathfinder.exploring){
            curTarget = null;
        }

        move(pathfinder.pathToExplore());
        rc.setIndicatorLine(rc.getLocation(), pathfinder.explorer.target, 255,255,0);

        MapLocation ml = attack(1);

        if(ml != null){
            rc.setIndicatorString("explore "+ (command == null)+" "+state);
            curTarget = ml;
            state = 3;
        }
    }

    static MapLocation attack(int attackType) throws GameActionException {
        RobotInfo attackLoc;

        if(attackType == 0){ // attack all (droids, buildings, archons, miners)
            attackLoc = getAttack(3, 1, 0, 2);
        } else if(attackType == 1){ //attack droids (droids, miners, buildings, archons)
            attackLoc = getAttack(3, 2, 1, 0);
        }  else if (attackType == 3){// attack army (buidings, droids, miners, archons))
            attackLoc = getAttack(1, 3, 2, 0);
        }  else { // attack eco (miners, buildings, droids, archons)
            attackLoc = getAttack(2, 1, 3, 0);
        }
        if(attackLoc!= null && rc.canAttack(attackLoc.getLocation())){
            rc.attack(attackLoc.getLocation());
            if(attackLoc.getType().equals(RobotType.ARCHON) && !rc.canSenseRobot(attackLoc.getID())){
                Communications.setEnemyArchonLocation(rc, attackLoc.getID(), new MapLocation(61, 61));
            }
        }
        if(attackLoc != null){
            return attackLoc.getLocation();
        }
        return null;
    }

    static RobotInfo getAttack(int prio1, int prio2, int prio3, int prio4) throws GameActionException {
        RobotInfo[] ml = new RobotInfo[4];
        int[] minHealth = {2000,2000,2000,2000};
        int[] maxID = {0,0,0,0};

        for(RobotInfo robot : enemies){
            RobotType type = robot.getType();
            int health = robot.getHealth();
            int id = robot.getID();
            if(rc.getLocation().distanceSquaredTo(robot.getLocation()) <= 13){
                if(type.isBuilding()){
                    if(type.equals(RobotType.ARCHON)){
                        if(health < minHealth[0]){
                            ml[0] = robot;
                            minHealth[0] = health;
                            Communications.setEnemyArchonLocation(rc, robot.getID(), robot.getLocation());
                        } else if(health == minHealth[0] && id > maxID[0]){
                            ml[0] = robot;
                            maxID[0] = id;
                        }
                    } else{
                        if(health < minHealth[1]){
                            ml[1] = robot;
                            minHealth[1] = health;
                        }else if(health == minHealth[1] && id > maxID[1]){
                            ml[1] = robot;
                            maxID[1] = id;
                        }
                    }
                } else{
                    if(type.equals(RobotType.MINER)){
                        if(health < minHealth[2]){
                            ml[2] = robot;
                            minHealth[2] = health;
                        }else if(health == minHealth[2] && id > maxID[2]){
                            ml[2] = robot;
                            maxID[2] = id;
                        }
                    } else {
                        if(health < minHealth[3]){
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

    static void move(Direction dir) throws GameActionException {
        if(dir != null && rc.canMove(dir)){
            rc.move(dir);
            pathfinder.explorer.updateVisited();;
        }
    }


}
