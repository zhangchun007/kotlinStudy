package com.jimmy.kotlinstudy.javaBean

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-24
 * @Version:        1.0
 */
sealed class SeasonSealed {
    //密封类内部的每个嵌套类都必须继承该类
    class Spring(var name: String) : SeasonSealed()
    class Summer(var name: String) : SeasonSealed()
    class Autumn(var name: String) : SeasonSealed()
    class Winter(var name: String) : SeasonSealed()
}