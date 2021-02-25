package com.jimmy.simple

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jimmy.simple.javabean.MessageInfo
import com.jimmy.simple.util.DateUtil
import kotlinx.android.synthetic.main.activity_act_response.*

/**
 * 接收序列化数据
 */
class ActResponseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_response)

        btn_act_response.setOnClickListener {
            val response = MessageInfo(et_response.text.toString(), DateUtil.nowTime)
            val intent = Intent()
            intent.putExtra("message", response)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        val request = intent.extras?.getParcelable<MessageInfo>("message")
        tv_response.text = "收到打包好的请求消息：\n请求时间为${request?.send_time}\n请求内容为${request?.content}"

    }
}
