package com.zxin.basemodule.model;

import android.text.TextUtils;

import com.zxin.basemodule.net.NioMD5;
import com.zxin.basemodule.util.CheckUtil;

import java.util.List;

public class Poi implements IPoi {
    public final static String SERVICE_NIO = "NIO";
    public final static String SERVICE_STOP = "stop";
    public final static String SERVICE_DOOR = "door";
    public final static String CAR_SERVICE = "car_service";

    /**
     * Nio client id.
     * Generate by {@link #generateServerKey(Poi)}
     */
    String id;
    /**
     * Server id. may be
     * Map bar: nid
     * BaiDu: uid.
     * Other engine id.
     */
    String uid;
    /**
     * Poi name.
     */
    String name;
    /**
     * Poi Address
     */
    String address;

    int districtId;
    /**
     * Navigation position.
     */
    LonLat navPosition;
    /**
     * View position.
     */
    LonLat viewPosition;
    /**
     * Poi Phone number.
     * support multi phone number, split by ';'
     */
    String phoneNumber;
    /**
     * Source id:
     * bai du id.
     * map bar id.
     * amap id.
     */
     String sid;
    /**
     * Poi aliasName
     */
    String aliasName;
    /**
     * Current city
     */
    String city;
    /**
     * poi's tag.
     */
    String tag;
    /**
     * Source.
     * {@link IPoi#SOURCE_BAI_DU}
     * {@link IPoi#SOURCE_DEFAULT}
     * {@link IPoi#SOURCE_GIS}
     */
    int source;
    /**
     * Child poi.
     */
    private List<Poi> children;
    /**
     * the count of poi
     */
    private int count;

    String serviceType;

    /**
     * Don't use this method to new instance. only for room database use.
     */
    public Poi() {
    }

    public Poi(int source) {
        this.source = source;
    }

    public Poi(Poi poi) {
        this.name = poi.name;
        this.source = poi.source;
        this.viewPosition = poi.viewPosition;
        this.navPosition = poi.navPosition;
        this.id = poi.getId();
        this.address = poi.getAddress();
    }

    public Poi(int source, String name, LonLat pos) {
        this(source);
        this.name = name;
        this.viewPosition = pos;
    }

    public Poi(String uid, String name, String address) {
        this.uid = uid;
        this.name = name;
        this.address = address;
    }

    public Poi(String uid, LonLat viewPosition, LonLat navPosition) {
        this.uid = uid;
        this.viewPosition = viewPosition;
        this.navPosition = navPosition;
    }

    public Poi(String uid, String name, String address, LonLat viewPosition, LonLat navPosition) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.viewPosition = viewPosition;
        this.navPosition = navPosition;
    }

    public Poi(String poiName, String navAddress, String poiId,
               String poiUid, LonLat navPoint, LonLat viewPoint,
               String sourceId, int districtId) {
        this.name = poiName;
        this.address = navAddress;
        this.uid = poiUid;
        this.viewPosition = viewPoint;
        this.navPosition = navPoint;
        this.sid = sourceId;
        this.districtId = districtId;
        setId(poiId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LonLat getNavPosition() {
        return navPosition != null ? navPosition : viewPosition;
    }

    public void setNavPosition(LonLat navPosition) {
        this.navPosition = navPosition;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public List<Poi> getChildren() {
        return children;
    }

    public void setChildren(List<Poi> children) {
        this.children = children;
    }

    public boolean isValid() {
        return (null != getViewPosition()) && !TextUtils.isEmpty(getName());
    }

    public String getId() {
        if (TextUtils.isEmpty(id)) {
            id = generateServerKey(this);
        }
        return id;
    }

    public void setId(String id) {
        setId(id, false);
    }

    /**
     * Set poi id, for this poi.
     * Normal poi id auto set by {@link #generateServerKey(Poi)}.
     * But in same issues, like sync with tsp server, tsp server favorite has been set a id, and the
     * rules not equals {@link #generateServerKey(Poi)}, for keep id is unique, force use server id.
     *
     * @param id
     * @param force force to set, use for sync with server poi,
     *              same with server item id equal poi id.
     */
    public void setId(String id, boolean force) {
        CheckUtil.require(!force && TextUtils.isEmpty(this.id),
                "This poi's id has been set, can't set again!");
        if (TextUtils.isEmpty(id)) {
            CheckUtil.require(getViewPosition() != null && !TextUtils.isEmpty(getName()),
                    "set id must after set position and name.");
            id = generateServerKey(this);
        }
        this.id = id;
    }


    public String getSid() {
        return sid;
    }

    /**
     * Set source id.
     * like bai du id.
     *
     * @param sid
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getSource() {
        return source;
    }

    /**
     * Set source.
     *
     * @param source {@link IPoi#SOURCE_BAI_DU}, {@link IPoi#SOURCE_GIS}, {@link IPoi#SOURCE_DEFAULT}
     *               , {@link IPoi#SOURCE_DEFAULT}
     */
    public void setSource(int source) {
        this.source = source;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setViewPosition(LonLat viewPosition) {
        this.viewPosition = viewPosition;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LonLat getViewPosition() {
        return viewPosition != null ? viewPosition : navPosition;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }


    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public int getDistrictId() {
        return districtId;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return name + "(" + address + "): " + viewPosition;
    }


    /**
     * Generate server key, real server key must contain account id,
     * this method generate key not contain account id, so you need
     * append account id when you use this.
     * <p>
     * This method's generate must equals with map bar code, please
     * don't change this code, if you don't understand what it does.
     *
     * @param poi
     * @return
     */
    private static String generateServerKey(Poi poi) {
        LonLat viewPoint = poi.getViewPosition();
        int lon = viewPoint.getLonInt();
        int lat = viewPoint.getLatInt();
        int alt = viewPoint.getAltInt();
        return generateServerKey(poi.getName(), poi.getAddress(), lon, lat, alt);
    }

    /**
     * Generate server key, real server key must contain account id,
     * this method generate key not contain account id, so you need
     * append account id when you use this.
     * <p>
     * This method's generate must equals with map bar code, please
     * don't change this code, if you don't understand what it does.
     *
     * @param
     * @return
     */
    private static String generateServerKey(String name, String address, int lon, int lat, int alt) {
        CheckUtil.require(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(address)
                && lon != 0 && lat != 0, "Must set name, address, available view point!");
        final boolean accurate = false;
        if (!accurate) {
            lon /= 10;
            lat /= 10;
            alt /= 10;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(address).append('_').append(lon).append('_').append(lat);
        if (alt != 0) {
            sb.append('_').append(alt);
        }
        String result = //MapEngine.getInstance().getSystem().getUserId() + "_" +
                NioMD5.getInstance().getMD5(sb.toString()).substring(0, 16);
        if (result.length() > 32) {
            result = result.substring(0, 32);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        boolean equal = super.equals(o);
        if (!equal) {
            if (o != null && o instanceof Poi) {
                Poi other = (Poi) o;
                if (name == null && other.navPosition == null) {
                    equal = true;
                } else if (name != null && name.equals(other.getName())) {
                    equal = true;
                } else {
                    return equal;
                }
                if (viewPosition == null && other.viewPosition == null) {
                    equal = true;
                } else if (viewPosition != null && viewPosition.equals(other.viewPosition)) {
                    equal = true;
                } else {
                    return equal;
                }

                if (navPosition == null && other.navPosition == null) {
                    equal = true;
                } else if (navPosition != null && navPosition.equals(other.navPosition)) {
                    equal = true;
                } else {
                    return equal;
                }

            }
        }
        return equal;
    }
}