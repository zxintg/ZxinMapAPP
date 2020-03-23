package com.zxin.basemodule.util;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import com.zxin.basemodule.app.ZxinRootApp;

@SuppressLint("MissingPermission")
public class NetworkUtil {
    public static final int NETYPE_NOCON = -1; // 无连接，用于区分断网和未知类型，方便统计
    public static final int NETYPE_UNKNOWN = 0; // 未知网络类型
    public static final int NETYPE_WIFI = 1; // WiFi连接
    public static final int NETYPE_2G = 2; // 2G
    public static final int NETYPE_3G = 3; // 3G
    public static final int NETYPE_4G = 4; // 4G
    public static final int NETYPE_TELECOM_2G = 5; // 电信2G(IS95A或者IS95B)
    public static final int NETYPE_MOBILE_UNICOM_2G = 6; // 移动或联通2G
    public static final int NETYPE_TELECOM_3G = 7; // 电信3G
    public static final int NETYPE_MOBILE_3G = 8; // 移动3G
    public static final int NETYPE_UNICOM_3G = 9; // 联通3G
    public static final int NETYPE_4G_UNKNOWN = 10; // 4G?

    /*
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    public static final int NETWORK_TYPE_GPRS = 1;
    public static final int NETWORK_TYPE_EDGE = 2;
    public static final int NETWORK_TYPE_UMTS = 3;
    public static final int NETWORK_TYPE_CDMA = 4;
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    public static final int NETWORK_TYPE_EVDO_A = 6;
    public static final int NETWORK_TYPE_1xRTT = 7;
    public static final int NETWORK_TYPE_HSDPA = 8;
    public static final int NETWORK_TYPE_HSUPA = 9;
    public static final int NETWORK_TYPE_HSPA = 10;
    public static final int NETWORK_TYPE_IDEN = 11;
    public static final int NETWORK_TYPE_EVDO_B = 12;
    public static final int NETWORK_TYPE_LTE = 13;
    public static final int NETWORK_TYPE_EHRPD = 14;
    public static final int NETWORK_TYPE_HSPAP = 15;


     */

    //wap 代理
    public static boolean mUseProxy = false;
    public static String mProxyHost = "";
    public static int mProxyPort = 0;
    //禁止该对象指令重排
    private static volatile NetworkUtil instance = null;

    /***
     * 关闭外部对该类进行实例化操作
     */
    private NetworkUtil() {

    }

    /**
     * 获得单例
     */
    public static NetworkUtil getInstance() {
        if (instance == null) {
            synchronized (NetworkUtil.class) {
                if (instance == null) {
                    instance = new NetworkUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 更新引擎的网络代理设置
     *
     * @param context
     */
    public void updateNetworkProxy(Context context) {

        mUseProxy = false;
        NetworkInfo info = getActiveNetworkInfo(context);

        if (info != null && info.isAvailable()) {
            String typeName = info.getTypeName().toLowerCase(); // WIFI/MOBILE

            if (typeName.equals("wifi") && info.isConnected()) { // wifi 连通状态
//                AppEngine.setProxyInfo(null, 0);
                return;
            }

            if (typeName.equals("mobile") || (typeName.equals("wifi") && !isWifiConnected(info))) { // wifi断开或mobile

                String extraInfo = info.getExtraInfo(); // 3gnet/3gwap/uninet/uniwap/cmnet/cmwap/ctnet/ctwap
                if (extraInfo != null) {
                    String extraInfoName = extraInfo.toLowerCase();
                    if (extraInfoName.startsWith("cmwap") || extraInfoName.startsWith(
                            "uniwap") || extraInfoName.startsWith("3gwap") || extraInfoName.startsWith("cuwap")) {
                        mProxyHost = "10.0.0.172";
                        mProxyPort = 80;
                        mUseProxy = true;
                    } else if (extraInfoName.startsWith("ctwap")) {
                        mProxyHost = "10.0.0.200";
                        mProxyPort = 80;
                        mUseProxy = true;
                    } else if (extraInfoName.startsWith("cmnet") || extraInfoName.startsWith(
                            "uninet") || extraInfoName.startsWith("ctnet") || extraInfoName.startsWith("3gnet")) {
                        mUseProxy = false;
                    }
                } else {
                    // 如果没有 apn 信息，则根据 proxy代理判断。
                    // 由于android 4.2 对 "content://telephony/carriers/preferapn" 读取进行了限制，我们通过系统接口获取。

                    // 绝大部分情况下不会走到这里
                    // 此两个方法是deprecated的，但在4.2下仍可用
                    String defaultProxyHost = android.net.Proxy.getDefaultHost();
                    int defaultProxyPort = android.net.Proxy.getDefaultPort();
                    if (defaultProxyHost != null && defaultProxyHost.length() > 0) {
                        /*
                         * 无法根据  proxy host 还原 apn 名字 这里不设置  mApn
                         */
                        if ("10.0.0.172".equals(defaultProxyHost.trim())) {
                            // 当前网络连接类型为cmwap || uniwap
                            mProxyHost = "10.0.0.172";
                            mProxyPort = defaultProxyPort;
                            mUseProxy = true;
                        } else if ("10.0.0.200".equals(defaultProxyHost.trim())) {
                            mProxyHost = "10.0.0.200";
                            mProxyPort = 80;
                            mUseProxy = true;
                        } else {
                        }
                    } else {
                        // 其它网络都看作是net
                    }
                }

            }
        }

    }

    /**
     * 获取当前活动的网络连接
     *
     * @param context
     * @return 活动的连接信息，可能为null
     */
    public NetworkInfo getActiveNetworkInfo(Context context) {
        if (context == null) {
            return null;
        }
        try {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return manager.getActiveNetworkInfo();
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 获取所有NetworkInfo
     *
     * @param context
     * @return
     */
    public NetworkInfo[] getAllNetworkInfo(Context context) {
        if (context == null) {
            return null;
        }
        try {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return manager.getAllNetworkInfo();
        } catch (Exception e) {
        }
        return null;
    }

    public String getCurrentNetMode(Context context) {
        int netype = NETYPE_NOCON;

        NetworkInfo info = getActiveNetworkInfo(context);

        if (null != info) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {  // 使用WiFi联网
                netype = NETYPE_WIFI;
            } else {    // 使用mobile联网
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                int type = tm.getNetworkType(); // mobile types
                switch (type) {
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                        netype = NETYPE_2G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        netype = NETYPE_4G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                        netype = NETYPE_TELECOM_2G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                        netype = NETYPE_MOBILE_UNICOM_2G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        netype = NETYPE_TELECOM_3G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_HSDPA: // HSDPA
                        netype = NETYPE_MOBILE_3G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_HSPAP: // HSPA+
                        netype = NETYPE_UNICOM_3G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                        netype = NETYPE_3G;
                        break;
                    default:
                        netype = NETYPE_UNKNOWN;
                        break;
                }
            }
        } else {
            netype = NETYPE_NOCON;
        }
        return Integer.toString(netype);
    }

    /****
     * 不提供上下文信息的
     * @return
     */
    public boolean isNetworkAvailable() {
        return isNetworkAvailable(ZxinRootApp.getApp());
    }

    /**
     * 获取当前网络状态
     *
     * @param context 系统上下位（GlobalUtils.getContext()）
     * @return true/false
     */
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            /*boolean isAvailable = cm.isNetworkAvailable();
            return isAvailable;*/
        }
        return false;
    }

    /**
     * WIFI是否连接
     *
     * @param context
     * @return WIFI已连接 返回 true,否则 false
     */
    public boolean isWifiConnected(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        return isWifiConnected(connectivityManager.getActiveNetworkInfo());
    }

    /**
     * 判断传入的 NetWorkInfo是否是wifi已连接
     *
     * @param activeNetInfo NetworkInfo
     * @return 是WIFI且已连接 返回true，否则false
     */
    private boolean isWifiConnected(NetworkInfo activeNetInfo) {
        if (activeNetInfo == null) {
            return false;
        }
        return ConnectivityManager.TYPE_WIFI == activeNetInfo.getType() && activeNetInfo.isConnected();
    }

    public int getWifiRssiLevel(Context context) {
        // getRssi得到的值是一个0到-100的区间值，其中0到-50表示信号最好，-50到-70表示信号偏差
        // 小于-70表示最差, 有可能连接不上或者掉线，一般Wifi已断则值为-200。
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo != null) {
            return wifiInfo.getRssi();
        }
        return -200;
    }

    /**
     * 判断wifi状态
     *
     * @param context
     * @return true 为可用
     */
    public boolean isWifiState(Context context) {
        if (context == null) {
            return false;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int wifiState = WifiManager.WIFI_STATE_UNKNOWN;
        try {
            wifiState = wifiManager.getWifiState();
        } catch (Exception e) {
            // 防止 NPE 错误
        }
        return wifiState == WifiManager.WIFI_STATE_ENABLED;
    }

    /*public boolean isConnected() {
        boolean result = false;

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) NioRootApp.getApp().getApplicationContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (null != connectivityManager) {
                //result = connectivityManager.isNetworkAvailable();
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null) {
                    switch (networkInfo.getState()) {
                        case CONNECTED:
                            result = true;
                            break;
                        case CONNECTING:
                        case DISCONNECTED:
                        case DISCONNECTING:
                        case SUSPENDED:
                        case UNKNOWN:
                        default:
                            result = false;
                            break;
                    }
                } else {
                    result = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }*/

}

