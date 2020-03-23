package com.zxin.basemodule;

public enum EngineType {
    AMAP("高德地图"), BAIDU("百度地图"), TANGRAM(""), MAPBOX("mapbox地图"),TOMTOM("TomTom地图"),TENXUN("腾讯地图"),GOOLGLE("谷歌地图");

    private String mTag;
    public String getTag(){
        return mTag;
    }

    EngineType(String tag){
        this.mTag = tag;
    }
}