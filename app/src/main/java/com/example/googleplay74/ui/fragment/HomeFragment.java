package com.example.googleplay74.ui.fragment;

import android.view.View;
import android.widget.ListView;

import com.example.googleplay74.ui.adapter.MyBaseAdapter;
import com.example.googleplay74.ui.holder.BaseHolder;
import com.example.googleplay74.ui.holder.HomeViewHolder;
import com.example.googleplay74.ui.view.LoadingPager;
import com.example.googleplay74.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页fragment
 */
public class HomeFragment extends BaseFragment {

    private List<String> data;
    private ListView lv;

    @Override
    public View onCreateSeccessView() {
        lv = new ListView(UIUtils.getContext());
        lv.setAdapter(new HomeAdapter(data));
        return lv;
    }

    @Override
    public LoadingPager.ResultState onLoad() {
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("我是数据:" + i);
        }

        return LoadingPager.ResultState.STATE_SUCCESS;
    }

    private class HomeAdapter extends MyBaseAdapter<String> {


        public HomeAdapter(List<String> data) {
            super(data);
        }

        @Override
        public BaseHolder<String> instanceHolder() {
            return new HomeViewHolder();
        }

        @Override
        public List<String> onLoadMore() {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 19; i++) {
                list.add("我是加载更多的数据:" + i);
            }
            return list;
        }

    }
}
