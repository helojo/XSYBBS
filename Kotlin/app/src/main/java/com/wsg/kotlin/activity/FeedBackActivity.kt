package com.wsg.kotlin.activity

import android.os.Bundle
import android.text.TextUtils
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.wsg.kotlin.R
import com.wsg.kotlin.util.Constant
import com.wsg.kotlin.util.sendMessage
import com.wsg.kotlin.util.toast
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.bean.Feedback
import com.wsg.kotlin.util.L
import kotlinx.android.synthetic.main.activity_feedback.*
import org.jetbrains.anko.doAsync


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   FeedBackActivity
 *  创建者:   wsg
 *  创建时间: 2019/5/27 14:53
 *  描述:     反馈界面
 */

class FeedBackActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        bt_back.setOnClickListener { saveFeed() }
    }

    private fun saveFeed() {
        if (!TextUtils.isEmpty(et_back.text.toString())){
            val b = Feedback(et_back.text.toString(),"android",BmobUser.getCurrentUser().objectId)
            doAsync {
                b.save(object : SaveListener<String>(){
                    override fun done(p0: String?, p1: BmobException?) {
                        if(p1 != null){
                            sendMessage(Constant.feedbackSuccess)
                        }else{
                            L.d(p1!!.toString())
                            L.d(p1!!.cause.toString())
                            L.d(p1!!.errorCode.toString())
                            sendMessage(Constant.feedbackFail)
                        }
                    }
                })
            }
        }else{
            toast("亲，输入内容不能为空~")
        }
    }

    override fun msgManagement(message: Int) {
        super.msgManagement(message)
        when(message){
            Constant.feedbackSuccess -> {
                toast("反馈成功")
                finish()
            }
            Constant.feedbackFail -> {
                toast("反馈失败，请检查网络")
            }
        }
    }
}