package com.example.playground.functionalPrograming

fun main(){
    val numbers = listOf(1, 2, 3)
    val fold = numbers.fold(10) { current, item ->
        println("current $current")
        println("item $item")
        println()
        current + item
    }

    println("Fold result: $fold")

/*output:
       current 10
       item 1

       current 11
       item 2

       current 13
       item 3

       Fold result: 16
*/

    numbers.foldRight(10) { item, current ->
        println("current $current")
        println("item $item")
        println()
        item + current
    }.also { println("Fold result: $fold")}

/*output:
       current 10
       item 3

       current 13
       item 2

       current 15
       item 1

       Fold result: 16
*/
    val number = listOf(1, 2, 3, 4, 5, 6)
    number.drop(3).also { println(it)}
    number.dropLast(3).also { println(it)}
    /*
   output: [4, 5, 6] / [1,2,3]
    */

    number.take(3) //output: [1, 2, 3]
    number.takeLast(3)

    //////////////////////////////// SLICE
    val total = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val slice = total.slice(3..6 step 2)

    println(slice) // output: [4, 5, 6, 7] with step : output: [4, 6]
    val index = listOf(2, 3, 5, 8) //total[2]=3, total[3]=4
    total.slice(index).also { println(it)} //output: [3, 4, 6, 9]

    /////////////////////////////// DISTINCT
    val totals = listOf(1, 2, 1, 3, 4, 5, 2, 3, 4, 5)
    val distinct = totals.distinct()

    println(distinct) // output: [1, 2, 3, 4, 5]

    /////CHUNKED
    val word = "QWERTY"
    val chunked = word.chunked(3)

    word.chunked(3) {
        it.toString().lowercase()
    }.also { println(it) }

    println(chunked) //output:[QWE, RTY]

    distinct()
}

data class Item(val key: String, val value: Any)

fun distinct(){
    val items = listOf(
        Item("1", "Kotlin"),
        Item("2", "is"),
        Item("3", "Awesome"),
        Item("3", "as"),
        Item("3", "Programming"),
        Item("3", "Language")
    )

    val distinctItems = items.distinctBy { it.key }
    distinctItems.forEach {
        println("${it.key} with value ${it.value}")
    }

    /*
   output:
       1 with value Kotlin
       2 with value is
       3 with value Awesome
    */

    val text = listOf("A", "B", "CC", "DD", "EEE", "F", "GGGG")
    val distinct = text.distinctBy {
        it.length
    }

    println(distinct) //output: [A, CC, EEE, GGGG]
}