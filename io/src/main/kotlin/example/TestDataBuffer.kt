package example

import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.core.io.buffer.DefaultDataBuffer
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.io.File
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousFileChannel
import java.nio.charset.StandardCharsets
import java.nio.file.FileSystems
import java.nio.file.StandardOpenOption


fun main() {
test_chatgpt()

}


val fileName = "myfile"

fun test_chatgpt() {
    val client = WebClient.create("https://example.com")
    val dataBufferFlux = client.get()
        .accept(MediaType.TEXT_HTML)
        .retrieve()
        .bodyToFlux(DataBuffer::class.java)

    val path = FileSystems.getDefault().getPath("example.html")
    DataBufferUtils.write(dataBufferFlux, path, StandardOpenOption.CREATE_NEW).block()

}


fun test() {
    val factory = DefaultDataBufferFactory()
    val randomString = randomStringByKotlinRandom(10000000)
    val dataBuffer: DefaultDataBuffer = factory.wrap(ByteBuffer.wrap(randomString.toByteArray(StandardCharsets.UTF_8)))
    val body: Mono<DataBuffer> = Mono.just(dataBuffer)

    val file = File(fileName)
    file.createNewFile()
    val channel: AsynchronousFileChannel = AsynchronousFileChannel.open(file.toPath(), StandardOpenOption.WRITE)

    DataBufferUtils.write(body, channel)
        .doOnNext(DataBufferUtils.releaseConsumer())
        .blockLast()

}


fun randomStringByKotlinRandom(lenght: Int): String {
    return "A".repeat(lenght)

}
