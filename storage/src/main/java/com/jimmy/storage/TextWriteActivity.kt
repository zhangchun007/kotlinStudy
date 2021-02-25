package com.jimmy.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import com.jimmy.storage.util.DateUtil
import kotlinx.android.synthetic.main.activity_text_write.*
import java.io.File

class TextWriteActivity : AppCompatActivity() {

    private var mPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_write)
        mPath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/"

        btn_save.setOnClickListener {
            when {
                et_name.text.isEmpty() -> Toast.makeText(this, "请先填写姓名", Toast.LENGTH_LONG).show();
                et_age.text.isEmpty() -> Toast.makeText(this, "请先填写年龄", Toast.LENGTH_LONG).show();
                et_height.text.isEmpty() -> Toast.makeText(
                    this,
                    "请先填写身高",
                    Toast.LENGTH_LONG
                ).show();
                et_weight.text.isEmpty() -> Toast.makeText(
                    this,
                    "请先填写体重",
                    Toast.LENGTH_LONG
                ).show();
                else -> {
                    val content = "姓名：${et_name.text}\n" +
                            "年龄：${et_age.text}\n" +
                            "体重：${et_weight.text}\n" +
                            "身高：${et_height.text}\n" +
                            "注册时间：${DateUtil.nowDateTime}\n"
                    val file_path = "$mPath${DateUtil.nowDateTime}.txt"

                    //writeText写入文本，writeBytes写入字节数组
                    //appendText追加文本，appendBytes追加字节数组
                    File(file_path).writeText(content)
                    tv_path.text = "用户注册信息文件的保存路径为：\n$file_path"
                    Toast.makeText(this, "文本已写入临时目录", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
