package example.testwebclientexception

import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient



    fun ioWebClient(): WebClient {
        return WebClient.builder()
//            .baseUrl("www.example.com")
            .baseUrl("http://feature13.avelon.team:8085/lapi/v1/no-auth/beetles/sync")
            .clientConnector(ReactorClientHttpConnector(HttpClient.create().wiretap(true)))
            .filter(handleError())
            .build()
    }

    private fun handleError(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofResponseProcessor { response: ClientResponse ->
//            if (response.statusCode().is4xxClientError || response.statusCode().is5xxServerError) {
//                return@ofResponseProcessor response.bodyToMono(ErrorDetail::class.java)
//                    .doOnNext { body: ErrorDetail -> logError(body) }
//                    .defaultIfEmpty(ErrorDetail(500, null,"INTERNAL ERROR"))
//                    .flatMap { body: ErrorDetail? -> Mono.error<ClientResponse?>(RuntimeException(body.toString())) }
//            }
            println("!!!!!!!!!!!!!HANDLE ${response.statusCode()}")
            Mono.just(response)
        }
    }

private fun logError(body: ErrorDetail) {
    println("Error status code ${body.status}. (${body.code}): ${body.message}")
}
