package com.jimmy.custom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jimmy.custom.service.MusicService
import kotlinx.android.synthetic.main.activity_notify_service.*

/**
 * 推送服务到前台
 */
class NotifyServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_service)
        var bPlay = false

        btn_send_service.setOnClickListener {
            bPlay = !bPlay
            val intent = Intent(this@NotifyServiceActivity, MusicService::class.java)
            intent.putExtra("is_play", bPlay)
            intent.putExtra("song", et_song.text.toString())
            if (bPlay) {
                startService(intent)
                Toast.makeText(
                    this@NotifyServiceActivity,
                    "歌曲${et_song.text}已在通知栏开始播放",
                    Toast.LENGTH_SHORT
                ).show()
                btn_send_service.text = "停止播放音乐"
            } else {
                stopService(intent)
                Toast.makeText(
                    this@NotifyServiceActivity,
                    "歌曲${et_song.text}已从通知栏清除",
                    Toast.LENGTH_SHORT
                ).show()
                btn_send_service.text = "开始播放音乐"
            }
        }
    }
}
