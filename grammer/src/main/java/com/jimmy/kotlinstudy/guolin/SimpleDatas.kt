package com.jimmy.kotlinstudy.guolin

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/8/3
 * @Version:        1.0
 *
 * 这里我们对SimpleData类进行了改造，在泛型T的声明前面加上了一个 out关键字。这就意味着现在T只能出现在out位置上，而不能出现在in位 置上，
 * 同时也意味着SimpleData在泛型T上是协变的。
 * 由于泛型T不能出现在in位置上，因此我们也就不能使用set()方法为 data参数赋值了，所以这里改成了使用构造函数的方式来赋值。你可能会 说，构造函数中的泛型T不也是在in位置上的吗?
 * 没错，但是由于这里我们 使用了val关键字，所以构造函数中的泛型T仍然是只读的，因此这样写是 合法且安全的。另外，即使我们使用了var关键字，
 * 但只要给它加上 private修饰符，保证这个泛型T对于外部而言是不可修改的，那么就都是 合法的写法。
 */
class SimpleDatas<out T>(val data: T?) {
    fun get(): T? {
        return data
    }
}