package cn.psvmc.zjlearnandroid.DemoUserbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cn.psvmc.zjlearnandroid.R;

/**
 * Created by PSVMC on 16/5/26.
 */
public class UserbookListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final int UserbookHeader = 0;
    final int UserbookItem = 1;
    final int UserbookFooter = 2;

    private List<UserbookItemModel> mDatas;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;

    public UserbookListAdapter(Context context, List<UserbookItemModel> mDatas) {
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return UserbookHeader;
        } else if (position == mDatas.size() + 1) {
            return UserbookFooter;
        } else {
            return UserbookItem;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == UserbookHeader) {
            HeaderViewHolder holder = new HeaderViewHolder(
                    mInflater.inflate(R.layout.userbook_header_item, viewGroup, false)
            );
            return holder;
        } else if (viewType == UserbookFooter) {
            FooterViewHolder holder = new FooterViewHolder(
                    mInflater.inflate(R.layout.userbook_footer_item, viewGroup, false)
            );
            return holder;
        } else {
            ItemViewHolder holder = new ItemViewHolder(
                    mInflater.inflate(R.layout.userbook_list_item, viewGroup, false)
            );
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position == 0) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;

        } else if (position == mDatas.size() + 1) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;

        } else {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

            final int realPos = position - 1;

            UserbookItemModel userbookItemModel = mDatas.get(realPos);
            itemViewHolder.nameTextView.setText(userbookItemModel.getName());
            itemViewHolder.firstCharTextView.setText(userbookItemModel.getFirstChar());
            if(realPos == 0){
                itemViewHolder.firstCharLayout.setVisibility(View.VISIBLE);
            }else{
                UserbookItemModel lastUserbookItemModel = mDatas.get(realPos-1);
                if(userbookItemModel.getFirstChar().equals(lastUserbookItemModel.getFirstChar())){
                    itemViewHolder.firstCharLayout.setVisibility(View.GONE);
                }else{
                    itemViewHolder.firstCharLayout.setVisibility(View.VISIBLE);
                }
            }

            if (mOnItemClickListener != null) {
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(v, realPos);
                    }
                });
            }
        }

    }


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * 处理item的点击事件和长按事件
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView nameTextView;
        private TextView firstCharTextView;
        private RelativeLayout firstCharLayout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.mImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            firstCharTextView = (TextView) itemView.findViewById(R.id.firstCharTextView);
            firstCharLayout = (RelativeLayout) itemView.findViewById(R.id.firstCharLayout);
        }
    }


    class HeaderViewHolder extends RecyclerView.ViewHolder {


        public HeaderViewHolder(View itemView) {
            super(itemView);

        }
    }


    class FooterViewHolder extends RecyclerView.ViewHolder {


        public FooterViewHolder(View itemView) {
            super(itemView);

        }
    }

}
