package com.huangyuanlove.sunflower_java;

import android.app.Application;


public class SunflowerApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getCrashHander().init(this);
    }
}
