package com.wsg.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wsg.kotlin.R
import com.wsg.kotlin.bean.Note
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.find


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.adapter
 *  文件名:   TouristNoteAdapter
 *  创建者:   wsg
 *  创建时间: 2019/6/17 14:28
 *  描述:     use
 */

class TouristNoteAdapter(var ctx : Context,var list : ArrayList<Note>) : RecyclerView.Adapter<TouristNoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_tourist_note,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list.get(position).title
        holder.type.text = list.get(position).typeid
        holder.time.text = list.get(position).updatedAt
        holder.content.text = list.get(position).content
    }

    class ViewHolder : RecyclerView.ViewHolder{
        val profile : CircleImageView
        val title : TextView
        val type : TextView
        val time : TextView
        val content : TextView

        constructor(itemView: View) : super(itemView) {
            profile = itemView.find(R.id.item_tl_note_profile)
            title = itemView.find(R.id.item_tl_note_title)
            type = itemView.find(R.id.item_tl_note_type)
            time = itemView.find(R.id.item_tl_note_time)
            content = itemView.find(R.id.item_tl_note_content)
        }
    }
}