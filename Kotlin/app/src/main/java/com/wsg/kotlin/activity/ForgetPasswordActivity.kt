package com.wsg.kotlin.activity

import android.os.Bundle
import android.text.TextUtils
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.util.Constant
import com.wsg.kotlin.util.sendMessage
import com.wsg.kotlin.util.toast
import kotlinx.android.synthetic.main.activity_forgetpassword.*
import org.jetbrains.anko.doAsync


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   ForgetPasswordActivity
 *  创建者:   wsg
 *  创建时间: 2019/6/17 11:03
 *  描述:     忘记密码界面
 */

class ForgetPasswordActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgetpassword)

        btn_forget_password.setOnClickListener {
            val s = et_email.text.toString()
            if(!TextUtils.isEmpty(s)){
                doAsync {
                    BmobUser.resetPasswordByEmail(s, object :  UpdateListener() {
                        override fun done(p0: BmobException?) {
                         if(p0 === null){
                             sendMessage(Constant.forgetPasswordSuccess)
                         }else{
                             sendMessage(Constant.forgetPasswordFail)
                         }
                        }
                    })
                }

            }else{
                toast("亲，输入框不能为空哦")
            }
        }

    }

    override fun msgManagement(message: Int) {
        super.msgManagement(message)
        when(message){
            Constant.forgetPasswordSuccess -> {
                toast("重置密码请求成功，请到邮箱进行密码重置操作")
            }
            Constant.modidyPasswordFail -> {
                toast("邮件发送失败，请检查邮箱是否输入正确")
            }
        }
    }
}