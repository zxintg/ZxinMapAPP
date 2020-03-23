package com.zxin.basemodule.guide;

public enum RoadLevel {
    HIGHWAY(0),
    CITY_FAST_WAY(1),
    NATION_WAY(2),
    PROVINCE_WAY(3),
    COUNTY_WAY(4),
    TOWN_WAY(5),
    OTHER_WAY(6),
    LEVEL9_WAY(7),
    FERRY_WAY(8),
    WALK_WAY(9),
    INVALID(10);
    private int mapLevel;
    private RoadLevel(int id) {
        mapLevel = id;
    }

    public int getRoadLevel() {
        return mapLevel;
    }

    public static RoadLevel getRoadLevel(int id) {
        if (id < 0 || id > 10) {
            return RoadLevel.INVALID;
        }
        return RoadLevel.values()[id];
    }
}
