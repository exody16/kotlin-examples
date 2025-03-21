package org.examples.myarticle

class Person(name: String, age: Int) {
    val name: String
    var age: Int

    var testString = "sfd"

    val isAdult: Boolean
        get() = age >= 18

    fun greet(greeting: String = "Hello", name: String) {
        println("$greeting, $name!")
    }

    fun getMyString() = "ef"

    init {
        this.name = name
        this.age = age
    }
}

class Rectangle(val width: Int, val height: Int) {
    val isBig: Boolean
        get() = width >= 18
    operator fun component1() = width
    operator fun component2() = height
}

fun main() {
    val rect = Rectangle(5, 10)
    val (width, height) = rect
    println("Width: $width, Height: $height")

    val list = listOf(1, 2, 3,4,5,6,7,8)
    val (a, b, c,d,e) = list

    println(Person("", 1).getMyString())

    val lazyValue: String by lazy { "Computed!" }

    println(lazyValue)
}