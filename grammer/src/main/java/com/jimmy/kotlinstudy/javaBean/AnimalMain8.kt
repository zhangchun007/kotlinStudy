package com.jimmy.kotlinstudy.javaBean

import android.content.Context
import android.widget.Toast

/**
 * @Description: 成员方法
 * @Author:         纯仔
 * @CreateDate:     2020-08-26
 * @Version:        1.0
 */

class AnimalMain8(var name: String, val sex: Int = 0) {
    var sexName: String

    init {
        sexName = if (sex == 0) "公" else "母"
    }

    fun getDesc(tag: String): String {
        return "欢迎来到$tag：这只${name}是${sexName}的"
    }
}