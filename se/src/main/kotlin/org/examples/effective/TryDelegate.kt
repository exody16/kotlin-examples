package org.examples.effective

interface MyInterface {
    fun getDelegatedValue(): Int
}

class MyClass: MyInterface {
    override fun getDelegatedValue(): Int {
        return 16
    }
}

class TryDelegate(delegate: MyInterface) : MyInterface by delegate

// Same affect as using delegate
class TryDelegateWithoutDelegate(private val delegate: MyInterface) {

    fun getDelegatedValue(): Int {
        return delegate.getDelegatedValue()
    }
}

val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}

fun main () {
    // with delegate
    val tryDelegate: TryDelegate = TryDelegate(MyClass())
    println(tryDelegate.getDelegatedValue())

    // without delegate
    val tryDelegateWithoutDelegate = TryDelegateWithoutDelegate(MyClass())
    println(tryDelegateWithoutDelegate.getDelegatedValue())

    println(lazyValue)
    println(lazyValue)
}