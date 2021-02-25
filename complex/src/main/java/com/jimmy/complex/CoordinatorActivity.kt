package com.jimmy.complex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_coordinator.*

class CoordinatorActivity : AppCompatActivity() {

    private var floating_show = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator)

        btn_snackbar.setOnClickListener {
            Snackbar.make(cl_main, "这是个提示条", Snackbar.LENGTH_SHORT).show()
        }

        btn_floating.setOnClickListener {
            if (floating_show) {
                fab_btn.hide()
                btn_floating.text = "显示悬浮按钮"
            } else {
                fab_btn.show()
                btn_floating.text = "隐藏悬浮按钮"
            }
            floating_show = !floating_show
        }
    }

}
