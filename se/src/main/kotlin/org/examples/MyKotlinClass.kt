package org.examples

class MyKotlinClass {

    companion object {

        val SOME_FIELD1 = "FIELD"

        @JvmField
        val SOME_FIELD2 = "FIELD"

        @JvmStatic
        fun forId(id: Int) {}
    }
}

fun main() {
    val myJavaClass = MyJavaClass()

    // Platform type (!)
    val unknownFieldPlatformType = myJavaClass.unknownField

    // NPE
//    print(unknownFieldPlatformType.length)

    // Eliminate platform types as soon as possible
    val unknownField: String? = myJavaClass.unknownField
    print(unknownField?.length)


    val nullableField = myJavaClass.nullableField

    val notNullField = myJavaClass.notNullField



    //  keep in mind that IntelliJ might help you with type hints, but in PR is not visible.

}

