package com.jimmy.kotlinstudy.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-08-25
 * @Version:        1.0
 */
//关键字object用来声明单利对象，就像Java中开发者定义的Utils工具类
//其内部属性等同于Java中的static静态属性，外部可直接获取属性指
object DateUtil {

    val nowDateTime: String
        get() {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return sdf.format(Date())
        }

    val nowDate: String
        get() {
            var sdf = SimpleDateFormat("yyyy-MM-dd")
            return sdf.format(Date())
        }

    val nowTime: String
        get() {
            var sdf = SimpleDateFormat("HH:mm:ss")
            return sdf.format(Date())
        }

    val nowTimeDetail: String
        get() {
            var sdf = SimpleDateFormat("HH:mm:ss.SSS")
            return sdf.format(Date())
        }

    fun getFormatTime(format: String = ""): String {
        var ft: String = format
        var sdf = if (!ft.isEmpty()) SimpleDateFormat(ft) else SimpleDateFormat("yyyyMMddHHmmss")
        return sdf.format(Date())
    }
}