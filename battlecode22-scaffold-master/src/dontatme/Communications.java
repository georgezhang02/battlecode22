package dontatme;

import battlecode.common.*;

import java.util.Objects;

// For Commmunication Scheme: https://docs.google.com/spreadsheets/d/1U5TSMQ_MRm_tilbJa-L8_SL3OOONUCgavhuT9dSQ4Jg/edit#gid=0
public class Communications {
    // Indices
    public static final int TURN_INFO = 0;

    private static final int FRIENDLY_ARCHON_OFFSET_EVEN = 1;
    private static final int FRIENDLY_ARCHON_OFFSET_ODD = 5;

    private static final int ENEMY_ARCHON_OFFSET = 9;
    private static final int LEAD_OFFSET = 13;
    public static final int ATTACK_EVEN_OFFSET = 17;
    public static final int ATTACK_ODD_OFFSET = 27;
    public static final int DEFENSE_EVEN_OFFSET = 37;
    public static final int DEFENSE_ODD_OFFSET = 40;
    public static final int BUILD_EVEN_OFFSET = 43;
    public static final int BUILD_ODD_OFFSET = 45;
    private static final int LEAD_EVEN_OFFSET = 47;
    private static final int LEAD_ODD_OFFSET = 50;
    private static final int MOVE_TO_EVEN_OFFSET = 53;
    private static final int MOVE_TO_ODD_OFFSET = 54;
    private static final int ARCHON_MOVING = 55;
    private static final int HAS_WIPED = 58;
    private static final int NEXT_INDICES = 59;
    private static final int GOLD_UNIT_COUNT_OFFSET = 60;
    private static final int LEAD_UNIT_COUNT_OFFSET = 62;

    public static final int ATTACK_SIZE = ATTACK_ODD_OFFSET - ATTACK_EVEN_OFFSET;
    public static final int DEFENSE_SIZE = DEFENSE_ODD_OFFSET - DEFENSE_EVEN_OFFSET;
    public static final int BUILD_SIZE = BUILD_ODD_OFFSET - BUILD_EVEN_OFFSET;
    private static final int NULL_LOCATION = 61;

    // cleans out your previous commands and adds your robot type to the ongoing
    // count
    public static void runStart(RobotController rc) throws GameActionException {

        switch (rc.getType()) {
            case ARCHON:
                setTeamArchonLocation(rc, rc.getID(), rc.getLocation());
                if (hasWiped(rc)) {
                    break;
                }
                checkTeamArchonDied(rc);
                wipeAttackCommands(rc);
                wipeDefCommands(rc);
                wipeUnitCounts(rc);
                wipeBuildCommands(rc);
                wipeNextIndex(rc);
                wipeArchonOrder(rc);
                wipeLeadLocation(rc);
                wipeMoveToCommands(rc);
                wipeArchonLocation(rc);
                setWiped(rc);
                break;
            case MINER:
                incrementMinerCount(rc);
                break;
            case SOLDIER:
                incrementSoldierCount(rc);
                break;
            case BUILDER:
                incrementBuilderCount(rc);
                break;
            case LABORATORY:
                incrementLabCount(rc);
                break;
            case SAGE:
                incrementSageCount(rc);
            default:
                break;
        }
    }

    /**
     * @return the archon that should be active in the alternating turn system
     * @throws GameActionException
     */
    public static int getArchonTurn(RobotController rc) throws GameActionException {
        return decode(rc.readSharedArray(TURN_INFO), 0) % rc.getArchonCount();
    }

    public static void incrementArchonTurn(RobotController rc) throws GameActionException {
        int arrayValue = rc.readSharedArray(TURN_INFO);
        rc.writeSharedArray(TURN_INFO, encode((decode(arrayValue, 0) + 1) % rc.getArchonCount(),
                decode(arrayValue, 1), decode(arrayValue, 2)));
    }

    public static int getMinerTurn(RobotController rc) throws GameActionException {
        return decode(rc.readSharedArray(TURN_INFO), 1);
    }

    public static void incrementMinerTurn(RobotController rc, int locInd) throws GameActionException {
        int arrayValue = rc.readSharedArray(TURN_INFO);
        rc.writeSharedArray(TURN_INFO, encode(decode(arrayValue, 0), decode(arrayValue, 1) + (int) Math.pow(2, locInd)));
        // System.out.println(decode(arrayValue, 1) + (int) Math.pow(2, locInd));
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

    public static void setArchonVisionLead(RobotController rc, int archonID, MapLocation leadLocation)
            throws GameActionException {
        int archonIndex = getTeamArchonIndexFromID(rc, archonID);

        int arrayValue = rc.readSharedArray(archonIndex + LEAD_OFFSET);
        if (archonIndex != -1) {
            rc.writeSharedArray(archonIndex + LEAD_OFFSET,
                    encode(leadLocation.x, leadLocation.y, decode(arrayValue, 2)));
        }
    }

    public static void setArchonVisionNoLead(RobotController rc, int archonID) throws GameActionException {
        int archonIndex = getTeamArchonIndexFromID(rc, archonID);
        if (archonIndex != -1) {
            int arrayValue = rc.readSharedArray(archonIndex + LEAD_OFFSET);
            rc.writeSharedArray(archonIndex + LEAD_OFFSET, encode(NULL_LOCATION, NULL_LOCATION, decode(arrayValue, 2)));
        }

    }

    /**
     * @return number of miners associated with the archon
     * @throws GameActionException
     */
    public static int getArchonMinerCount(RobotController rc, int archonID) throws GameActionException {
        int archonIndex = getTeamArchonIndexFromID(rc, archonID);
        return decode(rc.readSharedArray(archonIndex + LEAD_OFFSET), 2);
    }

    public static void setArchonMinerCount(RobotController rc, int archonID, int minerCount)
            throws GameActionException {
        int archonIndex = getTeamArchonIndexFromID(rc, archonID);
        int arrayValue = rc.readSharedArray(archonIndex + LEAD_OFFSET);
        rc.writeSharedArray(archonIndex + LEAD_OFFSET,
                encode(decode(arrayValue, 0), decode(arrayValue, 1), minerCount));
    }

    public static void incrementArchonMinerCount(RobotController rc, int archonID) throws GameActionException {
        int archonIndex = getTeamArchonIndexFromID(rc, archonID);
        int arrayValue = rc.readSharedArray(archonIndex + LEAD_OFFSET);
        int currentMinerCount = decode(arrayValue, 2);
        // "+currentMinerCount + " ");
        rc.writeSharedArray(archonIndex + LEAD_OFFSET,
                encode(decode(arrayValue, 0), decode(arrayValue, 1), currentMinerCount + 1));
    }

    /**
     * @return MapLocation of Team Archon
     * @throws GameActionException
     */
    public static MapLocation getTeamArchonLocation(RobotController rc, int archonID) throws GameActionException {
        int archonIndex = getTeamArchonIndexFromID(rc, archonID);

        if(archonIndex != -1){
            int offset = (rc.getRoundNum() % 2 == 0) ? FRIENDLY_ARCHON_OFFSET_ODD : FRIENDLY_ARCHON_OFFSET_EVEN;

            int arrayValue = rc.readSharedArray(archonIndex + offset);
            MapLocation archonLocation = new MapLocation(decode(arrayValue, 0), decode(arrayValue, 1));
            return archonLocation;
        } else{
            return null;
        }

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
        int offset = (rc.getRoundNum() % 2 == 0) ? FRIENDLY_ARCHON_OFFSET_ODD : FRIENDLY_ARCHON_OFFSET_EVEN;

        int arrayValue = rc.readSharedArray(index + offset);
        MapLocation archonLocation = new MapLocation(decode(arrayValue, 0), decode(arrayValue, 1));
        return archonLocation;
    }

    public static void setTeamArchonLocation(RobotController rc, int archonID, MapLocation archonLocation)
            throws GameActionException {
        int writeValue = encode(archonLocation.x, archonLocation.y, archonID);
        int offset = (rc.getRoundNum() % 2 == 0) ? FRIENDLY_ARCHON_OFFSET_EVEN : FRIENDLY_ARCHON_OFFSET_ODD;

        for (int i = 0; i < GameConstants.MAX_STARTING_ARCHONS; i++) {
            int arrayValue = rc.readSharedArray(i + offset);
            int arrID = decode(arrayValue, 2);
            int arrX = decode(arrayValue, 0);

            if (arrID == archonID || arrX >= 60) {
                rc.writeSharedArray(i + offset, writeValue);
                return;

            }
        }
    }

    public static void setTeamArchonLocationByIndex(RobotController rc, int archonID, int index,
            MapLocation archonLocation) throws GameActionException {
        if (!(index >= 0 && index < GameConstants.MAX_STARTING_ARCHONS)) {
            throw new IllegalArgumentException();
        }
        int offset = (rc.getRoundNum() % 2 == 0) ? FRIENDLY_ARCHON_OFFSET_EVEN : FRIENDLY_ARCHON_OFFSET_ODD;
        int writeValue = encode(archonLocation.x, archonLocation.y, archonID);
        rc.writeSharedArray(index + offset, writeValue);

    }

    public static int getTeamArchonIndexFromID(RobotController rc, int archonID) throws GameActionException {
        int offset = (rc.getRoundNum() % 2 == 0) ? FRIENDLY_ARCHON_OFFSET_ODD : FRIENDLY_ARCHON_OFFSET_EVEN;

        for (int i = 0; i < GameConstants.MAX_STARTING_ARCHONS; i++) {
            int arrayValue = rc.readSharedArray(i + offset);
            int arrID = decode(arrayValue, 2);
            if (arrID == archonID) {
                return i;
            }
        }
        return -1;
    }

    public static int getTeamArchonIDFromIndex(RobotController rc, int index) throws GameActionException {
        int offset = (rc.getRoundNum() % 2 == 0) ? FRIENDLY_ARCHON_OFFSET_ODD : FRIENDLY_ARCHON_OFFSET_EVEN;
        return decode(rc.readSharedArray(index + offset), 2);
    }


    public static void wipeArchonLocation(RobotController rc) throws GameActionException {
        int offset = (rc.getRoundNum() % 2 == 0) ? FRIENDLY_ARCHON_OFFSET_EVEN : FRIENDLY_ARCHON_OFFSET_ODD;
        for (int i = 0; i < GameConstants.MAX_STARTING_ARCHONS; i++) {
            rc.writeSharedArray(i + offset, encode(NULL_LOCATION, NULL_LOCATION));
        }
    }


    /**
     * @return MapLocation of Enemy Archon
     * @throws GameActionException
     */
    public static MapLocation getEnemyArchonLocation(RobotController rc, int archonID) throws GameActionException {
        int archonIndex = getEnemyArchonIndexFromID(rc, archonID);
        int arrayValue = rc.readSharedArray(archonIndex + ENEMY_ARCHON_OFFSET);
        MapLocation archonLocation = new MapLocation(decode(arrayValue, 0) - 1, decode(arrayValue, 1) - 1);
        return archonLocation;
    }

    /**
     * Easier to index and randomize
     * 
     * @return MapLocation of Enemy Archon through Index
     * @throws GameActionException
     */
    public static MapLocation getEnemyArchonLocationByIndex(RobotController rc, int index) throws GameActionException {
        if (!(index >= 0 && index < GameConstants.MAX_STARTING_ARCHONS)) {
            throw new IllegalArgumentException();
        }
        int arrayValue = rc.readSharedArray(index + ENEMY_ARCHON_OFFSET);
        MapLocation archonLocation = new MapLocation(decode(arrayValue, 0), decode(arrayValue, 1));
        return archonLocation;
    }

    public static void setEnemyArchonLocation(RobotController rc, int archonID, MapLocation archonLocation)
            throws GameActionException {
        int writeValue = encode(archonLocation.x, archonLocation.y, archonID);

        for (int i = 0; i < GameConstants.MAX_STARTING_ARCHONS; i++) {
            int arrayValue = rc.readSharedArray(i + ENEMY_ARCHON_OFFSET);
            int arrID = decode(arrayValue, 2);
            int arrX = decode(arrayValue, 0);
            if (arrID == archonID || arrX == 60) {
                rc.writeSharedArray(i + ENEMY_ARCHON_OFFSET, writeValue);
                return;

            }
        }
    }

    public static void setEnemyArchonLocationByIndex(RobotController rc, int archonID, int index,
            MapLocation archonLocation) throws GameActionException {
        if (!(index >= 0 && index < GameConstants.MAX_STARTING_ARCHONS)) {
            throw new IllegalArgumentException();
        }
        int writeValue = encode(archonLocation.x, archonLocation.y, archonID);
        rc.writeSharedArray(index + ENEMY_ARCHON_OFFSET, writeValue);

    }

    public static int getEnemyArchonIndexFromID(RobotController rc, int archonID) throws GameActionException {
        for (int i = 0; i < GameConstants.MAX_STARTING_ARCHONS; i++) {
            int arrayValue = rc.readSharedArray(i + ENEMY_ARCHON_OFFSET);
            int arrID = decode(arrayValue, 2);
            int arrX = decode(arrayValue, 0);

            if (arrX < 60 && arrID == archonID) {
                return i;
            }
        }
        return -1;
    }

    public static int getEnemyArchonIDFromIndex(RobotController rc, int index) throws GameActionException {
        return decode(rc.readSharedArray(index + ENEMY_ARCHON_OFFSET), 2);
    }

    public static class Command {
        public MapLocation location;
        public RobotType type;
        public boolean isAttack;
        public int num;

        public Command(MapLocation location, RobotType type, boolean isAttack) {
            this.location = location;
            this.type = type;
            this.isAttack = isAttack;
        }

        public Command(MapLocation location, int num, boolean isAttack) {
            this.location = location;
            this.num = num;
            this.isAttack = isAttack;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null)
                return false;
            Command command = (Command) o;
            return Objects.equals(location, command.location) && type == command.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(location, type);
        }

        public int priority() {
            return getCommandPrio(type, isAttack);
        }
    };

    /**
     * Send a command to attack (map location, robot type, robot id).
     * Commands are only overwriteable after one full turn
     * (each command is guaranteed one full turn).
     * Archons can overwrite non-archon commands, regardless of turn.
     * 
     * @return a boolean of whether the command was succesfully published
     * @throws GameActionException
     */
    public static boolean sendAttackCommand(RobotController rc, MapLocation location, RobotType t)
            throws GameActionException {
        int offset = (rc.getRoundNum() % 2 == 0) ? ATTACK_EVEN_OFFSET : ATTACK_ODD_OFFSET;
        int newCommand = encode(location.x, location.y, t.ordinal());

        int nextIndex = getNextAttackIndex(rc) + offset;
        int readVal = rc.readSharedArray(nextIndex);
        if (decode(readVal, 0) >= 60 || rc.getType().equals(RobotType.ARCHON)) {
            rc.writeSharedArray(nextIndex, newCommand);
            incrementAttackIndex(rc);

            return true;
        }
        //System.out.println("UGH OH TOO MANY ATTACK COMMANDS!: " + nextIndex);
        return false;
    }

    public static boolean sendStopAttackCommand(RobotController rc, MapLocation location) throws GameActionException {
        int offset = (rc.getRoundNum() % 2 == 0) ? ATTACK_EVEN_OFFSET : ATTACK_ODD_OFFSET;
        int newCommand = encode(location.x, location.y, 15);

        int nextIndex = getNextAttackIndex(rc) + offset;
        int readVal = rc.readSharedArray(nextIndex);
        if (decode(readVal, 0) == NULL_LOCATION || rc.getType().equals(RobotType.ARCHON)) {
            rc.writeSharedArray(nextIndex, newCommand);
            incrementAttackIndex(rc);

            return true;
        }
        return false;
    }

    /**
     * Retrieve all attack commands (use turn to determine turn)
     * 
     * @throws GameActionException
     */
    public static Command[] getAttackCommands(RobotController rc) throws GameActionException {
        int offset = (rc.getRoundNum() % 2 == 0) ? ATTACK_ODD_OFFSET : ATTACK_EVEN_OFFSET;
        Command[] commands = new Command[ATTACK_SIZE];
        for (int i = 0; i < ATTACK_SIZE; i++) {

            commands[i] = getCommandFromArray(rc, i + offset);
        }

        return commands;
    }

    /**
     * Send a command to defend (map location, robot type, robot id)
     * Commands are only overwriteable after one full turn
     * (each command is guaranteed one full turn).
     * Archons can overwrite non-archon commands, regardless of turn.
     * 
     * @return a boolean of whether the command was succesfully published
     * @throws GameActionException
     */
    public static boolean sendDefenseCommand(RobotController rc, MapLocation location, RobotType t)
            throws GameActionException {
        int offset = (rc.getRoundNum() % 2 == 0) ? DEFENSE_EVEN_OFFSET : DEFENSE_ODD_OFFSET;
        int newCommand = encode(location.x, location.y, t.ordinal());

        int nextIndex = getNextDefIndex(rc) + offset;
        int readVal = rc.readSharedArray(nextIndex);
        if ((decode(readVal, 0) >= 60 || rc.getType().equals(RobotType.ARCHON))) {
            rc.setIndicatorString("Defend " + location);
            rc.writeSharedArray(nextIndex, newCommand);
            incrementDefIndex(rc);

            return true;
        }
        return false;
    }

    public static boolean sendStopDefenseCommand(RobotController rc, MapLocation location) throws GameActionException {
        int offset = (rc.getRoundNum() % 2 == 0) ? DEFENSE_EVEN_OFFSET : DEFENSE_ODD_OFFSET;
        int newCommand = encode(location.x, location.y, 15);

        int nextIndex = getNextDefIndex(rc) + offset;
        int readVal = rc.readSharedArray(nextIndex);
        if ((decode(readVal, 0) == NULL_LOCATION || rc.getType().equals(RobotType.ARCHON))) {
            rc.writeSharedArray(nextIndex, newCommand);
            incrementDefIndex(rc);

            return true;
        }
        return false;
    }

    /**
     * Retrieve all defend commands (use turn to determine turn)
     *
     * @throws GameActionException
     */
    public static Command[] getDefenseCommand(RobotController rc) throws GameActionException {
        int offset = (rc.getRoundNum() % 2 == 0) ? DEFENSE_ODD_OFFSET : DEFENSE_EVEN_OFFSET;
        Command[] commands = new Command[DEFENSE_SIZE];
        for (int i = 0; i < DEFENSE_SIZE; i++) {
            commands[i] = getCommandFromArray(rc, i + offset);
        }

        return commands;
    }

    /**
     * Send a command for archons to move to(map location, robot type, robot id)
     * Commands are only overwriteable after one full turn
     * (each command is guaranteed one full turn).
     *
     * @throws GameActionException
     */
    public static boolean sendMoveToCommand(RobotController rc, MapLocation location, int numEnemies) throws GameActionException {
        int index = (rc.getRoundNum() % 2 == 0) ? MOVE_TO_EVEN_OFFSET : MOVE_TO_ODD_OFFSET;
        int newCommand = encode(location.x, location.y, Math.max(numEnemies, 15));

        int readVal = rc.readSharedArray(index);
        if ((decode(readVal, 0) == NULL_LOCATION || decode(readVal, 2) < numEnemies)) {
            rc.writeSharedArray(index, newCommand);

            return true;
        }
        return false;
    }


    /**
     * Retrieve all moveTo commands (use turn to determine turn)
     *
     * @throws GameActionException
     */
    public static Command getMoveToCommand(RobotController rc) throws GameActionException {
        int index = (rc.getRoundNum() % 2 == 0) ? MOVE_TO_ODD_OFFSET : MOVE_TO_EVEN_OFFSET;
        int readVal = rc.readSharedArray(index);
        Command command = new Command(
                new MapLocation(decode(readVal, 0), decode(readVal, 1)),
                decode(readVal, 2), true
        );

        return command;
    }

    /**
     * Returns the priority of the command
     */
    private static int getCommandPrio(RobotType type, boolean attacking) {
        if (type == null) {
            return 100;
        }
        switch (type) {
            case ARCHON:
                if (attacking) {
                    return 10;
                } else {
                    return 9;
                }
            case MINER:
                if (attacking) {
                    return 5;
                } else {
                    return 4;
                }
            case SOLDIER:
                if (attacking) {
                    return 7;
                } else {
                    return 6;
                }

            case WATCHTOWER:
                if (attacking) {
                    return 6;
                } else {
                    return 7;
                }
            default:
                break;
        }

        switch (type) {
            case ARCHON:
                if (attacking) {
                    return 10;
                } else {
                    return 9;
                }
            case WATCHTOWER:
                if (attacking) {
                    return 6;
                } else {
                    return 7;
                }
            default:
                break;
        }
        return -1;
    }

    /**
     * Returns how long the command executes for.
     * 
     * @return
     */
    public static int getCommandCooldown(RobotController rc, RobotType type, boolean attacking) {
        if (type == null) {
            return 0;
        }
        switch (type) {
            case ARCHON:
                if (attacking) {
                    return 300 * (rc.getMapHeight() + rc.getMapWidth()) / 120;
                } else {
                    return 10 * (rc.getMapHeight() + rc.getMapWidth()) / 120;
                }
            case MINER:
                return 10;

            case SOLDIER:
                return 10;

            case WATCHTOWER:
                if (attacking) {
                    return 50;
                } else {
                    return 50;
                }
            default:
                break;

        }
        return -1;
    }

    /**
     * Gets if your robot was within the command radius.
     */
    public static boolean inCommandRadius(RobotController rc, RobotType type, MapLocation ml, boolean attacking) {
        if (type == null) {
            return Math.sqrt(rc.getLocation().distanceSquaredTo(ml)) <= 3600;
        }
        switch (type) {
            case ARCHON:
                if (attacking) {
                    return Math.sqrt(rc.getLocation().distanceSquaredTo(ml)) <= 120;
                } else {
                    return Math.sqrt(rc.getLocation().distanceSquaredTo(ml)) <= (rc.getMapWidth() + rc.getMapHeight())
                            / 4;
                }
            case MINER:
                return Math.sqrt(rc.getLocation().distanceSquaredTo(ml)) <= 20
                        * (.5 + .5 * (rc.getMapWidth() + rc.getMapHeight()) / 120);
            case SOLDIER:
                return Math.sqrt(rc.getLocation().distanceSquaredTo(ml)) <= 20
                        * (.5 + .5 * (rc.getMapWidth() + rc.getMapHeight()) / 120);
            case WATCHTOWER:
                if (attacking) {
                    return Math.sqrt(rc.getLocation().distanceSquaredTo(ml)) <= 120;
                } else {
                    return Math.sqrt(rc.getLocation().distanceSquaredTo(ml)) <= 120;
                }
            default:
                break;
        }
        return false;
    }


    /**
     * Gets the command at the given index in the shared array.
     */
    public static Command getCommandFromArray(RobotController rc, int index) throws GameActionException {
        int arrayValue = rc.readSharedArray(index);

        MapLocation location = new MapLocation(decode(arrayValue, 0), decode(arrayValue, 1));

        int targetTypeOrdinal = decode(arrayValue, 2);
        
        RobotType targetType = null;
        
        if (targetTypeOrdinal <= 6) {
            targetType = RobotType.values()[targetTypeOrdinal];
        }

        return new Command(location, targetType, index < DEFENSE_EVEN_OFFSET);
    }

    /**
     * Cleans out the command from the shared array given that it is equal to the
     * given command.
     */
    public static boolean cleanCommand(RobotController rc, int index, int command) throws GameActionException {
        if (index != -1) {
            if (rc.readSharedArray(index) == command) {
                rc.writeSharedArray(index, NULL_LOCATION);
            }
        }
        return true;

    }

    /**
     * Wipes unit counts to prepare for current round of incrementing unit counts.
     */
    public static void wipeUnitCounts(RobotController rc) throws GameActionException {
        rc.writeSharedArray(rc.getRoundNum() % 2 + LEAD_UNIT_COUNT_OFFSET, 0);
        rc.writeSharedArray(rc.getRoundNum() % 2 + GOLD_UNIT_COUNT_OFFSET, 0);
    }

    /**
     * Gets unit counts in miner, solder, builder order.
     * 
     * @param rc
     * @return
     * @throws GameActionException
     */
    public static int[] getUnitCounts(RobotController rc) throws GameActionException {
        int readValLead = rc.readSharedArray(((rc.getRoundNum() + 1) % 2) + LEAD_UNIT_COUNT_OFFSET);
        int readValGold = rc.readSharedArray(((rc.getRoundNum() + 1) % 2) + GOLD_UNIT_COUNT_OFFSET);

        int minerCount = decode(readValLead, 0);
        int soldierCount = decode(readValLead, 1);
        int builderCount = decode(readValLead, 2);
        int labCount = decode(readValGold, 0);
        int sageCount = decode(readValGold, 1); 

        return new int[] { minerCount, soldierCount, builderCount, labCount, sageCount };

    }

    /**
     * Increments the overall miner count of team by 1.
     * 
     * @param rc
     * @throws GameActionException
     */
    public static void incrementMinerCount(RobotController rc) throws GameActionException {
        int readVal = rc.readSharedArray((rc.getRoundNum() % 2) + LEAD_UNIT_COUNT_OFFSET);

        int minerCount = decode(readVal, 0);
        int soldierCount = decode(readVal, 1);
        int builderCount = decode(readVal, 2);

        if (minerCount < 63) {
            int writeVal = encode(minerCount + 1, soldierCount, builderCount);

            rc.writeSharedArray(rc.getRoundNum() % 2 + LEAD_UNIT_COUNT_OFFSET, writeVal);
        }

    }

    /**
     * Increments the overall soldier count of team by 1.
     * 
     * @param rc
     * @throws GameActionException
     */
    public static void incrementSoldierCount(RobotController rc) throws GameActionException {
        int readVal = rc.readSharedArray(rc.getRoundNum() % 2 + LEAD_UNIT_COUNT_OFFSET);

        int minerCount = decode(readVal, 0);
        int soldierCount = decode(readVal, 1);
        int builderCount = decode(readVal, 2);

        if (soldierCount < 63) {

            int writeVal = encode(minerCount, soldierCount + 1, builderCount);

            rc.writeSharedArray(rc.getRoundNum() % 2 + LEAD_UNIT_COUNT_OFFSET, writeVal);
        }

    }

    /**
     * Increments the overall builder count of team by 1.
     * 
     * @param rc
     * @throws GameActionException
     */
    public static void incrementBuilderCount(RobotController rc) throws GameActionException {
        int readVal = rc.readSharedArray(rc.getRoundNum() % 2 + LEAD_UNIT_COUNT_OFFSET);

        int minerCount = decode(readVal, 0);
        int soldierCount = decode(readVal, 1);
        int builderCount = decode(readVal, 2);

        if (builderCount < 15) {
            int writeVal = encode(minerCount, soldierCount, builderCount + 1);

            rc.writeSharedArray(rc.getRoundNum() % 2 + LEAD_UNIT_COUNT_OFFSET, writeVal);
        }
    }

    /**
     * Increments the overall laboratory count of team by 1.
     * 
     * @param rc
     * @throws GameActionException
     */
    public static void incrementLabCount(RobotController rc) throws GameActionException {
        int readVal = rc.readSharedArray(rc.getRoundNum() % 2 + GOLD_UNIT_COUNT_OFFSET);

        int labCount = decode(readVal, 0);
        int sageCount = decode(readVal, 1);

        int writeVal = encode(labCount + 1, sageCount);
        rc.writeSharedArray(rc.getRoundNum() % 2 + LEAD_UNIT_COUNT_OFFSET, writeVal);
    }

    /**
     * Increments the overall sage count of team by 1.
     * 
     * @param rc
     * @throws GameActionException
     */
    public static void incrementSageCount(RobotController rc) throws GameActionException {
        int readVal = rc.readSharedArray(rc.getRoundNum() % 2 + GOLD_UNIT_COUNT_OFFSET);

        int labCount = decode(readVal, 0);
        int sageCount = decode(readVal, 1);

        int writeVal = encode(labCount, sageCount + 1);

        rc.writeSharedArray(rc.getRoundNum() % 2 + LEAD_UNIT_COUNT_OFFSET, writeVal);
    }

    /**
     * Gets the value of the archon order & increments it.
     */
    public static int getArchonOrder(RobotController rc) throws GameActionException {
        int arrVal = rc.readSharedArray(TURN_INFO);
        int x = decode(arrVal, 2);

        int writeVal = encode(decode(arrVal, 0), decode(arrVal, 1), x + 1);

        rc.writeSharedArray(TURN_INFO, writeVal);

        return x % 5;

    }

    /**
     * Wipes archon order from array depending on if round has switched to even or
     * odd.
     */
    public static void wipeArchonOrder(RobotController rc) throws GameActionException {
        int arrVal = rc.readSharedArray(0);
        int x = decode(arrVal, 2);

        if (x >= 5 && rc.getRoundNum() % 2 == 0) {
            int writeVal = encode(decode(arrVal, 0), decode(arrVal, 1), 0);
            rc.writeSharedArray(0, writeVal);
        } else if (x < 5 && rc.getRoundNum() % 2 != 0) {
            int writeVal = encode(decode(arrVal, 0), decode(arrVal, 1), 5);
            rc.writeSharedArray(0, writeVal);
        }

    }

    /**
     * Wipes unit counts to prepare for current round of incrementing unit counts.
     */
    public static void wipeAttackCommands(RobotController rc) throws GameActionException {
        int offset = (rc.getRoundNum() % 2 == 0) ? ATTACK_EVEN_OFFSET : ATTACK_ODD_OFFSET;
        for (int i = 0; i < ATTACK_SIZE; i++) {
            rc.writeSharedArray(i + offset, NULL_LOCATION);
        }
    }

    /**
     * Wipes unit counts to prepare for current round of incrementing unit counts.
     */
    public static void wipeDefCommands(RobotController rc) throws GameActionException {
        int offset = (rc.getRoundNum() % 2 == 0) ? DEFENSE_EVEN_OFFSET : DEFENSE_ODD_OFFSET;
        for (int i = 0; i < DEFENSE_SIZE; i++) {
            rc.writeSharedArray(i + offset, NULL_LOCATION);
        }
    }

    /**
     * Wipes unit counts to prepare for current round of incrementing unit counts.
     */
    public static void wipeBuildCommands(RobotController rc) throws GameActionException {
        int offset = (rc.getRoundNum() % 2 == 0) ? BUILD_EVEN_OFFSET : BUILD_ODD_OFFSET;
        for (int i = 0; i < 2; i++) {
            rc.writeSharedArray(i + offset, NULL_LOCATION);
        }
    }

    /**
     * Wipes unit counts to prepare for current round of incrementing unit counts.
     */
    public static void wipeMoveToCommands(RobotController rc) throws GameActionException {
        int index = (rc.getRoundNum() % 2 == 0) ? MOVE_TO_EVEN_OFFSET : MOVE_TO_ODD_OFFSET;
        rc.writeSharedArray(index, NULL_LOCATION);

    }

    /**
     * Wipes unit counts to prepare for current round of incrementing unit counts.
     */
    public static void wipeNextIndex(RobotController rc) throws GameActionException {
        rc.writeSharedArray(NEXT_INDICES, 0);
    }

    /**
     * Attempt to wipe commands this turn.
     * Return whether command was wiped succesfully.
     *
     * @throws GameActionException
     */
    public static boolean setWiped(RobotController rc) throws GameActionException {
        int arrValue = rc.readSharedArray(HAS_WIPED);
        if (decode(arrValue, 0) == rc.getRoundNum()) {
            return false;
        }
        rc.writeSharedArray(HAS_WIPED, encode(rc.getRoundNum() % 2,
                decode(arrValue, 1), decode(arrValue, 2)));
        return true;
    }

    /**
     * Return if the commands has been wiped this turn.
     * 
     * @throws GameActionException
     */
    public static boolean hasWiped(RobotController rc) throws GameActionException {
        int arrValue = rc.readSharedArray(HAS_WIPED);
        if (decode(arrValue, 0) == rc.getRoundNum() % 2) {
            return true;
        }
        return false;
    }

    public static void checkTeamArchonDied(RobotController rc) throws GameActionException {
        if(Communications.isArchonMoving(rc)){
            for(int i = 0; i< 4; i++){

                if(getTeamArchonLocation(rc, Communications.getArchonMovingID(rc))==null){
                    setArchonMoving(rc, 0, false, 0);
                    return;
                }
            }

        }



    }

    /**
     * Toggles whether an archon is moving this round.
     *
     * @throws GameActionException
     */
    public static void setArchonMoving(RobotController rc, int dist, boolean moving, int id) throws GameActionException {
        int arrValue = rc.readSharedArray(ARCHON_MOVING);
        int writeVal = (moving) ? 1 : 0;
        rc.writeSharedArray(ARCHON_MOVING, encode(
                dist, id,  writeVal));
    }

    /**
     * Toggles whether an archon is moving this round.
     *
     * @throws GameActionException
     */
    public static int getArchonMovingDistToTarget(RobotController rc) throws GameActionException {
        int arrValue = rc.readSharedArray(ARCHON_MOVING);
        return decode(arrValue, 0);
    }

    /**
     * Return if there is an archon moving
     *
     * @throws GameActionException
     */
    public static int getArchonMovingID(RobotController rc) throws GameActionException {
        int arrValue = rc.readSharedArray(ARCHON_MOVING);
        return decode(arrValue, 1);
    }

    /**
     * Return if there is an archon moving
     *
     * @throws GameActionException
     */
    public static boolean isArchonMoving(RobotController rc) throws GameActionException {
        int arrValue = rc.readSharedArray(ARCHON_MOVING);
        return decode(arrValue, 2) == 1;
    }

    /**
     * Gets the next available attack index.
     */
    public static int getNextAttackIndex(RobotController rc) throws GameActionException {
        int readVal = rc.readSharedArray(NEXT_INDICES);
        return decode(readVal, 0);
    }

    /**
     * Increments the attack index
     * 
     * @param rc
     * @throws GameActionException
     */
    public static void incrementAttackIndex(RobotController rc) throws GameActionException {
        int readVal = rc.readSharedArray(NEXT_INDICES);

        int attackIndex = decode(readVal, 0);
        int defIndex = decode(readVal, 1);
        int buildIndex = decode(readVal, 2);
        rc.writeSharedArray(NEXT_INDICES, encode((attackIndex + 1) % ATTACK_SIZE, defIndex, buildIndex));
    }

    /**
     * Gets the next available defend index.
     */
    public static int getNextDefIndex(RobotController rc) throws GameActionException {
        int readVal = rc.readSharedArray(NEXT_INDICES);
        return decode(readVal, 1);
    }

    /**
     * Increments the defend index
     * 
     * @param rc
     * @throws GameActionException
     */
    public static void incrementDefIndex(RobotController rc) throws GameActionException {
        int readVal = rc.readSharedArray(NEXT_INDICES);

        int attackIndex = decode(readVal, 0);
        int defIndex = decode(readVal, 1);
        int buildIndex = decode(readVal, 2);
        rc.writeSharedArray(NEXT_INDICES, encode(attackIndex, (defIndex + 1) % DEFENSE_SIZE, buildIndex));
    }

    /**
     * Gets the next available build index.
     */
    public static int getNextBuildIndex(RobotController rc) throws GameActionException {
        int readVal = rc.readSharedArray(NEXT_INDICES);
        return decode(readVal, 0);
    }

    /**
     * Increments the build index
     * 
     * @param rc
     * @throws GameActionException
     */
    public static void incrementBuildIndex(RobotController rc) throws GameActionException {
        int readVal = rc.readSharedArray(NEXT_INDICES);

        int attackIndex = decode(readVal, 0);
        int defIndex = decode(readVal, 1);
        int buildIndex = decode(readVal, 2);
        rc.writeSharedArray(NEXT_INDICES, encode(attackIndex, defIndex, (buildIndex + 1) % 2));
    }

    /**
     * Report good lead location.
     * 
     * @return boolean of whether the location was successfully registered
     * 
     * @throws GameActionException
     */
    public static boolean reportLeadLocation(RobotController rc, MapLocation leadLocation) throws GameActionException {
        int offset = (rc.getRoundNum() % 2 == 0) ? LEAD_EVEN_OFFSET : LEAD_ODD_OFFSET;
        int newLead = encode(leadLocation.x, leadLocation.y);

        for (int i = 0; i < LEAD_ODD_OFFSET - LEAD_EVEN_OFFSET; i++) {
            int arrayValue = rc.readSharedArray(i + offset);
            if (decode(arrayValue, 0) >= 60) {
                rc.writeSharedArray(i + offset, newLead);
                return true;
            }
        }

        return false;
    }

    /**
     * Get good lead locations.
     * 
     * @throws GameActionException
     */
    public static MapLocation[] getLeadLocation(RobotController rc) throws GameActionException {
        MapLocation[] leadLocations = new MapLocation[LEAD_ODD_OFFSET - LEAD_EVEN_OFFSET];
        int offset = (rc.getRoundNum() % 2 == 0) ? LEAD_ODD_OFFSET : LEAD_EVEN_OFFSET;

        for (int i = 0; i < LEAD_ODD_OFFSET - LEAD_EVEN_OFFSET; i++) {
            int arrayValue = rc.readSharedArray(i + offset);
            leadLocations[i] = new MapLocation(decode(arrayValue, 0), decode(arrayValue, 1));
        }

        return leadLocations;
    }

    /**
     * Wipe lead locations.
     * 
     * @throws GameActionException
     */
    public static void wipeLeadLocation(RobotController rc) throws GameActionException {
        int offset = (rc.getRoundNum() % 2 == 0) ? LEAD_EVEN_OFFSET : LEAD_ODD_OFFSET;
        for (int i = 0; i < 5; i++) {
            rc.writeSharedArray(i + offset, NULL_LOCATION);
        }
    }










    /**
     * Decodes array value into suedo bytes
     * 
     * could be hardcoded to save slight bytecode
     * 
     * index 0:
     * number % 64 / 1
     * index 1:
     * number % 64^2 / 64
     * ...
     */
    static int decode(int number, int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        } else {
            return (number % (int) Math.pow(64, index + 1) / (int) Math.pow(64, index));
        }
    }

    /**
     * Encodes a variable number of values into a single array value ready to be
     * written
     */
    private static int encode(int... fields) {
        int result = 0;
        for (int i = 0; i < fields.length; i++) {
            result += fields[i] * Math.pow(64, i);
        }
        return result;
    }
}
