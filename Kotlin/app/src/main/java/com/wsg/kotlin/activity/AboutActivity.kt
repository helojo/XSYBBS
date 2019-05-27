package com.wsg.kotlin.activity

import android.os.Bundle
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseActivity


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   AboutActivity
 *  创建者:   wsg
 *  创建时间: 2019/5/27 15:14
 *  描述:     关于
 */

class AboutActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }
}