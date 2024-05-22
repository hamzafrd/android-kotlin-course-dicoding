@file:OptIn(InternalCoroutinesApi::class, InternalCoroutinesApi::class)

import kotlinx.coroutines.*

//menggunakan launch():
//fun main() = runBlocking {
//    val job = launch {
//        // Do background task here
//    }
//}

//menggunakan Job():
fun main() = runBlocking {
    val job = launch(start = CoroutineStart.LAZY) {
        delay(5000L)
        println("Start new job!")
    }
    //setelah dijalankan akan menjadi state aktif.
    job.start()
    println("Other task")

//    job.start()
//    println("Other task 2")
//    //menunda eksekusi sampai job selesai .join*(
//    job.join()
//    println("Other task 3")

    delay(2000)
    job.cancel(cause = CancellationException("Time is up!"))
    println("Cancelling job...")
    if (job.isCancelled){
        println("Job is cancelled because ${job.getCancellationException().message}")
    }
}