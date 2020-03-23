package com.zxin.basemodule;

import android.app.Application;
import com.zxin.basemodule.model.LonLat;
import com.zxin.basemodule.system.ISystem;

public class InitParams {
    private Application context;
    private String id;
    private String vendor;
    private LonLat location;
    private EngineType type;
    private String carModel;
    private String platform;
    private String brand;
    private String offlineDataPath;
    private String workPath;
    /**
     * For get system property.
     * 1. token
     * 2. system property.
     * 3. user id.
     */
    private ISystem system;

    public Application getContext() {
        return context;
    }

    public void setContext(Application context) {
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public LonLat getLocation() {
        return location;
    }

    public void setLocation(LonLat location) {
        this.location = location;
    }

    public EngineType getType() {
        return type;
    }

    public void setType(EngineType type) {
        this.type = type;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOfflineDataPath() {
        return offlineDataPath;
    }

    public void setOfflineDataPath(String offlineDataPath) {
        this.offlineDataPath = offlineDataPath;
    }

    public String getWorkPath() {
        return workPath;
    }

    public void setWorkPath(String workPath) {
        this.workPath = workPath;
    }

    public void setSystem(ISystem system) {
        this.system = system;
    }

    public ISystem getSystem() {
        return system;
    }
}
