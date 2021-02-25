package com.jimmy.kotlinstudy.javaBean

/**
 * @Description: 重写父类的方法
 * @Author:         纯仔
 * @CreateDate:     2020-09-22
 * @Version:        1.0
 */
/**
 * 注意父类Bird已经在构造函数声明了属性，故而子类Duck无需重复声明属性
 * 也就是说，子类扽构造函数在输入参数前面不需要加val 和 var
 */
class Duck(name: String = "鸭子", sex: Int = Bird.MALE) : Bird(name, sex) {

}