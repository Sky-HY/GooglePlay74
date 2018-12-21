package com.example.googleplay74.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.cn.googleplay74.R;
import com.example.googleplay74.utils.UIUtil;

/**
 * 根据当前状态来加载不同的页面的布局
 */
public abstract class LoadingPager extends FrameLayout {

    private static final int STATE_LOAD_UNDO = 1;// 什么都没有做
    private static final int STATE_LOAD_LOADING = 2;// 数据加载中
    private static final int STATE_LOAD_ERROR = 3;// 数据加失败
    private static final int STATE_LOAD_EMPTY = 4;// 数据为空
    private static final int STATE_LOAD_SUCCESS = 5;// 数据加载成功

    private int currentState = STATE_LOAD_UNDO;// 当前状态
    private View mLoadingPager;
    private View mLoadErrorPager;
    private View mLoadEmptyPager;
    private View mLoadSuccessPager;

    public LoadingPager(@NonNull Context context) {
        super(context);
        initUI();
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }


    private void initUI() {
        // 加载中的布局
        if (mLoadingPager == null) {
            mLoadingPager = UIUtil.inflate(R.layout.pager_loading, null);
            addView(mLoadingPager);
        }

        // 加载失败的布局
        if (mLoadErrorPager == null) {
            mLoadErrorPager = UIUtil.inflate(R.layout.pager_load_error, null);
            addView(mLoadErrorPager);
        }
        // 重新加载数据
        mLoadErrorPager.findViewById(R.id.button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        // 加载的内容为空的布局
        if (mLoadEmptyPager == null) {
            mLoadEmptyPager = UIUtil.inflate(R.layout.pager_load_empty, null);
            addView(mLoadEmptyPager);
        }

        // 根据状态显示和隐藏布局
        showRightPager();
    }

    // 显示正确的布局
    private void showRightPager() {
        switch (currentState) {
            case STATE_LOAD_UNDO:
            case STATE_LOAD_LOADING:
                // 显示加载中布局
                mLoadingPager.setVisibility(View.VISIBLE);
                mLoadEmptyPager.setVisibility(View.GONE);
                mLoadErrorPager.setVisibility(View.GONE);
                if (mLoadSuccessPager != null) {
                    mLoadSuccessPager.setVisibility(View.GONE);
                }
                break;
            case STATE_LOAD_ERROR:
                // 显示异常布局
                mLoadErrorPager.setVisibility(View.VISIBLE);
                mLoadingPager.setVisibility(View.GONE);
                mLoadEmptyPager.setVisibility(View.GONE);
                if (mLoadSuccessPager != null) {
                    mLoadSuccessPager.setVisibility(View.GONE);
                }
                break;
            case STATE_LOAD_EMPTY:
                // 显示数据为空布局
                mLoadEmptyPager.setVisibility(View.VISIBLE);
                mLoadErrorPager.setVisibility(View.GONE);
                mLoadingPager.setVisibility(View.GONE);
                if (mLoadSuccessPager != null) {
                    mLoadSuccessPager.setVisibility(View.GONE);
                }
                break;
            case STATE_LOAD_SUCCESS:
                // 数据加载成果的布局
                if (mLoadSuccessPager == null) {
                    mLoadSuccessPager = onCreateSuccessView();
                    addView(mLoadSuccessPager);
                }
                mLoadSuccessPager.setVisibility(View.VISIBLE);
                mLoadErrorPager.setVisibility(View.GONE);
                mLoadingPager.setVisibility(View.GONE);
                mLoadEmptyPager.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    // 加载成功的布局,由子类实现
    public abstract View onCreateSuccessView();

    // 加载数据
    public void loadData() {

        new Thread() {
            @Override
            public void run() {
                // 子类实现
                final ResultState resultState = onLoad();
                // 工具类,切换到主线程
                UIUtil.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        if (resultState != null) {
                            // 跟新UI
                            currentState = resultState.state;
                            showRightPager();
                        }
                    }
                });
            }
        }.start();
    }

    // 加载数据
    public abstract ResultState onLoad();

    // 状态的枚举
    public enum ResultState {
        STATE_SUCCESS(STATE_LOAD_SUCCESS), STATE_EMPTY(STATE_LOAD_EMPTY), STATE_ERROR(STATE_LOAD_ERROR);

        private int state;

        ResultState(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }
}
