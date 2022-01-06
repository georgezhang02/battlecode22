package pathplanner;

import battlecode.common.*;

import java.awt.*;
import java.util.Map;
import java.util.Random;

/**
 * RobotPlayer is the class that describes your main robot strategy.
 * The run() method inside this class is like your main function: this is what we'll call once your robot
 * is created!
 */
public strictfp class RobotPlayer {

    /**
     * We will use this variable to count the number of turns this robot has been livea.
     * You can use static variables like this to save any information you want. Keep in mind that even though
     * these variables are static, in Battlecode they aren't actually shared between your robots.
     */
    static int turnCount = 0;
    static boolean pathing = false;

    static MapLocation target;
    /**
     * A random number generator.
     * We will use this RNG to make some random moves. The Random class is provided by the java.util.Random
     * import at the top of this file. Here, we *seed* the RNG with a constant number (6147); this makes sure
     * we get the same sequence of numbers every time this code is run. This is very useful for debugging!
     */
    static final Random rng = new Random(6147);

    /**
     * Array containing all the possible movement directions.
     */
    static final Direction[] directions = {
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
     * run() is the method that is called when a robot is instantiated in the Battlecode world.
     * It is like the main function for your robot. If this method returns, the robot dies!
     *
     * @param rc The RobotController object. You use it to perform actions from this robot, and to get
     *           information on its current status. Essentially your portal to interacting with the world.
     **/
    @SuppressWarnings("unused")
    public static void run(RobotController rc) throws GameActionException {

        // Hello world! Standard output is very useful for debugging.
        // Everything you say here will be directly viewable in your terminal when you run a match!
        //System.out.println("I'm a " + rc.getType() + " and I just got created! I have health " + rc.getHealth());

        // You can also use indicators to save debug notes in replays.
        rc.setIndicatorString("Hello world!");

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

        Direction dir = Direction.NORTHEAST;
        if(rc.canBuildRobot(RobotType.MINER, dir)){
            rc.buildRobot(RobotType.MINER, dir);
        }

    }

    /**
     * Run a single turn for a Miner.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void runMiner(RobotController rc) throws GameActionException {
        pathTo(rc, new MapLocation(rc.getMapWidth()-1, rc.getMapHeight()-1));


    }

    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    static void runSoldier(RobotController rc) throws GameActionException {
        // Try to attack someone

    }

    static void pathTo(RobotController rc, MapLocation target) throws GameActionException {

        MapLocation curPos = rc.getLocation();
        int height = rc.getMapHeight();
        int width = rc.getMapWidth();
        int size = height * width;

        int[][]dist = new int[height][width];
        int[][]pred = new int[height][width];

        for(int i = 0; i< size; i++){
            dist[i/height][i%height] = 7200000;
        }
        int sum = 0;
        int count = 0;
        for(MapLocation ml: rc.getAllLocationsWithinRadiusSquared(curPos, 100)){
            sum+= rc.senseRubble(ml)+10;
            count++;
        }

        sum/= count;

        dist[curPos.y][curPos.x] = 0;

        int cost = sum;




        //for(int i = 0; i<size; i++) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    MapLocation ml = new MapLocation(x, y);
                    if(rc.canSenseLocation(ml)){
                        cost = rc.isLocationOccupied(ml) ? 2000 : rc.senseRubble(ml) + 10;
                    }
                    if (inMap(x-1, y-1, width, height)) {
                        if(dist[y-1][x-1] + cost < dist[y][x]){
                            dist[y][x] = dist[y-1][x-1]+cost;
                            pred[y][x] = (y-1)*height + (x-1);
                        }
                    }

                    if (inMap(x-1, y, width, height)) {
                        if(dist[y][x-1] + cost < dist[y][x]){
                            dist[y][x] = dist[y][x-1]+cost;
                            pred[y][x] = (y)*height + (x-1);
                        }
                    }
                    if (inMap(x, y-1, width, height)) {
                        if(dist[y-1][x] + cost < dist[y][x]){
                            dist[y][x] = dist[y-1][x]+cost;
                            pred[y][x] = (y-1)*height+x;
                        }
                    }
                    if (inMap(x+1, y+1, width, height)) {
                        if(dist[y+1][x+1] + cost < dist[y][x]){
                            dist[y][x] = dist[y+1][x+1]+cost;
                            pred[y][x] = (y+1)*height + (x+1);
                        }
                    }
                    if (inMap(x, y+1, width, height)) {
                        if(dist[y+1][x] + cost < dist[y][x]){
                            dist[y][x] = dist[y+1][x]+cost;
                            pred[y][x] = (y+1)*height + x;
                        }
                    }
                    if (inMap(x+1, y, width, height)) {
                        if(dist[y][x+1] + cost < dist[y][x]){
                            dist[y][x] = dist[y][x+1]+cost;
                            pred[y][x] = y*height + (x+1);
                        }
                    }
                    if (inMap(x-1, y+1, width, height)) {
                        if(dist[y+1][x-1] + cost < dist[y][x]){
                            dist[y][x] = dist[y+1][x-1]+cost;
                            pred[y][x] = (y+1)*height + (x-1);
                        }
                    }
                    if (inMap(x+1, y-1, width, height)) {
                        if(dist[y-1][x+1] + cost < dist[y][x]){
                            dist[y][x] = dist[y-1][x+1]+cost;
                            pred[y][x] = (y-1)*height + x+1;
                        }
                    }

                }
            }
       // }

        int cur = target.y * height + target.x;
        int truePos = curPos.y *height + curPos.x;

        while(pred[cur/height][cur%height] != truePos){
            cur = pred[cur/height][cur%height];
        }
        MapLocation prev = new MapLocation(cur % height, cur/height);
        Direction fin = curPos.directionTo(prev);

        System.out.println(fin);

        if(rc.canMove(fin)){
            rc.move(fin);
        }

        /*
        int left = curPos.x - width;
        int right = width - curPos.x - 1;
        int bottom = curPos.y - height;
        int top = height - curPos.y -1;

        int maxX = (-left < right) ? right : -left;
        int maxY = (-bottom < top) ? top : -bottom;

        for(int i = 0; i< size; i++){
            dist[i] = 7200000;
        }
        dist[curPos.y * height + curPos.x] = 0;

        int sum = 0;
        int count=0;
        for(MapLocation ml: rc.getAllLocationsWithinRadiusSquared(curPos, 100)){
            sum+= rc.senseRubble(ml)+10;
            count ++;
        }

        sum/= count;

        for(int i = 1; i <= maxX+maxY; i++){
            for(int xdiff = 0; xdiff <= i; xdiff++){
                int ydiff = i - xdiff;
                int x;
                int y;
                int x2;
                int y2;
                int cost = sum;
                if(inMap((x = curPos.x + xdiff), (y = curPos.y + ydiff), width, height)){
                    MapLocation ml =new MapLocation(x, y);
                    if(rc.canSenseLocation(ml)){
                        cost = rc.isLocationOccupied(ml) ? 2000 : rc.senseRubble(ml) + 10;
                    }
                    if(inMap((x2 = x -1), (y2 = y), width, height)){
                        if(dist[y2*height + x2]  + cost < dist[y * height + x]){
                            dist[y * height + x] =  dist[y2*height + x2] + cost;
                            pred[y * height + x] = y2 * height + x2;
                        }
                    }
                    if(inMap((x2 = x), (y2 = y-1), width, height)){
                        if(dist[y2*height + x2]  + cost < dist[y * height + x]){
                            dist[y * height + x] =  dist[y2*height + x2] + cost;
                            pred[y * height + x] = y2 * height + x2;
                        }
                    }
                    if(inMap((x2 = x-1), (y2 = y-1), width, height)){
                        if(dist[y2*height + x2]  + cost < dist[y * height + x]){
                            dist[y * height + x] =  dist[y2*height + x2] + cost;
                            pred[y * height + x] = y2 * height + x2;
                        }
                    }
                }
                if(inMap((x = curPos.x + xdiff), (y = curPos.y - ydiff), width, height)){

                }
                if(inMap((x = curPos.x - xdiff), (y = curPos.y + ydiff), width, height)){

                }
                if(inMap((x = curPos.x - xdiff), (y = curPos.y - ydiff), width, height)){

                }


                
            }
        }
        int toTop = (curPos.y+1) *height + curPos.x;
        int toRight = curPos.y *height + curPos.x+1;

        int cur = target.y * height + target.x;
        int truePos = curPos.y *height + curPos.x;

        while(pred[cur] != truePos){
            cur = pred[cur];
        }
        MapLocation prev = new MapLocation(cur % height, cur/height);
        Direction fin = curPos.directionTo(prev);

        for(int i = 0; i<height; i++){
            for(int j= 0; j<width; j++){
                System.out.print(dist[i*height+j]);
            }
            System.out.println();
        }
        */


    }


    static boolean inMap(int x, int y,  int width, int height) throws GameActionException{
        return x>=0 && y>=0 && x< width && y< height;
    }


}
