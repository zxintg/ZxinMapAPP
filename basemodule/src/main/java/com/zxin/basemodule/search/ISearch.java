package com.zxin.basemodule.search;

public interface ISearch {

    enum SearchType {
        SUGGEST,
        RGC,
        SEARCH_AROUND, SEARCH_ALONG, SEARCH_DISTRICT, SHOW_NEARBY_CHARGING,
        POWER_SWAP_DETAIL, CHARGING_PILE_DETAIL, COMMON_POI_DETAIL,
    }

    class SearchAction {
        public SearchRequest request;
        public ISearchPoiResultListener listener;

        public SearchAction(SearchRequest request, ISearchPoiResultListener listener) {
            this.request = request;
            this.listener = listener;
        }
    }

    /**
     * Suggest
     */
    void suggest(SearchRequest request, ISearchPoiResultListener listener);

    /**
     * Search
     */
    void search(SearchRequest request, ISearchPoiResultListener listener);

    /**
     * Rgc
     */
    void rgc(SearchRequest request, ISearchPoiResultListener listener);

    void cancel(SearchRequest request);

    void cancelAll();
}