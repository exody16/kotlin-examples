package org.example.async

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalTime

@RestController
@RequestMapping("/async")
class AsyncController(
    private val hasAsync: HasAsync,
) {

    @PostMapping
    fun runAsync() {
        println("Start at ${LocalTime.now()} on ${Thread.currentThread()}")
        hasAsync.runOnSingleThread(1)
//            .get()
        println("End at ${LocalTime.now()} on ${Thread.currentThread()}")

    }

}