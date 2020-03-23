package com.zxin.basemodule.common;

public enum SearchResultCode implements IResultCode {
    /**
     * Search Error Code: From 2000 to 2999
     */
    RESULT_ERROR_SEARCH_TIME_OUT(2000, "ERROR_SEARCH_TIME_OUT"),

    NO_RESPONSE(2001, "ERROR_SEARCH_NO_RESPONSE"),

    PARSE_ERROR(2002, "RESPONSE_DATA_PARSE_ERROR"),

    RESULT_ERROR_SEARCH_NO_OFFLINE_DATA(2003, "ERROR_SEARCH_NO_OFFLINE_DATA"),

    AUTH_FAILED(2004, "AUTH_FAILED"),

    RESULT_ERROR_CANCEL(2005, "SEARCH_CANCEL"),

    RESULT_ERROR_EMPTY(2006, "SEARCH_CANCEL");

    private int code;
    private String msg;

    SearchResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
