package com.jimmy.kotlinstudy.guolin

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/8/3
 * @Version:        1.0
 * 这里我们在泛型T的声明前面加上了一个in关键字。这就意味着现在T只能 出现在in位置上，而不能出现在out位置上，同时也意味着Transformer 在泛型T上是逆变的。

 */
interface Transformers<in T> {
    fun transform(t: T): String
}