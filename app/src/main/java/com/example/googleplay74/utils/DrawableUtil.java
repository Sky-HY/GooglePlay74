package com.example.googleplay74.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * 选择器工具类
 */
public class DrawableUtil {

    // 创建一个形状
    public static GradientDrawable getGradientDrawable(int color, int radius) {
        GradientDrawable shape = new GradientDrawable();
        // 画矩形
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(color);
        shape.setCornerRadius(radius);
        return shape;
    }

    // 状态选择器
    public static StateListDrawable getSelector(Drawable normal, Drawable press) {
        StateListDrawable selector = new StateListDrawable();
        selector.addState(new int[]{android.R.attr.state_pressed}, press);
        selector.addState(new int[]{}, normal);
        return selector;
    }

    // 状态选择器
    public static StateListDrawable getSelector(int press, int normal, int radius) {
        GradientDrawable pressDrawable = getGradientDrawable(press, radius);
        GradientDrawable normalDrawable = getGradientDrawable(normal, radius);
        return getSelector(pressDrawable, normalDrawable);
    }


}
