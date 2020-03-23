package com.zxin.basemodule.model;

import com.zxin.basemodule.util.CheckUtil;

public final class LonLat {
    public static final int TYPE_GJC02 = 0;
    public static final int TYPE_WSG84 = 1;

    private double lon;

    private double lat;

    private double alt;

    private int type = TYPE_GJC02;

    public LonLat(double lon, double lat) {
        this(lon, lat, 0.0d, TYPE_GJC02);
    }

    public LonLat(double lon, double lat, double alt) {
        this(lon, lat, alt, TYPE_GJC02);
    }

    public LonLat(double lon, double lat, double alt, int type) {
        assert type == TYPE_GJC02 || type == TYPE_WSG84;
        this.lon = lon;
        this.lat = lat;
        this.alt = alt;
        this.type = type;
    }
    public LonLat(String location) {
        String[] parameters = location.split(",");
        if (parameters.length == 2) {
            this.lon = Double.parseDouble(parameters[0]);
            this.lat = Double.parseDouble(parameters[1]);
            this.alt = 0.0d;
            this.type = TYPE_GJC02;
        } else {
            CheckUtil.throwException("wrong location parameter in Json");
        }
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLonInt() {
        return toIntE5(getLon());
    }

    public int getLatInt() {
        return toIntE5(getLat());
    }

    public int getLonIntE6() {
        return toIntE6(getLon());
    }

    public int getLatIntE6() {
        return toIntE6(getLat());
    }

    public int getAltInt() {
        return (int) (getAlt() + 0.5);
    }


    @Override
    public String toString() {
        return "[" + lon + ", " + lat + ", " + alt + "]";
    }

    @Override
    public boolean equals(Object o) {
        boolean equal = super.equals(o);
        if (!equal) {
            if (o instanceof LonLat) {
                LonLat other = (LonLat) o;
                if (Double.doubleToLongBits(lon) == Double.doubleToLongBits(other.getLon())
                        && Double.doubleToLongBits(lat) == Double.doubleToLongBits(other.getLat())
                        && Double.doubleToLongBits(alt) == Double.doubleToLongBits(other.getAlt())
                        && Double.doubleToLongBits(type)
                        == Double.doubleToLongBits(other.getType())) {
                    return true;
                }
            }
        }
        return equal;
    }

    public static int toIntE5(double position) {
        return (int) (position * 1e5 + 0.5);
    }

    public static double toDoubleE5(int position) {
        return position / 1e5;
    }

    public static int toIntE6(double position) {
        return (int) (position * 1e6 + 0.5);
    }

    public static double toDoubleE6(int position) {
        return position / 1e6;
    }
}