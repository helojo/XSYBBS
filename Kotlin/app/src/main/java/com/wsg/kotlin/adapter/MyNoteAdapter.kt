package com.wsg.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.wsg.kotlin.R
import com.wsg.kotlin.activity.ModifyNoteActivity
import com.wsg.kotlin.bean.Note
import org.jetbrains.anko.find
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.adapter
 *  文件名:   MyNoteAdapter
 *  创建者:   wsg
 *  创建时间: 2019/6/24 11:09
 *  描述:     我的帖子适配器
 */

class MyNoteAdapter(var ctx: Context, var list: List<Note>) : BaseAdapter() {

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder: ViewHolder
        val view : View
        if (convertView == null) {
            view = (ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .inflate(R.layout.item_my_note, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.getTag() as ViewHolder
        }
        viewHolder.radar(list.get(position))
        return view
    }

    class ViewHolder {
        val ctx: Context
        val title: TextView
        val type: TextView
        val time: TextView
        val content: TextView
        val modify: ImageView
        val del: ImageView

        constructor(view: View) {
            ctx = view.context
            title = view.find(R.id.item_my_note_title)
            type = view.find(R.id.item_my_note_type)
            time = view.find(R.id.item_my_note_time)
            content = view.find(R.id.item_my_note_content)
            modify = view.find(R.id.item_my_note_modify)
            del = view.find(R.id.item_my_note_delete)
        }

         fun radar(note: Note) {
            title.text = note.title
            type.text = note.typeid
            time.text = note.updatedAt
            content.text = note.content
            modify.setOnClickListener {
                ctx.startActivity(ctx.intentFor<ModifyNoteActivity>("note" to note))
                //todo
            }
            del.setOnClickListener {
                note.delete(object : UpdateListener() {
                    override fun done(p0: BmobException?) {
                        ctx.toast("删除成功")
                        //todo
                    }
                })
            }
        }
    }
}
