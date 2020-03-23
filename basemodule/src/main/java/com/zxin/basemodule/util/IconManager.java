package com.zxin.basemodule.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/** Manage all the poi icon
 *
 * Created by lili.yin on 2020/01/10.
 */

public class IconManager {
    private static IconManager mInstance = new IconManager();

    private static final String[] COMMON_POI_NORMAL = {
            "qita_normal_poi_not_choose.png",
            "nio_not_choose.png",
            "park_notchoose.png",
            "door_notchoose.png",
            "car_wash_notchoose.png",
    };
    private static final String[] COMMON_POI_SELECT= {
            "qita_normal_poi_choose.png",
            "nio_choose.png",
            "park_choose.png",
            "door_choose.png",
            "carwash_choose.png",
    };

    private static final String[] CHARGER_POI_NORMAL = {
            "power_swap_not_choose.png",
            "xingxing_notchoose.png",
            "tld_notchoose.png",
            "guo_dian_not_choose.png",
            "nanjingyunkuaichong_red_normal.png",
            "nio_not_choose.png",
            "qita_charging_poi_not_choose.png"
    };
    private static final String[] CHARGER_POI_SELECT= {
            "power_swap_choose.png",
            "xingxing_chongdianzhuang.png",
            "tld_chongdianzhuang.png",
            "guo_dian_choose.png",
            "nanjingyunkuaichong_red_big.png",
            "nio_choose.png",
            "qita_charging_poi_choose.png"
    };
    private static final String[] CHARGER_POI_BUSY_NORMAL = {
            "huandianzhan_not_choose.png",
            "xingxing_gray.png",
            "telaidian_gray.png",
            "guo_dian_busy_not_choose.png",
            "nanjingyunkuaichong_gray.png",
            "nio_gray.png",
            "qita_busy_not_choose.png"
    };
    private static final String[] CHARGER_POI_BUSY_SELECT= {
            "power_swap_busy_choose.png",
            "xingxing_chongdianzhuang_gray_big.png",
            "tld_chongdianzhuang_gray_big.png",
            "guo_dian_busy_choose.png",
            "nio_chongdianzhuang_gray_big.png",
            "nanjingyunkuaichong_gray_big.png",
            "qita_busy_choose.png"
    };
    private static final String[] CHARGER_POI_UNREACHABLE_NORMAL = {
            "ic_unreachable.png",
            "ic_unreachable.png",
            "ic_unreachable.png",
            "ic_unreachable.png",
            "ic_unreachable.png",
            "ic_unreachable.png",
            "ic_unreachable.png"
    };
    private static final String[] CHARGER_POI_UNREACHABLE_SELECT= {
            "power_swap_busy_choose.png",
            "xingxing_chongdianzhuang_gray_big.png",
            "tld_chongdianzhuang_gray_big.png",
            "guo_dian_busy_choose.png",
            "nanjingyunkuaichong_gray_big.png",
            "nio_chongdianzhuang_gray_big.png",
            "qita_busy_choose.png"
    };


    public static IconManager getInstance() {
        return mInstance;
    }

    public List<String> getNormalIcon() {
        List<String> result = new ArrayList<>();
        result.addAll(Arrays.asList(COMMON_POI_NORMAL));
        result.addAll(Arrays.asList(CHARGER_POI_NORMAL));
        return result;
    }

    public List<String> getSelectedIcon() {
        List<String> result = new ArrayList<>();
        result.addAll(Arrays.asList(COMMON_POI_SELECT));
        result.addAll(Arrays.asList(CHARGER_POI_SELECT));
        return result;
    }

    public List<String> getChargerNormalIcon() {
        List<String> result = new ArrayList<>();
        result.addAll(Arrays.asList(CHARGER_POI_NORMAL));
        result.addAll(Arrays.asList(CHARGER_POI_BUSY_NORMAL));
        result.addAll(Arrays.asList(CHARGER_POI_UNREACHABLE_NORMAL));
        return result;
    }

    public List<String> getChargerSelectedIcon() {
        List<String> result = new ArrayList<>();
        result.addAll(Arrays.asList(CHARGER_POI_SELECT));
        result.addAll(Arrays.asList(CHARGER_POI_BUSY_SELECT));
        result.addAll(Arrays.asList(CHARGER_POI_UNREACHABLE_SELECT));
        return result;
    }

}
