package com.wsg.kotlin.activity

import android.os.Bundle
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseActivity


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   MyMessageCommentActivity
 *  创建者:   wsg
 *  创建时间: 2019/6/1 16:03
 *  描述:     我收到的评论
 */

class MyMessageCommentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mycomment)
    }
}