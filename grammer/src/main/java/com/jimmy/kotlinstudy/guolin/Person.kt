package com.jimmy.kotlinstudy.guolin

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/4/29
 * @Version:        1.0
 */
open class Person(val name: String, val age: Int) {
    fun eat() {
        println("$name is eating. He is $age years old.")
    }
}
