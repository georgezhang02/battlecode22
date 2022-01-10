package karthiktestp;

import battlecode.common.*;
import java.util.*;

import java.awt.*;
import java.lang.annotation.Target;
import java.util.Map;

strictfp class FollowMinersSoldierStrategy {
    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static int addY = 0;
    static void runSoldier(RobotController rc) throws GameActionException {
        // Try to attack someone
        int radius = rc.getType().actionRadiusSquared;
        Team ally = rc.getTeam();
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);
        if (enemies.length > 0) {
            MapLocation toAttack = enemies[0].location;
            if (rc.canAttack(toAttack)) {
                rc.attack(toAttack);
            }
        }
        Direction dir = null;
        MapLocation TargetLocation = null;
        RobotInfo[] allies = rc.senseNearbyRobots(radius, ally);
        if (allies.length > 0) {
            boolean isMiner = false;
            int counter = 0;
            ArrayList <Integer> MinerLocs = new ArrayList<Integer>(); //stores miner locations
            while(counter < allies.length) {
                if (allies[counter].getType().equals(RobotType.MINER))
                    MinerLocs.add(counter);
                counter += 1;
            }
            //check if minerlocs has values in it
            counter = 0;
            if (!MinerLocs.isEmpty()) {
                boolean isGoodTarget = false;
                while (counter < MinerLocs.size() && !isGoodTarget) {
                    if (rc.canSenseLocation(allies[MinerLocs.get(counter)].getLocation()) &&
                        !rc.canAttack(allies[MinerLocs.get(counter)].getLocation())) {
                        isGoodTarget = true;
                    }
                    else {
                        counter++;
                    }
                }
                if (isGoodTarget) {
                    int locX = allies[MinerLocs.get(counter)].getLocation().x;
                    int locY = allies[MinerLocs.get(counter)].getLocation().y;
                    TargetLocation = new MapLocation(locX, locY);
                }

            }
            else if (!allies[0].getType().equals(RobotType.SOLDIER) && !allies[0].getType().equals(RobotType.ARCHON)
                && !allies[0].getType().equals(RobotType.BUILDER)) {
                int locX = allies[0].getLocation().x;
                int locY = allies[0].getLocation().y;
                TargetLocation = new MapLocation(locX, locY);
            }
            else {// you do not want to path towards a soldier, archon, or watchtower generally bc otherwise it can lead to useless loitering
                dir = RobotPlayer.directions[RobotPlayer.rng.nextInt(RobotPlayer.directions.length)];
            }
            if (TargetLocation != null)
                dir = Pathing.pathTo(rc, TargetLocation);
        }
        else {
            dir = RobotPlayer.directions[RobotPlayer.rng.nextInt(RobotPlayer.directions.length)];
        }
        if (rc.canMove(dir)) {
            rc.move(dir);
            //System.out.println("I moved!");
        }
    }
}
