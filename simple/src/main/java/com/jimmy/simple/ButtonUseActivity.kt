package com.jimmy.simple

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_btn_use.*

class ButtonUseActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_btn_use)
        //6.1按钮的使用
        //6.1.1按钮的点击事件(三种方式)
        //1：匿名函数方式
        btn1.setOnClickListener { v ->
            btn1.text = "匿名形式"
        }
        //2:内部类方式
        btn1.setOnClickListener(MyClickListener())

        //3:接口实现方式
        btn1.setOnClickListener(this)


        //6.1.2复选框CheckBox
        checkbox.isChecked = false //默认是未选中
        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            tv_select.text = "您${if (isChecked) "勾选" else "取消勾选"}了复选框"
        }

        //6.1.3单选按钮RadioButton
        rg_sex.setOnCheckedChangeListener { group, checkedId ->
            tv_sex.text = when (checkedId) {
                R.id.rb_male -> "哇喔,你是个帅气的男孩"
                R.id.rb_female -> "哇喔,你是个漂亮的女孩"
                else -> "啥也没选"
            }
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn1) {
            btn1.text = "接口实现方式"
        }
    }


    private inner class MyClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            (v as Button).text = "内部类方式"
        }
    }

}
