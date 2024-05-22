package com.example.playground.functionalPrograming

fun main() {
    val numbers = 1.rangeTo(10)
    val evenNumbers = numbers.filter(::isEvenNumber)
    val evenNumbersExtension = numbers.filter(Int::isEvenNumberss)

    println(evenNumbers)

    println(::message.name) //name dari reference (yaitu message)
    println(::message.get())

    ::message.set("Kotlin Academy")
    println(::message.get())
}
var message = "Kotlin" //harus var

fun isEvenNumber(number: Int) = number % 2 == 0
fun Int.isEvenNumberss() = this % 2 == 0


//bonus inner functions in function
fun sum(valueA: Int, valueB: Int, valueC: Int): Int {
    fun Int.validateNumber(){
        if (this == 0) {
            throw IllegalArgumentException("value must be better than 0")
        }
    }

    valueA.validateNumber()
    valueB.validateNumber()
    valueC.validateNumber()

    return valueA + valueB + valueC
}