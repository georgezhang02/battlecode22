package dontatme;

import battlecode.common.*;

public strictfp class Miner {

    static int archonIndex;
    static int minerPhase;

    public Miner() {
        archonIndex = -1;
        minerPhase = 0;
    }

    /**
     * Run a single turn for a Miner.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {

        MapLocation me = rc.getLocation();
        int leadLocation = rc.readSharedArray(archonIndex);
        int leadX = leadLocation / 64;
        int leadY = leadLocation % 64;

        // Save the index of the archon it spawned from
        if (archonIndex == -1) {
            for (RobotInfo info : rc.senseNearbyRobots()) {
                if (info.getType() == RobotType.ARCHON) {
                    archonIndex = (info.getID() - 2) / 2;
                }
            }
        }

        // If arrived, go toward mining phase
        if (minerPhase == 0 && me.x == leadX && me.y == leadY) {
            minerPhase = 1;
        }

        int mineCount = 0;

        // Phase 0: navigate towards starting lead
        if (minerPhase == 0) {
            Direction dir = navigateTowards(leadX, leadY);
            if (rc.canMove(dir)) {
                rc.move(dir);
            }
        }

        // Phase 1: mine around
        else if (minerPhase == 1){
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    MapLocation mineLocation = new MapLocation(me.x + dx, me.y + dy);
                    // Notice that the Miner's action cooldown is very low.
                    // You can mine multiple times per turn!
                    while (rc.canMineGold(mineLocation)) {
                        rc.mineGold(mineLocation);
                    }
                    while (rc.canMineLead(mineLocation)) {
                        mineCount++;
                        rc.mineLead(mineLocation);
                    }
                }
            }
        }

        // Phase 2: finish mining everything in vision
        else if (minerPhase == 2) {

        }

        // If everything is mined, look for more stuff to mine
        if (minerPhase == 1 && mineCount == 0) {
            minerPhase = 2;
        }

    }

    // Given a coordinate to go towards, return a direction to travel
    static Direction navigateTowards(int x, int y) {
        return Direction.CENTER;
    }
}
