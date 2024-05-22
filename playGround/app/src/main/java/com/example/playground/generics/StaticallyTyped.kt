package com.example.playground.generics

fun main (){
    fun Any.p(){
        println(this)
    }
                    //tidak perlu menulis
    val points = mapOf<String, Int>( "alfian" to 10 , "dimas" to 20 ).also { println(it) }
    val statically = mapOf( "alfian" to 10 , "dimas" to 20 ).also { println(it) }
    val kecuali = mapOf<String,String>()

    points["alfian"]?.p()
    points.getValue("dimas").p()
}
