package dontatme;

import battlecode.common.*;

public class EnemyArchonSensing {

    // Updates enemy archon location (should be called each turn)
    public static void UpdateEnemyLocation(RobotController rc) throws GameActionException {
        RobotInfo[] robotsDetected = rc.senseNearbyRobots();
        for (RobotInfo robot : robotsDetected){
            // if found enemy archon
            if (robot.getType().equals(RobotType.ARCHON) && robot.getTeam() == rc.getTeam().opponent()) {
                // get id and location
                int enemyArchonId = robot.ID;
                MapLocation enemyArchonLocation = robot.location;

                int currentEnemyLocation = rc.readSharedArray(enemyArchonId / 2 + 8);
                MapLocation currentEnemyMapLocation= new MapLocation(currentEnemyLocation / 64, currentEnemyLocation % 64);

                // if new location found update array
                if (!enemyArchonLocation.equals(currentEnemyMapLocation)) {
                    rc.writeSharedArray(robot.ID / 2 + 8, enemyArchonLocation.x * 64 + enemyArchonLocation.y);
                }
            }
        }

        // TODO: check archon location with current location and clear if necessary
    }
}
