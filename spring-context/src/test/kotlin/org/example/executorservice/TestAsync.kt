package org.example.executorservice

import org.example.DummConfig
import org.example.async.HasAsync
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.task.TaskExecutor
import java.util.concurrent.Executors

@SpringBootTest(
    classes = [DummConfig::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class TestAsync {

    @Autowired
    lateinit var hasAsync: HasAsync

    @Autowired
    lateinit var myTaskExecutor: TaskExecutor

    @Test
    fun testAsync() {
        println("Test started! ${Thread.currentThread()}")
        for (i in 1..10) {
            hasAsync.runAsync(i)
        }
    }

    @Test
    fun testSingleThread() {
        println("Test started! ${Thread.currentThread()}")
        val newSingleThreadExecutor = Executors.newSingleThreadExecutor()
        val futureList = (1..3).toList()
            .map {
                val runOnSingleThread = hasAsync.runOnSingleThread(newSingleThreadExecutor, it)
//                runOnSingleThread.get()
            }
        println("Test ended! ${Thread.currentThread()}")
    }

}

