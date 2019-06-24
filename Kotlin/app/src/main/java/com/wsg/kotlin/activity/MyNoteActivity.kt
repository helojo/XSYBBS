package com.wsg.kotlin.activity

import android.os.Bundle
import android.view.View
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.wsg.kotlin.R
import com.wsg.kotlin.adapter.MyNoteAdapter
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.bean.Note
import com.wsg.kotlin.util.Constant
import com.wsg.kotlin.util.sendMessage
import kotlinx.android.synthetic.main.activity_mynote.*


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   MyNoteActivity
 *  创建者:   wsg
 *  创建时间: 2019/5/28 20:01
 *  描述:     我的帖子
 */

class MyNoteActivity : BaseActivity() {

    private lateinit var list : List<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mynote)
        tv_mynote.text = "正在加载，请稍等..."
        initData()
    }

    private fun initData() {
        val q = BmobQuery<Note>()
        q.addWhereEqualTo("userid",BmobUser.getCurrentUser().objectId)
        q.setLimit(50)
        q.order("-createdAt");
        q.findObjects(object : FindListener<Note>(){
            override fun done(p0: MutableList<Note>?, p1: BmobException?) {
                list = p0!!
                sendMessage(Constant.persionNoteSuccess)
            }
        })
    }

    override fun msgManagement(message: Int) {
        super.msgManagement(message)
        when(message){
            Constant.persionNoteSuccess -> {
                if(list.size == 0){
                }else{
                    tv_mynote.visibility = View.GONE
                    var adapter = MyNoteAdapter(this,list)
                    lv_my_note.adapter = adapter
                }
            }
        }
    }
}