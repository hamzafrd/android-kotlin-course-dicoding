package com.example.playground.generics

//konsep yang memungkinkan suatu kelas atau interface menjadi
//tipe parameter yang dapat digunakan untuk berbagai macam tipe data.
//menggunakan <>

interface List<T>{
    operator fun get(index: Int) : T
}

class LongList : List<Long>{
    override fun get(index: Int): Long {
        return this[index]
    }
}

class ArrayList<T> : List<T>{
    override fun get(index: Int): T {
        return this[index]
    }
}

fun main () {
    var longArrayList = ArrayList<Long>()

    val numbers = (1..100).toList()
//    print(numbers.slice<Int>(1..10))
    print(numbers.slice(1..10))

    val number = ListNumber<Long>()
    val numbers2 = ListNumber<Int>()
//    val numbers3 = ListNumber<String>() // error : Type argument is not within its bounds

    val numbering = listOf(1, 2, 3, 4, 5)
    numbering.sumNumber()
    val names = listOf("dicoding", "academy")
//    names.sumNumber() // error : inferred type String is not a subtype of Number
}

//contoh pada FUNGSI, slice bawaan
public fun <T> List<T>.slice(indices: Iterable<Int>) {
    /*...*/
}

//membatasi tipe parameter (: number)
class ListNumber<out T : Number> : List<Unit> {
    override fun get(index: Int) {
        /* .. */
    }
}

fun <T : Number> kotlin.collections.List<T>.sumNumber() {
    /* .. */
}
