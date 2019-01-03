package com.example.googleplay74.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.cn.googleplay74.R;
import com.example.googleplay74.domain.CateGoryInfo;
import com.example.googleplay74.utils.UIUtil;

/**
 * listView title的holder
 */
public class TitleViewHolder extends BaseHolder<CateGoryInfo> {

    private TextView mTv_title;

    @Override
    public View initView() {
        View view = UIUtil.inflate(R.layout.list_item_title, null);
        mTv_title = view.findViewById(R.id.tv_title);
        return view;
    }

    @Override
    public void refresh(CateGoryInfo data) {
        mTv_title.setText(data.title);
    }
}
