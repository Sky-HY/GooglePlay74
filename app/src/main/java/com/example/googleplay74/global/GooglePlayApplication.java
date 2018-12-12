package com.example.googleplay74.global;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

/**
 * 自定义全局Application
 */
public class GooglePlayApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static Handler handler;
    private static int myTid;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        myTid = Process.myTid();
    }

    /**
     * 获取上下文context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取handler
     */
    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程id
     */
    public static int getMainThreadId() {
        return myTid;
    }
}
