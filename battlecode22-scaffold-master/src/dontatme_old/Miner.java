package dontatme_old;

import java.util.ArrayList;
import java.util.List;

import battlecode.common.*;

public strictfp class Miner {

    static int archonID = -1;
    static MinerType minerType = MinerType.None;

    static MapLocation heading = null;

    static int stuckCounter = 0;
    static MapLocation selectedLocation = null;

    static Pathfinder pathfinder;

    static RobotInfo[] robotInfo;

    private static enum MinerType {
        None, BaseMiner, CenterMiner, ExpandMiner, ExploreMiner, RunningMiner
    }
    /**
     * Run a single turn for a Miner.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {

        if (pathfinder == null){
            pathfinder = new BFPathing20(rc);
        }

        robotInfo = rc.senseNearbyRobots();

        // Save the index of the archon the miner spawned from
        if (archonID == -1) {
            for (RobotInfo info : robotInfo) {
                if (info.getType() == RobotType.ARCHON) {
                    archonID = info.getID();
                }
            }
        }

        MapLocation me = rc.getLocation();

        // If just spawned, see if miner is the base or center miner
        if (minerType == MinerType.None) {
            int minerCount = Communications.getArchonMinerCount(rc, archonID);
            if ( minerCount <= 2) {
                minerType = MinerType.BaseMiner;
            } else if (minerCount <= 6) {
                minerType = MinerType.CenterMiner;
            } else {
                minerType = MinerType.ExploreMiner;
            }
        }

        MapLocation[]nearbyEnemies = new MapLocation[10];
        int index = 0;

        // Detect enemy archon location
        for (RobotInfo robot : robotInfo) {

            if (robot.getTeam() != rc.getTeam()) {
                if(robot.getType() == RobotType.ARCHON){
                    Communications.setEnemyArchonLocation(rc, robot.getID(), robot.getLocation());
                    System.out.println(robot.getLocation());
                } else if(index < 10 && (robot.getType() == RobotType.SOLDIER || robot.getType() == RobotType.SAGE
                        || robot.getType() == RobotType.WATCHTOWER)){
                    nearbyEnemies[index] = robot.getLocation();
                    index++;

                }


            }
        }

        // Mine around if possible
        mineAround(rc, me);

        if(index > 1){
            rc.setIndicatorString("Running Away");
            Direction dir = pathfinder.pathAwayFrom(nearbyEnemies);

            pathfinder.explorer.getExploreTarget(10, rc.getMapWidth(), rc.getMapHeight());


            if(rc.canMove(dir)){
                rc.move(dir);
            }
        }
        // run away from nearby enemies
        else if (rc.senseLead(me) <= 2) {

            // If the current heading still has lead and no miners, go there
            if (heading != null && rc.canSenseLocation(heading) && rc.senseLead(heading) > 2 && !rc.isLocationOccupied(heading)) {
                tryMove(rc, me, heading);
            }

            // If no current heading, move towards lead within miner vision if possible (and avoid other miners)
            else {
                heading = goTowardsNearbyLead(rc, me, rc.senseNearbyLocationsWithLead(20));

                // If no lead within vision, run code based on miner type
                if (heading == null) {
                    switch (minerType) {

                        case BaseMiner:
                            rc.setIndicatorString("Base Miner");
                            baseMiner(rc, me);
                            break;
                        case CenterMiner:
                            rc.setIndicatorString("Center Miner");
                            exploreMiner(rc, me);
                            break;
                        case ExpandMiner:
                            rc.setIndicatorString("Expand Miner");

                            exploreMiner(rc, me);
                            break;
                        case ExploreMiner:
                            rc.setIndicatorString("Explore Miner");
                            exploreMiner(rc, me);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }


    static void baseMiner(RobotController rc, MapLocation me) throws GameActionException {

        // Move towards lead location given by archon
        MapLocation archonLead = Communications.getArchonVisionLead(rc, archonID);
        if (archonLead.y != 61) {
            tryMove(rc, me, archonLead);
        }

        // If no more resources left, become center miner
        else {
            minerType = MinerType.CenterMiner;
        }
    }

    static void centerMiner(RobotController rc, MapLocation me) throws GameActionException{

        // Move towards the center
        MapLocation center = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
        Direction dir = pathfinder.pathToTarget(center, false);
        if (rc.canMove(dir)) {
            rc.move(dir);
            stuckCounter = 0;
        }
        else if (rc.isMovementReady() && rc.senseRobotAtLocation(me.add(dir)) != null) {
            stuckCounter++;
        }

        // If at the center, expand in a direction not towards a friendly archon
        if ((me.x == center.x && me.y == center.y) || stuckCounter >= 2) {
            List<Direction> archonDirections = new ArrayList<Direction>();
            for (Direction d : Helper.directions) {
                archonDirections.add(d);
            }
            for (int i = 0; i < rc.getArchonCount(); i++) {
                MapLocation archonLocation = Communications.getTeamArchonLocationByIndex(rc, i);
                archonDirections.remove(center.directionTo(archonLocation));
            }
            Direction selectedDirection = archonDirections.get(Communications.getMinerTurn(rc));
            selectedLocation = toEdge(rc, me, selectedDirection);
            Communications.incrementMinerTurn(rc, archonDirections.size());
            minerType = MinerType.ExpandMiner;
        }
    }

    static void expandMiner(RobotController rc, MapLocation me) throws GameActionException{

        // Move towards selected location, when edge is reached, become explore miner
        if (atEdge(rc, me)) {
            minerType = MinerType.ExploreMiner;
        } else {
            tryMove(rc, me, selectedLocation);
        }
    }

    static void exploreMiner(RobotController rc, MapLocation me) throws GameActionException{
        Direction exploreDir = pathfinder.pathToExplore();
        if (rc.canMove(exploreDir)) {
            rc.move(exploreDir);
        }
    }

    static void mineAround(RobotController rc, MapLocation me) throws GameActionException {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                MapLocation mineLocation = new MapLocation(me.x + dx, me.y + dy);
                while (rc.canMineGold(mineLocation)) {
                    rc.mineGold(mineLocation);
                }
                while (rc.canMineLead(mineLocation) &&  rc.senseLead(mineLocation) > 2) {
                    rc.mineLead(mineLocation);
                }
            }
        }

    }

    static MapLocation goTowardsNearbyLead(RobotController rc, MapLocation me, MapLocation[] leads) throws GameActionException {

        for (MapLocation lead : leads) {
            if (!rc.isLocationOccupied(lead) && rc.senseLead(lead) > 2) {

                tryMove(rc, me, lead);
                return lead;
            }
        }
        return null;
    }

    static void tryMove(RobotController rc, MapLocation me, MapLocation loc) throws GameActionException {
        Direction dir = pathfinder.pathToTarget(loc,false);
        if (dir != null && rc.canMove(dir)) {
            rc.move(dir);
            rc.setIndicatorLine(me.add(dir), loc, 0, 255, 0);
        }
    }

    static MapLocation toEdge(RobotController rc, MapLocation loc, Direction dir) {
        while (!atEdge(rc, loc)) {
            loc = loc.add(dir);
        }
        return loc;
    }

    static boolean atEdge(RobotController rc, MapLocation loc) {
        return loc.x == 0 || loc.x == rc.getMapWidth() - 1 || loc.y == 0 || loc.y == rc.getMapHeight() - 1;
    }
}