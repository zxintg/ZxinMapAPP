package com.zxin.basemodule.guide;

import com.zxin.basemodule.model.LonLat;

public class MapMatchInfo {
    /**
     * After Match location
     */
    private LonLat position;

    /**
     * Before Match longitude
     */
    private int longitude;

    /**
     * Before Match latitude
     */
    private int latitude;

    /**
     * Before Match altitude
     */
    private float altitude;

    /**
     * After Match speed
     */
    private float speed;
    private float angle;
    private long tickCount;
    private int type;
    private int status;
    private int distance;
    private RoadLevel roadLevel;
    private String matchRoadName;
    private double mMatchingLinkLength;
    private int speedLimit;
    private boolean isTunnel;
    private int absLinkId;

    public LonLat getPosition() {
        return position;
    }

    public void setPosition(LonLat position) {
        this.position = position;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public long getTickCount() {
        return tickCount;
    }

    public void setTickCount(long tickCount) {
        this.tickCount = tickCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public RoadLevel getRoadLevel() {
        return roadLevel;
    }

    public void setRoadLevel(RoadLevel roadLevel) {
        this.roadLevel = roadLevel;
    }

    public String getMatchRoadName() {
        return matchRoadName;
    }

    public void setMatchRoadName(String matchRoadName) {
        this.matchRoadName = matchRoadName;
    }

    public double getMatchingLinkLength() {
        return mMatchingLinkLength;
    }

    public void setMatchingLinkLength(double matchingLinkLength) {
        this.mMatchingLinkLength = matchingLinkLength;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public boolean isTunnel() {
        return isTunnel;
    }

    public void setTunnel(boolean tunnel) {
        isTunnel = tunnel;
    }

    public int getAbsLinkId() {
        return absLinkId;
    }

    public void setAbsLinkId(int absLinkId) {
        this.absLinkId = absLinkId;
    }
}
