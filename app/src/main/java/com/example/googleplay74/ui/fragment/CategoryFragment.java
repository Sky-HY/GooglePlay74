package com.example.googleplay74.ui.fragment;

import android.view.View;

import com.example.googleplay74.domain.CateGoryInfo;
import com.example.googleplay74.http.protocol.CateGoryProtocol;
import com.example.googleplay74.ui.adapter.MyBaseAdapter;
import com.example.googleplay74.ui.holder.BaseHolder;
import com.example.googleplay74.ui.holder.CategoryViewHolder;
import com.example.googleplay74.ui.holder.TitleViewHolder;
import com.example.googleplay74.ui.view.LoadingPager;
import com.example.googleplay74.ui.view.NoDividerListView;
import com.example.googleplay74.utils.UIUtil;

import java.util.List;

/**
 * 分类fragment
 */
public class CategoryFragment extends BaseFragment {
    private static final String TAG = "CategoryFragment";
    private List<CateGoryInfo> mData;

    @Override
    public View onCreateSeccessView() {
        NoDividerListView listView = new NoDividerListView(UIUtil.getContext());
        listView.setAdapter(new CategoryAdapter(mData));
        return listView;
    }

    @Override
    public LoadingPager.ResultState onLoad() {
        CateGoryProtocol protocol = new CateGoryProtocol();
        mData = protocol.getData(0);

        return check(mData);
    }

    // ListView adapter
    class CategoryAdapter extends MyBaseAdapter<CateGoryInfo> {

        public CategoryAdapter(List<CateGoryInfo> data) {
            super(data);
        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount() + 1;// 获取条目的总数
        }

        @Override
        public int getInnerViewType(int position) {
            CateGoryInfo info = mData.get(position);
            if (info.isTitle) {
                return super.getInnerViewType(position) + 1;// 标题条目
            } else {
                return super.getInnerViewType(position);// 普通条目
            }
        }

        @Override
        public BaseHolder<CateGoryInfo> instanceHolder(int position) {
            CateGoryInfo info = mData.get(position);
            if (info.isTitle){
                return new TitleViewHolder();
            }else{
                return new CategoryViewHolder();
            }
        }

        @Override
        public boolean hasMore() {
            return false; // 没有加载跟多(没有分页加载)
        }

        @Override
        public List<CateGoryInfo> onLoadMore() {
            return null;// 没有分页加载，此方法不重写
        }
    }
}
