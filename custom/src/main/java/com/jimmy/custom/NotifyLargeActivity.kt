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
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_notify_large.*
import kotlinx.android.synthetic.main.activity_text_progress.*
import java.util.*

/**
 * 大视图通知采用NotificationCompat.Builder构建。以下是参数设置的方法
 *1：setDefaults:设置通知到达的提醒方式。可设置多个方式，方式之间用or位运算符 or 连接
 *2：addAction:在本通知底部添加一个动作按钮，可同时添加多个按钮，按钮从左到右排序。该方法的第一个参数为按钮图片的资源ID,第二个参数为按钮的文本内
容，第三个参数为点击按钮对应的动作意图。
 * 3：setStyle: 设置该通知的大视图风格。大视图通知有三种风格类型，分别是大文本风
格NotificationCompat.BigTextStyle、大图片风格NotificationCompat.BigPictureStyle和收件箱风格NotificationCompat.InboxStyle.具体的风格类型取值说明见表9-3。
 */
class NotifyLargeActivity : AppCompatActivity() {
    private val styles = listOf("大文字风格", "大图片风格", "收件箱风格")
    private var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_large)
        sp_style.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, styles)
        sp_style.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                type = position
            }

        }

        btn_send_large.setOnClickListener {
            getStyleAndSend(et_title.text.toString(), et_message.text.toString(), type)
        }

    }

    private fun getStyleAndSend(title: String, message: String, type: Int) {
        var style: NotificationCompat.Style? = null
        when (type) {
            0 -> {//声明大文本风格
                style = NotificationCompat.BigTextStyle()
                style.setBigContentTitle(title)
                style.setSummaryText(message)
                style.bigText("这是一条大文字风格的通知消息")

            }
            1 -> {
                style = NotificationCompat.BigPictureStyle()
                style.setBigContentTitle(title)
                style.setSummaryText(message)
                style.bigLargeIcon(
                    BitmapFactory.decodeResource(
                        resources,
                        R.drawable.icon_financer
                    )
                )
                style.bigPicture(BitmapFactory.decodeResource(resources, R.drawable.icon_sunshine))

            }
            2 -> {
                style = NotificationCompat.InboxStyle()
                style.setBigContentTitle(title)
                style.setSummaryText(message)
                style.addLine("天青色等烟雨，而我在等你")
                style.addLine("炊烟袅袅升起，隔江千万里")
                style.addLine("在瓶底书汉隶仿前朝的飘逸")
            }
        }

        sendLargeNotify(title, message, style)
    }

    private fun sendLargeNotify(title: String, message: String, style: NotificationCompat.Style?) {
        val notifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                UUID.randomUUID().toString(),
                "大视图通知",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notifyMgr.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, channel.id)
        } else {
            //大视图通知需要通过NotificationCompat.Builder来构建
            builder = NotificationCompat.Builder(this)
        }

        //声明一个"取消"按钮扽动作意图
        val cancelIntent = Intent(this, NotifyLargeActivity::class.java)
        val piCancel = PendingIntent.getActivity(
            this,
            R.string.app_name,
            cancelIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        //声明一个"前往"按钮的动作意图
        val confirmIntent = Intent(this, NotifyLargeActivity::class.java)
        val piConfirm = PendingIntent.getActivity(
            this,
            R.string.app_name,
            confirmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        builder.setSmallIcon(R.drawable.ic_app)
            .setTicker("大视图消息来啦")
            .setWhen(System.currentTimeMillis())
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_app))
            .setContentTitle(title)
            .setContentText(message)
            .setDefaults(Notification.DEFAULT_ALL) //设置大视图通知的提醒方式
            .setStyle(style) //设置大视图通知的风格类型
            .addAction(R.drawable.icon_cancel, "取消", piCancel) //添加取消按钮
            .addAction(R.drawable.icon_confirm, "前往", piConfirm) //添加前往按钮

        val notify = builder.build()
        notifyMgr.notify(1, notify)

    }
}
