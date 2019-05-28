package com.wsg.kotlin.activity

import android.os.Bundle
import android.text.TextUtils
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.util.Constant
import com.wsg.kotlin.util.SpUtils
import com.wsg.kotlin.util.sendMessage
import com.wsg.kotlin.util.toast
import kotlinx.android.synthetic.main.activity_modify_password.*
import org.jetbrains.anko.doAsync


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   ModifyPasswordActivity
 *  创建者:   wsg
 *  创建时间: 2019/5/28 16:27
 *  描述:     修改密码
 */

class ModifyPasswordActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_password)
        btn_update_password.setOnClickListener { upDatePassWord() }
    }

    private fun upDatePassWord() {
        val old = et_now.text.toString()
        val new = et_new.text.toString()
        val newAgain = et_new_password.text.toString()

        if(!TextUtils.isEmpty(old) || !TextUtils.isEmpty(new) || !TextUtils.isEmpty(newAgain)){
            if(new == newAgain){
                val user = BmobUser.getCurrentUser()
                doAsync {
                    BmobUser.updateCurrentUserPassword(old,new,object : UpdateListener(){
                        override fun done(p0: BmobException?) {
                            if(p0 != null){
                                SpUtils.putString(application,SpUtils.passWord,new)
                                sendMessage(Constant.modifyPasswordSuccess)
                            }else{
                                sendMessage(Constant.modidyPasswordFail)
                            }
                        }
                    })
                }
            }else{
                toast("两次密码输入不一致，请重新输入")
            }
        }else{
            toast("输入框不能为空")
        }
    }

    override fun msgManagement(message: Int) {
        super.msgManagement(message)
        when(message){
            Constant.modifyPasswordSuccess -> {
                toast("密码修改成功")
                finish()
            }
            Constant.modidyPasswordFail ->{
                toast("密码修改失败，请检查网络")
            }
        }
    }
}