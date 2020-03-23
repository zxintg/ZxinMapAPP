package com.zxin.basemodule.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.HandlerThread;
import android.os.Looper;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.multidex.MultiDexApplication;

import com.zxin.basemodule.EngineType;
import com.zxin.basemodule.InitParams;
import com.zxin.basemodule.model.LonLat;
import com.zxin.basemodule.util.ZxinUtil;
import com.zxin.basemodule.viewmodel.ActivityViewModel;
import com.zxin.basemodule.viewmodel.AppViewModel;

import java.util.ArrayList;
import java.util.List;

public class ZxinRootApp extends MultiDexApplication implements ViewModelStoreOwner {
    private static ZxinRootApp sApp;
    private static Activity sActivity;
    private ViewModelStore mStore = new ViewModelStore();
    private static String mCurrentUser;

    public static final ZxinRootApp getApp() {
        return sApp;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        sApp = this;
    }

    public void attachActivity(Activity activity) {
        sActivity = activity;
    }


    /*public static final String getVehicleId() {
        return SystemProperties.get("persist.nextev.vehicle_id", "000000000000");
    }*/

    @Override
    public void onCreate() {
        super.onCreate();
        InitParams params = new InitParams();
        params.setContext(this);
        params.setBrand("Mapauto");
        params.setVendor("NIO");
        params.setCarModel("ES8");
        params.setPlatform("baidu");
        //params.setLocation(new LonLat(121.19691, 31.27860));
        params.setLocation(new LonLat(-84.6609330000, 43.5943320000));
        //params.setId(getVehicleId());
        //params.setType(EngineType.TANGRAM);
        //params.setType(EngineType.MAPBOX);
        params.setType(EngineType.BAIDU);
        //params.setType(EngineType.TOM_TOM);
        params.setWorkPath(ZxinUtil.getWorkDirectory());
        params.setOfflineDataPath(ZxinUtil.getWorkDirectory());

        //params.setSystem(new SystemImpl());
    }


    public final <T extends AppViewModel> T getAppViewModel(Class<T> clazz) {
        return ZxinRootApp.getApp().getViewModelProvider().get(clazz);
    }

    
    public static Activity getActivity() {
        return sActivity;
    }

    public ViewModelStore getViewModelStore() {
        return mStore;
    }
    private ViewModelProvider mViewModelProvider;
    private ViewModelProvider getViewModelProvider() {
        if (mViewModelProvider == null) {
            ViewModelProvider.AndroidViewModelFactory factory
                    = ViewModelProvider.AndroidViewModelFactory.getInstance(this);
            mViewModelProvider = new ViewModelProvider(this, factory);
        }
        return mViewModelProvider;
    }

    private static final String GLOBAL_SP_NAME = "GLOBAL_SP";

    public static SharedPreferences getSharedPreferences() {
        if (sApp != null) {
            return sApp.getSharedPreferences(GLOBAL_SP_NAME, Context.MODE_PRIVATE);
        }
        return null;
    }

    private static Looper mLooper;
    private static final Object mLock = new Object();

    public static Looper getSubLooper() {
        if (mLooper == null) {
            synchronized (mLock) {
                if (mLooper == null) {
                    HandlerThread handlerThread = new HandlerThread("DataCenterLog");
                    handlerThread.start();
                    mLooper = handlerThread.getLooper();
                }
            }
        }
        return mLooper;
    }
}
