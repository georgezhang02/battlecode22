package dontatme;

import battlecode.common.*;

public strictfp class Miner {

    static int archonIndex = -1;
    static int minerType = 0;
    static MapLocation heading = null;
    static int stuckCounter = 0;
    static Direction selectedDirection = Direction.CENTER;

    /**
     * Run a single turn for a Miner.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void run(RobotController rc) throws GameActionException {

        Pathfinder.rc = rc;

        // Save the index of the archon the miner spawned from
        if (archonIndex == -1) {
            for (RobotInfo info : rc.senseNearbyRobots()) {
                if (info.getType() == RobotType.ARCHON) {
                    archonIndex = info.getID() / 2;
                }
            }
        }

        MapLocation me = rc.getLocation();
        MapLocation[] leads = rc.senseNearbyLocationsWithLead(20);
        int arrayVal = rc.readSharedArray(archonIndex);

        // Mine around if possible
        mineAround(rc, me);

        // See if it is the base or center miner
        if (minerType == 0) {
            if (arrayVal / 4096 <= 2) {
                minerType = 1;
            } else {
                minerType = 2;
            }
        }
        
        // Run base or center or expand code
        switch (minerType) {
            case 1:
                baseMiner(rc, me, arrayVal, leads);
                rc.setIndicatorString("Base Miner");
                break;
            case 2:
                centerMiner(rc, me, leads);
                rc.setIndicatorString("Center Miner");
                break;
            case 3:
                expandMiner(rc, me, leads);
                rc.setIndicatorString("Expand Miner");
            default:
                break;
        }
    }

    static void baseMiner(RobotController rc, MapLocation me, int arrayVal, MapLocation[] leads) throws GameActionException {

        // If not on lead
        if (rc.senseLead(me) == 0) {

            // If the current heading still has lead and no miners, go there
            if (heading != null && rc.senseLead(heading) > 0 && rc.senseRobotAtLocation(heading) == null) {
                tryMove(rc, me, heading);
            }

            // If no current heading, move towards lead within miner vision if possible (and avoid other miners)
            else if (leads.length > 0) {
                heading = goTowardsNearbyLead(rc, me, leads);
            } 

            // If no lead within vision, move towards lead location given by archon
            else if (arrayVal % 64 != 61) {
                MapLocation archonLeadLocation = new MapLocation(arrayVal / 64 % 64, arrayVal % 64);
                tryMove(rc, me, archonLeadLocation);
            }

            // If no more resources left, become exploration miner
            else {
                minerType = 2;
            }
        }
    }

    static void centerMiner(RobotController rc, MapLocation me, MapLocation[] leads) throws GameActionException{

        // If not on lead
        if (rc.senseLead(me) == 0) {

            // If the current heading still has lead and no miners, go there
            if (heading != null && rc.senseLead(heading) > 0 && rc.senseRobotAtLocation(heading) == null) {
                tryMove(rc, me, heading);
            }

            else {
                
                // If no current heading, move towards lead within miner vision if possible (and avoid other miners)
                heading = goTowardsNearbyLead(rc, me, leads);
    
                // Otherwise, move towards the center
                MapLocation center = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
                if (heading == null) {
                    Pathfinder.target = center;
                    Direction dir = Pathfinder.pathToTarget();
                    if (rc.canMove(dir)) {
                        rc.move(dir);
                        stuckCounter = 0;
                    } else {
                        // rc.getMovementCooldownTurns() == 0 &&
                        if (rc.senseRobotAtLocation(me.add(dir)) != null) {
                            stuckCounter++;
                            rc.setIndicatorString(Integer.toString(stuckCounter));
                        }
                    }
                }

                if ((me.x == center.x && me.y == center.y) || stuckCounter >= 2) {
                    minerType = 3;
                    int arrayVal = rc.readSharedArray(archonIndex + 4);
                    MapLocation archonLocation = new MapLocation(arrayVal / 64 % 64, arrayVal % 64);
                    Direction forwards = me.directionTo(archonLocation).opposite();
                    Direction[] possibleDirections = new Direction[5];
                    possibleDirections[0] = forwards;
                    possibleDirections[1] = forwards.rotateLeft().rotateLeft();
                    possibleDirections[2] = forwards.rotateRight().rotateRight();
                    possibleDirections[3] = forwards.rotateLeft();
                    possibleDirections[4] = forwards.rotateRight();
                    int turn = arrayVal / 4096;
                    selectedDirection = possibleDirections[turn];
                    rc.writeSharedArray(archonIndex + 4, ((turn + 1) % 5) * 4096 + arrayVal);
                }
            }
        }
    }

    static void expandMiner(RobotController rc, MapLocation me, MapLocation[] leads) throws GameActionException{

        // If not on lead
        if (rc.senseLead(me) == 0) {

            // If the current heading still has lead and no miners, go there
            if (heading != null && rc.senseLead(heading) > 0 && rc.senseRobotAtLocation(heading) == null) {
                tryMove(rc, me, heading);
            }

            else {
                
                // If no current heading, move towards lead within miner vision if possible (and avoid other miners)
                heading = goTowardsNearbyLead(rc, me, leads);
    
                // Otherwise, move towards selected direction
                if (heading == null) {
                    if (rc.canMove(selectedDirection)) {
                        rc.move(selectedDirection);
                    } else {
                        Direction dir = Helper.directions[Helper.rng.nextInt(Helper.directions.length)];
                        if (rc.canMove(dir)) {
                            rc.move(dir);
                        } 
                    }
                }
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
            if (rc.senseRobotAtLocation(lead) == null) {
                if (tryMove(rc, me, lead)) {
                    return lead;
                }
            }
        }
        return null;
    }

    static boolean tryMove(RobotController rc, MapLocation me, MapLocation loc) throws GameActionException {
        Pathfinder.target = loc;
        Direction dir = Pathfinder.pathToTarget();
        if (rc.canMove(dir)) {
            rc.move(dir);
            rc.setIndicatorLine(me.add(dir), loc, 0, 255, 0);
            return true;
        }
        return false;
    }
}