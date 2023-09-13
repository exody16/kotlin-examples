package org.examples.dominoalgo

fun main() {
    val domino = Domino(2, false)
    domino.permDomino(listInit = ListInit.SOTO)

    val dominoWithCondition = Domino(2, true)
    dominoWithCondition.permDomino(listInit = ListInit.SOTO)

    // TODO why are different results?
    dominoWithCondition.solutions.diff(domino.solutions)


}