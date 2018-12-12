package com.example.googleplay74.ui.fragment;

import android.view.View;

import com.example.googleplay74.ui.view.LoadingPager;

/**
 *首页fragment
 */
public class HomeFragment extends BaseFragment {
    @Override
    public View onCreateSeccessView() {
        return null;
    }

    @Override
    public LoadingPager.ResultState onLoad() {
        return LoadingPager.ResultState.STATE_ERROR;
    }
}
