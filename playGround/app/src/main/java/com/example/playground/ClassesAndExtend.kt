package com.example.playground

class User(private val name: String, private val age: Int) {
    override fun toString(): String {
        return "User(name=$name, age=$age)"
    }

    fun introduce() {
        println("Hallo my name is $name im $age years old")
    }
}

data class DataUser(val name: String, val age: Int)

fun DataUser.introduce() {
    println("Hallo my name is $name im $age years old")
}

private fun main() {
    val user = User("northmen", 17)
    val dataUser = DataUser("northmen", 17)
    val dataclass2 = dataUser.copy()
    val dataUser3 = dataUser.copy(age = 12)
    println(user)
    println(dataUser.name)

    //Kelebihan data Class
    println()
    isTheSameUser(b = dataUser, a = dataclass2)
    isTheSameUser(dataUser3, dataUser)

    //cek tipe
    println()
    val tipe = DataUser::class
    if (tipe.isData) print("data class : ") else print("nope")
    print("${tipe.simpleName}")

    //destruct
    println()
    user.introduce()

    //varargs
    val sumSUM = intArrayOf(4, 4, 4)

    //spread parameter
    val sum = sumNumbers(5, 5, *sumSUM, 5)
    println(sum)

    getMessage("hamza")
    val getMessage2 = { name: String -> getMessage(name) }.also { println(it) }
}

private fun isTheSameUser(a: DataUser = DataUser("Hayuyuyu", 99), b: DataUser) = println(a == b)

private fun sumNumbers(vararg number: Int): Int {
    return number.size
}

fun getMessage(name: String): String {
    return "Hello $name"
}

