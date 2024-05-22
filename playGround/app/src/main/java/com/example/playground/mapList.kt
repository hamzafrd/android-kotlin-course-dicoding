@file:Suppress("SpellCheckingInspection")

package com.example.playground

val capital = mapOf(
    "Jakarta" to "Indonesia",
    "London" to "England",
    "New Delhi" to "India"
)

fun main(){
    println(capital["Amsterdam"]) //hasil null
    println(capital.getValue("Amsterdam")) //hasil error
    println(capital.keys)

    val mutableCapital = capital.toMutableMap()
    mutableCapital["Amsterdam"] = "Netherlands"
    mutableCapital["Berlin"] = "Germany"
    //tidak disarankan
}