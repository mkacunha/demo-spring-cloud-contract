package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return demos"

    request {
        url "/api/value"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
                valueOne: "one",
                valueTwo: "two"
        )
    }
}
