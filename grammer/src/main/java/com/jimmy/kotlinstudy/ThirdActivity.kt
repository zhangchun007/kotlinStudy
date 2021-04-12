package com.jimmy.kotlinstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_third.*
import java.util.*

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        //3.1 条件分支
        //3.1.1 简单分支
        var is_odd: Boolean = true;
        btn_if_simple.setOnClickListener {
            if (is_odd) {
                btn_if_simple.text = "凉风有信的迷底是'讽'"
            } else {
                btn_if_simple.text = "秋月无边的迷底是'二'"
            }
            is_odd = !is_odd
        }

        btn_if_simple2.setOnClickListener {
            btn_if_simple2.text = if (is_odd) {
                "凉风有信的迷底是'讽'"
            } else {
                "秋月无边的迷底是'二'"
            }
            is_odd = !is_odd
        }

        //3.1.2 多路分支
        var count: Int = 0
        btn_if_simple3.setOnClickListener {
            when (count) {
                0 -> btn_if_simple3.text = "凉风有信的迷底是'讽'"
                1 -> btn_if_simple3.text = "秋月无边的迷底是'二'"
                //if语句可以没有else，但是when语句必须带上else
                else -> btn_if_simple3.text = "好湿！"
            }
            count = (count + 1) % 3
        }

        //简化
        btn_if_simple4.setOnClickListener {
            btn_if_simple4.text = when (count) {
                0 -> "凉风有信的迷底是'讽'"
                1 -> "秋月无边的迷底是'二'"
                //if语句可以没有else，但是when语句必须带上else
                else -> "好湿！"
            }
            count = (count + 1) % 3
        }

        //kotlin 多路分支条件支持变量处理
        var odd: Int = 0
        var even: Int = 1
        btn_if_simple5.setOnClickListener {
            btn_if_simple5.text = when (count) {
                odd -> "凉风有信的迷底是'讽'"
                even -> "秋月无边的迷底是'二'"
                else -> "好湿！"
            }
            count = (count + 1) % 3
        }

        //kotlin支持多变量及变量区间判断
        /**
         * in 13..19 表示在13-19区间
         * !in 6..10 表示不再6-10区间
         */
        btn_if_simple6.setOnClickListener {
            btn_if_simple6.text = when (count) {
                1, 3, 5, 7, 9 -> "凉风有信的迷底是'讽'"
                in 13..19 -> "秋月无边的迷底是'二'"
                !in 6..10 -> "你猜不睬"
                else -> "好湿 好湿"
            }
            count = (count + 1) % 20
        }


        //3.1.3 类型判断
        //kotlin 用关键字is替代Java中的instanceof，同时分支判断也支持类型判断
        var countType: Number
        btn_if_simple7.setOnClickListener {
            count = (count + 1) % 3
            countType = when (count) {
                0 -> count.toLong()
                1 -> count.toDouble()
                else -> count.toFloat()
            }
            btn_if_simple7.text = when (countType) {
                is Long -> "此恨绵绵无绝期"
                is Double -> "树上的鸟儿成双对"
                else -> "门泊东吴万里船"
            }
        }

        //3.2 循环处理
        //3.2.1遍历循环
        var poemArray: Array<String> = arrayOf("朝辞白帝彩云间", "千里江陵一日还", "两岸猿声啼不住", "轻舟已过万重山")
        var poem: String = ""
        for (item in poemArray) {
            poem = "$poem$item,\n"
        }
        btn_cycler.text = poem

        //改进 偶数结尾。基数结尾，
        btn_cycler1.setOnClickListener {
            var poem: String = ""
            for (i in poemArray.indices) {
                if (i % 2 == 0) {
                    poem = "$poem ${poemArray[i]},\n"
                } else {
                    poem = "$poem ${poemArray[i]}。\n"
                }
            }
            btn_cycler1.text = poem
        }

        //3.2.2条件循环
        //解决kotlin无法满足Java中一些条件循环

        //until 关键字 左闭又开区间，合法值包括11但不包括66
        for (i in 11 until 66) {
        }

        //step 关键字 每次默认递增1，改为每次递增4
        for (i in 23..89 step 4) {
        }

        //downTo 表示递减
        for (i in 50 downTo 7) {
        }


        //3.2.3 跳出多重循环（continue 跟 break 关键字）
        var poem2Array: Array<String?> =
            arrayOf("朝辞白帝彩云间", null, "千里江陵一日还", "", "两岸猿声啼不住", "   ", "轻舟已过万重山")
        btn_break_cycler1.setOnClickListener {
            var poem: String = ""
            var pos: Int = -1
            var count: Int = 0
            while (pos <= poem2Array.size) {
                pos++

                //空串或者空格串，跳出当前循环
                if (poem2Array[pos].isNullOrBlank()) {
                    continue
                }
                if (count % 2 == 0) {
                    poem = "$poem ${poemArray[pos]},\n"
                } else {
                    poem = "$poem ${poemArray[pos]}。\n"
                }

                count++

                //达到4行，则结束循环
                if (count == 4)
                    break

            }

            btn_break_cycler1.text = poem

        }

        //嵌套循环想要跳出外层循环，只需要在外层循环加个@标记，只要遇到情况便直接跳出到这个标记

        btn_break_cycler2.setOnClickListener {
            var i: Int = 0
            var is_found = false

            outside@ while (i < poemArray.size) {
                var j: Int = 0
                var item = poemArray[j]
                while (j < item.length) {
                    if (item[j] == '一') {
                        is_found = true
                        //发现情况，直接跳出outside循环
                        break@outside
                    }
                    j++
                }
                //如果内层循环直接跳出两层循环，那么下面的判断语句就不需要了
//                if (is_found)
//                    break
                i++
            }

            btn_break_cycler2.text = if (is_found) "我找到'一'字了" else "没有找到'一'字"
        }


        //3.3空安全
        //3.3.1字符串的有效判读
        //校验字符串空值的几个方法
        /**
         * #isNullOrEmpty():为 空指针或者字符串长度为0时返回true，非空串与可空串均可调用
         * #isNullOrBlank():为 空指针,字符串长度为0或者全为空格时返回true,非空串与可空串均可调用
         * #isEmpty():字符串长度为0时返回true,只有非空串可调用
         * #isBlank():字符串长度为0或者全为空格时返回true，只有非空串可调用
         * #isNotEmpty():字符串长度大于0时返回true,只有非空串可调用
         * #isNotBlank():字符串长度大于0且不是全空格串时返回true，只有非空串可调用
         */

        //3.3.2声明可空变量
        //申明非空变量必须在调用前赋值
        var strNotNull: String = ""

        //申明可空变量
        var strCanNull: String?


        //判断字符串是否为空
        val strA: String = "非空"
        var strB: String? = null
        val strC: String = "可空串"

        //获取上述字符串的长度
        var lengthA: Int = 0
        var lengthB: Int = 0
        var lengthC: Int = 0
        btn_str_judge.setOnClickListener {
            lengthA = strA.length;

            lengthB = strB?.length ?: -1

            lengthC = if (strC != null) strC.length else -1

            btn_str_judge.text = "字符串A的长度为${lengthA}\n字符串B的长度为${lengthB}\n字符串C的长度为${lengthC}\n"

        }

        //3.3.3 校验空值的运算符
        var length_null: Int?
        //?运算符
        btn_str_judge2.setOnClickListener {
            //?.表示变量为空时直接返回null，所以返回值的变量必须声明为可空类型
            length_null = strB?.length
            btn_str_judge2.text = "使用?.得到字符串B的长度为$length_null"
        }

        //?:运算符
        btn_str_judge3.setOnClickListener {
            //?:表示为空时返回右边的值，即（x!=null）?x.length:y
            length_null = strB?.length ?: -1
            btn_str_judge3.text = "使用?:得到字符串B的长度为$length_null"
        }

        //!!字符串表示：强行把该变量从可空类型转化为非空类型，从而避免变量是否非空的校验
        //得注意该字符串得为非空字符串
        btn_str_judge4.setOnClickListener {
            strB = "排雷完毕"
            length_null = strB!!.length
            btn_str_judge4.text = "使用!!得到字符串B的长度为$length_null"
        }

        //3.4等式判断
        //3.4.1 结构相等
        //整型跟字符串相等都用==来判断，不等!=

        val helloHe: String = "你好"
        val helloShe: String = "她好"
        var isEqual: Boolean = false
        btn_equals.setOnClickListener {
            if (isEqual) {
                val result = helloHe == helloShe
                btn_equals.text = "比较$helloHe 和 $helloShe 是否相等\n ==的比较结果是$result"
            } else {
                val result = helloHe != helloShe
                btn_equals.text = "比较$helloHe 和 $helloShe 是否不相等\n ==的比较结果是$result"
            }
            isEqual = !isEqual
        }

        //3.4.2 引用相等
        //kotlin 引用相等用=== 引用不相等!==
        val date1: Date = Date()
        val date2: Any = date1.clone() //从date1原样克隆一份到date2
        btn_equals1.setOnClickListener {
            when (count++ % 4) {
                0 -> {
                    val result = date1 == date2
                    btn_equals1.text = "比较date1和date2是否结构相等\n比较的结果是$result"
                }
                1 -> {
                    val result = date1 != date2
                    btn_equals1.text = "比较date1和date2是否结构不相等\n比较的结果是$result"

                }
                2 -> {
                    val result = date1 === date2
                    btn_equals1.text = "比较date1和date2是否引用相等\n比较的结果是$result"
                }
                else -> {
                    val result = date1 !== date2
                    btn_equals1.text = "比较date1和date2是否引用不相等\n比较的结果是$result"
                }
            }
        }

        //3.4.4 is和in
        //运算符is 和!is 判断变量是否是某种类型 替换java instanceof关键字
        // in判断数组中是否存在某个元素
        val oneArray: IntArray = intArrayOf(1, 2, 3, 4, 5)
        btn_contains.setOnClickListener {
            val four: Int = 4
            val nine: Int = 9

            when (count++ % 4) {
                0 -> {
                    val result = four in oneArray
                    btn_contains.text = "比较$four 是否存在数组oneArray中\n in的比较结果是$result"
                }
                1 -> {
                    val result = four !in oneArray
                    btn_contains.text = "比较$four 是否存不在数组oneArray中\n !in的比较结果是$result"

                }
                2 -> {
                    val result = nine !in oneArray
                    btn_contains.text = "比较$nine 是否存在数组oneArray中\n in的比较结果是$result"

                }
                else -> {
                    val result = nine !in oneArray
                    btn_contains.text = "比较$nine 是否不存在数组oneArray中\n !in的比较结果是$result"

                }
            }


        }


    }
}
