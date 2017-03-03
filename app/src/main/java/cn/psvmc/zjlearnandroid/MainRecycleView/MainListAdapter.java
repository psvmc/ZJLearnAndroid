package cn.psvmc.zjlearnandroid.MainRecycleView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.psvmc.zjlearnandroid.MainRecycleView.Model.ListItemModel;
import cn.psvmc.zjlearnandroid.R;

/**
 * Created by PSVMC on 16/5/26.
 */
public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ItemViewHolder> {

    private List<ListItemModel> mDatas;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;

    public MainListAdapter(Context context, List<ListItemModel> mDatas) {
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        /**
         * 使用RecyclerView，ViewHolder是可以复用的。这根使用ListView的VIewHolder复用是一样的
         * ViewHolder创建的个数好像是可见item的个数+3
         */
        ItemViewHolder holder = new ItemViewHolder(mInflater.inflate(
                R.layout.main_list_item, viewGroup, false));
        return holder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int pos) {
        itemViewHolder.mTextView.setText(mDatas.get(pos).getName());
        itemViewHolder.tip.setText(mDatas.get(pos).getTip());
        if(mOnItemClickListener != null) {
            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, pos);
                }
            });
            itemViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(v, pos);
                    return true;
                }
            });
        }
    }


    /**
     * 向指定位置添加元素
     * @param position
     * @param value
     */
    public void add(int position, String value,String tip) {
        if(position > mDatas.size()) {
            position = mDatas.size();
        }
        if(position < 0) {
            position = 0;
        }
        mDatas.add(position, new ListItemModel(""+position,value,tip));
        /**
         * 使用notifyItemInserted/notifyItemRemoved会有动画效果
         * 而使用notifyDataSetChanged()则没有
         */
        notifyItemInserted(position);
    }

    /**
     * 移除指定位置元素
     * @param position
     * @return
     */
    public ListItemModel remove(int position) {
        if(position > mDatas.size()-1) {
            return null;
        }
        ListItemModel value = mDatas.remove(position);
        notifyItemRemoved(position);
        return value;
    }


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * 处理item的点击事件和长按事件
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private TextView tip;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textview);
            tip = (TextView) itemView.findViewById(R.id.tip);
        }
    }

}
