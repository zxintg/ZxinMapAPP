package com.zxin.basemodule.system;

import android.content.Context;

/**
 * For get platform information from the app module.
 *
 * app module must implement this interface and via {@link com.nio.map.InitParams#setSystem(ISystem)}
 * transform to engine.
 */
public interface ISystem {
    String getProperty(String key, String defaultValue);

    String getAuthorization();

    String getUserId();

    String getVid();

    String getAppId();

    String getHost();

    String getModel();

    String getOS();

    String getOSVer();

    long getRequestTimestamp();

    String getLanguage();

    String getRegion();

    String getAppVersionCode(Context context);

    String getAppVersionName(Context context);
}
