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
fun main() = runBlocking {
    val job = GlobalScope.launch {
        // launch a new coroutine and keep a reference to its Job
        delay(1000L)
        println("World!")
    }
}


