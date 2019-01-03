package com.example.googleplay74.ui.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.cn.googleplay74.R;
import com.example.googleplay74.utils.UIUtil;

import java.util.List;

/**
 * 首页listView头部
 */
public class HomeHeaderHolder extends BaseHolder<List<String>> implements ViewPager.OnPageChangeListener {

    private List<String> data;
    private ViewPager mViewPager;
    private RelativeLayout mRoot_layout;
    private LinearLayout mLinearLayout;
    private int mLastImageId = 0;

    @Override
    public View initView() {
        // 根布局
        mRoot_layout = new RelativeLayout(UIUtil.getContext());
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, UIUtil.dip2px(150));
        mRoot_layout.setLayoutParams(params);
        // viewPager
        mViewPager = new ViewPager(UIUtil.getContext());
        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mRoot_layout.addView(mViewPager, relativeLayoutParams);
        // 装小点的容器
        mLinearLayout = new LinearLayout(UIUtil.getContext());
        int px = UIUtil.dip2px(10);
        mLinearLayout.setPadding(px, px, px, px);
        relativeLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mRoot_layout.addView(mLinearLayout, relativeLayoutParams);

        return mRoot_layout;
    }

    @Override
    public void refresh(List<String> data) {
        this.data = data;
        mViewPager.setAdapter(new Adapter());
        // 从10000万开始
        mViewPager.setCurrentItem(data.size() * 100000);
        // 设置滚动监听
        mViewPager.addOnPageChangeListener(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < data.size(); i++) {
            ImageView imageView = new ImageView(UIUtil.getContext());

            if (i == 0) {
                imageView.setImageResource(R.drawable.indicator_selected);
            } else {
                params.leftMargin = UIUtil.dip2px(2);
                imageView.setImageResource(R.drawable.indicator_normal);
            }
            mLinearLayout.addView(imageView, params);
        }

        HomeHeaderTask task = new HomeHeaderTask();
        task.start();
    }


    class HomeHeaderTask implements Runnable {

        public void start() {
            // 移除之前发送的所有消息
            UIUtil.getHandler().removeCallbacksAndMessages(null);
            UIUtil.getHandler().postDelayed(this, 2000);
        }

        @Override
        public void run() {
            int currentItem = mViewPager.getCurrentItem();
            currentItem++;
            mViewPager.setCurrentItem(currentItem);
            UIUtil.getHandler().postDelayed(this, 2000);
        }
    }

    //ViewPager Adapter
    class Adapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int pos = position % data.size();
            String url = data.get(pos);
            ImageView view = new ImageView(UIUtil.getContext());
            Glide.with(UIUtil.getContext()).load("http://127.0.0.1:8090/image?name=" + url).into(view);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int pos = position % data.size();
        ImageView currentImageView = (ImageView) mLinearLayout.getChildAt(pos);
        currentImageView.setImageResource(R.drawable.indicator_selected);
        ImageView lastImageView = (ImageView) mLinearLayout.getChildAt(mLastImageId);
        lastImageView.setImageResource(R.drawable.indicator_normal);
        mLastImageId = pos;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
