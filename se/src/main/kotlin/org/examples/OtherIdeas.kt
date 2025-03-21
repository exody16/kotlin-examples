package org.examples

fun main() {


    val result: Result<Int> = try {
//        throw RuntimeException("my error")
        Result.success(1)
    } catch (e: Exception) {
        Result.failure(e)
    }

    result
        .onSuccess { value -> println("Success: $value") }
        .onFailure { exception -> println("Exception: $exception") }

}