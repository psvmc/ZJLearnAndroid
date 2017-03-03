package cn.psvmc.zjlearnandroid.DemoTabLayout.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import cn.psvmc.zjlearnandroid.DemoTabLayout.Adapter.ZJTabPagerAdapter;
import cn.psvmc.zjlearnandroid.DemoTabLayout.Fragment.ZJTabFragment;
import cn.psvmc.zjlearnandroid.DemoTabbar.M.ZJTabInfo;
import cn.psvmc.zjlearnandroid.R;

public class TabLayoutActivity extends AppCompatActivity{
    RelativeLayout back_layout;
    TabLayout zj_tablayout;
    ViewPager zj_vp;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        mcontext = this;
        initBack();
        initTab();
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

    private void initTab(){
        zj_tablayout = (TabLayout)findViewById(R.id.zj_tablayout);
        zj_vp = (ViewPager)findViewById(R.id.zj_vp);

        Bundle bundle1 = new Bundle();
        bundle1.putString("title","昨天很残酷");
        Bundle bundle2 = new Bundle();
        bundle2.putString("title","今天更残酷");
        Bundle bundle3 = new Bundle();
        bundle3.putString("title","明天很美好");

        ArrayList<ZJTabInfo> tabInfoList = new ArrayList<>();
        ZJTabInfo tab1 = new ZJTabInfo(ZJTabFragment.class, bundle1,"昨天");
        ZJTabInfo tab2 = new ZJTabInfo(ZJTabFragment.class, bundle2,"今天");
        ZJTabInfo tab3 = new ZJTabInfo(ZJTabFragment.class, bundle3,"明天");
        tabInfoList.add(tab1);
        tabInfoList.add(tab2);
        tabInfoList.add(tab3);

        ZJTabPagerAdapter pagerAdapter = new ZJTabPagerAdapter(this,tabInfoList);
        zj_vp.setAdapter(pagerAdapter);
        zj_tablayout.setTabTextColors(getResources().getColor(R.color.zj_gay), getResources().getColor(R.color.zj_blue));
        zj_tablayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.zj_blue));
        zj_tablayout.setTabsFromPagerAdapter(pagerAdapter);
        zj_tablayout.setupWithViewPager(zj_vp);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
    }
}
