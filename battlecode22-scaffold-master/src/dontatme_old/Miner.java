package dontatme_old;

import battlecode.common.*;

public strictfp class Miner {

    static int archonID = -1;
    static MinerType minerType = MinerType.None;

    static MapLocation heading = null;
    static Pathfinder pathfinder;

    static int runAwayTimer = 0;
    static MapLocation[] currentEnemies = null;

    private static enum MinerType {
        None, BaseMiner, ExploreMiner
    }

    /**
     * Run a single turn for a Miner.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {
        Communications.runStart(rc);

        RobotInfo[] robotInfo = rc.senseNearbyRobots();
        MapLocation me = rc.getLocation();
        MapLocation[] leads = rc.senseNearbyLocationsWithLead(20);
        MapLocation[] leadsClose = rc.senseNearbyLocationsWithLead(2);

        // Initialize pathfinding and archon index
        if (pathfinder == null){
            pathfinder = new BFPathing20(rc);
        }
        if (archonID == -1) {
            for (RobotInfo info : robotInfo) {
                if (info.getType() == RobotType.ARCHON) {
                    archonID = info.getID();
                }
            }
        }

        // If just spawned, set miner type
        if (minerType == MinerType.None) {
            int minerCount = Communications.getArchonMinerCount(rc, archonID);
            if (minerCount <= 2) {
                Communications.incrementArchonMinerCount(rc, archonID);
                minerType = MinerType.BaseMiner;
            } else {
                minerType = MinerType.ExploreMiner;
            }
        }

        // Mine around if possible
        mineAround(rc, me);

        currentEnemies = Helper.updateEnemyLocations(rc, robotInfo);
        runAwayTimer--;
        if (currentEnemies[0] != null || runAwayTimer > 0){
            Direction dir = pathfinder.pathAwayFrom(currentEnemies);
            if (rc.canMove(dir)){

                rc.move(dir);
            }

            if(currentEnemies[0] != null){
                runAwayTimer = 3;
                Communications.sendAttackCommand(rc, rc.getLocation(), RobotType.MINER);
                rc.setIndicatorString("Help me");
            }



        }
        // Otherwise, go mining
        else if (currentEnemies[0] == null) {

            // If not on lead
            int leadOnMe = rc.senseLead(me);

            int visionLeadCount = 0;
            for (MapLocation lead : leads) {
                visionLeadCount += rc.senseLead(lead);
            }

            if (leadOnMe == 0 || (leadOnMe == 1 && (leadsClose.length < 6 || visionLeadCount > 4 * leads.length || !noMinersAround(rc, me)))) {

                // If the current heading still has lead and no miners, go there
                if (heading != null && rc.canSenseLocation(heading) && rc.senseLead(heading) > 1 && noMinersAround(rc, heading)) {
                    tryMove(rc, me, heading);
                }

                // If no current heading, move towards lead within miner vision if possible (and avoid other miners)
                else {
                    heading = goTowardsNearbyLead(rc, me, leads);

                    // If no lead within vision, run code based on miner type
                    if (heading == null) {
                        switch (minerType) {
                            case BaseMiner:
                                rc.setIndicatorString("Base Miner");
                                baseMiner(rc, me);
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
    }

    static boolean noMinersAround(RobotController rc, MapLocation loc) throws GameActionException {
        for (Direction dir : Direction.allDirections()) {
            MapLocation nearby = loc.add(dir);
            if (rc.canSenseLocation(nearby)) {
                RobotInfo nearbyRobot = rc.senseRobotAtLocation(nearby);
                if (nearbyRobot != null && nearbyRobot.getID() != rc.getID() && nearbyRobot.getType() == RobotType.MINER) {
                    return false;
                }
            }
        }
        return true;
    }

    static void baseMiner(RobotController rc, MapLocation me) throws GameActionException {
        // Move towards lead location given by archon
        MapLocation archonLead = Communications.getArchonVisionLead(rc, archonID);
        if (archonLead.y != 61) {
            rc.setIndicatorDot(me, 255, 0, 0);
            tryMove(rc, me, archonLead);
        }
        // If no more resources left, become explorer
        else {
            minerType = MinerType.ExploreMiner;
        }
    }

    static void exploreMiner(RobotController rc, MapLocation me) throws GameActionException{
        Direction exploreDir = pathfinder.pathToExplore();
        if (rc.canMove(exploreDir)) {
            rc.move(exploreDir);
            rc.setIndicatorLine(me.add(exploreDir), pathfinder.explorer.target, 0, 0, 255);
        }
    }

    static void mineAround(RobotController rc, MapLocation me) throws GameActionException {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                MapLocation mineLocation = new MapLocation(me.x + dx, me.y + dy);
                while (rc.canMineGold(mineLocation)) {
                    rc.mineGold(mineLocation);
                }
                while (rc.canMineLead(mineLocation) && rc.senseLead(mineLocation) > 1) {
                    rc.mineLead(mineLocation);
                }
            }
        }

    }

    static MapLocation goTowardsNearbyLead(RobotController rc, MapLocation me, MapLocation[] leads) throws GameActionException {
        for (MapLocation lead : leads) {
            if (rc.senseLead(lead) > 1 && noMinersAround(rc, lead)) {
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
}