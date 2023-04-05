package com.mkacunha.controller

import com.mkacunha.domain.Domain
import com.mkacunha.service.DemoService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@RestController
class DemoController(
    private val demoService: DemoService,
    private val webClient: WebClient
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/api/value")
    fun get(): Mono<Response> =
        demoService
            .getDomain()
            .map { Response(it) }
            .doOnSuccess { logger.info("path=/api/value") }

    @GetMapping("/api/value/web-client")
    fun getWebClient(): Mono<Response> =
        webClient
            .get()
            .uri("http://localhost:8081/api/value/web-client-response")
            .retrieve()
            .bodyToMono(Response::class.java)
            .doOnSuccess { logger.info("path=/api/value/web-client") }


    @GetMapping("/api/value/web-client-response")
    fun getWebClientResponse(): Mono<Response> =
        demoService
            .getDomain()
            .map { Response(it) }
            .doOnSuccess { logger.info("path=/api/value/web-client-response") }

    class Response(
        val valueOne: String,
        val valueTwo: String
    ) {
        constructor(domain: Domain) : this(domain.valueOne, domain.valueTwo)
    }
}