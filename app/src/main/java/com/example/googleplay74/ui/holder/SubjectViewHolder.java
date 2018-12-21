package com.example.googleplay74.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cn.googleplay74.R;
import com.example.googleplay74.domain.SubjectInfo;
import com.example.googleplay74.utils.UIUtil;

/**
 * 专题viewHolder
 */
public class SubjectViewHolder extends BaseHolder<SubjectInfo> {

    private ImageView iv_icon;
    private TextView tv_desc;

    @Override
    public View initView() {
        View view = UIUtil.inflate(R.layout.list_item_subject, null);
        iv_icon = view.findViewById(R.id.iv_icon);
        tv_desc = view.findViewById(R.id.tv_desc);
        return view;
    }

    @Override
    public void refresh(SubjectInfo data) {
        Glide.with(UIUtil.getContext()).load("http://127.0.0.1:8090/image?name=" + data.url).into(iv_icon);
        tv_desc.setText(data.des);
    }
}
