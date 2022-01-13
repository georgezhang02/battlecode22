package dontatme;

import java.util.ArrayList;
import java.util.List;

import battlecode.common.*;

public strictfp class Miner {

    static int archonID = -1;
    static MinerType minerType = MinerType.None;

    static MapLocation heading = null;

    static int stuckCounter = 0;
    static Direction selectedDirection = null;

    static Pathfinder pathfinder;

    private static enum MinerType {
        None, BaseMiner, CenterMiner, ExpandMiner
    }
    /**
     * Run a single turn for a Miner.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {

        if(pathfinder == null){
            pathfinder = new BFPathing20(rc);
        }

        // Save the index of the archon the miner spawned from
        if (archonID == -1) {
            for (RobotInfo info : rc.senseNearbyRobots()) {
                if (info.getType() == RobotType.ARCHON) {
                    archonID = info.getID();
                }
            }
        }

        MapLocation me = rc.getLocation();

        // Mine around if possible
        mineAround(rc, me);

        // If just spawned, see if miner is the base or center miner
        if (minerType == MinerType.None) {
            if (Communications.getArchonMinerCount(rc, archonID) <= 2) {
                minerType = MinerType.BaseMiner;
            } else {
                minerType = MinerType.CenterMiner;
            }
        }
        
        // If not on lead
        if (rc.senseLead(me) == 0) {

            // If the current heading still has lead and no miners, go there
            if (heading != null && rc.canSenseLocation(heading) && rc.senseLead(heading) > 0 && !rc.isLocationOccupied(heading)) {
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
                            centerMiner(rc, me);
                            break;
                        case ExpandMiner:
                            rc.setIndicatorString("Expand Miner");
                            expandMiner(rc, me);
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
            for (int i = 1; i <= rc.getArchonCount(); i++) {
                MapLocation archonLocation = Communications.getTeamArchonLocationByIndex(rc, i);
                archonDirections.remove(center.directionTo(archonLocation));
            }
            selectedDirection = archonDirections.get(Communications.getMinerTurn(rc));
            Communications.incrementMinerTurn(rc, archonDirections.size());
            minerType = MinerType.ExpandMiner;
        }
    }

    static void expandMiner(RobotController rc, MapLocation me) throws GameActionException{

        // Move towards selected direction (random direction if stuck)
        if (rc.canMove(selectedDirection)) {
            rc.move(selectedDirection);
        } else {
            Direction dir = Helper.directions[Helper.rng.nextInt(Helper.directions.length)];
            if (rc.canMove(dir)) {
                rc.move(dir);
            } 
        }

    }

    static void mineAround(RobotController rc, MapLocation me) throws GameActionException {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                MapLocation mineLocation = new MapLocation(me.x + dx, me.y + dy);
                while (rc.canMineGold(mineLocation)) {
                    rc.mineGold(mineLocation);
                }
                while (rc.canMineLead(mineLocation)) {
                    rc.mineLead(mineLocation);
                }
            }
        }

    }

    static MapLocation goTowardsNearbyLead(RobotController rc, MapLocation me, MapLocation[] leads) throws GameActionException {
        for (MapLocation lead : leads) {
            if (!rc.isLocationOccupied(lead)) {
                tryMove(rc, me, lead);
                return lead;
            }
        }
        return null;
    }

    static void tryMove(RobotController rc, MapLocation me, MapLocation loc) throws GameActionException {
        Direction dir = pathfinder.pathToTarget(loc,false);
        if (rc.canMove(dir)) {
            rc.move(dir);
            rc.setIndicatorLine(me.add(dir), loc, 0, 255, 0);
        }
    }
}