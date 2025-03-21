package org.examples.myarticle

// 1. Extension functions
//fun multiply(receiver:Int): Int = receiver * 2
//
//// 2. Data classes
//class NoSugarDataClass(
//    val id: Int
//) {
//
//}
//
//// 3. String templates
//val greeting = StringBuilder().append("Hello, ").append(name).append("!").toString()


// Lambda expression
class LambdaImpl : (Int) -> Int {
    override fun invoke(x: Int): Int {
        return x * x
    }
}
