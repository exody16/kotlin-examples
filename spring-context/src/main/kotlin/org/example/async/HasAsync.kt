package org.example.async

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

@Component
class HasAsync(
    private val mySingleThreadExecutor: ExecutorService
) {


    @Async
    fun runAsync(i: Int) {
        println("This is async nr. $i! ${Thread.currentThread()}")
        Thread.sleep(3000)
        println("Async $i ended")
    }

//    fun runOnSingleThread(i: Int): Future<*> {
    fun runOnSingleThread(i: Int) {
//        val submit: Future<*> = mySingleThreadExecutor.submit {
        mySingleThreadExecutor.execute {
//        mySingleThreadExecutor.execute {
            println("This is async nr. $i at ${LocalDateTime.now()}! ${Thread.currentThread()}")
            TimeUnit.SECONDS.sleep(10)
            println("Async $i ended at ${LocalDateTime.now()}")
        }
//        return submit
    }

    fun runOnSingleThread(newSingleThreadExecutor: ExecutorService, i: Int): Future<*> {
        val submit: Future<*> = newSingleThreadExecutor.submit {
//        mySingleThreadExecutor.execute {
            println("This is async nr. $i at ${LocalDateTime.now()}! ${Thread.currentThread()}")
            TimeUnit.SECONDS.sleep(2)
            println("Async $i ended at ${LocalDateTime.now()}")
        }
        return submit
    }


}