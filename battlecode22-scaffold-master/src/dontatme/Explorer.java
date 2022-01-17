package dontatme;

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
                int x = (int) (mapWidth * Math.random());
                int y = (int) (mapHeight * Math.random());
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
        MapLocation curPos = rc.getLocation();

        int closeLeft = curPos.x;
        int closeBot = curPos.y;
        int closeRight = mapWidth - curPos.x ;
        int  closeTop = mapHeight - curPos.y;

        int closeDist = 61;

        int exploreTo = 0;

        boolean vert = true;
        if(closeTop< closeDist){
            exploreTo = 1;
            closeDist = closeTop;

        }
        if(closeBot< closeDist){
            exploreTo = 2;
            closeDist = closeBot;
        }

        if(closeLeft< closeDist){
            exploreTo = 3;
            closeDist = closeLeft;
            vert = false;
        }
        if(closeRight< closeDist){
            exploreTo = 4;
            closeDist = closeRight;
            vert = false;
        }

        if(vert){
            if(closeDist > mapHeight / 10){
                exploreTo = 0;
            }
        } else {
            if(closeDist > mapWidth / 10){
                exploreTo = 0;
            }
        }

        int angle;
        if(exploreTo == 0){
            angle = (int)((Math.random() * 10000)) % 360;
            rc.setIndicatorString("Not Close");

        }
        else if (exploreTo == 1){
            angle = (int)((Math.random() * 10000)) % 180  + 180 ;
            rc.setIndicatorString("Close TOp " + closeDist+" "+angle);
        } else if(exploreTo == 2){
            angle = (int)((Math.random() * 10000)) % 180 ;
            rc.setIndicatorString("Close Bot " + closeDist+" "+angle);
        }else if(exploreTo == 3){
            angle = (int) ((Math.random() * 10000)) % 180 + 270;
            rc.setIndicatorString("Close Left " + closeDist+" "+angle);
        }
        else {

            angle = (int)((Math.random() * 10000)) % 180  + 90 ;
            rc.setIndicatorString("Close Right " + closeDist+" "+angle);
        }

        
        int x = Math.max(Math.min(curPos.x + (int) (mapWidth / 2 * Math.cos(angle/180.0 * 3.1415926)), mapWidth -1), 0);
        int y = Math.max(Math.min(curPos.y + (int) (mapHeight /2 * Math.sin(angle/180.0 * 3.1415926)), mapHeight -1), 0);

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
