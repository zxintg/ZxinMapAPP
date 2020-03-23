package com.zxin.basemodule.guide;

import android.graphics.Bitmap;

public class ImageViewInfo {
    public enum State {
        SHOW, HIDE, UPDATE
    }

    private State state = State.HIDE;


    private int id;
    private int width;
    private int height;
    private int remainDistance;
    private int remainDistancePercent;
    private Bitmap bitmap;
    private String roadName;
    private String directionFileName;

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        if (width == 0 && bitmap != null) {
            width = bitmap.getWidth();
        }
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        if (height == 0 && bitmap != null) {
            height = bitmap.getHeight();
        }
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRemainDistance() {
        return remainDistance;
    }

    public void setRemainDistance(int remainDistance) {
        this.remainDistance = remainDistance;
    }

    public int getRemainDistancePercent() {
        return remainDistancePercent;
    }

    public void setRemainDistancePercent(int remainDistancePercent) {
        this.remainDistancePercent = remainDistancePercent;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getDirectionFileName() {
        return directionFileName;
    }

    public void setDirectionFileName(String directionFileName) {
        this.directionFileName = directionFileName;
    }
}
