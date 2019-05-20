package com.wsg.kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseActivity


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
    }

    //登录逻辑
    private fun toLogin() {
    }

}