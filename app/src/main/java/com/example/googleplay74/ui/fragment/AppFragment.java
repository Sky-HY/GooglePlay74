package com.example.googleplay74.ui.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.googleplay74.ui.view.LoadingPager;
import com.example.googleplay74.utils.UIUtils;

/**
 * 应用fragment
 */
public class AppFragment extends BaseFragment {
    @Override
    public View onCreateSeccessView() {
        TextView view = new TextView(UIUtils.getContext());
        view.setText(getClass().getSimpleName());
        view.setTextColor(Color.RED);
        return view;
    }

    @Override
    public LoadingPager.ResultState onLoad() {
        return LoadingPager.ResultState.STATE_SUCCESS;
    }
}
