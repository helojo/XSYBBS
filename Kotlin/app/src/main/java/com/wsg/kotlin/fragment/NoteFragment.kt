package com.wsg.kotlin.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.wsg.kotlin.R
import com.wsg.kotlin.activity.AddNoteActivity
import com.wsg.kotlin.activity.SearchNoteActivity
import com.wsg.kotlin.adapter.GlideImageLoader
import com.wsg.kotlin.adapter.NoteAdapter
import com.wsg.kotlin.base.BaseFragment
import com.wsg.kotlin.bean.Banne
import com.wsg.kotlin.bean.Note
import com.wsg.kotlin.util.Constant
import com.wsg.kotlin.view.recyclerview.DividerItemDecoration
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.activity_tourist.*
import kotlinx.android.synthetic.main.fragment_note.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.uiThread

/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.fragment
 *  文件名:   NoteFragment
 *  创建者:   wsg
 *  创建时间: 2019/5/25 11:01
 *  描述:     帖子
 */

class NoteFragment : BaseFragment() {

    private lateinit var banner : Banner
    private lateinit var rv : RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: NoteAdapter
    private  var list = ArrayList<Note>()
    private val listp = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_note, null)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        view!!.find<EditText>(R.id.et_note).setOnClickListener     { startActivity(intentFor<SearchNoteActivity>()) }
        view!!.find<ImageView>(R.id.iv_search).setOnClickListener  { startActivity(intentFor<SearchNoteActivity>()) }
        view!!.find<ImageView>(R.id.iv_post).setOnClickListener    { startActivity(intentFor<AddNoteActivity>())    }

        banner = view!!.find(R.id.banner) as Banner
        banner.setImageLoader(GlideImageLoader())
        banner.setImages(listOf(Constant.IMAGEONE, Constant.IMAGETWO, Constant.IMAGETHREE, Constant.IMAGEFOUR))
        banner.start()
        initBanne()
        rv = view.find(R.id.rv_note) as RecyclerView
        rv.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL_LIST))
        rv.layoutManager = LinearLayoutManager(activity)
        adapter = NoteAdapter(activity!!,list)
        rv.adapter = adapter
        initData()
        swipeRefreshLayout = view!!.find(R.id.swipe_refresh) as SwipeRefreshLayout

        swipeRefreshLayout.setOnRefreshListener {
            initData()
            swipeRefreshLayout.isRefreshing = false
        }

    }

    private fun initData() {
        var q = BmobQuery<Note>()
        q.order("-top")
        q.setLimit(50)
        doAsync {
            q.findObjects(object : FindListener<Note>(){
                override fun done(p0: MutableList<Note>?, p1: BmobException?) {
                    list.clear()
                    list.addAll(p0!!)
                    uiThread {
                        adapter.notifyDataSetChanged()
                    }
                }
            })
        }

    }

    private fun initBanne() {
       var q = BmobQuery<Banne>()
        doAsync {
            q.findObjects(object : FindListener<Banne>(){
                override fun done(p0: MutableList<Banne>?, p1: BmobException?) {
                    p0!!.forEach { listp.add(it.photo) }
                    uiThread {
                        banner.setImages(listp);
                        banner.start();
                    }
                }
            })
        }
    }
}