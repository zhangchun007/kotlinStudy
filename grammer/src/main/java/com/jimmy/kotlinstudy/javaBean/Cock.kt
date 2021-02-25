package com.jimmy.kotlinstudy.javaBean

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-22
 * @Version:        1.0
 */
class Cock(name: String = "鸡", sex: Int = Bird.MALE, voice: String = "喔喔喔") :
    Chicken(name, sex, voice) {

    override fun callOut(times: Int): String {
        var count = when {
            times <= 0 -> 0
            times >= 0 -> 9
            else -> times
        }
        return "$sexName$name${voice}叫了，${numberAarray[count]}声，原来它在报晓啊"
    }
}