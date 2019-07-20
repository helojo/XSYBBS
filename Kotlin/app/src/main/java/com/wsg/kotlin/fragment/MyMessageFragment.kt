package com.wsg.kotlin.fragment

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyphenate.easeui.ui.EaseConversationListFragment
import com.wsg.kotlin.base.BaseFragment
import java.lang.Exception


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.fragment
 *  文件名:   MyMessageFragment
 *  创建者:   wsg
 *  创建时间: 2019/5/25 11:05
 *  描述:     我的消息
 */

class MyMessageFragment  : EaseConversationListFragment(){

    private lateinit var myTask: MyTask

    override fun initView() {
        super.initView()
        query.isEnabled = false
        initData()
    }

    private fun initData() {
        myTask = MyTask()
        myTask.execute()
    }


    internal inner class MyTask : AsyncTask<Any,Any,Any>(){
        override fun doInBackground(vararg params: Any?){
            val timeInterval = 10000
            while (true){
                conversationListView.refresh();
                try {
                    Thread.sleep(timeInterval.toLong())
                }catch (e : Exception){
                    e.printStackTrace();
                }
            }
        }
    }

    override fun onDestroy() {
        if(myTask != null && myTask.status != AsyncTask.Status.FINISHED){
            myTask.cancel(true)
        }
        super.onDestroy()
    }

}