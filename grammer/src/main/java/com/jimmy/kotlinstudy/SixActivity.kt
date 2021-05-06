package com.jimmy.kotlinstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.jimmy.kotlinstudy.guolin.CellPhone
import com.jimmy.kotlinstudy.guolin.Student
import com.jimmy.kotlinstudy.guolin.lettersCount
import kotlinx.android.synthetic.main.activity_six.*
import kotlin.math.max

/**
 * 看郭霖第一行代码的笔记
 */
class SixActivity : AppCompatActivity() {

    lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_six)
        //笔记一：当一个函数中只有一行代码时，Kotlin允许我们不必编写函数体，可以直 接将唯一的一行代码写在函数定义的尾部，中间用等号连接即可
        //比如 largerNumber()

        //笔记二：if控制条件可以作为值直接返回 比如largerNum()

        //笔记三：kotlin中的循环
        //Java中最常用的for-i循环在 Kotlin中直接被舍弃了，而Java中另一种for-each循环则被Kotlin进行了 大幅度的加强，变成了for-in循环，
        // 所以我们只需要学习for-in循环的 用法就可以了。

        //区间
        val range = 0..10 //相当于[0,10] 两端值都包含
        for (i in 0..10) {
            println(i)
        }
        //左开右闭区间[0,10) step 2相当于循环中i = i + 2的效果
        var range1 = 0 until 10
        for (i in 0 until 10 step 2) {
            println(i)
        }
        //如果你 想创建一个降序的区间，可以使用downTo关键字
        //创建了一个[10, 1]的降序区间
        for (i in 10 downTo 1) {
            println(i)
        }

        //笔记四：带主构造函数的类，实力化的时候必须传入要求的所有参数
        var student = Student("a123", 5, "Jack", 19)

        //笔记五：
        // 数据类会会根据主构造函数中的参数帮你将equals()、 hashCode()、toString()等固定且无实际逻辑意义的方法自动生成， 从而大大减少了开发的工作量。
        //数据类必须有主构造函数
        val cellphone1 = CellPhone("Samsung", 1299.99)
        val cellphone2 = CellPhone("Samsung", 1299.99)
        println(cellphone1)
        println("cellphone1 equals cellphone2 " + (cellphone1 == cellphone2))

        //笔记六：集合的函数式api
        //首先来看一下Lambda的定义，如果用最直白的语言来阐述的话，Lambda就 是一小段可以作为参数传递的代码
        //Lambda表达式的语法结构:
        // {参数名1: 参数类型, 参数名2: 参数类型 -> 函数体}
        //这是Lambda表达式最完整的语法结构定义。首先最外层是一对大括号，如 果有参数传入到Lambda表达式中的话，我们还需要声明参数列表，参数列 表的结尾使用一个->符号，
        // 表示参数列表的结束以及函数体的开始，函数 体中可以编写任意行代码(虽然不建议编写太长的代码)，并且最后一行 代码会自动作为Lambda表达式的返回值。

        //场景一：如何在一个水果集合里面找到单词最长的那个 水果?
        val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
        val maxlengthFruit = list.maxBy { it.length }
        //约等于
        val lambda = { fruit: String -> fruit.length }
        val maxlengthFruit2 = list.maxBy(lambda)
        //简化操作1：
        val maxlengthFruit3 = list.maxBy({ fruit: String -> fruit.length })
        //简化操作2：
        val maxlengthFruit4 = list.maxBy() { fruit: String -> fruit.length }
        //简化操作3：
        val maxlengthFruit5 = list.maxBy { fruit: String -> fruit.length }
        //简化操作4：(类型推到机制)
        val maxlengthFruit6 = list.maxBy { fruit -> fruit.length }
        //简化操作5：当Lambda表达式的参数列表中只有一个参数时，也不必声明参数 名，而是可以使用it关键字来代替
        val maxlengthFruit7 = list.maxBy { it.length }


        //笔记7
        //集合中比较常用的函数式API
        //API 1：map函数 
        //它用于将集合中的每个元素 都映射成一个另外的值，映射的规则在Lambda表达式中指定，最终生成一 个新的集合。
        //比如，这里我们希望让所有的水果名都变成大写模式，就可 以这样写:
        val newList = list.map { it.toUpperCase() }
        for (item in newList) {
            println("Result is " + item)
        }
        //API 2：filter函数
        //顾名思义，filter函数是用来过滤集合中的数据的，它可以单独使用，也可 以配合刚才的map函数一起使用。
        //比如我们只想保留5个字母以内的水果，就可以借助filter函数来实现，
        val newList2 = list.filter { it.length <= 5 }.map { it.toUpperCase() }
        for (item in newList2) {
            println("Result is " + item)
        }

        //API 3：any函数
        //any函数用于判断集合中是否至少存在一个元素满足指定条件
        val anyResult = list.any { it.length <= 5 }

        //API 4：all函数
        //all函数用 于判断集合中是否所有元素都满足指定条件
        val allResult = list.all { it.length >= 5 }
        println("anyResult is " + anyResult + ", allResult is " + allResult)

        //笔记8
        //Java函数式API的使用
        //现在我们已经学习了Kotlin中函数式API的用法，但实际上在Kotlin中调用 Java方法时也可以使用函数式API，只不过这是有一定条件限制的。
        // 具体来 讲，如果我们在Kotlin代码中调用了一个Java方法，并且该方法接收一个 Java单抽象方法接口参数，就可以使用函数式API。
        // Java单抽象方法接口指 的是接口中只有一个待实现方法，如果接口中有多个待实现方法，则无法 使用函数式API。
        //举例：1
//        public interface Runnable {
//            void run();
//        }
        Thread { println("Thread is running") }.start()

        //举例2
//        public interface OnClickListener {
//            void onClick(View v);
//        }
        button.setOnClickListener { }


        //笔记9
        //如果你的类中存在很多全局变量实例，为了保证它们能够满足 Kotlin的空指针检查语法标准，你不得不做许多的非空判断保护才行，即 使你非常确定它们不会为空。
        //延迟初始化使用的是lateinit关键字，它可以告诉Kotlin编译器，我会在 晚些时候对这个变量进行初始化，这样就不用在一开始的时候将它赋值为 null了。
        // lateinit var btn: Button


        //笔记10 扩展函数
        //扩展函数表示即使在不修改某个类的源码的 情况下，仍然可以打开这个类，向该类添加新的函数。
        //扩展函数的语法结构

//        fun ClassName.methodName(param1: Int, param2: Int): Int { return 0
//        }
        val count = "ABC123xyz!@#".lettersCount()


        //笔记10 运算符重载
        //我们知道，Java中有 许多语言内置的运算符关键字，如+ - * / % ++ --。
        // 而Kotlin允许我 们将所有的运算符甚至其他的关键字进行重载，从而拓展这些运算符和关 键字的用法。

        //运算符重载使用的是operator关键字，只要在指定函数的前面加上 operator关键字，就可以实现运算符重载的功能了。
        // 但问题在于这个指定 函数是什么?这是运算符重载里面比较复杂的一个问题，因为不同的运算 符对应的重载函数也是不同的。
        // 比如说加号运算符对应的是plus()函数， 减号运算符对应的是minus()函数。

        //举例子：+
//        以加号运算符为例，如果想要实现让两个对象相加的功能，
//        那么它的语法结构如下:
//        class Obj {
//            operator fun plus(obj: Obj): Obj { // 处理相加的逻辑
//            }
//        }


        //笔记11 高阶函数
        //如果一个函数接收另一个函数作为参数， 或者返回值的类型是另一个函数，那么该函数就称为高阶函数。
        //函数类型的语法规则定义如下：
        //(String,int)->Unit

        //既然是定义一个函数类型，那么最关键的就是要声明该函数接收什么参 数，以及它的返回值是什么。
        // 因此，->左边的部分就是用来声明该函数接 收什么参数的，多个参数之间使用逗号隔开，如果不接收任何参数，写一 对空括号就可以了。
        // 而->右边的部分用于声明该函数的返回值是什么类 型，如果没有返回值就使用Unit，它大致相当于Java中的void。
        //举例：example()函数

        //调用
        val num1 = 100
        val num2 = 80
        val result1 = num1AndNum2(num1, num2) { n1, n2 ->
            n1 + n2
        }
        val result2 = num1AndNum2(num1, num2) { n1, n2 ->
            n1 - n2
        }
        println("result1 is $result1")
        println("result2 is $result2")

        //使用高阶函数模仿实现StringBuilder的功能
        val listFruit = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
        val result = StringBuilder().build {
            append("Start eating fruits.\n")
            for (fruit in listFruit) {
                append(fruit).append("\n")
            }
            append("Ate all fruits.")
        }
        println(result.toString())


        //笔记12 内联函数的作用
        //Kotlin高阶函数背后的实现原理。会将我们一直使用的 Lambda表达式在底层被转换成了匿名类的实现方式。
        // 这就表明，我们每调 用一次Lambda表达式，都会创建一个新的匿名类实例，当然也会造成额外 的内存和性能开销。
        //为了解决这个问题，Kotlin提供了内联函数的功能，它可以将使用Lambda 表达式带来的运行时开销完全消除。

        //特殊情况：noinline关键字
        //比如，一个高阶函数中如果接收 了两个或者更多函数类型的参数，这时我们给函数加上了inline关键字，那么Kotlin编译器会自动将所有引用的Lambda表达式全部进行内联。
        //但是，如果我们只想内联其中的一个Lambda表达式该怎么办呢?这时就可以使用noinline关键字了，如下所示:
        //举例子：inlineTest()

//        前面我们已经解释了内联函数的好处，那么为什么Kotlin还要提供一个 noinline关键字来排除内联功能呢?这是因为内联的函数类型参数在编译 的时候会被进行代码替换，
//        因此它没有真正的参数属性。非内联的函数类 型参数可以自由地传递给其他任何函数，因为它就是一个真实的参数，而 内联的函数类型参数只允许传递给另外一个内联函数，
//        这也是它最大的局 限性。

        //⚠️非局部返回
        // 另外，内联函数和非内联函数还有一个重要的区别，那就是内联函数所引 用的Lambda表达式中是可以使用return关键字来进行函数返回的，而非内 联函数只能进行局部返回
        println("main start")
        val str = ""
        printString(str) { s ->
            println("lambda start")
            if (s.isEmpty()) return@printString
            println(s)
            println("lambda end")
        }
        printString2(str) { s ->
            println("lambda start")
            if (s.isEmpty()) return
            println(s)
            println("lambda end")
        }

        //将高阶函数声明成内联函数是一种良好的编程习惯，事实上，绝大多数高 阶函数是可以直接声明成内联函数的，但是也有少部分例外的情况。观察 下面的代码示例:
        //⚠️crossinline关键字 它用于保证在内联 函数的Lambda表达式中一定不会使用return关键字，这样冲突就不存在 了，问题也就巧妙地解决了。
//        inline fun runRunnable(block: () -> Unit) {
//            val runnable = Runnable {
//                block()
//            }
//            runnable.run()
//        }

        //


    }


}

fun largerNumber(num1: Int, num2: Int) = max(num1, num2)

fun largerNum(num1: Int, num2: Int) = if (num1 > num2) num1 else num2

fun example(func: (String, Int) -> Unit) {
    //调用方式1
    func("hello", 123)
    //调用方式2
    func.invoke("hello", 123)

}

fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result
}

//使用高阶函数模仿实现StringBuilder的功能
//定义StringBuilder的扩展函数 build,这个扩展函数 接收一个函数类型参数，并且返回值类型也是StringBuilder。
//注意，这个函数类型参数的声明方式和我们前面学习的语法有所不同:它 在函数类型的前面加上了一个StringBuilder. 的语法结构。
// 这是什么 意思呢?其实这才是定义高阶函数完整的语法规则，在函数类型的前面加 上ClassName. 就表示这个函数类型是定义在哪个类当中的。

//那么这里将函数类型定义到StringBuilder类当中有什么好处呢?好处就 是当我们调用build函数时传入的Lambda表达式将会自动拥有 StringBuilder的上下文，
// 同时这也是apply函数的实现方式。
fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
    block()
    return this
}

//内联函数
//那么内联函数的工作原理又是什么呢?其实并不复杂，就是Kotlin编译器 会将内联函数中的代码在编译的时候自动替换到调用它的地方，这样也就 不存在运行时的开销了。
inline fun num1AndNum3(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result
}

//如果我们只想内联其中的一个Lambda表达式该怎么办呢?这时就可 以使用noinline关键字了，
inline fun inlineTest(block1: () -> Unit, noinline block2: () -> Unit) {
}

//内联函数和非内联函数还有一个重要的区别，那就是内联函数所引 用的Lambda表达式中是可以使用return关键字来进行函数返回的，而非内 联函数只能进行局部返回

fun printString(str: String, block: (String) -> Unit) {
    println("printString begin")
    block(str)
    println("printString end")
}

inline fun printString2(str: String, block: (String) -> Unit) {
    println("printString begin")
    block(str)
    println("printString end")
}

inline fun runRunnable(crossinline block: () -> Unit) {
    val runnable = Runnable {
        block()
    }
    runnable.run()
}



