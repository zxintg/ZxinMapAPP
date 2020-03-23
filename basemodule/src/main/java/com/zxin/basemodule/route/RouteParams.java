package com.zxin.basemodule.route;

import com.zxin.basemodule.model.Poi;
import com.zxin.basemodule.model.ViaPoi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RouteParams {

    private Poi startPoint;

    private Poi destPoint;

    private List<ViaPoi> viaList = new CopyOnWriteArrayList<>();

    private PlateInfo plateInfo;

    private IRoute.RouteOption routeOption = IRoute.RouteOption.RECOMMEND;

    private IRoute.NetMode mNetMode = IRoute.NetMode.FIRST_ONLINE;

    public void setNetMode(IRoute.NetMode mNetMode) {
        this.mNetMode = mNetMode;
    }

    public IRoute.NetMode getNetMode() {
        return mNetMode;
    }

    public Poi getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Poi startPoint) {
        this.startPoint = startPoint;
    }

    public Poi getDestPoint() {
        return destPoint;
    }

    public void setDestPoint(Poi destPoint) {
        this.destPoint = destPoint;
    }

    public List<ViaPoi> getViaList() {
        return viaList;
    }

    public List<Poi> getRouteList() {
        List<Poi> routeList = new ArrayList<>();
        routeList.add(startPoint);
        if (viaList != null && !viaList.isEmpty()) {
            routeList.addAll(viaList);
        }
        routeList.add(destPoint);
        return routeList;
    }

    public void setViaList(List<ViaPoi> viaList) {
        if (viaList == null || viaList.isEmpty()) {
            this.viaList.clear();
            return;
        }
        this.viaList = viaList;
    }

    public PlateInfo getPlateInfo() {
        return plateInfo;
    }

    public void setPlateInfo(PlateInfo plateInfo) {
        this.plateInfo = plateInfo;
    }

    public IRoute.RouteOption getRouteOption() {
        return routeOption;
    }

    public void setRouteOption(IRoute.RouteOption routeOption) {
        this.routeOption = routeOption;
    }
}
