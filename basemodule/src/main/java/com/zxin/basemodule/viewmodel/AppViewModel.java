package com.zxin.basemodule.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

public abstract class AppViewModel extends BaseViewModel {

    public AppViewModel(@NonNull Application application) {
        super(application);
    }
}
