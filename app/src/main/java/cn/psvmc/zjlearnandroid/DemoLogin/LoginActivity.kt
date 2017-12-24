package cn.psvmc.zjlearnandroid.DemoLogin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import cn.psvmc.utils.AndroidBug5497Workaround
import cn.psvmc.utils.L
import cn.psvmc.utils.ZJInputUtils
import cn.psvmc.zjlearnandroid.R
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AndroidBug5497Workaround.assistActivity(this)
        this.initBack()

        login_button.setOnClickListener {
            login()
        }



    }

    private fun initBack() {
        back_layout.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.close_enter, R.anim.close_exit)
    }

    private fun login() {
        var loginName = loginNameEditText.text
        var loginPwd = loginPwdEditText.text

        ZJInputUtils.hideInput(this)

        if (loginName.isEmpty()) {
            var snackbar = Snackbar.make(contentView, "用户名不能为空", 1250)
            snackbar.view.setBackgroundResource(R.color.zj_blue)
            snackbar.show()
        } else if (loginPwd.isEmpty()) {
            Snackbar.make(contentView, "密码不能为空", 1250).show()
        } else {
            print("登录")
        }
    }
}
