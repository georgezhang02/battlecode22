package pathplanner;

import battlecode.common.RobotController;

public class Explorer {
    static RobotController rc;
    boolean initialized;
    boolean[][]visited;

    public Explorer(RobotController rc){
        this.rc = rc;
        initialized = false;
        visited = new boolean[rc.getMapWidth()][rc.getMapHeight()];
    }
}
