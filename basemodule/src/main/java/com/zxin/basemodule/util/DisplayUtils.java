package com.zxin.basemodule.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import com.zxin.basemodule.model.LonLat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DisplayUtils {
    private static String mResDir = "";
    private static String mThemeRelatedDir = "";
    private static String sDARK_THEME_FILE = "dark";
    private static String sLIGHT_THEME_FILE = "light";

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

    public static double distance(LonLat p1, LonLat p2) {
        if (p1 == null || p2 == null) {
            return -1;
        }
        return distance(p1.getLat(), p1.getLon(), p2.getLat(), p2.getLon());
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
        view.layout(0,0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),  view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private static String getThemeRelatedDir(Context context, String fileName) {
        if (context == null || fileName == null) {
            return mThemeRelatedDir;
        }

//        if (NioThemeHelper.getThemeId(context) == Integer.parseInt(ThemeStore.THEME_DARK_ID)) {
//            mThemeRelatedDir = sDARK_THEME_FILE;
//        } else {
            try {
                context.getResources().getAssets().open(sLIGHT_THEME_FILE + File.separator + "hdpi" + File.separator + fileName);
                mThemeRelatedDir = sLIGHT_THEME_FILE;
            } catch (Exception e) {
                mThemeRelatedDir = sDARK_THEME_FILE;
            }
//        }
        return mThemeRelatedDir;
    }

    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {
        if (context == null) {
            return null;
        }
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open(getThemeRelatedDir(context, fileName) + File.separator + getResDir(context) + File.separator + fileName);
            Bitmap image = BitmapFactory.decodeStream(is);
            is.close();
            return image;
        } catch (IOException e) {
            return null;
        }
    }

}
