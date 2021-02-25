package com.jimmy.custom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_runnable.*

class RunnableActivity : AppCompatActivity() {

    private val handler = Handler()
    private var count = 0

    private var isStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_runnable)
        //Runnable对象在kotlin编码中的4种声明方式：
        btn_runnable.setOnClickListener {
            isStarted = !isStarted
            if (isStarted) {
                btn_runnable.text = "停止计数"
                //1：内部类
//                handler.post(counter)

                //2:匿名内部类
//                handler.post(counter)

                //3:匿名函数方式
//                handler.post(counter2)

                //4：匿名实例

                //第1点：在post方法中直接填写Runnable对象的定义代码
//                handler.post(Runnable {
//                    count++
//                    tv_result.text = "当前计数值为：$count"
//                })
                //第2点：如果该任务只需执行一次，则可采用匿名实例的方式，直接嵌入任务的执行代码
//                handler.post {
//                    count++
//                    tv_result.text = "当前计数值为：$count"
//                }
                //第3点：如果是延迟执行任务，则可将匿名实例作为postDelayed的输入参数
//                handler.postDelayed({
//                    count++
//                    tv_result.text = "当前计数值为：$count"
//                }, 1000)

            } else {
                btn_runnable.text = "开始计数"
                handler.removeCallbacks(counter)
            }

        }

    }

    //第一种：内部类方式
    inner private class Counter : Runnable {
        override fun run() {
            count++
            tv_result.text = "当前计数值为：$count"
            handler.postDelayed(this, 1000)
        }

    }

    //第二种：匿名内部类方式最正规
    //使用关键字object占位，表示这是一个匿名内部类
    private val counter = object : Runnable {
        override fun run() {
            count++
            tv_result.text = "当前计数值为：$count"
            handler.postDelayed(this, 1000)
        }
    }

    //第三种：匿名函数方式,(方式简洁，但是并不拥有类的完整结构，其内部都this关键字不再表示任务类自身，而是表示宿主activity类)
    //采取简化类实例扽任务对象适用于不需要重复刷新的场合
    private val counter2= Runnable {
        count++
        tv_result.text = "当前计数值为：$count"
    }


}
