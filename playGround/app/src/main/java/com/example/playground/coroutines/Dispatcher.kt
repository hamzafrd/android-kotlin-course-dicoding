@file:OptIn(DelicateCoroutinesApi::class)

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
//    launch(Dispatchers.Default) {
//        println("Starting in ${Thread.currentThread().name}")
//        delay(1000)
//        println("Resuming in ${Thread.currentThread().name}")
//    }.start()
//
//    launch(Dispatchers.IO) {
//        println("Starting in ${Thread.currentThread().name}")
//        delay(1000)
//        println("Resuming in ${Thread.currentThread().name}")
//    }.start()

    //ini yang beda
//    launch(Dispatchers.Unconfined) {
//        println("Starting in ${Thread.currentThread().name}")
//        delay(1000)
//        println("Resuming in ${Thread.currentThread().name}")
//    }.start()

//    Single Thread Context
//    Dispatcher ini menjamin bahwa setiap saat coroutine akan dijalankan pada thread yang Anda tentukan
    val dispatcher = newSingleThreadContext("myThread")
    launch(dispatcher) {
        println("Starting in ${Thread.currentThread().name}")
        delay(1000)
        println("Resuming in ${Thread.currentThread().name}")
    }.start()

//    Thread Pool
//    Sebuah dispatcher yang memiliki kumpulan thread.
    val dispatcher2 = newFixedThreadPoolContext(3, "myPool")

    launch(dispatcher2) {
        delay(1100)
        println("Starting in ${Thread.currentThread().name}")
        delay(1000)
        println("Resuming in ${Thread.currentThread().name}")
    }.start()
}