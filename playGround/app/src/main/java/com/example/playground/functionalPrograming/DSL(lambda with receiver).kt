package com.example.playground.functionalPrograming


fun theDefaultGuy(): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append("Hello ")
    stringBuilder.append("from ")
    stringBuilder.append("lambda")
    return stringBuilder.toString()
}

fun main() {
    val gigaChad = buildString {
        append("Hello ")
        append("from ")
        append("GigaChad")
    }

    println(gigaChad)
}

fun buildString(ciaau: StringBuilder.() -> Unit): String {
    val stringBuilder = StringBuilder()
    stringBuilder.ciaau()
    return stringBuilder.toString()
}
/*
   output : Hello from lambda
*/