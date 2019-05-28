package com.wsg.kotlin.activity

import android.os.Bundle
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_mymessage.*


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   MyMessageActivity
 *  创建者:   wsg
 *  创建时间: 2019/5/28 17:35
 *  描述:     use
 */

class MyMessageActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mymessage)
        rl_zan.setOnClickListener {  }
        rl_comment.setOnClickListener {  }
    }
}