package com.wsg.kotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import cn.bmob.v3.BmobUser
import com.hyphenate.chat.EMClient
import com.wsg.kotlin.R
import com.wsg.kotlin.activity.*
import com.wsg.kotlin.base.BaseFragment
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_mine.*
import org.jetbrains.anko.find
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

    private fun initView(view: View?) {
        view!!.find<CircleImageView>(R.id.profile_image).setOnClickListener  { startActivity(intentFor<ModifyPersionalInformationActivity>()) }
        view!!.find<TextView>(R.id.edit_user).setOnClickListener             { startActivity(intentFor<ModifyPersionalInformationActivity>()) }
        view!!.find<TextView>(R.id.tv_sell).setOnClickListener               { startActivity(intentFor<MyNoteActivity>()) }
        view!!.find<TextView>(R.id.tv_message).setOnClickListener            { startActivity(intentFor<MyMessageActivity>()) }
        view!!.find<TextView>(R.id.tv_modify).setOnClickListener             { startActivity(intentFor<ModifyPasswordActivity>()) }
        view!!.find<TextView>(R.id.tv_back).setOnClickListener               { startActivity(intentFor<FeedBackActivity>()) }
        view!!.find<TextView>(R.id.tv_about).setOnClickListener              { startActivity(intentFor<AboutActivity>()) }
        view!!.find<TextView>(R.id.tv_update).setOnClickListener             { startActivity(intentFor<UpDateActivity>()) }
        view!!.find<TextView>(R.id.tv_sign_out).setOnClickListener           { logout() }
        view!!.find<TextView>(R.id.tv_exit_system).setOnClickListener        { System.exit(0) }
    }
    //修改资料页面跳转过来 ->
    override fun onResume() {
        super.onResume()
//        val user = BmobUser.getCurrentUser()
//        tv_username.setText(user.username)
//        tv_sex.setText("20")
//        tv_age.setText("男")
//        tv_desc.setText("这个人很懒，什么都没留下")
    }

    private fun logout() {
        BmobUser.logOut()
        EMClient.getInstance().logout(true)
        startActivity(intentFor<LoginActivity>())
        activity!!.finish()
    }
}