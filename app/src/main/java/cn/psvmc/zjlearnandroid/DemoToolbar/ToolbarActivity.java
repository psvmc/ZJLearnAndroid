package cn.psvmc.zjlearnandroid.DemoToolbar;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import cn.psvmc.zjlearnandroid.R;

public class ToolbarActivity extends AppCompatActivity {
    String TAG = "ToolbarActivity";

    Button snackbarButton1;
    Button snackbarButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        initToolbar();
        initSnackButton();
    }

    private void initSnackButton(){
        snackbarButton1 = (Button)findViewById(R.id.snackbarButton1);
        snackbarButton2 = (Button)findViewById(R.id.snackbarButton2);

        snackbarButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(getWindow().getDecorView(), "显示完就消失", Snackbar.LENGTH_SHORT).show();
            }
        });

        snackbarButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Snackbar snackbar = Snackbar.make(getWindow().getDecorView(),"点击按钮也会消失",Snackbar.LENGTH_LONG);

                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.zj_blue));
                snackbar.setActionTextColor(getResources().getColor(R.color.zj_white));
                snackbar.setAction("消失",new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //必须写在setSupportActionBar(toolbar)的前面
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //这是自定义返回按钮的图标
        //toolbar.setNavigationIcon(R.drawable.zj_toobar_back);
        //使用系统默认返回图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.demo_toobar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_show:
                Log.i(TAG, "onMenuItemClick: 查看");
                break;
            case R.id.action_edit:
                Log.i(TAG, "onMenuItemClick: 编辑");
                break;
            case R.id.action_del:
                Log.i(TAG, "onMenuItemClick: 删除");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
    }
}
