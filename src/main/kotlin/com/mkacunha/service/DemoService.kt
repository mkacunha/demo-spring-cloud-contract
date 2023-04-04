package com.mkacunha.service

import com.mkacunha.domain.Domain
import org.springframework.stereotype.Service
import reactor.kotlin.core.publisher.toMono

@Service
class DemoService {

    fun getDomain() = Domain(valueOne = "one", valueTwo = "two").toMono()
}