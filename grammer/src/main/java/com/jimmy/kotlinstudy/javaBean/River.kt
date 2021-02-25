package com.jimmy.kotlinstudy.javaBean

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-24
 * @Version:        1.0
 */
class River<T>(var name: String, var length: T) {
    fun getInfo(): String {
        var unit: String = when (length) {
            is String -> "米"
            is Number -> "m"
            else -> " "
        }
        return "${name}的长度是$length$unit"
    }
}