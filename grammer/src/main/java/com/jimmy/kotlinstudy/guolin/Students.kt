package com.jimmy.kotlinstudy.guolin

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/4/29
 * @Version:        1.0
 */
//注意这里的代码变化，首先Student类的后面没有显式地定义主构造函 数，同时又因为定义了次构造函数，所以现在Student类是没有主构造函 数的。
// 那么既然没有主构造函数，继承Person类的时候也就不需要再加上 括号了
class Students : Person {
    //当一个 类没有显式地定义主构造函数且定义了次构造函数时，它就是没有主构造 函数的。
    constructor(name: String, age: Int) : super(name, age) {
    }
}