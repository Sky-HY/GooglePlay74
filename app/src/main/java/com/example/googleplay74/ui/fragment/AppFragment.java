package com.example.googleplay74.ui.fragment;

import android.view.View;

import com.example.googleplay74.domain.AppInfo;
import com.example.googleplay74.http.protocol.AppProtocol;
import com.example.googleplay74.ui.adapter.MyBaseAdapter;
import com.example.googleplay74.ui.holder.AppViewHolder;
import com.example.googleplay74.ui.holder.BaseHolder;
import com.example.googleplay74.ui.view.LoadingPager;
import com.example.googleplay74.ui.view.NoDividerListView;
import com.example.googleplay74.utils.UIUtil;

import java.util.List;

/**
 * 应用fragment
 */
public class AppFragment extends BaseFragment {
    private List<AppInfo> data;
    private NoDividerListView lv;

    @Override
    public View onCreateSeccessView() {
        lv = new NoDividerListView(UIUtil.getContext());
        lv.setAdapter(new AppAdapter(data));
        return lv;
    }

    @Override
    public LoadingPager.ResultState onLoad() {

        AppProtocol appProtocol = new AppProtocol();
        data = appProtocol.getData(0);

        return check(data);
    }

    class AppAdapter extends MyBaseAdapter<AppInfo> {

        public AppAdapter(List<AppInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<AppInfo> instanceHolder() {
            return new AppViewHolder();
        }

        @Override
        public List<AppInfo> onLoadMore() {
            AppProtocol appProtocol = new AppProtocol();
            data = appProtocol.getData(getDataSize());
            return data;
        }
    }
}
