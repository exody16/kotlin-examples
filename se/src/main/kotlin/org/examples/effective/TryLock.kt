package org.examples.effective

class TryLock {

    private val myList: MutableList<Int> = mutableListOf()
    private val LOCK = Any()

    fun loadAll(): List<Int> = synchronized(LOCK) {
        myList.toList()
    }

    fun add(int: Int) = synchronized(LOCK) {
        myList += int
    }

}