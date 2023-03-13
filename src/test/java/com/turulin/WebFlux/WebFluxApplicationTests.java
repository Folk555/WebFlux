package com.turulin.WebFlux;

import com.turulin.WebFlux.Models.MyMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;

@WebFluxTest() //Аннотация грузит только то что относится к WebFlux зависимостям
@AutoConfigureWebTestClient(timeout = "11000")
class WebFluxApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getMonoHello() {
        MyMessage expectedMessage = new MyMessage("hiBro", "noob");
        MyMessage actualMessage = webTestClient.get()
                .uri("/hello")
                .exchange()
                .returnResult(MyMessage.class)
                .getResponseBody()
                .blockFirst();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getFluxHello() {
        List<MyMessage> actualMessageList;
        actualMessageList = webTestClient.get()
                .uri("/helloFlux")
                .exchange()
                .returnResult(MyMessage.class)
                .getResponseBody()
                .collectList()
                .block();
        for (int i = 0; i < actualMessageList.size(); ++i) {
            Assertions.assertEquals(new MyMessage("string" + (i + 1), "noob"), actualMessageList.get(i));
        }
    }


}
