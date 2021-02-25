package com.jimmy.custom

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_notify_counter.*
import java.util.*

/**
 * 在消息通知的右边放置一个计时器Chronometer,这便形成了能够动态显示已逝去时间的计时消息，该消息常用于度量某个特殊事务的耗时时长。构建计时消息的时候，需要注
意下面两点:
(1) setWhen. 与setUsesChronometer两个方法同时只能调用其一 ，也就是说推送时间与计数器无法同时显示，因为它们都位于通知栏的右边。
2) setNumber 与setContentInfo两个方法同时只能调用其一，因为计数值与提示都位于通知栏的右下方。下面是发送计时消息的Kotlin代码片段:
 */
class NotifyCounterActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_counter)

        btn_send_counter.setOnClickListener {
            val title = et_title.text.toString()
            val message = et_message.text.toString()
            sendCounterNotify(title, message)
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun sendCounterNotify(title: String, message: String) {
        val notifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notifyBuilder: Notification.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notifyChannel = NotificationChannel(UUID.randomUUID().toString(), "带有计时器的通知", NotificationManager.IMPORTANCE_DEFAULT)
            notifyMgr.createNotificationChannel(notifyChannel)
            notifyBuilder = Notification.Builder(this, notifyChannel.id)
        } else {
            notifyBuilder = Notification.Builder(this)
        }

        //声明一个删除通知消息时触发的动作意图
        val cancelIntent = Intent(this, MainActivity::class.java);
        val piDelete = PendingIntent.getActivity(this,
            R.string.app_name, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notify = notifyBuilder.setDeleteIntent(piDelete)
            .setAutoCancel(true)
            .setUsesChronometer(true)
            .setProgress(100, 60, false)
            .setNumber(99)
            .setSmallIcon(R.drawable.ic_app)
            .setTicker("计数消息来啦")
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_app))
            .setContentTitle(title)
            .setContentText(message).build()
        notifyMgr.notify(R.string.app_name, notify)
    }
}
