package cn.psvmc.zjlearnandroid.DemoPopupWindow;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import cn.psvmc.zjlearnandroid.R;

public class ZJPopWindowActivity extends AppCompatActivity implements View.OnClickListener{

    private Button alertButton;
    RelativeLayout back_layout;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_window);
        mcontext = this;
        initBack();
        alertButton = (Button) findViewById(R.id.alertButton);
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertButtonAction();
            }
        });
    }

    private void alertButtonAction(){
        //实例化SelectPicPopupWindow
        ZJCustomPopWindow menuWindow = new ZJCustomPopWindow(this, this);
        //显示窗口
        menuWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
    }

    /**
     * 初始化后退操作
     */
    private void initBack(){
        back_layout = (RelativeLayout)findViewById(R.id.back_layout);
        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
    }

    @Override
    public void onClick(View v) {

    }
}
