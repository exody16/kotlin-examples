package org.examples.effective

class MyKClass {
}

fun main () {
//    println(MyKClass()::class.isData) // Exception in thread "main" kotlin.jvm.KotlinReflectionNotSupportedError: ake sure you have kotlin-reflect.jar in the classpath

    println(MyKClass()::class.java.name) // org.examples.effective.MyKClass

}