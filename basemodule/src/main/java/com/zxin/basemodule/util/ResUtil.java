package com.zxin.basemodule.util;

import com.zxin.basemodule.app.ZxinRootApp;

public class ResUtil {

    public static int getDimensionById(int resId) {
        return ZxinRootApp.getApp().getResources().getDimensionPixelSize(resId);
    }
}
