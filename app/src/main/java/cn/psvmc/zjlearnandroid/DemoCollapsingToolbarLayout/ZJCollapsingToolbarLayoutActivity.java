package cn.psvmc.zjlearnandroid.DemoCollapsingToolbarLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.psvmc.zjlearnandroid.MainRecycleView.MainListAdapter;
import cn.psvmc.zjlearnandroid.MainRecycleView.Model.ListItemModel;
import cn.psvmc.zjlearnandroid.MainRecycleView.RecycleViewDivider;
import cn.psvmc.zjlearnandroid.R;

public class ZJCollapsingToolbarLayoutActivity extends AppCompatActivity {
    Context context = this;
    private RecyclerView mRecyclerView;
    private MainListAdapter mListAdapter;
    private List<ListItemModel> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zjcollapsing_toolbar_layout);
        initView();
        initRecycleView();
    }

    private void initView(){
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setTitle("Mr.张");
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色

    }

    private void initRecycleView(){
        mDatas.remove(mDatas);

        for(int i = 0;i<10;i++){
            mDatas.add(new ListItemModel(
                    "tag"+i,
                    "名称"+i,
                    "我是简介"+i
            ));
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mListAdapter = new MainListAdapter(this, mDatas);
        mRecyclerView.setAdapter(mListAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.VERTICAL));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
