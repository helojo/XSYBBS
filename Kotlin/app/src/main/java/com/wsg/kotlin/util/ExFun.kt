package com.wsg.kotlin.util

import android.widget.Toast
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.base.BaseFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.Util
 *  文件名:   ExFun
 *  创建者:   wsg
 *  创建时间: 2019/5/21 10:25
 *  描述:     扩展函数
 */

fun BaseActivity.toast(s : String){
    Toast.makeText(applicationContext,s,Toast.LENGTH_SHORT).show()
}

fun BaseFragment.toast(s : String){
    Toast.makeText(activity!!.applicationContext,s,Toast.LENGTH_SHORT).show()
}

//使用rxjava 代替Handler 立刻发送消息
fun BaseActivity.sendMessage(m : Int){
    Observable
        .empty<Any>()
        .observeOn(AndroidSchedulers.mainThread())
        .doOnComplete({ msgManagement(m) })
        .subscribe()
}
//使用rxjava 代替Handler 延迟发送消息
fun BaseActivity.sendMessageDelayed(m :Int ,d : Long){
    Observable
        .timer(d, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .doOnComplete { msgManagement(m) }
        .subscribe()
}