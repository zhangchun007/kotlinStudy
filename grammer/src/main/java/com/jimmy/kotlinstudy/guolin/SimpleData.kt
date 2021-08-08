package com.jimmy.kotlinstudy.guolin

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/8/3
 * @Version:        1.0
 */
class SimpleData<T> {
    private var data: T? = null

    fun set(t: T?) {//T 在in的位置
        data = t
    }

    fun get(): T? {//T 在out的位置
        return data
    }
}