package com.example.googleplay74.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.googleplay74.ui.holder.BaseHolder;
import com.example.googleplay74.ui.holder.MoreViewHolder;
import com.example.googleplay74.utils.UIUtil;

import java.util.List;

/**
 * ListView数据适配器的基类
 *
 * @param <T> 数据类型
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private static final int TYPE_NORMAL = 0; // 加载更多条目
    private static final int TYPE_MORE = 1;  // 普通条目

    private List<T> data;

    public MyBaseAdapter(List<T> data) {
        this.data = data;
    }

    // 返回条目类型
    @Override
    public int getItemViewType(int position) {
        if (position == data.size()) {
            return TYPE_MORE;
        } else {
            return getInnerViewType();
        }
    }

    // 指定条目的返回类型,子类可以重写
    public int getInnerViewType() {
        return TYPE_NORMAL;
    }

    // 两种类型的条目
    @Override
    public int getViewTypeCount() {
        return 2;// 返回两种布局:普通布局和加载更多布局
    }

    @Override
    public int getCount() {
        return data.size() + 1;
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder;
        if (convertView == null) {
            if (getItemViewType(position) == TYPE_MORE) {
                // 加载更多条目
                holder = new MoreViewHolder(hasMore());
            } else {
                // 普通条目，具体什么条目,子类实现
                holder = instanceHolder();
            }
        } else {
            holder = (BaseHolder) convertView.getTag();
        }

        // 刷新数据
        if (getItemViewType(position) != TYPE_MORE) {
            holder.setData(data.get(position));
        } else {
            // 加载更多布局
            // 一旦加载更多布局展示出来, 就开始加载更多
            // 只有在有更多数据的状态下才加载更多
            MoreViewHolder moreHolder = (MoreViewHolder) holder;
            if (moreHolder.getData() == MoreViewHolder.STATE_MORE_MORE) {
                loadMore(moreHolder);
            }
        }

        return holder.getView();
    }

    // 初始化ViewHolder
    public abstract BaseHolder<T> instanceHolder();

    // 是否可以加载更多，默认可以，子类可以重写
    public boolean hasMore() {
        return true;
    }

    private boolean isLoadMore = false; // 标记是否正在加载更多

    // 加载更多数据
    private void loadMore(final MoreViewHolder holder) {
        if (!isLoadMore) {
            isLoadMore = true;
            new Thread() {
                @Override
                public void run() {
                    final List<T> listData = onLoadMore();
                    UIUtil.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            if (listData != null) {
                                if (listData.size() < 20) {
                                    // 没有更多数据了
                                    holder.setData(MoreViewHolder.STATE_MORE_EMPTY);
                                    UIUtil.showToast("没有更多数据了...");
                                } else {
                                    // 还有更多数据
                                    holder.setData(MoreViewHolder.STATE_MORE_MORE);
                                }
                                // 设置数据
                                data.addAll(listData);
                                notifyDataSetChanged();
                            } else {
                                holder.setData(MoreViewHolder.STATE_MORE_ERROR);
                            }
                        }
                    });
                }
            }.start();

            isLoadMore = false;
        }
    }

    // 加载数据，子类实现
    public abstract List<T> onLoadMore();

    // 获取集合数据总数
    public int getDataSize() {
        return data.size();
    }
}
