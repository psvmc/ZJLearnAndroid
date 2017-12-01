package cn.psvmc.zjlearnandroid.DemoChat.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import java.util.List;

import cn.psvmc.zjlearnandroid.DemoChat.Model.ChatMsgModel;
import cn.psvmc.zjlearnandroid.R;

/**
 * Created by zhangjian on 2017/12/1.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ItemViewHolder>{

    private final int chatViewTypeLeft = 1;
    private final int chatViewTypeRight = 2;


    private List<ChatMsgModel> mDatas;
    private LayoutInflater mInflater;
    private ChatAdapter.OnItemClickListener mOnItemClickListener;

    private int loginUserId = 1;


    public ChatAdapter(Context context, List<ChatMsgModel> mDatas) {
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    @Override
    public int getItemViewType(int position) {
        ChatMsgModel itemData = mDatas.get(position);
        if(itemData.getFromId() == loginUserId){
            return chatViewTypeRight;
        }else{
            return chatViewTypeLeft;
        }

    }

    @Override
    public ChatAdapter.ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if(i == chatViewTypeLeft){
            ChatAdapter.ItemViewHolder holder = new ChatAdapter.ItemViewHolder(mInflater.inflate(
                    R.layout.chat_list_item_left, viewGroup, false));
            return holder;
        }else{
            ChatAdapter.ItemViewHolder holder = new ChatAdapter.ItemViewHolder(mInflater.inflate(
                    R.layout.chat_list_item_right, viewGroup, false));
            return holder;
        }

    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final ChatAdapter.ItemViewHolder itemViewHolder, final int pos) {


        ChatMsgModel chatMsgModel = mDatas.get(pos);

        if(chatMsgModel.getType() == 1){
            itemViewHolder.textLayout.setVisibility(View.VISIBLE);
            itemViewHolder.imageLayout.setVisibility(View.GONE);
        }else if(chatMsgModel.getType() == 2){
            itemViewHolder.textLayout.setVisibility(View.GONE);
            itemViewHolder.imageLayout.setVisibility(View.VISIBLE);
        }

        if(mOnItemClickListener != null) {
            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, pos);
                }
            });
        }
    }


    /**
     * 向指定位置添加元素
     * @param position
     */
    public void add(int position, ChatMsgModel msg) {
        if(position > mDatas.size()) {
            position = mDatas.size();
        }
        if(position < 0) {
            position = 0;
        }
        mDatas.add(position, msg);
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
    public ChatMsgModel remove(int position) {
        if(position > mDatas.size()-1) {
            return null;
        }
        ChatMsgModel value = mDatas.remove(position);
        notifyItemRemoved(position);
        return value;
    }


    public void setOnItemClickListener(ChatAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * 处理item的点击事件和长按事件
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView userImgView;
        private RelativeLayout textLayout;
        private RelativeLayout imageLayout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            userImgView = (ImageView) itemView.findViewById(R.id.userImgView);
            textLayout = (RelativeLayout) itemView.findViewById(R.id.textLayout);
            imageLayout = (RelativeLayout) itemView.findViewById(R.id.imageLayout);
        }
    }
}
