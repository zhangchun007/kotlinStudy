package com.jimmy.kotlinstudy.javaBean

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-22
 * @Version:        1.0
 */
class Duck1(name: String = "鸭子", sex: Int = Bird.MALE) : Bird(name, sex) {

    override fun getSexName(sex: Int): String {
        return if (sex == MALE) "雄" else "雌"
    }
}