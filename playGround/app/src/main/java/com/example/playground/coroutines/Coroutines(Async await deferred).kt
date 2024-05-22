package com.example.playground.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking{
    launch {
        delay(1000L)
        println("Coroutines!")
    }
    println("Hello,")
    delay(1000L)
////////////////////////////////////////////////////////////////////////////////////////////////
    val timeOne = measureTimeMillis {
        val capital = getCapital()
        val income = getIncome()
        println("Your profit is ${income - capital}")
    }

    val timeTwo = measureTimeMillis {
        val capital = async { getCapital() }
        val income = async { getIncome() }
        println("Your profit is ${income.await() - capital.await()}")
    }

    println("Completed in $timeOne ms@timeOne vs $timeTwo ms@timeTwo")
}

//nilai deferred
suspend fun getCapital(): Int {
    delay(1000L)
    return 50000
}

suspend fun getIncome(): Int {
    delay(1000L)
    return 75000
}