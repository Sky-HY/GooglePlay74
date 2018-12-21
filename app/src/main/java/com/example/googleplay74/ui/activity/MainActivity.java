package com.example.googleplay74.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.cn.googleplay74.R;
import com.example.googleplay74.ui.fragment.FragmentFactory;
import com.example.googleplay74.ui.view.PagerTab;
import com.example.googleplay74.utils.UIUtil;

public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private PagerTab mPagerTab;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用material Design中的toolbar
        Toolbar tool_bar = findViewById(R.id.tool_bar);
        tool_bar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(tool_bar);

        mViewPager = findViewById(R.id.viewPager);
        mPagerTab = findViewById(R.id.pagerTab);
        // 适配器
        myAdapter = new MyAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myAdapter);
        // 给tab设置ViewPager
        mPagerTab.setViewPager(mViewPager);

        mPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                FragmentFactory.createFragment(position).loadData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //ViewPager添加fragment的ViewPager
    class MyAdapter extends FragmentPagerAdapter {

        private String[] tabStr;

        public MyAdapter(FragmentManager fm) {
            super(fm);
            // 获取ViewPager的页签标题
            tabStr = UIUtil.getStringArray(R.array.tab_names);
        }

        // 设置tabPager标题内容
        @Override
        public CharSequence getPageTitle(int position) {
            return tabStr[position];
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return tabStr.length;
        }
    }
}
