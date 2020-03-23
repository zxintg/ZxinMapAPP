package com.zxin.basemodule;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import com.zxin.basemodule.app.ZxinRootApp;
import com.zxin.basemodule.system.ISystem;
import com.zxin.basemodule.util.ZxinUtil;

import java.util.Locale;

/**
 * Support give platform information to map engine.
 *
 */
public class SystemImpl implements ISystem {
    private static String mVersionName;
    private static String mVersionCode;

    @Override
    public String getProperty(String key, String value) {
        return "";//SystemProperties.get(key, value);
    }

    @Override
    public String getAuthorization() {
        return "";
    }

    @Override
    public String getUserId() {
        return "";
    }

    public String getVid() {
        return "";
    }

    @Override
    public String getAppId() {
        return "";
    }

    @Override
    public String getHost() {
        return  ""; //URLHost.getNavigationHost();
    }

    @Override
    public String getModel() {
        return Build.MODEL;
    }

    @Override
    public String getOS() {
        return "android";
    }

    @Override
    public String getOSVer() {
        return Build.DISPLAY;
    }

    @Override
    public long getRequestTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    @Override
    public String getLanguage() {
        Locale locale = ZxinRootApp.getApp().getResources().getConfiguration().locale;
        return locale.getLanguage();
    }

    @Override
    public String getRegion() {
        Locale locale = ZxinRootApp.getApp().getResources().getConfiguration().locale;
        return locale.getCountry();
    }

    @Override
    public String getAppVersionCode(Context context) {
        if (TextUtils.isEmpty(mVersionCode)) {
            try {
                PackageManager packageManager = context.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                String appVersion = packageInfo.versionCode + ""; // appVersion
                mVersionCode = appVersion;
            } catch (Exception e) {
                return mVersionCode = "";
            }
        }
        return mVersionCode;
    }

    @Override
    public String getAppVersionName(Context context) {
        if (TextUtils.isEmpty(mVersionName)) {
            try {
                PackageManager packageManager = context.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                String appVersion = packageInfo.versionName + ""; // appVersion
                mVersionName = appVersion;
            } catch (Exception e) {
                return mVersionName = "";
            }
        }
        return mVersionName;
    }
}
