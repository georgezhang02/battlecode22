package dontatme;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class Explorer {
    static RobotController rc;

    static boolean[][]visited;

    static Direction[]  vision = {Direction.SOUTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.WEST, Direction.WEST, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.EAST, Direction.EAST, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST, Direction.NORTHEAST, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTHWEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.SOUTHWEST, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTHEAST, Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST, Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTHWEST, Direction.NORTHWEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH};

    public Explorer(RobotController rc){
        this.rc = rc;
        visited = new boolean[rc.getMapWidth()][rc.getMapHeight()];
    }

    public MapLocation getExploreTarget(int tries, int mapWidth, int mapHeight){
        int count = tries;
        MapLocation ml = null;
        while(ml == null && count > 0) {
            int x = (int) (mapWidth * Math.random());
            int y = (int) (mapHeight * Math.random());
            if(!visited[x][y]){
                return new MapLocation(x, y);
            }
            count--;
        }
        return null;
    }

    public MapLocation getExploreTargetRandom(int mapWidth, int mapHeight){
        MapLocation curPos = rc.getLocation();

        boolean closeLeft = curPos.x < mapWidth;
        boolean closeBot = curPos.y < mapHeight;

        int angle;
        if(closeBot){
            if(closeLeft){
                angle = (int) (90 * Math.random());
            } else{
                angle = (int) (90 * Math.random()) + 90;
            }

        } else{
            if(closeLeft){
                angle = (int) (90 * Math.random()) + 270;
            } else{
                angle = (int) (90 * Math.random()) + 360;
            }
        }

        int maxDim = Math.max(mapWidth, mapHeight);

        int x = Math.max(Math.min((int) (maxDim/2 * Math.cos(angle)), mapWidth), 0);
        int y = Math.max(Math.min((int) (maxDim/2 * Math.sin(angle)), mapHeight), 0);

        return new MapLocation(x, y);
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
