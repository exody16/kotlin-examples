package org.examples

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MQTTServiceApplication

fun main(args: Array<String>) {
    runApplication<MQTTServiceApplication>(*args)
}
