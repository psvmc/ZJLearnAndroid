package cn.psvmc.zjlearnandroid.DemoTreeView

/**
 * Created by zhangjian on 2018/1/10.
 */

data class TreeItemModel(var id: Int,
                         val name: String,
                         var level: Int,
                         val childArr: ArrayList<TreeItemModel>?,
                         var isselect: Boolean,
                         var isexpand: Boolean) {

    constructor(id: Int,
                name: String,
                level: Int,
                childArr: ArrayList<TreeItemModel>?) : this(id, name, level, childArr, false, false)
}
