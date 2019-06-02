package com.wsg.kotlin.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.wsg.kotlin.R
import com.wsg.kotlin.bean.Note
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.find


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.adapter
 *  文件名:   NoteAdapter
 *  创建者:   wsg
 *  创建时间: 2019/5/25 16:02
 *  描述:     use
 */

class NoteAdapter(var ctx :Context,var list: List<Note>) : RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val myViewHolder = MyViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_note,p0,false))
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        if(list.get(p1).image != null){
            //todo  设置头像
        }
        p0.title.text = list.get(p1).title
        p0.type.text = list.get(p1).typeid
        p0.time.text = list.get(p1).getUpdatedAt().substring(0,10)
        p0.content.text = list.get(p1).content
        p0.zanConut.text = list.get(p1).zancount.toString()
        p0.replayCount.text = list.get(p1).replaycount.toString()
    }


    class MyViewHolder : RecyclerView.ViewHolder {
        var profile : CircleImageView
        var title : TextView
        var type : TextView
        var time : TextView
        var content : TextView
        var zan : ImageView
        var zanConut : TextView
        var replay : ImageView
        var replayCount : TextView

        constructor(itemView: View) : super(itemView) {
            profile = itemView.find(R.id.item_note_profile)
            title = itemView.find(R.id.item_note_title)
            type = itemView.find(R.id.item_note_type)
            time = itemView.find(R.id.item_note_time)
            content = itemView.find(R.id.item_note_content)
            zan = itemView.find(R.id.item_note_zan)
            zanConut = itemView.find(R.id.item_note_zancount)
            replay = itemView.find(R.id.item_note_replay)
            replayCount = itemView.find(R.id.item_note_replaycount)
        }
    }
}