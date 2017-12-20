package cn.psvmc.zjlearnandroid.DemoUserbook;

import java.util.HashMap;

import android.app.Activity;
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

import android.widget.TextView;

import cn.psvmc.zjlearnandroid.R;


/**
 * 字母索引条
 *
 * @author Administrator
 */
public class QuickAlphabeticBar extends android.support.v7.widget.AppCompatImageButton {
    String TAG = "QuickAlphabeticBar";
    private TextView mDialogText; // 中间显示字母的文本框
    private Handler mHandler; // 处理UI的句柄
    private RecyclerView recyclerView; // 列表
    private float mHight; // 高度
    // 字母列表索引
    private String[] letters = new String[]{"#", "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z"};
    // 字母索引哈希表
    private HashMap<String, Integer> alphaIndexer;
    Paint paint = new Paint();
    boolean showBkg = false;
    int choose = -1;

    public QuickAlphabeticBar(Context context) {
        super(context);
    }

    public QuickAlphabeticBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public QuickAlphabeticBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 初始化
    public void init(Activity ctx) {
        mDialogText = (TextView) ctx.findViewById(R.id.fast_position);
        mDialogText.setVisibility(View.INVISIBLE);
        mHandler = new Handler();
    }

    // 设置需要索引的列表
    public void setListView(RecyclerView mList) {
        this.recyclerView = mList;
    }

    // 设置字母索引哈希表
    public void setAlphaIndexer(HashMap<String, Integer> alphaIndexer) {
        this.alphaIndexer = alphaIndexer;
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
            if (null != alphaIndexer) {
                if (alphaIndexer.containsKey(key)) {
                    int pos = alphaIndexer.get(key);
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
                    if (selectIndex > 0 && selectIndex < letters.length) {
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

}
