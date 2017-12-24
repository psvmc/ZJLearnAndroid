package cn.psvmc.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object ZJInputUtils {
    fun hideInput(activity: Activity) {
        //得到InputMethodManager的实例
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive) {
            //如果开启
            imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
        }
    }
}