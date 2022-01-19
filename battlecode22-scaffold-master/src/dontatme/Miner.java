package dontatme;

import battlecode.common.*;

public strictfp class Miner {

    static int archonID = -1;
    static MinerType minerType = MinerType.None;

    static MapLocation heading = null;
    static Pathfinder pathfinder = null;
    static MapLocation exploreLoc = null;

    static int runAwayTimer = 0;
    static MapLocation[] currentEnemies = null;

    static int leadThreshold = 1;

    private static enum MinerType {
        None, BaseMiner, ExpandMiner, ExploreMiner
    }

    /**
     * Run a single turn for a Miner.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {
        Communications.runStart(rc);

        MapLocation me = rc.getLocation();
        RobotInfo[] robotInfo = rc.senseNearbyRobots();
        MapLocation[] leads = rc.senseNearbyLocationsWithLead(20);

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

        // If just spawned, become base miner if it's the first miner
        if (minerType == MinerType.None) {
            int minerCount = Communications.getArchonMinerCount(rc, archonID);
            if (minerCount == 0) {
                Communications.incrementArchonMinerCount(rc, archonID);
                minerType = MinerType.BaseMiner;
            } else {
                minerType = MinerType.ExploreMiner;
            }
        }

        // Choose to destroy mine if seeing an enemy archon or miner
        leadThreshold = 1;
        for (RobotInfo robot : robotInfo) {
            if (robot.getTeam() != rc.getTeam() && (robot.getType() == RobotType.ARCHON || robot.getType() == RobotType.MINER )) {
                leadThreshold = 0;
                break;
            }
        }

        // Mine around if possible
        mineAround(rc, me);

        // Set exploration as done if found location
        if (exploreLoc != null && rc.canSenseLocation(exploreLoc) && rc.senseLead(exploreLoc) == 0) {
            exploreLoc = null;
            System.out.println("Done exploring");
        }

        // Run away from enemies for 2 moves if necessary
        currentEnemies = Helper.updateEnemyLocations(rc, robotInfo);
        runAwayTimer--;
        if (currentEnemies[0] != null || runAwayTimer > 0){
            Direction dir = pathfinder.pathAwayFrom(currentEnemies);
            if (rc.canMove(dir)){
                rc.move(dir);
            }

            // There are still enemies, call in reinforcement
            if (currentEnemies[0] != null){
                runAwayTimer = 4;
                Communications.sendAttackCommand(rc, currentEnemies[0], RobotType.MINER);
                rc.setIndicatorString("Help me");
            }
        }

        // Otherwise, if not on mineable lead, go mining
        else if (mineRate(rc, me) == 0) {

            // If the current heading still has mineable lead and no friendly miner, path there
            if (heading != null && rc.canSenseLocation(heading) && rc.senseLead(heading) > leadThreshold &&
                    !friendlyMinerAt(rc, heading)) {
                tryMove(rc, me, heading);
            }

            // If no current heading, move towards best lead within miner vision if possible
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

        // If on lead, try to optimize mine rate by moving around if possible
        else {
            double bestMineRate = mineRate(rc, me);;
            Direction bestDirection = null;
            for (Direction dir : Helper.directions) {
                double targetMineRate = mineRate(rc, me.add(dir));
                if (rc.canMove(dir) && targetMineRate > bestMineRate) {
                    bestMineRate = targetMineRate;
                    bestDirection = dir;
                }
            }
            if (bestDirection != null) {
                rc.move(bestDirection);
                rc.setIndicatorDot(me.add(bestDirection), 0, 255, 255);
            }
        }
    }

    static void baseMiner(RobotController rc, MapLocation me) throws GameActionException {
        // Move towards lead location given by archon
        MapLocation archonLead = Communications.getArchonVisionLead(rc, archonID);
        if (archonLead.y != 61 && minersNear(rc, archonLead) < 2) {
            rc.setIndicatorDot(me, 255, 0, 0);
            tryMove(rc, me, archonLead);
        }
        // If no more resources left, become explorer
        else {
            minerType = MinerType.ExploreMiner;
            exploreMiner(rc, me);
        }
    }

    static void exploreMiner(RobotController rc, MapLocation me) throws GameActionException{
        int minerTurn = Communications.getMinerTurn(rc);
        if (exploreLoc == null && minerTurn < 31) {
            int height = rc.getMapHeight();
            int width = rc.getMapWidth();
            MapLocation selectedLocation = null;
            int locInd = -1;
            int selectedLocInd = -1;
            for (int i = 0; i < 5; i++) {
                if ((minerTurn >> i) % 2 == 0) {
                    switch(i) {
                        case 0:
                            selectedLocation = new MapLocation(0, 0);
                            selectedLocInd = 0;
                            break;
                        case 1:
                            selectedLocation = new MapLocation(width - 1, 0);
                            selectedLocInd = 1;
                            break;
                        case 2:
                            selectedLocation = new MapLocation(0, height - 1);
                            selectedLocInd = 2;
                            break;
                        case 3:
                            selectedLocation = new MapLocation(width - 1, height - 1);
                            selectedLocInd = 3;
                            break;
                        case 4:
                            selectedLocation = new MapLocation((width - 1) / 2, (height - 1) / 2);
                            selectedLocInd = 4;
                            break;
                        default:
                            break;
                    }
                }
                if (exploreLoc == null || selectedLocation.distanceSquaredTo(me) < exploreLoc.distanceSquaredTo(me)) {
                    exploreLoc = selectedLocation;
                    locInd = selectedLocInd;
                }
            }
            Communications.incrementMinerTurn(rc, locInd);
            System.out.println("Explore: " + exploreLoc);
            tryMove(rc, me, exploreLoc);
        } else if (exploreLoc != null) {
            tryMove(rc, me, exploreLoc);
        } else {
            Direction exploreDir = pathfinder.pathToExplore();
            //exploreLoc = pathfinder.explorer.target;
            if (rc.canMove(exploreDir)) {
                rc.move(exploreDir);
                rc.setIndicatorLine(me.add(exploreDir), pathfinder.explorer.target, 0, 0, 255);
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
                while (rc.canMineLead(mineLocation) && rc.senseLead(mineLocation) > leadThreshold) {
                    rc.mineLead(mineLocation);
                }
            }
        }
    }

    static MapLocation goTowardsNearbyLead(RobotController rc, MapLocation me, MapLocation[] leads) throws GameActionException {
        for (MapLocation lead : leads) {
            if (rc.senseLead(lead) > leadThreshold && !friendlyMinerAt(rc, lead)) {
                // && (exploreLoc == null || towards(me, lead, exploreLoc))
                tryMove(rc, me, lead);
                return lead;
            }
        }
        return null;
    }

    /*
    static boolean towards(MapLocation me, MapLocation lead, MapLocation exploreLoc) {
        Direction toLead = me.directionTo(lead);
        Direction toExplore = me.directionTo(exploreLoc);
        return toLead == toExplore || toLead == toExplore.rotateLeft() || toLead == toExplore.rotateRight()
                || toLead == toExplore.rotateLeft().rotateLeft() || toLead == toExplore.rotateRight().rotateRight();
    }
    */

    static int minersNear(RobotController rc, MapLocation lead) throws GameActionException {
        int count = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                MapLocation loc = new MapLocation(lead.x + dx, lead.y + dy);
                if (friendlyMinerAt(rc, loc)) {
                    count++;
                }
            }
        }
        return count;
    }

    static boolean friendlyMinerAt(RobotController rc, MapLocation loc) throws GameActionException {
        if (rc.canSenseLocation(loc)) {
            RobotInfo leadRobot = rc.senseRobotAtLocation(loc);
            return leadRobot != null &&
                    (leadRobot.getTeam() == rc.getTeam() && leadRobot.getType() == RobotType.MINER);
        }
        return false;
    }

    static double mineRate(RobotController rc, MapLocation lead) throws GameActionException {
        int leadCount = 0;
        for (Direction dir : Helper.directions) {
            MapLocation nearby = lead.add(dir);
            if (rc.canSenseLocation(nearby)) {
                int nearbyLead = rc.senseLead(nearby);
                if (nearbyLead != 0) {
                    leadCount += nearbyLead - leadThreshold;
                }
            }
        }
        int rubbleCount = 0;
        if (rc.canSenseLocation(lead)) {
            int locLead = rc.senseLead(lead);
            if (locLead != 0) {
                leadCount += locLead - leadThreshold;
            }
            rubbleCount = rc.senseRubble(lead);
        }
        if (leadCount > 10) {
            leadCount = 10;
        }
        double cooldown = (1 + ((double) rubbleCount) / 10) * 2;
        return leadCount / cooldown;
    }

    static void tryMove(RobotController rc, MapLocation me, MapLocation loc) throws GameActionException {
        Direction dir = pathfinder.pathToTarget(loc,false);
        /*
        if (dir != null && rc.canMove(dir)) {
            double targetMineRate = mineRate(rc, loc);
            double currentMineRate = mineRate(rc, me);
            if (targetMineRate >= currentMineRate){
                rc.move(dir);
                rc.setIndicatorLine(me.add(dir), loc, 0, 255, 0);
            }
        }
         */
        if (dir != null && rc.canMove(dir)) {
            rc.move(dir);
            rc.setIndicatorLine(me.add(dir), loc, 0, 255, 0);
        }
    }
}