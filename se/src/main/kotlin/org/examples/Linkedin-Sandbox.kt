package org.examples

import kotlin.properties.Delegates


fun main(args: Array<String>) {
    val highSchores = listOf(3, 1, 2)

    highSchores.sortedDescending()
        .forEach { println(it) }

    val s = """"
    hello\n\n
    """

    println(s)

    println(Empty())

    generateSequence(1.99) { if (it < 99) it +1 else null}.toList()

    for(count in 1..1000) {
        simple(count)
    }

    println(Inite().x)

    println(listOf(1,2,3,4).drop(2))

    println(::add.invoke(1))

}




fun add(a: Int): Int {
    return a
}

class Inite {
    var x = 5;

    init {
        x = 6;
    }

    companion object {
        const val COLO = "Blue"
        @JvmField val SIE = Empty()
    }

}


inline fun simple(x: Int): Int {
    return x * x
}

class Empty {
    val name: String by Delegates.notNull()
}


abstract class Person(val name: String) {
    abstract fun displayJob(description: String);
}

class P(name: String): Person(name) {
    override fun displayJob(description: String) {
        TODO("Not yet implemented")
    }

}