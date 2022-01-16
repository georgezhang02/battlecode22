package dontatme;

import battlecode.common.GameConstants;
import battlecode.common.MapLocation;

public strictfp class Prediction {
    static int mapCenterX = GameConstants.MAP_MAX_WIDTH / 2;
    static int mapCenterY = GameConstants.MAP_MAX_HEIGHT / 2;

    // Returns a possible location of enemy archon on a vertically reflected map
    public MapLocation getVerticalConverse(MapLocation myArchon) {
        int predictedY = mapCenterY - (myArchon.y - mapCenterY);
        return new MapLocation(myArchon.x, predictedY);
    }

    // Returns a possible location of enemy archon on a horizontally reflected map
    public MapLocation getHorizontalConverse(MapLocation myArchon) {
        int predictedX = mapCenterX = (myArchon.x - mapCenterX);
        return new MapLocation(predictedX, myArchon.y);
    }

    // Returns a possible location of enemy archon on a rotational map
    public MapLocation getRotationalConverse(MapLocation myArchon) {
        int predictedX = mapCenterX - (myArchon.x - mapCenterX);
        int predictedY = mapCenterY - (myArchon.y - mapCenterY);

        return new MapLocation(predictedX, predictedY);
    }
}
