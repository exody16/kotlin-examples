//data class Dom(val left: Int, val right: Int) {
//    fun flip(): Dom = Dom(right, left)
//}
//
//fun allOutcomes(solution: List<Dom>, playerOne: List<Dom>, playerTwo: List<Dom>): List<List<Dom>> {
//    val allSolutions = mutableListOf<List<Dom>>()
//    val used = mutableSetOf<Dom>()
//
//    fun dfs(currentPlayer: String, currentSolution: List<Dom>, remainingOne: List<Dom>, remainingTwo: List<Dom>) {
//        if (remainingOne.isEmpty() && remainingTwo.isEmpty()) {
//            allSolutions.add(currentSolution)
//            return
//        }
//
//        val (dominos, otherPlayerDominos, otherPlayer) = if (currentPlayer == "ONE") {
//            playerOne to playerTwo to "TWO"
//        } else {
//            playerTwo to playerOne to "ONE"
//        }
//
//        for (domino in dominos) {
//            val flippedDomino = domino.flip()
//            if (domino !in used && flippedDomino !in used) {
//                val nextSolution: List<Dom>
//                if (currentSolution.isEmpty()) {
//                    nextSolution = listOf(domino)
//                } else {
//                    val left = currentSolution.first().left
//                    val right = currentSolution.last().right
//                    nextSolution = if (domino.left == left) {
//                        currentSolution + domino
//                    } else if (domino.right == right) {
//                        listOf(domino) + currentSolution
//                    } else {
//                        continue
//                    }
//                }
//                used.add(domino)
//                dfs(otherPlayer, nextSolution, remainingOne.filterNot { it == domino }, remainingTwo.filterNot { it == domino })
//                used.remove(domino)
//            }
//        }
//    }
//
//    dfs("ONE", solution.toList(), playerOne, playerTwo)
//    return allSolutions
//}
//
//fun main() {
//    val solution = listOf(Dom(0, 0))
//    val playerOne = listOf(Dom(0, 1), Dom(0, 2), Dom(1, 1))
//    val playerTwo = listOf(Dom(1, 2), Dom(2, 2))
//
//    val outcomes = allOutcomes(solution, playerOne, playerTwo)
//    outcomes.forEach { outcome ->
//        println("SOLUTION: $outcome - Player ONE Wins")
//    }
//}
