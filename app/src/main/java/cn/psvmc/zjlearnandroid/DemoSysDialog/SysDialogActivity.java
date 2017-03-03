package cn.psvmc.zjlearnandroid.DemoSysDialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import cn.psvmc.utils.ZJViewUtils;
import cn.psvmc.zjlearnandroid.R;

public class SysDialogActivity extends AppCompatActivity {

    private Button alertButton,progressDialogButton;
    RelativeLayout back_layout;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys_dialog);
        mcontext = this;
        initBack();
        alertButton = (Button) findViewById(R.id.alertButton);
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertButtonAction();
            }
        });

        progressDialogButton = (Button) findViewById(R.id.progressDialogButton);
        progressDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialogButtonAction();
            }
        });
    }

    private void alertButtonAction(){
        ZJViewUtils.showDialog(mcontext,"提示","并没有什么卵用？" ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Snackbar.make(getWindow().getDecorView(), "点击了确定", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void progressDialogButtonAction(){
        final ProgressDialog pd = ProgressDialog.show(this, "登录", "加载中，请稍后……");
        new Handler().postDelayed(new Runnable(){
            public void run() {
                pd.dismiss();
            }
        }, 2000);
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
}
