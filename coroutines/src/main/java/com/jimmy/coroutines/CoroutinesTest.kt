package com.jimmy.coroutines

import android.provider.Settings
import kotlinx.coroutines.*
import kotlin.coroutines.suspendCoroutine

/**
 * @Description:    看郭霖协程学习
 * @Author:         zhangchun
 * @CreateDate:     2021/5/5
 * @Version:        1.0
 */

fun main() {
    //GlobalScope.launch函数可以创建一个协程的作用域，这样传递给 launch函数的代码块(Lambda表达式)就是在协程中运行的了，
    // 那么现在运行main()函数，日志能成功 打印出来吗?如果你尝试一下，会发现没有任何日志输出。
    GlobalScope.launch {
        println("codes run in coroutine scope")
    }

    //这是因为，Global.launch函数每次创建的都是一个顶层协程，这种协程 当应用程序运行结束时也会跟着一起结束。
    //刚才的日志之所以无法打印出 来，就是因为代码块中的代码还没来得及运行，应用程序就结束了。
    // 要解决这个问题也很简单，我们让程序延迟一段时间再结束就行了，如下 所示:

    GlobalScope.launch {
        println("codes run in coroutine scope")
    }
    Thread.sleep(1000)

    //可是这种写法还是存在问题，如果代码块中的代码在1秒钟之内不能运行结 束，那么就会被强制中断。观察如下代码:
    //我们在代码块中加入了一个delay()函数，并在之后又打印了一行日志。 delay()函数可以让当前协程延迟指定时间后再运行，但它和 Thread.sleep()方法不同。
    // delay()函数是一个非阻塞式的挂起函数， 它只会挂起当前协程，并不会影响其他协程的运行。而Thread.sleep() 方法会阻塞当前的线程，这样运行在该线程下的所有协程都会被阻塞。
    // 注 意，delay()函数只能在协程的作用域或其他挂起函数中调用。
    GlobalScope.launch {
        println("codes run in coroutine scope")
        delay(1500)
        println("codes run in coroutine scope finished")
    }
    Thread.sleep(1000)


    //那么有没有什么办法能让应用程序在协程中所有代码都运行完了之后再结 束呢?当然也是有的，借助runBlocking函数就可以实现这个功能:
    //runBlocking函数同样会创建一个协程的作用域，但是它可以保证在协程 作用域内的所有代码和子协程没有全部执行完之前一直阻塞当前线程。
    // 需 要注意的是，runBlocking函数通常只应该在测试环境下使用，在正式环 境中使用容易产生一些性能上的问题。
    runBlocking {
        println("codes run in coroutine scope")
        delay(1500)
        println("codes run in coroutine scope finished")
    }

    //那么如何才能创建多个协程呢?很简单，使用launch函数就可以了，如下 所示:
    //注意这里的launch函数和我们刚才所使用的GlobalScope.launch函数 不同。首先它必须在协程的作用域中才能调用，其次它会在当前协程的作 用域下创建子协程。
    // 子协程的特点是如果外层作用域的协程结束了，该作 用域下的所有子协程也会一同结束。
    // 相比而言，GlobalScope.launch函 数创建的永远是顶层协程，这一点和线程比较像，因为线程也没有层级这 一说，永远都是顶层的。
    runBlocking {
        launch {
            println("launch1")
            delay(1000)
            println("launch1 finished")
        }
        launch {
            println("launch2")
            delay(1000)
            println("launch2 finished")
        }
    }

    //可能你需要将部分代码提取 到一个单独的函数中。这个时候就产生了一个问题:我们在launch函数中 编写的代码是拥有协程作用域的，但是提取到一个单独的函数中就没有协 程作用域了
    //为此Kotlin提供了一个suspend关键字，使用它可以将任意函数声明成挂 起函数，而挂起函数之间都是可以互相调用的，如下所示:
    //举例：printDot(),这样就可以在printDot()函数中调用delay()函数了。

//    suspend fun printDot(){
//        println(".")
//        delay(1000)
//    }
    //⚠️suspend关键字只能将一个函数声明成挂起函数，是无法给它提供 协程作用域的。

    //这个问题可以借助coroutineScope函数来解决。coroutineScope函数 也是一个挂起函数，因此可以在任何其他挂起函数中调用。
    // 它的特点是会继承外部的协程的作用域并创建一个子协程，借助这个特性，我们就可以 给任意挂起函数提供协程作用域了。示例写法如下:

//    suspend fun printDot1()= coroutineScope {
//        launch {
//            println(".")
//            delay(1000)
//        }
//    }


    //另外，coroutineScope函数和runBlocking函数还有点类似，它可以 保证其作用域内的所有代码和子协程在全部执行完之前，外部的协程会一 直被挂起。
    //你会看到，控制台会以1秒钟的间隔依次输出数字1到10，然后才会打印 coroutineScope函数结尾的日志，最后打印runBlocking函数结尾的 日志。
    //⚠️
    //虽然看上去coroutineScope函数和runBlocking函数的作用是有点类 似的，但是coroutineScope函数只会阻塞当前协程，既不影响其他协 程，也不影响任何线程，因此是不会造成任何性能上的问题的。
    // 而 runBlocking函数由于会挂起外部线程，如果你恰好又在主线程中当中调 用它的话，那么就有可能会导致界面卡死的情况，所以不太推荐在实际项 目中使用。
    runBlocking {
        coroutineScope {
            launch {
                for (i in 1..10) {
                    println(i)
                    delay(1000)
                }
            }
        }
        println("coroutineScope finished")
    }
    println("runBlocking finished")


    //11.7.2 更多的作用域构建器
    //在上一小节中，我们学习了GlobalScope.launch、runBlocking、 launch、coroutineScope这几种作用域构建器，它们都可以用于创建 一个新的协程作用域。
    // 不过GlobalScope.launch和runBlocking函数 是可以在任意地方调用的，coroutineScope函数可以在协程作用域或挂 起函数中调用，而launch函数只能在协程作用域中调用。
    //前面已经说了，runBlocking由于会阻塞线程，因此只建议在测试环境下 使用。而GlobalScope.launch由于每次创建的都是顶层协程，一般也不 太建议使用，除非你非常明确就是要创建顶层协程。

    //实际项目中协程的取消方法
    //现在所有调用CoroutineScope的launch函数所创建的协程，都会被关 联在Job对象的作用域下面。
    // 这样只需要调用一次cancel()方法，就可以 将同一作用域内的所有协程全部取消，从而大大降低了协程管理的成本。
    val job = Job()
    val scope = CoroutineScope(job)
    scope.launch {
        //处理具体的逻辑
    }
    scope.cancel()

    //你已经知道了调用 launch函数可以创建一个新的协程，但是launch函数只能用于执行一段 逻辑，却不能获取执行的结果，因为它的返回值永远是一个Job对象。
    // 那么 有没有什么办法能够创建一个协程并获取它的执行结果呢?当然有，使用 async函数就可以实现。
    //async函数必须在协程作用域当中才能调用，它会创建一个新的子协程并 返回一个Deferred对象，如果我们想要获取async函数代码块的执行结 果，
    // 只需要调用Deferred对象的await()方法即可，代码如下所示:
    runBlocking {
        val result = async {
            5 + 5
        }.await()
        println(result)
    }
    //⚠️事实上，在调用了async函数之后， 代码块中的代码就会立刻开始执行。
    // 当调用await()方法时，如果代码块 中的代码还没执行完，那么await()方法会将当前协程阻塞住，直到可以 获得async函数的执行结果。
    //async串行执行耗时时间约为2000ms
    runBlocking {
        val start = System.currentTimeMillis()
        val result1 = async {
            delay(1000)
            5 + 5
        }.await()
        val result2 = async {
            delay(1000)
            4 + 6
        }.await()
        println("result is ${result1 + result2}.")
        val end = System.currentTimeMillis()
        println("cost ${end - start} ms.")

    }

    //但是这种写法明显是非常低效的，因为两个async函数完全可以同时执行 从而提高运行效率。现在对上述代码使用如下的写法进行修改:
    //现在我们不在每次调用async函数之后就立刻使用await()方法获取结果 了，而是仅在需要用到async函数的执行结果时才调用await()方法进行 获取，这样两个async函数就变成一种并行关系了
    //async函数并行运行耗时约为1000ms
    runBlocking {
        val start = System.currentTimeMillis()
        val deferred1 = async {
            delay(1000)
            5 + 5
        }
        val deferred2 = async {
            delay(1000)
            4 + 6
        }
        println("result is ${deferred1.await() + deferred2.await()}.")
        val end = System.currentTimeMillis()
        println("cost ${end - start} milliseconds.")
    }

    //最后，我们再来学习一个比较特殊的作用域构建器:withContext()函 数。withContext()函数是一个挂起函数，大体可以将它理解成async函 数的一种简化版写法，示例写法如下:
    //调用withContext()函数之后，会立即执行代码 块中的代码，同时将外部协程挂起。
    // 当代码块中的代码全部执行完之后， 会将最后一行的执行结果作为withContext()函数的返回值返回，
    // 因此基 本上相当于val result = async{ 5 + 5 }.await()的写法。唯一 不同的是，withContext()函数强制要求我们指定一个线程参数
    runBlocking {
        val result = withContext(Dispatchers.Default) {
            5 + 5
        }
        print(result)
    }


    //协程是一种轻量级的线程的概念，因此很多传统编程情况下 需要开启多线程执行的并发任务，现在只需要在一个线程下开启多个协程 来执行就可以了。
    // 但是这并不意味着我们就永远不需要开启线程了，比如 说Android中要求网络请求必须在子线程中进行，即使你开启了协程去执行 网络请求，假如它是主线程当中的协程，那么程序仍然会出错。
    // 这个时候 我们就应该通过线程参数给协程指定一个具体的运行线程。

    //线程参数主要有以下3种值可选:Dispatchers.Default、 Dispatchers.IO和Dispatchers.Main。
    //Dispatchers.Default：表示会使用一种默认低并发的线程策略，当你要执行的代码属于计算密集型任务时，开启过高的并发反而可能会影响任务的运行效率，此时就可以使 用Dispatchers.Default。
    //Dispatchers.IO表示会使用一种较高并发 的线程策略，当你要执行的代码大多数时间是在阻塞和等待中，比如说执 行网络请求时，为了能够支持更高的并发数量，此时就可以使用 Dispatchers.IO。
    //Dispatchers.Main则表示不会开启子线程，而是 在Android主线程中执行代码，但是这个值只能在Android项目中使用，纯 Kotlin程序使用这种类型的线程参数会出现错误。


    //11.7.3 使用协程简化回掉的写法
    //异步网络请求数据都是靠回掉机制实现获取数据响应的功能
    //比如：
//    HttpUtil.sendHttpRequest(address, object : HttpCallbackListener { override fun onFinish(response: String) {
//        // 得到服务器返回的具体内容 }
//        override fun onError(e: Exception) { // 在这里对异常情况进行处理
//        }
//    })

    //只需要借助suspendCoroutine函数就能 将传统回调机制的写法大幅简化，
    //suspendCoroutine函数必须在协程作用域或挂起函数中才能调用，它接 收一个Lambda表达式参数，主要作用是将当前协程立即挂起，
    // 然后在一个 普通的线程中执行Lambda表达式中的代码。Lambda表达式的参数列表上会 传入一个Continuation参数，调用它的resume()方法或 resumeWithException()可以让协程恢复执行。

//    suspend fun request(address: String): String {
//        return suspendCoroutine { continuation ->
//            HttpUtil.sendHttpRequest(address, object : HttpCallbackListener
//            {   override fun onFinish(response: String) {
//                    continuation.resume(response)
//                }
//                        override fun onError(e: Exception) {
//                    continuation.resumeWithException(e)
//                }
//            )
//            }
//        }
//    }

//    你可能会说，这里不是仍然使用了传统回调的写法吗?代码怎么就变得更 加简化了?这是因为，不管之后我们要发起多少次网络请求，都不需要再 重复进行回调实现了。
//    比如说获取百度首页的响应数据，就可以这样写:
//getBaiduResponse()是一个挂起函数，因此当它调用了request()函 数时，当前的协程就会被立刻挂起，然后一直等待网络请求成功或失败 后，
// 当前协程才能恢复运行。这样即使不使用回调的写法，我们也能够获 得异步网络请求的响应数据，而如果请求失败，则会直接进入catch语句 当中。
//    suspend fun getBaiduResponse() {
//        try {
//            val response = request("https://www.baidu.com/")
//            // 对服务器响应的数据进行处理
//        } catch (e: Exception) {
//            // 对异常情况进行处理 }
//        }
//
//    }

    //不过这里你可能又会产生新的疑惑，getBaiduResponse()函数被声明成 了挂起函数，这样它也只能在协程作用域或其他挂起函数中调用了，使用 起来是不是非常有局限性?确实如此，因为suspendCoroutine函数本身 就是要结合协程一起使用的。


}


suspend fun printDot() {
    println(".")
    delay(1000)
}

suspend fun printDot1() = coroutineScope {
    launch {
        println(".")
        delay(1000)
    }
}