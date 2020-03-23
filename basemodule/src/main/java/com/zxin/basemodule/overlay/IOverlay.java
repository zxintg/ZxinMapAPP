package com.zxin.basemodule.overlay;

import com.zxin.basemodule.common.IResultCode;

public interface IOverlay extends IOverlayControl {
    IResultCode addMapItemGroup(MapItem group);
    IResultCode removeMapItemGroup(MapItem group);
    IResultCode updateMapItemGroup(MapItem group);
    IResultCode clearAllMapItemGroup();
}
