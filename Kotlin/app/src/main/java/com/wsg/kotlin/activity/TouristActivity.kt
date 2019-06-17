package com.wsg.kotlin.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.wsg.kotlin.R
import com.wsg.kotlin.adapter.GlideImageLoader
import com.wsg.kotlin.adapter.TouristNoteAdapter
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.bean.Note
import com.wsg.kotlin.util.Constant
import com.wsg.kotlin.util.sendMessage
import com.wsg.kotlin.view.recyclerview.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_tourist.*
import org.jetbrains.anko.doAsync


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   TouristActivity
 *  创建者:   wsg
 *  创建时间: 2019/6/17 11:00
 *  描述:     游客浏览
 *
 */

class TouristActivity : BaseActivity(){

    private lateinit var items: ArrayList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tourist)
        initView()
        initData()
    }

    private fun initView() {
        banner.setImageLoader(GlideImageLoader())
        banner.setImages(listOf(Constant.IMAGEONE,Constant.IMAGETWO,Constant.IMAGETHREE,Constant.IMAGEFOUR))
        banner.start()
    }

    private fun initData() {
        doAsync {
            val q = BmobQuery<Note>()
            q.setLimit(10)
            q.order("-top")
            q.findObjects(object : FindListener<Note>(){
                override fun done(p0: MutableList<Note>?, p1: BmobException?) {
                        items = ArrayList()
                        items.addAll(p0!!)
                        sendMessage(Constant.touristNoteSuccess)
                }
            })
        }
    }

    override fun msgManagement(message: Int) {
        super.msgManagement(message)
        when(message){
            Constant.touristNoteSuccess -> {
                var adapter = TouristNoteAdapter(this,items)

                rvt_note.layoutManager = LinearLayoutManager(this)
                rvt_note.addItemDecoration(DividerItemDecoration(this,
                    DividerItemDecoration.VERTICAL_LIST))
                rvt_note.adapter = adapter
            }
        }
    }
}