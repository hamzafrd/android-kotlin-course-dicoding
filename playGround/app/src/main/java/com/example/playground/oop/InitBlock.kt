package com.example.playground.oop

//perhatikan nama parameters dan properties

//PROPERTI BLOCK MEMUNGKINkAN UNTUK MENULISKAN PROPERTI DI DALAM BODY
//tidak ada val di props , maka dari itu default constructor secara otomatis mati.
class Animal2(name: String, age: Int){
    val name: String
    val age: Int
    var isMammal: Boolean //harus ganti var
    var weight: Double = 0.0 //harus init angka atau di blok init

    init {
//        weight = if(pWeight < 0) 0.1 else pWeight -> tidak bisa memanggil parameter construct
        this.age = if(age < 0) 0 else age
        this.name = name
        isMammal = false
    }

    constructor(name: String, pWeight: Double , age: Int, isMammal: Boolean) : this(name, age) {
        this.isMammal = isMammal
        weight = if (pWeight < 0) {
            0.0
        } else {
            pWeight
        }
    }
}

fun main() {
    val dicodingCat = Animal2("Dicoding Miaw", -999999999.9, 2, true)
    println("Nama: ${dicodingCat.name}, Berat: ${dicodingCat.weight}, Umur: ${dicodingCat.age}, mamalia: ${dicodingCat.isMammal}")
}