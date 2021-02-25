package com.jimmy.simple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //6.1按钮的使用
        var intent1 = Intent(this, ButtonUseActivity::class.java);
        btn_use.setOnClickListener { startActivity(intent1) }

        //6.2.1 线性布局
        var intent2 = Intent(this, LinearActivity::class.java);
        btn_linear.setOnClickListener { startActivity(intent2) }

        //6.2.2 相对布局
        var intent3 = Intent(this, RelativeLayoutActivity::class.java);
        btn_relative.setOnClickListener { startActivity(intent3) }

        //6.2.3 约束布局
        var intent4 = Intent(this, ConstraintLayoutActivity::class.java);
        btn_constrain.setOnClickListener { startActivity(intent4) }

        //6.3 图文控件
        var intent5 = Intent(this, TextMarqueeActivity::class.java);
        btn_marquee.setOnClickListener { startActivity(intent5) }

        //6.3.2 图像视图
        var intent6 = Intent(this, ImageActivity::class.java);
        btn_img.setOnClickListener { startActivity(intent6) }

        //6.3.3 文本编辑框
        var intent7 = Intent(this, EditTextActivity::class.java);
        btn_edt.setOnClickListener { startActivity(intent7) }

        //6.4.1 Activity之间数据传递
        var intent8 = Intent(this@MainActivity, ActFirstActivity::class.java);
        btn_intent.setOnClickListener { startActivity(intent8) }

        //6.4.2 传递序列化数据
        var intent9 = Intent(this@MainActivity, ActRequestActivity::class.java);
        btn_intent2.setOnClickListener { startActivity(intent9) }

        //6.4.3 跳转制定启动模式
        var intent10 = Intent(this@MainActivity, ActFirstActivity::class.java);
        btn_intent3.setOnClickListener { startActivity(intent10) }


        //6.5.1 AlertDialog
        var intent11 = Intent(this@MainActivity, AlertDialogActivity::class.java);
        btn_intent4.setOnClickListener { startActivity(intent11) }

        //6.5.2 登陆页面
        var intent12 = Intent(this, LoginPageActivity::class.java);
        btn_intent5.setOnClickListener { startActivity(intent12) }
    }
}
