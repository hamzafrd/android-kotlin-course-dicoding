package com.example.playground.functionalPrograming

fun Any.show(args:String=""){
    println(this)
    if (args!=""){
     println("your args is $args")
    }
}

fun main() {
    val message = "Hello Kotlin!"

    with(message) {
        println("value is $this")
        println("with length ${this.length}")
    }

    StringBuilder().apply {
        append("Hello ")
        append("Kotlin!")
    }.show()

    StringBuilder().apply {
        append("(with also) Hello ")
        append("Kotlin!")
    }.also { println(it) }

    letRUN(null)
    letRUN(message)
}

fun letRUN(messageORNull:String?){
    messageORNull?.let {
        val length = it.length * 2
        val text = "text length $length"
        println(text)
    } ?: run {
        val text = "com.example.playground.part2.getMessage is null"
        println(text)
    }
}