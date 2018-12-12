package com.example.googleplay74.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.googleplay74.ui.view.LoadingPager;
import com.example.googleplay74.utils.UIUtils;

/**
 * fragment基类
 */
public abstract class BaseFragment extends Fragment {

    private LoadingPager mLoadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 页面加载的布局,具体加载什么布局,由状态决定
        mLoadingPager = new LoadingPager(UIUtils.getContext()) {
            @Override
            public View onCreateSuccessView() {
                // 由baseFragment的子类实现
                return BaseFragment.this.onCreateSeccessView();
            }

            @Override
            public ResultState onLoad() {
                // 由baseFragment的子类实现
                return BaseFragment.this.onLoad();
            }
        };

        return mLoadingPager;
    }

    public abstract View onCreateSeccessView();

    public abstract LoadingPager.ResultState onLoad();

    /**
     * 加载数据
     */
    public void loadData() {
        mLoadingPager.loadData();
    }
}
