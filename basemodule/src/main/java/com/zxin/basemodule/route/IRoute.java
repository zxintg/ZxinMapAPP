package com.zxin.basemodule.route;

import com.zxin.basemodule.common.IResultCode;

public interface IRoute {

    enum RouteOption {
        INVALID(0),
        RECOMMEND(1),
        HIGHWAY_PRIORITY(2),
        AVOID_HIGHWAY(4),
        LESS_CHARGE(8),
        AVOID_TRAFFIC_JAM(16),
        TIME_FIRST(32);
        private int value;

        private RouteOption(int value) {
            this.value = value;
        }

        public int getValue(){
            return value;
        }

        public static RouteOption valueOf(int value) {    //    手写的从int到enum的转换函数
            switch (value) {
                case 1:
                    return RECOMMEND;
                case 2:
                    return HIGHWAY_PRIORITY;
                case 4:
                    return AVOID_HIGHWAY;
                case 8:
                    return LESS_CHARGE;
                case 16:
                    return AVOID_TRAFFIC_JAM;
                case 32:
                    return TIME_FIRST;
                default:
                    return INVALID;
            }
        }
    }

    enum NetMode {
        ONLINE, OFFLINE, FIRST_ONLINE, FIRST_OFFLINE, INVALID
    }

    enum RouteState {
        ROUTE_STATE_START,
        ROUTE_STATE_IN_PROGRESS,
        ROUTE_STATE_SUCCESS,
        ROUTE_STATE_ERROR,
    }

    interface IRouteListener {
        void onRouteStateChange(RouteState state, RouteResult result);

    }

    /**
     * Start calculate route.
     *
     * @param params     calculate route params.
     * @param navigating is or not in guiding.
     * @param listener   get result
     * @return error code
     */
    IResultCode startRoute(RouteParams params, boolean navigating, IRouteListener listener);


    RouteParams getLastSuccessRouteParams();

    void clearRouteSession();

    void cancelRoute();
}
