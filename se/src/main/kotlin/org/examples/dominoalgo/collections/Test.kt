package org.examples.dominoalgo.collections

data class MyObj(
   val myValues: List<String>
) {

    companion object {
        fun fromValues(myValues2: List<Int> = emptyList()): MyObj {
            return MyObj(myValues2.map { it.toString() })
        }
    }
}

fun main() {
   println(MyObj(listOf("1gwegw")))
    println(MyObj.fromValues(listOf(1)))
}
