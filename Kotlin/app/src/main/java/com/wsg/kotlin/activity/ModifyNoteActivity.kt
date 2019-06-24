package com.wsg.kotlin.activity

import android.os.Bundle
import android.widget.RadioButton
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.bean.Note
import kotlinx.android.synthetic.main.activity_modify_note.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   ModifyNoteActivity
 *  创建者:   wsg
 *  创建时间: 2019/6/21 19:06
 *  描述:     修改帖子
 */

class ModifyNoteActivity : BaseActivity() {

    private lateinit var note : Note
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_note)
        note = intent.getSerializableExtra("note") as Note
        initView()
    }

    private fun initView() {
        modify_note_title.setText(note.title)
        modify_note_content.setText(note.content)
        var i = 0
        while (i < modify_radioGroup.childCount){
            if((modify_radioGroup.getChildAt(i) as RadioButton).text.toString().trim().equals(note.typeid.toString().trim())){
                (modify_radioGroup.getChildAt(i) as RadioButton).isChecked = true
                break
            }
        }
        modify_radioGroup.setOnCheckedChangeListener { group, checkedId ->
            note.typeid = find<RadioButton>(checkedId).text.toString().trim()
        }

        modify_note.setOnClickListener { updateNote() }

    }

    private fun updateNote() {
        note.title = modify_note_title.text.toString()
        note.content = modify_note_content.text.toString()

        note.update(note.objectId,object : UpdateListener(){
            override fun done(p0: BmobException?) {
                runOnUiThread {
                    toast("修改成功")
                    finish()
                }
            }
        })
    }
}