package org.examples.subclass

open class ParentClass {

    private val privateField: Int = 1

    protected fun getPrivateField(): Int {
        println("Protected")
        return privateField
    }

}

class TestSubclass: ParentClass() {

    fun subProtected():Int {
        return getPrivateField()
    }

}


fun main() {
    TestSubclass().subProtected()
}