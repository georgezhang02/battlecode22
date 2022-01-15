package dontatme;

import battlecode.common.*;

// For Commmunication Scheme: https://docs.google.com/spreadsheets/d/1U5TSMQ_MRm_tilbJa-L8_SL3OOONUCgavhuT9dSQ4Jg/edit#gid=0
public class Communications {

    // Indices
    private static final int TURN_INFO = 0;

    // TODO: Fix archon ID bug
    private static final int FRIENDLY_ARCHON_OFFSET = 1;
    private static final int ENEMY_ARCHON_OFFSET = 5;
    private static final int LEAD_OFFSET = 9;
    private static final int ATTACK_OFFSET = 13;
    private static final int DEFENSE_OFFSET = 23;
    private static final int BUILD_OFFSET = 33;
    private static final int ANOMALY = 38;

    /**
     * @return the archon that should be active in the alternating turn system
     * @throws GameActionException
     */
    public static int getArchonTurn(RobotController rc) throws GameActionException {
        return decode(rc.readSharedArray(TURN_INFO), 0);
    }

    public static void incrementArchonTurn(RobotController rc) throws GameActionException {
        int arrayValue = rc.readSharedArray(TURN_INFO);
        rc.writeSharedArray(TURN_INFO, encode((decode(arrayValue, 0) + 1) % rc.getArchonCount(), decode(arrayValue, 1)));
    }


    public static int getMinerTurn(RobotController rc) throws GameActionException {
        return decode(rc.readSharedArray(TURN_INFO), 1);
    }

    public static void incrementMinerTurn(RobotController rc, int size) throws GameActionException {
        int arrayValue = rc.readSharedArray(TURN_INFO);
        rc.writeSharedArray(TURN_INFO, encode(decode(arrayValue, 0), (decode(arrayValue, 1) + 1) % size));
    }

    /**
     * @return one lead location within vision of the archon
     * @throws GameActionException
     */
    public static MapLocation getArchonVisionLead(RobotController rc, int archonID) throws GameActionException {
        int archonIndex = getTeamArchonIndexFromID(rc, archonID);
        int arrayValue = rc.readSharedArray(archonIndex + LEAD_OFFSET);
        MapLocation leadLocation = new MapLocation(decode(arrayValue, 0), decode(arrayValue, 1));
        return leadLocation;
    }

    public static void setArchonVisionLead(RobotController rc, int archonID, MapLocation leadLocation) throws GameActionException {
        int archonIndex = getTeamArchonIndexFromID(rc, archonID);
        int arrayValue = rc.readSharedArray(archonIndex+ LEAD_OFFSET);
        rc.writeSharedArray(archonIndex+ LEAD_OFFSET, encode(leadLocation.x, leadLocation.y, decode(arrayValue, 2)));
    }

    public static void setArchonVisionNoLead(RobotController rc, int archonID) throws GameActionException {
        int archonIndex = getTeamArchonIndexFromID(rc, archonID);
        int arrayValue = rc.readSharedArray(archonIndex+ LEAD_OFFSET);
        rc.writeSharedArray(archonIndex+ LEAD_OFFSET, encode(61, 61, decode(arrayValue, 2)));
    }

    /**
     * @return number of miners associated with the archon
     * @throws GameActionException
     */
    public static int getArchonMinerCount(RobotController rc, int archonID) throws GameActionException {
        int archonIndex = getTeamArchonIndexFromID(rc, archonID);
        return decode(rc.readSharedArray(archonIndex+ LEAD_OFFSET), 2);
    }

    public static void setArchonMinerCount(RobotController rc, int archonID, int minerCount) throws GameActionException {
        int archonIndex = getTeamArchonIndexFromID(rc, archonID);
        int arrayValue = rc.readSharedArray(archonIndex+ LEAD_OFFSET);
        rc.writeSharedArray(archonIndex+ LEAD_OFFSET,
                encode(decode(arrayValue, 0), decode(arrayValue, 1), minerCount));
    }

    public static void incrementArchonMinerCount(RobotController rc, int archonID) throws GameActionException {
        int archonIndex = getTeamArchonIndexFromID(rc, archonID);
        int arrayValue = rc.readSharedArray(archonIndex+ LEAD_OFFSET);
        int currentMinerCount = decode(arrayValue, 2);
        rc.writeSharedArray(archonIndex+ LEAD_OFFSET,
                encode(decode(arrayValue, 0), decode(arrayValue, 1), currentMinerCount + 1));
    }
    




    /**
     * @return MapLocation of Team Archon
     * @throws GameActionException
     */
    public static MapLocation getTeamArchonLocation(RobotController rc, int archonID) throws GameActionException {
        int archonIndex = getTeamArchonIndexFromID(rc, archonID);
        int arrayValue = rc.readSharedArray(archonIndex + FRIENDLY_ARCHON_OFFSET);
        MapLocation archonLocation = new MapLocation(decode(arrayValue, 0), decode(arrayValue, 1));
        return archonLocation;
    }

    /**
     * Easier to index and randomize
     * 
     * @return MapLocation of Team Archon through Index 
     * @throws GameActionException
     */
    public static MapLocation getTeamArchonLocationByIndex(RobotController rc, int index) throws GameActionException {
        if (!(index >= 0 && index < GameConstants.MAX_STARTING_ARCHONS)) {
            throw new IllegalArgumentException();
        }
        int arrayValue = rc.readSharedArray(index + FRIENDLY_ARCHON_OFFSET);
        MapLocation archonLocation = new MapLocation(decode(arrayValue, 0), decode(arrayValue, 1));
        return archonLocation;
    }

    public static void setTeamArchonLocation(RobotController rc, int archonID, MapLocation archonLocation) throws GameActionException {
        int writeValue = encode(archonLocation.x, archonLocation.y, archonID);

        for(int i = 0; i< GameConstants.MAX_STARTING_ARCHONS; i++){
            int arrayValue = rc.readSharedArray(i + FRIENDLY_ARCHON_OFFSET);
            int arrID = decode(arrayValue,2);

            rc.setIndicatorString(i+" ");
            if(arrID == archonID || arrID == 0){
                rc.writeSharedArray(i+FRIENDLY_ARCHON_OFFSET, writeValue);
                return;

            }
        }
    }

    public static int getTeamArchonIndexFromID(RobotController rc, int archonID) throws GameActionException {
        for(int i = 0; i < GameConstants.MAX_STARTING_ARCHONS; i++) {
            int arrayValue = rc.readSharedArray(i + FRIENDLY_ARCHON_OFFSET);
            if(arrayValue != 0 && decode(arrayValue, 2) == archonID){
                return i;
            }
        }
        return -1;
    }


    /**
     * @return MapLocation of Enemy Archon (returns (-1, -1) if archon has not been found)
     * @throws GameActionException
     */
    public static MapLocation getEnemyArchonLocation(RobotController rc, int archonID) throws GameActionException {
        int archonIndex = getEnemyArchonIndexFromID(rc, archonID);
        int arrayValue = rc.readSharedArray(archonIndex+ ENEMY_ARCHON_OFFSET);
        MapLocation archonLocation = new MapLocation(decode(arrayValue, 0) - 1, decode(arrayValue, 1) - 1);
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
        MapLocation archonLocation = new MapLocation(decode(arrayValue, 0) - 1, decode(arrayValue, 1) - 1);
        return archonLocation;
    }

    public static void setEnemyArchonLocation(RobotController rc, int archonID, MapLocation archonLocation) throws GameActionException {
        int writeValue = encode(archonLocation.x, archonLocation.y, archonID);

        for(int i = 0; i< GameConstants.MAX_STARTING_ARCHONS; i++){
            int arrayValue = rc.readSharedArray(i + ENEMY_ARCHON_OFFSET);
            int arrID = decode(arrayValue,2);
            if(arrID == archonID || arrID == 0){
                rc.writeSharedArray(i + ENEMY_ARCHON_OFFSET, writeValue);
                return;

            }
        }
    }

    public static int getEnemyArchonIndexFromID(RobotController rc, int archonID) throws GameActionException {
        for(int i = 0; i < GameConstants.MAX_STARTING_ARCHONS; i++) {
            int arrayValue = rc.readSharedArray(i + ENEMY_ARCHON_OFFSET);
            if(arrayValue != 0 && decode(arrayValue, 2) != 0){
                return i;
            }
        }
        return -1;
    }

    



    public static class AttackCommand
    {
        public MapLocation location; 
        public RobotType type;  
        public int ID;

        public AttackCommand(MapLocation location, RobotType type, int ID) {
            this.location = location;
            this.type = type;
            this.ID = ID;
        }
    };

    public static void sendAttackCommand(RobotController rc, MapLocation location, RobotType t, int ID) {
        
        for (int i = 0; i < 10; i++) {
            if (rc.getRoundNum() > currentCommands[i].round + 1) {
                rc.writeSharedArray(i + ATTACK_OFFSET, newCommand);
                return;
            } 
            // archon can overwrite non-archon commands
            else if (currentCommands[i].id > 10000 && id < 10000) {
                rc.writeSharedArray(i + ATTACK_OFFSET, newCommand);
                return;
            }
        }
    }

    public static AttackCommand[] getAttackCommands(RobotController rc) throws GameActionException{
        AttackCommand[] commands = new AttackCommand[10];
        for (int i = 0; i < 10; i++) {
            
            int arrayValue = rc.readSharedArray(i + ATTACK_OFFSET);
            
            MapLocation attackLocation = new MapLocation(decode(arrayValue, 0), decode(arrayValue, 1));
            
            int targetTypeOrdinal = decode(arrayValue, 2);
            RobotType targetType = RobotType.values()[targetTypeOrdinal];
            
            int ID = decode(arrayValue, 3);
            commands[i] = new AttackCommand(attackLocation, targetType, ID);
        }

        return commands;
    }

    /**
     * Send a command to defend (map location, robot type, robot id)
     * Commands are only overwriteable after one full turn 
     * (each command is guaranteed one full turn).
     * Archons can overwrite non-archon commands, regardless of turn.
     * 
     * @throws GameActionException
     */
    public static void sendDefenseCommand(RobotController rc, MapLocation location, RobotType t, int id) throws GameActionException {
        int newCommand = encode(location.x, location.y, t.ordinal(), id, rc.getRoundNum());
        Command[] currentCommands = getDefenseCommand(rc);
        
        for (int i = 0; i < 10; i++) {
            if (rc.getRoundNum() > currentCommands[i].round + 1) {
                rc.writeSharedArray(i + DEFENSE_OFFSET, newCommand);
                return;
            } 
            // archon can overwrite non-archon commands
            else if (currentCommands[i].id > 10000 && id < 10000) {
                rc.writeSharedArray(i + DEFENSE_OFFSET, newCommand);
                return;
            }
        }
    }

    /**
     * Retrieve all defend commands (use turn to determine turn)
     * 
     * @throws GameActionException
     */ 
    public static Command[] getDefenseCommand(RobotController rc) throws GameActionException{
        Command[] commands = new Command[10];
        for (int i = 0; i < 10; i++) {
            
            int arrayValue = rc.readSharedArray(i + DEFENSE_OFFSET);
            
            MapLocation defenseLocation = new MapLocation(decode(arrayValue, 0), decode(arrayValue, 1));
            
            int targetTypeOrdinal = decode(arrayValue, 2);
            RobotType targetType = RobotType.values()[targetTypeOrdinal];
            
            int id = decode(arrayValue, 3);
            int round = decode(arrayValue, 4);
            commands[i] = new Command(defenseLocation, targetType, id, round);
        }

        return commands;
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
