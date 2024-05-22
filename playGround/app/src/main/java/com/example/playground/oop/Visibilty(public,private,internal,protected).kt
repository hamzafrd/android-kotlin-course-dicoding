package com.example.playground.oop

class Animal3(private var name: String, private val weight: Double?=null, private val age: Int?=null, private val isMammal: Boolean?=null){
    fun getName() : String {
        return name
    }

    fun setName(newName: String) {
        name = newName
    }
}
//tidak bisa diakses tanpa getter setter eksplisit
fun main() {
    val dicodingCat = Animal3("Dicoding Miaw")
//    println("Nama: ${dicodingCat.name}, Berat: ${dicodingCat.weight}, Umur: ${dicodingCat.age}, mamalia: ${dicodingCat.isMammal}")
    println("Nama: ${dicodingCat.getName()}")
    dicodingCat.setName("Gooose")
    println(dicodingCat.getName())

    main2()
}

//open artinya bisa di override.
open class Animal4(val name: String, protected val weight: Double)
//mengakses protected dengan getter setter
class Cat(pName: String, pWeight: Double) : Animal4(pName, pWeight)

fun main2() {
    val cat = Cat("Dicoding Miaw", 2.0)
    println("Nama Kucing: ${cat.name}")
//    println("Berat Kucing: ${cat.weight}") //Cannot access 'weight': it is protected in 'Cat'
}