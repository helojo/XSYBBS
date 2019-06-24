package com.wsg.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wsg.kotlin.R
import com.wsg.kotlin.activity.NoteDetailActivity
import com.wsg.kotlin.bean.Note
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.find
import org.jetbrains.anko.intentFor


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.adapter
 *  文件名:   NoteAdapter
 *  创建者:   wsg
 *  创建时间: 2019/6/17 15:59
 *  描述:     主页帖子适配器
 */

class NoteAdapter(var ctx : Context,var list: List<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(LayoutInflater.from(ctx).inflate( R.layout.item_note,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list.get(position).title
        holder.type.text = list.get(position).typeid
        holder.time.text = list.get(position).updatedAt
        holder.content.text = list.get(position).content
        holder.zanCount.text = list.get(position).zancount.toString()
        holder.replayCount.text = list.get(position).replaycount.toString()

        holder.itemView.setOnClickListener {
            ctx.startActivity(ctx.intentFor<NoteDetailActivity>("note" to list.get(position)))
        }
    }


    class ViewHolder :RecyclerView.ViewHolder{
        val profile : CircleImageView
        val title : TextView
        val type : TextView
        val time : TextView
        val content : TextView
        val zanCount : TextView
        val replayCount : TextView

        constructor(itemView: View) : super(itemView){
            profile = itemView.find(R.id.item_note_profile)
            title = itemView.find(R.id.item_note_title)
            type = itemView.find(R.id.item_note_type)
            time = itemView.find(R.id.item_note_time)
            content = itemView.find(R.id.item_note_content)
            zanCount = itemView.find(R.id.item_note_zancount)
            replayCount = itemView.find(R.id.item_note_replaycount)
        }
    }
}