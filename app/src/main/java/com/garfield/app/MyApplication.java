package com.garfield.app;

import android.app.Application;

import com.garfield.viewmonitor.api.ExposureManager;

/**
 * Created by gaowei on 2017/12/7.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ExposureManager.getInstance().init(this);
    }
}
