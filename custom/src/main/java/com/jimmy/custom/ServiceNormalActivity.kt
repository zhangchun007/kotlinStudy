package com.jimmy.custom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateUtils
import android.widget.TextView
import com.example.custom.util.DateUtil
import com.jimmy.custom.service.NormalService
import kotlinx.android.synthetic.main.activity_service_normal.*

/**
 * 普通服务
 */
class ServiceNormalActivity : AppCompatActivity() {

    var intentNormal: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_normal)
        btn_start.setOnClickListener {
            intentNormal = Intent(this@ServiceNormalActivity, NormalService::class.java)
            intentNormal!!.putExtra("request_content", et_request.text.toString())
            startService(intentNormal)
        }

        btn_stop.setOnClickListener {
            if (intentNormal != null) {
                stopService(intentNormal)
            }
        }
        Normal.tv_normal = findViewById<TextView>(R.id.tv_normal)
    }


    companion object Normal {
        private var tv_normal: TextView? = null
        private var mDesc = ""
        //静态方法showText给NormalService内部调用

        fun showText(desc: String) {
            mDesc="${mDesc}${DateUtil.nowTime}$desc\n"
            //如果tv_normal非空才设置文本，否则不设置文本
            tv_normal?.text = mDesc
        }
    }
}
