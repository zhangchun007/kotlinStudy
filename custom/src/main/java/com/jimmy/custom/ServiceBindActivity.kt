package com.jimmy.custom

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.TextView
import com.example.custom.util.DateUtil
import com.jimmy.custom.service.BindService
import kotlinx.android.synthetic.main.activity_service_bind.*

/**
 * 绑定式服务
 *
 */
class ServiceBindActivity : AppCompatActivity() {
    private var mBindService: BindService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_bind)
        Bind.tv_bind = findViewById<TextView>(R.id.tv_bind)

        btn_start_bind.setOnClickListener {
            val intentBind = Intent(this@ServiceBindActivity, BindService::class.java)
            intentBind.putExtra("request_content", et_request.text.toString())
            bindService(intentBind, mFirstConn, Context.BIND_AUTO_CREATE)
        }
        btn_unbind.setOnClickListener {
            if (mBindService != null) {
                unbindService(mFirstConn)
                mBindService = null
            }
        }
    }

    private val mFirstConn = object : ServiceConnection {
        //无法获取到服务对象时的操作
        override fun onServiceDisconnected(name: ComponentName?) {
            mBindService = null
            Log.d(TAG, "onServiceDisconnected")
        }

        //获取服务对象时的操作
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

            //如果服务运行于另外一个进程，则不能直接强制转换类型，
            //否则会报错“java.lang.ClassCastException: android.os.BinderProxy cannot be cast to...”
            mBindService = (service as BindService.LocalBinder).service
            Log.d(TAG, "onServiceConnected")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mBindService != null) {
            unbindService(mFirstConn)
            mBindService = null
        }
    }

    companion object Bind {
        private val TAG = "ServiceBindActivity"
        private var tv_bind: TextView? = null
        private var mDesc = ""

        fun showText(desc: String) {
            mDesc = "$mDesc${DateUtil.nowTime} $desc\n"
            tv_bind?.text = mDesc
        }
    }
}
