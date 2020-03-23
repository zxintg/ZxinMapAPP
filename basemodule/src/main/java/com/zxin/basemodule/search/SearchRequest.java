package com.zxin.basemodule.search;

import android.text.TextUtils;

import com.zxin.basemodule.model.LonLat;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Search request.
 */
public class SearchRequest {

    public final static int SEARCH_SOURCE_UNKNOWN = 0;
    public final static int SEARCH_SOURCE_GIS = 1;
    public final static int SEARCH_SOURCE_BAIDU = 2;

    String keyWord;
    LonLat position;

    /**
     * Data id,
     * use data id it can query a data detail information,
     * if set data id, other params will ignore, only use data id to query.
     */
    String dataId;

    /**
     * the type of search(use only when search and detail)
     */
    ISearch.SearchType searchType;
    /**
     * route list, for along route search
     */
    List<LonLat> routeList;

    /**
     * Search radius. unit is meter, default is 10km
     */
    float radius = 10000.0f;

    /**
     * classify search
     */
    String classify = "default";

    /**
     * Page size
     */
    int pageSize = 30;

    /**
     * page number, default is first page
     */
    int pageNumber = 0;

    /**
     * Country: Default China
     * cn: China
     * us: America
     */
    String country = "cn";


    /**
     * City
     */
    String city = "";

    /**
     * the search_along distance
     */
    long distance;

    // Add for jackson parser
    protected SearchRequest() {

    }
    /**
     * the cancel executor of okhttp search(used to cancel search)
     */
    Object executor;

    /**
     * network available condition
     */
    boolean isOnline = true;

    /**
     * the category of search engine
     */
    int searchSource = SEARCH_SOURCE_BAIDU;

    /**
     * the current car position
     */
    LonLat currentPosition;

    /**
     * the category of search resource(only use in keyword != null)
     */
    String resourceType;

    /**
     * could offline search or not
     */
    boolean couldOfflineSearch = true;

    /**
     * timeout future (only use in NioSearchImpl)
     */
    Future timeoutFuture;

    /**
     * the search request is could wait or not;
     */
    boolean isCouldWait;

    public SearchRequest(String keyWord) {
        this.keyWord = keyWord;
    }

    public SearchRequest(String keyWord, LonLat position) {
        this.keyWord = keyWord;
        this.position = position;
    }


    public SearchRequest(String keyWord, LonLat position, ISearch.SearchType searchType) {
        this.keyWord = keyWord;
        this.position = position;
        this.searchType = searchType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public LonLat getPosition() {
        return position;
    }

    public void setPosition(LonLat position) {
        this.position = position;
    }

    public void setSearchType(ISearch.SearchType searchType) {
        this.searchType = searchType;
    }

    public ISearch.SearchType getSearchType() {
        return searchType;
    }

    public List<LonLat> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<LonLat> routeList) {
        this.routeList = routeList;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public int getDistrictId() {
        return 289;
    }

    public String getAddress() {
        return "";
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setSearchSource(int searchSource) {
        this.searchSource = searchSource;
    }

    public int getSearchSource() {
        return searchSource;
    }

    public void setCurrentPosition(LonLat currentPosition) {
        this.currentPosition = currentPosition;
    }

    public LonLat getCurrentPosition() {
        return currentPosition;
    }

    public void setCancelExecutor(Object executor) {
        this.executor = executor;
    }

    public Object getCancelExecutor() {
        return executor;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setCouldOfflineSearch(boolean couldOfflineSearch) {
        this.couldOfflineSearch = couldOfflineSearch;
    }

    public boolean isCouldOfflineSearch() {
        return couldOfflineSearch;
    }

    public void setTimeoutFuture(Future timeoutFuture) {
        this.timeoutFuture = timeoutFuture;
    }

    public Future getTimeoutFuture() {
        return timeoutFuture;
    }

    public void setCouldWait(boolean couldWait) {
        isCouldWait = couldWait;
    }

    public boolean isCouldWait() {
        return isCouldWait;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SearchRequest{keyWord=")
                .append(keyWord);
        if (position != null) {
            sb.append(", position=").append(position);
        }

        sb.append(", searchType=").append(searchType);

        if (routeList != null) {
            sb.append(", routeList=").append(routeList);
        }

        if (radius > 0) {
            sb.append(", radius=").append(radius);
        }

        if (!TextUtils.isEmpty(classify)) {
            sb.append(", classify=").append(classify);
        }

        sb.append(", pageSize=").append(pageSize)
                .append(", pageNumber=").append(pageNumber);
        if (!TextUtils.isEmpty(country)) {
            sb.append(", country=").append(country);
        }

        if (!TextUtils.isEmpty(city)){
            sb.append(", city=").append(city);
        }

        if (distance > 0) {
            sb.append(", distance=").append(distance);
        }

        if (executor != null) {
            sb.append(", executor=").append(executor);
        }

        sb.append(", isOnline=").append(isOnline);
        sb.append(", searchSource=").append(searchSource);
        if (currentPosition != null) {
            sb.append(", currentPosition=").append(currentPosition);
        }

        if (!TextUtils.isEmpty(resourceType)) {
            sb.append(", resourceType=").append(resourceType);
        }

        sb.append(", couldOfflineSearch=").append(couldOfflineSearch);

        sb.append("}");
        return sb.toString();
    }
}