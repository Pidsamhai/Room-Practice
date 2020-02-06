package com.mengxyz.room.room.practice.db.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mengxyz.room.room.practice.R
import com.mengxyz.room.room.practice.db.entity.Word

class WordAdapter(
    private val context: Context
) :RecyclerView.Adapter<WordAdapter.Holder>(){

    private val inflater:LayoutInflater = LayoutInflater.from(context)
    private var worlds = emptyList<Word>()

    inner class Holder(itemView:View):RecyclerView.ViewHolder(itemView){
        val worldItemView:TextView = itemView.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = inflater.inflate(R.layout.recycle_item,parent,false)
        return Holder(v)
    }

    override fun getItemCount(): Int = worlds.size

    fun setWorlds(words: List<Word>){
        this.worlds = words
        Log.e("Adapter", this.worlds.size.toString())
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.worldItemView.text = worlds[position].world
    }
}