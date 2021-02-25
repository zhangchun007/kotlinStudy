package com.jimmy.complex.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jimmy.complex.R
import com.jimmy.complex.bean.RecyclerInfo

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-30
 * @Version:        1.0
 */
class RecyclerStaggeredAdapter(context: Context,private val infos:MutableList<RecyclerInfo>):RecyclerBaseAdapter<RecyclerView.ViewHolder>(context) {
    override fun getItemCount(): Int=infos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view=inflater.inflate(R.layout.item_recycler_staggered,parent,false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as ItemHolder
        vh.iv_pic.setImageResource(infos[position].pic_id)
        vh.tv_title.text = infos[position].title
    }
    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ll_item = view.findViewById<LinearLayout>(R.id.ll_item)
        var iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
        var tv_title = view.findViewById<TextView>(R.id.tv_title)
    }
}