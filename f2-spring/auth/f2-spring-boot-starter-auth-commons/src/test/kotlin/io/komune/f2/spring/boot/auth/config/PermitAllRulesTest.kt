package io.komune.f2.spring.boot.auth.config

import jakarta.annotation.security.PermitAll
import java.util.function.Function
import java.util.function.Supplier
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint
import org.springframework.test.web.reactive.server.WebTestClient

/**
 * Drives [permitAllFunctionBeans] through the f2 function web stack, whose spring-cloud-function
 * controller maps GET, POST, PUT and DELETE on every function path.
 */
@SpringBootTest(
    classes = [PermitAllRulesTest.App::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class PermitAllRulesTest(
    @LocalServerPort private val port: Int,
) {

    @SpringBootApplication
    class App {

        @Bean("openSupplier")
        @PermitAll
        fun openSupplier(): Supplier<String> = Supplier { "open" }

        @Bean("openFunction")
        @PermitAll
        fun openFunction(): Function<String, String> = Function { value -> value.uppercase() }

        @Bean("securedFunction")
        fun securedFunction(): Function<String, String> = Function { value -> value.uppercase() }

        @Bean
        fun securityWebFilterChain(
            http: ServerHttpSecurity,
            applicationContext: ApplicationContext,
        ): SecurityWebFilterChain {
            http.permitAllFunctionBeans(applicationContext, "")
            http.authorizeExchange { exchange -> exchange.anyExchange().authenticated() }
            http.exceptionHandling { handling ->
                handling.authenticationEntryPoint(HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED))
            }
            http.csrf { csrf -> csrf.disable() }
            return http.build()
        }
    }

    private val webTestClient: WebTestClient by lazy {
        WebTestClient.bindToServer().baseUrl("http://localhost:$port").build()
    }

    private fun call(method: HttpMethod, path: String) = webTestClient.method(method)
        .uri(path)
        .apply { if (method != HttpMethod.GET && method != HttpMethod.HEAD) bodyValue("a") }
        .exchange()

    @Test
    fun `a PermitAll supplier answers GET without a token`() {
        call(HttpMethod.GET, "/openSupplier").expectStatus().isOk
            .expectBody(String::class.java).isEqualTo("open")
    }

    @Test
    fun `a PermitAll function answers POST without a token`() {
        call(HttpMethod.POST, "/openFunction").expectStatus().isOk
            .expectBody(String::class.java).isEqualTo("A")
    }

    @Test
    fun `a PermitAll function refuses PUT and DELETE without a token`() {
        listOf(HttpMethod.PUT, HttpMethod.DELETE).forEach { method ->
            call(method, "/openFunction").expectStatus().isUnauthorized
        }
    }

    @Test
    fun `a PermitAll path refuses the other verbs without a token`() {
        listOf(HttpMethod.HEAD, HttpMethod.PATCH, HttpMethod.OPTIONS).forEach { method ->
            call(method, "/openSupplier").expectStatus().isUnauthorized
            call(method, "/openFunction").expectStatus().isUnauthorized
        }
    }

    @Test
    fun `a function without PermitAll refuses POST without a token`() {
        call(HttpMethod.POST, "/securedFunction").expectStatus().isUnauthorized
    }
}
