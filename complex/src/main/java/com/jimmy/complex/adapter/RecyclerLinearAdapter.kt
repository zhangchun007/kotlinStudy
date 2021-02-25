package com.jimmy.complex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.jimmy.complex.R
import com.jimmy.complex.bean.RecyclerInfo
import com.jimmy.complex.adapter.RecyclerExtras.OnItemClickListener
import com.jimmy.complex.adapter.RecyclerExtras.OnItemLongClickListener

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-29
 * @Version:        1.0
 */
class RecyclerLinearAdapter(
    private val context: Context,
    private val infos: MutableList<RecyclerInfo>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), OnItemClickListener, OnItemLongClickListener {


    val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = inflater.inflate(R.layout.item_recycler_linear, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = infos.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //强转
        val vh: ItemHolder = holder as ItemHolder
        vh.iv_pic.setImageResource(infos[position].pic_id)
        vh.tv_title.text = (infos[position].title)
        vh.tv_desc.text = (infos[position].desc)

        //列表的点击事件
        vh.ll_item.setOnClickListener { v ->
            itemClickListener?.onItemClick(v, position)
        }
        vh.ll_item.setOnLongClickListener() { v ->
            itemLongClickListener?.onItemLongClick(v, position);true
        }

    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    private var itemLongClickListener: OnItemLongClickListener? = null
    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        this.itemLongClickListener = listener
    }

    override fun onItemClick(view: View, position: Int) {
        val desc = "您点击了第${position + 1}项，标题是${infos[position].title}"
        Toast.makeText(context, desc, Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(view: View, position: Int) {
        val desc = "您长按了第${position + 1}项，标题是${infos[position].title}"
        Toast.makeText(context, desc, Toast.LENGTH_SHORT).show()
    }

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ll_item = view.findViewById<LinearLayout>(R.id.ll_item)
        var iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
        var tv_title = view.findViewById<TextView>(R.id.tv_title)
        var tv_desc = view.findViewById<TextView>(R.id.tv_desc)

    }

}


