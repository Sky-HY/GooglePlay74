package com.example.googleplay74.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.googleplay74.utils.UIUtils;

/**
 * fragment基类
 */
public class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView view = new TextView(UIUtils.getContext());
        view.setText(getClass().getSimpleName());
        view.setTextColor(Color.RED);
        return view;
    }
}
