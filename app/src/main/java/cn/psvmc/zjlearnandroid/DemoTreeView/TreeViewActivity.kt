package cn.psvmc.zjlearnandroid.DemoTreeView

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cn.psvmc.zjlearnandroid.R
import kotlinx.android.synthetic.main.activity_tree_view.*

class TreeViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree_view)
        this.initBack()
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
}
