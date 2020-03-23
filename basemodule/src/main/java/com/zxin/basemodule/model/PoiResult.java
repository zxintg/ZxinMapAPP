package com.zxin.basemodule.model;

import com.zxin.basemodule.common.IResultCode;
import com.zxin.basemodule.search.SearchRequest;
import java.util.List;

public class PoiResult<T extends IPoi> {
    public static final int SEARCH_RESPONSE_TYPE_NORMAL = 0;
    public static final int SEARCH_RESPONSE_TYPE_OTHER_CITY = 1;
    public static final int SEARCH_RESPONSE_TYPE_CITIES = 2;

    private List<T> data;
    private IResultCode code;
    private SearchRequest request;
    private int responseType;

    public PoiResult() {
    }

    public PoiResult(IResultCode code, List<T> data, SearchRequest request) {
        this.code = code;
        this.data = data;
        this.request = request;

        if (data != null && !data.isEmpty()) {
            T first = data.get(0);
            String keyWord = request.getKeyWord();
            String firstName = first.getName();
            if (firstName != null && keyWord != null && !firstName.contains(keyWord)) {

            }
        }
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public IResultCode getCode() {
        return code;
    }

    public void setCode(IResultCode code) {
        this.code = code;
    }

    public SearchRequest getRequest() {
        return this.request;
    }

    public void setResponseType(int responseType) {
        this.responseType = responseType;
    }

    public int getResponseType() {
        return responseType;
    }
}