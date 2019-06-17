package com.wsg.kotlin.activity

import android.os.Bundle
import android.view.Window
import com.hyphenate.easeui.ui.EaseChatFragment
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseActivity


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   ChatActivity
 *  创建者:   wsg
 *  创建时间: 2019/6/2 15:25
 *  描述:     聊天界面
 */

class ChatActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chat);
        val ease = EaseChatFragment();
        ease.arguments = intent.extras
        supportFragmentManager.beginTransaction().add(R.id.layout_chat,ease).commit()
    }
}