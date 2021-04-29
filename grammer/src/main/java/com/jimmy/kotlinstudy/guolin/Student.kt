package com.jimmy.kotlinstudy.guolin

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/4/29
 * @Version:        1.0
 *继承一个类时要调用：Person()括号的原因：
 *
 *但是这和那对括号又有什么关系呢?这就 涉及了Java继承特性中的一个规定，子类中的构造函数必须调用父类中的 构造函数，这个规定在Kotlin中也要遵守。
 *
 * 而如果我们将Person改造一下，将姓名和年龄都放到主构造函数当中，如 下所示:
 *
 * 如果我们想解决这个错误的话，就必须给Person类的构造函数传入name 和age字段，可是Student类中也没有这两个字段呀。
 * 很简单，没有就加 呗。我们可以在Student类的主构造函数中加上name和age这两个参数， 再将这两个参数传给Person类的构造函数，代码如下所示
 *
 * 注意，我们在Student类的主构造函数中增加name和age这两个字段时， 不能再将它们声明成val，因为在主构造函数中声明成val或者var的参数 将自动成为该类的字段，
 * 这就会导致和父类中同名的name和age字段造成 冲突。因此，这里的name和age参数前面我们不用加任何关键字，让它的 作用域仅限定在主构造函数当中即可。
 *
 *
 */
class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age) {

    //Kotlin规定，当一个类既有主构造函数又有次构造函数时，所有的次构造 函数都必须调用主构造函数(包括间接调用)。这里我通过一个具体的例 子就能简单阐明，代码如下:
    constructor(name: String, age: Int) : this("", 0, name, age)
    constructor() : this("", 0)


}