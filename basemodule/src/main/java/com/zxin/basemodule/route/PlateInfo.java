package com.zxin.basemodule.route;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlateInfo {
    private String mProvince;
    private String mPlateNumber;
    private String mPlateColor;
    private String mPlateCarClass;
    private PlateInfo mNextPlate;

    public PlateInfo(String mProvince, String mPlateNumber) {
        this.mProvince = mProvince;
        this.mPlateNumber = mPlateNumber;
    }
    public String getProvince() {
        return mProvince;
    }

    public String getPlateNumber() {
        return mPlateNumber;
    }

    public String getmPlateColor() {
        return mPlateColor;
    }

    public String getmPlateCarClass() {
        return mPlateCarClass;
    }

    public PlateInfo getNextPlate() {
        return mNextPlate;
    }

    public void setNextPlate(PlateInfo mNextPlate) {
        this.mNextPlate = mNextPlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlateInfo that = (PlateInfo) o;
        return mProvince.equals(that.mProvince)
                && mPlateNumber.equals(that.mPlateNumber);
    }

    @Override
    public int hashCode() {
        //TODO hashcode
        return Objects.hash(mProvince, mPlateNumber);
    }

    @Override
    public String toString() {
        return mProvince + mPlateNumber;
    }

    public boolean isLegal() {
        String mPlateNumber = this.toString();
        Pattern p = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领 A-Z]{1}[A-Z]{1}(?:(?![A-Z]{4,5})[A-Z0-9]){4,5}[A-Z0-9挂学警港澳]{1}$");
        Matcher m = p.matcher(mPlateNumber);
        if (!m.matches()) {
            return false;
        }
        return true;
    }
}
