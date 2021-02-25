package com.jimmy.kotlinstudy.javaBean

import android.content.Context
import android.widget.Toast

/**
 * @Description:  类中自定义变量
 * @Author:         纯仔
 * @CreateDate:     2020-08-26
 * @Version:        1.0
 */

class AnimalMain7(var name: String, val sex: Int = 0) {
    var sexName: String

    init {
        sexName = if (sex == 0) "公" else "母"
    }
}