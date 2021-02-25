package com.jimmy.kotlinstudy.javaBean

import android.content.Context
import android.widget.Toast

/**
 * @Description:  给Java代码用的
 * @Author:         纯仔
 * @CreateDate:     2020-08-26
 * @Version:        1.0
 */

//JvmOverloads 让Java代码识别该类
//添加了JvmOverloads标记，所以必须补上关键字constructor
class AnimalMain5 @JvmOverloads constructor(context: Context, name: String, sex: Int = 0) {
    init {
        var sexName: String = if (sex == 0) "公" else "母"
        Toast.makeText(context, "这只$name 是${sexName}的", Toast.LENGTH_LONG).show();
    }
}