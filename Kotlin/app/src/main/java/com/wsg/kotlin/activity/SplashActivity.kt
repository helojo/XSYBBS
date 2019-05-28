package com.wsg.kotlin.activity

import android.os.Bundle
import android.view.WindowManager
import com.wsg.kotlin.R
import com.wsg.kotlin.util.Constant
import com.wsg.kotlin.util.sendMessageDelayed
import com.wsg.kotlin.base.BaseActivity
import org.jetbrains.anko.intentFor


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   SplashActivity
 *  创建者:   wsg
 *  创建时间: 2019/5/13 11:36
 *  描述:     闪屏页
 */

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //设置全屏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //两秒后跳转
        sendMessageDelayed(Constant.splash,2000)
    }


    override fun msgManagement(message: Int) {
        super.msgManagement(message)
        if(Constant.splash == 0){
            startActivity(intentFor<LoginActivity>())
            finish()
        }

    }

}