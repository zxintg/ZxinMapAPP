package com.zxin.basemodule.views;

import android.view.MotionEvent;

import com.zxin.basemodule.model.LonLat;
import com.zxin.basemodule.overlay.MapItem;

public interface IMapView {
    public static final float DEFAULT_PINNING_ZOOM_LEVEL = 17f;

    // 北向上默认比例尺为100m 与 DisplayUtils scales 数组 严格对应
    public static final int DEFAULT_SCALE_NORTH_UP = 17;

    // 3D车头向上默认比例尺为50m  修改为100米 与 DisplayUtils scales 数组 严格对应
    public static final int DEFAULT_SCALE_3DHEAD_UP = 17;

    // 车2D头向上默认比例尺为50m  修改为100米 与 DisplayUtils scales 数组 严格对应
    public static final int DEFAULT_SCALE_2DHEAD_UP = 17;
    //加电助手默认比例尺 200米
    public static final float DEFAULT_SCALE_CHARGING = 16.0f;
    //设置默认比例尺标识
    public static final float DEFAULT_SCALE = 0;
    //不需要设置比例尺标识
    public static final float SCALE_NORMAL = -1;

    // 3d默认俯仰角为-60度
    public static final float DEFAULT_PITCH_BIRDVIEW = -60f;

    // 2d默认俯仰角为0度
    public static final float DEFAULT_PITCH_HEADUP = 0f;

    //默认MapView偏转角度  MapAngle
    public static final float DEFAULT_MAP_ANGLE = 0f;


    public final static int CENTER_DP_OFFSET_Y = 55;
    public final static int HALF_SCREEN_HEIGHT = 533; //半屏高度为533dp

    enum MapType {
        NORMAL, SATELLITE
    }

    enum  MapMode {
        Mode_2D, Mode_3D
    }

    enum ThemeMode {
        DAY, Night
    }

    interface IThemeChangedListener {
        void onThemeChanged(ThemeMode mode);
    }

    interface IMapViewListener {
        void onMapLevelChanged(float level);
        void onMapPositionChanged(LonLat lonLat);
    }

    /**
     * Listen map view click event.
     */
    interface IMapTouchListener {
        void onMapDown(MotionEvent event);
        boolean onMapUp(MotionEvent event);
        boolean onMapDoubleTap(MotionEvent event);
        boolean onMapScroll(MotionEvent e1, MotionEvent e2);
        boolean onMapClick(MotionEvent event, MapItem item);
        boolean onMapLongPress(MotionEvent event, LonLat lonLat);
    }

    interface OnMapLoadListener {
        void onMapCreated();
        void onMapLoaded();
        void onSurfaceChanged();
    }

}