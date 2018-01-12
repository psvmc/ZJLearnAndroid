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
                        TreeItemModel(6, "家", 1,
                                arrayListOf<TreeItemModel>(
                                        TreeItemModel(10, "第1节", 2, null),
                                        TreeItemModel(11, "第2节", 2, null),
                                        TreeItemModel(12, "第3节", 2, null),
                                        TreeItemModel(13, "第4节", 2, null)
                                )
                        ),
                        TreeItemModel(7, "春", 1,
                                arrayListOf<TreeItemModel>(
                                        TreeItemModel(14, "第1节", 2, null),
                                        TreeItemModel(15, "第2节", 2, null),
                                        TreeItemModel(16, "第3节", 2, null),
                                        TreeItemModel(17, "第4节", 2, null)
                                )
                        ),
                        TreeItemModel(8, "秋", 1, null)
                )
        )
        var model02 = TreeItemModel(2, "数学", 0, arrayListOf<TreeItemModel>(
                TreeItemModel(46, "第1章", 1,null),
                TreeItemModel(47, "第2章", 1, null)
        ))
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
        if(mListAdapter != null){
            if (itemData.childArr != null) {
                itemData.isexpand = !itemData.isexpand;
                mListAdapter!!.update(position)
                if (itemData.isexpand) {
                    mListAdapter!!.addList(position + 1, itemData.childArr!!)
                } else {
                    mListAdapter!!.removeSon(position)
                }
            }
        }


    }

    override fun onCheckClick(position: Int) {
        var itemData = this.mDatas.get(position);
        itemData.isselect = !itemData.isselect;
        mListAdapter!!.update(position)
        if(itemData.isselect){
            selectSon(itemData)
            mListAdapter!!.updateSon(position)
            selectParent(itemData)
        }else{
            unselectSon(itemData)
            mListAdapter!!.updateSon(position)
            unselectParent(itemData)
        }
    }

    //选中所有的子项
    fun selectSon(itemData:TreeItemModel){
        var childArr = itemData.childArr
        if(childArr != null){
            childArr.forEach(fun(item:  TreeItemModel){
                item.isselect = true
                selectSon(item)
            })
        }
    }

    //取消选中所有的子项
    fun unselectSon(itemData:TreeItemModel){
        var childArr = itemData.childArr
        if(childArr != null){
            childArr.forEach(fun(item:  TreeItemModel){
                item.isselect = false
                unselectSon(item)
            })
        }
    }

    //如果子项全部选中该项选中
    fun selectParent(itemData:TreeItemModel){
        var parentItem = getParentItem(itemData)
        if(parentItem != null){
            if(isAllChildCheck(parentItem)){
                parentItem.isselect = true;
                selectParent(parentItem)
                mListAdapter!!.update(getItemPosition(parentItem))
            }
        }
    }

    //如果子项有未选中的取消该项选中
    fun unselectParent(itemData:TreeItemModel){
        var parentItem = getParentItem(itemData)
        if(parentItem != null){
            if(!isAllChildCheck(parentItem)){
                parentItem.isselect = false;
                unselectParent(parentItem)
                mListAdapter!!.update(getItemPosition(parentItem))
            }
        }
    }

    //获取对象的position
    fun getItemPosition(itemData:TreeItemModel):Int{
        for(i in 0.. mDatas.size-1){
            if(mDatas.get(i).equals(itemData)){
                return i;
            }
        }
        return 0;
    }

    //是否所有的子节点都选中了
    fun isAllChildCheck(itemData:TreeItemModel):Boolean{
        var childArr = itemData.childArr
        var result = true;
        if(childArr != null){
            childArr.forEach(fun(item:  TreeItemModel){
                if(!item.isselect){
                    result = false
                    return@forEach
                }
            })
        }
        return result
    }

    //获取父级
    fun getParentItem(itemData:TreeItemModel) : TreeItemModel?{
        for(item in mDatas){
            var childArr = item.childArr
            if(childArr != null){
                for(itemData2 in childArr){
                    if(itemData2.equals(itemData)){
                        return item
                    }
                }
            }

        }
        return null;
    }
}
