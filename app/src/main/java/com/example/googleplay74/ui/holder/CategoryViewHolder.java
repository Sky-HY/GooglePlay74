package com.example.googleplay74.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cn.googleplay74.R;
import com.example.googleplay74.domain.CateGoryInfo;
import com.example.googleplay74.utils.LogUtil;
import com.example.googleplay74.utils.UIUtil;

/**
 * 分类页面ViewHolder
 */
public class CategoryViewHolder extends BaseHolder<CateGoryInfo> implements View.OnClickListener {
    private static final String TAG = "CategoryViewHolder";
    private ImageView mIv_icon1;
    private ImageView mIv_icon2;
    private ImageView mIv_icon3;
    private TextView mTv_name1;
    private TextView mTv_name2;
    private TextView mTv_name3;

    @Override
    public View initView() {
        View view = UIUtil.inflate(R.layout.list_item_category, null);
        LinearLayout ll_grid1 = view.findViewById(R.id.ll_grid1);
        LinearLayout ll_grid2 = view.findViewById(R.id.ll_grid2);
        LinearLayout ll_grid3 = view.findViewById(R.id.ll_grid3);

        mIv_icon1 = view.findViewById(R.id.iv_icon1);
        mIv_icon2 = view.findViewById(R.id.iv_icon2);
        mIv_icon3 = view.findViewById(R.id.iv_icon3);

        mTv_name1 = view.findViewById(R.id.tv_name1);
        mTv_name2 = view.findViewById(R.id.tv_name2);
        mTv_name3 = view.findViewById(R.id.tv_name3);

        ll_grid1.setOnClickListener(this);
        ll_grid2.setOnClickListener(this);
        ll_grid3.setOnClickListener(this);

        return view;
    }

    @Override
    public void refresh(CateGoryInfo data) {
        mTv_name1.setText(data.name1);
        mTv_name2.setText(data.name2);
        mTv_name3.setText(data.name3);
        LogUtil.i(TAG,data.name1+"---"+data.name2+"---"+data.name3);
        Glide.with(UIUtil.getContext()).load("http://127.0.0.1:8090/image?name="+data.url1).into(mIv_icon1);
        Glide.with(UIUtil.getContext()).load("http://127.0.0.1:8090/image?name="+data.url2).into(mIv_icon2);
        Glide.with(UIUtil.getContext()).load("http://127.0.0.1:8090/image?name="+data.url3).into(mIv_icon3);
    }

    @Override
    public void onClick(View v) {

    }
}
