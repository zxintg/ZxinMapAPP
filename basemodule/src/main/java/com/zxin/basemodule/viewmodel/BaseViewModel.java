package com.zxin.basemodule.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.zxin.basemodule.app.BaseScene;

/**
 * BaseViewModel is all view model parent.
 * Impl has three impl class.
 * 1. {@link SceneViewModel}: data life cycle is fragment.
 * 2. {@link ActivityViewModel}: data life cycle is activity.
 * 3. {@link AppViewModel}: data life cycle is application.
 *
 * Author: Alex.Liu
 */
public abstract class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }


    /**
     * Update the view model data can't be scene object.
     * scene object will listener view mode data changed and update UI.
     * @param caller
     */
    protected void checkCaller(Object caller) {
        if (caller instanceof BaseScene) {
            throw new IllegalArgumentException("ViewMode set method can't called by Scene");
        }
    }

    /**
     * Clear the view model data.
     */
    public void clear() {
        onCleared();
    }
}
