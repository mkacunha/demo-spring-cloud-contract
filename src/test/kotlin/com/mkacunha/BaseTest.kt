package com.mkacunha

import io.restassured.module.webtestclient.RestAssuredWebTestClient
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [DemoSpringCloudContractApplication::class])
abstract class BaseTest {

    @Autowired
    private lateinit var context: ApplicationContext

    @BeforeEach
    fun setUp() {
        RestAssuredWebTestClient.applicationContextSetup(context)
    }
}