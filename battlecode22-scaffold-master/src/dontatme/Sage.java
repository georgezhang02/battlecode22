package dontatme;

import battlecode.common.*;

public strictfp class Sage {
    public enum SageState {
        Rushing, Defending, Exploring, PURSUING, ArchonDefense, Anomaly, Healing
    }

    static SageState currentState = SageState.Exploring;

    static Pathfinder pathfinder;

    static RobotInfo[] enemies;
    static RobotInfo[] allies;

    static RobotController rc;

    static Communications.Command command;
    static int commandTimer = 0;
    static int currentPriority = 0;
    static MapLocation currentTarget;

    static MapLocation archonDef;

    static int combatCooldown = 0;

    static int enemyCount;
    static int allyCount;
    static int droidCount;
    static int buildingCount;


    public Sage (RobotController rc) throws GameActionException {
        this.rc = rc;
        pathfinder = new BFPathing34(rc);
    }

    public void run() throws GameActionException {
        Communications.runStart(rc);

        Team opponent = rc.getTeam().opponent();
        enemies = rc.senseNearbyRobots(RobotType.SAGE.visionRadiusSquared, opponent);
        allies = rc.senseNearbyRobots(RobotType.SAGE.visionRadiusSquared, rc.getTeam());

        combatCooldown--;

        MapLocation healingLoc = findHealingArchon();
        if(currentState == SageState.Healing){
            currentTarget = healingLoc;
        } else{
            if(commandTimer <= 0){
                clearCommand();
            } else{
                commandTimer--;
            }

            Helper.updateEnemyLocations(rc, enemies);
            readComms();
            if(command != null){
                if(command.type == RobotType.ARCHON){
                    currentState = command.isAttack ? SageState.Rushing : SageState.ArchonDefense;
                } else {
                    currentState = command.isAttack ? SageState.PURSUING : SageState.Defending;
                }
            } else {
                currentState = SageState.Exploring;
            }
        }

        switch (currentState){
            case Healing:
                healAt(currentTarget);
                rc.setIndicatorString("Healing");
                break;
            case Rushing:
                offense(currentTarget, 0);
                rc.setIndicatorString("Rushing ");
                break;
            case PURSUING:
                offense(currentTarget, 1);
                rc.setIndicatorString("Pursuing");
                break;
            case Defending:
                defense(currentTarget, 6);
                rc.setIndicatorString("Defending");
                break;
            case Exploring:
                explore();
                rc.setIndicatorString("Exploring");
                break;
            case ArchonDefense:
                defense(currentTarget, 10);
                rc.setIndicatorString("ArchonDefense");
                break;
            case Anomaly:
                rc.setIndicatorString("Anomaly");
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
        currentState = SageState.Exploring;
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
/*
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
        }*/


    }

    static MapLocation findHealingArchon() throws GameActionException {
        MapLocation ans = null;
        if(rc.getHealth() <= RobotType.SAGE.getMaxHealth(1) / 3){
            double lowestDist = 120;
            for(int i = 0; i< 4; i++){
                MapLocation archonLoc = Communications.getTeamArchonLocationByIndex(rc, i);

                if(archonLoc.x < 60){
                    double dist = Math.sqrt(rc.getLocation().distanceSquaredTo(archonLoc));
                    if(dist < lowestDist){
                        if(rc.getHealth() < 600 / dist){
                            lowestDist = dist;
                            ans = archonLoc;
                            currentState = SageState.Healing;
                        }
                    }
                }
            }
        }
        return ans;
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
                clearCommand();
            }
        }
    }

    static Direction lookForBetterSquare() throws GameActionException{
        for(Direction dir: Direction.allDirections()){
            if(rc.canMove(dir) && 1.5 * (rc.senseRubble(rc.getLocation().add(dir) ) + 10)
                    <= rc.senseRubble(rc.getLocation())+ 10){
                return dir;
            }
        }
        return Direction.CENTER;
    }


    static void offense(MapLocation target, int attackType) throws GameActionException {
        checkCurrentAttackingTarget();

        // check everything that you can see

        enemyCount = 0;
        allyCount = 1;

        MapLocation[] enemyPos = new MapLocation[5];

        int closestEnemy = 35;

        for(RobotInfo robot:enemies){
            if(isAttackableDroid(robot.getType())){
                int dist =  robot.getLocation().distanceSquaredTo(rc.getLocation());
                if(dist < closestEnemy){
                    closestEnemy = dist;
                    enemyPos[0] = robot.getLocation();
                }
                enemyCount++;
            }
            if(robot.getType() == RobotType.SAGE || robot.getType() == RobotType.SOLDIER){
                droidCount ++;
            } else if(robot.getType() == RobotType.WATCHTOWER || robot.getType() == RobotType.ARCHON){
                buildingCount ++;
            }
        }
        for(RobotInfo robot: allies){
            if(isAttackableDroid(robot.getType())){
                allyCount++;
            }
        }

        if(enemyCount > 0){
            if(commandTimer <= 0){
                Communications.sendAttackCommand(rc, enemyPos[0], RobotType.SOLDIER);
                commandTimer = Communications.getCommandCooldown(rc, RobotType.SOLDIER, true);
            }
            commandTimer = Communications.getCommandCooldown(rc, RobotType.SOLDIER, true);


            currentTarget = enemyPos[0];
        }

        // initial attack
        attack();

        // looking for best movement


        Direction dir = Direction.CENTER;



        if(enemyCount > 0 || !rc.isActionReady()){ // in combat
            if(enemyCount > 0){
                Communications.sendMoveToCommand(rc, rc.getLocation(), enemyCount );
            }
            if(!rc.isActionReady() &&  closestEnemy <= 20){ // action not ready
                dir = pathfinder.pathAwayFrom(enemyPos, 0); // kite
            } else if(enemyCount >= allyCount){
                dir = lookForBetterSquare();
            }  else {
                //action is ready, but you outnumber all opponents in your attack radius
                dir = pathfinder.pathToTargetGreedy(target, 0); // path to target close
            }
        } // travelling
        else if(target != null && !pathfinder.targetWithinRadius(target, 52)){
            move(pathfinder.pathToTarget(target, false)); // path to target from far away
        } else if(target != null && pathfinder.targetWithinRadius(target, 52)){
            dir = pathfinder.pathToTargetGreedy(target, 0); // path to target close
        }else{
            currentState = SageState.Exploring;
        }
        if(rc.canMove(dir) && rc.senseRubble(rc.getLocation().add(dir)) + 10
                <=  1.5 * (rc.senseRubble(rc.getLocation())+ 10) ){
            move(dir); // path to target from far away
        }


        if(rc.isActionReady()){
            attack();

            if(enemyPos[0] != null){
                Communications.sendAttackCommand(rc, enemyPos[0], RobotType.SOLDIER);
                currentTarget = enemyPos[0];
                currentState = SageState.PURSUING;
            }
        }
    }

    static void defense(MapLocation target, int minDistance) throws GameActionException {
        enemyCount = 0;
        allyCount = 1;

        int closestEnemy = 35;
        MapLocation[] enemyPos = new MapLocation[5];

        for(RobotInfo robot:enemies){
            if(isAttackableDroid(robot.getType())){
                int dist =  robot.getLocation().distanceSquaredTo(rc.getLocation());
                if(dist < closestEnemy){
                    closestEnemy = dist;
                    enemyPos[0] = robot.getLocation();
                }
                enemyCount++;
            }
            if(robot.getType() == RobotType.SAGE || robot.getType() == RobotType.SOLDIER){
                droidCount ++;
            } else if(robot.getType() == RobotType.WATCHTOWER || robot.getType() == RobotType.ARCHON){
                buildingCount ++;
            }
        }
        for(RobotInfo robot: allies){
            if(isAttackableDroid(robot.getType())){
                allyCount++;
            }
        }

        attack();

        if(2 * enemyCount >= 3 * allyCount){
            move(pathfinder.pathAwayFrom(enemyPos));
        }
        else if(target != null && !pathfinder.targetWithinRadius(target, minDistance)){
            move(pathfinder.pathToTarget(target, false));
        } else{
            if(enemies == null || enemies.length == 0 || enemies[0]== null){
                clearCommand();
            }
        }

        if(rc.isActionReady()){
            attack();
        }
    }


    static void explore() throws GameActionException {
        if(!pathfinder.exploring){
            currentTarget = null;
        }

        enemyCount = 0;
        allyCount = 1;
        int closestEnemy = 35;
        MapLocation[] enemyPos = new MapLocation[5];

        for(RobotInfo robot:enemies){
            if(isAttackableDroid(robot.getType())){
                int dist =  robot.getLocation().distanceSquaredTo(rc.getLocation());
                if(dist < closestEnemy){
                    closestEnemy = dist;
                    enemyPos[0] = robot.getLocation();
                }
                enemyCount++;
            }
            if(robot.getType() == RobotType.SAGE || robot.getType() == RobotType.SOLDIER){
                droidCount ++;
            } else if(robot.getType() == RobotType.WATCHTOWER || robot.getType() == RobotType.ARCHON){
                buildingCount ++;
            }
        }
        for(RobotInfo robot: allies){
            if(isAttackableDroid(robot.getType())){
                allyCount++;
            }
        }

       attack();


        if(enemyCount >=1){
            move(pathfinder.pathAwayFrom(enemyPos));
            Communications.sendAttackCommand(rc, enemyPos[0], RobotType.SOLDIER);
            currentTarget = enemyPos[0];
            currentState = SageState.PURSUING;
            combatCooldown = 3;
        } else{
            move(pathfinder.pathToExplore());
        }

        if(rc.isActionReady()){
            attack();

            if(enemyPos[0] != null){
                Communications.sendAttackCommand(rc, enemyPos[0], RobotType.SOLDIER);
                currentTarget = enemyPos[0];
                currentState = SageState.PURSUING;
            }
        }
    }


    static void attack() throws GameActionException {
        if(rc.isActionReady()){
            if(enemyCount > 0){

                rc.setIndicatorString(droidCount+" "+buildingCount);
                if (droidCount <= 2) {
                    // System.out.println("RAW ATTACK!");
                    rawAttack(1);
                } else if  (droidCount == 0 && buildingCount <= 1){
                    rawAttack(3);
                }
                else if(droidCount > 2 || buildingCount > 1 ||
                        buildingCount <= droidCount ||  (allyCount == 1 && enemyCount > 0)){
                    if(droidCount >= buildingCount){
                        rc.envision(AnomalyType.CHARGE);
                    } else{
                        rc.envision(AnomalyType.FURY);
                    }
                }
            }
        }
    }

    void healAt(MapLocation target) throws GameActionException {

        if(rc.getHealth() >= RobotType.SAGE.getMaxHealth(1) * 0.8){
            currentState = SageState.Exploring;
            clearCommand();
            this.run();
        } else{
            rc.setIndicatorString("Healing");

            // check everything that you can see
            int enemyCount = 0;
            int allyCount = 1;

            MapLocation[] enemyPos = new MapLocation[5];

            int closestEnemy = 35;

            for(RobotInfo robot:enemies){
                if(isAttackableDroid(robot.getType())){
                    int dist =  robot.getLocation().distanceSquaredTo(rc.getLocation());
                    if(dist < closestEnemy){
                        closestEnemy = dist;
                        enemyPos[0] = robot.getLocation();
                    }
                    enemyCount++;
                }
            }
            for(RobotInfo robot: allies){
                if(isAttackableDroid(robot.getType())){
                    allyCount++;
                }
            }

            if(enemyCount > 0){
                if(commandTimer <= 0){
                    Communications.sendAttackCommand(rc, enemyPos[0], RobotType.SOLDIER);
                    commandTimer = Communications.getCommandCooldown(rc, RobotType.SOLDIER, true);
                }


                currentTarget = enemyPos[0];
            }

            // initial attack
            attack();

            // looking for best movement
            Direction dir = Direction.CENTER;

            if(enemyCount > 0 || !rc.isActionReady() && pathfinder.targetWithinRadius(target, 20)){ // in combat
                if(!rc.isActionReady() ){ // action not ready
                    dir = pathfinder.pathAwayFrom(enemyPos, 0); // kite
                } else if(enemyCount >= allyCount){
                    dir = lookForBetterSquare();
                }  else {
                    if(enemyPos[0]!= null && target != null){
                        dir = pathfinder.pathToTargetGreedy(target, 0); // path to target close
                    }
                }
            } // travelling
            else if(target != null && !pathfinder.targetWithinRadius(target, 8)){
                move(pathfinder.pathToTarget(target, false)); // path to target from far away
            } else{
                //move(Direction.allDirections()[(int) (10000 * Math.random()) % 9]);

                // possibly move randomly to create space?
            }
            if(rc.isMovementReady()){
                if(rc.canMove(dir) && rc.senseRubble(rc.getLocation().add(dir)) + 10
                        <= 1.5 * (rc.senseRubble(rc.getLocation())+ 10) ){
                    move(dir); // path to target from far away
                }
            }
            if(rc.isActionReady()){
                attack();
            }


            currentState = SageState.Healing;
        }
    }

    static MapLocation rawAttack(int attackType) throws GameActionException {
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
            if(rc.getLocation().distanceSquaredTo(robot.getLocation()) <= RobotType.SAGE.actionRadiusSquared){
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

    static Boolean isAttackableDroid(RobotType t) {
        return t == RobotType.SOLDIER || t == RobotType.WATCHTOWER || t == RobotType.SAGE;
    }
}
