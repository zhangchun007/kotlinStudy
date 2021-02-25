package com.jimmy.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jimmy.storage.util.DateUtil
import kotlinx.android.synthetic.main.activity_app_write.*
import java.util.*

class AppWriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_write)
        btn_save.setOnClickListener {
            val app = MainApplication.instance()
            app.mInfoMap.put("name", et_name.text.toString())
            app.mInfoMap.put("age", et_age.text.toString())
            app.mInfoMap.put("height", et_height.text.toString())
            app.mInfoMap.put("weight", et_weight.text.toString())
            app.mInfoMap.put("update_time", DateUtil.nowDateTime)
        }
    }
}
