package com.jimmy.custom.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.jimmy.custom.ServiceNormalActivity

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-11-10
 * @Version:        1.0
 */
class NormalService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        ServiceNormalActivity.showText("创建服务")
        super.onCreate()

    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val bundle = intent.extras
        val request_content = bundle?.getString("request_content")
        ServiceNormalActivity.showText("启动服务，收到请求内容：${request_content}")
        return Service.START_STICKY

    }
    override fun onDestroy() {
        ServiceNormalActivity.showText("停止服务")
        super.onDestroy()
    }
}