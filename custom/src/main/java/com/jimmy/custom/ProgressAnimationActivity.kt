package com.jimmy.custom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_progress_animation.*

class ProgressAnimationActivity : AppCompatActivity() {

    private var mProgress = 0
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_animation)

        btn_animation.setOnClickListener {
            btn_animation.isEnabled = false
            //延时50毫秒开始进度动画
            handler.postDelayed(animation, 50)
        }
    }

    private val animation = object : Runnable {
        override fun run() {
            if (mProgress <=100) {
                tpb_progress.progress = mProgress
                tpb_progress.progressText = "当前处理进度为$mProgress%"
                //当前进度未满100%，则继续进度刷新动画
                handler.postDelayed(this, 50)
                mProgress++
            } else {
                //进度条动画结束，恢复初始进度数值
                mProgress = 0
                btn_animation.isEnabled = true
            }
        }
    }
}
