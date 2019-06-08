package com.wsg.kotlin.activity

import android.os.Bundle
import android.text.TextUtils
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.bean.User
import com.wsg.kotlin.util.L
import com.wsg.kotlin.util.toast
import kotlinx.android.synthetic.main.activity_modify_information.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   ModifyPersionalInformationActivity
 *  创建者:   wsg
 *  创建时间: 2019/5/28 18:51
 *  描述:     修改个人资料
 */

class ModifyPersionalInformationActivity : BaseActivity() {

    private lateinit var user : BmobUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_information)
        initView()
    }

    private fun initView() {
        user = BmobUser.getCurrentUser()
        et_username.setText(user.username)
        et_sex.setText("男")
        et_age.setText("20")
        et_desc.setText("这个人很懒，什么都没留下")
        btn_update_ok.setOnClickListener { modifyInfo() }
    }

    private fun modifyInfo() {
        if(!TextUtils.isEmpty(et_username.text)){
            user.username = et_username.text.toString()

            doAsync {
                user.update(user.objectId,object : UpdateListener(){
                    override fun done(p0: BmobException?) {
                       if(p0 !== null){
                           uiThread { toast("修改成功");finish() }
                       }else{
                           L.d(p0!!.toString())
                           L.d(p0!!.cause.toString())
                           L.d(p0!!.errorCode.toString())
                           uiThread { toast("修改失败，请检查网络") }
                       }
                    }
                })
            }

        }else{
            toast("输入框不能为空")
        }
    }
}