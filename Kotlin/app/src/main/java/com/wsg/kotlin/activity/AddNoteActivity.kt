package com.wsg.kotlin.activity

import android.os.Bundle
import android.text.TextUtils
import android.widget.RadioButton
import android.widget.RadioGroup
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.bean.Note
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import com.wsg.kotlin.bean.User
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.wsg.kotlin.util.Constant
import com.wsg.kotlin.util.sendMessage
import kotlinx.android.synthetic.main.activity_addnote.*
import org.jetbrains.anko.doAsync


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   AddNoteActivity
 *  创建者:   wsg
 *  创建时间: 2019/5/25 17:34
 *  描述:     发布帖子
 */

class AddNoteActivity :BaseActivity() {

    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnote)
        initView()
    }

    private fun initView() {


        type = "吐槽"

        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                val check : RadioButton = find(checkedId)
                type = check.text.toString().toString().trim()
            }
        })

        newnote_post.setOnClickListener { newNote() }
    }

    private fun newNote() {
        var title = newnote_title.text.toString().trim()
        var content = newnote_content.text.toString()
        if(TextUtils.isEmpty(title) || TextUtils.isEmpty(content)){
            toast("亲，输入框不能为空，请重新输入")
        }else{
            val note = Note(BmobUser.getCurrentUser<User>(User::class.java).objectId,
                "",type,0,title,content,0,0)

            doAsync {
                note.save(object : SaveListener<String>(){
                    override fun done(p0: String?, p1: BmobException?) {
                        if(p1 != null){
                            sendMessage(Constant.postSuccess)
                        }else{
                            sendMessage(Constant.postFail)
                        }
                    }

                })
            }
        }
    }

    override fun msgManagement(message: Int) {
        super.msgManagement(message)
        when(message){
            Constant.postSuccess ->{
                toast("发布成功")
                finish()
            }
            Constant.postFail ->{
                toast("发布失败，请检查网络")
            }
        }
    }
}