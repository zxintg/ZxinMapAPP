package com.zxin.basemodule.util;

public class CheckUtil {

    public static final void require(boolean state, String message) {
        if (!state) {
            throw new IllegalStateException(message);
        }
    }

    public static void throwException(String message) {
        throw new IllegalStateException(message);
    }

    public static void assertState(boolean state, String message) {
        if (!state) {
            throwException(message);
        }
    }
}
