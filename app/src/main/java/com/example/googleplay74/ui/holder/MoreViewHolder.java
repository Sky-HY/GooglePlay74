package com.example.googleplay74.ui.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.googleplay74.R;
import com.example.googleplay74.utils.UIUtils;

/**
 * 加载更多的ViewHolder
 */
public class MoreViewHolder extends BaseHolder<Integer> {

    public static final int STATE_MORE_MORE = 100;
    public static final int STATE_MORE_ERROR = 101;
    public static final int STATE_MORE_EMPTY = 102;

    public LinearLayout ll_more;
    private TextView tv_more_error;

    public MoreViewHolder(boolean hasMore) {
        // 设置初始样式
        setData(hasMore ? STATE_MORE_MORE : STATE_MORE_EMPTY);
    }

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_itme_more, null);
        ll_more = view.findViewById(R.id.ll_more);
        tv_more_error = view.findViewById(R.id.tv_more_error);
        return view;
    }

    @Override
    public void refresh(Integer data) {
        switch (data) {
            case STATE_MORE_MORE:
                // 加载更多样式
                ll_more.setVisibility(View.VISIBLE);
                tv_more_error.setVisibility(View.GONE);
                break;
            case STATE_MORE_ERROR:
                // 加载出错样式
                ll_more.setVisibility(View.GONE);
                tv_more_error.setVisibility(View.VISIBLE);
                break;
            case STATE_MORE_EMPTY:
                // 没有更多数据的样式
                ll_more.setVisibility(View.GONE);
                tv_more_error.setVisibility(View.GONE);
                break;
        }
    }
}
