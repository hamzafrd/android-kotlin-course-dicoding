@file:Suppress("SpellCheckingInspection")

package com.example.playground.functionalPrograming
//type alias
typealias ArithmeticWithNullSafety = ((Int?, Int?) -> String)?
typealias Arithmetic = ((Int, Int) -> Int)
typealias Arraying = ArrayList<String>
fun Any.printss(){
    println(this)
}

fun main(){
    val sum: ArithmeticWithNullSafety = { valueA, valueB -> (valueB?.let{ valueA?.plus(it) } ?: "There is a null") as String }
    val multiply: Arithmetic = { valueA, valueB -> valueA * valueB }
    val withoutTypeAlias:(Int,Int)->Int = { valueA, valueB->valueA/valueB }
    val Array : Arraying = arrayListOf("hallo", "World")

    val a:Int? = null
    sum?.invoke(6,a)?.printss()
    multiply(6,6).printss()


    //extension
    println(10.plusThree())
    println(10.slice)

    val value:Int? = null
    println(value.slice)

}


//extension function
fun Int.plusThree(): String {
    return "Your value + 3 is ${this + 3}"
}

//extension variable
val Int?.slice: String
    get() =  if(this!=null) "Your value /2 is ${this.div(2)}" else "nah bro"


