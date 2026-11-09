package ownattributes

import f2.spring.exception.NotFoundException
import f2.spring.exception.config.F2ErrorWebExceptionHandler
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.webflux.error.DefaultErrorAttributes
import org.springframework.boot.webflux.error.ErrorAttributes
import org.springframework.boot.webflux.error.ErrorWebExceptionHandler
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

/** An application that declares its own error attributes. */
@SpringBootApplication
class OwnAttributesApp {
    @Bean
    fun ownErrorAttributes(): ErrorAttributes = object : DefaultErrorAttributes() {
        override fun getErrorAttributes(request: ServerRequest, options: ErrorAttributeOptions) =
            super.getErrorAttributes(request, options).apply { put("own", "yes") }
    }

    @Bean
    fun ownRoutes(): RouterFunction<ServerResponse> = router {
        GET("/missing") { throw NotFoundException("Item", "i-1") }
    }
}

@SpringBootTest(classes = [OwnAttributesApp::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class F2ExceptionWebFluxOwnAttributesTest(
    @LocalServerPort private val port: Int,
    @Autowired private val context: ApplicationContext,
) {

    @Test
    fun `the F2 handler answers with the application's own error attributes, at the status they name`() {
        WebTestClient.bindToServer().baseUrl("http://localhost:$port").build()
            .get().uri("/missing")
            .exchange()
            .expectStatus().isNotFound
            .expectBody()
            .jsonPath("$.own").isEqualTo("yes")
        assertThat(context.getBeansOfType(ErrorWebExceptionHandler::class.java).values)
            .hasExactlyElementsOfTypes(F2ErrorWebExceptionHandler::class.java)
    }
}
