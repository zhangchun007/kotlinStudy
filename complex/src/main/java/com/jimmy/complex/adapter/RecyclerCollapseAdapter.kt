package com.jimmy.complex.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jimmy.complex.R
import com.jimmy.complex.bean.RecyclerInfo

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-10-11
 * @Version:        1.0
 */
class RecyclerCollapseAdapter(context: Context, private var titles: Array<String>) :
    RecyclerBaseAdapter<RecyclerView.ViewHolder>(context) {
    override fun getItemCount(): Int = titles.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater.inflate(R.layout.item_collapse, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as ItemHolder
        holder.tv_seq.text = "${position + 1}"
        holder.tv_title.text = titles[position]
    }

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_seq = view.findViewById<TextView>(R.id.tv_seq)
        val tv_title = view.findViewById<TextView>(R.id.tv_title)
    }
}