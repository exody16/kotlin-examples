package example

import io.github.oshai.kotlinlogging.KLoggingEventBuilder
import io.github.oshai.kotlinlogging.KotlinLogging

class TestLog {

    private val log = KotlinLogging.logger {}

    fun myLog() {
        val exception = RuntimeException("My exception message")
        log.atInfo {
            message = "My message {} end, ex message {}"
            arguments = arrayOf("My arg", exception.message)
            cause = exception
            payload = mapOf("k1" to "v1", "k2" to "v2")
            internalCompilerData = KLoggingEventBuilder.InternalCompilerData(
                className = TestLog::class.java.canonicalName,
                lineNumber = 123,
                fileName = "test.log",
                methodName = "myMethod",
            )
        }

//        val threadNr = 1
//        val liveValues = 123
//        log.info { "${"subscriber {} received {} live values"} $threadNr $liveValues" }
//        log.error(exception) { "There was an error while fetching device type ${toCall()}" }


    }

}

fun main() {
    TestLog().myLog()
}