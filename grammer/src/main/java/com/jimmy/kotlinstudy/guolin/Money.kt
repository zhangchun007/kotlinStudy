package com.jimmy.kotlinstudy.guolin

/**
 * @Description: 运算符重载
 * @Author:         zhangchun
 * @CreateDate:     2021/4/30
 * @Version:        1.0
 */
class Money(var value: Int) {
    operator fun plus(money: Money): Money {
        val sum = value + money.value
        return Money(sum)
    }

    operator fun plus(newValue: Int): Money {
        val sum = value + newValue
        return Money(sum)
    }
}