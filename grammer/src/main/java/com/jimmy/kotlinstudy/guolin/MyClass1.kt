package com.jimmy.kotlinstudy.guolin

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/8/2
 * @Version:        1.0
 */
class MyClass1 {
    fun <T> method(param: T): T {
        return param
    }

    //这种写法就表明，我们只能将method()方法的泛型指定成数字类型，比如 Int、Float、Double等。但是如果你指定成字符串类型，就肯定会报 错，因为它不是一个数字。
    fun<T:Number>method(param:T):T{
        return  param
    }
}