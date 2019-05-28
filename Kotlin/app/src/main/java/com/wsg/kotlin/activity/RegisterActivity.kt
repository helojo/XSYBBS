package com.wsg.kotlin.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hyphenate.chat.EMClient
import com.wsg.kotlin.R
import com.wsg.kotlin.util.Constant
import com.wsg.kotlin.util.SpUtils
import com.wsg.kotlin.util.sendMessage
import com.wsg.kotlin.util.toast
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.bean.User
import org.jetbrains.anko.doAsync


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   RegisterActivity
 *  创建者:   wsg
 *  创建时间: 2019/5/13 11:57
 *  描述:     注册界面
 */

class RegisterActivity : BaseActivity(){

    lateinit var name : EditText
    lateinit var password : EditText
    lateinit var passwordAgain : EditText
    lateinit var emile : EditText
    lateinit var button :Button
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()
    }

    private fun initView() {
        name = findViewById(R.id.et_user)
        password = findViewById(R.id.et_pass)
        passwordAgain = findViewById(R.id.et_password)
        emile = findViewById(R.id.et_email)
        button = findViewById(R.id.btnRegistered)

        button.setOnClickListener(View.OnClickListener { registerUser() })
    }

    //注册用户
    private fun registerUser() {
        val userName = name.text.toString()
        val pass = password.text.toString()
        val passAgin = passwordAgain.text.toString()
        val m = emile.text.toString()


        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(passAgin) || TextUtils.isEmpty(m)){
            toast(getString(R.string.textnotnull))
        }else{
            //kotlin == 判断内容是否相等 === 判断引用是否相等
            if(pass != passAgin){
                toast(getString(R.string.passagainfail))
            }else{
                //开始注册用户
                user = User()
                user.username = userName
                user.setPassword(pass)
                user.email = m
                doAsync {
                    user.signUp(object :SaveListener<User>(){
                        override fun done(p0: User?, p1: BmobException?) {
                            if(p1 == null){
                                regresterChat(user.getUsername(),user.getObjectId(),userName,pass);
                            }else{
                                sendMessage(Constant.registerFail)
                            }
                        }
                    })
                }

            }
        }
    }

    private fun regresterChat(username: String?, objectId: String?, userName: String, pass: String) {
        EMClient.getInstance().createAccount(username, objectId);//同步方法
        SpUtils.putString(this,SpUtils.userName,userName)
        SpUtils.putString(this,SpUtils.passWord,pass)
        sendMessage(Constant.registerSucess)
    }

    override fun msgManagement(message: Int) {
        super.msgManagement(message)
        when(message){
            Constant.registerSucess ->{
                toast(getString(R.string.registersuccess))
                finish()
            }
            Constant.registerFail ->{
                toast(getString(R.string.registerfail))
            }
        }
    }

}