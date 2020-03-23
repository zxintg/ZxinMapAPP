package com.zxin.basemodule.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

public abstract class ActivityViewModel extends BaseViewModel {
    public ActivityViewModel(@NonNull Application application) {
        super(application);
    }
}
