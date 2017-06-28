package pacts

import au.com.dius.pact.consumer.PactVerificationResult
import au.com.dius.pact.consumer.groovy.PactBuilder
import groovyx.net.http.RESTClient
import org.junit.Test

import java.time.format.DateTimeParseException

import static java.time.LocalDateTime.now
import static java.time.LocalDateTime.parse
import static java.time.format.DateTimeFormatter.ofPattern

class StatusEndpointPact {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS"

    @Test
    void "pact for /status"() {
        def statusEndpointPact = new PactBuilder()

        statusEndpointPact {
            serviceConsumer "StatusCLI" 	        // Define the service consumer by name
            hasPactWith "StatusEndpoint"            // Define the service provider that the consumer has a pact with
            port 1234                               // The port number for the service. It is optional, leave it out to use a random one

            given('status endpoint is up')
            uponReceiving('a status enquiry')
            withAttributes(method: 'get', path: '/status')
            willRespondWith(status: 200, headers: ['Content-Type': 'application/json'])
            withBody {
                status "OK"
                currentDateTime timestamp(DATE_TIME_PATTERN, now().toString())
            }
        }

        // Execute the run method to have the mock server run.
        // It takes a closure to execute your requests and returns a PactVerificationResult.
        PactVerificationResult result = statusEndpointPact.runTest {
            def client = new RESTClient('http://localhost:1234/')
            def response = client.get(path: '/status')

            assert response.status == 200
            assert response.contentType == 'application/json'
            assert response.data.status == 'OK'
            assert dateTimeMatchesExpectedPattern(response.data.currentDateTime)
        }

        assert result == PactVerificationResult.Ok.INSTANCE  // This means it is all good
    }

    private boolean dateTimeMatchesExpectedPattern(String currentDateTime) {
        try {
            parse(currentDateTime, ofPattern(DATE_TIME_PATTERN))
        } catch (DateTimeParseException e) {
            return false
        }

        return true
    }
}
