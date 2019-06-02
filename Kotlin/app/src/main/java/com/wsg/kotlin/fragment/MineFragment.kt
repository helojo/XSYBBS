package com.wsg.kotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bmob.v3.BmobUser
import com.hyphenate.chat.EMClient
import com.wsg.kotlin.R
import com.wsg.kotlin.activity.*
import com.wsg.kotlin.base.BaseFragment
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_modify_information.*
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.fragment_mine.profile_image
import org.jetbrains.anko.support.v4.intentFor


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.fragment
 *  文件名:   MineFragment
 *  创建者:   wsg
 *  创建时间: 2019/5/25 11:03
 *  描述:     个人中心
 */

class MineFragment : BaseFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mine, null)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        profile_image.setOnClickListener  { startActivity(intentFor<ModifyPersionalInformationActivity>()) }
        edit_user.setOnClickListener      { startActivity(intentFor<ModifyPersionalInformationActivity>()) }
        tv_sell.setOnClickListener        { startActivity(intentFor<MyNoteActivity>()) }
        tv_message.setOnClickListener     { startActivity(intentFor<MyMessageActivity>()) }
        tv_modify.setOnClickListener      { startActivity(intentFor<ModifyPasswordActivity>()) }
        tv_back.setOnClickListener        { startActivity(intentFor<FeedBackActivity>()) }
        tv_about.setOnClickListener       { startActivity(intentFor<AboutActivity>()) }
        tv_update.setOnClickListener      { startActivity(intentFor<UpDateActivity>()) }
        tv_sign_out.setOnClickListener    { logout() }
        tv_exit_system.setOnClickListener { System.exit(0) }
    }
    //修改资料页面跳转过来 ->
    override fun onResume() {
        super.onResume()
        val user = BmobUser.getCurrentUser()
        et_name.setText(user.username)
        et_age.setText("20")
        et_sex.setText("男")
        et_desc.setText("这个人很懒，什么都没留下")
    }

    private fun logout() {
        BmobUser.logOut()
        EMClient.getInstance().logout(true)
        startActivity(intentFor<LoginActivity>())
        activity!!.finish()
    }
}