package com.jimmy.storage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //写入共享参数
        btn_share_write.setOnClickListener {
            val intent = Intent(this@MainActivity, ShareWriteActivity::class.java)
            startActivity(intent)
        }
        //读取共享参数
        btn_share_read.setOnClickListener {
            val intent = Intent(this@MainActivity, ShareReadActivity::class.java)
            startActivity(intent)
        }
        //读取共享参数
        btn_login_share.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginShareActivity::class.java)
            startActivity(intent)
        }
        //写入数据库
        btn_sqlite_write.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginShareActivity::class.java)
            startActivity(intent)
        }

        btn_file_path.setOnClickListener {
            val intent = Intent(this@MainActivity, FilePathActivity::class.java)
            startActivity(intent)
        }

        btn_text_write.setOnClickListener {
            val intent = Intent(this@MainActivity, TextWriteActivity::class.java)
            startActivity(intent)
        }

        btn_text_read.setOnClickListener {
            val intent = Intent(this@MainActivity, TextReadActivity::class.java)
            startActivity(intent)
        }
        btn_image_write.setOnClickListener {
            val intent = Intent(this@MainActivity, ImageWriteActivity::class.java)
            startActivity(intent)
        }
        btn_image_reader.setOnClickListener {
            val intent = Intent(this@MainActivity, ImageReadActivity::class.java)
            startActivity(intent)
        }
        btn_application.setOnClickListener {
            val intent = Intent(this@MainActivity, AppWriteActivity::class.java)
            startActivity(intent)
        }

        btn_optionmenu.setOnClickListener {
            val intent = Intent(this@MainActivity, MenuOptionActivity::class.java)
            startActivity(intent)
        }


    }
}
