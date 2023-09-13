package org.examples

import org.springframework.messaging.Message
import org.springframework.messaging.MessageHandler
import org.springframework.stereotype.Component

@Component
class MqttMessageHandler: MessageHandler {

    override fun handleMessage(message: Message<*>) {
        println("New message: $message")
        try {
            if (message.payload.toString().contains("exception")) {
                throw RuntimeException("Auch!!!!! Exception")
            }
        } catch (e: Exception) {
            println("Catched exception, continue..")
        }

    }

}
