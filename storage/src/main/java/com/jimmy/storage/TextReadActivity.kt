package com.jimmy.storage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_text_read.*
import java.io.File

class TextReadActivity : AppCompatActivity() {

    private lateinit var mPath: String
    private var fileNames: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_read)
        sp_file.visibility = View.VISIBLE
        tv_spinner.visibility = View.GONE

        btn_delete.setOnClickListener {
            fileNames.forEach {
                val file = File(mPath + it)
                if (!file.delete()) {

                }
            }
            refreshSpinner()
            Toast.makeText(this, "已删除临时目录下的所有文本文件", Toast.LENGTH_SHORT).show()
        }
        mPath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/"
        refreshSpinner()
    }

    private fun refreshSpinner() {
        fileNames.clear()
        //在该目录下走一圈，得到文件目录树结构
        val fileTree: FileTreeWalk = File(mPath).walk()
        fileTree.maxDepth(1)//需遍历的目录层级为1，即无需检查子目录
            .filter { it.isFile }//只挑选文件，不处理文件夹
            .filter { it.extension == "txt" }//选择扩展名为txt的文本文件
            .forEach { fileNames.add(it.name) } //循环处理符合条件的文件

        if (!fileNames.isEmpty()) {
            sp_file.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fileNames)

            sp_file.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    tv_spinner.text = fileNames[position]
                    val file_path = mPath + fileNames[position]
                    //readText读取文本形式的文件内容，readLines按行读取文件内容
                    val content = File(file_path).readText()
                    tv_text.text = "文件内容如下：\n$content"
                }

            }
        } else {
            tv_spinner.text = ""
            tv_spinner.setOnClickListener { }
            tv_text.text = ""
        }
    }
}
