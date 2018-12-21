package com.example.googleplay74.ui.fragment;

import android.view.View;

import com.example.googleplay74.domain.SubjectInfo;
import com.example.googleplay74.http.protocol.SubjectProtocol;
import com.example.googleplay74.ui.adapter.MyBaseAdapter;
import com.example.googleplay74.ui.holder.BaseHolder;
import com.example.googleplay74.ui.holder.SubjectViewHolder;
import com.example.googleplay74.ui.view.LoadingPager;
import com.example.googleplay74.ui.view.NoDividerListView;
import com.example.googleplay74.utils.UIUtil;

import java.util.List;

/**
 * 专题fragment
 */
public class SubjectFragment extends BaseFragment {
    private List<SubjectInfo> data;
    private NoDividerListView lv;

    @Override
    public View onCreateSeccessView() {
        lv = new NoDividerListView(UIUtil.getContext());
        lv.setAdapter(new SubjectAdapter(data));
        return lv;
    }

    @Override
    public LoadingPager.ResultState onLoad() {
        SubjectProtocol protocol = new SubjectProtocol();
        data = protocol.getData(0);
        return check(data);
    }

    class SubjectAdapter extends MyBaseAdapter<SubjectInfo> {

        public SubjectAdapter(List<SubjectInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<SubjectInfo> instanceHolder() {
            return new SubjectViewHolder();
        }

        @Override
        public List<SubjectInfo> onLoadMore() {
            SubjectProtocol protocol = new SubjectProtocol();
            return protocol.getData(getDataSize());
        }
    }
}
