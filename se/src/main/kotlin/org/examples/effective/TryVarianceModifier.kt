package org.examples.effective

class BaseClassTestOut<out T>(
    val x: T
)

class BaseClassTestIn<in T> {
    fun inputT(t: T) {
        println(t)
    }
}



fun main() {
    val baseClassTestOut = BaseClassTestOut<Int>(1)

    val baseClassTestIn = BaseClassTestIn<Int>().inputT(1)


}