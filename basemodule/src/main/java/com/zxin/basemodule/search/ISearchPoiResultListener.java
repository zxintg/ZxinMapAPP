package com.zxin.basemodule.search;

import com.zxin.basemodule.common.IResultCode;
import com.zxin.basemodule.model.Poi;
import com.zxin.basemodule.model.PoiResult;

public interface ISearchPoiResultListener {
    void onSearchResult(PoiResult<Poi> result, IResultCode resultCode);
}