package com.jimmy.custom.service

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.*
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import com.jimmy.custom.MainActivity
import com.jimmy.custom.R

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-11-10
 * @Version:        1.0
 */
class MusicService : Service() {
    private val mBinder = LocalBinder()
    private var mBaseTime: Long = 0
    private var isPlay = true
    private var mSong: String = ""
    private var mProgress = 0
    private var PAUSE_EVENT = ""
    private var mPauseTime: Long = 0
    private var pauseReceiver: PauseReceiver? = null
    private val handler = Handler()

    private val playTask = object : Runnable {
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        override fun run() {
            if (isPlay) {
                if (mProgress < 100) {
                    mProgress += 2
                } else {
                    mProgress = 0
                }
                handler.postDelayed(this, 1000)
            }
            val notify =
                getNotify(this@MusicService, PAUSE_EVENT, mSong, isPlay, mProgress, mBaseTime)
            //持续刷新通知栏上的播放进度
            startForeground(2, notify)
        }

    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun getNotify(
        ctx: Context,
        event: String,
        song: String,
        isPlay: Boolean,
        progress: Int,
        time: Long
    ): Notification {
        val pIntent = Intent(event)
        val nIntent = PendingIntent.getBroadcast(
            ctx,
            R.string.app_name, pIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notify_music = RemoteViews(ctx.packageName, R.layout.notify_music)

        if (isPlay) {
            notify_music.setTextViewText(R.id.btn_play, "暂停")
            notify_music.setTextViewText(R.id.tv_play, "${song}正在播放")
            notify_music.setChronometer(R.id.chr_play, time, "%s", true)
        } else {
            notify_music.setTextViewText(R.id.btn_play, "继续")
            notify_music.setTextViewText(R.id.tv_play, "${song}暂停播放")
            notify_music.setChronometer(R.id.chr_play, time, "%s", false)
        }
        notify_music.setProgressBar(R.id.pb_play, 100, progress, false)
        notify_music.setOnClickPendingIntent(R.id.btn_play, nIntent)

        val builder: Notification.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("102", "前台服务——通知形式展现", NotificationManager.IMPORTANCE_DEFAULT)
            val notifyManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notifyManager.createNotificationChannel(channel)
            builder = Notification.Builder(ctx, channel.id)
        } else {
            builder = Notification.Builder(ctx)
        }

        val intent = Intent(ctx, MainActivity::class.java)
        val cIntent = PendingIntent.getActivity(
            ctx,
            R.string.app_name, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        return builder.setContentIntent(cIntent)
            .setContent(notify_music)
            .setTicker(song)
            .setSmallIcon(R.drawable.tt_s).build()
    }

    override fun onBind(intent: Intent?): IBinder? = mBinder

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        mBaseTime = SystemClock.elapsedRealtime()
        Log.e("服务——mBaseTime初始化", mBaseTime.toString())
        isPlay = intent.getBooleanExtra("is_play", true)
        mSong = intent.getStringExtra("song")
        handler.postDelayed(playTask, 200)
        return Service.START_STICKY
    }

    override fun onCreate() {
        PAUSE_EVENT = resources.getString(R.string.pause_event)
        pauseReceiver = PauseReceiver()
        registerReceiver(pauseReceiver, IntentFilter(PAUSE_EVENT))
        super.onCreate()


    }

    override fun onDestroy() {
        unregisterReceiver(pauseReceiver)
        super.onDestroy()
    }

    inner class PauseReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                isPlay = !isPlay
                if (isPlay) {
                    handler.postDelayed(playTask, 200)
                    if (mPauseTime > 0) {
                        val gap = SystemClock.elapsedRealtime() - mPauseTime
                        Log.e("服务——gap", gap.toString())
                        mBaseTime += gap
                        Log.e("服务——mBaseTime", mBaseTime.toString())
                    } else {
                        mPauseTime = SystemClock.elapsedRealtime()
                        Log.e("服务——mPauseTime", mPauseTime.toString())
                    }
                }
            }
        }

    }

    inner class LocalBinder : Binder() {
        val service: MusicService
            get() = this@MusicService
    }
}