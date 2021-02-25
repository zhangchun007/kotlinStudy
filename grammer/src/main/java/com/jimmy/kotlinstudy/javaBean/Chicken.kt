package com.jimmy.kotlinstudy.javaBean

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-22
 * @Version:        1.0
 */
abstract class Chicken(name: String, sex: Int, var voice: String) : Bird(name, sex) {
    val numberAarray: Array<String> = arrayOf("一", "二", "三", "四", "五", "六", "七", "八", "九", "十")

    //抽象方法必须在子类进行重写，所以可以省略关键字open，
    abstract fun callOut(times: Int): String
}
