package com.wsg.kotlin.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.wsg.kotlin.R
import com.wsg.kotlin.util.toast
import com.wsg.kotlin.activity.AddNoteActivity
import com.wsg.kotlin.activity.SearchNoteActivity
import com.wsg.kotlin.adapter.GlideImageLoader
import com.wsg.kotlin.adapter.NoteAdapter
import com.wsg.kotlin.base.BaseFragment
import com.wsg.kotlin.bean.Banne
import com.wsg.kotlin.bean.Note
import com.youth.banner.Banner
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.uiThread


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.fragment
 *  文件名:   NoteFragment
 *  创建者:   wsg
 *  创建时间: 2019/5/25 11:01
 *  描述:     帖子
 */

class NoteFragment : BaseFragment(), View.OnClickListener {

    private lateinit var etSearch: EditText
    private lateinit var ivSearch: ImageView
    private lateinit var ivPost: ImageView
    private lateinit var rvNote: RecyclerView
    private lateinit var banner: Banner
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private var listp = ArrayList<String>()
    private var listNote = ArrayList<Note>()
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_note, null)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        etSearch = find(R.id.et_note)
        etSearch.setOnClickListener(this)
        ivSearch = find(R.id.iv_search)
        ivSearch.setOnClickListener(this)
        ivPost = find(R.id.iv_post)
        ivPost.setOnClickListener(this)
        rvNote = find(R.id.rv_note)
        banner =  find(R.id.banner)
        swipeRefreshLayout = find(R.id.swipe_refresh)

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeRefreshLayout.setOnRefreshListener {
            initData()
            swipeRefreshLayout.isRefreshing = false
        }

        //设置轮播图
        listp.add("http://202.200.82.150/u/cms/www/201806/27104449b14a.jpg");
        listp.add("http://202.200.82.150/u/cms/www/201805/16111826e3zf.jpg");
        listp.add("http://202.200.82.150/u/cms/www/201710/30114208slub.jpg");
        listp.add("http://202.200.82.150/u/cms/www/201806/26174701kiz0.png");

        //设置图片加载器
        banner.setImageLoader(GlideImageLoader())
        //设置图片集合
        banner.setImages(listp)

        //banner设置方法全部调用完毕时最后调用
        banner.start()
        initBanner()

        noteAdapter = getActivity()?.let { NoteAdapter(it, listNote) }!!
        rvNote.adapter = noteAdapter

    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    private fun initBanner() {
        var query = BmobQuery<Banne>()
        query.setLimit(10)
        query.order("-createdAt")
        doAsync {
            query.findObjects(object : FindListener<Banne>() {
                override fun done(p0: MutableList<Banne>?, p1: BmobException?) {
                    if (p1 != null) {
                        listp.clear()
                        for (bane in p0!!) {
                            listp.add(bane.photo)
                        }
                        uiThread {
                            banner.setImages(listp)
                            banner.start()
                        }
                    }
                }
            })
        }
    }

    private fun initData() {
        val query = BmobQuery<Note>()
        query.setLimit(50)
        query.order("-top")

        doAsync {
            query.findObjects(object : FindListener<Note>() {
                override fun done(p0: MutableList<Note>?, p1: BmobException?) {
                    if (p1 != null) {
                        listNote.clear()
                        for (note in p0!!) {
                            listNote.add(note)
                        }
                        uiThread { noteAdapter.notifyDataSetChanged() }
                    } else {
                        uiThread {
                            toast("数据请求失败，请检查网络")
                        }
                    }
                }

            })
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.et_note,R.id.iv_search -> {
                startActivity(intentFor<SearchNoteActivity>())
            }
            R.id.iv_post ->{
                startActivity(intentFor<AddNoteActivity>())
            }
        }
    }
}