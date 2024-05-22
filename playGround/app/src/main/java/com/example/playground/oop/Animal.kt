package com.example.playground.oop

fun main(){
    class Animal (
        val name : String,
        val weight : Double,
        val age : Int,
        val isMammal : Boolean
        ){

        fun eat(){
            "$name is eating".also{println(it)}
        }

        fun sleep(){
            println("$name is sleeping")
        }
    }

    Animal("Zebra",55.5,18,true).eat()
}