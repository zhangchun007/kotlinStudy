package com.jimmy.kotlinstudy.javaBean

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-22
 * @Version:        1.0
 */

//kotlin 默认每个类都不能被继承，如果想让某个类成为基类，就需要把该类开放出来，也就是添加关键字open作为修饰符
/**
 * kotlin的开放性修饰符的取值说明
 *
 * public：对所有人开放，kotlin的类，函数，变量不加开放性修饰符的话，默认就是public类型
 * internal:只对本模块内部开放，这是kotlin新增的关键字。对于app开发来说，本模块是指app自身
 * protected:只对自己和子类开放
 * private：只对自己开放，即私有
 */
open class Bird(var name: String, val sex: Int = 0) {

    var sexName: String

    init {
        sexName = getSexName(sex)
    }

    open protected fun getSexName(sex: Int): String {
        return if (sex == MALE) "公" else "母"
    }

    open fun getDesc(tag: String): String {
        return "欢迎来到$tag：这只${name}是${sexName}的"
    }

    companion object BirdStatic {
        val MALE = 0
        val FEMALE = 1
        val UNKNOWN = -1
        fun judgeSex(sexName: String): Int {
            var sex: Int = when (sexName) {
                "公", "雄" -> MALE
                "母", "雌" -> FEMALE
                else -> UNKNOWN
            }
            return sex
        }
    }
}