package com.example.googleplay74.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.cn.googleplay74.R;
import com.example.googleplay74.utils.LogUtil;

/**
 * 根据内部控件的宽高比来设置自己的高度
 */

public class RatioLayout extends FrameLayout {
    private static final String TAG = "RatioLayout";
    private float ratio;

    public RatioLayout(@NonNull Context context) {
        super(context);
    }

    public RatioLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 方式一获取属性
        // attrs.getAttributeFloatValue()
        // 方式二获取属性

        // 获取RatioLayout的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioLayout);
        // 获取 ratio属性
        ratio = typedArray.getFloat(R.styleable.RatioLayout_ratio, -1);
        typedArray.recycle();
    }

    public RatioLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // 控件的宽和模式
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        // 控件的高和模式
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        LogUtil.i(TAG, "width:" + width + "   height:" + height);
        LogUtil.i(TAG, "widthMode:" + widthMode + "  heightMode:" + heightMode);

        // MeasureSpec.AT_MOST; 至多模式, 控件有多大显示多大, wrap_content
        // MeasureSpec.EXACTLY; 确定模式, 类似宽高写死成dip, match_parent
        // MeasureSpec.UNSPECIFIED; 未指定模式.

        // 根据图片的比例去设置控件的高度
        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY && ratio > 0) {
            // 图片的宽
            int imageWidth = width - getPaddingLeft() - getPaddingRight();
            // 图片的高
            int imageHeight = (int) (imageWidth/ratio + 0.5);
            // 控件的高
            height = imageHeight + getPaddingBottom() + getPaddingTop();

            // 重新根据模式来生成控件的高
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
