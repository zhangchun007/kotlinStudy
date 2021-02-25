package com.jimmy.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.jimmy.storage.util.DateUtil
import com.jimmy.storage.util.FileUtil
import kotlinx.android.synthetic.main.activity_image_write.*

class ImageWriteActivity : AppCompatActivity() {

    private val types = listOf<String>("未婚", "已婚")
    private var bMarried = false
    private var mPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_write)
        mPath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/"

        sp_married.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = types[0]
        tv_spinner.setOnClickListener {
            sp_married.visibility = View.VISIBLE
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
                    //获取截图
                    val bitmap = ll_info.drawingCache
                    val file_path = "$mPath${DateUtil.getFormatTime()}.png"
                    FileUtil.saveImage(file_path, bitmap)
                    tv_path.text = "用户注册信息图片的保存路径为：\n$file_path"
                    Toast.makeText(this, "图片已存入临时目录", Toast.LENGTH_SHORT).show()

                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        ll_info.isDrawingCacheEnabled = true
    }

    override fun onStop() {
        super.onStop()
        ll_info.isDrawingCacheEnabled = false
    }
}
