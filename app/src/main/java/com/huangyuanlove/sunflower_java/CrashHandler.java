package com.huangyuanlove.sunflower_java;


import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static volatile CrashHandler crashHandler;

    private Context context;

    private CrashHandler(){}

    public void init(Context context){
        this.context = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static CrashHandler getCrashHander(){
        if (crashHandler == null){
            synchronized (CrashHandler.class){
                if (crashHandler == null){
                    crashHandler = new CrashHandler();
                }
            }
        }
        return crashHandler;
    }

    @Override
    public void uncaughtException(Thread t, final Throwable e) {

        context.startActivity(new Intent(context,CrashActivity.class));


        WriteLogToFile.writePushMessageToFile(t.getName());
        WriteLogToFile.writePushMessageToFile(e.getMessage());

    }
}
