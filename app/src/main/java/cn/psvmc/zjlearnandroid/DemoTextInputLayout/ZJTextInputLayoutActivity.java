package cn.psvmc.zjlearnandroid.DemoTextInputLayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import cn.psvmc.zjlearnandroid.R;

public class ZJTextInputLayoutActivity extends AppCompatActivity {
    RelativeLayout back_layout;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zjtext_input_layout);
        mcontext = this;
        initBack();
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
