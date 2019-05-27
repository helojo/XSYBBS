package com.wsg.kotlin.fragment


import com.hyphenate.easeui.ui.EaseContactListFragment
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseFragment


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.fragment
 *  文件名:   FriendFragment
 *  创建者:   wsg
 *  创建时间: 2019/5/25 11:04
 *  描述:     我的好友
 */

class FriendFragment : EaseContactListFragment(){

    override fun initView() {
        super.initView()
        query.isEnabled = false

    }

    override fun setUpView() {
        super.setUpView()

    }
}