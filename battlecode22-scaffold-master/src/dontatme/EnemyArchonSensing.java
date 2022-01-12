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
                MapLocation currentEnemyLocation = Communications.getEnemyArchonLocation(rc, enemyArchonId);

                // if new location found update array
                if (!enemyArchonLocation.equals(currentEnemyLocation)) {
                    Communications.setEnemyArchonLocation(rc, enemyArchonId, enemyArchonLocation);
                }
            }
        }

        // TODO: check archon location with current location and clear if necessary
    }
}
