package cn.psvmc.zjlearnandroid.DemoUserbook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;

import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import android.view.MotionEvent;
import android.view.View;

import android.view.ViewGroup;
import android.widget.TextView;


/**
 * 字母索引条
 *
 * @author psvmc
 */
public class QuickAlphabeticBar extends android.support.v7.widget.AppCompatImageButton {
    //String TAG = "QuickAlphabeticBar";
    private TextView mDialogText; // 中间显示字母的文本框
    private Handler mHandler; // 处理UI的句柄
    private RecyclerView recyclerView; // 列表

    Context mContext;
    // 字母列表索引
    private String[] letters = new String[]{"#", "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z"};
    // 字母索引哈希表
    private HashMap<String, Integer> sectionIndex;
    Paint paint = new Paint();
    boolean showBkg = false;
    int choose = -1;

    public QuickAlphabeticBar(Context context) {
        super(context);
        mContext = context;
    }

    public QuickAlphabeticBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    public QuickAlphabeticBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    // 初始化
    public void init() {
        mHandler = new Handler();
    }

    public void setDialogText(TextView mDialogText) {
        this.mDialogText = mDialogText;
        mDialogText.setVisibility(View.INVISIBLE);
    }

    // 设置需要索引的列表
    public void setListView(RecyclerView mList) {
        this.recyclerView = mList;
    }

    // 设置字母索引哈希表
    public void setSectionIndex(HashMap<String, Integer> sectionIndex) {
        this.sectionIndex = sectionIndex;
        Set<String> keys = sectionIndex.keySet();
        List<String> keyList = new ArrayList<String>();
        keyList.addAll(keys);
        Collections.sort(keyList);
        letters = new String[keyList.size()];
        for (int i = 0; i < keyList.size(); i++) {
            letters[i] = keyList.get(i);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int act = event.getAction();
        float y = event.getY();
        final int oldChoose = choose;
        // 计算手指位置，找到对应的段，让mList移动段开头的位置上
        int selectIndex = (int) (y / (getHeight() / letters.length));

        if (selectIndex > -1 && selectIndex < letters.length) { // 防止越界
            String key = letters[selectIndex];
            mDialogText.setText(key);
            if (null != sectionIndex) {
                if (sectionIndex.containsKey(key)) {
                    int pos = sectionIndex.get(key);
                    moveToPosition(recyclerView, pos);
                }
            }
        }
        switch (act) {
            case MotionEvent.ACTION_DOWN:
                showBkg = true;
                if (oldChoose != selectIndex) {
                    if (selectIndex > 0 && selectIndex < letters.length) {
                        choose = selectIndex;
                        invalidate();
                    }
                }
                if (mHandler != null) {
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            if (mDialogText != null && mDialogText.getVisibility() == View.INVISIBLE) {
                                mDialogText.setVisibility(VISIBLE);
                            }
                        }
                    });
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (oldChoose != selectIndex) {
                    if (selectIndex >= 0 && selectIndex < letters.length) {
                        choose = selectIndex;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                showBkg = false;
                choose = -1;
                if (mHandler != null) {
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            if (mDialogText != null
                                    && mDialogText.getVisibility() == View.VISIBLE) {
                                mDialogText.setVisibility(INVISIBLE);
                            }
                        }
                    });
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int sigleHeight = height / letters.length; // 单个字母占的高度
        for (int i = 0; i < letters.length; i++) {
            paint.setColor(Color.parseColor("#aaaaaa"));
            paint.setTextSize(30);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);
            if (i == choose) {
                paint.setColor(Color.parseColor("#00BFFF")); // 滑动时按下字母颜色
                paint.setFakeBoldText(true);
            }
            // 绘画的位置
            float xPos = width / 2 - paint.measureText(letters[i]) / 2;
            float yPos = sigleHeight * i + sigleHeight;
            canvas.drawText(letters[i], xPos, yPos, paint);
            paint.reset();
        }
    }


    private void moveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));

        if (position < firstItem) {
            // 如果跳转位置在第一个可见位置之前，就smoothScrollToPosition可以直接跳转
            mRecyclerView.scrollToPosition(position);
        } else if (position <= lastItem) {
            // 跳转位置在第一个可见项之后，最后一个可见项之前
            // smoothScrollToPosition根本不会动，此时调用smoothScrollBy来滑动到指定位置
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.scrollBy(0, top);
            }
        } else {
            // 如果要跳转的位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
            mRecyclerView.scrollToPosition(position);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        // 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 设置wrap_content的默认宽 / 高值
        // 默认宽/高的设定并无固定依据,根据需要灵活设置
        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
        int sigleHeight = dip2px(mContext, 20);
        int mWidth = sigleHeight;
        int mHeight = sigleHeight * letters.length;

        // 当布局参数设置为wrap_content时，设置默认值
        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight);
            // 宽 / 高任意一个布局参数为= wrap_content时，都设置默认值
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, mHeight);
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
