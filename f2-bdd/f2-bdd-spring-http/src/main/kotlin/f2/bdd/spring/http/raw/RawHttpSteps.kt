package f2.bdd.spring.http.raw

import f2.bdd.spring.autoconfigure.steps.F2SpringContextBag
import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import org.assertj.core.api.Assertions.assertThat

class RawHttpSteps : F2SpringStep(), En {

    private companion object {
        const val TIMEOUT_SECONDS = 20L
    }

    private val client: HttpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(TIMEOUT_SECONDS))
        .build()

    private var response: HttpResponse<String>? = null

    init {
        // Only populate the bag. Context teardown is already handled by the other step classes.
        Before { scenario: Scenario ->
            bag = F2SpringContextBag.init(scenario)
        }

        When("Raw: I send {string} to {string}") { method: String, path: String ->
            response = send(method, path, null, null)
        }

        When(
            "Raw: I send {string} to {string} with body {string} and content type {string}"
        ) { method: String, path: String, body: String, contentType: String ->
            response = send(method, path, body, contentType)
        }

        Then("Raw: The response status is {int}") { expected: Int ->
            assertThat(current().statusCode()).isEqualTo(expected)
        }

        Then("Raw: The response body contains {string}") { expected: String ->
            assertThat(current().body()).contains(expected)
        }

        Then("Raw: The response body does not contain {string}") { unexpected: String ->
            assertThat(current().body()).doesNotContain(unexpected)
        }
    }

    private fun current(): HttpResponse<String> =
        requireNotNull(response) { "No raw HTTP request has been sent yet" }

    private fun send(
        method: String,
        path: String,
        body: String?,
        contentType: String?
    ): HttpResponse<String> {
        val builder = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:${bag.httpPort}$path"))
            .timeout(Duration.ofSeconds(TIMEOUT_SECONDS))
        val publisher = if (body == null) {
            HttpRequest.BodyPublishers.noBody()
        } else {
            HttpRequest.BodyPublishers.ofString(body)
        }
        builder.method(method, publisher)
        if (contentType != null) {
            builder.header("Content-Type", contentType)
        }
        return client.send(builder.build(), HttpResponse.BodyHandlers.ofString())
    }
}
