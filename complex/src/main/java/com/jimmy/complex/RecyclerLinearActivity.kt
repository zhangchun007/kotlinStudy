package com.jimmy.complex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.complex.widget.SpacesItemDecoration
import com.jimmy.complex.adapter.RecyclerLinearAdapter
import com.jimmy.complex.bean.RecyclerInfo
import kotlinx.android.synthetic.main.activity_recycler_linear.*

class RecyclerLinearActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_linear)

        rv_linear.layoutManager=LinearLayoutManager(this)
        val adapter=RecyclerLinearAdapter(this,RecyclerInfo.defaultList)
        adapter.setOnItemClickListener(adapter)
        adapter.setOnItemLongClickListener(adapter)
        rv_linear.adapter = adapter
        rv_linear.itemAnimator = DefaultItemAnimator()
        rv_linear.addItemDecoration(SpacesItemDecoration(5))
    }
}
