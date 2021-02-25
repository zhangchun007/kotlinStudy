package com.jimmy.coroutines

import kotlinx.coroutines.*

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-11-17
 * @Version:        1.0
 */

/**
 * 本质上，协程是轻量级的线程。 它们在某些 CoroutineScope 上下文中与 launch 协程构建器 一起启动。
 * 这里我们在 GlobalScope 中启动了一个新的协程，这意味着新协程的生命周期只受整个应用程序的生命周期限制。
 */
fun main() {
    GlobalScope.launch {
        // 在后台启动一个新的协程并继续
        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒） //delay 是一个特殊的 挂起函数 ，它不会造成线程阻塞，但是会 挂起 协程，并且只能在协程中使用。

        println("World!") // 在延迟后打印输出
    }
    println("Hello,") // 协程已在等待时主线程还在继续
    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
}

//调用了 runBlocking 的主线程会一直 阻塞 直到 runBlocking 内部的协程执行完毕。
fun main2() {
    GlobalScope.launch {
        //在后台启动一个新的协程并继续
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    runBlocking {
        //但是这个表达式阻塞了主线程
        delay(2000L)
    }
}

//上面的写法更改下
fun main3() = runBlocking {
    GlobalScope.launch {
        delay(1000)
        println("World!")
    }
    println("Hello,")
    delay(2000)
}

//延迟一段时间来等待另一个协程运行并不是一个好的选择。让我们显式（以非阻塞方式）等待所启动的后台 Job 执行结束：
fun main4() = runBlocking {
    val job = GlobalScope.launch {
        delay(1000)
        println("World!")
    }
    println("Hello,")
    job.join()
}

//结构化的并发
//协程的实际使用还有一些需要改进的地方。 当我们使用 GlobalScope.launch 时，我们会创建一个顶层协程。
// 虽然它很轻量，但它运行时仍会消耗一些内存资源。如果我们忘记保持对新启动的协程的引用，它还会继续运行。
// 如果协程中的代码挂起了会怎么样（例如，我们错误地延迟了太长时间），如果我们启动了太多的协程并导致内存不足会怎么样？
// 必须手动保持对所有已启动协程的引用并 join 之很容易出错。
//
// 有一个更好的解决办法。我们可以在代码中使用结构化并发。 我们可以在执行操作所在的指定作用域内启动协程，
// 而不是像通常使用线程（线程总是全局的）那样在 GlobalScope 中启动。

fun main5() = runBlocking {
    launch {
        // 在 runBlocking 作用域中启动一个新协程
        delay(1000)
        println("World!")
    }
    println("Hello,")

}

//作用域构建器
//除了由不同的构建器提供协程作用域之外，还可以使用 coroutineScope 构建器声明自己的作用域。它会创建一个协程作用域并且在所有已启动子协程执行完毕之前不会结束。
//runBlocking 与 coroutineScope 可能看起来很类似，因为它们都会等待其协程体以及所有子协程结束。
// 主要区别在于，runBlocking 方法会阻塞当前线程来等待， 而 coroutineScope 只是挂起，会释放底层线程用于其他用途。
// 由于存在这点差异，runBlocking 是常规函数，而 coroutineScope 是挂起函数。

fun main6() = runBlocking {
    launch {
        // 在 runBlocking 作用域中启动一个新协程
        delay(200)
        println("Task from runBlocking")
    }

    coroutineScope {
        // 创建一个协程作用域
        launch {
            delay(500)
            println("Task from nested launch")
        }
        delay(100)
        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
    }
    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
}


//提取函数重构
//我们来将 launch { …… } 内部的代码块提取到独立的函数中。当你对这段代码执行“提取函数”重构时，你会得到一个带有 suspend 修饰符的新函数。
// 这是你的第一个挂起函数。在协程内部可以像普通函数一样使用挂起函数， 不过其额外特性是，同样可以使用其他挂起函数（如本例中的 delay）来挂起协程的执行
fun main7() = runBlocking {
    launch { doWorld() }
    println("Hello,")
}
//// 这是你的第一个挂起函数
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}

//全局协程像守护线程
fun main11()= runBlocking {
    GlobalScope.launch {
        repeat(1000){
            i-> println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // just quit after delay
}