package dontatme_base;

import battlecode.common.*;

public strictfp class Miner {

    static int archonID = -1;
    static MinerType minerType = MinerType.None;

    static MapLocation heading = null;
    static Pathfinder pathfinder = null;
    static MapLocation exploreLoc = null;

    static boolean searchLocal = true;

    static int runAwayTimer = 0;
    static MapLocation[] currentEnemies = null;

    static int leadThreshold = 1;

    private static enum MinerType {
        None, BaseMiner, ExploreMiner
    }

    /**
     * Run a single turn for a Miner.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {
        Communications.runStart(rc);
        MapLocation me = rc.getLocation();
        RobotInfo[] robotInfo = rc.senseNearbyRobots();

        MapLocation[] golds = rc.senseNearbyLocationsWithGold(20);
        int [][] goldAmounts = goldAmounts(rc, me, golds);

        MapLocation[] leads = rc.senseNearbyLocationsWithLead(20);
        int[][] leadAmounts = leadAmounts(rc, me, leads);

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

        // Initialize exploring location if needed
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

        // If there are golds nearby, optimize
        else if (goldMinable(goldAmounts)) {
            double maxRate = 0;
            MapLocation max = null;
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    MapLocation possLocation = new MapLocation(me.x + dx, me.y + dy);
                    if (rc.canSenseLocation(possLocation)) {
                        double targetMineRate = goldMineRate(rc, possLocation, dx, dy, goldAmounts);
                        if ((targetMineRate > maxRate || (targetMineRate == maxRate && goldAmounts[dx+2][dy+2] > 0)) && !friendlyMinerAt(rc, possLocation)) {
                            maxRate = targetMineRate;
                            max = possLocation;
                        }
                    }
                }
            }
            if (max != null && !max.equals(me) && maxRate > goldMineRate(rc, me, 0, 0, goldAmounts)) {
                tryMove(rc, me, max, false);
            }
        }

        // If there are golds in vision, go towards
        else if (availableGoldInVision(rc, golds)) {
            if (heading != null && rc.canSenseLocation(heading) && rc.senseGold(heading) > 0 &&
                    !friendlyMinerAt(rc, heading)) {
                tryMove(rc, me, heading, false);
            }
            else {
                heading = goTowardsNearbyGold(rc, me, golds);
            }
        }

        // Otherwise, if not on mineable lead, go mining
        else if (!leadMinable(leadAmounts)) {
            // If the current heading still has mineable lead and no friendly miner, path there
            if (heading != null && rc.canSenseLocation(heading) && rc.senseLead(heading) > leadThreshold &&
                    !friendlyMinerAt(rc, heading)) {
                tryMove(rc, me, heading, false);
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
        else if (searchLocal) {
            double maxRate = 0;
            MapLocation max = null;
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx != 0 || dy != 0) {
                        MapLocation possLocation = new MapLocation(me.x + dx, me.y + dy);
                        if (rc.canSenseLocation(possLocation)) {
                            double targetMineRate = leadMineRate(rc, possLocation, dx, dy, leadAmounts);
                            if ((targetMineRate > maxRate || (targetMineRate == maxRate && leadAmounts[dx+3][dy+3] > 0)) && !friendlyMinerAt(rc, possLocation)) {
                                maxRate = targetMineRate;
                                max = possLocation;
                            }
                        }
                    }
                }
            }
            double selfRate = leadMineRate(rc, me, 0, 0, leadAmounts);
            if (max != null && (maxRate > selfRate || (maxRate == selfRate && rc.senseLead(max) > 0 && rc.senseLead(me) == 0))) {
                tryMove(rc, me, max, false);
            } else {
                searchLocal = false;
            }
        } else {
            double maxRate = 0;
            MapLocation max = null;
            for (int dx = -2; dx <= 2; dx++) {
                for (int dy = -2; dy <= 2; dy++) {
                    if (!(dx <= 1 && dx >= -1 && dy <= 1 && dy >= -1)) {
                        MapLocation possLocation = new MapLocation(me.x + dx, me.y + dy);
                        if (rc.canSenseLocation(possLocation)) {
                            double targetMineRate = leadMineRate(rc, possLocation, dx, dy, leadAmounts);
                            if ((targetMineRate > maxRate || (targetMineRate == maxRate && leadAmounts[dx+3][dy+3] > 0)) && !friendlyMinerAt(rc, possLocation)) {
                                maxRate = targetMineRate;
                                max = possLocation;
                            }
                        }
                    }
                }
            }
            if (max != null && maxRate > 3 * leadMineRate(rc, me, 0, 0, leadAmounts)) {
                if (tryMove(rc, me, max, false)) {
                    searchLocal = true;
                }
            } else {
                searchLocal = true;
            }
        }
    }

    static void baseMiner(RobotController rc, MapLocation me) throws GameActionException {
        // Move towards lead location given by archon
        MapLocation archonLead = Communications.getArchonVisionLead(rc, archonID);
        if (archonLead.y != 61 && minersNear(rc, archonLead) < 2) {
            tryMove(rc, me, archonLead, false);
        }
        // If no more resources left, become explorer
        else {
            minerType = MinerType.ExploreMiner;
            exploreMiner(rc, me);
        }
    }

    static void exploreMiner(RobotController rc, MapLocation me) throws GameActionException{
        if (exploreLoc != null) {
            tryMove(rc, me, exploreLoc, false);
        } else {
            Direction exploreDir = pathfinder.pathToExplore();
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
            }
        }
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                MapLocation mineLocation = new MapLocation(me.x + dx, me.y + dy);
                while (rc.canMineLead(mineLocation) && rc.senseLead(mineLocation) > leadThreshold) {
                    rc.mineLead(mineLocation);
                }
            }
        }
    }

    static MapLocation goTowardsNearbyLead(RobotController rc, MapLocation me, MapLocation[] leads) throws GameActionException {
        for (MapLocation lead : leads) {
            if (rc.senseLead(lead) > leadThreshold && !friendlyMinerAt(rc, lead)
                && (exploreLoc == null || towards(me, lead, exploreLoc))) {
                tryMove(rc, me, lead, false);
                return lead;
            }
        }
        return null;
    }

    static boolean availableGoldInVision(RobotController rc, MapLocation[] golds) throws GameActionException {
        for (MapLocation gold : golds) {
            if (!friendlyMinerAt(rc, gold)) {
                return true;
            }
        }
        return false;
    }

    static MapLocation goTowardsNearbyGold(RobotController rc, MapLocation me, MapLocation[] golds) throws GameActionException {
        for (MapLocation gold : golds) {
            if (!friendlyMinerAt(rc, gold)) {
                tryMove(rc, me, gold, false);
                return gold;
            }
        }
        return null;
    }

    static boolean towards(MapLocation me, MapLocation lead, MapLocation exploreLoc) {
        Direction toLead = me.directionTo(lead);
        Direction toExplore = me.directionTo(exploreLoc);
        return toLead == toExplore || toLead == toExplore.rotateLeft() || toLead == toExplore.rotateRight()
                || toLead == toExplore.rotateLeft().rotateLeft() || toLead == toExplore.rotateRight().rotateRight();
    }

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
                    (leadRobot.getTeam() == rc.getTeam() && leadRobot.getType() != RobotType.SOLDIER && leadRobot.getType() != RobotType.SAGE);
        }
        return false;
    }

    static boolean leadMinable(int[][] leadAmounts) {
        for (int dx = 1; dx <= 5; dx++) {
            for (int dy = 1; dy <= 5; dy++) {
                if (leadAmounts[dx][dy] > leadThreshold) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean goldMinable(int[][] goldAmounts) {
        for (int dx = 1; dx <= 3; dx++) {
            for (int dy = 1; dy <= 3; dy++) {
                if (goldAmounts[dx][dy] > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    static int[][] leadAmounts(RobotController rc, MapLocation me, MapLocation[] leads) throws GameActionException {
        int[][] amounts = new int[7][7];
        for (MapLocation lead : leads) {
            int x = lead.x - me.x + 3;
            int y = lead.y - me.y + 3;
            if (x >= 0 && x < 7 && y >= 0 && y < 7) {
                amounts[x][y] = rc.senseLead(lead);
            }
        }
        return amounts;
    }

    static int[][] goldAmounts(RobotController rc, MapLocation me, MapLocation[] golds) throws GameActionException {
        int[][] amounts = new int[5][5];
        for (MapLocation gold : golds) {
            int x = gold.x - me.x + 2;
            int y = gold.y - me.y + 2;
            if (x >= 0 && x < 5 && y >= 0 && y < 5) {
                amounts[x][y] = rc.senseGold(gold);
            }
        }
        return amounts;
    }

    static double leadMineRate(RobotController rc, MapLocation loc, int offX, int offY, int[][] leadAmounts) throws GameActionException {
        int leadCount = 0;
        for (int x = offX + 2 ; x <= offX + 4; x++) {
            for (int y = offY + 2; y <= offY + 4; y++) {
                if (leadAmounts[x][y] > leadThreshold) {
                    leadCount += leadAmounts[x][y] - leadThreshold;
                }
            }
        }
        if (leadCount > 10) {
            leadCount = 10;
        }
        return leadCount / (double) (rc.senseRubble(loc) + 1);
    }

    static double goldMineRate(RobotController rc, MapLocation loc, int offX, int offY, int[][] goldAmounts) throws GameActionException {
        int goldCount = 0;
        for (int x = offX + 1 ; x <= offX + 3; x++) {
            for (int y = offY + 1; y <= offY + 3; y++) {
                if (goldAmounts[x][y] > 0) {
                    goldCount += goldAmounts[x][y];
                }
            }
        }
        if (goldCount > 10) {
            goldCount = 10;
        }
        return goldCount / (double) (rc.senseRubble(loc) + 1);
    }

    static boolean tryMove(RobotController rc, MapLocation me, MapLocation loc, boolean greedy) throws GameActionException {
        Direction dir = pathfinder.pathToTarget(loc, greedy);
        if (dir != null && rc.canMove(dir)) {
            rc.move(dir);
            rc.setIndicatorLine(me.add(dir), loc, 0, 255, 0);
            return true;
        }
        return false;
    }
}