package com.jimmy.simple

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import kotlinx.android.synthetic.main.activity_text_marquee.*

/**
 * 跑马灯效果
 */
class TextMarqueeActivity : AppCompatActivity() {

    private var bPause = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_marquee)
        tv_marquee.text = "快讯：红色预警，超强台风泰利即将登陆，请居民关紧门窗，备足粮油，做好防汛救灾准备"

        tv_marquee.textSize = 17f
        tv_marquee.setTextColor(Color.BLACK)
        tv_marquee.setBackgroundColor(Color.WHITE)

        //左对齐且居中
        tv_marquee.gravity = Gravity.LEFT or Gravity.CENTER

        //从左像右滚动的的跑马灯
        tv_marquee.ellipsize = TextUtils.TruncateAt.MARQUEE
        //跑马灯效果务必设置单行显示
        tv_marquee.setSingleLine(true)


        tv_marquee.setOnClickListener {
            bPause = !bPause
            tv_marquee.isFocusable = if (bPause) false else true
            tv_marquee.isFocusableInTouchMode = if (bPause) false else true
        }


        /**
         * ellipsize 多余文本的显示方式
         * 1：TextUtils.TruncateAt.MARQUEE 跑马灯显示
         * 2：TextUtils.TruncateAt.END 省略号在末尾
         * 3：TextUtils.TruncateAt.MIDDLE 省略号在中间
         * 4：TextUtils.TruncateAt.START 省略号在开头
         */

        //⚠️：kotlin中的位运算符
        //1：按位与 and  (java中 &)
        //2：按位或 or  (java中 |)
        //3：按位异或 xor  (java中 ^)
        //4：按位左移 shl  (java中 <<)
        //5：按位右移 shr  (java中 >>)
        //6：无符号右移，高位补0 ushr  (java中 >>>)
    }
}