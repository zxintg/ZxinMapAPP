package com.zxin.basemodule.util;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class EventUtil {
    public static boolean dispatchClickEvent(MotionEvent event, View view, Point point) {
        CheckUtil.require(view.getX() == 0 && view.getY() == 0,
                "Only can dispatch special view");

        int x = view.getWidth() >> 1;
        int y = view.getHeight();
        point.x = Math.round(event.getX() - Math.abs(point.x - x));
        point.y = Math.round(event.getY() - Math.abs(point.y - y));
        return dispatchClickEventInternal(view, point);
    }

    private static boolean dispatchClickEventInternal(View view, Point point) {
        if (view instanceof ViewGroup) {
            ViewGroup temp = (ViewGroup) view;
            for (int i = 0; i < temp.getChildCount(); ++i) {
                View child = temp.getChildAt(i);
                if(dispatchClickEventInternal(child, point)){
                    return true;
                }
            }
            if (view.hasOnClickListeners() && isClickOnView(view, point)) {
                view.performClick();
                return true;
            }
            return false;
        } else {
            if (view.hasOnClickListeners() && isClickOnView(view, point)) {
                view.performClick();
                return true;
            }
            return false;
        }
    }

    private static final Rect rect = new Rect();
    private static boolean isClickOnView(View view, Point point) {
        view.getGlobalVisibleRect(rect);
        return rect.contains(point.x, point.y);
    }
}
