package com.example.googleplay74.ui.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.googleplay74.ui.view.LoadingPager;
import com.example.googleplay74.utils.UIUtil;

/**
 * 游戏fragment
 */
public class GameFragment extends BaseFragment {
    @Override
    public View onCreateSeccessView() {
        TextView textView = new TextView(UIUtil.getContext());
        textView.setText("game");
        textView.setTextColor(Color.RED);
        textView.setTextSize(20);
        return textView;
    }

    @Override
    public LoadingPager.ResultState onLoad() {
        return LoadingPager.ResultState.STATE_SUCCESS;
    }
}
