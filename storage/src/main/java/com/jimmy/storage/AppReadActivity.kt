package com.jimmy.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_app_read.*

class AppReadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_read)
        readAppMemory()
    }

    private fun readAppMemory() {
        var desc = "全局内存中保存的信息如下："
        val mapParam = MainApplication.instance().mInfoMap
        for (item_map in mapParam) {
            desc = "$desc\n　${item_map.key}的取值为${item_map.value}"
        }
        if (mapParam.isEmpty()) {
            desc = "全局内存中保存的信息为空"
        }
        tv_app.text = desc
    }
}
