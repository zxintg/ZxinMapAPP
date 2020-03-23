package com.zxin.basemodule.route;

import com.zxin.basemodule.guide.TurnKind;

public class RoadInfo {
    private TurnKind tbt = TurnKind.TURN_KIND_ALONG;
    private String name;
    private int meter;
    private int trafficStatus;

    public TurnKind getTurnByTurn() {
        return tbt;
    }

    public void setTurnByTurn(TurnKind tbt) {
        this.tbt = tbt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return meter;
    }

    public void setDistance(int meter) {
        this.meter = meter;
    }

    public int getTrafficStatus() {
        return trafficStatus;
    }

    public void setTrafficStatus(int trafficStatus) {
        this.trafficStatus = trafficStatus;
    }
}
