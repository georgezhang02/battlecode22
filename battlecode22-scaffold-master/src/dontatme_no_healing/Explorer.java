package dontatme_no_healing;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class Explorer {
    static RobotController rc;

    static boolean[][]visited;

    static Direction[]  vision = {Direction.SOUTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.WEST, Direction.WEST, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.EAST, Direction.EAST, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST, Direction.NORTHEAST, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTHWEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.SOUTHWEST, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTHEAST, Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST, Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTHWEST, Direction.NORTHWEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH};

    static MapLocation target;

    static boolean fastExplore;


    public Explorer(RobotController rc, boolean fastExplore){
        this.rc = rc;
        this.fastExplore = fastExplore;
        this.target = rc.getLocation();
        if (!fastExplore){
            visited = new boolean[rc.getMapWidth()][rc.getMapHeight()];
        }

    }

    // Choose random unvisited location through visited array, if can't find in tries moves
    //chooses random on radius
    public void getExploreTarget(int tries, int mapWidth, int mapHeight){
        if(fastExplore){
            getExploreTargetRandom(mapWidth, mapHeight);
        } else{
            int count = tries;
            MapLocation ml = null;
            while(ml == null && count > 0) {
                int x = (int) (10000* Math.random()) %mapWidth;
                int y = (int) (10000 * Math.random()) % mapHeight;
                if(!visited[x][y]){
                    target = new MapLocation(x, y);
                }
                count--;
            }
            if(ml == null){
                getExploreTargetRandom(mapWidth, mapHeight);
            }
        }


    }

    // returns random target towards the edges a larger distance away
    public void getExploreTargetRandom(int mapWidth, int mapHeight){
        int x = (int) (10000* Math.random()) %mapWidth;
        int y = (int) (10000 * Math.random()) % mapHeight;

        target = new MapLocation(x, y);



    }


    public void updateVisited() throws GameActionException {
        MapLocation cur = rc.getLocation();
        visited[cur.x][cur.y] = true;
        for(int i = 0; i< vision.length; i++){
            cur = cur.add(vision[i]);
            if(rc.onTheMap(cur)){
                visited[cur.x][cur.y] = true;
            }


        }
    }



}
