package com.example.playground

fun main() {
    val colorValue: Array<Color> = Color.values()
    val colorValue2: Color = Color.valueOf("RED")
    colorValue.forEach{ color -> colorPrint(color) }


    val rangeNumber = 1..2 step 1
    val orRange = {
        1.rangeTo(10)
        10.downTo(1)
    }

    rangeNumber.forEach{
        print("$it ")
    }
    for ((i,index) in rangeNumber.withIndex()){
        print("\nindex : $index with value of ${i.toChar()}");
    }

    println();
    val listOfIntNullable = listOf(1, 2, 3, null, 5, null, 7)
    for (i in listOfIntNullable){
        if (i == null) continue
        print(i)
    }

    println();
    loop@ for (i in 1..10) {
        println("Outside Loop")

        for (j in 1..10) {
            println("Inside Loop")
            if ( j > 2) break@loop
        }
    }

    for (i in 10 until 12) {
        print("$i ")
    }
}

enum class Color(val value: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

fun colorPrint(value: Color) = print("$value ")
