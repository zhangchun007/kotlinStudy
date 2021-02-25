package com.jimmy.network

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_message.setOnClickListener {
            var intent = Intent(this@MainActivity, MessageActivity::class.java)
            startActivity(intent)
        }
        btn_asynctask.setOnClickListener {
            var intent = Intent(this@MainActivity, AsyncTaskActivity::class.java)
            startActivity(intent)
        }
        btn_json.setOnClickListener {
            var intent = Intent(this@MainActivity, JsonParseActivity::class.java)
            startActivity(intent)
        }
        btn_json_convert.setOnClickListener {
            var intent = Intent(this@MainActivity, JsonConvertActivity::class.java)
            startActivity(intent)
        }
        btn_json_convert.setOnClickListener {
            var intent = Intent(this@MainActivity, HttpRequestActivity::class.java)
            startActivity(intent)
        }


    }
}
