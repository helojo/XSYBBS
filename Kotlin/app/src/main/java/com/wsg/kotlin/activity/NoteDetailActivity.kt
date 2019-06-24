package com.wsg.kotlin.activity

import android.os.Bundle
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.bean.Note
import kotlinx.android.synthetic.main.activity_note_deatil.*
import org.jetbrains.anko.intentFor


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   NoteDetailActivity
 *  创建者:   wsg
 *  创建时间: 2019/6/17 17:36
 *  描述:     帖子详情
 */

class NoteDetailActivity : BaseActivity(){

    private lateinit var note : Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_deatil)
        note = intent.getSerializableExtra("note") as Note
        initView()
    }

    private fun initView() {
        note_detail_title.text = note.title
        note_detail_type.text = note.typeid
        note_detail_time.text = note.updatedAt
        note_detail_content.text = note.content
        note_detail_zancount.text = note.zancount.toString()
        note_detail_replaycount.text = note.replaycount.toString()

        note_detail_profile.setOnClickListener    { startActivity(intentFor<PersionalDealActivity>("id" to note.userid)) }
        note_detail_zan.setOnClickListener {  }
        bt_note_detail.setOnClickListener         {  }
    }
}