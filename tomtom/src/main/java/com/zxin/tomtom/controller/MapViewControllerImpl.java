package com.zxin.tomtom.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import com.zxin.basemodule.common.IResultCode;
import com.zxin.basemodule.common.ResultCode;
import com.zxin.basemodule.model.LonLat;
import com.zxin.basemodule.overlay.MapItem;
import com.zxin.basemodule.route.RouteInfo;
import com.zxin.basemodule.views.IMapView;
import com.zxin.basemodule.views.IMapViewController;
import java.util.ArrayList;
import java.util.List;

public class MapViewControllerImpl implements IMapViewController {

    private View mMapView;
    public MapViewControllerImpl(View view) {
        mMapView = view;
    }

    @Override
    public ResultCode setMapTheme(final IMapView.ThemeMode mode,
                                  final IMapView.IThemeChangedListener listener) {
        return ResultCode.RESULT_SUCCESS;
    }

    @Override
    public ResultCode setLocationRes(int drawableId) {
        return ResultCode.RESULT_SUCCESS;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void setMapMode(IMapView.MapMode mode) {

    }

    @Override
    public void setMapViewListener(IMapView.IMapViewListener listener) {

    }

    @Override
    public void setFollowingCar(boolean followingCar) {

    }

    @Override
    public void drawRoutes(List<RouteInfo> routeInfoList) {

    }

    @Override
    public void clearRoutes() {

    }

    @Override
    public void setRouteDisplayRect(RectF rect) {

    }

    @Override
    public void selectRoute(RouteInfo info) {

    }

    @Override
    public void configAccordingToGuideStatus() {

    }

    @Override
    public MapItem getClickedMapItem(int x, int y, boolean customMakerPriority) {
        return null;
    }

    @Override
    public LonLat screenToGeoCoordinate(int x, int y) {
        return null;
    }

    @Override
    public Point geoToScreenCoordinate(@NonNull LonLat lonLat) {
        return null;
    }

    @Override
    public ResultCode setMapOffset(float offsetX, float offsetY, boolean animator) {
        return null;
    }

    @Override
    public ResultCode setMapCenter(LonLat center, boolean animator) {
        return ResultCode.RESULT_SUCCESS;
    }

    @Override
    public ResultCode setTmcShow(boolean showTmc) {
        return null;
    }

    @Override
    public String getMapTag() {
        return null;
    }

    @Override
    public ResultCode setMapLevel(float level, boolean animator, boolean isDelay) {
        return ResultCode.RESULT_SUCCESS;
    }

    @Override
    public ResultCode setMapPitch(float pitch, boolean animator) {
        return ResultCode.RESULT_SUCCESS;
    }

    @Override
    public IResultCode addMapItemGroup(MapItem group) {
        return null;
    }

    @Override
    public IResultCode removeMapItemGroup(MapItem group) {
        return null;
    }

    @Override
    public IResultCode updateMapItemGroup(MapItem group) {
        return null;
    }

    @Override
    public IResultCode clearAllMapItemGroup() {
        return null;
    }

    @Override
    public boolean dispatchClickEvent(MotionEvent event) {
        return false;
    }

    @Override
    public ResultCode useDynamicCarLogo(boolean mapThemeMode) {
        return null;
    }

    @Override
    public ResultCode setElementVisible(boolean isDestLine) {
        return null;
    }

    @Override
    public ResultCode setMapTextSize(int mapTextSize) {
        return null;
    }

    @Override
    public float getMapPitch() {
        return 0;
    }

    @Override
    public ResultCode setLogoPosition(boolean isSpecial, int marginRight, int marginBottom) {
        return null;
    }

    @Override
    public ResultCode setTTS(boolean showTTS) {
        return null;
    }

    @Override
    public ResultCode setMapAutoLevel(boolean isAutoLevel) {
        return null;
    }

    @Override
    public boolean getMapAutoLevelStatus() {
        return false;
    }

    @Override
    public ResultCode setMapAngle(float mapAngle, boolean smooth) {
        return null;
    }

    @Override
    public float getMapAngle() {
        return 0;
    }

    @Override
    public ResultCode clearCarIcon(){
        return null;
    }

    @Override
    public ResultCode setCarIcon(Bitmap bitmap, boolean isRecycledBitmap){
        return null;
    }
}
