package com.jimmy.kotlinstudy.javaBean

import android.content.Context
import android.widget.Toast

/**
 * @Description:  带默认参数的构造函数
 * @Author:         纯仔
 * @CreateDate:     2020-08-26
 * @Version:        1.0
 */
class AnimalMain4(context: Context, name: String, sex: Int = 0) {
    init {
        var sexName: String = if (sex == 0) "公" else "母"
        Toast.makeText(context, "这只$name 是${sexName}的", Toast.LENGTH_LONG).show();
    }
}