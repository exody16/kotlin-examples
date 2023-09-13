package org.examples.dominoalgo

import kotlin.math.abs

fun main() {
    val str = "123456789"
    val n = str.length
    permute(str, 0, n - 1)
}

private fun permute(str: String, l: Int, r: Int) {
    for (i in 0 until str.length - 1) {
//        if (abs(str[i].toInt() - str[i+1].toInt()) > 1) return
    }



    if (l in 0..2) {
        val spaces = " ".repeat((l) * 4)
//        println("${spaces}str: $str, left: $l, right: $r")
    }
    var str = str
    if (l == r) {
        if (!gameCondition(str, str.length)) return
        println(str)
    } else {
        for (i in l..r) {
            str = swap(str, l, i)
            if (!gameCondition(str, l)) return
            permute(str, l + 1, r)
            str = swap(str, l, i)
        }
    }
}


/**
 * Swap Characters at position
 * @param a string value
 * @param i position 1
 * @param j position 2
 * @return swapped string
 */
fun swap(a: String, i: Int, j: Int): String {
    val temp: Char
    val charArray = a.toCharArray()
    temp = charArray[i]
    charArray[i] = charArray[j]
    charArray[j] = temp
    return String(charArray)
}

fun gameCondition(str: String, n: Int): Boolean {
    if (n < 2) {
        return true
    }
    for (i in 0 .. n - 2) {
        if (abs(str[i].toInt() - str[i+1].toInt()) > 1) return false
    }
    return true
}