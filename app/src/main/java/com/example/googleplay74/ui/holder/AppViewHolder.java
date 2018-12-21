package com.example.googleplay74.ui.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cn.googleplay74.R;
import com.example.googleplay74.domain.AppInfo;
import com.example.googleplay74.utils.UIUtil;

public class AppViewHolder extends BaseHolder<AppInfo> {
    private ImageView iv_icon, iv_download;
    private TextView tv_name, tv_size, tv_download, tv_desc;
    private RatingBar rb_star;
    @Override
    public View initView() {
        View view = UIUtil.inflate(R.layout.list_item_app, null);
        iv_icon = view.findViewById(R.id.iv_icon);
        tv_name = view.findViewById(R.id.tv_name);
        rb_star = view.findViewById(R.id.rb_star);
        tv_size = view.findViewById(R.id.tv_size);
        iv_download = view.findViewById(R.id.iv_download);
        tv_download = view.findViewById(R.id.tv_download);
        tv_desc = view.findViewById(R.id.tv_desc);
        return view;
    }

    @Override
    public void refresh(AppInfo data) {
        Glide.with(UIUtil.getContext()).load("http://127.0.0.1:8090/image?name=" + data.iconUrl).into(iv_icon);
        tv_name.setText(data.name);
        tv_size.setText(Formatter.formatFileSize(UIUtil.getContext(), data.size));
        rb_star.setRating(data.stars);
        tv_desc.setText(data.des);
    }
}
