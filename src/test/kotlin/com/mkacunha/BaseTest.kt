package com.mkacunha

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import io.restassured.module.webtestclient.RestAssuredWebTestClient
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit.jupiter.SpringExtension

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [Application::class])
abstract class BaseTest {

    @Autowired
    private lateinit var context: ApplicationContext

    private val wireMockServer = WireMockServer(8081)

    @BeforeEach
    fun setUp() {
        RestAssuredWebTestClient.applicationContextSetup(context)
    }

    @BeforeAll
    fun setUpALl() {
        wireMockServer.start()
        configureFor("localhost", 8081)

        stubFor(
            get("/api/value/web-client-response")
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withHeader("Content-Length", "35")
                        .withBody("""{"valueOne": "one","valueTwo": "two"}""")
                )
        )
    }

    @AfterAll
    fun tearDownAll() {
        wireMockServer.stop()
    }
}