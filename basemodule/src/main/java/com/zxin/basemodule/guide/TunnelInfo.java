package com.zxin.basemodule.guide;

public class TunnelInfo {
    private int distance = -1;
    private int nextTunnelLength = 0;
    private int nextTunnelRoadLevel = -1;
    private int currentTunnelStatus;

    public TunnelInfo() {
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getNextTunnelLength() {
        return this.nextTunnelLength;
    }

    public void setNextTunnelLength(int nextTunnelLength) {
        this.nextTunnelLength = nextTunnelLength;
    }

//    @BDMapmatchRoadLevel
//    public int getNextTunnelRoadLevel() {
//        int nextTunnelRoadLevel = this.nextTunnelRoadLevel;
//        return nextTunnelRoadLevel;
//    }

//    public void setNextTunnelRoadLevel(@BDMapmatchRoadLevel int nextTunnelRoadLevel) {
//        this.nextTunnelRoadLevel = nextTunnelRoadLevel;
//    }
//
//    @BDTunnelStatus
//    public int getCurrentTunnelStatus() {
//        return this.currentTunnelStatus;
//    }
//
//    public void setCurrentTunnelStatus(@BDTunnelStatus int currentTunnelStatus) {
//        this.currentTunnelStatus = currentTunnelStatus;
//    }
}
