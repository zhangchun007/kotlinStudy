package com.jimmy.kotlinstudy.javaBean

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-22
 * @Version:        1.0
 */
interface Behavior {
    open fun fly(): String

    fun swim(): String

    fun run(): String {
        return "大多数鸟儿跑的并不快,只有鸵鸟等少数鸟类才擅长奔跑"
    }

    //抽象属性
    var skilledSports: String
}