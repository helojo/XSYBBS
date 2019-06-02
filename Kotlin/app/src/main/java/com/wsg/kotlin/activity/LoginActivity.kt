package com.wsg.kotlin.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import com.wsg.kotlin.MainActivity
import com.wsg.kotlin.R
import com.wsg.kotlin.util.Constant
import com.wsg.kotlin.util.SpUtils
import com.wsg.kotlin.util.sendMessage
import com.wsg.kotlin.util.toast
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.bean.User
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.progressDialog


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   LoginActivity
 *  创建者:   wsg
 *  创建时间: 2019/5/13 11:54
 *  描述:     登录界面
 */

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        bt_login.setOnClickListener        { toLogin() }
        bt_registered.setOnClickListener   {  startActivity(intentFor<RegisterActivity>()) }
    }

    //登录逻辑
    private fun toLogin() {
        val name = et_name.text.toString()
        val pass = et_password.text.toString()

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)){
            toast(getString(R.string.textnotnull))
        }else {
            var user = User()
            user.username= name
            user.setPassword(pass)

            doAsync {
                user.login(object :SaveListener<User>(){
                    override fun done(p0: User?, p1: BmobException?) {
                        if(p1 == null){
                            EMClient.getInstance().login(name,p0!!.objectId,object : EMCallBack{
                                override fun onSuccess() {
                                    EMClient.getInstance().groupManager().loadAllGroups();
                                    EMClient.getInstance().chatManager().loadAllConversations();
                                    SpUtils.putString(applicationContext,SpUtils.name,name)
                                    SpUtils.putString(applicationContext,SpUtils.passWord,pass)
                                    sendMessage(Constant.loginSuccess)
                                }

                                override fun onProgress(progress: Int, status: String?) {}

                                override fun onError(code: Int, error: String?) {
                                    sendMessage(Constant.loginFail)
                                }
                            })
                        }else{
                            sendMessage(Constant.loginFail)
                        }
                    }
                })
            }

        }

    }

    override fun msgManagement(message: Int) {
        super.msgManagement(message)
        when(message){
            Constant.loginSuccess ->{
                startActivity(intentFor<MainActivity>())
                finish()
            }
            Constant.loginFail ->{
                toast(getString(R.string.loginfail))
            }
        }
    }

}