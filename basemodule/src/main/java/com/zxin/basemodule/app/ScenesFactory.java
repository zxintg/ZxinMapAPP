package com.zxin.basemodule.app;

import android.os.Bundle;

public class ScenesFactory {

    public static final <T extends BaseScene> T createScene(Class<T> clazz, Bundle bundle, BasePresenter presenter)  {
        try {
            T value = clazz.newInstance();
            value.mPresenter = presenter;
            value.setArguments(bundle);
            return value;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(clazz + " Can't  instance");
    }
}
