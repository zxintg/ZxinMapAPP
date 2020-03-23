package com.zxin.basemodule.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.zxin.basemodule.app.ZxinRootApp;
import com.zxin.basemodule.model.LonLat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DisplayUtil {
    private static String mResDir = "";
    private static final double meterMD5Factor = 111320;
    private static final int[] scales = {
            2000000, 1000000, 500000, 200000, 100000, //3 - 7
            50000, 25000, 20000, 10000, 5000, //8 - 12
            2000, 1000, 500, 200, 100, //13 - 17
            50, 20, 10, 5 //18 - 21
    };

    private final static double ZOOM_CONST = 320000;
    private final static double R = 6371229;

    private final static int INTERSECT_R = 15; // 点击半径, 判断是否相交, 单位: px

    private static String mThemeRelatedDir = "";
    private static final String DARK_THEME_FILE = "dark";
    private static final String LIGHT_THEME_FILE = "light";

    /*****
     * 厘米转换px
     * 72dpi 1厘米
     * @param cm 单位 厘米
     * @return
     */
    public static float cm2px(float cm){
        float scale = ZxinRootApp.getApp().getResources().getDisplayMetrics().density;
        return cm * 72 * scale + 0.5f;
    }

    public static double getZoomLevelByDist(double dist) {
        int startIndex = 3;
        int count = scales.length;
        if (dist < scales[count - 1]) {
            return count - 1 + startIndex;
        } else if (dist > scales[0]) {
            return startIndex;
        }

        for (int i = 1; i < count; i++) {
            int high = scales[i - 1];
            int low = scales[i];
            if (dist > low && dist < high) {
                double per = (dist - low) / (high - low);
                return i - 1 + startIndex + per;
            }
        }
        return count - 1 + startIndex;
    }

    public static double getZoomByDistPerPx(double dist) {
        double distPerDp = dist * ZxinRootApp.getApp().getResources().getDisplayMetrics().density;
        double zoom = Math.log(ZOOM_CONST / distPerDp) / Math.log(2);
        return zoom;
    }

    public static double getPxDistByZoom(double zoom) {
        double distPerDp = ZOOM_CONST / Math.pow(2, zoom);
        double dist = distPerDp / ZxinRootApp.getApp().getResources().getDisplayMetrics().density;
        return dist;
    }

    public static double distance(LonLat p1, LonLat p2) {
        if (p1 == null || p2 == null) {
            return -1;
        }
        return distance(
                p1.getLat(),
                p1.getLon(),
                p2.getLat(),
                p2.getLon());
    }

    //根据两者经纬度 计算距离
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double costLat = Math.cos(((lat1 + lat2) / 2.0) * Math.PI / 180);
        double dLat = lat1 - lat2;
        double dLon = (lon1 - lon2) * costLat;
        return Math.sqrt(dLat * dLat + dLon * dLon) * meterMD5Factor;
    }

    private static String getResDir(Context context) {
        if (mResDir.equals("")) {
            mResDir = context.getResources().getDisplayMetrics().density < 2 ? "hdpi" : "xhdpi";
        }
        return mResDir;
    }

    /**
     * Get a bitmap from assets dir.
     *
     * Relative with theme and density.
     *
     * @param context
     * @param fileName
     * @return null or bitmap
     */
    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {
        if (context == null) {
            return null;
        }
        AssetManager am = context.getAssets();
        InputStream is = null;
        try {
            is = am.open(File.separator + getResDir(context) + File.separator + fileName);
            Bitmap image = BitmapFactory.decodeStream(is);
            is.close();
            return image;
        } catch (IOException e) {
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                   e.printStackTrace();
                }
            }
        }
    }

    /**
     * Get screen with.
     * @return
     */
    public final static int getWidth() {
        return ZxinRootApp.getApp().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * Get screen height.
     * @return
     */
    public final static int getHeight() {
        return ZxinRootApp.getApp().getResources().getDisplayMetrics().heightPixels;
    }

    public static double getLongXDiff(double longX, double latY, double distance) {
        double a = (180 * distance) / (Math.PI * R * Math.cos(latY * Math.PI / 180));
        return a;
    }


    public static double getLatYDiff(double longX, double latY, double distance) {
        double a = (180 * distance) / (Math.PI * R);
        return a;
    }

    public static boolean isClickedPoint(int x, int y, int x1, int y1) {
        return x - INTERSECT_R < x1 + INTERSECT_R &&
                x1 - INTERSECT_R < x + INTERSECT_R &&
                y - INTERSECT_R < y1 + INTERSECT_R &&
                y1 - INTERSECT_R < y + INTERSECT_R;
    }

    /****
     * 根据缩放等级获取实际距离
     * @param zoomLevel 地图缩放等级
     * @return 返回 zoomLevel 对应的实际距离
     */
    public static int getDistanceByZoom(float zoomLevel) {
        int mLevel = Math.round(zoomLevel);
        if(mLevel < 3){
            mLevel = 3;
        }
        if(mLevel > 21){
            mLevel = 21;
        }
        return scales[mLevel - 3];
    }


    public static final Bitmap createBitmapFromView(View view) {
        // measure & layout
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.invalidate();
        // get bitmap
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        bitmap = Bitmap.createBitmap(bitmap);
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

}
