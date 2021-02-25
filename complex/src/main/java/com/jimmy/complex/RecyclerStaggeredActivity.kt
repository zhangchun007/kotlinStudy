package com.jimmy.complex

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.complex.widget.SpacesItemDecoration
import com.jimmy.complex.adapter.RecyclerStaggeredAdapter
import com.jimmy.complex.bean.RecyclerInfo
import kotlinx.android.synthetic.main.activity_recycler_staggered.*

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-30
 * @Version:        1.0
 */
class RecyclerStaggeredActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_staggered)

        rv_staggered.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        val adapter = RecyclerStaggeredAdapter(this, RecyclerInfo.defaultStag)
        rv_staggered.adapter = adapter
        rv_staggered.itemAnimator = DefaultItemAnimator()
        rv_staggered.addItemDecoration(SpacesItemDecoration(5))
    }
}