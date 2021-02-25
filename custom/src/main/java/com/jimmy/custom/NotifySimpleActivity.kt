package com.jimmy.custom

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_notify_simple.*
import java.util.*

class NotifySimpleActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_simple)
        btn_send_simple.setOnClickListener {
            val title = et_title.text.toString()
            val message = et_message.text.toString()

            sendSimpleNotify(title, message)
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun sendSimpleNotify(title: String, message: String) {
        //获取系统的通知管理器
        val notifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notifyBuilder: Notification.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //8.0及以后版本必须声明channel
            val notifyChanel = NotificationChannel(
                UUID.randomUUID().toString(),
                "简单通知",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notifyMgr.createNotificationChannel(notifyChanel)
            val notifyChanelID: String = notifyChanel.id

            notifyBuilder = Notification.Builder(this, notifyChanelID)
        } else {
            notifyBuilder = Notification.Builder(this)
        }

        //声明一个点击通知栏时触发的动作意图
        val clickIntent = Intent(this, MainActivity::class.java);
        val poClick = PendingIntent.getActivity(
            this,
            R.string.app_name,
            clickIntent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        //开始构建简单消息的各个参数
        /**
         * setContentIntent（）//设置内容的延时意图PendingIntent，在点击该通知时触发该意图。通常用PendingIntent.getActivity方法获取延时意图对象，
         *                      getActivity方法表示点击后跳转到该页面
         *
         */
        val notify = notifyBuilder.setContentIntent(poClick).setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_app).setTicker("简单消息来了").setWhen(System.currentTimeMillis())
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_app))
            .setContentTitle(title)
            .setContentText(message).build()

        notifyMgr.notify(R.string.app_name, notify)


    }
}
