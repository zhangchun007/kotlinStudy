package com.jimmy.custom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_text_progress.*

class TextProgressActivity : AppCompatActivity() {

    private val progresses =
        listOf("0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_progress)
        sp_progress.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, progresses)

        sp_progress.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val progress = progresses[position].toInt()
                tpb_progress.progress = progress
                tpb_progress.progressText = "当前处理进度为$progress%"
            }
        }
    }
}
