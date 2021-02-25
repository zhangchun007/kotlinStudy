package com.jimmy.coroutines

import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.coroutineContext

/**
 * @Description: 协程调度篇 https://juejin.cn/post/6844903854245412878
 * @Author:         纯仔
 * @CreateDate:     2021-01-05
 * @Version:        1.0
 */

suspend inline fun Job.Key.currentJob() = coroutineContext[Job]

suspend fun main() {
    val job = GlobalScope.launch(CoroutineName("Hello")) {
        LogUtils.log(Job.currentJob())
    }
    job.join()
    LogUtils.log(Job.currentJob())
}







