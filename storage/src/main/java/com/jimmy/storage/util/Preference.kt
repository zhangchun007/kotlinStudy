package com.jimmy.storage.util

import android.content.Context
import android.content.SharedPreferences
import java.lang.IllegalArgumentException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @Description: 属性代理存储数据
 * @Author:         纯仔
 * @CreateDate:     2020-10-20
 * @Version:        1.0
 */

/**
 * 属性代理用的黑科技：
 *
 * 1：模板类
 * 因为共享参数允许保存的数据类型包括整型，浮点型等所以要将Preference类定义成模板类
 *
 * 泛型T,Any 和 * 三者的区别：
 * (1）T是抽象的泛型，在模板类中用来占位子，外部调用模板类时才能确定T的具体类型
 *（2）Any是Kotin的基本类型，所有Kotlin类都从Any派生而来，故而它相当于Java里面的Object
 * (3)星号“*”表示一个不确定的类型，同样也是在外部调用时才能确定，这点跟T比较像。但T出现在模板类的定义中，而*与模板类无关，它出现在单个函数定义的参数列表中，因此星号相当于Java里面的问号“?”
 *
 * 2：委托属性
 *注意到外部利用Preference声明参数字段时，后面跟着表达式“by Preference（..)”，这个by表示代理动作，而这里则为属性代理，所谓属性代理，是说该属性的类型不变，但是属性的读写行为被后面的类接管了
 *
 *
 * 3：lazy修饰符
 * 模板类Preference<T>声明了一个共享参数的prefs对象，其中用到了关键字lazy，lazy的意思是懒惰，表示只在该属性第一次使用时执行初始化。
 * 联想到Kotlin还有类似的关键字名叫lateinit,意思是延迟初始化，加上lazy 可以归纳出Kotlin变量的三种初始化操作，具体说明如下。
 *
 * (1):声明时赋值:这是最常见的变量初始化，在声明某个变量时，立即在后面通过等号“=”给它赋予具体的值。
 * (2)通过关键字lateinit延迟初始化:变量声明时没有马上赋值，但该变量仍是个非空变量，何时初始化由开发者编码决定。
 * (3)通过修饰符lazy在首次使用时初始化:声明变量时指定初始化动作，但该动作要等到变量第一次使用时才 进行初始化。
 *
 * 4：with函数
 * with函数的书写格式形如“with(函数头语句) {函数体语句}”， 看这架势，with方法的函数语句分为两部分，详述如下。
 *
 * (1)函数头语句:头部语句位于紧跟with的圆括号内部。它先于函数体语句执行，并且头部语句返回一个对象，函数体语句在该对象的命名空间中运行。
 * 也就是说，体语句可以直接调用该对象的方法，而无须显式指定头部对象的实例名称。
 *
 * (2)函数体语句:体语句位于常规的大括号内部。它要等头部语句处理完毕才会执行，同时体语句在头部语句返回对象的命名空间中运行。
 * 也就是说，体语句允许直接调用头部对象的方法，而无须显式指定该对象的实例名称。
 *
 *
 */
class Preference<T>(val context: Context, val name: String, val default: T) :
    ReadWriteProperty<Any?, T> {

    //通过属性代理初始化共享参数对象
    val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(
            "default",
            Context.MODE_PRIVATE
        )
    }


    //接管属性值的获取行为
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    //接管属性值的修改行为
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }


    //利用with函数定义临时的命名空间
    private fun findPreference(name: String, default: T): T = with(prefs) {
        val res: Any? = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        return res as T
    }


    private fun putPreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply()  //commit方法和apply方法都表示提交修改
    }
}