package com.example.googleplay74.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 自定义没有分割线的ListView
 */
public class NoDividerListView extends ListView {
    public NoDividerListView(Context context) {
        super(context);
        initView();
    }

    public NoDividerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public NoDividerListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setSelector(new ColorDrawable());// 设置默认状态选择器为全透明
        setDivider(null);// 去掉分隔线
        setCacheColorHint(Color.TRANSPARENT);
    }

}
