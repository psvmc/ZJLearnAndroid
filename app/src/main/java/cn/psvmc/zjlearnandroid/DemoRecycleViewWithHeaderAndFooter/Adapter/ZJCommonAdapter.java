package cn.psvmc.zjlearnandroid.DemoRecycleViewWithHeaderAndFooter.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by PSVMC on 16/7/14.
 */
public abstract class ZJCommonAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected Context mcontext;

    /**
     * 向指定位置添加元素
     *
     * @param position
     * @param value
     */
    public void add(int position, T value) {
        if (position > mDatas.size()) {
            position = mDatas.size();
        }
        if (position < 0) {
            position = 0;
        }
        mDatas.add(position, value);
        /**
         * 使用notifyItemInserted/notifyItemRemoved会有动画效果
         * 而使用notifyDataSetChanged()则没有
         */
        notifyItemInserted(position + 1);
    }

    /**
     * 移除指定位置元素
     *
     * @param position
     * @return
     */
    public T remove(int position) {
        if (position > mDatas.size() - 1) {
            return null;
        }
        T value = mDatas.remove(position);
        notifyItemRemoved(position + 1);
        return value;
    }

    /**
     * 更新单行内容item
     *
     * @param position 不包含头部和尾部
     */
    public void update(int position) {
        if (position > mDatas.size() - 1) {
            return;
        }
        notifyItemChanged(position + 1);
    }

    /**
     * 是否是头部
     * @param position 包含头尾
     * @return
     */
    public boolean isHeader(int position){
        if (position == 0) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * 是否为尾部
     * @param position 包含头尾
     * @return
     */
    public boolean isFooter(int position){
        if (position == mDatas.size() + 1) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return ITEM_TYPE_HEADER;
        } else if (isFooter(position)) {
            return ITEM_TYPE_BOTTOM;
        } else {
            return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            return zjCreateHeaderViewHolder(parent);
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            return zjCreateFooterViewHolder(parent);
        } else {
            return zjCreateContentViewHolder(parent);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeader(position)) {
            zjBindHeaderViewHolder(holder);
        } else if (isFooter(position)) {
            zjBindFooterViewHolder(holder);
        } else {
            zjBindContentViewHolder(holder, position - 1);
        }
    }

    @Override
    public int getItemCount() {
        if (null == mDatas) {
            return 2;
        } else {
            return mDatas.size() + 2;
        }
    }

    /**
     * 创建Header
     *
     * @param parent
     * @return
     */
    public abstract RecyclerView.ViewHolder zjCreateHeaderViewHolder(ViewGroup parent);

    /**
     * 创建Content
     *
     * @param parent
     * @return
     */
    public abstract RecyclerView.ViewHolder zjCreateContentViewHolder(ViewGroup parent);

    /**
     * 创建Footer
     *
     * @param parent
     * @return
     */
    public abstract RecyclerView.ViewHolder zjCreateFooterViewHolder(ViewGroup parent);

    /**
     * 设置Header事件或数据
     *
     * @param holder
     */
    public abstract void zjBindHeaderViewHolder(RecyclerView.ViewHolder holder);

    /**
     * 设置Content事件或数据
     *
     * @param holder
     * @param position 不包含头尾
     */
    public abstract void zjBindContentViewHolder(RecyclerView.ViewHolder holder, int position);

    /**
     * 设置Footer事件或数据
     *
     * @param holder
     */
    public abstract void zjBindFooterViewHolder(RecyclerView.ViewHolder holder);

}

