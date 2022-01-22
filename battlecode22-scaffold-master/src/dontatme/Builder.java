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

        if(labCount < builderCount){
            if(moves < 5 && (curTarget== null || !pathfinder.targetWithinRadius(curTarget, 20))){
                curTarget = getClosestCorner(rc);
                move(rc, pathfinder.pathToTarget(curTarget, false));
                rc.setIndicatorString("moving");

            }
            else if (robots.length > 3  && moves < 30){
                int index = 0;
                MapLocation[]nearby = new MapLocation[10];
                for(int i = 0; i< robots.length && index < 10; i++){

                    nearby[index++] = robots[i].getLocation();
                }
                move(rc, pathfinder.pathAwayFrom(nearby));
            } else{
                if(rc.isActionReady() && rc.getTeamLeadAmount(rc.getTeam()) >= 180){
                    Direction dir = findDirLowestRubble(rc, rc.getLocation());
                    if(dir != null && rc.canBuildRobot(RobotType.LABORATORY, dir)){
                        rc.buildRobot(RobotType.LABORATORY, dir);
                    }
                }
            }
        } else{
            MapLocation toRepair = findBuildingToRepair(rc, robots);
            if(toRepair!= null){
                if(rc.getLocation().distanceSquaredTo(toRepair) > 5){
                    move(rc, pathfinder.pathToTarget(toRepair, false));
                } else if(rc.canRepair(toRepair)){
                    rc.repair(toRepair);
                }
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
}
