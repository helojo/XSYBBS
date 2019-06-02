package com.wsg.kotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wsg.kotlin.R
import com.wsg.kotlin.activity.AddNoteActivity
import com.wsg.kotlin.activity.SearchNoteActivity
import com.wsg.kotlin.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_note.*
import org.jetbrains.anko.support.v4.intentFor

/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.fragment
 *  文件名:   NoteFragment
 *  创建者:   wsg
 *  创建时间: 2019/5/25 11:01
 *  描述:     帖子
 */

class NoteFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_note, null)
       // initView(view)
        return view
    }

    private fun initView(view: View?) {
        et_note.setOnClickListener   { startActivity(intentFor<SearchNoteActivity>()) }
        iv_search.setOnClickListener { startActivity(intentFor<SearchNoteActivity>()) }
        iv_post.setOnClickListener   { startActivity(intentFor<AddNoteActivity>()) }
    }
}