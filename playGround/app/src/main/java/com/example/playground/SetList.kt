@file:Suppress("SpellCheckingInspection")

package com.example.playground

private fun main(){
    val setA = setOf(1, 2, 4, 2, 1, 5)
    val setB = setOf(1, 2, 4, 5)
    val setC = setOf(1, 5, 7)
    val union = setA.union(setC)
    val intersect = setA.intersect(setC)

    println(union)
    println(5 in setB)
    println(intersect)

    mutset()
}

//Pada Mutable Set kita bisa menambah dan menghapus item namun tak bisa mengubah nilai seperti pada List.
fun mutset(){
    val mutableSet = mutableSetOf(1, 2, 4, 2, 1, 5)
//mutableSet[2] = 6 // tidak bisa mengubah set immutable
    mutableSet.add(6) // menambah item di akhir set
    mutableSet.remove(1) //menghapus item yang memiliki nilai 1
}