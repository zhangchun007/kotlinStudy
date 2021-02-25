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
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_notify_special.*
import java.util.*

/**
 * 三种特殊的通知类型
 * 1：进度通知
 * 2：浮动通知
 *    浮动通知指的是不离开当前页面并在屏幕顶部悬挂显示的消息通知，该功能需要Android
 *    5.0及更高版本的系统支持。浮动通知的应用场景挺广的，包括但不限于收到新的短信、
 *   微信群有人发红包等。设置浮动通知的关键是调用Notification.Builder对象的
 *   setFullScreenIntent方法，该方法指定了浮动窗的点击事件以及优先级。
 *
 * 3:锁屏通知
 * 锁屏通知指的是在锁屏界面依然提示通知内容的消息通知，该功能需要Android 5.0及更
 * 高版本的系统支持。设置锁屏通知的关键是调用Notification.Builder对象的setVisibility方
 * 法，该方法用于指定通知消息在锁屏状态下的显示方式，显示方式的取值说明见表9-4。
 *
 * Notification.VISIBILITY_PUBLIC：显示通知的全部类型
 * Notification.VISIBILITY_PRIVATE ：显示基本信息，如通知的图标，但隐藏通知的全部内容
 * Notification.VISIBILITY_SECRET: 不显示任何内容，包括图标
 *
 *
 */
class NotifySpecialActivity : AppCompatActivity() {

    private val handler = Handler()
    private var count = 0
    private var refreshNotify: ProgressNotify? = null


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_special)
        btn_notify_progress.setOnClickListener { v -> clickNotify(v) }
        btn_notify_float.setOnClickListener { v -> clickNotify(v) }
        btn_lock_private.setOnClickListener { v -> clickNotify(v) }
        btn_lock_public.setOnClickListener { v -> clickNotify(v) }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun clickNotify(v: View) {
        val title = et_title.text.toString()
        val message = et_message.text.toString()
        when (v.id) {
            R.id.btn_notify_progress -> startProgressNotify(title, message)
            R.id.btn_notify_float -> sendFloatNotify(title, message)
            R.id.btn_lock_private -> sendLockNotify(title, message, false)
            R.id.btn_lock_public -> sendLockNotify(title, message, true)
        }
    }

    private fun startProgressNotify(title: String, message: String) {
        count = 0
        refreshNotify = ProgressNotify(title, message)
        handler.post(refreshNotify)

    }


    private inner class ProgressNotify(private val title: String, private val message: String) :
        Runnable {
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        override fun run() {
            sendProgressNotify(title, message, count)
            count++
            if (count <= 100) {
                handler.postDelayed(refreshNotify, 200)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun sendProgressNotify(title: String, message: String, progress: Int) {
        val notifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder: Notification.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //8.0及以后版本必须声明channel
            val notifyChannel = NotificationChannel(
                UUID.randomUUID().toString(),
                "简单通知",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notifyMgr.createNotificationChannel(notifyChannel)
            val notifyChannelID: String = notifyChannel.id
            builder = Notification.Builder(this, notifyChannelID)
        } else {
            builder = Notification.Builder(this)
        }

        val clickIntent = Intent(this, MainActivity::class.java)
        val piClick = PendingIntent.getActivity(
            this,
            R.string.app_name, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        builder.setContentIntent(piClick)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_app)
            .setTicker("进度通知来啦")
            .setWhen(System.currentTimeMillis())
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_app))
            .setContentTitle(title)
            .setContentText(message)
            .setProgress(100, progress, false) //设置进度条的当前进度
        val notify = builder.build()
        notifyMgr.notify(R.string.app_name, notify)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun sendFloatNotify(title: String, message: String) {

        val notifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder: Notification.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //8.0及以后版本必须声明channel
            val notifyChannel =
                NotificationChannel("99", "a浮窗通知", NotificationManager.IMPORTANCE_DEFAULT)
            notifyMgr.createNotificationChannel(notifyChannel)
            val notifyChannelID: String = notifyChannel.id
            builder = Notification.Builder(this, notifyChannelID)
        } else {
            builder = Notification.Builder(this)
        }

        val clickIntent = Intent(this, MainActivity::class.java)
        val piClick = PendingIntent.getActivity(
            this,
            R.string.app_name, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(piClick)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_app)
            .setTicker("浮动通知来啦")
            .setWhen(System.currentTimeMillis())
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_app))
            .setContentTitle(title)
            .setContentText(message)
            .setFullScreenIntent(piClick, true) //设置浮动窗的点击事件
        val notify = builder.build()
        notifyMgr.notify(R.string.app_name, notify)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun sendLockNotify(title: String, message: String, visibile: Boolean) {

        val notifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder: Notification.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //8.0及以后版本必须声明channel
            val notifyChannel =
                NotificationChannel("100", "锁屏通知", NotificationManager.IMPORTANCE_DEFAULT)
            notifyMgr.createNotificationChannel(notifyChannel)
            val notifyChannelID: String = notifyChannel.id
            builder = Notification.Builder(this, notifyChannelID)
        } else {
            builder = Notification.Builder(this)
        }

        val clickIntent = Intent(this, MainActivity::class.java)
        val piClick = PendingIntent.getActivity(
            this,
            R.string.app_name, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        builder.setContentIntent(piClick)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_app)
            .setTicker("锁屏通知来啦")
            .setWhen(System.currentTimeMillis())
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_app))
            .setContentTitle(title)
            .setContentText(message)
        //设置锁屏通知的可见性
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Toast.makeText(this, "锁屏通知需要5.0以上系统支持。", Toast.LENGTH_SHORT).show()
            return
        } else {
            Toast.makeText(this, "已推送锁屏通知。", Toast.LENGTH_SHORT).show()
            builder.setVisibility(if (visibile) Notification.VISIBILITY_PUBLIC else Notification.VISIBILITY_PRIVATE)
        }
        val notify = builder.build()
        //获取系统的通知管理器
        notifyMgr.notify(R.string.app_name, notify)
    }
}
