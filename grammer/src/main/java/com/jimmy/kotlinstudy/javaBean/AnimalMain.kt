package com.jimmy.kotlinstudy.javaBean

import android.content.Context
import android.widget.Toast

/**
 * @Description: 构造函数
 * @Author:         纯仔
 * @CreateDate:     2020-08-26
 * @Version:        1.0
 */
class AnimalMain(context: Context, name: String) {
    init {
        Toast.makeText(context, "这是只$name", Toast.LENGTH_LONG).show();
    }
}