package dontatme;

import battlecode.common.*;
import java.util.Random;

public strictfp class Helper {

    // All possible directions
    public static final Direction[] directions = {
        Direction.NORTH,
        Direction.NORTHEAST,
        Direction.EAST,
        Direction.SOUTHEAST,
        Direction.SOUTH,
        Direction.SOUTHWEST,
        Direction.WEST,
        Direction.NORTHWEST,
    }; 

    /**
     * A random number generator.
     * We will use this RNG to make some random moves. The Random class is provided by the java.util.Random
     * import at the top of this file. Here, we *seed* the RNG with a constant number (6147); this makes sure
     * we get the same sequence of numbers every time this code is run. This is very useful for debugging!
     */
    public static final Random rng = new Random(6147);

    // Updates enemy location (should be called each turn)
    public static MapLocation[] updateEnemyLocations(RobotController rc, RobotInfo[] robotsDetected) throws GameActionException {
        MapLocation[] nearbyEnemies = new MapLocation[10];
        int index = 0;
        for (RobotInfo robot : robotsDetected){
            if (robot.getTeam() != rc.getTeam()) {
                if (robot.getType().equals(RobotType.ARCHON) && robot.getTeam() == rc.getTeam().opponent()) {
                    // get id and location
                    int enemyArchonId = robot.ID;
                    MapLocation enemyArchonLocation = robot.location;
                    MapLocation currentEnemyLocation = Communications.getEnemyArchonLocation(rc, enemyArchonId);
    
                    // if new location found update array
                    if (!enemyArchonLocation.equals(currentEnemyLocation)) {
                        Communications.setEnemyArchonLocation(rc, enemyArchonId, enemyArchonLocation);
                    }
                } else if (index < 10 && (robot.getType() == RobotType.SOLDIER || robot.getType() == RobotType.SAGE
                        || robot.getType() == RobotType.WATCHTOWER)){
                    nearbyEnemies[index] = robot.getLocation();
                    index++;
                }
            }
        }
        return nearbyEnemies;

        // TODO: check archon location with current location and clear if necessary
    }
}
