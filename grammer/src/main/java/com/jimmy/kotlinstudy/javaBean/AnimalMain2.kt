package com.jimmy.kotlinstudy.javaBean

import android.content.Context
import android.widget.Toast

/**
 * @Description: 二级构造函数
 * @Author:         纯仔
 * @CreateDate:     2020-08-26
 * @Version:        1.0
 */
class AnimalMain2(context: Context, name: String) {
    init {
        Toast.makeText(context, "这是只$name", Toast.LENGTH_LONG).show();
    }

    /**
     * 二级构造函数:
     * # 二级构造函数没有函数名称
     * # "this(context,name)相当于Java中的super(context,name)
     * # 二级构造函数从属于主构造函数，使用二级构造函数声明该类的实例，则优先调用祝构造函数，再调用二级构造函数
     */
    constructor(context: Context, name: String, sex: Int) : this(context, name) {
        var sexName: String = if (sex == 0) "公" else "母"
        Toast.makeText(context, "这只$name 是${sexName}的", Toast.LENGTH_LONG).show();
    }
}