package com.jimmy.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_linear.*

class LinearActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear)

        //6.2使用页面布局
        //6.2.1线性布局LinearLayout

        //设置ll_margin内部试图的排列方式为水平排列
        ll_margin.orientation = LinearLayout.HORIZONTAL
        //设置ll_margin内部视图的对齐方式为剧中对其
        ll_margin.gravity = Gravity.CENTER

        btn_margin_vertical.setOnClickListener {
            //kotlin对变量进行类型转换扽关键字是as
            val params = ll_margin.layoutParams as LinearLayout.LayoutParams
            //setMargins方法为设置该视图与外部视图的空白距离
            params.setMargins(0, 100, 0, 100)
            ll_margin.layoutParams = params
        }

        btn_margin_horizontal.setOnClickListener {
            //kotlin对变量进行类型转换扽关键字是as
            val params = ll_margin.layoutParams as LinearLayout.LayoutParams
            //setMargins方法为设置该视图与外部视图的空白距离
            params.setMargins(100, 0, 100, 0)
            ll_margin.layoutParams = params
        }

        btn_padding_vertical.setOnClickListener {
            //此处设置左边和右边的padding间隔距离为100
            ll_margin.setPadding(0, 100, 0, 100)
        }
        btn_padding_horizontal.setOnClickListener {
            //此处设置顶部和底部的padding间隔距离为100
            ll_margin.setPadding(100, 0, 100, 0)
        }
    }
}
