package com.zxin.basemodule.common;

public class ResultCode implements IResultCode {

    /**
     * Success Code.
     */
    public static final ResultCode RESULT_SUCCESS = new ResultCode(0, "OK");

    /**
     * Common Error Code: From 1 to 999
     */
    public static final ResultCode RESULT_ERROR = new ResultCode(1, "ERROR");
    public static final ResultCode NULL_REQUEST = new ResultCode(2, "NULL REQUEST");


    private int code;
    private String msg;

    public ResultCode(int code, String msg) {
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
