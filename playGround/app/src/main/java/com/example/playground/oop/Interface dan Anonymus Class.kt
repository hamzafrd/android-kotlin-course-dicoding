package com.example.playground.oop

//Interfaces merupakan suatu konsep sifat umum yang
//nantinya digunakan oleh suatu kelas agar dapat MEMILIKI SIFAT tersebut.
interface IFly {
    fun fly()
    val numberOfWings: Int
}

class Bird(override val numberOfWings: Int) : IFly {
    override fun fly() {
        if(numberOfWings > 0) println("Flying with $numberOfWings wings")
        else println("I'm Flying without wings")
    }
}

fun mainWithAnonymousClass() {
    //gunakan "object":
    flyWithWings(object : IFly {
        override fun fly() {
            if(numberOfWings > 0) println("Flying with $numberOfWings wings")
            else println("I'm Flying without wings")
        }

        //params disini
        override val numberOfWings: Int
            get() = 2
    })
}


fun flyWithWings(bird: IFly) {
    bird.fly()
}

fun main() {
    flyWithWings(Bird(2))
    mainWithAnonymousClass()
}

