package com.zxin.basemodule.util;

import android.widget.Toast;

import com.zxin.basemodule.app.ZxinRootApp;

/**
 * 统一Toast的调用
 * Created by guang.chen2 on 2018/2/2.
 */

public final class ToastUtils {
    private static Toast sToast;
    private static boolean isCanShow = false;
    public static void canShowToast(boolean canShow) {
        isCanShow = canShow;
        if (!canShow && sToast != null) {
            sToast.cancel();
        }
    }

    public static Toast getToast() {
        return sToast;
    }

    public static void show(int id, boolean force) {
        show(ZxinRootApp.getApp().getString(id), Toast.LENGTH_SHORT, force);
    }

    public static void show(String message) {
        show(message, Toast.LENGTH_SHORT);
    }

    /**
     * Show a toast message
     * @param message the message.
     * @param duration duration, value must be of {@link Toast#LENGTH_LONG} or {@link Toast#LENGTH_SHORT}
     */
    public static void show(String message, int duration) {
        show(message, duration, false);
    }

    private static void show(String message, int duration, boolean force) {
        //TODO There is a bug if don't create a new toast instance
        // will execute a animation when show a toast and before toast not removed.
        // this may a window animation defined by {@link WindowManagerService} for switch window.
        /*checkToast();
        sToast.setText(message);
        sToast.setDuration(len);
        sToast.show();
        */
        if (!isCanShow && !force) {
            return;
        }
        if (sToast != null) {
            sToast.cancel();
        }
        sToast = Toast.makeText(ZxinRootApp.getApp(), message, duration);
        sToast.show();
    }

    /**
     * Show a toast message
     * @param message the string id.
     */
    public static void show(int message) {
        show(message, Toast.LENGTH_SHORT);
    }


    /**
     * Show a toast message
     * @param message the string id.
     * @param duration duration, value must be of {@link Toast#LENGTH_LONG} or {@link Toast#LENGTH_SHORT}
     */
    public static void show(int message, int duration) {
        if (!isCanShow) {
            return;
        }
        show(ZxinRootApp.getApp().getString(message), duration);
    }
}
