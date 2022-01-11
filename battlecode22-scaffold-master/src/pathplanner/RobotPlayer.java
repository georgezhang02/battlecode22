package pathplanner;

import battlecode.common.*;

import java.util.Random;


public strictfp class RobotPlayer {

    static int turnCount = 0;
    static boolean pathing = false;

    static final Random rng = new Random(6147);


    static Pathfinder pathfinder;

    @SuppressWarnings("unused")
    public static void run(RobotController rc) throws GameActionException {

        // Hello world! Standard output is very useful for debugging.
        // Everything you say here will be directly viewable in your terminal when you run a match!
        //System.out.println("I'm a " + rc.getType() + " and I just got created! I have health " + rc.getHealth());

        // You can also use indicators to save debug notes in replays.
        rc.setIndicatorString("Hello world!");
        pathfinder = new BFPathing20(rc);

        while (true) {
            // This code runs during the entire lifespan of the robot, which is why it is in an infinite
            // loop. If we ever leave this loop and return from run(), the robot dies! At the end of the
            // loop, we call Clock.yield(), signifying that we've done everything we want to do.

            turnCount += 1;  // We have now been alive for one more turn!

            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode.
            try {
                // The same run() function is called for every robot on your team, even if they are
                // different types. Here, we separate the control depending on the RobotType, so we can
                // use different strategies on different robots. If you wish, you are free to rewrite
                // this into a different control structure!
                switch (rc.getType()) {
                    case ARCHON:
                        runArchon(rc);
                        break;
                    case MINER:
                        runMiner(rc);
                        break;
                    case SOLDIER:
                        runSoldier(rc);
                        break;
                    case LABORATORY: // Examplefuncsplayer doesn't use any of these robot types below.
                    case WATCHTOWER: // You might want to give them a try!
                    case BUILDER:
                    case SAGE:
                        break;
                }
            } catch (GameActionException e) {
                // Oh no! It looks like we did something illegal in the Battlecode world. You should
                // handle GameActionExceptions judiciously, in case unexpected events occur in the game
                // world. Remember, uncaught exceptions cause your robot to explode!
                System.out.println(rc.getType() + " Exception");
                e.printStackTrace();

            } catch (Exception e) {
                // Oh no! It looks like our code tried to do something bad. This isn't a
                // GameActionException, so it's more likely to be a bug in our code.
                System.out.println(rc.getType() + " Exception");
                e.printStackTrace();

            } finally {
                // Signify we've done everything we want to do, thereby ending our turn.
                // This will make our code wait until the next turn, and then perform this loop again.
                Clock.yield();
            }
            // End of loop: go back to the top. Clock.yield() has ended, so it's time for another turn!
        }

        // Your code should never reach here (unless it's intentional)! Self-destruction imminent...
    }

    /**
     * Run a single turn for an Archon.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void runArchon(RobotController rc) throws GameActionException {
        // Pick a direction to build in.

        Direction dir = Direction.NORTH;
        if(rc.canBuildRobot(RobotType.MINER, dir)){
            rc.buildRobot(RobotType.MINER, dir);
        }

    }

    /**
     * Run a single turn for a Miner.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void runMiner(RobotController rc) throws GameActionException {

        Direction dir = null;
        RobotInfo[] enemyML= rc.senseNearbyRobots(20, rc.getTeam().opponent());
        MapLocation[] enemyPos = new MapLocation[enemyML.length];
        if(enemyML.length > 0){
            for(int i = 0; i< enemyPos.length; i++){
                enemyPos[i] = enemyML[i].getLocation();
            }

            dir =  pathfinder.pathAwayFrom(enemyPos);
        }else {
            pathfinder.target = new MapLocation(rc.getMapWidth()/ 2, rc.getMapHeight()/2);
            dir = pathfinder.bfPathToTarget();

        }


        if(dir!= null) {

        } else{

        }
        if(dir != null && rc.canMove(dir)){
            rc.move(dir);
        }


    }

    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void runSoldier(RobotController rc) throws GameActionException {
        // Try to attack someone

    }






}
