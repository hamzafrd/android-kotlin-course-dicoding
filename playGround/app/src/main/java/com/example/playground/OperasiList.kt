@file:Suppress("SpellCheckingInspection")

package com.example.playground

private fun main(){
    val numberList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(numberList)

    val evenList = numberList.filter { it.rem(2)  == 0 }
    println(evenList)

    val notEvenList = numberList.filterNot { it % 2 == 0 }
    println(notEvenList)

    val multipliedBy5 = numberList.map { it * 5 }
    println(multipliedBy5.count { it % 3 == 0 })

    val firstOddNumber = numberList.find { it % 2 == 1 }
    println("First Odd Number : $firstOddNumber")

    val firstOrNullNumber = numberList.firstOrNull { it % 2 == 1 }
    println(firstOrNullNumber)

    val moreThan10 = numberList.first { it > 5 }
    println(moreThan10) //6

//    val total = numberList.sum()

    val kotlinChar = listOf('k', 'o', 't', 'l', 'i', 'n')
    val ascendingSort = kotlinChar.sorted()
//    val descendingSort = kotlinChar.sortedDescending()
    println(ascendingSort)
}
