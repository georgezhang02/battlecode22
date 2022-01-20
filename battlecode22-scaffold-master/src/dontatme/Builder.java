package dontatme;

import battlecode.common.*;

import java.awt.*;

public strictfp class Builder {
    /**
     * Run a single turn for a Builder.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */

    //checks if the builder has moved towards the center already
    static boolean movedTowardsCenter = false;
    static boolean hasBuiltTower = false;
    static Pathfinder pathfinder;
    public static void run(RobotController rc) throws GameActionException {

        //key questions: priority order -> how will builders decide when to move
        //adding the ability to store lead for builders to use
        //adding the ability to store gold -> is it hte responsibility of the builder to check lead amounts
        //adding decision-making on the builder end that's separate from command structure(?)

    }
    
    //blow up for lead resources
    static void detonate(RobotController rc) throws GameActionException {
        //search areas near me in cardinal directions
        int lowestRubble = Integer.MAX_VALUE;
        int lowestIndex = Integer.MAX_VALUE;
        for (int i = 0; i < Helper.directions.length; i++) {
            int amountRubble = rc.senseRubble(rc.getLocation().add(Helper.directions[i]));
            if (amountRubble < lowestRubble && rc.canMove(Helper.directions[i])) {
                lowestRubble = amountRubble;
                lowestIndex = i;
            }
        }
        Direction dir = Helper.directions[lowestIndex];
        rc.move(dir);
        rc.disintegrate();

    }

    static void buildBuilding(RobotController rc, RobotType type) throws GameActionException {
        Team myTeam = rc.getTeam();

        //cost of watchtower
        if (rc.isActionReady() && type.equals(RobotType.WATCHTOWER) &&
                rc.getTeamLeadAmount(myTeam) >= 150 ) {
            Direction dir = findDirLowestRubble(rc, rc.getLocation());
            rc.buildRobot(type, dir);
        }
        if (rc.isActionReady() && type.equals(RobotType.LABORATORY) &&
                rc.getTeamLeadAmount(myTeam) >= 180) {
            Direction dir = findDirLowestRubble(rc, rc.getLocation());
            rc.buildRobot(type, dir);
        }
    }

    static void mutateBuilding(RobotController rc, RobotType type, RobotInfo target) throws GameActionException {
        //check if action ready
        if (rc.canMutate(target.getLocation()));
            rc.mutate(target.getLocation());
    }

    static void repairProtoTowers(RobotController rc) throws GameActionException {
        RobotInfo [] protoTowers = findNearbyProtoTowers(rc);
        if (protoTowers[0] != null && rc.isActionReady()) {

            //find highest health prototype
            int highestHealthUnit = findHighestHealth(rc, protoTowers);
            rc.repair(protoTowers[highestHealthUnit].getLocation());
        }
    }

    static void repairProtoLabs(RobotController rc) throws GameActionException {
        RobotInfo [] protoLabs = findNearbyProtoLabs(rc);
        if (protoLabs[0] != null && rc.isActionReady()) {
            int highestHealthUnit = findHighestHealth(rc, protoLabs);
            rc.repair(protoLabs[highestHealthUnit].getLocation());
            //find highest health prototype

        }
    }


    static void repairTowers(RobotController rc) throws GameActionException {
        RobotInfo [] towers = findNearbyTowers(rc);
        if (towers[0] != null && rc.isActionReady()) {

            //find lowest health tower
            int lowestHealthTower = findLowestHealth(rc, towers);
            rc.repair(towers[lowestHealthTower].getLocation());
        }
    }

    static void repairLabs(RobotController rc) throws GameActionException {
        RobotInfo [] labs = findNearbyLabs(rc);
        if (labs[0] != null && rc.isActionReady()) {

            //find lowest health tower
            int lowestHealthUnit = findLowestHealth(rc, labs);
            rc.repair(labs[lowestHealthUnit].getLocation());
        }
    }

    static RobotInfo [] findNearbyProtoTowers(RobotController rc) {
        RobotInfo [] nearbyProtos = findNearbyProtos(rc);
        if (nearbyProtos[0] == null)
            return null;

        RobotInfo [] nearbyProtoTowers = findNearbyTowers(rc, nearbyProtos);

        if (nearbyProtoTowers[0] == null)
            return null;

        return nearbyProtoTowers;
    }

    static RobotInfo [] findNearbyProtoLabs(RobotController rc) {
        RobotInfo [] nearbyProtos = findNearbyProtos(rc);
        if (nearbyProtos[0] == null)
            return null;

        RobotInfo [] nearbyProtoLabs = findNearbyLabs(rc, nearbyProtos);

        if (nearbyProtoLabs[0] == null)
            return null;

        return nearbyProtoLabs;
    }

    //find nearby prototypes
    static RobotInfo [] findNearbyProtos(RobotController rc) {
        RobotInfo [] nearbyBuildings = findNearbyBuildings(rc);
        if (nearbyBuildings[0] == null)
            return null;

        RobotInfo [] nearbyProtos = new RobotInfo[nearbyBuildings.length];
        int numberProtos = 0;

        for (int i = 0; i < nearbyBuildings.length; i++) {
            if (nearbyBuildings[i].getMode().equals(RobotMode.PROTOTYPE)) {
                nearbyProtos[numberProtos] = nearbyBuildings[i];
                numberProtos++;
            }
        }
        if (nearbyProtos[0] == null)
            return null;

        return nearbyProtos;
    }
    
    //get nearby towers
    static RobotInfo [] findNearbyTowers(RobotController rc) {
        RobotInfo [] nearbyBuildings = findNearbyBuildings(rc);
        if (nearbyBuildings[0] == null)
            return null;

        RobotInfo [] nearbyTowers = new RobotInfo [nearbyBuildings.length];
        int numberTowers = 0;

        for (int i = 0; i < nearbyBuildings.length; i++) {
            if (nearbyBuildings[i].getType().equals(RobotType.WATCHTOWER)) {
                nearbyTowers[numberTowers] = nearbyBuildings[i];
                numberTowers++;
            }
        }
        
        if (nearbyTowers[0] == null)
            return null;
        return nearbyTowers;
        
    }
    static RobotInfo [] findNearbyTowers(RobotController rc, RobotInfo [] nearbyBuildings) {
        if (nearbyBuildings[0] == null)
            return null;

        RobotInfo [] nearbyTowers = new RobotInfo [nearbyBuildings.length];
        int numberTowers = 0;

        for (int i = 0; i < nearbyBuildings.length; i++) {
            if (nearbyBuildings[i].getType().equals(RobotType.WATCHTOWER)) {
                nearbyTowers[numberTowers] = nearbyBuildings[i];
                numberTowers++;
            }
        }

        if (nearbyTowers[0] == null)
            return null;
        return nearbyTowers;

    }
    
    //get nearby labs
    static RobotInfo [] findNearbyLabs(RobotController rc) {
        RobotInfo [] nearbyBuildings = findNearbyBuildings(rc);
        if (nearbyBuildings[0] == null)
            return null;

        RobotInfo [] nearbyLabs = new RobotInfo [nearbyBuildings.length];
        int numberLabs = 0;

        for (int i = 0; i < nearbyBuildings.length; i++) {
            if (nearbyBuildings[i].getType().equals(RobotType.LABORATORY)) {
                nearbyLabs[numberLabs] = nearbyBuildings[i];
                numberLabs++;
            }
        }

        if (nearbyLabs[0] == null)
            return null;
        return nearbyLabs;
    }

    static RobotInfo [] findNearbyLabs(RobotController rc, RobotInfo [] nearbyBuildings) {
        if (nearbyBuildings[0] == null)
            return null;

        RobotInfo [] nearbyLabs = new RobotInfo [nearbyBuildings.length];
        int numberLabs = 0;

        for (int i = 0; i < nearbyBuildings.length; i++) {
            if (nearbyBuildings[i].getType().equals(RobotType.LABORATORY)) {
                nearbyLabs[numberLabs] = nearbyBuildings[i];
                numberLabs++;
            }
        }

        if (nearbyLabs[0] == null)
            return null;
        return nearbyLabs;
    }

    //get nearby buildings
    static RobotInfo [] findNearbyBuildings(RobotController rc) {
        Team myTeam = rc.getTeam();
        RobotInfo [] nearbyRobots = rc.senseNearbyRobots(rc.getType().visionRadiusSquared,
                myTeam);
        RobotInfo [] nearbyBuildings = new RobotInfo [10];
        int numberBuildingsFound = 0;
        for (int i = 0; i < nearbyRobots.length; i++) {
            if (nearbyRobots[i].getType().isBuilding()) {
                nearbyBuildings[numberBuildingsFound] = nearbyRobots[i];
                numberBuildingsFound++;
            }
        }

        //handle null values aka no buildings found
        if (numberBuildingsFound == 0)
            return null;
        else
            return nearbyBuildings;
    }

    private static int findHighestHealth(RobotController rc, RobotInfo [] units) {
        int highestHealthUnit = 0;
        int highestHealth = 0;
        int i = 0;

        while (i < units.length && units[i] != null) {
            if (units[i].getHealth() > highestHealth) {
                highestHealth = units[i].getHealth();
                highestHealthUnit = i;
            }
            i++;
        }
        return highestHealthUnit;
    }

    private static int findLowestHealth(RobotController rc, RobotInfo [] units)
            throws GameActionException {
        int lowestHealthUnit = 0;
        int lowestHealth = Integer.MAX_VALUE;
        int i = 0;

        while (i < units.length && units[i] != null) {
            if (units[i].getHealth() < lowestHealth) {
                lowestHealth = units[i].getHealth();
                lowestHealthUnit = i;
            }
        }
        return lowestHealthUnit;
    }

    private static Direction findDirLowestRubble(RobotController rc, MapLocation myLoc)
        throws GameActionException{
        Direction dir = null;
        int minRubble = GameConstants.MAX_RUBBLE;

        for (int i = 0; i < Helper.directions.length; i++) {
            MapLocation tempLoc = myLoc.add(Helper.directions[i]);

            //checks if there is a robot on a square and if it has a lower rubble count
            //than what is currently stored
            if (rc.senseRobotAtLocation(tempLoc) == null && rc.senseRubble(tempLoc) < minRubble) {
                dir = Helper.directions[i];
                minRubble = rc.senseRubble(tempLoc);
            }
        }

        if (dir == null)
            return null;
        return dir;
    }
}
