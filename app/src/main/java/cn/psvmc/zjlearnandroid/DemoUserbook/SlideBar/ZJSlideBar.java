package cn.psvmc.zjlearnandroid.DemoUserbook.SlideBar;

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

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.widget.TextView;


/**
 * 字母索引条
 *
 * @author psvmc
 */
public class ZJSlideBar extends android.support.v7.widget.AppCompatImageButton {
    String TAG = "ZJSlideBar";
    private TextView mDialogTextView; // 中间显示字母的文本框
    private Handler mHandler; // 处理UI的句柄
    private RecyclerView mRecyclerView; // 列表
    private int mFontSize = 12;
    boolean mShowAllIndex = false;

    Context mContext;

    // 字母列表索引
    private String[] letters = new String[]{"☆", "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z"};
    // 字母索引哈希表
    private HashMap<String, Integer> sectionIndex;

    Paint paint = new Paint();
    int choose = -1;

    public ZJSlideBar(Context context) {
        super(context);
        mContext = context;
    }

    public ZJSlideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    public ZJSlideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    /**
     * 初始化
     *
     * @param recyclerView recyclerView
     * @param mDialogText  mDialogText
     * @param fontSize     文字的大小
     */
    public void init(RecyclerView recyclerView, TextView mDialogText, int fontSize) {
        this.mRecyclerView = recyclerView;

        this.mDialogTextView = mDialogText;
        mDialogText.setVisibility(View.INVISIBLE);

        mFontSize = fontSize;
        mHandler = new Handler();

        this.setVisibility(View.INVISIBLE);
    }


    /**
     * 设置字母索引哈希表
     *
     * @param sectionIndex 字母索引哈希表
     * @param sectionList  显示的section
     */
    public void setSectionIndex(HashMap<String, Integer> sectionIndex, List<String> sectionList) {
        this.mShowAllIndex = false;
        this.sectionIndex = sectionIndex;
        if (!this.mShowAllIndex && null != sectionList) {
            letters = new String[sectionList.size()];
            for (int i = 0; i < sectionList.size(); i++) {
                letters[i] = sectionList.get(i);
            }
        }

        this.requestLayout();
        this.invalidate();
        this.setVisibility(View.VISIBLE);
    }

    /**
     * 设置字母索引哈希表 字母索引哈希表
     *
     * @param sectionIndex
     */
    public void setSectionIndex(HashMap<String, Integer> sectionIndex) {
        this.mShowAllIndex = true;
        this.sectionIndex = sectionIndex;

        this.requestLayout();
        this.invalidate();
        this.setVisibility(View.VISIBLE);
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
            if (null != mDialogTextView) {
                mDialogTextView.setText(key);
            } else {
                Log.e(TAG, " DialogTextView不能为null");
            }

            if (null != sectionIndex) {
                if (sectionIndex.containsKey(key)) {
                    int pos = sectionIndex.get(key);
                    if (null != mRecyclerView) {
                        moveToPosition(mRecyclerView, pos);
                    } else {
                        Log.e(TAG, "RecyclerView不能为null");
                    }
                }
            }
        }

        switch (act) {
            case MotionEvent.ACTION_DOWN:

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
                            if (mDialogTextView != null && mDialogTextView.getVisibility() == View.INVISIBLE) {
                                mDialogTextView.setVisibility(VISIBLE);
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
                choose = -1;
                if (mHandler != null) {
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            if (mDialogTextView != null
                                    && mDialogTextView.getVisibility() == View.VISIBLE) {
                                mDialogTextView.setVisibility(INVISIBLE);
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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        // 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int sigleHeight = dip2px(mFontSize) + 20;
        int mWidth = sigleHeight;
        int mHeight = sigleHeight * letters.length;

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, mHeight);
        } else {
            setMeasuredDimension(widthSize, heightSize);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int sigleHeight = height / letters.length; // 单个字母占的高度

        for (int i = 0; i < letters.length; i++) {
            paint.setColor(Color.parseColor("#999999"));
            paint.setTextSize(dip2px(mFontSize));
            paint.setTypeface(Typeface.DEFAULT);
            paint.setAntiAlias(true);
            if (i == choose) {
                paint.setColor(Color.parseColor("#00BFFF")); // 滑动时按下字母颜色
                paint.setFakeBoldText(true);
            }
            // 绘画的位置
            float xPos = width / 2 - paint.measureText(letters[i]) / 2;
            float yPos = sigleHeight * i + sigleHeight - 15;
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
            mRecyclerView.scrollToPosition(position);
        } else if (position <= lastItem) {
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.scrollBy(0, top);
            }
        } else {
            mRecyclerView.scrollToPosition(position);
        }
    }


    private int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
