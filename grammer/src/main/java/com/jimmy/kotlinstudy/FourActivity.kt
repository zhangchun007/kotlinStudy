package com.jimmy.kotlinstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jimmy.kotlinstudy.utils.DateUtil
import kotlinx.android.synthetic.main.activity_four.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * 函数的运用
 */
class FourActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four)
        //4:函数运用
        //4.1函数的基本用法
        //4.1.1与Java声明方式的区别
        //  override fun onCreate(savedInstanceState: Bundle?) {}

        //4.1.2输入参数的格式
        getDinnerEmpty()
        getDinnerInput(2, 4.0, "一瓢", 2.0f)
        getDinnerCanNull(2, 4.0, null, 2.0f)

        //4.1.3 输出参数的格式
        // fun main():Int
        //默认返回值
        getDinnerEmpty()

        //return 关键字返回输出
        getDinnerOutput()

        //4.2输入参数的变化
        //4.2.1默认参数
        var isOdd = true
        btn_fun5.setOnClickListener {
            btn_fun5.text =
                if (isOdd) getFourBig("古代的四大发明", "造纸术", "印刷术", "火药", "指南针") else
                    getFourBig("现代的四大发明", "高铁", "网购", "移动支付", "共享单车")

            isOdd = !isOdd
        }

        //带默认参数的函数
        btn_fun6.setOnClickListener {
            btn_fun6.text = getFourBigDefault("古代的四大发明")
        }

        //4.2.2 命名参数
        //不满意参数的默认值，也可在调用函数时输入新的值
        btn_fun7.setOnClickListener {
            btn_fun7.text = getFourBigDefault("古代的四大发明", "蔡伦发明的造纸术")
        }
        //指定某个参数的输入值
        btn_fun8.setOnClickListener {
            btn_fun8.text = getFourBigDefault("古代的四大发明", second = "活字印刷术")
        }

        //4.2.3 可变参数
        //kotlin 中新增关键字vararg，例如："vararg args:String?" 替代Java中的写法"String...args"
        btn_fun9.setOnClickListener {
            btn_fun9.text = if (isOdd) getFourBigVararg("古代的四大发明") else getFourBigVararg(
                "古代的七大发明",
                "造纸术",
                "印刷术",
                "火药",
                "指南针",
                "丝绸",
                "瓷器",
                "茶叶"
            )
            isOdd = !isOdd
        }

        //可变数组参数
        btn_fun10.setOnClickListener {
            btn_fun10.text = if (isOdd) getFourBigArray("古代的四大发明") else getFourBigArray(
                "古代的七大发明",
                "造纸术",
                "印刷术",
                "火药",
                "指南针",
                arrayOf("丝绸", "瓷器", "茶叶"),
                arrayOf("国画", "中医", "武术")
            )
            isOdd = !isOdd
        }

        //4.3几种特殊的函数
        //4.3.1泛型函数
        var count = 0
        btn_fun11.setOnClickListener {
            btn_fun11.text = when (count % 3) {
                0 -> appendString<String>(
                    "古代四大发明", "造纸术",
                    "印刷术",
                    "火药",
                    "指南针"
                )
                1 -> appendString<Int>("小于10的素数", 2, 3, 5, 9)
                else -> appendString<Double>("烧钱的日子", 5.20, 6.18, 11.11, 12.12)

            }
            count++
        }

        //4.3.2内联函数
        //举个例子，Int、 Float和IDouble 都继承自Number类，但是假如定义一个输入参数形式为setArayNumber(array:Array<Number> )的函数，
        // 它并不接受Array<Int>或者Array <Double> 的入参。如果要让该方法同时接收整型和双精度的数组入参，就得指定泛型变量T来自
        //于基类Number,即将“<T>"改为“<reified T : Number>"，同时在fun前面添加关键字 inline，表示该函数属于内联函数。
        var int_array: Array<Int> = arrayOf(1, 2, 3)
        var float_array: Array<Float> = arrayOf(1.0f, 2.0f, 3.0f)
        var double_array: Array<Double> = arrayOf(11.11, 22.22, 33.33)

        btn_fun12.setOnClickListener {
            btn_fun12.text = when (count % 3) {
                0 -> setArrayStr<Int>(int_array)
                1 -> setArrayStr<Float>(float_array)
                else -> setArrayStr<Double>(double_array)
            }
            count++
        }

        btn_fun12.let {
            repeat(100){print("a")}
        }



        //4.3.3 简化函数
        //将函数看做变量的思想
        btn_fun13.setOnClickListener {
            btn_fun13.text = factorialSimple(5).toString()
        }

        //4.3.4尾递归函数
        //Kotlin体系还存在一种特殊的递归函数，名叫尾递归函数，它指的是函数末尾的返回值重复调用了自身函数。此时要在fun前
        //面加上关键字tailrec，告诉编译器这是一一个尾递归函数，则编译器会相应进行优化，从 而提高程序性能。
        //比如：余璇函数
        btn_fun14.setOnClickListener {
            btn_fun14.text = findFixPoint(60.0).toString()
        }

        //4.3.5高阶函数
        //函数a作为参数传入函数b,此时函数b称为高阶函数
        var string_array: Array<String> = arrayOf("How", "do", "you", "do", "I'm", "fine")
        btn_fun15.setOnClickListener {
            btn_fun15.text = when (count % 4) {
                0 -> "字符串数组的默认最大值为${string_array.max()}"
//                1 -> "字符串数组按长度比较的最大值为${maxCustom<String>(string_array,
//                    { a, b -> a.length > b.length })}"
                1 -> "字符串数组按长度比较的最大值为${maxCustom<String>(string_array)
                    { a, b -> a.length > b.length }}"
                2 -> "字符串数组的默认最大值（使用高阶函数为）${maxCustom<String>(string_array, { a, b -> a > b })}"
                else -> "字符串数组按去掉空格再比较长度的最大值为${maxCustom<String>(string_array,
                    { a, b -> a.trim().length > b.trim().length })}"
            }
            count++
        }


        //4.4 增强系统函数
        //4.4.1扩展函数
        //允许开发者给系统类补写新的方法，无需新增额外的工具类
        val array: Array<Double> = arrayOf(1.0, 2.0, 3.0, 4.0)
        btn_fun16.setOnClickListener {
            array.swap(0, 3)
            btn_fun16.text = setArrayStr<Double>(array)
        }

        //4.4.2 扩展高阶函数
        btn_fun17.setOnClickListener {
            btn_fun17.text = when (count % 4) {
                0 -> "字符串数组的默认最大值为${string_array.max()}"
                1 -> "字符串数组按长度比较的最大值为${string_array.maxCustomize({ a, b -> a.length > b.length })}"
                else -> "字符串数组按去掉空格再比较长度的最大值为${string_array.maxCustomize({ a, b -> a.trim().length > b.trim().length })}"

            }
            count++

        }

        //4.4.3 日期时间函数（扩展日期函数）
        btn_fun18.setOnClickListener {
            btn_fun18.text = when (count++ % 5) {
                0 -> "当前日期时间为${Date().getNowDateTime()}"
                1 -> "当前日期为${Date().getNowDate()}"
                2 -> "当前时间为${Date().getNowTime()}"
                3 -> "当前毫秒时间为${Date().getFormatTimeDetail()}"
                else -> "当前中文日期时间为${Date().getFormatTime("yyyy年MM月dd日HH时mm分ss秒")}"
            }
        }
        //4.4.4 单利对象
        //关键字object用来声明单利对象，就像Java中开发者定义的Utils工具类
        //其内部属性等同于Java中的static静态属性，外部可直接获取属性指

        btn_fun19.setOnClickListener {
            btn_fun19.text = "单利对象" + when (count++ % 5) {
                0 -> "当前日期时间为${DateUtil.nowDateTime}"
                1 -> "当前日期为${DateUtil.nowDate}"
                2 -> "当前时间为${DateUtil.nowTime}"
                3 -> "当前毫秒时间为${DateUtil.nowTimeDetail}"
                else -> "当前中文日期时间为${DateUtil.getFormatTime("yyyy年MM月dd日HH时mm分ss秒")}"
            }
        }

        

    }


    fun getDinnerEmpty(): Unit {
        btn_fun1.text = "此函数没有输入参数，也没有输出参数"
    }

    fun getDinnerInput(egg: Int, leek: Double, water: String, shell: Float) {
        btn_fun2.text = "这是带参数的函数"
    }

    fun getDinnerCanNull(egg: Int, leek: Double, water: String?, shell: Float) {
        btn_fun3.text = if (water != null) "食材包括：两个鸡蛋，一把韭菜，几瓢清水" else "没有水没法做汤了"
    }

    fun getDinnerOutput(): String {
        var dinner: String = "巧妇难为无米之炊"
        return dinner
    }

    fun getFourBig(
        general: String,
        first: String,
        second: String,
        third: String,
        four: String
    ): String {
        var answer: String = "$general:$first,$second,$third,$four"
        return answer
    }

    fun getFourBigDefault(
        general: String,
        first: String = "造纸术",
        second: String = "印刷术",
        third: String = "火药",
        four: String = "指南针"
    ): String {
        var answer: String = "$general:$first,$second,$third,$four"
        return answer
    }

    fun getFourBigVararg(
        general: String,
        first: String = "造纸术",
        second: String = "印刷术",
        third: String = "火药",
        four: String = "指南针", vararg otherArray: String?
    ): String {
        var answer: String = "$general:$first,$second,$third,$four"
        //循环取出可变参数包含的所有字段
        for (item in otherArray) {
            answer = "$answer,$item"
        }
        return answer
    }

    fun getFourBigArray(
        general: String,
        first: String = "造纸术",
        second: String = "印刷术",
        third: String = "火药",
        four: String = "指南针", vararg otherArray: Array<String>
    ): String {
        var answer: String = "$general:$first,$second,$third,$four"
        //先遍历每个数组
        for (array in otherArray) {
            //在遍历某个数组中的所有元素
            for (item in array) {
                answer = "$answer,$item"
            }
        }
        return answer
    }

    //泛型函数
    fun <T> appendString(tag: String, vararg otherInfo: T?): String {
        var str: String = "$tag:"

        //循环取出可变参数包含的所有字段
        for (item in otherInfo) {
            str = "$str,${item.toString()}"

        }
        return str

    }

    inline fun repeat(times: Int, action: (Int) -> Unit) {
        for (index in 0 until times) {
            action(index)
        }
    }

    //内联函数
    inline fun <reified T : Number> setArrayStr(array: Array<T>): String {
        var str: String = "数组元素依次排列："
        for (item in array) {
            str = str + item.toString() + ","
        }
        return str
    }

    //简化前函数
    fun factorial(n: Int): Int {
        if (n <= 1) return n
        else return n * factorial(n - 1)
    }

    //简化后函数
    fun factorialSimple(n: Int): Int = if (n <= 1) n else n * factorial(n - 1)

    //尾递归函数
    tailrec fun findFixPoint(x: Double = 1.0): Double =
        if (x == Math.cos(x)) x else findFixPoint(Math.cos(x))


    //高阶函数
    //greater()有两个输入参数，返回boolean类型的值
    //greater()函数中，如果第一个参数大于第二个参数，就认为greater（）返回true,否则返回false
    //下面高阶函数的第二个参数就是一 个函数变量，其中变量名称为greater， 冒号后面的“(T, T)”表示greater函数有两个类型为T的输入参数，该函数的返回值是Boolean类型。现在有
    //了高阶函数的定义，再来看看外部如何调用这个高阶函数，调用的示例代码如下:
    fun <T> maxCustom(array: Array<T>, greater: (T, T) -> Boolean): T? {
        var max: T? = null
        for (item in array) {
            if (max == null || greater(item, max))
                max = item
        }
        return max
    }

    //扩展函数
    fun <T> Array<T>.swap(pos1: Int, pos2: Int) {
        val temp = this[pos1]//this表示数组自身
        this[pos1] = this[pos2]
        this[pos2] = temp
    }

    //扩展高阶函数

    fun <T> Array<T>.maxCustomize(greater: (T, T) -> Boolean): T? {
        var max: T? = null
        for (item in this) {
            if (max == null || greater(item, max))
                max = item
        }
        return max
    }

    //日期函数
    //返回形如2017-10-1 10:00:00格式
    fun Date.getNowDateTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(this)
    }

    //只返回日期字符串
    fun Date.getNowDate(): String {
        var sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(this)
    }

    //只返回时间字符串
    fun Date.getNowTime(): String {
        var sdf = SimpleDateFormat("HH:mm:ss")
        return sdf.format(this)
    }

    //只返回时间字符串，精确到毫秒
    fun Date.getFormatTimeDetail(): String {
        var sdf = SimpleDateFormat("HH:mm:ss.SSS")
        return sdf.format(this)
    }

    //返回开发者指定格式的日期字符串
    fun Date.getFormatTime(format: String = ""): String {
        var ft: String = format
        var sdf = if (!ft.isEmpty()) SimpleDateFormat(ft) else SimpleDateFormat("yyyyMMddHHmmss")
        return sdf.format(this)
    }
}
