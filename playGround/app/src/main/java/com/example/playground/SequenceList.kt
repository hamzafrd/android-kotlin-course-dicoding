@file:Suppress("SpellCheckingInspection")

package com.example.playground

fun main() {
    val list = (1..1000000).toList()

    //ini biasanya yaitu eager eval atau horizontal eval. olah data baru kemudian di map.
    list.filter { it % 5 == 0 }.map { it * 2 }.forEach { println(it) }

    //ini lazy atau vertical eval, dimulai dari 1, tidak memenuhi tidak lanjut. klo memenuhi LANGSUNG lanjut map.
    list.asSequence().filter { it % 5 == 0 }.map { it * 2 }.forEach { println(it) }

    //objek sequence
    val sequenceNumber = generateSequence(1) { it + 1 }
    sequenceNumber.take(5).forEach { print("$it ") } // Output: 1 2 3 4 5
}