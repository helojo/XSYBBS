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

class MineFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mine, null)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        profile_image.setOnClickListener(this)
        edit_user.setOnClickListener(this)
        tv_sell.setOnClickListener(this)
        tv_message.setOnClickListener(this)
        tv_modify.setOnClickListener(this)
        tv_back.setOnClickListener(this)
        tv_about.setOnClickListener(this)
        tv_update.setOnClickListener(this)
        tv_sign_out.setOnClickListener(this)
        tv_exit_system.setOnClickListener(this)

        val user = BmobUser.getCurrentUser()
        et_name.setText(user.username)
        et_age.setText("20")
        et_sex.setText("男")
        et_desc.setText("这个人很懒，什么都没留下")
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

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.profile_image ,
            R.id.edit_user ->      { startActivity(intentFor<ModifyPersionalInformationActivity>()) }
            R.id.tv_sell ->        { startActivity(intentFor<MyNoteActivity>()) }
            R.id.tv_message ->     { startActivity(intentFor<MyMessageActivity>()) }
            R.id.tv_modify ->      { startActivity(intentFor<ModifyPasswordActivity>()) }
            R.id.tv_back ->        { startActivity(intentFor<FeedBackActivity>()) }
            R.id.tv_about ->       { startActivity(intentFor<AboutActivity>()) }
            R.id.tv_update ->      { startActivity(intentFor<UpDateActivity>()) }
            R.id.tv_sign_out ->    { logout() }
            R.id.tv_exit_system -> { System.exit(0) }
        }
    }

    private fun logout() {
        BmobUser.logOut()
        EMClient.getInstance().logout(true)
        startActivity(intentFor<LoginActivity>())
        activity!!.finish()
    }
}