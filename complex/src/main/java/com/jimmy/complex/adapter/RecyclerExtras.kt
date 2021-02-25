package com.jimmy.complex.adapter

import android.view.View

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-30
 * @Version:        1.0
 */
class RecyclerExtras {
    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int)
    }

    interface OnItemDeleteClickListener {
        fun onItemDeleteClick(view: View, position: Int)
    }
}