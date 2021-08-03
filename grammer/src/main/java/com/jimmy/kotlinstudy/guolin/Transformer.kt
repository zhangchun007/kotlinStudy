package com.jimmy.kotlinstudy.guolin

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/8/3
 * @Version:        1.0
 * 可以看到，Transformer接口中声明了一个transform()方法，它接收 一个T类型的参数，并且返回一个String类型的数据，
 * 这意味着参数T在经 过transform()方法的转换之后将会变成一个字符串。至于具体的转换逻 辑是什么样的，则由子类去实现，Transformer接口对此并不关心。
 */
interface Transformer<T> {
    fun transform(t: T): String
}