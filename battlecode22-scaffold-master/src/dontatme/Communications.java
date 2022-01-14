package dontatme;

import battlecode.common.*;

// For Commmunication Scheme: https://docs.google.com/spreadsheets/d/1U5TSMQ_MRm_tilbJa-L8_SL3OOONUCgavhuT9dSQ4Jg/edit#gid=0
public class Communications {

    // Indices
    private static final int ARCHON_TURN = 0;

    // TODO: Fix archon ID bug
    private static final int ARCHON_IDS = 1;

    private static final int FRIENDLY_ARCHON_OFFSET = 5;
    private static final int ENEMY_ARCHON_OFFSET = 9;

    /**
     * @return the archon that should be active in the alternating turn system
     * @throws GameActionException
     */
    public static int getArchonTurn(RobotController rc) throws GameActionException {
        return decode(rc.readSharedArray(ARCHON_TURN), 0);
    }

    public static void incrementArchonTurn(RobotController rc) throws GameActionException {
        int arrayValue = rc.readSharedArray(ARCHON_TURN);
        rc.writeSharedArray(ARCHON_TURN, encode(decode(arrayValue, 0) + 1, decode(arrayValue, 1)));
    }


    public static int getMinerTurn(RobotController rc) throws GameActionException {
        return decode(rc.readSharedArray(ARCHON_TURN), 1);
    }

    public static void incrementMinerTurn(RobotController rc, int size) throws GameActionException {
        int arrayValue = rc.readSharedArray(ARCHON_TURN);
        rc.writeSharedArray(ARCHON_TURN, encode(decode(arrayValue, 0), (decode(arrayValue, 1) + 1) % size));
    }


    /**
     * @return one lead location within vision of the archon
     * @throws GameActionException
     */
    public static MapLocation getArchonVisionLead(RobotController rc, int archonID) throws GameActionException {
        int arrayValue = rc.readSharedArray(archonID / 2);
        MapLocation leadLocation = new MapLocation(decode(arrayValue, 1), decode(arrayValue, 0));
        return leadLocation;
    }

    public static void setArchonVisionLead(RobotController rc, int archonID, MapLocation leadLocation) throws GameActionException {
        int arrayValue = rc.readSharedArray(archonID / 2);
        rc.writeSharedArray(archonID / 2, encode(leadLocation.x, leadLocation.y, decode(arrayValue, 2)));
    }

    public static void setArchonVisionNoLead(RobotController rc, int archonID) throws GameActionException {
        int arrayValue = rc.readSharedArray(archonID / 2);
        rc.writeSharedArray(archonID / 2, encode(61, 61, decode(arrayValue, 2)));
    }

    /**
     * @return number of miners associated with the archon
     * @throws GameActionException
     */
    public static int getArchonMinerCount(RobotController rc, int archonID) throws GameActionException {
        return decode(rc.readSharedArray(archonID / 2), 2);
    }

    public static void setArchonMinerCount(RobotController rc, int archonID, int minerCount) throws GameActionException {
        int arrayValue = rc.readSharedArray(archonID / 2); 
        rc.writeSharedArray(archonID / 2, encode(decode(arrayValue, 0), decode(arrayValue, 1), minerCount));
    }

    public static void incrementArchonMinerCount(RobotController rc, int archonID) throws GameActionException {
        int arrayValue = rc.readSharedArray(archonID / 2); 
        int currentMinerCount = decode(arrayValue, 2);
        rc.writeSharedArray(archonID / 2, encode(decode(arrayValue, 0), decode(arrayValue, 1), currentMinerCount + 1));
    }
    




    /**
     * @return MapLocation of Team Archon
     * @throws GameActionException
     */
    public static MapLocation getTeamArchonLocation(RobotController rc, int archonID) throws GameActionException {
        int arrayValue = rc.readSharedArray(archonID / 2 + FRIENDLY_ARCHON_OFFSET);
        MapLocation archonLocation = new MapLocation(decode(arrayValue, 1), decode(arrayValue, 0));
        return archonLocation;
    }

    /**
     * Easier to index and randomize
     * 
     * @return MapLocation of Team Archon through Index 
     * @throws GameActionException
     */
    public static MapLocation getTeamArchonLocationByIndex(RobotController rc, int index) throws GameActionException {
        if (!(index > 0 && index <= rc.getArchonCount())) {
            throw new IllegalArgumentException();
        }
        int arrayValue = rc.readSharedArray(index + FRIENDLY_ARCHON_OFFSET);
        MapLocation archonLocation = new MapLocation(decode(arrayValue, 1), decode(arrayValue, 0));
        return archonLocation;
    }

    public static void setTeamArchonLocation(RobotController rc, int archonID, MapLocation archonLocation) throws GameActionException {
        int writeValue = encode(archonLocation.x, archonLocation.y);
        rc.writeSharedArray(archonID / 2 + FRIENDLY_ARCHON_OFFSET, writeValue);
    }




    /**
     * @return MapLocation of Enemy Archon (returns (-1, -1) if archon has not been found)
     * @throws GameActionException
     */
    public static MapLocation getEnemyArchonLocation(RobotController rc, int archonID) throws GameActionException {
        int arrayValue = rc.readSharedArray(archonID / 2 + ENEMY_ARCHON_OFFSET);
        MapLocation archonLocation = new MapLocation(decode(arrayValue, 1) - 1, decode(arrayValue, 0) - 1);
        return archonLocation;
    }

    /**
     * Easier to index and randomize
     * 
     * @return MapLocation of Enemy Archon through Index (returns (-1, -1) if archon has not been found)
     * @throws GameActionException
     */
    public static MapLocation getEnemyArchonLocationByIndex(RobotController rc, int index) throws GameActionException {
        if (!(index >= 0 && index < GameConstants.MAX_STARTING_ARCHONS)) {
                throw new IllegalArgumentException();
        }
        int arrayValue = rc.readSharedArray(index + ENEMY_ARCHON_OFFSET);
        MapLocation archonLocation = new MapLocation(decode(arrayValue, 1) - 1, decode(arrayValue, 0) - 1);
        return archonLocation;
    }

    public static void setEnemyArchonLocation(RobotController rc, int archonID, MapLocation archonLocation) throws GameActionException {
        int writeValue = encode(archonLocation.x + 1, archonLocation.y + 1);
        rc.writeSharedArray(archonID / 2 + ENEMY_ARCHON_OFFSET, writeValue);
    }

    /**
     * Decodes array value into suedo bytes
     * 
     * could be hardcoded to save slight bytecode
     * 
     * index 0:
     *      number % 64 / 1
     * index 1:
     *      number % 64^2 / 64
     * ...
     */
    private static int decode(int number, int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        } else {
            return (number % (int)Math.pow(64, index + 1) / (int)Math.pow(64, index));
        }
    }

    /**
     * Encodes a variable number of values into a single array value ready to be written
     */
    private static int encode(int ...fields) {
        int result = 0;
        for (int i = 0; i < fields.length; i++) {
            result += fields[i] * Math.pow(64, i);
        }
        return result;
    }
}
