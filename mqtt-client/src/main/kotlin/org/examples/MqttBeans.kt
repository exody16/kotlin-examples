package org.examples

import org.eclipse.paho.client.mqttv3.MqttAsyncClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.core.MessageProducer
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory
import org.springframework.integration.mqtt.core.MqttPahoClientFactory
import org.springframework.integration.mqtt.event.MqttConnectionFailedEvent
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler

@Configuration
class MqttBeans(
    private val mqttMessageHandler: MqttMessageHandler
) {
    @Value("\${avelon.mqtt.internal-mosquitto-uri:tcp://localhost:1883}")
    private lateinit var mosquittoBroker: String

    @Bean
    fun mqttClientFactory(): MqttPahoClientFactory {

        val factory = DefaultMqttPahoClientFactory()
        val options = MqttConnectOptions()
        options.serverURIs = arrayOf(mosquittoBroker)
        options.isCleanSession = true
        factory.connectionOptions = options
        return factory
    }

    @Bean
    fun mqttInputChannel(): MessageChannel {
        return DirectChannel()
    }

    /**
     * Returns an instance of ${@link MqttPahoMessageDrivenChannelAdapter}, which subscribes to all topics ('#').
     */
    @Bean
    fun inbound(): MessageProducer {
        val adapter = MqttPahoMessageDrivenChannelAdapter(
            MqttAsyncClient.generateClientId(),
            mqttClientFactory(), "#"
        )
        adapter.setConverter(DefaultPahoMessageConverter())
        adapter.setQos(2)
        adapter.outputChannel = mqttInputChannel()
        return adapter
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    fun handler(): MessageHandler {
        return mqttMessageHandler
    }

    @Bean
    fun eventListener(): ApplicationListener<*> {
        return ApplicationListener { event: MqttConnectionFailedEvent -> println("ERROR!!!!: " + event.cause.stackTraceToString()) }
    }
}
