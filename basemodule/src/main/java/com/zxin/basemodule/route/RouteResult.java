package com.zxin.basemodule.route;

import com.zxin.basemodule.common.ResultCode;

import java.util.List;

public class RouteResult {
    List<RouteInfo> routeList;
    IRoute.NetMode netMode;
    IRoute.RouteState routeState;
    RouteParams params;
    ResultCode resultCode;
    RouteInfo selectRoute;

    public List<RouteInfo> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<RouteInfo> routeList) {
        this.routeList = routeList;
    }

    public IRoute.NetMode getNetMode() {
        return netMode;
    }

    public void setNetMode(IRoute.NetMode netMode) {
        this.netMode = netMode;
    }

    public IRoute.RouteState getRouteState() {
        return routeState;
    }

    public void setRouteState(IRoute.RouteState routeState) {
        this.routeState = routeState;
    }

    @Override
    public String toString() {
        return "RouteResult:NetMode: " + netMode + " ResultSize: " + (routeList != null ? routeList.size() : 0);
    }

    public void setParams(RouteParams params) {
        this.params = params;
    }

    public RouteParams getParams() {
        return params;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }


    public RouteInfo getSelectRoute() {
        return selectRoute;
    }

    public void setSelectRoute(RouteInfo selectRoute) {
        this.selectRoute = selectRoute;
    }
}
