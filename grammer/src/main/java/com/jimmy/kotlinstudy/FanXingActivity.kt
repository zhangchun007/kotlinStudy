package com.jimmy.kotlinstudy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jimmy.kotlinstudy.guolin.*
import java.lang.StringBuilder
import java.util.*

/**
 * @Description:    泛型跟委托
 * @Author:         zhangchun
 * @CreateDate:     2021/8/2
 * @Version:        1.0
 */
class FanXingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fanxing)

        /**首先解释一下什么是泛型:
         * 泛型允许我们在不指定具体类型的情况下进行 编程，这样编写出来的代码将会拥有更好的扩展性
         *
         * 泛型主要有两种定义方式:一种是定义泛型类，MyClass
         * 另一种是定义泛型方法， 使用的语法结构都是<T>。
         */
        //泛型类
        val myClass = MyClass<Int>()
        val result = myClass.method(123)

        //泛型方法
        val myClass1 = MyClass1()
        var result1 = myClass1.method<Int>(123)
        //Kotlin还拥有非常出色的类型推导机制 上述可以简写为：
        var result2 = myClass1.method(123)

        //Kotlin还允许我们对泛型的类型进行限制。目前你可以将method()方法的 泛型指定成任意类型，但是如果这并不是你想要的话，
        //还可以通过指定上界的方式来对泛型的类型进行约束，比如这里将method()方法的泛型上界 设置为Number类型，如下所示


        //⚠️：另外，在默认情况下，所有的泛型都是可以指定成可空类型的，这是因为 在不手动指定上界的时候，泛型的上界默认是Any?
        //而如果想要让泛型的 类型不可为空，只需要将泛型的上界手动指定成Any就可以了。

        //应用：将build()高阶函数泛型化使之成为一个像apply一样的函数


        /**
         * 泛型的高级特性：
         * Java的泛型功能是通过类型擦除机制来实现的。什么意思呢?就是说泛型对于类型的约束只在编译时期存在，运行的时候仍然会按 照JDK 1.5之前的机制来运行，
         * JVM是识别不出来我们在代码中指定的泛型 类型的。例如，假设我们创建了一个List<String>集合，虽然在编译时 期只能向集合中添加字符串类型的元素，
         * 但是在运行时期JVM并不能知道它 本来只打算包含哪种类型的元素，只能识别出来它是个List。
         * 这种机制使得我们不可能使用a is T或者 T::class.java这样的语法，因为T的实际类型在运行的时候已经被擦除了。
         *
         * 然而不同的是，Kotlin提供了一个内联函数的概念，内联函数中的代码会在编译的时候自动被 替换到调用它的地方，这样的话也就不存在什么泛型擦除的问题了，
         * 因为代码在编译之后会直接使用实际的类型来替代内联函数中的泛型声明。
         * 例如：foo()方法中调用了bar内联函数，代码在编译之后会直接使用实际的类型来替代内联函数中的泛型声明
         * fun foo(){
         *  //do something with T type
         * }
         * 这就意味着，Kotlin中是可以将内联函数中的泛型进行实化的。
         *
         *
         * 那么具体该怎么写才能将泛型实化呢?首先，该函数必须是内联函数才 行，也就是要用inline关键字来修饰该函数。
         * 其次，在声明泛型的地方必 须加上reified关键字来表示该泛型要进行实化.
         * 例如：getGenericType()
         * 那么借助泛型实化，到底可以实现什么 样的效果呢?从函数名就可以看出来了，这里我们准备实现一个获取泛型 实际类型的功能，代码如下所示:
         * 例如：getGenericTypes()
         * 虽然只有一行代码，但是这里却实现了一个Java中完全不可能实现的功 能:getGenericType()函数直接返回了当前指定泛型的实际类型。
         * T.class这样的语法在Java中是不合法的，而在Kotlin中，借助泛型实化 功能就可以使用T::class.java这样的语法了。
         */
        val type1 = getGenericTypes<String>()
        val type2 = getGenericTypes<Int>()
        println("type1 is $type1")
        println("type2 is $type2")


        /**
         * 泛型实例话的应用：
         * 泛型实化功能允许我们在泛型函数当中获得泛型的实际类型，这也就使得 类似于a is T、T::class.java这样的语法成为了可能。
         *
         * 应用一：页面跳转
         * 例如：startActivity()
         * startActivity<TestActivity>(context)
         * startActivitys<TestActivity>(context){
         * putExtra("param1","data")
         * putExtra("param2","123")
         * }
         */

        /**
         * 泛型的协变：
         * 我们创建了一个Student 的实例，并将它封装到SimpleData<Student>当中，然后将 SimpleData<Student>作为参数传递给handleSimpleData()方法。
         * 但是handleSimpleData()方法接收的是一个SimpleData<Person>参 数(这里假设可以编译通过)，那么在handleSimpleData()方法中，我 们就可以创建一个Teacher的实例，
         * 并用它来替换 SimpleData<Person>参数中的原有数据。这种操作肯定是合法的，因为 Teacher也是Person的子类，所以可以很安全地将Teacher的实例设置 进去。
         * 但是问题马上来了，回到main()方法当中，我们调用 SimpleData<Student>的get()方法来获取它内部封装的Student数 据，
         * 可现在SimpleData<Student>中实际包含的却是一个Teacher的实 例，那么此时必然会产生类型转换异常。
         * 所以，为了杜绝这种安全隐患，Java是不允许使用这种方式来传递参数 的。换句话说，即使Student是Person的子类， SimpleData<Student>并不是SimpleData<Person>的子类。
         * 不过，回顾一下刚才的代码，你会发现问题发生的主要原因是我们在 handleSimpleData()方法中向SimpleData<Person>里设置了一个Teacher的实例。
         * 如果SimpleData在泛型T上是只读的话，肯定就没有类 型转换的安全隐患了，那么这个时候SimpleData<Student>可不可以成 为SimpleData<Person>的子类呢?
         *
         * 讲到这里，我们终于要引出泛型协变的定义了。假如定义了一个 MyClass<T>的泛型类，其中A是B的子类型，
         * 同时MyClass<A>又是 MyClass<B>的子类型，那么我们就可以称MyClass在T这个泛型上是协变 的。
         * 但是如何才能让MyClass<A>成为MyClass<B>的子类型呢?刚才已经讲 了，如果一个泛型类在其泛型类型的数据上是只读的话，那么它是没有类 型转换安全隐患的。
         * 而要实现这一点，则需要让MyClass<T>类中的所有 方法都不能接收T类型的参数。换句话说，T只能出现在out位置上，而不能 出现在in位置上。
         */

        val student = Studentz("Tom", 19)
        val data = SimpleData<Studentz>()
        data.set(student)
//        handleSimpleData(data) // 实际上这行代码会报错，这里假设它能编译通过
        val studentData = data.get()

        // * 现在修改SimpleData类的代码，如下所示:
        val students = Studentz("jack", 19)
        val datas = SimpleDatas<Studentz>(students)
        handleSimpleDataz(datas)
        val studentDatas = datas.get()

        /**
         * 泛型的逆变：
         *那么这里先引出定义吧，假如定义了一个MyClass<T>的泛型类，其中A是B的子类型，同时 MyClass<B>又是MyClass<A>的子类型，那么我们就可以称MyClass在T 这个泛型上是逆变的
         *
         * 先我们在main()方法中编写了一个Transformer<Person>的匿名类 实现，并通过transform()方法将传入的Person对象转换成了一个“姓 名+年龄”拼接的字符串。
         * 而handleTransformer()方法接收的是一个 Transformer<Student>类型的参数，这里在handleTransformer() 方法中创建了一个Student对象，并调用参数的transform()方法将 Student对象转换成一个字符串。
         *
         * 这段代码从安全的角度来分析是没有任何问题的，因为Student是Person 的子类，使用Transformer<Person>的匿名类实现将Student对象转换 成一个字符串也是绝对安全的，并不存在类型转换的安全隐患。
         * 但是实际 上，在调用handleTransformer()方法的时候却会提示语法错误，原因 也很简单，Transformer<Person>并不是Transformer<Student>的 子类型。
         *
         *
         */
        val trans = object : Transformer<Person> {
            override fun transform(t: Person): String {
                return "${t.name}${t.age}"
            }

        }
//        handleTransformer(trans)//// 这行代码会报错
        //修改Transformer接口中的代码，
         val trans2 = object : Transformers<Person> {
            override fun transform(t: Person): String {
                return "${t.name}${t.age}"
            }

        }
        handleTransformers(trans2)
    }

    /**
     * 高阶函数
     */
    fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
        block()
        return this
    }

    //泛型化
    fun <T> T.build(block: T.() -> Unit): T {
        block()
        return this
    }

    fun foo() {
        bar<String>()
    }

    inline fun <T> bar() {
        //do something with T type
    }

    //内联函数实例化
    inline fun <reified T> getGenericType() {
    }

    inline fun <reified T> getGenericTypes() = T::class.java


    /**
     * 泛型实例化应用一：
     * 通常启动一个Activity可以这么写：
     *  val intent=Intent(context,TestActivity::class.java)
     *  context.startActivity(intent)
     *
     *  但是Kotlin的 泛型实化功能使得我们拥有了更好的选择。
     */

    inline fun <reified T> startActivity(context: Context) {
        val intent = Intent(context, T::class.java)
        context.startActivity(intent)
    }

    //startActivity()添加跳转参数
    inline fun <reified T> startActivitys(context: Context, block: Intent.() -> Unit) {
        val intent = Intent(context, T::class.java)
        intent.block()
        context.startActivity(intent)
    }

    //协变
    fun handleSimpleData(data: SimpleData<Person>) {
        val teacher = Teacher("Jack", 35)
        data.set(teacher)
    }

    private fun handleSimpleDataz(data: SimpleDatas<Person>) {
        data.get()
    }

    //逆变
    private fun handleTransformer(trans: Transformer<Student>) {
        val student = Student("Tom", 19)
        val result = trans.transform(student)
    }
    private fun handleTransformers(trans: Transformers<Student>) {
        val student = Student("Tom", 19)
        val result = trans.transform(student)
    }


}