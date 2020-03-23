package com.zxin.basemodule.model;

public interface IPoi {
    int SOURCE_DEFAULT = 0;
    int SOURCE_GIS = 1;
    int SOURCE_BAI_DU = 2;
    int SOURCE_TOM_TOM = 3;

    // 0-100: History type
    int TYPE_HISTORY_TEXT = 0;
    int TYPE_HISTORY_NAV = 1;
    int TYPE_HISTORY_MAX = 99;

    // 100-1000: Favorite type.
    int TYPE_FAVORITE_NORMAL = 100;
    int TYPE_FAVORITE_HOME = 101;
    int TYPE_FAVORITE_COMPANY = 102;
    int TYPE_FAVORITE_MAX = 999;

    int TYPE_FAVORITE_NORMAL_ENGINE = 103; //engine default baidu
    int TYPE_FAVORITE_HOME_ENGINE = 104;   //engine default baidu
    int TYPE_FAVORITE_COMPANY_ENGINE = 105;//engine default baidu
    int TYPE_FAVORITE_UPDATE_ALL = 106;

    int TYPE_DEFAULT = 1000;

    //source of favorite or history
    int SOURCE_TYPE_FROM_ENGINE = 1001; //engine default baidu
    int SOURCE_TYPE_FROM_LOCAL = 1002;
    int SOURCE_TYPE_FROM_ALL = 1003;

    String getName();
}
