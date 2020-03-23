package com.zxin.basemodule.guide;

import java.util.ArrayList;

public class LaneInfo {
    private boolean isVisible;
    private int remainDistance;
    private ArrayList<LaneItem> itemList;

    public LaneInfo() {
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public int getRemainDistance() {
        return this.remainDistance;
    }

    public void setRemainDistance(int remainDistance) {
        this.remainDistance = remainDistance;
    }

    public ArrayList<LaneItem> getItemList() {
        return this.itemList;
    }

    public void setItemList(ArrayList<LaneItem> itemList) {
        this.itemList = itemList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("isVisible: ").append(this.isVisible).append(", ").append("remainDistance: ")
                .append(this.remainDistance).append(", ");
        if (null != this.itemList) {
            int size = this.itemList.size();

            for(int i = 0; i < size; ++i) {
                LaneItem item = this.itemList.get(i);
                if (null != item) {
                    sb.append("The ").append(i).append("th: ").append(item.toString());
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
        } else if (!(other instanceof LaneInfo)) {
            return false;
        } else {
            LaneInfo info =
                    (LaneInfo)other;
            if (this.getItemList().size() != info.getItemList().size()) {
                return false;
            } else {
                for(int i = 0; i < this.getItemList().size(); ++i) {
                    if (!this.getItemList().get(i).equals(info.getItemList().get(i))) {
                        return false;
                    }
                }

                return true;
            }
        }
    }

    public int hashCode() {
        return this.getRemainDistance();
    }
}
