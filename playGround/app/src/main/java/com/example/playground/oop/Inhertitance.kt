abstract class AnimalGeneral(protected val name: String, protected val weight: Double, protected val age: Int, protected val isCarnivore: Boolean){

    open fun eat(){
        println("$name sedang makan!")
    }

    open fun sleep(){
        println("$name sedang tidur!")
    }
}

//Lihat !! ada overloading
class Cat(pName: String, pWeight: Double, pAge: Int, pIsCarnivore: Boolean, val furColor: String, val numberOfFeet: Int)
    : AnimalGeneral(pName, pWeight, pAge, pIsCarnivore) {

    fun getCat(){
        println(name)
    }
    //overloading nama fungsi sama, param berbeda tetap bisa.
    fun getCat(param: String){
        println("$name and your parameters : $param")
    }

    fun playWithHuman() {
        println("$name bermain bersama Manusia !")
    }

    override fun eat(){
        println("$name sedang memakan ikan !")
    }

    override fun sleep() {
        println("$name sedang tidur di bantal !")
    }
}

fun main(){
    val dicodingCat = Cat("Dicoding Miaw", 3.2, 2, true, "Brown", 4)

    dicodingCat.playWithHuman()
    dicodingCat.eat()
    dicodingCat.sleep()
}