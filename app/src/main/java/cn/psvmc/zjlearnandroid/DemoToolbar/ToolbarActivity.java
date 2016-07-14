package cn.psvmc.zjlearnandroid.DemoToolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import cn.psvmc.zjlearnandroid.R;

public class ToolbarActivity extends AppCompatActivity {
    String TAG = "ToolbarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //必须写在setSupportActionBar(toolbar)的前面
        toolbar.setTitle("Toolbar使用");
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
