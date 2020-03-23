package com.zxin.basemodule.util;

import android.location.Location;
import com.zxin.basemodule.model.LonLat;

public class CoordTransformUtils {

    static double xPi = 3.14159265358979324 * 3000.0 / 180.0;
    static double pi = 3.1415926535897932384626d;  // π
    static double a = 6378245.0d;  // 长半轴
    static double ee = 0.00669342162296594323d;  // 偏心率平方

    public static Location gcj02_to_wgs84(Location location) {
        if (location == null) {
            return null;
        }
        double lng = location.getLongitude(), lat = location.getLatitude();
        if (out_of_china(lng, lat)) {
            return location;
        }
        double dlat = transformlat(lng - 105.0, lat - 35.0);
        double dlng = transformlng(lng - 105.0, lat - 35.0);
        double radlat = lat / 180.0 * pi;
        double magic = Math.sin(radlat);
        magic = 1 - ee * magic * magic;
        double sqrtmagic = Math.sqrt(magic);
        dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * pi);
        dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * pi);
        double mglat = lat + dlat;
        double mglng = lng + dlng;
        location.setLongitude(lng * 2 - mglng);
        location.setLatitude(lat * 2 - mglat);
        return location;
    }

    public static LonLat gcj02_to_wgs84(LonLat location) {
        if (location == null) {
            return null;
        }
        double lng = location.getLon(), lat = location.getLat();
        if (out_of_china(lng, lat)) {
            return location;
        }
        double dlat = transformlat(lng - 105.0, lat - 35.0);
        double dlng = transformlng(lng - 105.0, lat - 35.0);
        double radlat = lat / 180.0 * pi;
        double magic = Math.sin(radlat);
        magic = 1 - ee * magic * magic;
        double sqrtmagic = Math.sqrt(magic);
        dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * pi);
        dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * pi);
        double mglat = lat + dlat;
        double mglng = lng + dlng;
        location.setLon(lng * 2 - mglng);
        location.setLat(lat * 2 - mglat);
        location.setType(LonLat.TYPE_WSG84);
        return location;
    }

    private static boolean out_of_china(double lng, double lat) {
        return !(lng > 73.66 && lng < 135.05 && lat > 3.86 && lat < 53.55);
    }

    private static double transformlat(double lng, double lat) {
        double ret = 0d;
        ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat +
        0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * pi) + 20.0 *
                Math.sin(2.0 * lng * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lat * pi) + 40.0 *
                Math.sin(lat / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(lat / 12.0 * pi) + 320 *
                Math.sin(lat * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformlng(double lng, double lat) {
        double ret = 0d;
        ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng +
        0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * pi) + 20.0 *
                Math.sin(2.0 * lng * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lng * pi) + 40.0 *
                Math.sin(lng / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(lng / 12.0 * pi) + 300.0 *
                Math.sin(lng / 30.0 * pi)) * 2.0 / 3.0;
        return ret;
    }
}
