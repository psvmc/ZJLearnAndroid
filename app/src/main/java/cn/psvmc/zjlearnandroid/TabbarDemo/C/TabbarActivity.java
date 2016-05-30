package cn.psvmc.zjlearnandroid.TabbarDemo.C;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioGroup;

import java.util.ArrayList;

import cn.psvmc.zjlearnandroid.R;
import cn.psvmc.zjlearnandroid.TabbarDemo.Lib.ZJTabInfo;
import cn.psvmc.zjlearnandroid.TabbarDemo.Lib.ZJTabSelectDelegate;
import cn.psvmc.zjlearnandroid.TabbarDemo.Lib.ZJTabsAdapter;

public class TabbarActivity extends AppCompatActivity  implements ZJTabSelectDelegate {
    String TAG = "TabbarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbar);
        initView();
    }

    private void initView() {
        ArrayList<ZJTabInfo> tabInfoList = new ArrayList<>();
        ZJTabInfo tab1 = new ZJTabInfo("" + 0, Fragment1.class, null, R.drawable.tab_selector_tweet, "消息");
        ZJTabInfo tab2 = new ZJTabInfo("" + 1, Fragment2.class, null, R.drawable.tab_selector_message, "通讯录");
        ZJTabInfo tab3 = new ZJTabInfo("" + 2, Fragment3.class, null, R.drawable.tab_selector_task, "应用");
        ZJTabInfo tab4 = new ZJTabInfo("" + 3, Fragment4.class, null, R.drawable.tab_selector_me, "我");
        tabInfoList.add(tab1);
        tabInfoList.add(tab2);
        tabInfoList.add(tab3);
        tabInfoList.add(tab4);

        RadioGroup zjRadioGroup = (RadioGroup)findViewById(R.id.zjtab);
        ViewPager mViewPage = (ViewPager) findViewById(R.id.pager);
        ZJTabsAdapter mTabsAdapter = new ZJTabsAdapter(this, mViewPage, zjRadioGroup, tabInfoList);
        mViewPage.setAdapter(mTabsAdapter);
    }

    @Override
    public void zjtabbarSelectItem(int index) {
        Log.i(TAG, "选中项的index为："+index);
    }
}
