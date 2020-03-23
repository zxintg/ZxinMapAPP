package com.zxin.basemodule.util;

import android.app.AlarmManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;


import com.zxin.basemodule.app.ZxinRootApp;

import java.io.File;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alex.liu on 2017/11/30.
 */

public class ZxinUtil {

    public static final String ACTIVITY_NAME = "com.zxin.map.MainActivity";
    private static final String ENGINE_CACHE_DIR = "ZxinMapCache";
    private static final String NAVI_INFO_ENGINE_CACHE_DIR = "mcarnavi";
    private static final String NAVI_INFO_DATA_ZIP_FILE = "mcarnavi.zip";

    private static final String APP_LOG_CACHE_DIR = "ZxinAppLog";
    private static final String BAIDU_LOG_DIR = "BaiduMapAutoSDK/log";
    private static final String SDK_LOG_TEACE_DIR = "trace";
    private static final String PATH_PROPERTY_NAME = "persist.sys.nav_res_path";
    private static final String LAUNCHER_PACKAGE_NAME_KEY = "persist.sys.launcher_pkg";
    public static final String PREFERENCE_DAY_TO_DELET_LOG = "delete_log_time";
    public static final String PREFERENCE_IS_RECORD_LOG = "record_log_yes_or_no";
    private static final String PREFERENCE_IS_ACTIVATE_SDK = "activate_sdk_yes_or_no";

    public static final String ALARM_ICON_CHANGE = "icon_change";
    public static final String CARLOGO_DEFAULT_NAME = "guoqin_my_car_loc.png";
    public static final String CARLOGO_PATH = "carlogo/";
    /**
     * Nio vehicle Rom res path is : "/storage/nionavi"; Nio Pad Rom res path is :
     * "/storage/emulated/nionavi"; Other rom res path is /sdcard/nionavi
     */
    public static final String PATH_VALUE_ON_CUSTOMER = "/sdcard/zxinnavi";
    public static final String ACCOUNT_PIC = "user.png";

    public static final int PATH_VALUE_DAY_TO_DELETE_LOG = 3;
    public static final long ONE_DAY = (24 * 60 * 60 * 1000);
    public static final String PATH_VALUE_ON_CAR = "/storage/zxinnavi";
    private static final String DEBUG_MAGIC_STRING = "#*123456*#";
    /*关闭SDK log的默认记录*/
    private static boolean mIsLog = false;
    private static int mDeleteLogDay = 3;
    // 离线数据存储路径
    private static String mOfflineDataPath = "";
    private static final String mMunicipality[] = {"上海", "北京", "天津", "重庆", "香港", "澳门", "台湾"};
    private static String mVersionName;
    private static String mVersionCode;
    public static final String CAR_MODEL_ES8 = "ES8";
    public static final String CAR_MODEL_ES6 = "ES6";

    public static final String EXTRA_TYPE = "type";//对应com.nextev.push.NextevPush 的 Constants.EXTRA_TYPE
    public static final String EXTRA_MESSAGE = "com.nextev.push.EXTRA_MESSAGE";//对应com.nextev.push.NextevPush 的 Constants.EXTRA_MESSAGE
    public static final String INTENT_ALARM_CLOCK = "com.nio.navi.intent_alarm_clock"; //闹钟

    public static final String PUSH_SUBTYPE = "hu_app_config";

    public static String getAppVersionName(Context context) {
        if (TextUtils.isEmpty(mVersionName)) {
            try {
                PackageManager packageManager = context.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                String appVersion = packageInfo.versionName + ""; // appVersion
                mVersionName = appVersion;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mVersionName;
    }

    public static String getAppVersionCode(Context context) {
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

    public static final long getSyncTimestamp() {
        return System.currentTimeMillis();
    }

    public static String getSyncDateStamp() {
        Date date = new Date(System.currentTimeMillis());
        String dateStr = date.toString();
        return dateStr;
    }

    public static String getIntervalsTime(long pastTime, long currentTime) {
        return (currentTime - pastTime) / 1000.0 + "s";
    }

    public static String getSyncDateStamp(long timeMillis) {
        if (timeMillis == 0) {
            return "";
        }
        Date data = new Date(timeMillis);
        String dataStr = data.toString();
        return dataStr;
    }

    public static final long getUpdateTimestamp() {
        return System.currentTimeMillis();
    }

    public static final long getRequestTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static final long getReportTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static final String getAppId() {
        return "";
    }

    public static String getAk() {
        return "1b4ceb0c4e9946919cbb4e0fcb40b0f1";
    }

    public static String getLanguage() {
        Locale locale = ZxinRootApp.getApp().getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        return language;
    }

    public static String getRegion() {
        Locale locale = ZxinRootApp.getApp().getResources().getConfiguration().locale;
        String country = locale.getCountry();
        return country;
    }

    public static String getOS() {
        return "android";
    }

    public static String getOSVer() {
        return Build.DISPLAY;
    }

    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * Get the event time, the value unit is s
     *
     * @return
     */
    public static final long getEventTime() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * Get the event time, the value unit is ms
     *
     * @return
     */
    public static final long getTimestamp() {
        return System.currentTimeMillis();
    }

    public static double convertPointsToGps(int gps) {
        return gps / 1e5;
    }


    public static int convertGpsToInt(double gps) {
        return (int) (gps * 1e5);
    }

    public static int convertGpsToInt(String gps) {
        return convertGpsToInt(Double.parseDouble(gps));
    }

    /**
     *  判断某个字符串是否存在于数组中
     *  @param stringArray 原数组
     *  @param subStr 查找的字符串
     *  @return 是否找到
     */
    private static boolean contains(String[] stringArray, String subStr) {
        for (String s : stringArray) {
            if (subStr.indexOf(s) != -1) {
                return true;
            }
        }

        return false;
    }


    /**
     * 去除字符串中的空格、回车、换行符、制表符等
     * @param str
     * @return
     */
    public static String replaceSpecialStr(String str) {
        String repl = "";
        if (str != null) {
            String regEx="[`~!@#$%^&*()+=|{}':;',\\\\s*\\\\t\\\\r\\\\n\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            repl = m.replaceAll("").trim();
        }
        return repl;
    }

    /**
     * 解析地址字符串获取省、市、区、路段
     *
     * @param address 地址字符串
     * @return 解析数组，0为province，1为city，2为area，3为road
     */
    public static String[] parseAddress(String address) {
        String regex = "([^省]+自治区|.*?省)?([^市]+自治州|.*?行政区|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)?([^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province, city, area, road = "";

        if (m.find()) {
            String[] result = new String[4];
            province = m.group(1);
            result[0] = province == null ? "" : replaceSpecialStr(province);
            city = m.group(2);
            result[1] = city == null ? "" : replaceSpecialStr(city);

            /*处理没有省份的直辖市/行政区*/
            if ("".equals(result[0]) && contains(mMunicipality, result[1])) {
                result[0] = result[1];
            }

            area = m.group(3);

            String newArea = area == null ? "" : replaceSpecialStr(area);
            result[2] = newArea;
            //添加区域中包含".县|.区|.市|.旗|.海域|.岛" 时 获取第一个。排除不需要的数据信息
            String pattern = ".县|.区|.市|.旗|.海域|.岛";
            Matcher mm = Pattern.compile(pattern).matcher(newArea);
            if(mm.find()) {
                result[2] = newArea.substring(0,mm.end());
            }
            road += m.group(4);
            result[3] = road == null ? "" : replaceSpecialStr(road);

            return result;
        }
        return null;
    }

    /*  平板：/storage/emulated/nionavi/NioMapCache/BaiduMapAutoSDK/log/NioAppLog */
    /*  车机：/storage/nionavi/NioMapCache/BaiduMapAutoSDK/log/NioAppLog */
    public static String getAppLogDirPath() {
        return getBaiduLogDirPath() + File.separator + APP_LOG_CACHE_DIR;
    }

    /*  平板：/storage/emulated/nionavi/NioMapCache/BaiduMapAutoSDK/log/trace */
    /*  车机：/storage/nionavi/NioMapCache/BaiduMapAutoSDK/log/trace */
    public static String getRecordTracePath() {
        return getBaiduLogDirPath() + File.separator + SDK_LOG_TEACE_DIR;
    }

    /*  平板：/storage/emulated/nionavi/NioMapCache/BaiduMapAutoSDK/log */
    /*  车机：/storage/nionavi/NioMapCache/BaiduMapAutoSDK/log */
    public static String getBaiduLogDirPath() {
        return getWorkDirectory() + File.separator + BAIDU_LOG_DIR;
    }

    /*这个方法是因为百度SDK现在记录log有两个地方*/
    /*  平板：/storage/emulated/nionavi/NioMapCache/log */
    /*  车机：/storage/nionavi/NioMapCache/log */
    public static String getBaiduLogDirPath2() {
        return getWorkDirectory() + File.separator + "log";
    }

    /*  平板：/storage/emulated/nionavi/NioMapCache */
    /*  车机：/storage/nionavi/NioMapCache */
    public static String getWorkDirectory() {
        if (TextUtils.isEmpty(mOfflineDataPath)) {
            mOfflineDataPath = getSdPath() + File.separator + ENGINE_CACHE_DIR;
        }

        return mOfflineDataPath;
    }

    /*  平板：/storage/emulated/nionavi/NioMapCache */
    /*  车机：/storage/nionavi/NioMapCache */
    public static String getNaviInfoMapDataDirectory() {
        return getSdPath() + File.separator + NAVI_INFO_ENGINE_CACHE_DIR;
    }

    /*  平板：/storage/emulated/nionavi/NioMapCache */
    /*  车机：/storage/nionavi/NioMapCache */
    public static String getNaviInfoMapDataZIP() {
        return getSdPath() + File.separator + NAVI_INFO_DATA_ZIP_FILE;
    }

    /*  平板：/storage/emulated/nionavi */
    /*  车机：/storage/nionavi */
    private static String getSdPath() {
        String naviPath = ""/*SystemProperties.get(PATH_PROPERTY_NAME,
                PATH_VALUE_ON_CUSTOMER)*/;

        return naviPath;
    }

    /*****
     *平板：/storage/emulated/nionavi
     *车机：/storage/nionavi
     * bind user picture
     */
    public static String getBuindUserPic(){
       return getWorkDirectory() + File.separator + ACCOUNT_PIC;
    }

    /****
     * 获取CarLogo 存储绝对路径
     * @return path：
     * 平板：/storage/emulated/nionavi
     * 车机：/storage/nionavi  name：    guoqin_my_car_loc.png
     */
    public static String getCarLogoPath() {
        return getSdPath()+ File.separator + CARLOGO_PATH;
    }


    /****
     * 判断是否在当前时间是之前
     * @param time
     * @return
     */
    public static boolean isValidTime(long time) {
        if (time <= 0) {
            return false;
        }
        long current = new Date().getTime();
        if (time <= current) {
            return true;
        }
        return false;
    }

    /****
     * 判断是否在当前时间是之后
     * @param time
     * @return
     */
    public static boolean isValidEndTime(long time) {
        if (time > 0) {
            return false;
        }
        long current = new Date().getTime();
        if (current <= time) {
            return true;
        }
        return false;
    }

    /****
     * 获取闹钟实例
     * @param context
     * @return
     *
     * RTC_WAKEUP :表示闹钟在睡眠状态下唤醒系统并执行提示功能，绝对时间。
     * RTC : 睡眠状态下不可用，绝对时间。
     * ELAPSED_REALTIME_WAKEUP : 睡眠状态下可用，相对时间。
     * ELAPSED_REALTIME : 睡眠状态下不可用，相对时间。
     *
     */
    public static AlarmManager getAlarmManager(Context context) {
        return (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

}
