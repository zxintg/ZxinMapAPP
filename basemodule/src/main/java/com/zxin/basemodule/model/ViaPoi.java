package com.zxin.basemodule.model;

public class ViaPoi extends Poi {

    public ViaPoi(String name, LonLat pos) {
        super(IPoi.SOURCE_DEFAULT, name, pos);
    }

    public ViaPoi(Poi poi) {
        super(poi);
        this.isPassed = false;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    boolean isPassed;
    int index = -1;
}
