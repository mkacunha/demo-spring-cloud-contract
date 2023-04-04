package com.mkacunha.controller

import com.mkacunha.domain.Domain
import com.mkacunha.service.DemoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class DemoController(
    private val demoService: DemoService
) {

    @GetMapping("/api/value")
    fun get(): Mono<Response> =
        demoService
            .getDomain()
            .map { Response(it) }

    class Response(
        val valueOne: String,
        val valueTwo: String
    ) {
        constructor(domain: Domain) : this(domain.valueOne, domain.valueTwo)
    }
}