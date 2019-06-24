package com.wsg.kotlin.activity

import android.os.Bundle
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import com.hyphenate.chat.EMClient
import com.hyphenate.easeui.EaseConstant
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.bean.User
import kotlinx.android.synthetic.main.activity_persiondetail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.lang.Exception


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   PersionalDealActivity
 *  创建者:   wsg
 *  创建时间: 2019/6/21 17:05
 *  描述:     用户详情
 */

class PersionalDealActivity : BaseActivity() {

    private lateinit var id : String
    private lateinit var user : User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persiondetail)
        initData()
    }

    //初始化数据
    private fun initData() {
        id = intent.getStringExtra("id")
       doAsync {
           var q = BmobQuery<User>()
           q.getObject(id!!,object : QueryListener<User>(){
               override fun done(p0: User?, p1: BmobException?) {
                   user = p0!!
                   uiThread { initView() }
               }
           })
       }
    }

    //初始化视图
    private fun initView() {
        show_name.text = user.username
        show_sex.setImageResource(R.drawable.male)
        show_desc.text = "这个人很懒，什么都没留下~"
        btn_add_friend.setOnClickListener {
            try {
                //参数为要添加的好友的username和添加理由
                EMClient.getInstance().contactManager().addContact(user.username,"你好，很高兴认识你");
                toast("添加好友成功~")
            }catch (e : Exception){
                e.printStackTrace()
                toast("添加好友失败，您和对方已经是好友~")
            }
        }
        btn_send_message.setOnClickListener {
            startActivity(
                intentFor<ChatActivity>
                    (EaseConstant.EXTRA_USER_ID to user.username ,
                    EaseConstant.EXTRA_CHAT_TYPE to EaseConstant.CHATTYPE_SINGLE)
            )
        }
    }
}