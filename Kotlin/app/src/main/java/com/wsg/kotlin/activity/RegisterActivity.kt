package com.wsg.kotlin.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.bean.User


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
        val userName = name.text
        val pass = password.text
        val passAgin = passwordAgain.text
        val m = emile.text


        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(passAgin) || TextUtils.isEmpty(m)){
            Toast.makeText(this,"输入框不能为空，请检查",Toast.LENGTH_SHORT)
        }else{
            //kotlin == 判断内容是否相等 === 判断引用是否相等
            if(pass != passAgin){
                Toast.makeText(this,"两次密码输入不一致，请检查",Toast.LENGTH_SHORT)
            }else{
                //开始注册用户
            }
        }
    }
}