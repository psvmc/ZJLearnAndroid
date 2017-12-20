package cn.psvmc.zjlearnandroid.DemoUserbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import cn.psvmc.zjlearnandroid.DemoChat.C.ChatActivity;
import cn.psvmc.zjlearnandroid.DemoCollapsingToolbarLayout.ZJCollapsingToolbarLayoutActivity;
import cn.psvmc.zjlearnandroid.DemoDialog.ZJDialogActivity;
import cn.psvmc.zjlearnandroid.DemoDrawerLayout.ZJDrawerLayoutActivity;
import cn.psvmc.zjlearnandroid.DemoFloatingActionButton.FloatingActionButtonActivity;
import cn.psvmc.zjlearnandroid.DemoPopupWindow.ZJPopWindowActivity;
import cn.psvmc.zjlearnandroid.DemoRecycleViewWithHeaderAndFooter.C.FileListActivity;
import cn.psvmc.zjlearnandroid.DemoSysDialog.SysDialogActivity;
import cn.psvmc.zjlearnandroid.DemoTabLayout.Activity.TabLayoutActivity;
import cn.psvmc.zjlearnandroid.DemoTabbar.C.TabbarActivity;
import cn.psvmc.zjlearnandroid.DemoTabbar2.C.Tabbar2Activity;
import cn.psvmc.zjlearnandroid.DemoTextInputLayout.ZJTextInputLayoutActivity;
import cn.psvmc.zjlearnandroid.DemoToolbar.ToolbarActivity;
import cn.psvmc.zjlearnandroid.MainRecycleView.MainListAdapter;
import cn.psvmc.zjlearnandroid.MainRecycleView.Model.ListItemModel;
import cn.psvmc.zjlearnandroid.MainRecycleView.RecycleViewDivider;
import cn.psvmc.zjlearnandroid.R;

public class UserbookActivity extends AppCompatActivity implements UserbookListAdapter.OnItemClickListener {

    String TAG = "UserbookActivity";

    RelativeLayout back_layout;
    Context context = this;
    Activity activity = this;

    private RecyclerView mRecyclerView;
    private UserbookListAdapter mListAdapter;
    private List<UserbookItemModel> mDatas = new ArrayList<>();

    private QuickAlphabeticBar quickAlphabeticBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userbook);
        initBack();
        initRecycleView();

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


    private void initRecycleView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mListAdapter = new UserbookListAdapter(context, mDatas);
        mRecyclerView.setAdapter(mListAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.VERTICAL));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mListAdapter.setOnItemClickListener(this);

        initQuickBar();

        reloadData();
        reloadQuickBar();
    }

    private void initQuickBar() {
        quickAlphabeticBar = (QuickAlphabeticBar) findViewById(R.id.fast_scroller);
        quickAlphabeticBar.init(activity);
        quickAlphabeticBar.setListView(mRecyclerView);
    }

    private void reloadQuickBar() {
        HashMap<String, Integer> alphaIndexer = new HashMap<>();
        alphaIndexer.put("#",0);
        for (int i = 0; i < mDatas.size(); i++) {
            String firstChar = mDatas.get(i).getFirstChar();
            if (alphaIndexer.get(firstChar) == null) {
                alphaIndexer.put(firstChar,i+1);
            }
        }
        quickAlphabeticBar.setAlphaIndexer(alphaIndexer);
    }

    private void reloadData() {
        mDatas.remove(mDatas);
        mDatas.add(new UserbookItemModel("张剑", "", "Z"));
        mDatas.add(new UserbookItemModel("李四", "", "L"));
        mDatas.add(new UserbookItemModel("王麻子", "", "W"));
        mDatas.add(new UserbookItemModel("赵六", "", "Z"));
        mDatas.add(new UserbookItemModel("尼古拉斯", "", "N"));
        mDatas.add(new UserbookItemModel("小明", "", "X"));
        mDatas.add(new UserbookItemModel("王宁", "", "W"));
        mDatas.add(new UserbookItemModel("张瑞红", "", "Z"));
        mDatas.add(new UserbookItemModel("黄晓婧", "", "H"));
        mDatas.add(new UserbookItemModel("柯南", "", "K"));

        Collections.sort(mDatas);
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i(TAG, "onItemClick: " + position);
    }

}
