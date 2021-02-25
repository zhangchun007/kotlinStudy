package com.jimmy.custom

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_notify_custom.*

/**
 * 远程视图RemoteViews
 *
 * RemoteViews与Activity的区别
 *(1) RemoteViews主要 用于通知栏部件和桌面部件的布局，而Activity 用于活动页面的布局。
 *(2) RemoteViews只支持少数几种控件，如TextView、ImageView、 Button、ImageButton. ProgressBar, Chronometer (计时器) . AnalogClock (模拟时钟)
 *(3) RemoteViews不可直接获取和设置控件信息，只能通过该对象的set方法来修改控件信息。
 *
 * 下面是远程视图RemoteViews的常用方法。
 * ●构造函数:创建一个RemoteViews对象。 第一个参数是包名，第二个参数是布局文件id。
 * ●setViewVisibility: 设置指定控件是否可见。
 * ●setViewPadding: 设置指定控件的间距。
 * ●setTextViewText: 设置指定TextView或Button控件的文字内容。
 * ●setTextViewTextSize: 设置指定TextView或Button控件的文字大小。
 * ●setTextColor: 设置指定TextView或Button控件的文字颜色。
 * ●setrTextViewCompoundDrawables :设置指定TextView或Button控件的文字周围图标。
 * ●setImageViewResource: 设置ImageView或ImgacButton控件的资源编号。
 * ●setmageViewBimap: 设置ImageView或ImgacButton控件的位图对象。
 * ●setChronometer: 设置计时器信息。
 * ●setProgressBar: 设置进度条信息，包括最大值与当前进度。
 * ●setOnClickPendingIntent: 设置指定控件的点击响应动作。
 *
 * 完成RemoteViews对象的构建与设置之后，再调用Notification.Builder对象的setContent方
 * 法，即可完成自定义通知布局的设置。下面是一个远程视图用到的播放器通知布局文件例子:
 */
class NotifyCustomActivity : AppCompatActivity() {

    lateinit var mLocalBroadcastManager: LocalBroadcastManager

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_custom)
        val PAUSE_EVENT = resources.getString(R.string.pause_event)

        initClickEvent(PAUSE_EVENT)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun initClickEvent(PAUSE_EVENT: String) {
        btn_send_custom.setOnClickListener {

            val contentView = getNotifyMusic(
                this,
                PAUSE_EVENT,
                et_song.text.toString(),
                true,
                50,
                SystemClock.elapsedRealtime()
            )

            sendCustomNotify(this, et_song.text.toString(), contentView, null)
        }


        //自定义折叠式通知
        btn_send_expand.setOnClickListener {
            val contentView=getNotifyMusic(this,PAUSE_EVENT,et_song.text.toString(),true,50,SystemClock.elapsedRealtime())

            val bigContentView = getNotifyExpand(this, PAUSE_EVENT,
                et_song.text.toString(), true, 50, SystemClock.elapsedRealtime())

            sendCustomNotify(this, et_song.text.toString(), contentView, bigContentView)
        }


    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun sendCustomNotify(
        ctx: Context,
        song: String,
        contentView: RemoteViews,
        bigContentView: RemoteViews?
    ) {
        val notifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder: Notification.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("101", "自定义通知视图的通知", NotificationManager.IMPORTANCE_DEFAULT)
            notifyMgr.createNotificationChannel(channel)
            builder = Notification.Builder(ctx, channel.id)
        } else {
            builder = Notification.Builder(ctx)
        }
        //声明一个点击通知消息时触发的动作意图
        val intent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            ctx,
            R.string.app_name,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(contentIntent)
            .setContent(contentView) //采用自定义的通知布局
            .setTicker(song)
            .setSmallIcon(R.drawable.tt_s)

        val notify = builder.build()
        //如果builder中没有配置setContent，也可以在此配置，两者最终都指向一个变量
        //        notify.contentView = contentView
        if (bigContentView != null) {
            //展开后的自定义通知
            notify.bigContentView = bigContentView
        }
        notifyMgr.notify(R.string.app_name, notify)
    }

    //获取播放器的通知栏布局
    private fun getNotifyMusic(
        ctx: Context,
        actionStr: String,
        song: String,
        isPlay: Boolean,
        progress: Int,
        time: Long
    ): RemoteViews {
        //从notify_music.xml布局文件构造远程视图对象
        val notify_music = RemoteViews(ctx.packageName, R.layout.notify_music)
        if (isPlay) {
            notify_music.setTextViewText(R.id.btn_play, "暂停")
            notify_music.setTextViewText(R.id.tv_play, song + "正在播放")
            notify_music.setChronometer(R.id.chr_play, time, "%s", true)
        } else {
            notify_music.setTextViewText(R.id.btn_play, "继续")
            notify_music.setTextViewText(R.id.tv_play, song + "暂停播放")
            notify_music.setChronometer(R.id.chr_play, time, "%s", false)
        }
        notify_music.setProgressBar(R.id.pb_play, 100, progress, false)

        //设置暂停/继续按钮的点击动作对应的广播事件
        val pIntent = Intent(actionStr)
        val piPause = PendingIntent.getBroadcast(
            ctx,
            R.string.app_name,
            pIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        notify_music.setOnClickPendingIntent(R.id.btn_play, piPause)

        return notify_music

    }

    //获取折叠视图展开后的通知栏布局
    private fun getNotifyExpand(ctx: Context, event: String, song: String, isPlay: Boolean, progress: Int, time: Long): RemoteViews {
        //从notify_expand.xml布局文件构造远程视图对象
        val notify_expand = RemoteViews(ctx.packageName, R.layout.notify_expand)
        if (isPlay) {
            notify_expand.setTextViewText(R.id.btn_play, "暂停")
            notify_expand.setTextViewText(R.id.tv_play, song + "正在播放")
            notify_expand.setChronometer(R.id.chr_play, time, "%s", true)
        } else {
            notify_expand.setTextViewText(R.id.btn_play, "继续")
            notify_expand.setTextViewText(R.id.tv_play, song + "暂停播放")
            notify_expand.setChronometer(R.id.chr_play, time, "%s", false)
        }
        notify_expand.setProgressBar(R.id.pb_play, 100, progress, false)

        //设置播放按钮的点击动作对应的广播事件
        val pIntent = Intent(event)
        val piPause = PendingIntent.getBroadcast(
            ctx, R.string.app_name, pIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        notify_expand.setOnClickPendingIntent(R.id.btn_play, piPause)

        //设置返回按钮的点击动作对应的跳转事件
        val bIntent = Intent(ctx, NotifyCustomActivity::class.java)
        val piBack = PendingIntent.getActivity(ctx,
            R.string.app_name, bIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        notify_expand.setOnClickPendingIntent(R.id.btn_back, piBack)
        return notify_expand
    }
}
