package com.zxin.basemodule.views;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.MotionEvent;

import androidx.annotation.NonNull;

import com.zxin.basemodule.common.IResultCode;
import com.zxin.basemodule.common.ResultCode;
import com.zxin.basemodule.model.LonLat;
import com.zxin.basemodule.overlay.MapItem;
import com.zxin.basemodule.route.RouteInfo;
import com.zxin.basemodule.util.CheckUtil;

import java.util.List;
import java.util.Vector;

public class MapViewController implements IMapViewController {

    private Vector<IMapViewController> mControllers = new Vector<>();

    private MapViewController() {

    }

    private static final MapViewController INSTANCE = new MapViewController();

    public static MapViewController getInstance() {
        return INSTANCE;
    }

    public void addController(IMapViewController mapViewController) {
        mControllers.add(mapViewController);
    }

    public void removeController(IMapViewController mapViewController) {
        mControllers.remove(mapViewController);
    }

    public void clearController() {
        mControllers.clear();
    }

    @Override
    public void onResume() {
        for (IMapViewController controller: mControllers) {
            controller.onResume();
        }
    }

    @Override
    public void onDestroy() {
        for (IMapViewController controller: mControllers) {
            controller.onDestroy();
        }
    }

    @Override
    public boolean dispatchClickEvent(MotionEvent event) {
        checkState();
        for (IMapViewController controller: mControllers) {
            if (controller.dispatchClickEvent(event)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onPause() {
        for (IMapViewController controller: mControllers) {
            controller.onPause();
        }
    }

    private void checkState() {
        CheckUtil.assertState(!mControllers.isEmpty()
                , "Map Controller not add, can't exec map control method");
    }

    @Override
    public void setMapMode(IMapView.MapMode mode) {
        checkState();
        for (IMapViewController controller: mControllers) {
            controller.setMapMode(mode);
        }
    }

    @Override
    public void setMapViewListener(IMapView.IMapViewListener listener) {
        for (IMapViewController controller: mControllers) {
            controller.setMapViewListener(listener);
        }
    }

    @Override
    public void setFollowingCar(boolean followingCar) {
        checkState();
        for (IMapViewController controller: mControllers) {
            controller.setFollowingCar(followingCar);
        }
    }

    @Override
    public void drawRoutes(List<RouteInfo> routeInfoList) {
        checkState();
        for (IMapViewController controller: mControllers) {
            controller.drawRoutes(routeInfoList);
        }
    }

    @Override
    public void clearRoutes() {
        checkState();
        for (IMapViewController controller: mControllers) {
            controller.clearRoutes();
        }
    }

    @Override
    public void setRouteDisplayRect(RectF rect) {
        checkState();
        for (IMapViewController controller: mControllers) {
            controller.setRouteDisplayRect(rect);
        }
    }

    @Override
    public void selectRoute(RouteInfo info) {
        checkState();
        for (IMapViewController controller: mControllers) {
            controller.selectRoute(info);
        }
    }

    @Override
    public void configAccordingToGuideStatus() {
        checkState();
        for (IMapViewController controller: mControllers) {
            controller.configAccordingToGuideStatus();
        }
    }

    @Override
    public IResultCode clearAllMapItemGroup() {
        checkState();
        for (IMapViewController controller: mControllers) {
            controller.clearAllMapItemGroup();
        }
        return ResultCode.RESULT_SUCCESS;
    }

    @Override
    public IResultCode addMapItemGroup(MapItem layer) {
        checkState();
        for (IMapViewController controller: mControllers) {
            controller.addMapItemGroup(layer);
        }
        return ResultCode.RESULT_SUCCESS;
    }

    @Override
    public IResultCode removeMapItemGroup(MapItem layer) {
        checkState();
        for (IMapViewController controller: mControllers) {
            controller.removeMapItemGroup(layer);
        }
        return ResultCode.RESULT_SUCCESS;
    }

    @Override
    public IResultCode updateMapItemGroup(MapItem layer) {
        checkState();
        for (IMapViewController controller: mControllers) {
            controller.updateMapItemGroup(layer);
        }
        return ResultCode.RESULT_SUCCESS;
    }

    @Override
    public MapItem getClickedMapItem(int x, int y, boolean customMakerPriority) {
        checkState();
        for (IMapViewController controller: mControllers) {
            MapItem item = controller.getClickedMapItem(x, y, customMakerPriority);
            if (item != null) {
                return item;
            }
        }
        return null;
    }

    @Override
    public LonLat screenToGeoCoordinate(int x, int y) {
        checkState();
        for (IMapViewController controller: mControllers) {
            LonLat lonlat = controller.screenToGeoCoordinate(x, y);
            if (lonlat != null) {
                return lonlat;
            }
        }
        return null;
    }

    @Override
    public Point geoToScreenCoordinate(@NonNull LonLat lonLat) {
        checkState();
        for (IMapViewController controller: mControllers) {
            Point point = controller.geoToScreenCoordinate(lonLat);
            if (point != null) {
                return point;
            }
        }
        return null;
    }

    @Override
    public ResultCode setMapCenter(LonLat center, boolean animator) {
        checkState();
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        for (IMapViewController controller: mControllers) {
            ResultCode code = controller.setMapCenter(center, animator);
            if (ResultCode.RESULT_SUCCESS != code) {
                resultCode = code;
            }
        }
        return resultCode;
    }

    @Override
    public ResultCode setMapOffset(float offsetX, float offsetY, boolean animator) {
        checkState();
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        for (IMapViewController controller: mControllers) {
            ResultCode code = controller.setMapOffset(offsetX, offsetY, animator);
            if (ResultCode.RESULT_SUCCESS != code) {
                resultCode = code;
            }
        }
        return resultCode;
    }

    @Override
    public ResultCode setTmcShow(boolean isShow) {
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        for (IMapViewController controller: mControllers) {
            ResultCode code = controller.setTmcShow(isShow);
            if (ResultCode.RESULT_SUCCESS != code) {
                resultCode = code;
            }
        }
        return resultCode;
    }

    @Override
    public String getMapTag() {
        for (IMapViewController controller: mControllers) {
            return controller.getMapTag();
        }
        return "";
    }

    @Override
    public ResultCode setMapTheme(IMapView.ThemeMode mode, IMapView.IThemeChangedListener listener) {
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        for (IMapViewController controller: mControllers) {
            ResultCode code = controller.setMapTheme(mode, listener);
            if (ResultCode.RESULT_SUCCESS != code) {
                resultCode = code;
            }
        }
        return resultCode;
    }

    @Override
    public ResultCode setLocationRes(int drawableId) {
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        for (IMapViewController controller: mControllers) {
            ResultCode code = controller.setLocationRes(drawableId);
            if (ResultCode.RESULT_SUCCESS != code) {
                resultCode = code;
            }
        }
        return resultCode;
    }

    @Override
    public ResultCode setMapLevel(float level, boolean animator,boolean isDelay) {
        checkState();
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        for (IMapViewController controller : mControllers) {
            ResultCode code = controller.setMapLevel(level, animator,isDelay);
            if (ResultCode.RESULT_SUCCESS != code) {
                resultCode = code;
            }
        }
        return resultCode;
    }

    @Override
    public ResultCode setMapPitch(float pitch, boolean animator) {
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        for (IMapViewController controller : mControllers) {
            ResultCode code = controller.setMapPitch(pitch, animator);
            return code;
        }
        return resultCode;
    }

    @Override
    public ResultCode useDynamicCarLogo(boolean isCarSpeed) {
        checkState();
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        for (IMapViewController controller : mControllers) {
            ResultCode code = controller.useDynamicCarLogo(isCarSpeed);
            if (ResultCode.RESULT_SUCCESS != code) {
                resultCode = code;
            }
        }
        return resultCode;
    }

    @Override
    public ResultCode setElementVisible(boolean isDestLine) {
        checkState();
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        for (IMapViewController controller : mControllers) {
            ResultCode code = controller.setElementVisible(isDestLine);
            if (ResultCode.RESULT_SUCCESS != code) {
                resultCode = code;
            }
        }
        return resultCode;
    }

    @Override
    public ResultCode setMapTextSize(int mapTextSize) {
        checkState();
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        for (IMapViewController controller : mControllers) {
            ResultCode code = controller.setMapTextSize(mapTextSize);
            if (ResultCode.RESULT_SUCCESS != code) {
                resultCode = code;
            }
        }
        return resultCode;
    }

    @Override
    public float getMapPitch() {
        checkState();
        for (IMapViewController controller : mControllers) {
            float mapPitch = controller.getMapPitch();
            return mapPitch;
        }
        return IMapView.DEFAULT_PITCH_HEADUP;
    }

    @Override
    public ResultCode setLogoPosition(boolean isSpecial, int marginRight, int marginBottom) {
        checkState();
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        for (IMapViewController controller : mControllers) {
            ResultCode code = controller.setLogoPosition(isSpecial, marginRight, marginBottom);
            if (ResultCode.RESULT_SUCCESS != code) {
                resultCode = code;
            }
        }
        return resultCode;
    }

    public ResultCode setTTS(boolean showTTS) {
        checkState();
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        for (IMapViewController controller : mControllers) {
            ResultCode code = controller.setTTS(showTTS);
            if (ResultCode.RESULT_SUCCESS != code) {
                resultCode = code;
            }
        }
        return resultCode;
    }

    @Override
    public ResultCode setMapAutoLevel(boolean isAutoLevel) {
        checkState();
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        for (IMapViewController controller : mControllers) {
            ResultCode code = controller.setMapAutoLevel(isAutoLevel);
            if (ResultCode.RESULT_SUCCESS != code) {
                resultCode = code;
            }
        }
        return resultCode;
    }

    @Override
    public boolean getMapAutoLevelStatus() {
        checkState();
        for (IMapViewController controller : mControllers) {
            return controller.getMapAutoLevelStatus();
        }
        return false;
    }

    @Override
    public ResultCode setMapAngle(float mapAngle, boolean smooth) {
        checkState();
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        for (IMapViewController controller : mControllers) {
            ResultCode code = controller.setMapAngle(mapAngle,smooth);
            if (ResultCode.RESULT_SUCCESS != code) {
                resultCode = code;
            }
        }
        return resultCode;
    }

    @Override
    public float getMapAngle() {
        checkState();
        for (IMapViewController controller : mControllers) {
            return controller.getMapAngle();
        }
        return IMapView.DEFAULT_MAP_ANGLE;
    }

    @Override
    public ResultCode clearCarIcon(){
        checkState();
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        for (IMapViewController controller : mControllers) {
            ResultCode code = controller.clearCarIcon();
            if (ResultCode.RESULT_SUCCESS != code) {
                resultCode = code;
            }
        }
        return resultCode;
    }

    @Override
    public ResultCode setCarIcon(Bitmap bitmap, boolean isRecycledBitmap){
        checkState();
        ResultCode resultCode = ResultCode.RESULT_SUCCESS;
        return resultCode;
    }
}