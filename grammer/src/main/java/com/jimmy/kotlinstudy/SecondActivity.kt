package com.jimmy.kotlinstudy

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //2.1.1基本类型的变量声明 Int/Long/Float/Double/Boolean/Char/String
        var i: Int = 0

        //注意：var/val 的区别：val修饰过的变量只能在第一次声明时赋值，后续不能再赋值；var修饰过的变量在任何时候都允许赋值

        //2.1.2 变量之间的转换（toInt toLong toFloat toDoublt toChar toString）

        val origin: Float = 65.0f
        btn_int.setOnClickListener { btn_int.text = "转化为整型:" + origin.toInt() }

        btn_long.setOnClickListener { btn_long.text = "转化为长整型:" + origin.toLong() }

        btn_Double.setOnClickListener { btn_Double.text = "转化为双精度型:" + origin.toDouble() }

        btn_boolean.setOnClickListener { btn_boolean.text = "转化为boolean型:" + origin.isNaN() }

        btn_char.setOnClickListener { btn_char.text = "转化为字符串型:" + origin.toChar() }


        //2.2 数组
        //2.2.1 数组变量的声明

        var int_array: IntArray = intArrayOf(1, 2, 3)
        var long_array: LongArray = longArrayOf(4, 5, 6)
        var float_array: FloatArray = floatArrayOf(1.0f, 2.0f, 3.0f)
        var double_array: DoubleArray = doubleArrayOf(4.0, 5.0, 6.0);
        var boolean_array: BooleanArray = booleanArrayOf(false, true, false)
        var char_array: CharArray = charArrayOf('a', 'b', 'c')

        //注意kotlin数组类型不包括字符串数组类型，
        var string_array: Array<String> = arrayOf("How", "are", "you")
        var int_array2: Array<Int> = arrayOf(1, 2, 3)
        var long_array2: Array<Long> = arrayOf(4, 5, 6)
        var double_array2: Array<Double> = arrayOf(4.0, 5.0, 6.0)
        var float_array2: Array<Float> = arrayOf(1.0f, 2.0f, 3.0f)
        var boolean_array2: Array<Boolean> = arrayOf(false, true, false)
        var char_array2: Array<Char> = arrayOf('a', 'b', 'c')

        //2.2.2 数组元素的操作
        //获取数组长度：Java 使用.length,kotlin使用.size
        //获取指定位置的数组元素;kotlin获取数组元素的两个方式：1：string_array[i] 2：string_array.get(i)

        btn_get.setOnClickListener {
            var str: String = ""
            var i: Int = 0
            while (i < string_array.size) {
                str = str + string_array[i] + ","
                // str=str+string_array.get(i)+","
                i++
            }
            btn_get.text = str
        }


        //2.3 字符串
        //2.3.1 字符串与基本类型的转换（toInt,toLong,toFloat,toDouble,toBoolean,toCharArray）

        //2.3.2 字符串的常用方法
        val origin_string: String = "123456.789"
        if (origin_string.indexOf('.') > 0) {
            btn_substring.text = origin_string.substring(0, origin_string.indexOf('.'))
        }

        //注意：Java中split方法返回的是数组，kotlin返回的是集合
        btn_split.setOnClickListener {
            var strList: List<String> = origin_string.split(".")
            var strResult: String = ""
            for (item in strList) {
                strResult = strResult + item + ","
            }
            btn_split.text = "字符串分割:" + strResult
        }

        //2.3.3 字符串模版及其拼接
        btn_format.setOnClickListener {
            btn_format.text = "字符串模版:$origin_string"

        }

        btn_format_length.setOnClickListener {
            btn_format_length.text = "字符串模版长度:${origin_string.length}"
        }

        //${'***'} 输出转义字符
        btn_format_opt.setOnClickListener {
            btn_format_opt.text = "美元符号的输出：${'$'}$origin"
        }

        //2.4 容器
        //2.4.1 容器的基本操作
        //分类
        /**
         * 只读集合             Set             setOf()
         * 可变集合         MutableSet          mutableSetOf()
         * 只读队列            list             listOf()
         * 可变队列         MutableList         mutableListof()
         * 只读映射          map                 mapOf()
         * 可变映射         MutableMap          mutableMapOf()
         *
         */
        //2.4.2 集合Set/MutableSet

        //for-in遍历
        val goodsMutSet: Set<String> =
            setOf("iphone11", "mate30", "小米10", "oppo11", "vivo20", "魅族17")
        btn_set_for_in.setOnClickListener {
            var desc = ""
            for (item in goodsMutSet) {
                desc = "$desc 名称：$item \n"
            }
            btn_set_for_in.text = "手机畅销榜包含以下${goodsMutSet.size}款手机：\n$desc"
        }
        //迭代器遍历

        btn_set_iterator.setOnClickListener {
            var desc = ""
            val iterator = goodsMutSet.iterator();
            while (iterator.hasNext()) {
                val item = iterator.next()
                desc = "$desc 名称：$item \n"
            }
            btn_set_iterator.text = "手机畅销榜包含以下${goodsMutSet.size}款手机：\n$desc"
        }

        //forEach遍历(内部使用it代表每个元素)

        btn_set_forEach.setOnClickListener {
            var desc = ""
            goodsMutSet.forEach {
                desc = "${desc}名称：${it}\n"
            }
            btn_set_forEach.text = "手机畅销榜包含以下${goodsMutSet.size}款手机：\n$desc"
        }


        //2.4.3队列List/MutableList
        //常用方法
        /**
         * get(pos) 获取指定位置的元素
         * add() 将元素添加到队尾或者指定位置
         * set() 替换或者修改制定位置的元素
         * removeAt() 删除指定位置的元素
         *
         */

        //便利：除了for-in iterator foreach 还支持下标循环遍历
        val goodsMutList: List<String> =
            listOf("iphone11", "mate30", "小米10", "oppo11", "vivo20", "魅族17")

        btn_list.setOnClickListener {
            var desc = ""
            for (i in goodsMutList.indices) {
                val item = goodsMutList.get(i)
                desc = "${desc}名称：${item}\n"
            }
            btn_list.text = "手机畅销榜包含以下${goodsMutSet.size}款手机：\n$desc"
        }

        //mutablelist 中sort排序：sortBy方法表示按照指定条件升序排列，sortByDescending方法表示按照指定条件降序排序

        var sortAsc = true
        btn_sort.setOnClickListener {
            if (sortAsc) {
                goodsMutList.sortedBy { it.length }
            } else {
                goodsMutList.sortedByDescending { it.length }
            }

            var desc = ""
            for (item in goodsMutList) {
                desc = "${desc}名称：${item}\n"
            }

            btn_sort.text = "手机畅销榜已经按照${if (sortAsc) "升序" else "降序"}${goodsMutSet.size}款手机：\n$desc"
            sortAsc = !sortAsc
        }


        //2.4.4 映射Map/MutableMap
        /**
         * 常用方法：
         *
         * containsKey() 判断是否存在指定键名的元素
         * containsValue() 判断是否存在指定键值的元素
         * put() 添加或者替换元素
         * remove()方法通过键名删除元素
         *
         * 初始化方式：
         * 1："键名 to 键值"的形式
         * 2："pair(键名,键值)"形式
         *
         */
        var goodsMutMap: MutableMap<String, String> = mutableMapOf(
            Pair("苹果", "iphone11"),
            Pair("华为", "mate30"),
            Pair("小米", "小米10"),
            Pair("欧珀", "oppo11"),
            Pair("步步高", "vivo98"),
            Pair("魅族", "meizu17")
        )

        //for-in 循环
        btn_map_forin.setOnClickListener {
            var desc = ""
            for (item in goodsMutMap) {
                desc = "${desc}厂家：${item.key},名称:${item.value}\n"
            }
            btn_map_forin.text = "手机畅销榜包含以下${goodsMutMap.size}款手机：\n $desc"
        }

        //迭代器遍历
        btn_map_iterator.setOnClickListener {
            var desc = ""
            var iterator = goodsMutMap.iterator()

            while (iterator.hasNext()) {
                val item = iterator.next()
                desc = "${desc}厂家：${item.key},名称:${item.value}\n"
            }
            btn_map_iterator.text = "手机畅销榜包含以下${goodsMutMap.size}款手机：\n $desc"
        }

        //forEach遍历
        btn_map_forEach.setOnClickListener {
            var desc = ""
            goodsMutMap.forEach { key, value ->
                desc = "${desc}厂家：${key},名称:${value}\n"
            }

            btn_map_forEach.text = "手机畅销榜包含以下${goodsMutMap.size}款手机：\n $desc"
        }

    }
}
