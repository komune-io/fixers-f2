package f2.spring.exception.config

import f2.dsl.cqrs.exception.F2Exception
import f2.spring.exception.ConflictException
import f2.spring.exception.ForbiddenAccessException
import f2.spring.exception.NotFoundException
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Bean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@SpringBootApplication
class TestApp {

	@Bean
	fun routes(): RouterFunction<ServerResponse> = router {
		GET("/not-found") {
			throw NotFoundException("User", "123")
		}
		GET("/forbidden") {
			throw ForbiddenAccessException("Access denied to this resource")
		}
		GET("/conflict") {
			throw ConflictException("User", "email", "test@example.com")
		}
		GET("/f2-exception") {
			throw F2Exception(message = "Something broke", code = 500)
		}
		GET("/generic-error") {
			throw IllegalStateException("unexpected")
		}
	}
}

@SpringBootTest(
	classes = [TestApp::class],
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class F2ErrorWebExceptionHandlerTest {

	@LocalServerPort
	private var port: Int = 0

	private val webTestClient: WebTestClient by lazy {
		WebTestClient.bindToServer().baseUrl("http://localhost:$port").build()
	}

	@Test
	fun `should return 404 for NotFoundException`() {
		webTestClient.get().uri("/not-found")
			.exchange()
			.expectStatus().isNotFound
			.expectBody()
			.jsonPath("$.code").isEqualTo(404)
			.jsonPath("$.message").isEqualTo("User [123] not found")
	}

	@Test
	fun `should return 403 for ForbiddenAccessException`() {
		webTestClient.get().uri("/forbidden")
			.exchange()
			.expectStatus().isForbidden
			.expectBody()
			.jsonPath("$.code").isEqualTo(403)
			.jsonPath("$.message").isEqualTo("Access denied to this resource")
	}

	@Test
	fun `should return 409 for ConflictException`() {
		webTestClient.get().uri("/conflict")
			.exchange()
			.expectStatus().isEqualTo(409)
			.expectBody()
			.jsonPath("$.code").isEqualTo(409)
			.jsonPath("$.message").isEqualTo("User with email [test@example.com] already exists")
	}

	@Test
	fun `should return 500 for F2Exception`() {
		webTestClient.get().uri("/f2-exception")
			.exchange()
			.expectStatus().is5xxServerError
			.expectBody()
			.jsonPath("$.code").isEqualTo(500)
			.jsonPath("$.message").isEqualTo("Something broke")
	}

	@Test
	fun `should return 500 for generic RuntimeException`() {
		webTestClient.get().uri("/generic-error")
			.exchange()
			.expectStatus().is5xxServerError
	}

	@Test
	fun `should include id and timestamp in error response`() {
		webTestClient.get().uri("/not-found")
			.exchange()
			.expectBody()
			.jsonPath("$.id").isNotEmpty
			.jsonPath("$.timestamp").isNotEmpty
	}
}
