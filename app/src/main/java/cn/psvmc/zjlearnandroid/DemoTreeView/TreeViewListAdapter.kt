package cn.psvmc.zjlearnandroid.DemoTreeView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import cn.psvmc.utils.DensityUtils
import cn.psvmc.zjlearnandroid.MainRecycleView.Model.ListItemModel
import cn.psvmc.zjlearnandroid.R

open class TreeViewListAdapter(private val context: Context, private val mDatas: ArrayList<TreeItemModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mInflater: LayoutInflater? = null
    private var mOnItemClickListener: OnItemClickListener? = null

    init {
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
                mInflater!!.inflate(R.layout.treeview_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        var itemHolder = holder as ItemViewHolder

        var itemData = mDatas.get(position);
        itemHolder.nameTextView.text = itemData.name
        if (itemData.isselect) {
            itemHolder.checkImageView.setImageResource(R.drawable.check001)
        } else {
            itemHolder.checkImageView.setImageResource(R.drawable.check002)
        }

        if (itemData.isexpand) {
            itemHolder.arrowImageView.setImageResource(R.drawable.arrow_up)
        } else {
            itemHolder.arrowImageView.setImageResource(R.drawable.arrorw_dowm)
        }

        if (itemData.childArr == null) {
            itemHolder.arrowImageView.visibility = View.INVISIBLE
        } else {
            itemHolder.arrowImageView.visibility = View.VISIBLE
        }

        var pT = itemHolder.outerLayout.paddingTop;
        var pB = itemHolder.outerLayout.paddingBottom;
        var pL = itemHolder.outerLayout.paddingLeft;
        var pR = itemHolder.outerLayout.paddingRight;

        itemHolder.outerLayout.setPadding(DensityUtils.dp2px(context,20f)* itemData.level + DensityUtils.dp2px(context,10f),pT,pB,pR);

        itemHolder.itemView.setOnClickListener({
            mOnItemClickListener?.onItemClick(itemHolder.itemView, position);
        })

        itemHolder.checkImageView.setOnClickListener({
            mOnItemClickListener?.onCheckClick(position)
        })
    }

    override fun getItemCount(): Int {
        return mDatas.count()
    }

    fun update(position: Int) {
        var position = position
        if (position > mDatas.size) {
            position = mDatas.size
        }
        if (position < 0) {
            position = 0
        }
        notifyItemChanged(position)
    }

    fun add(position: Int, list: ArrayList<TreeItemModel>) {
        var position = position
        if (position > mDatas.size) {
            position = mDatas.size
        }
        if (position < 0) {
            position = 0
        }
        mDatas.addAll(position, list);
        notifyItemRangeInserted(position, list.size)
        notifyItemRangeChanged(position+list.size-1,mDatas.size-position-list.size+1)
    }

    fun removeSon(position: Int) {
        var position = position
        if (position > mDatas.size) {
            position = mDatas.size
        }
        if (position < 0) {
            position = 0
        }

        var itemData = mDatas.get(position);
        var removedList = ArrayList<TreeItemModel>();
        for (i in position + 1..mDatas.size - 1) {
            if (mDatas.get(i).level > itemData.level) {
                mDatas.get(i).isexpand = false
                removedList.add(mDatas.get(i))
            } else {
                break
            }
        }
        mDatas.removeAll(removedList)
        notifyItemRangeRemoved(position + 1, removedList.size);
        notifyItemRangeChanged(position+1,mDatas.size-position-1)
    }


    internal inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkImageView: ImageView
        val nameTextView: TextView
        val arrowImageView: ImageView
        var outerLayout:LinearLayout

        init {
            checkImageView = itemView.findViewById(R.id.checkImageView) as ImageView
            nameTextView = itemView.findViewById(R.id.nameTextView) as TextView
            arrowImageView = itemView.findViewById(R.id.arrowImageView) as ImageView
            outerLayout = itemView.findViewById(R.id.outerLayout) as LinearLayout
        }
    }

    open fun setOnItemClickListener(mOnItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener
    }

    /**
     * 处理item的点击事件和长按事件
     */
    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onCheckClick(position: Int)
    }


}
