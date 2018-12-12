package com.example.googleplay74.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.googleplay74.global.GooglePlayApplication;

/**
 * 常用工具类
 */
public class UIUtils {

    /////////////////////////////获取全局信息////////////////////////////////////////////////////

    public static Context getContext() {
        return GooglePlayApplication.getContext();
    }

    public static Handler getHandler() {
        return GooglePlayApplication.getHandler();
    }

    // 获取主线程id
    public static int getMainThreadId() {
        return GooglePlayApplication.getMainThreadId();
    }


    /////////////////////////////获取资源文件////////////////////////////////////////////////////

    // 获取String下的字符串
    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }

    // 获取String数组
    public static String[] getStringArray(int id) {
        return getContext().getResources().getStringArray(id);
    }

    // 获取图片
    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }

    // 获取颜色
    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    // 获取颜色的状态选择器
    public static ColorStateList getColorStateList(int id) {
        return getContext().getResources().getColorStateList(id);
    }

    // 获取尺寸
    public static int getDimen(int id) {
        return getContext().getResources().getDimensionPixelSize(id);
    }


    /////////////////////////////dp与px的相互转换////////////////////////////////////////////////////

    // dp转换px
    public static int dip2px(float dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5f);
    }

    // px转换dp
    public static float px2dip(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }

    ///////////////////加载布局文件//////////////////////////
    public static View inflate(int id, ViewGroup parent) {
        return LayoutInflater.from(getContext()).inflate(id, parent, false);
    }

    ///////////////////判断是否在主线程//////////////////////////
    public static boolean isRunOnUIThrean() {
        int currentThreadId = Process.myTid();
        if (getMainThreadId() == currentThreadId) {
            return true;
        }
        return false;
    }

    ///////////////////运行在主线程//////////////////////////
    public static void runOnUIThread(Runnable r) {
        if (isRunOnUIThrean()) {
            // 已经是在主线程了
            r.run();
        } else {
            // 切换到主线程。原理是因为looper在主线程
            getHandler().post(r);
        }
    }

}
