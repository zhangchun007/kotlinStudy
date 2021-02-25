package com.jimmy.custom

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.custom.util.DateUtil
import com.example.custom.util.vibrator
import kotlinx.android.synthetic.main.activity_text_progress.*
import kotlinx.android.synthetic.main.activity_vibrator.*

class VibratorActivity : AppCompatActivity() {

    private val durations = listOf(500, 1000, 2000, 3000, 4000, 5000)
    private val descs = listOf("0.5秒", "1秒", "2秒", "3秒", "4秒", "5秒")
    private var interval: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vibrator)
        //常规做法：从系统服务中获取震动器对象
//            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//            vibrator.vibrate(3000)
        //利用扩展函数获得震动器对象
//        getVibrator().vibrate(interval.toLong())
        //利用扩展函数实现扩展属性，直接使用vibrator即可指代震动器对象
        sp_duration.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, descs)

        sp_duration.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                interval = durations[position].toInt()
                vibrator.vibrate(interval.toLong())
                tv_vibrator.text = "${DateUtil.nowTime} 手机震动了${interval / 1000.0f}秒"
            }
        }

    }

    fun Context.getVibrator(): Vibrator {
        return getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }
}
