package com.jimmy.custom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * 自定义视图的第三个步骤是重写绘图函数，绘图函数包括onDraw和dispatchDraw两种
 * 二者的区别是: onDraw 既出现在控件类视图又出现在布局类视图，而dispatchDraw只出现在布局类视图。
 *
 * 假设一个布局文件包含-一个线性布局LinearLayout节点，且LinearLayout节点下又包含-一个文本视图TextView节点，则它们之间的绘图函数调用顺序
 * 依次为:线性布局的onDraw-◆文本视图的onDraw-→线性布局的dispatchDraw.这个绘图次序意味着线性布局在onDraw函数中绘制的画面很可能被后来的文本视图涂鸦所覆盖，
 * 但最终定稿的却是线性布局在dispatchDraw函数中的绘图结果。借用“螳螂捕蝉，黄雀在后”的成语类比，
 * 此时线性布局的onDraw函数是蝉，文本视图的onDraw函数是螳螂，线性布局的dispatchDraw函数是黄雀。
 *
 * 讲完了onDraw与dispatchDraw两个函数之间的次序关系，也就弄清楚了两种绘图函数分别适用的场合，即控件视图只能重写onDraw函数，
 * 而布局视图若不想绘图效果被下级控件覆盖，则必须重写dispatchDraw函数。
 */
class DrawRoundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_round)
    }
}
