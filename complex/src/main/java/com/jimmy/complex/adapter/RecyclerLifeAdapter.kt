package com.jimmy.complex.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jimmy.complex.R
import com.jimmy.complex.bean.LifeItem
import kotlinx.android.synthetic.main.item_life.*

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-10-11
 * @Version:        1.0
 */
class RecyclerLifeAdapter(context: Context, private var lifes: MutableList<LifeItem>) :
    RecyclerBaseAdapter<RecyclerView.ViewHolder>(context) {

    override fun getItemCount(): Int = lifes.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as ItemHolder
        itemHolder.iv_pic.setImageResource(lifes.get(position).pic_id)
        itemHolder.tv_title.text = lifes.get(position).title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater.inflate(R.layout.item_life, parent, false)
        return ItemHolder(view)
    }

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
        val tv_title = view.findViewById<TextView>(R.id.tv_title)
    }
}