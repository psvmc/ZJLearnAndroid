package cn.psvmc.zjlearnandroid.DemoDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.psvmc.zjlearnandroid.R;

public class ZJDialogActivity extends AppCompatActivity {

    private Button alertButton;
    RelativeLayout back_layout;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zjdialog);
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

    private void alertButtonAction() {
        ZJCustomDialog.Builder builder = new ZJCustomDialog.Builder(this);
        builder.setMessage("这个就是自定义的提示框");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(mcontext, "确认", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(mcontext, "取消", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

    /**
     * 初始化后退操作
     */
    private void initBack() {
        back_layout = (RelativeLayout) findViewById(R.id.back_layout);
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
