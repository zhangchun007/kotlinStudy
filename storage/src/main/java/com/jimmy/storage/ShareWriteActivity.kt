package com.jimmy.storage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.jimmy.storage.util.DateUtil
import com.jimmy.storage.util.Preference
import kotlinx.android.synthetic.main.activity_share_write.*

class ShareWriteActivity : AppCompatActivity() {

    private val types = listOf("未婚", "已婚")
    private var bMarried = false
    //声明字符串类型的委托属性
    private var name: String by Preference(this, "name", "")
    private var age: Int by Preference(this, "age", 0)
    private var height: Long by Preference(this, "height", 0)
    private var weight: Float by Preference(this, "weight", 0f)
    private var married: Boolean by Preference(this, "married", false)
    private var update_time: String by Preference(this, "update_time", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_write)

        sp_married.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types)

        sp_married.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tv_spinner.text = types[position]
                bMarried = if (position == 0) false else true
            }

        }


        btn_save.setOnClickListener {
            when {
                et_name.text.isEmpty() -> Toast.makeText(this, "请先填写姓名", Toast.LENGTH_SHORT).show()
                et_age.text.isEmpty() -> Toast.makeText(this, "请先填写年龄", Toast.LENGTH_SHORT).show()
                et_height.text.isEmpty() -> Toast.makeText(
                    this,
                    "请先填写身高",
                    Toast.LENGTH_SHORT
                ).show()
                et_weight.text.isEmpty() -> Toast.makeText(
                    this,
                    "请先填写体重",
                    Toast.LENGTH_SHORT
                ).show()
                else -> {
                    name = et_name.text.toString()
                    age = et_age.text.toString().toInt()
                    height = et_height.text.toString().toLong()
                    weight = et_weight.text.toString().toFloat()
                    married = bMarried
                    update_time = DateUtil.nowDateTime
                    Toast.makeText(this, "数据已写入共享参数", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
