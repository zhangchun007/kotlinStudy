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
import kotlinx.android.synthetic.main.item_recycler_linear.*

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-10-19
 * @Version:        1.0
 */
class RecyclerSwipeAdapter(context: Context, private val infos: MutableList<RecyclerInfo>) :
    RecyclerBaseAdapter<RecyclerView.ViewHolder>(context) {
    override fun getItemCount(): Int = infos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater.inflate(R.layout.item_recycler_linear, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as ItemHolder
        itemHolder.iv_pic.setImageResource(infos[position].pic_id)
        itemHolder.tv_title.text = infos[position].title
        itemHolder.tv_desc.text = infos[position].desc
        itemHolder.tv_delete.visibility = if (infos[position].pressed) View.VISIBLE else View.GONE

        itemHolder.tv_delete.setOnClickListener {
            v -> itemDeleteClickListener?.onItemDeleteClick(v,position)
        }
        //列表的点击事件
        itemHolder.ll_item.setOnClickListener { v ->
            itemClickListener?.onItemClick(v, position)
        }
        itemHolder.ll_item.setOnLongClickListener() { v ->
            itemLongClickListener?.onItemLongClick(v, position);true
        }


    }

    private inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ll_item = view.findViewById<LinearLayout>(R.id.ll_item)
        var iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
        var tv_title = view.findViewById<TextView>(R.id.tv_title)
        var tv_desc = view.findViewById<TextView>(R.id.tv_desc)
        var tv_delete = view.findViewById<TextView>(R.id.tv_delete)
    }
}