package dontatme;

import battlecode.common.*;

public strictfp class Miner {

    static int archonIndex = -1;
    static int minerType = 0;

    /**
     * Run a single turn for a Miner.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {

        // Save the index of the archon it spawned from
        if (archonIndex == -1) {
            for (RobotInfo info : rc.senseNearbyRobots()) {
                if (info.getType() == RobotType.ARCHON) {
                    archonIndex = info.getID() / 2;
                }
            }
        }

        MapLocation me = rc.getLocation();

        // Mine around if possible
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

        int arrayVal = rc.readSharedArray(archonIndex);

        // See if it is the base or exploration miner
        if (minerType == 0) {
            if (arrayVal / 4096 <= 2) {
                minerType = 1;
            } else {
                minerType = 2;
            }
        }
        
        switch (minerType) {
            case 1:
                baseMiner(rc, me, arrayVal);
                break;
            case 2:
                explorationMiner(rc);
                break;
            default:
                break;
        }
    }

    static void baseMiner(RobotController rc, MapLocation me, int arrayVal) throws GameActionException {

        MapLocation[] leads = rc.senseNearbyLocationsWithLead(20);
        // If not on lead
        if (rc.senseLead(me) == 0) {

            // Move towards lead within miner vision if possible (and avoid other miners)
            if (leads.length > 0) {
                for (MapLocation lead : leads) {
                    if (rc.senseRobotAtLocation(lead) == null) {
                        Direction dir = Pathing.pathTo(rc, lead);
                        if (rc.canMove(dir)) {
                            rc.move(dir);
                            break;
                        }
                    }  
                }
            } 

            // Otherwise, move towards lead location given by archon
            else if (arrayVal % 64 != 61) {
                Direction dir = Pathing.pathTo(rc, new MapLocation(arrayVal / 64 % 64, arrayVal % 64));
                rc.setIndicatorString("" + dir.dx + dir.dy);
                if (rc.canMove(dir)) {
                    rc.move(dir);
                }
            }

            // If no more resources left, become exploration miner
            else {
                minerType = 2;
                explorationMiner(rc);
            }
        }

    }

    static void explorationMiner(RobotController rc) {

    }
}