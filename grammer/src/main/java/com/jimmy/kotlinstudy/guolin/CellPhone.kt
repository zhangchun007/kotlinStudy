package com.jimmy.kotlinstudy.guolin

/**
 * @Description: 数据类
 * @Author:         zhangchun
 * @CreateDate:     2021/4/29
 * @Version:        1.0
 *
 * 数据类会根据主构造函数中的参数帮你将equals()、 hashCode()、toString()等固定且无实际逻辑意义的方法自动生成， 从而大大减少了开发的工作量。
 * 数据类必须有主构造函数
 */
data class CellPhone(val brand:String,val price:Double) {

}