package com.jimmy.storage

import android.app.Application
import java.lang.IllegalArgumentException
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-10-30
 * @Version:        1.0
 */
class MainApplication : Application() {
    var mInfoMap = mutableMapOf<String, String>()
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    //单利化的第一种方式：声明一个简单的application属性
//    companion object {
//        //情况一：声明可空的属性
//        private var instance: MainApplication? = null
//        fun instance() = instance!!

//        //情况二：声明延时初始化属性
//        private lateinit var instance:MainApplication
//        fun instance()= instance
//    }

//    //单利化的第二种方式：利用系统自带的Delegates生成委托属性
//    companion object {
//        private var instance: MainApplication by Delegates.notNull()
//        fun instance() = instance
//    }

    //定义一个属性管理类，进行非空和重复赋值的判断
    companion object {
        private var instance: MainApplication by NotNullSingleValueVar()
        fun instance() = instance
    }
}

//定义一个属性管理类，进行非空和重复赋值的判断
private class NotNullSingleValueVar<T>() : ReadWriteProperty<Any?, T> {
    private var value: T? = null
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw  IllegalStateException("application not initialized")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("application already initialized")
    }

}