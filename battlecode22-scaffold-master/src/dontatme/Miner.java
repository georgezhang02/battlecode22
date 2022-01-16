package dontatme;

import battlecode.common.*;

public strictfp class Miner {

    static int archonID = -1;
    static MinerType minerType = MinerType.None;

    static MapLocation heading = null;
    static MapLocation pastHeading = null;
    static Pathfinder pathfinder;

    static int runAwayTimer = 0;
    static MapLocation[] currentEnemies = null;



    private static enum MinerType {
        None, BaseMiner, CampMiner, ExploreMiner
    }

    /**
     * Run a single turn for a Miner.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {

        RobotInfo[] robotInfo = rc.senseNearbyRobots();
        MapLocation me = rc.getLocation();

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
                minerType = MinerType.BaseMiner;
            } else {
                if (Math.random() > .5){
                    minerType = MinerType.CampMiner;
                } else {
                    minerType = MinerType.ExploreMiner;
                }
            }
        }

        mineAround(rc, me);

        MapLocation[] newEnemies = new MapLocation[10];
        int index = 0;
        for(RobotInfo robot: robotInfo){
            if(index < 10 && !robot.getTeam().isPlayer() && (robot.type == RobotType.SOLDIER ||
                    robot.type == RobotType.SAGE ||robot.type == RobotType.WATCHTOWER)){
                newEnemies[index] = robot.getLocation();
                index++;
            }
        }
        if (newEnemies[0] != null || currentEnemies == null) {
            currentEnemies = newEnemies;
        }

        if (currentEnemies[0] != null && runAwayTimer < 3){
            System.out.println(me + " " + "running away");
            Direction dir = pathfinder.pathAwayFrom(currentEnemies);
            if (rc.canMove(dir)){
                rc.move(dir);
            }
            if (newEnemies[1] != null){
                //Communications.sendDefenseCommand(rc,rc.getLocation(), RobotType.MINER, rc.getID());
            }
            runAwayTimer++;
        }

        // Otherwise, go mining
        else if (currentEnemies[0] == null) {
            runAwayTimer = 0;

            // Mine around if possible
            int leadThreshold = 1;
            if (minerType == MinerType.CampMiner) {
                leadThreshold = 0;
            }

            // If not on lead
            if (rc.senseLead(me) <= leadThreshold) {

                // If the current heading still has lead and no miners, go there
                if (heading != null && rc.senseLead(heading) > leadThreshold && noMinersAround(rc, heading)) {
                    tryMove(rc, me, heading);
                }

                // If no current heading, move towards lead within miner vision if possible (and avoid other miners)
                else {
                    heading = goTowardsNearbyLead(rc, me, leadThreshold);

                    // If no lead within vision, run code based on miner type
                    if (heading == null) {
                        switch (minerType) {
                            case BaseMiner:
                                rc.setIndicatorString("Base Miner");
                                baseMiner(rc, me);
                                break;
                            case CampMiner:
                                rc.setIndicatorString("Camp Miner");
                                exploreMiner(rc, me);
                            case ExploreMiner:
                                rc.setIndicatorString("Explore Miner");
                                exploreMiner(rc, me);
                                break;
                            default:
                                break;
                        }
                    } else {
                        pastHeading = heading;
                        rc.setIndicatorString("" + pastHeading.x + pastHeading.y);
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
                if (nearbyRobot != null && nearbyRobot.ID != rc.getID()) {
                    return false;
                }
            }
        }
        return true;
    }

    static void baseMiner(RobotController rc, MapLocation me) throws GameActionException {
        // Move towards lead location given by archon
        MapLocation archonLead = Communications.getArchonVisionLead(rc, archonID);
        if (archonLead.y != 61 && noMinersAround(rc, archonLead)) {
            tryMove(rc, me, archonLead);
        }

        // If no more resources left, become camp miner
        else {
            minerType = MinerType.CampMiner;
        }
    }

    static void exploreMiner(RobotController rc, MapLocation me) throws GameActionException{
        Direction exploreDir = pathfinder.pathToExplore();
        if (rc.canMove(exploreDir)) {
            rc.move(exploreDir);
            rc.setIndicatorLine(me.add(exploreDir), me.add(exploreDir).add(exploreDir), 0, 0, 255);
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

    static MapLocation goTowardsNearbyLead(RobotController rc, MapLocation me, int leadThreshold) throws GameActionException {
        MapLocation[] leads = rc.senseNearbyLocationsWithLead(20);
        for (MapLocation lead : leads) {
            if (rc.senseLead(lead) > leadThreshold && noMinersAround(rc, lead) && !lead.equals(pastHeading)) {
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