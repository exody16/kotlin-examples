package org.examples

import java.util.*

const val DESIRED_PERSON_PER_DAY = 2

fun main() {

    val napok = intArrayOf(1, 0, 1, 0, 0).asList()

    for (p1: Int in 0..4) {
        val newN1 = rotateRight(napok, p1)
        for (p2: Int in 0..4) {
            val newN2 = rotateRight(napok, p2)

            for (p3: Int in 0..4) {
                val newN3 = rotateRight(napok, p3)

                for (p4: Int in 0..4) {
                    val newN4 = rotateRight(napok, p4)

                    for (pLast: Int in 0..4) {
                        val last = rotateRight(napok, pLast)
//                    val isMatching = checkResults(newN1, newN2, last)
//                   val isMatching = checkResults(newN1, newN2, newN3, last)
                        val isMatching = checkResults(newN1, newN2, newN3, newN4, last)
                        if (isMatching) return
                    }
                }
            }


        }
    }

    println("NO RESULTS!")

}

fun rotateRight(l: List<Int>, distance: Int): List<Int> {
    val newList = ArrayList(l)
    Collections.rotate(newList, distance)
    return newList
}

fun checkResults(vararg nList: List<Int>): Boolean {
    if (nList.isEmpty()) {
        return false
    }
    var totalList = nList[0].toMutableList()

    nList.drop(1)
        .forEach {
            totalList = totalList.zip(it) { e1, e2 -> e1 + e2 }.toMutableList()
        }

    val isMatching = totalList.filter { it >= DESIRED_PERSON_PER_DAY }.size == totalList.size
    if (isMatching) {
        println("=============")
        println("RESULT")
        println("=============")
        nList.forEach { nL -> println(nL) }
        println("SUM:")
        println(totalList)
    }
    return isMatching

}



