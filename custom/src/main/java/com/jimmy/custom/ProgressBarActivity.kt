package com.jimmy.custom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_progress_bar.*

class ProgressBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar)

        //设置最大进度
        pb_progress.max = 100
        //设置默认进度
        pb_progress.progress = 0
        //设置进度条图形
        pb_progress.progressDrawable = resources.getDrawable(R.drawable.progress_green)
        btn_progress.setOnClickListener {
            //根据输入的进度数值，展示进度条的当前进度
            pb_progress.progress = et_progress.text.toString().toInt()
        }
    }
}
