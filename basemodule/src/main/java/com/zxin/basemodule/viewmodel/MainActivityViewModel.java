package com.zxin.basemodule.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.zxin.basemodule.model.LonLat;
import com.zxin.basemodule.model.Poi;
import com.zxin.basemodule.views.IMapView;

public class MainActivityViewModel extends ActivityViewModel {
    private MutableLiveData<IMapView.MapMode> mMapMode = new MutableLiveData<>();
    private MutableLiveData<Boolean> isFollowingCar = new MutableLiveData<>();
    private MutableLiveData<Float> mapLevel = new MutableLiveData<>();
    private MutableLiveData<Boolean> isMapLoaded = new MutableLiveData<>();
    private MutableLiveData<Boolean> isGuidanceReady = new MutableLiveData<>();
    private MutableLiveData<Boolean> isRouteCalculating = new MutableLiveData<>();
    private MutableLiveData<LonLat> mPositionChanged = new MutableLiveData<>();
    private MutableLiveData<Boolean> isNavigating = new MutableLiveData<>();
    private MutableLiveData<String> mPushAlarmActive = new MutableLiveData<>();

    private Poi mLastDest;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<IMapView.MapMode> getMapMode() {
        return mMapMode;
    }

    public void setMapMode(Object caller, IMapView.MapMode mode) {
        checkCaller(caller);
        mMapMode.postValue(mode);
    }


    public MutableLiveData<Boolean> isFollowingCar() {
        return isFollowingCar;
    }

    public void setFollowingCar(Object caller, boolean followingCar) {
        checkCaller(caller);
        isFollowingCar.postValue(followingCar);
    }

    public MutableLiveData<Float> getMapLevel() {
        return mapLevel;
    }

    public void setMapLevel(float mapLevel) {
        this.mapLevel.postValue(mapLevel);
    }

    public void setDest(Poi poi) {
        mLastDest = poi;
    }

    public Poi getLastDest() {
        return mLastDest;
    }

    public void setMapLoaded(Boolean isMapLoaded) {
        this.isMapLoaded.setValue(isMapLoaded);
    }

    public MutableLiveData<Boolean> isMapLoaded() {
        return isMapLoaded;
    }

    public void setGuidanceReady(Boolean isGuidanceReady) {
        this.isGuidanceReady.setValue(isGuidanceReady);
    }

    public MutableLiveData<Boolean> isGuidanceReady() {
        return isGuidanceReady;
    }

    public void setRouteCalculating(Boolean isRouteCalculating) {
        this.isRouteCalculating.setValue(isRouteCalculating);
    }

    public MutableLiveData<Boolean> isRouteCalculating() {
        return isRouteCalculating;
    }

    public void setPositionChanged(LonLat lonLat) {
        this.mPositionChanged.postValue(lonLat);
    }

    public MutableLiveData<LonLat> getPositionChanged() {
        return mPositionChanged;
    }

    public void setNavigating(Boolean isNavigating) {
        this.isNavigating.setValue(isNavigating);
    }

    public MutableLiveData<Boolean> isNavigating() {
        return isNavigating;
    }

    public void setPushAlarmActive(String pushAlarmActive) {
        this.mPushAlarmActive.setValue(pushAlarmActive);
    }

    public MutableLiveData<String> getPushAlarmActive() {
        return mPushAlarmActive;
    }
}
