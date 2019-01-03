package com.example.googleplay74.ui.fragment;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.googleplay74.http.protocol.HotProtocol;
import com.example.googleplay74.ui.view.FlowLayout;
import com.example.googleplay74.ui.view.LoadingPager;
import com.example.googleplay74.utils.DrawableUtil;
import com.example.googleplay74.utils.UIUtil;

import java.util.List;
import java.util.Random;

/**
 * 排行fragment
 */
public class HotFragment extends BaseFragment {

    private List<String> data;

    @Override
    public View onCreateSeccessView() {
        ScrollView scrollView = new ScrollView(UIUtil.getContext());
        FlowLayout flowLayout = new FlowLayout(UIUtil.getContext());
        int padding = UIUtil.dip2px(10);
        // 设置内边距
        flowLayout.setPadding(padding, padding, padding, padding);
        // 设置水平间距
        flowLayout.setHorizontalSpacing(UIUtil.dip2px(6));
        // 设置垂直间距
        flowLayout.setVerticalSpacing(UIUtil.dip2px(8));

        TextView view;
        for (int i = 0; i < data.size(); i++) {
            view = new TextView(UIUtil.getContext());
            view.setText(data.get(i));
            view.setGravity(Gravity.CENTER);
            view.setPadding(padding, padding, padding, padding);
            view.setTextColor(Color.WHITE);
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

            // 设置随机颜色,颜色不能太大或者太小,避免太亮或者太暗
            Random random = new Random();
            int r = 30 + random.nextInt(200);
            int g = 30 + random.nextInt(200);
            int b = 30 + random.nextInt(200);
            // 设置背景图片选择器
            view.setBackgroundDrawable(DrawableUtil.getSelector(Color.rgb(r, g, b), 0xffcecece, UIUtil.dip2px(6)));

            // 设置点击事件，才会相应选择器
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            flowLayout.addView(view);
        }
        scrollView.addView(flowLayout);
        return scrollView;
    }

    @Override
    public LoadingPager.ResultState onLoad() {
        HotProtocol protocol = new HotProtocol();
        data = protocol.getData(0);
        return check(data);
    }
}
