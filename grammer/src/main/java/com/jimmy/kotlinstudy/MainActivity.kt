package com.jimmy.kotlinstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        tv_content.text = "这是我的第一个Kotlin的demo"
//        tv_content.setOnClickListener{
//            Toast.makeText(this, "hello Toast !", Toast.LENGTH_LONG).show();
//            tv_content.text="我被点击了一下"}

        var intent = Intent(this, SecondActivity::class.java);
        btn_one.setOnClickListener { startActivity(intent) }

        var intent2 = Intent(this, ThirdActivity::class.java);
        btn_two.setOnClickListener { startActivity(intent2) }

        var intent3 = Intent(this, FourActivity::class.java);
        btn_three.setOnClickListener { startActivity(intent3) }

        var intent4 = Intent(this, FiveActivity::class.java);
        btn_four.setOnClickListener { startActivity(intent4) }


        var intent5 = Intent(this, SixActivity::class.java);
        btn_six.setOnClickListener { startActivity(intent5) }

        var intent6 = Intent(this, FanXingActivity::class.java);
        btn_fanxing.setOnClickListener { startActivity(intent6) }



    }
}
