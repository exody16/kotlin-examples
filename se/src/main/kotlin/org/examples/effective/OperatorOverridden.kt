package org.examples.effective


infix fun Int.myInfix(x: Int) = this + 10

class MemberClassForInfix {
    infix fun myInfixClassName(suffix: String) = this.javaClass.simpleName + " " + suffix
}


fun main() {

    println(2.myInfix(1)) // 12 infix function
    println(2 myInfix 1) // 12  12 - omitting the dot and the parentheses for the call

    println(MemberClassForInfix() myInfixClassName "suffix")

    println(2.plus(5)) // 5 default plus operator

}