package dontatme;

import battlecode.common.*;

public strictfp class Soldier {

    public enum SoldierState {
        Rushing, Defending, Exploring, Pursuing, ArchonDefense, Anomaly
    }

    static SoldierState currentState = SoldierState.Exploring;

    static Pathfinder pathfinder;

    static RobotInfo[] enemies;
    static RobotInfo[] allies;

    static RobotController rc;

    static Communications.Command command;
    static int commandTimer = 0;
    static int currentPriority = 0;
    static MapLocation currentTarget;
    
    static MapLocation archonDef;

    public Soldier (RobotController rc) throws GameActionException {
        this.rc = rc;
        pathfinder = new BFPathing20(rc);
    }

    public void run() throws GameActionException {
        Communications.runStart(rc);

        Team opponent = rc.getTeam().opponent();
        enemies = rc.senseNearbyRobots(20, opponent);
        allies = rc.senseNearbyRobots(20, rc.getTeam());

        if(commandTimer <= 0){
            clearCommand();
        } else{
            commandTimer--;
        }

        readComms();

        Helper.updateEnemyLocations(rc, enemies);

        if(command != null){
            if(command.type == RobotType.ARCHON){  
                currentState = command.isAttack ? SoldierState.Rushing : SoldierState.ArchonDefense;
            } else {
                currentState = command.isAttack ? SoldierState.Pursuing : SoldierState.Defending;
            }
        } else {
            currentState = SoldierState.Exploring;
        }


        switch (currentState){
            case Rushing:
                offense(currentTarget, 0);
                break;
            case Pursuing:
                offense(currentTarget, 1);
                break;
            case Defending:
                defense(currentTarget, 6);
                break;
            case Exploring:
                explore();
                break;
            case ArchonDefense:
                defense(currentTarget, 10);
                break;
            case Anomaly:
                prepForAnomaly(AnomalyType.ABYSS);
                break;

        }
    }
        
    public static boolean currentlyAttacking() {
        if (command != null) {
            return command.isAttack;
        }
        return false;
    }

    static void clearCommand() {
        command = null;
        commandTimer = 0;
        currentPriority = 0;
        currentTarget = null;
    }

    static void setCommand(Communications.Command c) {
        command = c;
        commandTimer = Communications.getCommandCooldown(rc, c.type, c.isAttack);
        currentPriority = c.priority();
        currentTarget = c.location;
    }

    static void readComms() throws GameActionException {
        // find command with the highest priority (including current command)
        int maxPrio = currentPriority;
        
        Communications.Command[] attackCommands = Communications.getAttackCommands(rc);
        Communications.Command[] defendCommands = Communications.getDefenseCommand(rc);

        // for all attack commands
        for(int i = 0; i < attackCommands.length; i++){
            Communications.Command ac = attackCommands[i];
            // if location is valid
            if(ac.location.x < 60){
                // if command is a stop attacking command
                if(ac.type == null && ac.location.equals(currentTarget) && currentlyAttacking()){
                    // stop doing whatever we are oding
                    clearCommand();
                    return;
                // if its not a stop attacking command
                } else{
                    // get the command with the highest priority that is within our range
                    if(ac.priority() > maxPrio && Communications.inCommandRadius(rc, ac.type, ac.location, true)){
                        maxPrio = ac.priority();

                        // get priority command
                        setCommand(ac);
                    }
                }
            }
        }

        // for all defend commmand
        for(int i = 0; i < defendCommands.length; i++){
            Communications.Command dc = defendCommands[i];
            // if location is valid
            if(dc.location.x < 60){
                // if command is a stop defending command and we're defending
                if(dc.type == null && dc.location.equals(currentTarget) && !currentlyAttacking()){
                    // stop doing whatever we are doing
                    clearCommand();
                    return;
                // if its not a stop defending command
                } else{
                    // get the command with the highest priority that is within our range
                    if(dc.priority() > maxPrio && Communications.inCommandRadius(rc, dc.type, dc.location, false)){
                        maxPrio = dc.priority();

                        setCommand(dc);
                    }
                }
            }
        }
    }

    static void checkCurrentAttackingTarget() throws GameActionException {
        if(command != null && 
            command.type != null &&
            rc.canSenseLocation(command.location) && 
            currentlyAttacking() && 
            command.type.isBuilding()) {

            RobotInfo robot = rc.senseRobotAtLocation(command.location);
            if(robot == null || (robot.getType() != command.type && !robot.getTeam().isPlayer())){
                Communications.sendStopAttackCommand(rc, command.location);
            }
        }
    }

    static void offense(MapLocation target, int attackType) throws GameActionException {
        checkCurrentAttackingTarget();

        int enemyCount = 0;
        int allyCount = 1;

        MapLocation[] enemyPos = new MapLocation[5];

        for(RobotInfo robot:enemies){
            if(robot.getType() == RobotType.SOLDIER || robot.getType() == RobotType.WATCHTOWER){
                if(enemyCount < 5){
                    enemyPos[enemyCount] = robot.getLocation();
                }
                enemyCount++;
            }
        }
        for(RobotInfo robot: allies){
            if(robot.getType() == RobotType.SOLDIER){
                allyCount++;
            }
        }

        MapLocation ml = attack(attackType);

        if(enemyCount > 0){
            Communications.sendAttackCommand(rc, enemyPos[0], RobotType.SOLDIER);
        }
        if(enemyCount >= 1 && ml != null){
            move(pathfinder.pathAwayFrom(enemyPos));
        }
        else if(target != null && !pathfinder.targetWithinRadius(target, 6)){
            move(pathfinder.pathToTarget(target, false));
        } 
        else {
            if(ml!= null){
                currentTarget = ml;
            } else {
                clearCommand();
            }
        }

        if(rc.isActionReady()){
            ml = attack(attackType);
        }
    }

    static void defense(MapLocation target, int minDistance) throws GameActionException {
        int enemyCount = 0;
        int allyCount = 1;

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

        MapLocation ml = attack(1);

        if(2 * enemyCount >= 3 * allyCount){
            move(pathfinder.pathAwayFrom(enemyPos));
        }
        else if(target != null && !pathfinder.targetWithinRadius(target, minDistance)){
            move(pathfinder.pathToTarget(target, false));
        } else{
            if(ml== null){
                clearCommand();
            }
        }

        if(rc.isActionReady()){
            ml = attack(1);
        }
    }


    static void explore() throws GameActionException {
        if(!pathfinder.exploring){
            currentTarget = null;
        }

        int enemyCount = 0;
        int allyCount = 1;

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

        MapLocation ml = attack(1);

        if(enemyCount >=1){
            move(pathfinder.pathAwayFrom(enemyPos));
            if(ml != null){
                currentTarget = ml;
                currentState = SoldierState.Pursuing;
            }
        } else{
            move(pathfinder.pathToExplore());
            rc.setIndicatorLine(rc.getLocation(), pathfinder.explorer.target, 255,255,0);

            if(ml != null){
                currentTarget = ml;
                currentState = SoldierState.Pursuing;
            }
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
        if(attackLoc != null && rc.canAttack(attackLoc.getLocation())){
            rc.attack(attackLoc.getLocation());
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

    static void prepForAnomaly(AnomalyType anomalyType)
    {

    }
}
