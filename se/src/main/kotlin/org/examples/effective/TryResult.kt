package org.examples.effective


fun main() {
    val tryresult1 = tryresult(1)
    println(tryresult1)

    tryresult1.onFailure { println("Failure") }
        .onSuccess { println("Success") }

    println(tryresult(2))

    val convertertToResult = runCatching { thisThrowsException() }

}

fun tryresult(i: Int): Result<Int> {
    return if (i == 1) Result.success(1) else Result.failure(RuntimeException("my error"))
}

fun thisThrowsException(): Int {
    throw IllegalArgumentException("")
}