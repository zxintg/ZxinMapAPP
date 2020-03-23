package com.zxin.basemodule.app;

import android.util.ArrayMap;

import androidx.annotation.NonNull;

public final class BaseIntent implements IIntent {

    private String mAction;
    private ArrayMap<String, Object> mMap = new ArrayMap<>();
    private IIntent mRequestIntent;

    public BaseIntent() {
        this(IIntent.ACTION_DEFAULT);
    }

    public BaseIntent(String action) {
        setAction(action);
    }

    @Override
    public String getAction() {
        return mAction;
    }

    @Override
    public void setAction(String action) {
        mAction = action;
    }

    @Override
    public void putString(String key, String value) {
        mMap.put(key, value);
    }

    @Override
    public String getString(String key, String defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        } else {
            return (String) value;
        }
    }

    @Override
    public void putInt(String key, int value) {
        mMap.put(key, value);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        } else {
            return (int) value;
        }
    }

    @Override
    public void putLong(String key, long value) {
        mMap.put(key, value);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        } else {
            return (long) value;
        }
    }

    @Override
    public void putFloat(String key, float value) {
        mMap.put(key, value);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        Object value = mMap.get(key);
        if (value == null) {
            return defaultValue;
        } else {
            return (float) value;
        }
    }

    @Override
    public void putData(String key, Object data) {

        mMap.put(key, data);
    }

    @Override
    public Object getData(String key) {
        return mMap.get(key);
    }

    @Override
    public boolean hasExtra(String key) {
        return mMap.containsKey(key);
    }

    @Override
    public void setRequestIntent(IIntent intent) {
        mRequestIntent = intent;
    }

    @Override
    public IIntent getRequestIntent() {
        return mRequestIntent;
    }

    @NonNull
    @Override
    public String toString() {
        return mAction + " -> " + super.toString();
    }
}
