package com.jimmy.coroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


/**
 * @Description: 协程启动篇 https://juejin.cn/post/6844903854245412871#heading-1
 * @Author:         纯仔
 * @CreateDate:     2021-01-04
 * @Version:        1.0
 *
 * DEFAULT：立即执行协程体
 * ATOMIC：立即执行协程体，但在开始运行之前无法取消
 * UNDISPATCHED：立即在当前线程执行协程体，直到第一个 suspend 调用
 * LAZY：只有在需要的情况下运行
 *
 * 调用 Job.start，主动触发协程的调度执行
 * 调用 Job.join，隐式的触发协程的调度执行 --因为要等待协程执行完毕，因此输出的结果一定是：
 */

/**
 * DEFAULT 立即执行协程体
 *
 * DEFAULT 是饿汉式启动，launch 调用后，会立即进入待调度状态，一旦调度器 OK 就可以开始执行
 * 运行结果：
 * 16:07:26:546 [main] 1
 * 16:07:26:605 [main] 3
 * 16:07:26:606 [DefaultDispatcher-worker-1] 2
 * 16:07:26:609 [main] 4
 *
 *  job.join() 协程执行
 *  job.start() 协程不执行
 */
suspend fun main1() {

    log(1)
    val job = GlobalScope.launch {
        log(2)
    }
    log(3)
    job.join()
    log(4)
}

/**
 * LAZY 只有在需要的情况下运行
 *
 * LAZY 是懒汉式启动，launch 后并不会有任何调度行为，协程体也自然不会进入执行状态，直到我们需要它执行的时候。
 * 这其实就有点儿费解了，什么叫我们需要它执行的时候呢？就是需要它的运行结果的时候， launch 调用后会返回一个 Job 实例，对于这种情况，我们可以：
 * .调用 Job.start，主动触发协程的调度执行
 * .调用 Job.join，隐式的触发协程的调度执行
 *
 * job.join() 协程执行
 * 执行结果：1，3，2，4
 * job.start() 协程不执行
 * 执行结果：1，3，4
 *
 *
 */
suspend fun main12() {
    log(1)
    val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
        log(2)
    }
    log(3)
    job.join()
    log(4)
}

/**
 * ATOMIC 立即执行协程体，但在开始运行之前无法取消
 *
 * ATOMIC 只有涉及 cancel 的时候才有意义，cancel 本身也是一个值得详细讨论的话题，在这里我们就简单认为 cancel 后协程会被取消掉，也就是不再执行了。
 * 那么调用 cancel 的时机不同，结果也是有差异的，例如协程调度之前、开始调度但尚未执行、已经开始执行、执行完毕等等。
 */
suspend fun main13() {
    //执行结果是：
    //16:27:20:645 [main] 1
    //16:27:20:698 [DefaultDispatcher-worker-1] 2
    //16:27:20:700 [main] 3
//    log(1)
//    val job = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
//        log(2)
//    }
//    job.cancel()
//    log(3)

    //需要注意的是，cancel 调用一定会将该 job 的状态置为 cancelling，只不过ATOMIC 模式的协程在启动时无视了这一状态。
    //实际上在遇到第一个挂起点之前，它的执行是不会停止的，而 delay 是一个 suspend 函数，这时我们的协程迎来了自己的第一个挂起点，
    // 恰好 delay 是支持 cancel 的，因此后面的 3 将不会被打印。
    //运行结果：1，2，4
//    16:32:09:476 [main] 1
//    16:32:09:529 [DefaultDispatcher-worker-1] 2
//    16:32:09:530 [main] 4
    log(1)
    val job = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
        log(2)
        delay(1000)
        log(3)
    }
    job.cancel()
    log(4)
    job.join()
}

/**
 * UNDISPATCHED
 * 协程在这种模式下会直接开始在当前线程下执行，直到第一个挂起点，这听起来有点儿像前面的 ATOMIC，
 *  不同之处在于 UNDISPATCHED 不经过任何调度器即开始执行协程体。当然遇到挂起点之后的执行就取决于挂起点本身的逻辑以及上下文当中的调度器了
 *
 *  运行结果：
 *  16:34:01:415 [main] 1
 *  16:34:01:452 [main] 2
 *  16:34:01:464 [main] 4
 *  16:34:01:591 [DefaultDispatcher-worker-1] 3
 *  16:34:01:592 [DefaultDispatcher-worker-1] 5
 *
 */
suspend fun main() {

    log(1)
    val job = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
        log(2)
        delay(100)
        log(3)
    }
    log(4)
    job.join()//不调用该方法跟ATOMIC模式一样
    log(5)

}


val dateFormat = SimpleDateFormat("HH:mm:ss:SSS")
val now = {
    dateFormat.format(Date(System.currentTimeMillis()))
}

fun log(msg: Any?) = println("${now()} [${Thread.currentThread().name}] $msg")



