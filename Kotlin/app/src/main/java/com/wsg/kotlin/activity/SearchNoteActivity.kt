package com.wsg.kotlin.activity

import android.os.Bundle
import android.view.View
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.wsg.kotlin.R
import com.wsg.kotlin.adapter.NoteAdapter
import com.wsg.kotlin.util.Constant
import com.wsg.kotlin.util.sendMessage
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.bean.Note
import kotlinx.android.synthetic.main.activity_searchnote.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   SearchNoteActivity
 *  创建者:   wsg
 *  创建时间: 2019/5/25 19:51
 *  描述:     搜索帖子
 */

class SearchNoteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchnote)
        iv_searchnote.setOnClickListener { searchNote() }
    }

    private fun searchNote() {
        tv_searchnote.visibility = View.VISIBLE
        val s = et_searchnote.text.toString()
        val query = BmobQuery<Note>()
        query.addWhereEqualTo("title", s);
        query.setLimit(10);

        doAsync {
            query.findObjects(object : FindListener<Note>() {
                override fun done(p0: MutableList<Note>?, p1: BmobException?) {
                    if (p1 != null) {
                        if (p0!!.size != 0) {
                            uiThread {
                                tv_searchnote.visibility = View.GONE
                                //初始化数据
                                val adapter = NoteAdapter(this@SearchNoteActivity,p0)
                                rv_searchnote.adapter = adapter
                            }
                        } else {
                            sendMessage(Constant.searchSuccessNoData)
                        }
                    } else {
                        sendMessage(Constant.searchFail)
                    }
                }
            })
        }

    }

    override fun msgManagement(message: Int) {
        super.msgManagement(message)
        when (message) {
            Constant.searchSuccessNoData -> {
                tv_searchnote.text = "查找成功，暂无此类记录"
            }
            Constant.searchFail -> {
                tv_searchnote.text = "查找失败，请检查网络"
            }
        }
    }
}