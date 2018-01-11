package cn.psvmc.zjlearnandroid.DemoTreeView

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import cn.psvmc.zjlearnandroid.MainRecycleView.RecycleViewDivider
import cn.psvmc.zjlearnandroid.R
import kotlinx.android.synthetic.main.activity_tree_view.*

class TreeViewActivity : AppCompatActivity(), TreeViewListAdapter.OnItemClickListener {
    var TAG = "TreeViewActivity"


    private var mListAdapter: TreeViewListAdapter? = null
    private val mDatas = ArrayList<TreeItemModel>()
    private var context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree_view)
        this.initBack()
        this.initRecycleView()
    }

    /**
     * 初始化后退操作
     */
    private fun initBack() {
        back_layout.setOnClickListener({
            onBackPressed()
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.close_enter, R.anim.close_exit)
    }

    private fun initRecycleView() {
        mListAdapter = TreeViewListAdapter(context, mDatas)
        mRecyclerView!!.adapter = mListAdapter

        mRecyclerView!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        //mRecyclerView!!.addItemDecoration(RecycleViewDivider(context, LinearLayoutManager.VERTICAL))
        // 设置item动画
        mRecyclerView!!.itemAnimator = DefaultItemAnimator()
        mListAdapter!!.setOnItemClickListener(this)
        this.reloadData()
    }

    private fun reloadData() {
        mDatas.removeAll(mDatas)

        var model01 = TreeItemModel(1, "语文", 0,
                arrayListOf<TreeItemModel>(
                        TreeItemModel(6, "第1章", 1,
                                arrayListOf<TreeItemModel>(
                                        TreeItemModel(10, "第1节", 2, null),
                                        TreeItemModel(11, "第2节", 2, null),
                                        TreeItemModel(12, "第3节", 2, null),
                                        TreeItemModel(13, "第4节", 2, null)
                                )
                        ),
                        TreeItemModel(7, "第2章", 1,
                                arrayListOf<TreeItemModel>(
                                        TreeItemModel(14, "第1节", 2, null),
                                        TreeItemModel(15, "第2节", 2, null),
                                        TreeItemModel(16, "第3节", 2, null),
                                        TreeItemModel(17, "第4节", 2, null)
                                )
                        ),
                        TreeItemModel(8, "第3章", 1, null),
                        TreeItemModel(9, "第4章", 1, null)
                )
        )
        var model02 = TreeItemModel(2, "数学", 0, null)
        var model03 = TreeItemModel(3, "英语", 0, arrayListOf<TreeItemModel>(
                TreeItemModel(36, "第1章", 1,
                        arrayListOf<TreeItemModel>(
                                TreeItemModel(20, "第1节", 2, null),
                                TreeItemModel(21, "第2节", 2, null),
                                TreeItemModel(22, "第3节", 2, null),
                                TreeItemModel(23, "第4节", 2, null)
                        )
                ),
                TreeItemModel(37, "第2章", 1,
                        arrayListOf<TreeItemModel>(
                                TreeItemModel(24, "第1节", 2, null),
                                TreeItemModel(25, "第2节", 2, null),
                                TreeItemModel(26, "第3节", 2, null),
                                TreeItemModel(27, "第4节", 2, null)
                        )
                )
        )
        )

        mDatas.add(model01)
        mDatas.add(model02)
        mDatas.add(model03)

        mListAdapter!!.notifyDataSetChanged()
    }

    override fun onItemClick(view: View, position: Int) {
        var itemData = this.mDatas.get(position);
        Log.i(TAG, "position：" + position)
        Log.i(TAG, "数据的ID：" + itemData.id + " name：" + itemData.name)
        if (itemData.childArr != null) {
            itemData.isexpand = !itemData.isexpand;
            mListAdapter!!.update(position)
            if (itemData.isexpand) {
                mListAdapter!!.add(position + 1, itemData.childArr!!)
            } else {
                mListAdapter!!.removeSon(position)
            }
        } else {
            Log.i(TAG, "子项为空")
        }

    }

    override fun onCheckClick(position: Int) {

    }
}
