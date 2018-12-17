package com.example.googleplay74.ui.holder;

import android.view.View;

/**
 * ViewHolder的基类
 */
public abstract class BaseHolder<E> {

    private View mRootView;
    public E data;

    public BaseHolder() {
        mRootView = initView();
        mRootView.setTag(this);
    }

    // 子类实现具体UI
    public abstract View initView();

    // 设置数据
    public void setData(E data) {
        this.data = data;
        refresh(data);
    }

    public E getData() {
        return data;
    }

    // 刷新数据
    public abstract void refresh(E data);

    // 获取View
    public View getView() {
        return mRootView;
    }
}
