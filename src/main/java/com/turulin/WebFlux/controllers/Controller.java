package com.turulin.WebFlux.controllers;

import com.turulin.WebFlux.Models.MyMessage;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class Controller {
    @GetMapping("/hello")
    @ResponseBody
    public Mono<MyMessage> sayHello(@RequestParam(defaultValue = "noob") String name) throws InterruptedException {
        Thread.sleep(2000);
        return Mono.just(new MyMessage("hiBro", name));
    }

    @GetMapping("/helloFlux")
    @ResponseBody
    public Flux<MyMessage> sayHelloFlux(@RequestParam(defaultValue = "noob") String name) {
        return Flux.just(
                "string1",
                "string2",
                "string3",
                "string4",
                "string5"
        ).map(str -> new MyMessage(str, name)).delayElements(Duration.ofSeconds(2));
    }
}
