package cn.psvmc.zjlearnandroid.DemoPopupWindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import cn.psvmc.zjlearnandroid.R;


public class ZJCustomPopWindow extends PopupWindow {

    private Button btn_cancel;
    private View mMenuView;
    private LinearLayout paixv,xiazai,shangchuan,huishouzhan,quanbu,tupian,wendang,shipin,yinyue,shoucang;

    public ZJCustomPopWindow(Activity context, final OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.zj_custom_popwindow, null);
        btn_cancel = (Button) mMenuView.findViewById(R.id.btn_cancel);
        paixv = (LinearLayout) mMenuView.findViewById(R.id.paixv);
        xiazai = (LinearLayout) mMenuView.findViewById(R.id.xiazai);
        shangchuan = (LinearLayout) mMenuView.findViewById(R.id.shangchuan);
        huishouzhan = (LinearLayout) mMenuView.findViewById(R.id.huishouzhan);
        quanbu = (LinearLayout) mMenuView.findViewById(R.id.quanbu);
        tupian = (LinearLayout) mMenuView.findViewById(R.id.tupian);
        wendang = (LinearLayout) mMenuView.findViewById(R.id.wendang);
        shipin = (LinearLayout) mMenuView.findViewById(R.id.shipin);
        yinyue = (LinearLayout) mMenuView.findViewById(R.id.yinyue);
        shoucang = (LinearLayout) mMenuView.findViewById(R.id.shoucang);
        //取消按钮
        btn_cancel.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });
        //设置按钮监听
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });

        paixv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                itemsOnClick.onClick(v);;
            }
        });
        xiazai.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                itemsOnClick.onClick(v);;
            }
        });
        shangchuan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                itemsOnClick.onClick(v);;
            }
        });
        huishouzhan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                itemsOnClick.onClick(v);;
            }
        });
        quanbu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                itemsOnClick.onClick(v);;
            }
        });
        tupian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                itemsOnClick.onClick(v);;
            }
        });
        wendang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                itemsOnClick.onClick(v);;
            }
        });
        shipin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                itemsOnClick.onClick(v);;
            }
        });
        yinyue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                itemsOnClick.onClick(v);;
            }
        });
        shoucang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                itemsOnClick.onClick(v);;
            }
        });
    }

}