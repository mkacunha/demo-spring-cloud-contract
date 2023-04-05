package contracts

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    description = "should return demos"
    request {
        url = url("/api/value/web-client")
        method = GET
    }
    response {
        status = OK

        headers {
            header("Content-Type", "application/json")
        }

        body = body(
            "valueOne" to "one",
            "valueTwo" to "two"
        )
    }
}