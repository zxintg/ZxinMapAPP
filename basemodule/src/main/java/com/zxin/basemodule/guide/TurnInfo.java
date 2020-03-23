package com.zxin.basemodule.guide;

public class TurnInfo {
    private int remainDistance;
    private String directionName;
    private String nodeName;
    private int tollFare;
    private TurnKind turnKind = TurnKind.TURN_KIND_FRONT;
    private String iconFileName;
    private String nextRoadName;
    private int sideStreet;
    private int followDirectionType;
    private int combineSideStreet;
    private boolean isStraight;

    public int getRemainDistance() {
        return remainDistance;
    }

    public void setRemainDistance(int remainDistance) {
        this.remainDistance = remainDistance;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public int getTollFare() {
        return tollFare;
    }

    public void setTollFare(int tollFare) {
        this.tollFare = tollFare;
    }

    public TurnKind getTurnKind() {
        return turnKind;
    }

    public void setTurnKind(TurnKind turnKind) {
        this.turnKind = turnKind;
    }

    public String getIconFileName() {
        return iconFileName;
    }

    public void setIconFileName(String iconFileName) {
        this.iconFileName = iconFileName;
    }

    public String getNextRoadName() {
        return nextRoadName;
    }

    public void setNextRoadName(String nextRoadName) {
        this.nextRoadName = nextRoadName;
    }

    public int getSideStreet() {
        return sideStreet;
    }

    public void setSideStreet(int sideStreet) {
        this.sideStreet = sideStreet;
    }

    public int getFollowDirectionType() {
        return followDirectionType;
    }

    public void setFollowDirectionType(int followDirectionType) {
        this.followDirectionType = followDirectionType;
    }

    public int getCombineSideStreet() {
        return combineSideStreet;
    }

    public void setCombineSideStreet(int combineSideStreet) {
        this.combineSideStreet = combineSideStreet;
    }

    public boolean isStraight() {
        return isStraight;
    }

    public void setStraight(boolean straight) {
        isStraight = straight;
    }
}
