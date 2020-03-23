package com.zxin.basemodule.route;

import com.zxin.basemodule.model.LonLat;

import java.util.ArrayList;
import java.util.List;

public class RouteInfo {
    private int id = -1;
    private String routeName;
    private List<LonLat> pointList = new ArrayList<>();
    private List<RoadInfo> roadList = new ArrayList<>();

    private long totalDistance = 0;
    private long totalTime = 0;
    private long trafficLight = 0;

    private int fare = 0;
    private Object mRef;
    private String geometry;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public List<LonLat> getPointList() {
        return pointList;
    }

    public void setPointList(List<LonLat> pointList) {
        this.pointList = pointList;
    }

    public long getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(long totalDistance) {
        this.totalDistance = totalDistance;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public long getTrafficLight() {
        return trafficLight;
    }

    public void setTrafficLight(long trafficLight) {
        this.trafficLight = trafficLight;
    }

    public List<RoadInfo> getRoadList() {
        return roadList;
    }

    public void setRoadList(List<RoadInfo> roadList) {
        this.roadList = roadList;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public Object getRef() {
        return mRef;
    }

    public void setRef(Object mRef) {
        this.mRef = mRef;
    }


    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    /**
     * Get geometry, common use to render route on map view.
     *
     * @see {@link com.mapbox.geojson.LineString#fromPolyline(String, int)}
     * @return polyline string
     */
    public String getGeometry() {
        return geometry;
    }
}
