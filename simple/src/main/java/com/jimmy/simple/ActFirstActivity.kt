package com.jimmy.simple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jimmy.simple.util.DateUtil
import kotlinx.android.synthetic.main.activity_act_first.*

/**
 * activity之间数据传递
 */
class ActFirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_first)

        //activity之间数据传递
        btn_act_request.setOnClickListener {
            val intent = Intent(this, ActSecondActivity::class.java)
            intent.putExtra("request_time", DateUtil.nowTime)
            intent.putExtra("request_content", et_request.text.toString())
            startActivity(intent)
        }

        //指定启动模式
        //方式一：在AndroidManifest.xml中添加属性launchMode
        /**
         * launchMode值属性：
         * 1：standard 标准模式：每次都是重新创建该页面扽实例并放入栈尾
         * 2：singleTop:启动activity时，判断栈顶正好是该activity的实例，就重用该实刚:否则创建新的实例并放入栈顶，否有则的情况与standard类似
         * 3：singleTask:启动activity 判断栈中如果存在该activity的实侧，就重用该实例，并清徐位于该实例上面的所有实例:否则的情况处理standard
         * 4：singleInstance:启动activity时将该activity的实何放入一个新找中,原栈的实例列表保持不变
         */

        //方式二：在代码里面设置启动标志
        /**
         * 1：intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) 等同于standard模式
         * 2：intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP) 等同于singleTop模式
         * 3：intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
         *    当栈中存在待跳转的activity实例时，重新创建一个新实例，并将原来实例上方扽所有实例加以清除，该值与launchMode="singleTask"类似，
         *    但是launchMode="singleTask"采用onNewIntent启动原任务，而FLAG_ACTIVITY_CLEAR_TOP采用先onDestroy再onCreate创建新任务
         *
         * 4：intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
         * 与launchMode="standard"情况类似，但栈中不保存新启动扽activity实例。这样下次无论以何种方式再启动该实例，也要走standard扽完整流程
         *
         * 5： intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
         *     该标志非常暴力，跳转到新页面时，栈中的原有实例都被清空，该标示需要结合Intent.FLAG_ACTIVITY_NEW_TASK使用
         *
         */
        btn_act_flag.setOnClickListener {
            val intent = Intent(this, ActSecondActivity::class.java)
            intent.putExtra("request_time", DateUtil.nowTime)
            intent.putExtra("request_content", et_request.text.toString())

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }


    }
}
