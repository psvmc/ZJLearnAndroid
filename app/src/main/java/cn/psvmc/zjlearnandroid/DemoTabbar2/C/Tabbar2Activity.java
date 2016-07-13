package cn.psvmc.zjlearnandroid.DemoTabbar2.C;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

import cn.psvmc.zjlearnandroid.DemoTabbar.C.Fragment1;
import cn.psvmc.zjlearnandroid.DemoTabbar.C.Fragment2;
import cn.psvmc.zjlearnandroid.DemoTabbar.C.Fragment3;
import cn.psvmc.zjlearnandroid.DemoTabbar.C.Fragment4;
import cn.psvmc.zjlearnandroid.DemoTabbar2.M.ZJTabInfo2;
import cn.psvmc.zjlearnandroid.R;

public class Tabbar2Activity extends AppCompatActivity {

    private FragmentTabHost mTabHost;

    //定义一个布局
    private LayoutInflater layoutInflater;

    ArrayList<ZJTabInfo2> tabInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbar2);
        initData();
        initView();
    }

    private void initData(){

        tabInfoList = new ArrayList<>();
        ZJTabInfo2 tab1 = new ZJTabInfo2(Fragment1.class, "消息",R.drawable.tab_selector_tweet2);
        ZJTabInfo2 tab2 = new ZJTabInfo2(Fragment2.class, "通讯录",R.drawable.tab_selector_message2);
        ZJTabInfo2 tab3 = new ZJTabInfo2(Fragment3.class, "应用",R.drawable.tab_selector_task2);
        ZJTabInfo2 tab4 = new ZJTabInfo2(Fragment4.class, "我",R.drawable.tab_selector_me2);
        tabInfoList.add(tab1);
        tabInfoList.add(tab2);
        tabInfoList.add(tab3);
        tabInfoList.add(tab4);
    }

    /**
     * 初始化组件
     */
    private void initView(){
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        //得到fragment的个数
        int count = tabInfoList.size();

        for(int i = 0; i < count; i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tabInfoList.get(i).text).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, tabInfoList.get(i).clss, null);
        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(tabInfoList.get(index).resId);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(tabInfoList.get(index).text);

        return view;
    }
}
