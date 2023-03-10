package com.turulin.WebFlux;

import com.turulin.WebFlux.Models.MyMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class WebFluxApplicationTests {

	WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
	@Autowired
	WebTestClient webTestClient;

	@Test
	void webFluxTimeExecutionTest() throws Exception {
		MyMessage myMessage = new MyMessage("hiBro", "noob");
		MyMessage myMessageArray[] = new MyMessage[10];
		long start = System.currentTimeMillis();
		System.out.println(webClient.get().uri("/hello").retrieve()
				.bodyToMono(MyMessage.class).block());
		System.out.println(webTestClient.get().uri("/helloFlux").exchange().toString());
		long finish = System.currentTimeMillis();
		long elapsed = finish - start;
		System.out.println("Прошло времени, мс: " + elapsed / 1000);
		for (int i = 0; i < 25; i++) {
			webTestClient.get().uri("/hello").exchange();
			//.exchangeToMono(clientResponse -> clientResponse.bodyToMono(MyMessage.class))
			//.block();

			finish = System.currentTimeMillis();
			elapsed = finish - start;
			System.out.println("Прошло времени, мс: " + elapsed / 1000);
		}
	}

}
