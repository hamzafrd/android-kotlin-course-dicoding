@file:Suppress("SpellCheckingInspection")

package com.example.playground.functionalPrograming
typealias number = (Int)->Int

fun main() {
    resulting(10 ,doubleTheValue)
    //resulting(initValue = 10, doubleIT = value -> value + value)

    //HERE IS HOF
    println(resulting(50){value -> value + value})
}

fun testPerformance() {
    //tools->kotin bytecode->semakin banyak isntance peforma semakin turun
    //inline -> meningkatkan peforma tetapi memakan ruang yang lebih/waktu kompilasi.
    resulting(10,doubleTheValue)
    resulting(20){ value ->
        value + value
    }
}

inline fun resulting(initValue: Int, doubleIT: number) {
    val result = doubleIT(initValue)
    println(result)
}

//inilah kenapa HOF diperlukan, karena extension tetap melakukan hal yang sama dengan tanpa inline.
inline val doubleTheValue: number
    get() = { value -> value + value }




