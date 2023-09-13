package org.examples.dominoalgo

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

class Domino(
    val n: Int,
    val withCondition: Boolean,
) {
    val solutions = Solutions()

    /**
     * Calculates all the possible permutations of the "domino" game
     * ATTENTION: Currently ends only for n <=4
     * Solutions:
     * n = 3 (S, O...,T...)  -> Total solutions: 78. Time(ms): 289. Factorial: 3628800. Perm index: 18840. Skipped perms: 33615
     * n = 3 (S, O, T, O...) -> Total solutions: 550. Time(ms): 279. Factorial: 3628800. Perm index: 21496. Skipped perms: 33850
     * n = 4 (S, O...,T...) -> Total solutions: 1683. Time(ms): 71033. Factorial: 1307674368000. Perm index: 36583276. Skipped perms: 129333593
     * n = 4, (S, O, T, O...) -> Total solutions: 95525. Time(ms): 222879. Factorial: 1307674368000. Perm index: 42614832. Skipped perms: 124046040
     *
     */
    fun permDomino(listInit: ListInit) {
        // Initialize list
        val list = ArrayDeque<Dom>()
        // highest domino value
        (0..n).forEach { i ->
            list.addLast(Dom(i, i))
            (i + 1..n).forEach { j ->
                list.addLast(Dom(i, j))
            }
        }
//    list.shuffle()

        // Set players
        list[0].player = Player.START

        val size = list.size
        if (listInit == ListInit.SOOT) {
            for (i in 1..size / 2) {
                list[i].player = Player.ONE
            }
            for (i in size / 2 + 1 until list.size) {
                list[i].player = Player.TWO
            }
        } else if (listInit == ListInit.SOTO) {
            for (i in 1..size / 2) {
                list[i * 2 - 1].player = Player.ONE
            }
            for (i in 1 until size / 2) {
                list[i * 2].player = Player.TWO
            }
        }

//    list.shuffle()

        solutions.factorial = factorialFunc(list.size)
        println("Size: ${list.size}. Total permutations: ${solutions.factorial}. Full list: $list")

        permute(list, 0, size - 1)

        solutions.printSolutionsSummary()
    }

    /**
     *  Permutates the list
     *  If l == r, it means that it's a unique leaf, so we can extract the solution
     */
    private fun permute(list: List<Dom>, l: Int, r: Int) {
        if (l == r) {
            solutions.permIndex++
//        if (solutions.permIndex % 100L == 0L) {
//            println("${solutions.permIndex} out of ${solutions.factorial}")
//        }
            val matchingList = calculateMatchingList(list)

            // enable this to get only matching list of a given size
//        if (matchingList.size < 10) return
            if (!solutions.addIfNotContains(matchingList)) {
                return
            }
//        if (matchingList.size % 2 != list.size % 2 && matchingList.size != list.size) {
//            println("${Player.ONE} Wins! Total:${list.size} Doms:${matchingList.size}   $matchingList       $list")
//        } else {
//            println("${Player.TWO} Wins! Total:${list.size} Doms:${matchingList.size}   $matchingList       $list")
//        }
        } else {
            for (i in l..r) {
                Collections.swap(list, l, i)
                if (withCondition && !gameCondition(list, l)) {
                    solutions.skippedPerms++
                return
                }
                permute(list, l + 1, r)
                Collections.swap(list, l, i)
            }
        }
    }

    /**
     * Gets the longest list that has a valid condition
     */
    fun calculateMatchingList(list: List<Dom>): MutableList<Dom> {
        val startDom = list.first { it.player == Player.START }
        val startDomIndex = list.indexOf(startDom)
        val matchingList = ArrayDeque(mutableListOf<Dom>(startDom))
        var leftIsOver = false
        var rightIsOver = false

        for (i in 1 until list.size - 1) {
            if (startDomIndex - i >= 0 && !leftIsOver) {
                val testList = ArrayDeque(matchingList.toMutableList())
                testList.addFirst(list[startDomIndex - i])
                if (gameCondition(testList, testList.size)) {
                    matchingList.addFirst(list[startDomIndex - i])
                } else {
                    leftIsOver = true
                }
            }
            if (startDomIndex + i < list.size && !rightIsOver) {
                val testList = ArrayDeque(matchingList.toMutableList())
                testList.addLast(list[startDomIndex + i])
                if (gameCondition(testList, testList.size)) {
                    matchingList.addLast(list[startDomIndex + i])
                } else {
                    rightIsOver = true
                }
            }
        }

        return matchingList

    }

    fun gameCondition(list: List<Dom>, fixedSize: Int): Boolean {
        if (fixedSize < 2) {
            return true
        }
        // Check if dominoes in the fixed list are matching and [Player.ONE] is neighbor of [Player.Start]
        for (i in 0..fixedSize - 2) {
            if (!list[i].match(list[i + 1])) return false
            if (list[i].player == Player.START) {
                if (i == 0) {
                    if (list[1].player != Player.ONE) return false
                } else {
                    if (list[i - 1].player != Player.ONE && list[i + 1].player != Player.ONE) return false
                }
            }
        }
        return isPossiblePlayerOrder(list, fixedSize)
    }

    /**
     * Checks if the fixed size domino list has a possible player order
     */
    fun isPossiblePlayerOrder(list: List<Dom>, fixedSize: Int): Boolean {
        val startDomIndex = list.indexOf(list.first { it.player == Player.START })
        if (startDomIndex > fixedSize - 1) {
            return true
        }
        var diff = 0
        for (i in 1 until fixedSize) {
            if (startDomIndex - i >= 0) {
                if (list[startDomIndex - i].player == Player.ONE) diff++ else diff--
            }
            if (startDomIndex + i <= fixedSize - 1 && startDomIndex + i < list.size) {
                if (list[startDomIndex + i].player == Player.ONE) diff++ else diff--
            }
            val absDiff = if (diff > 0) diff - 1 else abs(diff)
            if (absDiff > startDomIndex) {
                return false
            }
            if (absDiff > list.size - fixedSize) {
                return false
            }
            if (list.size == fixedSize) {
                if (diff < 0) {
                    return false
                }
                if (diff > 1) {
                    return false
                }
            }
        }
        return true
    }

    private fun factorialFunc(input: Int): Long {
        return if (input == 1) input.toLong() else input * factorialFunc(input - 1)
    }

}

data class Dom(var player: Player, var a: Int, var b: Int) {

    constructor(a: Int, b: Int) : this(Player.UNKNOWN, a, b)

    fun match(dom: Dom): Boolean {
        return a == dom.a || a == dom.b
                || b == dom.a || b == dom.b
    }

}

enum class Player {
    UNKNOWN, START, ONE, TWO;
}

enum class ListInit {
    SOOT, SOTO;
}

data class Solutions(
    val solutions: MutableMap<Int, MutableList<List<Dom>>> = mutableMapOf(),
    val startTime: Long = System.currentTimeMillis(),
    var endTime: Long = 0L,
    var factorial: Long = 0L,
    var permIndex: Long = 0L,
    var skippedPerms: Long = 0L,
) {

    fun addIfNotContains(list: List<Dom>): Boolean {
        if (solutions[list.size] == null) {
            solutions[list.size] = mutableListOf()
        }
        val currentList: MutableList<List<Dom>> = solutions[list.size]!!
        if (!currentList.contains(list) && !currentList.contains(list.reversed())) {
            solutions[list.size]?.add(list)
            return true
        }
        return false
    }

    fun printSolutionsSummary() {
        endTime = (System.currentTimeMillis() - startTime)
        println("Total solutions: ${allSolutions()}. Time(ms): $endTime. Factorial: $factorial. Perm index: $permIndex. Skipped perms: $skippedPerms")
    }

    fun diff(solutions: Solutions) {
        this.solutions.values.forEach{
                it.forEach{thisSol ->
            solutions.solutions.values.forEach{
                    if (it.contains(thisSol)) {
                        println(thisSol)
                    }
                }
        }}
    }

    private fun allSolutions(): Int {
        var count = 0
        solutions.values.forEach {
            count += it.count()
        }
        return count
    }

}

