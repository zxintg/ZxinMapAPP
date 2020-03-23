package com.zxin.basemodule.views;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.zxin.basemodule.common.ResultCode;
import com.zxin.basemodule.model.LonLat;
import com.zxin.basemodule.overlay.IOverlay;
import com.zxin.basemodule.overlay.MapItem;
import com.zxin.basemodule.route.RouteInfo;

import java.util.List;

public interface IMapViewController extends IOverlay {

    void onDestroy();

    void onResume();

    void onPause();

    void setMapMode(IMapView.MapMode mode);

    void setMapViewListener(IMapView.IMapViewListener listener);

    void setFollowingCar(boolean followingCar);

    void drawRoutes(List<RouteInfo> routeInfoList);

    void clearRoutes();

    void setRouteDisplayRect(RectF rect);

    void selectRoute(RouteInfo info);

    void configAccordingToGuideStatus();

    /**
     * Get user clicked map item.
     * May be a poi, a route, and so on.
     * split it by {@link MapItem#getItemType()}
     *
     * @param x
     * @param y
     * @param customMakerPriority
     * @return MapItem
     */
    MapItem getClickedMapItem(int x, int y, boolean customMakerPriority);

    /**
     * Transform screen coordinate to geo coordinate.
     *
     * @param x screen x
     * @param y screen y
     * @return lon and lat.
     */
    LonLat screenToGeoCoordinate(int x, int y);


    /**
     * Transform geo coordinate to screen coordinate.
     *
     * @param lonLat gen coordinate
     * @return screen coordinate.
     */
    Point geoToScreenCoordinate(@NonNull LonLat lonLat);


    /**
     * Set map offset.
     *
     * @param offsetX
     * @param offsetY
     * @return
     */
    ResultCode setMapOffset(float offsetX, float offsetY, boolean animator);

    /**
     * Set map center.
     * Notice:
     * 1. This method disable follow car via {@link #setFollowingCar(boolean)}
     * 2. Effect with {@link #setMapOffset(float, float, boolean)}
     *
     * @param center
     * @return
     */
    ResultCode setMapCenter(LonLat center, boolean animator);

    ResultCode setTmcShow(boolean showTmc);

    ResultCode setMapTheme(IMapView.ThemeMode mode, IMapView.IThemeChangedListener listener);

    ResultCode setLocationRes(int drawableId);

    /**
     * Set map level.
     *
     * @param level
     * @param animator
     * @param isDelay
     * @return
     */
    ResultCode setMapLevel(float level, boolean animator, boolean isDelay);

    ResultCode setMapPitch(float pitch, boolean animator);

    String getMapTag();

    /****
     * set useDynamicCarLogo
     * @param mapThemeMode
     * @return
     */
    ResultCode useDynamicCarLogo(boolean mapThemeMode);

    /****
     * set ElementVisible
     * @param isDestLine
     * @return
     */
    ResultCode setElementVisible(boolean isDestLine);

    /*****
     * set MapTextSize
     * @param mapTextSize
     * @return
     */
    ResultCode setMapTextSize(int mapTextSize);

    /****
     * get MapPitch
     * @return
     */
    float getMapPitch();

    /*****
     * set baidu logo position
     * @param isSpecial
     * @param marginRight
     * @param marginBottom
     */
    ResultCode setLogoPosition(boolean isSpecial, final int marginRight, final int marginBottom);


    /****
     * set TTS
     * @param showTTS
     * @return
     */
    ResultCode setTTS(boolean showTTS);

    /****
     * set MapAutoLevel
     * @param isAutoLevel
     * @return
     */
    ResultCode setMapAutoLevel(boolean isAutoLevel);

    /****
     * get MapAutoLevelStatus
     * @return
     */
    boolean getMapAutoLevelStatus();

    /*****
     * set MapAngle
     * @param mapAngle
     * @param smooth
     */
    ResultCode setMapAngle(float mapAngle, boolean smooth);

    /****
     * get MapAngle
     * @return
     */
    float getMapAngle();

    /***
     * 清空自车图片
     * @return
     */
    ResultCode clearCarIcon();

    /****
     * 设置自车图标
     * @return
     */
    ResultCode setCarIcon(Bitmap bitmap, boolean isRecycledBitmap);
}
