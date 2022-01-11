package dontatme;

import battlecode.common.*;

public class Communications {

    // Indices
    private static final int ARCHON_TURN = 2;




    /**
     * @return the archon that should be active in the alternating turn system
     * @throws GameActionException
     */
    public static int getArchonTurn(RobotController rc) throws GameActionException {
        return rc.readSharedArray(ARCHON_TURN);
    }

    public static void setArchonTurn(RobotController rc, int value) throws GameActionException {
        rc.writeSharedArray(ARCHON_TURN, value);
    }




    /**
     * @return one lead location within vision of the archon
     * @throws GameActionException
     */
    public static MapLocation getArchonVisionLead(RobotController rc, int archonID) throws GameActionException {
        int arrayValue = rc.readSharedArray(archonID / 2);
        MapLocation leadLocation = new MapLocation(arrayValue % 64, (arrayValue / 64) % 64);
        return leadLocation;
    }

    public static void setArchonVisionLead(RobotController rc, int archonID, MapLocation leadLocation) throws GameActionException {
        int arrayValue = rc.readSharedArray(archonID / 2);
        rc.writeSharedArray(archonID / 2, (leadLocation.x * 64 + leadLocation.y) + (arrayValue / 4096 * 4096));
    }




    /**
     * @return number of miners associated with the archon
     * @throws GameActionException
     */
    public static int getArchonMinerCount(RobotController rc, int archonID) throws GameActionException {
        return rc.readSharedArray(archonID / 2) / 4096;
    }

    public static void setArchonMinerCount(RobotController rc, int archonID, int minerCount) throws GameActionException {
        int arrayValue = rc.readSharedArray(archonID / 2); 
        rc.writeSharedArray(archonID / 2, (minerCount * 4096) + (arrayValue % 4096));
    }

    public static void incrementMinerCount(RobotController rc, int archonID) throws GameActionException {
        int arrayValue = rc.readSharedArray(archonID / 2); 
        int currentMinerCount = arrayValue / 4096;
        rc.writeSharedArray(archonID / 2, (currentMinerCount + 1 * 4096) + (arrayValue % 4096));
    }
    




    /**
     * @return MapLocation of Team Archon
     * @throws GameActionException
     */
    public static MapLocation getTeamArchonLocation(RobotController rc, int archonID) throws GameActionException {
        int arrayValue = rc.readSharedArray(archonID / 2 + 4);
        MapLocation archonLocation = new MapLocation(arrayValue % 64, arrayValue / 64);
        return archonLocation;
    }

    /**
     * Can be used for randomizing
     * 
     * @return MapLocation of Team Archon through Index 
     * @throws GameActionException
     */
    public static MapLocation getTeamArchonLocationByIndex(RobotController rc, int index) throws GameActionException {
        if (!(index > 0 && index < rc.getArchonCount())) {
            throw new IllegalArgumentException();
        }
        int arrayValue = rc.readSharedArray(index + 4);
        MapLocation archonLocation = new MapLocation(arrayValue % 64, arrayValue / 64);
        return archonLocation;
    }

    public static void setTeamArchonLocation(RobotController rc, int archonID, MapLocation archonLocation) throws GameActionException {
        int writeValue = archonLocation.x * 64 + archonLocation.y;
        rc.writeSharedArray(archonID / 2 + 4, writeValue);
    }




    /**
     * @return MapLocation of Enemy Archon
     * @throws GameActionException
     */
    public static MapLocation getEnemyArchonLocation(RobotController rc, int archonID) throws GameActionException {
        int arrayValue = rc.readSharedArray(archonID / 2 + 8);
        MapLocation archonLocation = new MapLocation(arrayValue % 64, arrayValue / 64);
        return archonLocation;
    }

    /**
     * Can be used for randomizing
     * 
     * @return MapLocation of Enemy Archon through Index 
     * @throws GameActionException
     */
    public static MapLocation getEnemyArchonLocationByIndex(RobotController rc, int index) throws GameActionException {
        if (!(index > 0 && index < GameConstants.MAX_STARTING_ARCHONS)) {
            throw new IllegalArgumentException();
        }
        int arrayValue = rc.readSharedArray(index + 8);
        MapLocation archonLocation = new MapLocation(arrayValue % 64, arrayValue / 64);
        return archonLocation;
    }

    public static void setEnemyArchonLocation(RobotController rc, int archonID, MapLocation archonLocation) throws GameActionException {
        int writeValue = archonLocation.x * 64 + archonLocation.y;
        rc.writeSharedArray(archonID / 2 + 8, writeValue);
    }
}
