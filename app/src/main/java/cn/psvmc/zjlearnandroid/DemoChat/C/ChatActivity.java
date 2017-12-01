package cn.psvmc.zjlearnandroid.DemoChat.C;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.psvmc.zjlearnandroid.DemoChat.Adapter.ChatAdapter;
import cn.psvmc.zjlearnandroid.DemoChat.Model.ChatMsgModel;


import cn.psvmc.zjlearnandroid.MainRecycleView.RecycleViewDivider;
import cn.psvmc.zjlearnandroid.R;

public class ChatActivity extends AppCompatActivity implements ChatAdapter.OnItemClickListener{


    private String TAG = "ChatActivity";

    private RecyclerView mRecyclerView;
    private ChatAdapter mListAdapter;
    private List<ChatMsgModel> chatListData = new ArrayList<>();
    RelativeLayout back_layout;

    Context mcontext;

    @Override
    public void onItemClick(View view, int position) {

    }


    interface ZJChatHandlerStatus {
        int endrefresh = 0;
        int reloadData = 1;
        int autoRefresh = 2;
        int connectFail = 999;
    }


    private Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ChatActivity.ZJChatHandlerStatus.endrefresh:

                    break;
                case ChatActivity.ZJChatHandlerStatus.reloadData:
                    mListAdapter.notifyDataSetChanged();
                    break;
                case ChatActivity.ZJChatHandlerStatus.autoRefresh:
                    
                    break;
                case ChatActivity.ZJChatHandlerStatus.connectFail:
                    Snackbar.make(getWindow().getDecorView(), "连接服务器失败！", Snackbar.LENGTH_SHORT).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mcontext = this;
        initRecycleView();
        initBack();

        loadData();

    }

    /**
     * 初始化列表
     */
    private void initRecycleView(){
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        mListAdapter = new ChatAdapter(mcontext, chatListData);
        mRecyclerView.setAdapter(mListAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mcontext, LinearLayoutManager.VERTICAL, false));
        //mRecyclerView.addItemDecoration(new RecycleViewDivider(mcontext, LinearLayoutManager.VERTICAL,false));
        // 设置item动画
        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());
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



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
    }


    //加载数据
    private void loadData() {
        chatListData.removeAll(chatListData);

        chatListData.add(new ChatMsgModel(1,1,2,"小明","","小红",""));
        chatListData.add(new ChatMsgModel(1,2,1,"小红","","小明",""));
        chatListData.add(new ChatMsgModel(2,1,2,"小明","","小红",""));
        chatListData.add(new ChatMsgModel(2,2,1,"小红","","小明",""));
        Message message2 = new Message();
        message2.what = ChatActivity.ZJChatHandlerStatus.reloadData;
        myHandler.sendMessageAtTime(message2, SystemClock.uptimeMillis() + 200);
    }


}
