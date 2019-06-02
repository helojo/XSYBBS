package com.wsg.kotlin.fragment


import com.hyphenate.easeui.ui.EaseContactListFragment
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseFragment
import com.hyphenate.easeui.domain.EaseUser
import android.widget.AdapterView
import android.view.View
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.noButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.yesButton


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
        listView.onItemLongClickListener = object : AdapterView.OnItemLongClickListener {
            override fun onItemLongClick(parent: AdapterView<*>, view: View, position: Int, id: Long): Boolean {
                var easeUser = listView.getItemAtPosition(position) as EaseUser
                alert("删除好友", "你确定要删除好友嘛") {
                    yesButton { removeFriend(easeUser) }
                    cancelButton { }
                }.show()
                return true
            }
        }
    }

    private fun removeFriend(easeUser: EaseUser) {
        try {
            EMClient.getInstance().contactManager().deleteContact(easeUser.getUsername());
            toast("删除好友成功")
            //刷新页面
            contactsMap.remove(easeUser.getUsername());
            refresh();
        }catch (e : Exception){
            e.printStackTrace()
            toast("好友删除失败，请检查网络")
        }
        refresh();
    }

}