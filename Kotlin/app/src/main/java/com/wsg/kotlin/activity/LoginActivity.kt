package com.wsg.kotlin.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.wsg.kotlin.MainActivity
import com.wsg.kotlin.R
import com.wsg.kotlin.Util.Constant
import com.wsg.kotlin.Util.SpUtils
import com.wsg.kotlin.Util.sendMessage
import com.wsg.kotlin.Util.toast
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.bean.User
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

    lateinit var etName : EditText
    lateinit var etPassword : EditText
    lateinit var btLogin : Button
    lateinit var btRegister : Button
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        etName = findViewById(R.id.et_name)
        etPassword = findViewById(R.id.et_password)
        btLogin = findViewById(R.id.bt_login)
        btRegister = findViewById(R.id.bt_registered)

        btLogin.setOnClickListener(View.OnClickListener { toLogin() })
        btRegister.setOnClickListener(View.OnClickListener { toRegister() })
    }

    //去注册
    private fun toRegister() {
        startActivity(intentFor<RegisterActivity>())
    }

    //登录逻辑
    private fun toLogin() {
        val name = etName.text.toString()
        val pass = etPassword.text.toString()

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)){
            toast(getString(R.string.textnotnull))
        }else {
            progressDialog("请稍候...", "登录中")
            user = User()
            user.username= name
            user.setPassword(pass)

            doAsync {
                user.login(object :SaveListener<User>(){
                    override fun done(p0: User?, p1: BmobException?) {
                        if(p1 == null){
                            SpUtils.putString(applicationContext,SpUtils.name,name)
                            SpUtils.putString(applicationContext,SpUtils.passWord,pass)
                            sendMessage(Constant.loginSuccess)
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