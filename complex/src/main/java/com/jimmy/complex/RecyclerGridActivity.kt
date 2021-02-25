package com.jimmy.complex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.complex.widget.SpacesItemDecoration
import com.jimmy.complex.adapter.RecyclerGridAdapter
import com.jimmy.complex.bean.RecyclerInfo
import kotlinx.android.synthetic.main.activity_recycler_grid.*

class RecyclerGridActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_grid)
        rv_grid.layoutManager = GridLayoutManager(this, 5)
        val adapter = RecyclerGridAdapter(this@RecyclerGridActivity, RecyclerInfo.defaultGrid)
        adapter.setOnItemClickListener(adapter)
        adapter.setOnItemLongClickListener(adapter)
        rv_grid.adapter = adapter
        rv_grid.itemAnimator = DefaultItemAnimator()
        rv_grid.addItemDecoration(SpacesItemDecoration(5))
    }
}
