package com.zxin.basemodule.overlay;

public interface IMapItem {
    String SUFFIX_TMP = "_tmp";
    int ITEM_TYPE_UNKNOWN = 0;
    int ITEM_TYPE_BASE_MAP = 1;
    int ITEM_TYPE_BASE_POI = 2;
    int ITEM_TYPE_POI_MARKER = 3;
    int ITEM_TYPE_POI_BKG_MARKER = 4;
    int ITEM_TYPE_ROUTE = 5;
    int ITEM_TYPE_CUSTOM_MARKER = 6;
    int ITEM_TYPE_OPERATING_POI = 7;
    int ITEM_TYPE_TRAFFIC_UGC_POI = 8;
    int ITEM_TYPE_ROUTE_START = 9;
    int ITEM_TYPE_ROUTE_DESTINATION = 10;
    int ITEM_TYPE_ROUTE_WAYPOINT = 11;
}
