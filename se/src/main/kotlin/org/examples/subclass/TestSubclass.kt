package org.examples.subclass

import org.examples.ParentAbstractClass

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

class TestSubclassJava: ParentAbstractClass() {

    fun subProtected() {
        javaProtectedParent();
    }

}

fun main() {
    TestSubclass().subProtected();
}