package com.example.googleplay74.ui.fragment;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.example.googleplay74.http.protocol.RecommendProtocol;
import com.example.googleplay74.ui.view.LoadingPager;
import com.example.googleplay74.ui.view.fly.ShakeListener;
import com.example.googleplay74.ui.view.fly.StellarMap;
import com.example.googleplay74.utils.LogUtil;
import com.example.googleplay74.utils.UIUtil;

import java.util.List;
import java.util.Random;

/**
 * 推荐fragment
 */
public class RecommendFragment extends BaseFragment {
    private static final String TAG = "RecommendFragment";
    private List<String> data;
    private StellarMap stellarMap;

    @Override
    public View onCreateSeccessView() {

        // 开源工具
        stellarMap = new StellarMap(UIUtil.getContext());
        // 设置适配器
        stellarMap.setAdapter(new Myadapter());
        int px = UIUtil.dip2px(10);
        // 设置内边距
        stellarMap.setInnerPadding(px, px, px, px);
        // 设置规则
        stellarMap.setRegularity(6, 9);
        // 设置0页默认被选中，有动画
        stellarMap.setGroup(0, true);

        ShakeListener listener = new ShakeListener(UIUtil.getContext());
        // 摇一摇切换下一页
        listener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                stellarMap.zoomIn();
            }
        });
        return stellarMap;
    }

    @Override
    public LoadingPager.ResultState onLoad() {
        RecommendProtocol protocol = new RecommendProtocol();
        data = protocol.getData(0);
        return check(data);
    }

    class Myadapter implements StellarMap.Adapter {

        // 获取总页数
        @Override
        public int getGroupCount() {
            return 2;
        }

        // 每页多少条数(最后一页显示所有剩下的数据)
        @Override
        public int getCount(int group) {
            if (group < getGroupCount() - 1) {
                return data.size() / getGroupCount();
            } else {
                return data.size() / getGroupCount() + data.size() % getGroupCount();
            }
        }

        @Override
        public View getView(int group, int position, View convertView) {
            TextView view = new TextView(UIUtil.getContext());
            // 获取实际数据
            position += group * getCount(group - 1);
            view.setText(data.get(position));

            Random random = new Random();
            // 设置随机字号
            int size = random.nextInt(20) + 15;
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);

            // 设置随机颜色,颜色不能太大或者太小,避免太亮或者太暗
            int r = 30 + random.nextInt(200);
            int g = 30 + random.nextInt(200);
            int b = 30 + random.nextInt(200);
            view.setTextColor(Color.rgb(r, g, b));


            LogUtil.i(TAG, "position:" + position + "     group:" + group);
            return view;
        }

        // 下一页
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            if (isZoomIn) {
                // 向下滑
                group++;
                if (group == getGroupCount()) {
                    group = 0;
                }
            } else {
                // 向上滑
                group--;
                if (group == -1) {
                    group = getGroupCount() - 1;
                }
            }
//            LogUtil.i(TAG, "group:" + group + "  isZommIn:" + isZoomIn);
            return group;
        }
    }
}
