package com.jimmy.coroutines

import java.text.SimpleDateFormat
import java.util.*

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2021-01-06
 * @Version:        1.0
 */
object LogUtils{

    val dateFormat = SimpleDateFormat("HH:mm:ss:SSS")
    val now = {
        dateFormat.format(Date(System.currentTimeMillis()))
    }

    fun log(msg: Any?) = println("${now()} [${Thread.currentThread().name}] $msg")
}