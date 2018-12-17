package com.example.googleplay74.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.cn.googleplay74.R;
import com.example.googleplay74.utils.UIUtils;

/**
 * home页面的ViewHolder
 */
public class HomeViewHolder extends BaseHolder<String> {

    public TextView tv_content;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_home, null);
        tv_content = view.findViewById(R.id.tv_content);
        return view;
    }

    @Override
    public void refresh(String data) {
        tv_content.setText(data);
    }
}
