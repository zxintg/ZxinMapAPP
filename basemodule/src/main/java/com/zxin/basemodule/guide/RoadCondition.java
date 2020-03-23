package com.zxin.basemodule.guide;

public class RoadCondition{
    private int roadIndex;
    private IGuide.RoadConditionType roadConditionType;
    private int distance;
    private int travelTime;

    public RoadCondition() {
    }

    public int getRoadIndex() {
        return this.roadIndex;
    }

    public void setRoadIndex(int roadIndex) {
        this.roadIndex = roadIndex;
    }

    public IGuide.RoadConditionType getRoadConditionType() {
        return this.roadConditionType;
    }

    public void setRoadConditionType(IGuide.RoadConditionType roadConditionType) {
        this.roadConditionType = roadConditionType;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTravelTime() {
        return this.travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

}
