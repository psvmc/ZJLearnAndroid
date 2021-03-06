package cn.psvmc.zjlearnandroid.MainRecycleView;

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

import cn.psvmc.zjlearnandroid.DemoChat.C.ChatActivity;
import cn.psvmc.zjlearnandroid.DemoCollapsingToolbarLayout.ZJCollapsingToolbarLayoutActivity;
import cn.psvmc.zjlearnandroid.DemoDrawerLayout.ZJDrawerLayoutActivity;
import cn.psvmc.zjlearnandroid.DemoLogin.LoginActivity;
import cn.psvmc.zjlearnandroid.DemoSysDialog.SysDialogActivity;
import cn.psvmc.zjlearnandroid.DemoDialog.ZJDialogActivity;
import cn.psvmc.zjlearnandroid.DemoFloatingActionButton.FloatingActionButtonActivity;
import cn.psvmc.zjlearnandroid.DemoPopupWindow.ZJPopWindowActivity;
import cn.psvmc.zjlearnandroid.DemoRecycleViewWithHeaderAndFooter.C.FileListActivity;
import cn.psvmc.zjlearnandroid.DemoTabLayout.Activity.TabLayoutActivity;
import cn.psvmc.zjlearnandroid.DemoTabbar.C.TabbarActivity;
import cn.psvmc.zjlearnandroid.DemoTabbar2.C.Tabbar2Activity;
import cn.psvmc.zjlearnandroid.DemoTextInputLayout.ZJTextInputLayoutActivity;
import cn.psvmc.zjlearnandroid.DemoToolbar.ToolbarActivity;
import cn.psvmc.zjlearnandroid.DemoTreeView.TreeViewActivity;
import cn.psvmc.zjlearnandroid.DemoUserbook.UserbookActivity;
import cn.psvmc.zjlearnandroid.MainRecycleView.Model.ListItemModel;
import cn.psvmc.zjlearnandroid.R;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    Activity activity = this;

    private RecyclerView mRecyclerView;
    private MainListAdapter mListAdapter;
    private List<ListItemModel> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("RecyclerView的使用");
        mDatas.remove(mDatas);


        mDatas.add(new ListItemModel(
                "TreeView",
                "树状菜单",
                "用到了Kotlin和RecycleView"
        ));

        mDatas.add(new ListItemModel(
                "Login",
                "登陆页",
                "用到了Kotlin和ConstraintLayout"
        ));

        mDatas.add(new ListItemModel(
                "Userbook",
                "通讯录",
                "类似微信通讯录"
        ));
        
        mDatas.add(new ListItemModel(
                "Toolbar_Snackbar",
                "Toolbar和Snackbar",
                "使用Toolbar和Snackbar"
        ));

        mDatas.add(new ListItemModel(
                "tabbar01",
                "Tabbar方式一",
                "RadioGroup + ViewPager + Fragment \n加载相邻的Fragment"
        ));
        mDatas.add(new ListItemModel(
                "tabbar02",
                "Tabbar方式二",
                "FragmentTabHost + Fragment \n加载选中的Fragment"
        ));
        mDatas.add(new ListItemModel(
                "FloatingActionButton",
                "FloatingActionButton",
                "悬浮按钮"
        ));
        mDatas.add(new ListItemModel(
                "RecycleView",
                "RecycleView",
                "附带Header和Footer \n集成自动下拉刷新 \n实现Item的展开折叠"
        ));

        mDatas.add(new ListItemModel(
                "SysDialog",
                "SysDialog",
                "系统自带的弹窗 \n包含确认取消 进度条等"
        ));

        mDatas.add(new ListItemModel(
                "PopWindow",
                "PopWindow",
                "自定义弹出层(PopWindow实现)"
        ));

        mDatas.add(new ListItemModel(
                "Dialog",
                "Dialog",
                "自定义弹出层(Dialog实现)"
        ));


        mDatas.add(new ListItemModel(
                "TabLayout",
                "TabLayout",
                "Tab页"
        ));

        mDatas.add(new ListItemModel(
                "DrawerLayout",
                "DrawerLayout",
                "侧滑菜单 \n使用了NavigationView"
        ));

        mDatas.add(new ListItemModel(
                "TextInputLayout",
                "TextInputLayout",
                "输入提示"
        ));

        mDatas.add(new ListItemModel(
                "CollapsingToolbarLayout",
                "CollapsingToolbarLayout",
                "响应式Toobar效果 \nAppBarLayout + CollapsingToolbarLayout + Toolbar"
        ));

        mDatas.add(new ListItemModel(
                "Chat",
                "Chat",
                "聊天页面的简单实现"
        ));


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mListAdapter = new MainListAdapter(this, mDatas);
        mRecyclerView.setAdapter(mListAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.VERTICAL));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mListAdapter.setOnItemClickListener(new MainListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent myIntent = new Intent();
                myIntent.putExtra("title", "我的分享");
                String tag = mDatas.get(position).getTag();
                if (tag.equals("tabbar01")) {
                    myIntent.setClass(context, TabbarActivity.class);
                } else if (tag.equals("tabbar02")) {
                    myIntent.setClass(context, Tabbar2Activity.class);
                } else if (tag.equals("FloatingActionButton")) {
                    myIntent.setClass(context, FloatingActionButtonActivity.class);
                } else if (tag.equals("RecycleView")) {
                    myIntent.setClass(context, FileListActivity.class);
                } else if (tag.equals("SysDialog")) {
                    myIntent.setClass(context, SysDialogActivity.class);
                } else if (tag.equals("PopWindow")) {
                    myIntent.setClass(context, ZJPopWindowActivity.class);
                } else if (tag.equals("Dialog")) {
                    myIntent.setClass(context, ZJDialogActivity.class);
                } else if (tag.equals("Toolbar_Snackbar")) {
                    myIntent.setClass(context, ToolbarActivity.class);
                } else if (tag.equals("TabLayout")) {
                    myIntent.setClass(context, TabLayoutActivity.class);
                } else if (tag.equals("DrawerLayout")) {
                    myIntent.setClass(context, ZJDrawerLayoutActivity.class);
                } else if (tag.equals("TextInputLayout")) {
                    myIntent.setClass(context, ZJTextInputLayoutActivity.class);
                } else if (tag.equals("CollapsingToolbarLayout")) {
                    myIntent.setClass(context, ZJCollapsingToolbarLayoutActivity.class);
                } else if (tag.equals("Chat")) {
                    myIntent.setClass(context, ChatActivity.class);
                } else if (tag.equals("Userbook")) {
                    myIntent.setClass(context, UserbookActivity.class);
                } else if(tag.equals("Login")){
                    myIntent.setClass(context, LoginActivity.class);
                } else if(tag.equals("TreeView")){
                    myIntent.setClass(context, TreeViewActivity.class);
                }

                startActivity(myIntent);
                activity.overridePendingTransition(R.anim.open_enter, R.anim.open_exit);

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

}
