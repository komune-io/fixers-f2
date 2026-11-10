package consumer

import f2.spring.exception.NotFoundException
import f2.spring.exception.config.F2ErrorAttributes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.webflux.error.ErrorAttributes
import org.springframework.boot.webflux.autoconfigure.error.DefaultErrorWebExceptionHandler
import org.springframework.boot.webflux.error.ErrorWebExceptionHandler
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

/** An application that neither scans `f2` nor imports anything from it: the error body is F2's all the same. */
@SpringBootApplication
class ConsumerApp {
    @Bean
    fun consumerRoutes(): RouterFunction<ServerResponse> = router {
        GET("/missing") { throw NotFoundException(name = "Item", id = "jdbc:postgresql://db-internal:5432/app") }
    }
}

@SpringBootTest(classes = [ConsumerApp::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class F2ExceptionWebFluxAutoConfigurationTest(
    @LocalServerPort private val port: Int,
    @Autowired private val context: ApplicationContext,
) {

    @Test
    fun `an application without any import gets Spring Boot's error handler and the F2 attributes`() {
        assertThat(context.getBeansOfType(ErrorWebExceptionHandler::class.java).values)
            .hasExactlyElementsOfTypes(DefaultErrorWebExceptionHandler::class.java)
        assertThat(context.getBean(ErrorAttributes::class.java)).isInstanceOf(F2ErrorAttributes::class.java)
    }

    @Test
    fun `its errors answer with the refusal code and nothing they were raised with`() {
        // jdbc:… is the caller's id here, a refusal's own value: it is told back, and nothing else is.
        WebTestClient.bindToServer().baseUrl("http://localhost:$port").build()
            .get()
            .uri("/missing")
            .exchange()
            .expectStatus()
            .isNotFound
            .expectBody()
            .jsonPath("$.refusal")
            .isEqualTo("http.NOT_FOUND")
            .jsonPath("$.exception")
            .doesNotExist()
            .jsonPath("$.trace")
            .doesNotExist()
    }
}
