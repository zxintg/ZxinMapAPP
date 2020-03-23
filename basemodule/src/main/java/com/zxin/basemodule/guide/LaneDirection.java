package com.zxin.basemodule.guide;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LaneDirection {
    public static final int LANE_VALID_NONE = 0;
    public static final int LANE_VALID_POSSIBLE = 1;
    public static final int LANE_VALID_RECOMMEND = 2;
    public static final int LANE_VALID_IMPOSSIBLE = 3;
    private byte direction;
    @LaneDirection.LaneValidType
    private int validType;

    public LaneDirection() {
    }

    public byte getDirection() {
        return this.direction;
    }

    public void setDirection(byte direction) {
        this.direction = direction;
    }

    @LaneDirection.LaneValidType
    public int getValidType() {
        return this.validType;
    }

    public void setValidType(@LaneDirection.LaneValidType int validType) {
        this.validType = validType;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("direction: ").append(this.direction).append(", ").append("validType: ")
                .append(this.validType).append(", ");
        return sb.toString();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other == null) {
            return false;
        } else if (!(other instanceof LaneDirection)) {
            return false;
        } else {
            LaneDirection direction = (LaneDirection) other;
            if (this.getDirection() != direction.getDirection()) {
                return false;
            } else {
                return this.getValidType() == direction.getValidType();
            }
        }
    }

    public int hashCode() {
        return this.getValidType();
    }

    @Retention(RetentionPolicy.CLASS)
    public @interface LaneValidType {
    }
}
