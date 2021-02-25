package com.jimmy.custom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jimmy.custom.bean.GoodsInfo
import kotlinx.android.synthetic.main.activity_custom_property.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_CustomProperty.setOnClickListener {
            var intent1 = Intent(this, CustomPropertyActivity::class.java);
            startActivity(intent1)
        }
        btn_measure.setOnClickListener {
            var intent = Intent(this, MeasureViewActivity::class.java);
            startActivity(intent)
        }
        btn_draw.setOnClickListener {
            var intent = Intent(this, DrawRoundActivity::class.java);
            startActivity(intent)
        }
        btn_runnable.setOnClickListener {
            var intent = Intent(this, RunnableActivity::class.java);
            startActivity(intent)
        }
        btn_progress.setOnClickListener {
            var intent = Intent(this, ProgressBarActivity::class.java);
            startActivity(intent)
        }
        btn_text_progress.setOnClickListener {
            var intent = Intent(this, TextProgressActivity::class.java);
            startActivity(intent)
        }
        btn_text_progress_animal.setOnClickListener {
            var intent = Intent(this, ProgressAnimationActivity::class.java);
            startActivity(intent)
        }
        btn_simple_notification.setOnClickListener {
            var intent = Intent(this, NotifySimpleActivity::class.java);
            startActivity(intent)
        }
        btn_counter_notification.setOnClickListener {
            var intent = Intent(this, NotifyCounterActivity::class.java);
            startActivity(intent)
        }
        btn_large_notification.setOnClickListener {
            var intent = Intent(this, NotifyLargeActivity::class.java);
            startActivity(intent)
        }
        btn_special_notification.setOnClickListener {
            var intent = Intent(this, NotifySpecialActivity::class.java);
            startActivity(intent)
        }
        btn_custom_notification.setOnClickListener {
            var intent = Intent(this, NotifyCustomActivity::class.java);
            startActivity(intent)
        }
        btn_service_normal.setOnClickListener {
            var intent = Intent(this, ServiceNormalActivity::class.java);
            startActivity(intent)
        }
        btn_service_bind.setOnClickListener {
            var intent = Intent(this, ServiceBindActivity::class.java);
            startActivity(intent)
        }
        btn_vibrator.setOnClickListener {
            var intent = Intent(this, VibratorActivity::class.java);
            startActivity(intent)
        }
    }
}
