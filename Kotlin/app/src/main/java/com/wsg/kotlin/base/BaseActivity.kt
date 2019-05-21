package com.wsg.kotlin.base

import android.os.Bundle
import android.os.Message
import android.support.v7.app.AppCompatActivity


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.base
 *  文件名:   BaseActivity
 *  创建者:   wsg
 *  创建时间: 2019/5/13 10:54
 *  描述:     全局Activity 封装
 */

open class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //处理异步消息
    open fun msgManagement(message: Int){

    }
}
 
 