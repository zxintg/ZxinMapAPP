package com.zxin.basemodule.app;

public interface IIntent {

    int RESULT_OK = 0;
    int RESULT_ERROR = -1;
    int RESULT_CANCEL = 1;
    int RESULT_CLOSE = 2;
    int RESULT_OPERATION_NAV = 3;
    int RESULT_SMART_SEARCH = 4;

    String ACTION_GET_RESULT = "action_get_result";
    String ACTION_DEFAULT = "action_default";
    String ACTION_SHOW_POI_INFO = "action_show_poi_info";

    String EXTRA_REQUEST_CODE = "extra_request_code";
    String EXTRA_RESULT_CODE = "extra_result_code";

    String EXTRA_POI_DATA = "extra_poi_data";
    String EXTRA_KEY_TARGET = "extra_key_target";
    String NEARBY_POI_DATA = "nearby_poi_data";
    String SEARCH_RESULT_DATA = "search_result_data";



    public enum Target {
        ADD_HOME, ADD_COMPANY, ADD_FAVORITE, ADD_VIA, GOTO_POI, SHOW_POI,POI_AROUND
    }

    void setAction(String action);

    String getAction();

    void putString(String key, String value);

    String getString(String key, String defaultValue);

    void putInt(String key, int value);

    int getInt(String key, int defaultValue);

    void putLong(String key, long value);

    long getLong(String key, long defaultValue);

    void putFloat(String key, float value);

    float getFloat(String key, float defaultValue);

    void putData(String key, Object data);

    Object getData(String key);

    boolean hasExtra(String key);

    void setRequestIntent(IIntent intent);

    IIntent getRequestIntent();
}