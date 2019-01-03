package com.example.googleplay74.ui.fragment;

import android.view.View;

import com.example.googleplay74.domain.AppInfo;
import com.example.googleplay74.http.protocol.HomeProtocol;
import com.example.googleplay74.ui.adapter.MyBaseAdapter;
import com.example.googleplay74.ui.holder.BaseHolder;
import com.example.googleplay74.ui.holder.HomeViewHolder;
import com.example.googleplay74.ui.view.LoadingPager;
import com.example.googleplay74.ui.view.NoDividerListView;
import com.example.googleplay74.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页fragment
 */
public class HomeFragment extends BaseFragment {

    private NoDividerListView lv;
    private ArrayList<AppInfo> data;
    private HomeProtocol protocol;

    @Override
    public View onCreateSeccessView() {
        lv = new NoDividerListView(UIUtil.getContext());

        lv.setAdapter(new HomeAdapter(data));
        return lv;
    }

    // 子线程
    @Override
    public LoadingPager.ResultState onLoad() {
        protocol = new HomeProtocol();
        data = protocol.getData(0);
        // 校验数据
        return check(data);
    }


    // listView适配器子类
    private class HomeAdapter extends MyBaseAdapter<AppInfo> {

        public HomeAdapter(List<AppInfo> data) {
            super(data);
        }

        // 返回对应的ViewHolder
        @Override
        public BaseHolder<AppInfo> instanceHolder(int position) {
            return new HomeViewHolder();
        }

        // 加载更多
        @Override
        public List<AppInfo> onLoadMore() {
            // 当前集合的下标作为网络请求数据的索引
            return protocol.getData(getDataSize());
        }

    }
}
