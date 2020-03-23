package com.zxin.basemodule.common;

public enum RouteResultCode implements IResultCode {

    /**
     * Route Error Code: From 1000 to 1999
     */
    RESULT_ERROR_ROUTE_TIME_OUT(1000, "ERROR_ROUTE_TIME_OUT"),

    RESULT_ERROR_ROUTE_NETWORK(1999, "ERROR_ROUTE_NETWORK");



    private int code;
    private String msg;
    private RouteResultCode(int code, String msg) {
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
