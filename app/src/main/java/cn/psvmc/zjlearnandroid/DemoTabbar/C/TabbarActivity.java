package cn.psvmc.zjlearnandroid.DemoTabbar.C;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioGroup;

import java.util.ArrayList;

import cn.psvmc.zjlearnandroid.R;
import cn.psvmc.zjlearnandroid.DemoTabbar.M.ZJTabInfo;
import cn.psvmc.zjlearnandroid.DemoTabbar.Delegate.ZJTabSelectDelegate;
import cn.psvmc.zjlearnandroid.DemoTabbar.Adapter.ZJTabsAdapter;

public class TabbarActivity extends AppCompatActivity implements ZJTabSelectDelegate {
    String TAG = "TabbarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbar);
        initView();
    }

    private void initView() {
        ArrayList<ZJTabInfo> tabInfoList = new ArrayList<>();
        ZJTabInfo tab1 = new ZJTabInfo(Fragment1.class, null);
        ZJTabInfo tab2 = new ZJTabInfo(Fragment2.class, null);
        ZJTabInfo tab3 = new ZJTabInfo(Fragment3.class, null);
        ZJTabInfo tab4 = new ZJTabInfo(Fragment4.class, null);
        tabInfoList.add(tab1);
        tabInfoList.add(tab2);
        tabInfoList.add(tab3);
        tabInfoList.add(tab4);

        RadioGroup zjRadioGroup = (RadioGroup) findViewById(R.id.zjtab);
        ViewPager mViewPage = (ViewPager) findViewById(R.id.pager);
        ZJTabsAdapter mTabsAdapter = new ZJTabsAdapter(this, mViewPage, zjRadioGroup, tabInfoList);
        mViewPage.setAdapter(mTabsAdapter);
    }

    @Override
    public void zjtabbarSelectItem(int index) {
        Log.i(TAG, "选中项的index为：" + index);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
    }
}
