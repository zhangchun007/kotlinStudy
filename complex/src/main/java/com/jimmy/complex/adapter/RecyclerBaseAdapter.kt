package com.jimmy.complex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-30
 * @Version:        1.0
 */
abstract class RecyclerBaseAdapter<VH : RecyclerView.ViewHolder>(val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), RecyclerExtras.OnItemClickListener,
    RecyclerExtras.OnItemLongClickListener {

    val inflater: LayoutInflater = LayoutInflater.from(context)
    //获得列表项的个数
    override abstract fun getItemCount(): Int

    //根据布局文件创建视图持有者，需要子类重写
    override abstract fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder

    //绑定视图持有者中的各个控件对象，需要子类重写
    override abstract fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)

    override fun getItemViewType(position: Int): Int = 0

    override fun getItemId(position: Int): Long = position.toLong()

    var itemClickListener: RecyclerExtras.OnItemClickListener? = null
    fun setOnItemClickListener(listener: RecyclerExtras.OnItemClickListener) {
        this.itemClickListener = listener
    }

    var itemLongClickListener: RecyclerExtras.OnItemLongClickListener? = null
    fun setOnItemLongClickListener(listener: RecyclerExtras.OnItemLongClickListener) {
        this.itemLongClickListener = listener
    }

    var itemDeleteClickListener: RecyclerExtras.OnItemDeleteClickListener? = null
    fun setOnItemDeleteClickListener(listener: RecyclerExtras.OnItemDeleteClickListener) {
        this.itemDeleteClickListener = listener
    }


    override fun onItemClick(view: View, position: Int) {}

    override fun onItemLongClick(view: View, position: Int) {}

}