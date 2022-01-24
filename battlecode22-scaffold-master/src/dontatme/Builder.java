package dontatme;

import battlecode.common.*;

import java.awt.*;
import java.util.Map;

public strictfp class Builder {

    //checks if the builder has moved towards the center already
    static Pathfinder pathfinder;


    static MapLocation curTarget;
    static int moves = 0;
    static int turn = 0;

    static int labCooldown = 0;
    static boolean startBuilding = false;

    static Direction[]buildDir = new Direction[]{Direction.SOUTH, Direction.NORTHEAST, Direction.NORTHWEST,
            Direction.SOUTHWEST, Direction.SOUTH, Direction.SOUTHEAST, Direction. NORTHEAST, Direction.NORTHEAST,
            Direction.NORTHWEST, Direction.NORTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST};
    public static void run(RobotController rc) throws GameActionException {
        turn++;

        if(pathfinder == null){
            pathfinder = new BFPathing20(rc);
        }


        Communications.runStart(rc);

        int[] units = Communications.getUnitCounts(rc);

        // deciding to rush
        int minerCount = units[0];
        int soldierCount = units[1];
        int builderCount = units[2];

        int labCount = units[3];
        int sageCount = units[4];



        RobotInfo[] robots = rc.senseNearbyRobots();

        //detect if there's a nearby archon and fix it if it's low health
        /*MapLocation [] nearbyArchons = findNearbyArchons(rc, robots);
        if (nearbyArchons[0] != null) { //there is a nearby archon
            int lowestHealthArchon = findLowerHealthArchon(rc, nearbyArchons);
            if (lowestHealthArchon != -1) {
                if (rc.canRepair(nearbyArchons[lowestHealthArchon])) {
                    rc.repair(nearbyArchons[lowestHealthArchon]);
                }
                else {
                    pathfinder.bfPathToTarget(nearbyArchons[lowestHealthArchon]);
                }
            }
        }*/

        int maxLab = (int)(2 * (.3+ 2 * ((rc.getMapWidth() * rc.getMapHeight())/ 3600.0)));
        maxLab = Math.max(Math.min(maxLab, 4), 1);

        MapLocation toRepair = findBuildingToRepair(rc, robots);
        rc.setIndicatorString("lab cd: " + labCooldown);
        if(toRepair!= null){
            if(rc.getLocation().distanceSquaredTo(toRepair) > 5){
                move(rc, pathfinder.pathToTarget(toRepair, false));
            } else if(rc.canRepair(toRepair)){
                rc.repair(toRepair);
            }
        } else if(labCount < 1){
            if(robots.length > 1
                    && moves < 5
                    && (curTarget== null || !pathfinder.targetWithinRadius(curTarget, 20))){
                curTarget = getClosestCorner(rc);
                move(rc, pathfinder.pathToTarget(curTarget, false));
            }
            else if (robots.length > 3  && moves < 10){
                int index = 0;
                MapLocation[]nearby = new MapLocation[10];
                for(int i = 0; i< robots.length && index < 10; i++){

                    nearby[index++] = robots[i].getLocation();
                }
                move(rc, pathfinder.pathAwayFrom(nearby));
            } else{
                Direction better = lookForBetterSquare(rc);

                rc.setIndicatorString("building");

                if(moves < 10 && better != null){
                    move(rc, better);
                    moves += 4;
                } else{
                    Communications.setBuildingLab(rc);

                    if(rc.isActionReady() && rc.getTeamLeadAmount(rc.getTeam()) >= 180){

                        Direction dir = findDirLowestRubble(rc, rc.getLocation());
                        if(dir != null && rc.canBuildRobot(RobotType.LABORATORY, dir)){
                            Communications.setNotBuildingLab(rc);
                            rc.buildRobot(RobotType.LABORATORY, dir);
                            labCooldown = 100;
                        }
                    }
                }

            }
        } else if (labCount < maxLab) {
            if (startBuilding) {
                Communications.setBuildingLab(rc);
                if(rc.isActionReady() && rc.getTeamLeadAmount(rc.getTeam()) >= 180){

                    Direction dir = findDirLowestRubble(rc, rc.getLocation());
                    if(dir != null && rc.canBuildRobot(RobotType.LABORATORY, dir)){
                        Communications.setNotBuildingLab(rc);
                        rc.buildRobot(RobotType.LABORATORY, dir);
                        startBuilding = false;
                    }
                }
            }
            if (labCooldown > 0) {
                labCooldown--;
                startBuilding = true;
            } else {
                labCooldown = 100;
            }
        }
    }

    static MapLocation findBuildingToRepair(RobotController rc, RobotInfo[]robotInfo) {

        MapLocation ans = null;
        int dist = 1000000;
        for(int i = 0; i< robotInfo.length; i++){
            if(robotInfo[i].getLocation().distanceSquaredTo(rc.getLocation()) < dist){
                if(robotInfo[i].getType()== RobotType.LABORATORY && robotInfo[i].getHealth() < 100) {
                    dist = robotInfo[i].getLocation().distanceSquaredTo(rc.getLocation());
                    ans = robotInfo[i].getLocation();
                }
                else if(robotInfo[i].getType()== RobotType.WATCHTOWER && robotInfo[i].getHealth() < 600) {
                    dist = robotInfo[i].getLocation().distanceSquaredTo(rc.getLocation());
                    ans = robotInfo[i].getLocation();
                }
                else if(robotInfo[i].getType()== RobotType.ARCHON && robotInfo[i].getHealth() < 150) {
                    dist = robotInfo[i].getLocation().distanceSquaredTo(rc.getLocation());
                    ans = robotInfo[i].getLocation();
                }

            }
        }
        return ans;
    }

    private static MapLocation getClosestCorner(RobotController rc){
        MapLocation ans = new MapLocation(61, 61);
        int dist = 3600;

        MapLocation curPos = rc.getLocation();
        MapLocation test = new MapLocation(0, 0);

        if(curPos.distanceSquaredTo(test) < dist){
            ans = test;
            dist = curPos.distanceSquaredTo(test);
        }
        test = new MapLocation(rc.getMapWidth()-1, 0);

        if(curPos.distanceSquaredTo(test) < dist){
            ans = test;
            dist = curPos.distanceSquaredTo(test);
        }
        test = new MapLocation(0, rc.getMapHeight()-1);

        if(curPos.distanceSquaredTo(test) < dist){
            ans = test;
            dist = curPos.distanceSquaredTo(test);
        }
        test = new MapLocation(rc.getMapWidth()-1, rc.getMapHeight()-1);

        if(curPos.distanceSquaredTo(test) < dist){
            ans = test;
            dist = curPos.distanceSquaredTo(test);
        }

        return ans;

    }

    static void move(RobotController rc, Direction dir) throws GameActionException {
        if(rc.isMovementReady()){
            moves++;
        }
        if(dir != null && rc.canMove(dir)){
            rc.move(dir);
            pathfinder.explorer.updateVisited();;
        }
    }

    static Direction lookForBetterSquare(RobotController rc) throws GameActionException{
        for(Direction dir: Direction.allDirections()){
            if(rc.canMove(dir) && 1.5 * (rc.senseRubble(rc.getLocation().add(dir) ) + 10)
                    <= rc.senseRubble(rc.getLocation())+ 10){
                return dir;
            }
        }
        return Direction.CENTER;
    }
    


    private static Direction findDirLowestRubble(RobotController rc, MapLocation myLoc)
        throws GameActionException{
        Direction dir = null;
        int minRubble = GameConstants.MAX_RUBBLE;

        for (int i = 0; i < Helper.directions.length; i++) {
            MapLocation tempLoc = myLoc.add(Helper.directions[i]);

            //checks if there is a robot on a square and if it has a lower rubble count
            //than what is currently stored
            if (rc.onTheMap(tempLoc) && !rc.isLocationOccupied(tempLoc) && rc.senseRubble(tempLoc) < minRubble) {
                dir = Helper.directions[i];
                minRubble = rc.senseRubble(tempLoc);
            }
        }

        if (dir == null)
            return null;
        return dir;
    }

    static MapLocation [] findNearbyArchons(RobotController rc, RobotInfo [] robots) throws GameActionException {

        //returns a MapLocations array of archon positions in the overall robots array
        MapLocation [] archonPos = new MapLocation [robots.length];
        archonPos[0] = null;
        int archonCounter = 0;
        for (int i = 0; i < robots.length; i++) {
            if (robots[i].getType().equals(RobotType.ARCHON)) {
                archonPos[archonCounter] = robots[i].getLocation();
                archonCounter++;
            }
        }
        return archonPos;
    }

    static int findLowerHealthArchon(RobotController rc, MapLocation [] archonLocs) throws GameActionException {
        int counter = 0;
        int lowestHealthArchon = -1;
        int highestHealthDiff = 0; //archon health
        while (counter < archonLocs.length && archonLocs[counter] != null) {
            RobotInfo tempArch = rc.senseRobotAtLocation(archonLocs[counter]);
            if (tempArch.getHealth() < RobotType.ARCHON.getMaxHealth(tempArch.getLevel())) {
                int tempHealthDiff = tempArch.getHealth() - RobotType.ARCHON.getMaxHealth(tempArch.getLevel());
                if (tempHealthDiff > highestHealthDiff) {
                    lowestHealthArchon = counter;
                }
            }
            counter++;
        }
        return lowestHealthArchon;
    }

}
