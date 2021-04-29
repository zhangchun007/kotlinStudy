package com.jimmy.kotlinstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jimmy.kotlinstudy.guolin.CellPhone
import com.jimmy.kotlinstudy.guolin.Student
import kotlinx.android.synthetic.main.activity_six.*
import kotlin.math.max

/**
 * 看郭霖第一行代码的笔记
 */
class SixActivity : AppCompatActivity() {

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




    }


}

fun largerNumber(num1: Int, num2: Int) = max(num1, num2)

fun largerNum(num1: Int, num2: Int) = if (num1 > num2) num1 else num2

