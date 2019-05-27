package com.wsg.kotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyphenate.easeui.ui.EaseConversationListFragment
import com.wsg.kotlin.base.BaseFragment


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.fragment
 *  文件名:   MyMessageFragment
 *  创建者:   wsg
 *  创建时间: 2019/5/25 11:05
 *  描述:     我的消息
 */

class MyMessageFragment  : EaseConversationListFragment(){

    override fun initView() {
        super.initView()
        query.isEnabled = false
        initData()
    }

    private fun initData() {

    }
}