package com.zxin.basemodule.guide;

public class SafetyInfo {
    private boolean isVisible;
    private int type;
    private int remainDistance;

    public SafetyInfo() {
    }

    public boolean isVisible() {
        return this.isVisible && 0 < this.remainDistance;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRemainDistance() {
        return this.remainDistance;
    }

    public void setRemainDistance(int remainDistance) {
        this.remainDistance = remainDistance;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("isVisible: ").append(this.isVisible).append(", ").append("type: ").
                append(this.type).append(", ").append("remainDistance: ").
                append(this.remainDistance).append(", ");
        return sb.toString();
    }
}
