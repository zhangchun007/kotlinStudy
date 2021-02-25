package com.jimmy.simple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jimmy.simple.javabean.MessageInfo
import com.jimmy.simple.util.DateUtil
import kotlinx.android.synthetic.main.activity_act_first.*
import kotlinx.android.synthetic.main.activity_act_request.*
import kotlinx.android.synthetic.main.activity_act_request.btn_act_request
import kotlinx.android.synthetic.main.activity_act_request.et_request

/**
 * 实现序列化数据传递
 */
class ActRequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_request)
        btn_act_request.setOnClickListener {
            val info = MessageInfo(et_request.text.toString(), DateUtil.nowTime)
            val intent = Intent(this, ActResponseActivity::class.java)
            intent.putExtra("message", info)
            startActivityForResult(intent, 0)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            val response = data.extras?.getParcelable<MessageInfo>("message")
            tv_request.text = "收到返回消息：\n应答时间为${response?.send_time}\n应答内容为${response?.content}"
        }
    }
}
