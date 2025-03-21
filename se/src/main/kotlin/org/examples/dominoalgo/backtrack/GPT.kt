//data class Domino(val left: Int, val right: Int)
//
//fun main() {
//    val startingSolution = mutableListOf(Domino(0, 0))
//    val oneDominos = listOf(Domino(0, 1), Domino(0, 2), Domino(1, 1))
//    val twoDominos = listOf(Domino(1, 2), Domino(2, 2))
//
//    findOutcomes(startingSolution, oneDominos, twoDominos, true)
//}
//
//fun findOutcomes(
//    solution: MutableList<Domino>,
//    oneDominos: List<Domino>,
//    twoDominos: List<Domino>,
//    isOneTurn: Boolean
//) {
//    if (oneDominos.isEmpty() && twoDominos.isEmpty()) {
//        println("Outcome: $solution")
//        return
//    }
//
//    val playerDominos = if (isOneTurn) oneDominos else twoDominos
//
//    // Check if the current player has no valid moves
//    if (playerDominos.none { isMoveValid(solution, it) }) {
//        println("Player ${if (isOneTurn) "TWO" else "ONE"} loses")
//        return
//    }
//
//    for (domino in playerDominos) {
//        val newSolution = if (isOneTurn) solution.toMutableList() + domino else mutableListOf(domino) + solution
//
//        val remainingDominos = if (isOneTurn) oneDominos - domino else twoDominos - domino
//
//        if (isMoveValid(newSolution)) {
//            findOutcomes(newSolution, oneDominos, twoDominos, !isOneTurn)
//        }
//    }
//}
//
//fun isMoveValid(solution: List<Domino>, domino: Domino): Boolean {
//    if (solution.isEmpty()) return true
//    val firstDomino = solution.first()
//    val lastDomino = solution.last()
//    return (firstDomino.left == domino.right || firstDomino.left == domino.left || lastDomino.right == domino.left || lastDomino.right == domino.right)
//}
