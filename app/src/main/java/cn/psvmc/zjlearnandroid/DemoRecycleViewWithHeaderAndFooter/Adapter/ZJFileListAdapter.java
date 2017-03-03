package cn.psvmc.zjlearnandroid.DemoRecycleViewWithHeaderAndFooter.Adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cn.psvmc.utils.DensityUtils;
import cn.psvmc.utils.ZJFileUtils;
import cn.psvmc.zjlearnandroid.DemoRecycleViewWithHeaderAndFooter.Model.ZJFileModel;
import cn.psvmc.zjlearnandroid.R;

/**
 * Created by PSVMC on 16/7/14.
 */
public class ZJFileListAdapter extends ZJCommonAdapter<ZJFileModel> {

    private OnItemClickListener onItemClickListener;

    public ZJFileListAdapter(Context context, List<ZJFileModel> mDatas) {
        this.mDatas = mDatas;
        this.mInflater = LayoutInflater.from(context);
        this.mcontext = context;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.onItemClickListener = mOnItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder zjCreateHeaderViewHolder(ViewGroup parent) {
        HeaderViewHolder holder = new HeaderViewHolder(mInflater.inflate(R.layout.file_search_header, parent, false));
        return holder;
    }

    @Override
    public RecyclerView.ViewHolder zjCreateContentViewHolder(ViewGroup parent) {
        ContentViewHolder holder = new ContentViewHolder(mInflater.inflate(
                R.layout.file_list_item, parent, false));
        return holder;
    }

    @Override
    public RecyclerView.ViewHolder zjCreateFooterViewHolder(ViewGroup parent) {
        View itemView = mInflater.inflate(R.layout.file_search_footer, parent, false);
        FooterViewHolder holder = new FooterViewHolder(itemView);
        return holder;
    }

    @Override
    public void zjBindHeaderViewHolder(final RecyclerView.ViewHolder holder) {
        final HeaderViewHolder itemViewHolder = (HeaderViewHolder) holder;
        if(!itemViewHolder.search_button.hasOnClickListeners()){
            itemViewHolder.search_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != onItemClickListener) {
                        String searchText = itemViewHolder.search_text.getText().toString();
                        onItemClickListener.onSearchClick(v, searchText);

                    }
                }
            });

            itemViewHolder.search_close_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemViewHolder.search_text.setText("");
                }
            });

            itemViewHolder.search_text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
            itemViewHolder.search_text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.toString().equals("")) {
                        if (null != onItemClickListener) {
                            itemViewHolder.search_close.setVisibility(View.INVISIBLE);
                            onItemClickListener.onClearSearch();
                        }
                    }else{
                        itemViewHolder.search_close.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

    }

    @Override
    public void zjBindContentViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ContentViewHolder itemViewHolder = (ContentViewHolder) holder;
        final ZJFileModel zjFileModel = mDatas.get(position);
        if (zjFileModel.getTYPE() == 0) {
            itemViewHolder.leftImageView.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.ic_parttern_icon_folder));
        } else {
            itemViewHolder.leftImageView.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.ic_parttern_icon_doc2));
            String suffix = ZJFileUtils.getSuffix(zjFileModel.getFILE_PATH());
            Drawable drawable = ZJFileUtils.getDrawableBySuffix(mcontext, suffix);
            if (drawable != null) {
                itemViewHolder.leftImageView.setImageDrawable(drawable);
            }
        }

        if (zjFileModel.getIS_TAR() == 1) {
            itemViewHolder.file_star_image.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.file_operation_star_check));
        } else {
            itemViewHolder.file_star_image.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.file_operation_star));
        }
        final ContentViewHolder finalItemViewHolder = itemViewHolder;
        boolean isOpen = zjFileModel.isOpen();
        if (isOpen) {
            ViewGroup.LayoutParams vg = itemViewHolder.file_list_item.getLayoutParams();
            vg.height = DensityUtils.dp2px(mcontext, 120);
            itemViewHolder.file_list_item.setLayoutParams(vg);
        } else {
            ViewGroup.LayoutParams vg = itemViewHolder.file_list_item.getLayoutParams();
            vg.height = DensityUtils.dp2px(mcontext, 60);
            itemViewHolder.file_list_item.setLayoutParams(vg);
        }
        itemViewHolder.file_name.setText(zjFileModel.getFILE_NAME());
        if(zjFileModel.getTYPE() == 0){
            itemViewHolder.file_time.setText(zjFileModel.getUPDATE_DATE());
        }else{
            String timeStr = "";
            timeStr += ZJFileUtils.getFileSizeStr(zjFileModel.getFILE_SIZE())+",";
            timeStr += zjFileModel.getUPDATE_DATE();
            itemViewHolder.file_time.setText(timeStr);
        }

        if (null != onItemClickListener) {
            itemViewHolder.file_item_fold_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onLayoutClick(v, position);
                }
            });

            itemViewHolder.file_item_top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });

            itemViewHolder.file_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onShareClick(v, position);
                }
            });

            itemViewHolder.file_move.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onMoveClick(v, position);
                }
            });

            itemViewHolder.file_star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onStarClick(v, position);
                }
            });

            itemViewHolder.file_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onMoreClick(v, position);
                }
            });
        }
        Integer targetHeight = 0;
        if (zjFileModel.isOpen()) {
            targetHeight = DensityUtils.dp2px(mcontext, 120);
            itemViewHolder.file_item_fold_image.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.cell_item_fold));
        } else {
            targetHeight = DensityUtils.dp2px(mcontext, 60);
            itemViewHolder.file_item_fold_image.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.cell_item_unfold));
        }
        ViewWrapper viewWrapper = new ViewWrapper(finalItemViewHolder.file_list_item);
        ObjectAnimator
                .ofInt(viewWrapper, "height", targetHeight)
                .setDuration(120)
                .start();
    }

    @Override
    public void zjBindFooterViewHolder(RecyclerView.ViewHolder holder) {
    }

    private static class ViewWrapper {
        private ViewGroup mTarget;

        public ViewWrapper(ViewGroup target) {
            mTarget = target;
        }

        public int getHeight() {
            return mTarget.getLayoutParams().height;
        }

        public void setHeight(int height) {
            ViewGroup.LayoutParams vg = mTarget.getLayoutParams();
            vg.height = height;
            mTarget.setLayoutParams(vg);
        }
    }

    /**
     * 处理item的点击事件和长按事件
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onLayoutClick(View view, int position);

        void onShareClick(View view, int position);

        void onMoveClick(View view, int position);

        void onStarClick(View view, int position);

        void onMoreClick(View view, int position);

        void onSearchClick(View view, String searchText);

        void onClearSearch();
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public EditText search_text;
        public Button search_button;
        public ImageView search_close;
        public RelativeLayout search_close_layout;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            search_text = (EditText) itemView.findViewById(R.id.search_text);
            search_button = (Button) itemView.findViewById(R.id.search_button);
            search_close = (ImageView) itemView.findViewById(R.id.search_close);
            search_close_layout = (RelativeLayout) itemView.findViewById(R.id.search_close_layout);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout file_item_fold_layout;
        public LinearLayout file_item_bottom_layout;
        public LinearLayout file_list_item;
        public TextView file_name, file_time;
        public ImageView leftImageView, file_star_image;
        public LinearLayout file_item_left, file_item_top;
        public RelativeLayout file_share, file_move, file_star, file_more;
        public ImageView file_item_fold_image;

        public ContentViewHolder(View itemView) {
            super(itemView);
            file_item_fold_layout = (RelativeLayout) itemView.findViewById(R.id.file_item_fold);
            file_item_bottom_layout = (LinearLayout) itemView.findViewById(R.id.file_item_bottom);
            file_list_item = (LinearLayout) itemView.findViewById(R.id.file_list_item);
            file_name = (TextView) itemView.findViewById(R.id.file_name);
            file_time = (TextView) itemView.findViewById(R.id.file_time);
            leftImageView = (ImageView) itemView.findViewById(R.id.leftImageView);
            file_item_left = (LinearLayout) itemView.findViewById(R.id.file_item_left);
            file_item_top = (LinearLayout) itemView.findViewById(R.id.file_item_top);
            file_item_fold_image = (ImageView) itemView.findViewById(R.id.file_item_fold_image);

            file_share = (RelativeLayout) itemView.findViewById(R.id.file_share);
            file_move = (RelativeLayout) itemView.findViewById(R.id.file_move);
            file_star = (RelativeLayout) itemView.findViewById(R.id.file_star);
            file_more = (RelativeLayout) itemView.findViewById(R.id.file_more);
            file_star_image = (ImageView) itemView.findViewById(R.id.file_star_image);
        }
    }
}