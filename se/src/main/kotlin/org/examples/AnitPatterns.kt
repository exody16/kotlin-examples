package org.examples

import java.util.*

fun main() {

    //  1. The Iterate-and-Mutate Programming Anti-Pattern
    val listOfStrings = listOf("1", "2", "3")
    val mutableList: MutableList<Int> = mutableListOf()

    // Wrong
    listOfStrings.mapNotNull { it.toIntOrNull() }
        .forEach { mutableList.add(it) }

    // Correct
    val list = listOfStrings.mapNotNull { it.toIntOrNull() }


    // 2. Avoid using !! where it's not necessary
    // Wrong - NPE is possible
    fun printLength1(x: String?) {
        println(x!!.length)
    }

    // Correct - null value is swallowed
    fun printLength2(x: String?) {
        x?.let { println(x) }
    }

    // Correct - throw meaningful exception
    fun printLength3(x:String?) {
        x?.let { println(x) }
            ?: throw IllegalArgumentException("Argument cannot be null")
    }

    //   3. Don't use "it" in multi-line expression
    // Wrong
    listOf("1", "2", "3").map {
        Pair(
            first = it.toInt(),
            second = it.toInt() + 1
        )
    }

    // Correct
    listOf("1", "2", "3").map { number: String ->
        Pair(
            first = number.toInt(),
            second = number.toInt() + 1
        )
    }


    //   4. Use val + assignment instead of var instead
    val text = "11"

    // Wrong
    var input1 = 0
    if (text.isNotEmpty()) {
        input1 = text.toInt()
    }

    // Correct
    val input2 = when {
        text.isNotEmpty() -> text.toInt()
        else -> 0
    }


    //   5. Expose statics to Java through @JvmField or @JvmStatic instead of companions

    // Wrong
    //String someField1 = MyKotlinClass.Companion.getSOME_FIELD1();

    // Correct
    //String someField2 = MyKotlinClass.SOME_FIELD2;


    //   6. Use constructors with default arguments instead of telescoping constructors (multiple constructors with different arguments)
    // Wrong
    class MyClass1(val x: Int, val y: Int, val z: Int) {
        constructor(x: Int) : this(x, 2, 3)
        constructor(x: Int, y: Int) : this(x, y, 3)
    }

    // Correct - default constructors
    class MyClass2(val x: Int, val y: Int = 2, val z: Int = 3)

    // Correct - factory methods


    //    7. Use naming arguments when there are multiple arguments with the same type
    fun myFun(x: Int, y: Int) {}

    // Wrong
    myFun(1, 2)

    // Correct
    myFun(x = 1, y = 2)

    // Correct - when Calling from Java, pass a Local Transfer Object (LTO)


    //   8. Eliminate platform types as soon as possible
    // Wrong
    // Platform type (!)
    val unknownFieldPlatformType = MyJavaClass().unknownField

    // Correct
    val unknownField: String? = MyJavaClass().unknownField

    // Correct
    val nullable = MyJavaClass().nullableField

    // Correct
    val notNull = MyJavaClass().notNullField


    //  9. Don't use optional
    // Wrong
    val xOptional: Optional<Int> = Optional.of(1)
    xOptional.ifPresentOrElse(
        { println("Present $it") },
        { println("Not present") })

    // Correct
    val xNullable: Int = 1
    xNullable
        ?.let { println("Present $it") }
    //        ?.let{ println("Present $xNullable") } // or with smart cast
        ?: { println("Not present") }


    //  10. Don't use stream


    // Wrong
    listOf(1, 2, 3).stream()
        .map { it + 1 }
        .toList()

    // Correct
    listOf(1, 2, 3)
        .map { it + 1 }


    //   11. Prefer to return null or Result.failure(additional information in the case of failure) when an error is expected, and an exception when an error is not expected
    // Wrong (error is expected)
    fun myParse1(x: String): Int {
        return try {
            x.toInt()
        } catch (e: Exception) {
            println(e)
            throw RuntimeException("Not a number")
        }
    }

    // Correct with Result (additional information in the case of failure
    fun myParse2(x: String): Result<Int> {
        return x.toIntOrNull()
            ?.let { number -> Result.success(number) }
            ?: Result.failure(RuntimeException("Not a number"))
    }

    // Correct with null
    fun myParse3(x: String): Int? {
        return x.toIntOrNull()
    }


    //  12. Reduce cognitive load - shorter is not always better
    data class Person(val isAdult: Boolean)

    val person = Person(true)

    person.takeIf { it.isAdult }
        ?.let { println("Is adult") }
        ?: println("Is not adult")


    if (person != null && person.isAdult) {
        println("Is adult")
    } else {
        println("Is not adult")
    }


    // 13. Don't use nullable collections
    val x: Collection<Int> = listOf()

    // Wrong
    fun process1(x: Collection<Int>?) {}
//...

    process1(x)

    // Correct
    fun process2(x: Collection<Int>) {}
//...
    process2(x ?: listOf())


}

