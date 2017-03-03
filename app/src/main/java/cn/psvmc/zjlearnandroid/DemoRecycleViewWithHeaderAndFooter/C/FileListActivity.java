package cn.psvmc.zjlearnandroid.DemoRecycleViewWithHeaderAndFooter.C;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.psvmc.swiperefreshlayout.AutoSwipeRefreshLayout;
import cn.psvmc.zjlearnandroid.MainRecycleView.RecycleViewDivider;
import cn.psvmc.zjlearnandroid.DemoRecycleViewWithHeaderAndFooter.Adapter.ZJFileListAdapter;
import cn.psvmc.zjlearnandroid.DemoRecycleViewWithHeaderAndFooter.Model.ZJFileModel;
import cn.psvmc.zjlearnandroid.R;

public class FileListActivity extends AppCompatActivity implements ZJFileListAdapter.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener{
    private String TAG = "FileListActivity";

    private RecyclerView mRecyclerView;
    private ZJFileListAdapter mListAdapter;
    private List<ZJFileModel> fileListData = new ArrayList<>();
    private AutoSwipeRefreshLayout mSwipeLayout;
    RelativeLayout back_layout;

    Context mcontext;




    interface ZJHandlerStatus {
        int endrefresh = 0;
        int reloadData = 1;
        int autoRefresh = 2;
        int connectFail = 999;
    }


    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ZJHandlerStatus.endrefresh:
                    mSwipeLayout.setRefreshing(false);
                    break;
                case ZJHandlerStatus.reloadData:
                    mListAdapter.notifyDataSetChanged();
                    break;
                case ZJHandlerStatus.autoRefresh:
                    mSwipeLayout.autoRefresh();
                    break;
                case ZJHandlerStatus.connectFail:
                    Snackbar.make(getWindow().getDecorView(), "连接服务器失败！", Snackbar.LENGTH_SHORT).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);
        mcontext = this;
        initRecycleView();
        initBack();
        initRefresh();
    }

    /**
     * 初始化列表
     */
    private void initRecycleView(){
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        mListAdapter = new ZJFileListAdapter(mcontext, fileListData);
        mRecyclerView.setAdapter(mListAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mcontext, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(mcontext, LinearLayoutManager.VERTICAL,false));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mListAdapter.setOnItemClickListener(this);
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

    /**
     * 初始化下拉刷新
     */
    private void initRefresh(){
        //下拉刷新
        mSwipeLayout = (AutoSwipeRefreshLayout) findViewById(R.id.id_swipe_ly);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(android.R.color.background_light));
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);

        Message message = new Message();
        message.what = ZJHandlerStatus.autoRefresh;
        myHandler.sendMessageAtTime(message, SystemClock.uptimeMillis() + 200);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    //加载数据
    private void loadData() {

        fileListData.removeAll(fileListData);
        fileListData.add(new ZJFileModel("001",0,"软件","",0,"2012-10-10 10:10",1));
        fileListData.add(new ZJFileModel("001",1,"1.jpg","http://www.psvmc.cn/1.jpg",0,"2012-10-10 10:10",0));
        fileListData.add(new ZJFileModel("001",1,"2.rar","http://www.psvmc.cn/2.rar",0,"2012-06-10 02:10",0));
        fileListData.add(new ZJFileModel("001",1,"3.mov","http://www.psvmc.cn/3.mov",0,"2014-10-10 12:10",1));
        fileListData.add(new ZJFileModel("001",0,"音乐","",0,"2016-10-10 10:10",1));
        fileListData.add(new ZJFileModel("001",1,"1.png","http://www.psvmc.cn/1.png",0,"2012-10-10 10:10",0));
        fileListData.add(new ZJFileModel("001",1,"2.zip","http://www.psvmc.cn/2.zip",0,"2012-06-10 02:10",0));
        fileListData.add(new ZJFileModel("001",1,"3.ppt","http://www.psvmc.cn/3.ppt",0,"2014-10-10 12:10",1));
        fileListData.add(new ZJFileModel("001",0,"书籍","",0,"2015-10-10 10:10",1));
        fileListData.add(new ZJFileModel("001",1,"1.mp3","http://www.psvmc.cn/1.mp3",0,"2012-10-10 10:10",0));
        fileListData.add(new ZJFileModel("001",1,"2.mp4","http://www.psvmc.cn/2.mp4",0,"2012-06-10 02:10",0));
        fileListData.add(new ZJFileModel("001",1,"3.txt","http://www.psvmc.cn/3.txt",0,"2014-10-10 12:10",1));
        Message message = new Message();
        message.what = ZJHandlerStatus.endrefresh;
        myHandler.sendMessageAtTime(message, SystemClock.uptimeMillis() + 1200);

        Message message2 = new Message();
        message2.what = ZJHandlerStatus.reloadData;
        myHandler.sendMessageAtTime(message2, SystemClock.uptimeMillis() + 1200);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i(TAG, "onItemClick: "+ position);
    }

    @Override
    public void onLayoutClick(View view, int position) {
        ZJFileModel selectItem = fileListData.get(position);
        if (selectItem.isOpen()) {
            selectItem.setOpen(false);
        } else {
            selectItem.setOpen(true);
        }
        mListAdapter.update(position);

        for (int i = 0; i < fileListData.size(); i++) {
            if (i != position) {
                if (fileListData.get(i).isOpen()) {
                    fileListData.get(i).setOpen(false);
                    mListAdapter.update(i);
                }
            }
        }
    }

    @Override
    public void onShareClick(View view, int position) {
        Snackbar.make(getWindow().getDecorView(), "分享", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onMoveClick(View view, int position) {
        Snackbar.make(getWindow().getDecorView(), "移动", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onStarClick(View view, int position) {
        Snackbar.make(getWindow().getDecorView(), "收藏", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onMoreClick(View view, int position) {
        Snackbar.make(getWindow().getDecorView(), "更多", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSearchClick(View view, String searchText) {
        Snackbar.make(getWindow().getDecorView(), "搜索", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClearSearch() {
        Snackbar.make(getWindow().getDecorView(), "清空搜索", Snackbar.LENGTH_SHORT).show();
    }
}
