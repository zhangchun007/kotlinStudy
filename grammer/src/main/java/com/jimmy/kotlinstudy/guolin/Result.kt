package com.jimmy.kotlinstudy.guolin

import java.lang.Exception

/**
 * @Description: 密封类
 * @Author:         zhangchun
 * @CreateDate:     2021/4/29
 * @Version:        1.0
 */
//这里定义了一个Result接口，用于表示某个操作的执行结果，接口中不用 编写任何内容。然后定义了两个类去实现Result接口:
// 一个Success类用 于表示成功时的结果，一个Failure类用于表示失败时的结果
//interface Result
//class Success(val msg: String) : Result
//class Failure(val error: Exception) : Result

//接下来再定义一个getResultMsg()方法，用于获取最终执行结果的信 息
fun getResultMsg(result: Result) = when (result) {
    is Success -> result.msg
    is Failure -> result.error.message
}

//如果我们新增了一个 Unknown类并实现Result接口，用于表示未知的执行结果，但是忘记在 getResultMsg()方法中添加相应的条件分支，
// 编译器在这种情况下是不 会提醒我们的，而是会在运行的时候进入else条件里面，从而抛出异常并 导致程序崩溃。

//密封类可以解决这个问题
//密封类的关键字是sealed class 它的用法同样非常简单，我们可以轻 松地将Result接口改造成密封类的写法:
sealed class Result
class Success(val msg: String) : Result()
class Failure(val error: Exception) : Result()

//可以看到，代码并没有什么太大的变化，只是将interface关键字改成了 sealed class
// 另外，由于密封类是一个可继承的类，因此在继承它的 时候需要在后面加上一对括号
//那么改成密封类之后有什么好处呢?
//你会发现现在getResultMsg()方法 中的else条件已经不再需要了，如下所示:

//?这是因为当在when语句中 传入一个密封类变量作为条件时，Kotlin编译器会自动检查该密封类有哪 些子类，并强制要求你将每一个子类所对应的条件全部处理。
// 这样就可以 保证，即使没有编写else条件，也不可能会出现漏写条件分支的情况。
// 而 如果我们现在新增一个Unknown类，并也让它继承自Result，此时 getResultMsg()方法就一定会报错，必须增加一个Unknown的条件分支 才能让代码编译通过。

//另外再多说一句，密封类及其所 有子类只能定义在同一个文件的顶层位置，不能嵌套在其他类中，这是被 密封类底层的实现机制所限制的。