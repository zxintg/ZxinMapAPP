package com.zxin.basemodule.guide;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class LaneItem {
    public static final int LANE_TYPE_NORMAL = 0;
    public static final int LANE_TYPE_BUS = 1;
    public static final int LANE_TYPE_HIGHPASS = 2;
    public static final int LANE_TYPE_HOV = 3;
    public static final int LANE_LEVEL_NORMAL = 0;
    public static final int LANE_LEVEL_UNDERPASS = 1;
    public static final int LANE_LEVEL_OVERPASS = 2;
    public static final int LANE_VARIATION_NONE = 0;
    public static final int LANE_VARIATION_INCREASE = 1;
    public static final int LANE_VARIATION_DECREASE = 2;
    @LaneItem.LaneType
    private int laneType;
    @LaneItem.LaneLevelType
    private int laneLevelType;
    @LaneItem.LaneVariationType
    private int laneVariationType;
    private ArrayList<LaneDirection> directionList;

    public LaneItem() {
    }

    @LaneItem.LaneType
    public int getLaneType() {
        return this.laneType;
    }

    public void setLaneType(@LaneItem.LaneType int laneType) {
        this.laneType = laneType;
    }

    @LaneItem.LaneLevelType
    public int getLaneLevelType() {
        return this.laneLevelType;
    }

    public void setLaneLevelType(@LaneItem.LaneLevelType int laneLevelType) {
        this.laneLevelType = laneLevelType;
    }

    @LaneItem.LaneVariationType
    public int getLaneVariationType() {
        return this.laneVariationType;
    }

    public void setLaneVariationType(@LaneItem.LaneVariationType int laneVariationType) {
        this.laneVariationType = laneVariationType;
    }

    public ArrayList<LaneDirection> getDirectionList() {
        return this.directionList;
    }

    public void setDirectionList(ArrayList<LaneDirection> directionList) {
        this.directionList = directionList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("laneType: ").append(this.laneType).append(", ").append("laneLevelType: ")
                .append(this.laneLevelType).append(", ").append("laneVariationType: ")
                .append(this.laneVariationType).append(", ");
        if (null != this.directionList) {
            int size = this.directionList.size();

            for(int i = 0; i < size; ++i) {
                LaneDirection direction = (LaneDirection)this.directionList.get(i);
                if (null != direction) {
                    sb.append("The ").append(i).append("th: ").append(direction.toString());
                }
            }
        }

        return sb.toString();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other == null) {
            return false;
        } else if (!(other instanceof LaneItem)) {
            return false;
        } else {
            LaneItem item = (LaneItem)other;
            if (this.getLaneType() != item.getLaneType()) {
                return false;
            } else if (this.getDirectionList().size() != item.getDirectionList().size()) {
                return false;
            } else {
                for(int i = 0; i < this.getDirectionList().size(); ++i) {
                    if (!item.getDirectionList().get(i).equals(this.getDirectionList().get(i))) {
                        return false;
                    }
                }

                if (this.getLaneLevelType() != item.getLaneLevelType()) {
                    return false;
                } else if (this.getLaneVariationType() != item.getLaneVariationType()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    public int hashCode() {
        return this.getLaneType();
    }

    @Retention(RetentionPolicy.CLASS)
    public @interface LaneVariationType {
    }

    @Retention(RetentionPolicy.CLASS)
    public @interface LaneLevelType {
    }

    @Retention(RetentionPolicy.CLASS)
    public @interface LaneType {
    }
}
