package example;

import example.testwebclientexception.IOWebClientConfigKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.util.Map;

public class TestWebclient {


    Logger log = LoggerFactory.getLogger(TestWebclient.class);

    public static void main(String[] args) {
test3();
    }

    public static int ret() {
        System.out.println("CALLED!");
        return 1;
    }

    public static void test3() {
        ResponseEntity<Void> block = IOWebClientConfigKt.ioWebClient()
                .post()
                .bodyValue(Map.of("serialNumber", "inhouse-beetle"))
//                .body(BodyInserters.fromValue("1"))
                .retrieve()
//               .bodyToMono(Void.class)
//               .onErrorResume(e -> {
//                   System.out.println(e);
//                   return Mono.empty();
//               })
//               .block();
//                .onStatus(HttpStatusCode::isError, response -> Mono.empty())
                .toBodilessEntity()
                .onErrorComplete()
                .block();

        System.out.println(block.getStatusCode().value());
        System.out.println(("END!!!!!!!!!!"));




    }

    public static void test() {
       IOWebClientConfigKt.ioWebClient()
               .post()
//                .bodyValue(Map.of("serialNumber", "inhouse-beetle"))
               .body(BodyInserters.fromValue("1"))
               .retrieve()
//               .bodyToMono(Void.class)
//               .onErrorResume(e -> {
//                   System.out.println(e);
//                   return Mono.empty();
//               })
//               .block();
               .onStatus(HttpStatusCode::isError, response -> {
                   // Handle error status codes here
                   int statusCode = response.statusCode().value();
                   System.out.println(statusCode);
                   // ...
                   return Mono.empty();
               })
               .bodyToMono(String.class)
               .subscribe(System.out::println);

        System.out.println(("END!!!!!!!!!!"));


    }

//    static void test2() {
//        String response = IOWebClientConfigKt.ioWebClient()
//                .get()
//                .uri("/example")
//                .exchangeToMono(response -> {
//
//                    // Handle error status codes here
//                    int statusCode = response.statusCode().value();
//                    System.out.println(statusCode);
//                    // ...
//                    return response.bodyToMono(String.class);
//                })
////                .bodyToMono(String.class)
//                .block();
//
//    }


}
