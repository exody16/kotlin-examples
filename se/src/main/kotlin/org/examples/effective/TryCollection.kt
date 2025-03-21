package org.examples.effective


fun <T> Collection<T>.chunkedExactly(size: Int): List<List<T>> {
    return this
        .take(this.size - (this.size % size))
        .chunked(size)
}

fun main() {


    // chunk
    val intRange: IntRange = 1..5
    println(intRange.toList()
        .chunkedExactly(2)
    )

    println(intRange.toList()
        .windowed(2, 2, false)
    )
}