package com.zxin.basemodule.guide;

public class CameraInfo {
    private boolean isVisible;
    private IGuide.CameraType type;
    private int remainDistance;
    private int speedLimit;
    private boolean isOverSpeed;

    public CameraInfo() {
    }

    public boolean isVisible() {
        return this.isVisible && 0 < this.remainDistance;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public IGuide.CameraType getType() {
        return this.type;
    }

    public void setType(IGuide.CameraType type) {
        this.type = type;
    }

    public int getRemainDistance() {
        return this.remainDistance;
    }

    public void setRemainDistance(int remainDistance) {
        this.remainDistance = remainDistance;
    }

    public int getSpeedLimit() {
        return this.speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public boolean isOverSpeed() {
        return this.isOverSpeed;
    }

    public void setOverSpeed(boolean overSpeed) {
        this.isOverSpeed = overSpeed;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("isVisible: ").append(this.isVisible).append(", ").append("type: ").
                append(this.type).append(", ").append("remainDistance: ").
                append(this.remainDistance).append(", ").append("speedLimit: ").
                append(this.speedLimit).append(", ").append("isOverSpeed: ").
                append(this.isOverSpeed).append(", ");
        return sb.toString();
    }

}
