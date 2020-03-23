package com.zxin.basemodule.guide;

import com.zxin.basemodule.common.IResultCode;
import com.zxin.basemodule.model.ViaPoi;
import com.zxin.basemodule.route.IRoute;
import com.zxin.basemodule.route.RouteInfo;

import java.util.List;

public interface IGuide {

    enum GuideState {
        GUIDING, CRUISING
    }

    enum RefreshRouteState {
        /**
         * No new route.
         */
        NO_ROUTE,

        /**
         * Find new route and auto switch to new route.
         */
        NEW_ROUTE,

        /**
         * Find new route but need user to decide switch.
         */
        USER_SWITCH,
        /**
         * Find new route.
         */
        OTHER_ROUTE,

        ERROR
    }

    enum RefreshRouteRequestCode {
        /**
         * Refresh button.
         */
        CLICK_REFRESH_BUTTON,

        /**
         * Overview button.
         */
        CLICK_OVERVIEW_BUTTON,

        /**
         * Auto refresh.
         */
        AUTO_SDK_ROUTE_REFRESH,
    }

    enum RouteChangeType {
        HIDE_CHANGE_ROUTE_MAIN_SLAVE(-2),
        HIDE_CHANGE_ROUTE_ON_UNDER(-1),
        HIDE_CHANGE_ROUTE(0),
        CHANGE_TO_MAIN_ROUTE(1),
        CHANGE_TO_SLAVE_ROUTE(2),
        CHANGE_TO_PARALLEL_ROUTE_ON(4),
        CHANGE_TO_PARALLEL_ROUTE_UNDER(8);

        private final int value;
        RouteChangeType(int i) {
            this.value = i;
        }
        public int getValue() {
            return value;
        }
    }

    enum RoadConditionType {
        RC_TYPE_INVAILD,
        RC_TYPE_STRAIGHT_WAY,
        RC_TYPE_SLIGHT_CONGESTION,
        RC_TYPE_CONGESTION,
        RC_TYPE_HEAVY_CONGESTION;
        public static RoadConditionType getRoadConditionType(int id) {
            return RoadConditionType.values()[id];
        }
    }

    enum CameraType {
        CAMERA_TYPE_NONE,
        CAMERA_TYPE_SPEED_LIMITED,
        CAMERA_TYPE_TRAFFIC_LIGHT,
        CAMERA_TYPE_PECCANRY,
        CAMERA_TYPE_PRESS_PHOTO,
        CAMERA_TYPE_INTERVAL_IN,
        CAMERA_TYPE_NO_AUTO_LANE,
        CAMERA_TYPE_SECURITY_MONITORING,
        CAMERA_TYPE_BUS_LANE,
        CAMERA_TYPE_INTERVAL_OUT,
        CAMERA_TYPE_NO_PARKING,
        CAMERA_TYPE_ONE_WAY_ROAD,
        CAMERA_TYPE_LEFT_TURN_FOBIDDEN,
        CAMERA_TYPE_RIGHT_TURN_FOBIDDEN,
        CAMERA_TYPE_U_TURN_FOBIDDEN,
        CAMERA_TYPE_NO_ADMITTANCE,
        CAMERA_TYPE_VEHICLE_LIMITED,
        CAMERA_TYPE_EMERGENCY_LANE,
        CAMERA_TYPE_HOV_LANE,
        CAMERA_TYPE_NO_PASS_GREEN_LIGHT
    }

    interface ISpeechListener {
        /**
         * Speech content.
         * @param type TTS type.
         * @param preempt is or not preempt current play tts. if false, need add the play list.
         *
         * @param content content.
         */
        void onSpeechUpdate(int type, boolean preempt, String content);
    }

    /**
     * Listener basic guidance information.
     *
     * 1. turn by turn info
     * 2. highway info
     * 3. road name
     * 4. road type
     * 5. current address
     * 6. lane info.
     * 7. remain info.
     * 8. road speed limit info.
     * 9. map match info.
     * 10. way point remain info.
     * 11.
     */
    interface IBasicGuideInfoListener {
        /**
         * tunnel info update.
         * @param info
         */
        void onTunnelInfoUpdate(TunnelInfo info);

        void onTurnInfoUpdate(List<TurnInfo> info);

        void onHighwayInfoUpdate(HighwayInfo info);

        void onLaneInfoUpdate(LaneInfo info);

        void onRemainInfoUpdate(int remainDist, int remainTime, float carProgress);

        void onWayPointsRemainInfoUpdate(int[] remainDist, int[] remainTime);

        void onMapMatchingInfoUpdate(MapMatchInfo info);

        void onIntervalSpeedLimitInfoUpdate(IntervalSpeedLimitInfo intervalSpeedLimitInfo);
    }

    interface IRoadImageViewListener {
        void onImageViewUpdate(ImageViewInfo info);
    }

    interface IAssistGuideInfoListener {
        void onCameraInfoUpdate(CameraInfo info);

        void onSafetyInfoUpdate(SafetyInfo info);
    }

    interface IGuideStateListener {
        void onGuideStateUpdate(GuideState state);

        void onViaArrived(ViaPoi poi);

        void onDestinationArrived();

        void onDestParkAvailableUpdate();

        void onAvoidRouteMsgUpdate();

        void onRoadConditionUpdated();

        void onChangeRouteUpdated(int status);

        void onChangeRouteResultUpdate(int var1);

        void onRouteReGenerated(IRoute.RouteState state);

        void onRefreshRouteUpdate(RefreshRouteState state, int routeId, RefreshRouteRequestCode trigger);

        void onTimeRestrictedRoadEntered();

        void onSwitchToOnlineRouteUpdate(boolean success);

        void onSwitchRouteUpdated(boolean success);
    }

    void addGuideStateListener(IGuideStateListener listener);

    void removeGuideStateListener(IGuideStateListener listener);

    void addSpeechListener(ISpeechListener listener);

    void removeSpeechListener(ISpeechListener listener);

    void addBasicGuideInfoListener(IBasicGuideInfoListener listener);

    void removeBasicGuideInfoListener(IBasicGuideInfoListener listener);

    void addRoadImageViewListener(IRoadImageViewListener listener);

    void removeRoadImageViewListener(IRoadImageViewListener listener);

    void addAssistGuideInfoListener(IAssistGuideInfoListener listener);

    void removeAssistGuideInfoListener(IAssistGuideInfoListener listener);

    IResultCode startGuidance(RouteInfo info, boolean simulation);

    IResultCode stopGuidance();

    boolean isGuiding();

    void setSimulationPause(boolean pause);

    void setSimulationSpeed(int speed);

    boolean refreshRoute();

    List<RoadCondition> getRoadCondition();

    float getCarProgress();

    IResultCode enterOverviewMode(DisplayMargin displayMargin);

    IResultCode exitOverviewMode();

    IResultCode switchMainAuxiliaryRoad(int type);

    int getSelectRouteIdx();

    void switchRoute(int routeId, boolean user);
}
