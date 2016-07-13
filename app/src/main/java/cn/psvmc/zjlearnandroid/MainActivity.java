package cn.psvmc.zjlearnandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.psvmc.zjlearnandroid.DemoFloatingActionButton.FloatingActionButtonActivity;
import cn.psvmc.zjlearnandroid.DemoRecycleView.RecycleViewDivider;
import cn.psvmc.zjlearnandroid.DemoRecycleView.ShareListAdapter;
import cn.psvmc.zjlearnandroid.DemoTabbar.C.TabbarActivity;
import cn.psvmc.zjlearnandroid.DemoTabbar2.C.Tabbar2Activity;
import cn.psvmc.zjlearnandroid.Model.ListItemModel;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    Activity activity = this;

    private RecyclerView mRecyclerView;
    private ShareListAdapter mListAdapter;
    private List<ListItemModel> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("RecyclerView的使用");
        mDatas.remove(mDatas);
        mDatas.add(new ListItemModel("tabbar01","Tabbar方式一","(RadioGroup + ViewPager + Fragment) \n(加载相邻的Fragment)"));
        mDatas.add(new ListItemModel("tabbar02","Tabbar方式二","(FragmentTabHost + Fragment) \n(加载选中的Fragment)"));
        mDatas.add(new ListItemModel("FloatingActionButton","FloatingActionButton",""));
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mListAdapter = new ShareListAdapter(this, mDatas);
        mRecyclerView.setAdapter(mListAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(context,LinearLayoutManager.VERTICAL));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mListAdapter.setOnItemClickListener(new ShareListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent myIntent = new Intent();
                myIntent.putExtra("title","我的分享");
                String tag = mDatas.get(position).getTag();
                if(tag.equals("tabbar01")){
                    myIntent.setClass(context, TabbarActivity.class);
                }else if(tag.equals("tabbar02")){
                    myIntent.setClass(context, Tabbar2Activity.class);
                }else if(tag.equals("FloatingActionButton")){
                    myIntent.setClass(context, FloatingActionButtonActivity.class);
                }

                startActivity(myIntent);
                activity.overridePendingTransition(R.anim.open_enter,R.anim.open_exit);

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

}
