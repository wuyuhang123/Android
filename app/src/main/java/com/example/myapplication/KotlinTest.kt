package com.example.myapplication

import kotlinx.coroutines.*

/**
 * @Author wuyuhang
 * @Date 2023/7/9 20:25
 * @Describe 用于测试kotlin协程相关的东西
 */
fun main(args: Array<String>) {
    testKotlinCoroutineScope4()
}

fun testKotlinCoroutine1() {
    log("start")
    GlobalScope.launch {
        launch {
            delay(400)
            log("launch A")
        }
        launch {
            delay(300)
            log("launch B")
        }
        log("GlobalScope")
    }
    log("end")
    Thread.sleep(500)
}

fun testKotlinCoroutineScope2() {
    log("start")
    runBlocking {
        launch {
            repeat(3) {
                delay(100)
                log("launchA - $it, time: ${System.currentTimeMillis()}")
            }
        }
        launch {
            repeat(3) {
                delay(100)
                log("launchB - $it, time: ${System.currentTimeMillis()}")
            }
        }
        GlobalScope.launch {
            repeat(3) {
                delay(120)
                log("GlobalScope - $it, time: ${System.currentTimeMillis()}")
            }
        }
    }
    log("end")
}

fun testKotlinCoroutineScope3() {
    GlobalScope.launch(Dispatchers.IO) {
        delay(600)
        log("GlobalScope")
    }
    runBlocking {
        delay(500)
        log("runBlocking")
    }
    //主动休眠两百毫秒，使得和 runBlocking 加起来的延迟时间多于六百毫秒
    Thread.sleep(200)
    log("after sleep")
}

fun testKotlinCoroutineScope4() {
    runBlocking {
        launch {
            delay(100)
            log("Task from runBlocking")
        }
        coroutineScope {
            launch {
                delay(500)
                log("Task from nested launch")
            }
            delay(50)
            log("Task from coroutine scope")
        }
        log("Coroutine scope is over")
    }
}

val job = Job()

val scope = CoroutineScope(job + Dispatchers.IO)

fun testKotlinCoroutineScope5(): Unit = runBlocking {
    log("job is $job")
    val job = scope.launch {
        try {

            delay(3000)
        } catch (e: CancellationException) {
            log("job is cancelled")
            throw e
        }
        log("end")
    }
    delay(1000)
    log("scope job is ${scope.coroutineContext[Job]}")
    scope.coroutineContext[Job]?.cancel()
}


private fun log(msg: Any?) = println("[${Thread.currentThread().name}] $msg")





