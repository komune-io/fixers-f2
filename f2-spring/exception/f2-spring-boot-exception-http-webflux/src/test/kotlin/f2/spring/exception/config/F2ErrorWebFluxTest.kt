package f2.spring.exception.config

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.F2Refusal
import f2.dsl.cqrs.exception.F2Exception
import f2.spring.exception.ConflictException
import f2.spring.exception.F2HttpException
import f2.spring.exception.ForbiddenAccessException
import f2.spring.exception.NotFoundException
import f2.spring.exception.statusError
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.WebFilter
import tools.jackson.databind.json.JsonMapper

data class RequiredNameBody(val name: String)

@SpringBootApplication
class TestApp {

	@Bean
	fun refusingFilter(jsonMapper: JsonMapper) = WebFilter { exchange, chain ->
		if (exchange.request.path.value() == "/filtered") {
			exchange.writeRefusal(statusError(HttpStatus.UNAUTHORIZED), jsonMapper)
		} else {
			chain.filter(exchange)
		}
	}

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
			throw F2Exception(F2Error(message = "Something broke", code = 500))
		}
		GET("/refusal") {
			throw F2HttpException(
				status = HttpStatus.CONFLICT, message = "The item is cited: it stays",
				refusal = F2Refusal("item.CITED", parameters = mapOf("id" to "i-1"), citers = listOf("A")),
			)
		}
		GET("/wrapped-refusal") {
			throw IllegalStateException("outer", IllegalStateException("inner", NotFoundException("User", "9")))
		}
		GET("/generic-error") {
			throw IllegalStateException("unexpected")
		}
		GET("/raw-status-exception") {
			throw ResponseStatusException(HttpStatus.BAD_REQUEST, "raw bad request")
		}
		GET("/raw-status-exception-404") {
			throw ResponseStatusException(HttpStatus.NOT_FOUND, "raw not found")
		}
		POST("/kotlin-null-field") { request ->
			request.bodyToMono(RequiredNameBody::class.java).flatMap { ServerResponse.ok().build() }
		}
	}
}

@SpringBootTest(
	classes = [TestApp::class],
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class F2ErrorWebFluxTest(
	@LocalServerPort private val port: Int,
) {

	private val webTestClient: WebTestClient by lazy {
		WebTestClient.bindToServer().baseUrl("http://localhost:$port").build()
	}

	@Test
	fun `should answer NotFoundException as the refusal naming the entity and its id`() {
		webTestClient.get().uri("/not-found")
			.exchange()
			.expectStatus().isNotFound
			.expectBody()
			.jsonPath("$.status").isEqualTo(404)
			.jsonPath("$.refusal").isEqualTo("http.NOT_FOUND")
			.jsonPath("$.message").isEqualTo("User [123] not found")
			.jsonPath("$.parameters.entity").isEqualTo("User")
			.jsonPath("$.parameters.id").isEqualTo("123")
	}

	@Test
	fun `should answer ForbiddenAccessException as a refusal telling its sentence`() {
		webTestClient.get().uri("/forbidden")
			.exchange()
			.expectStatus().isForbidden
			.expectBody()
			.jsonPath("$.refusal").isEqualTo("http.FORBIDDEN")
			.jsonPath("$.message").isEqualTo("Access denied to this resource")
	}

	@Test
	fun `should answer ConflictException as the refusal naming its values`() {
		webTestClient.get().uri("/conflict")
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.CONFLICT)
			.expectBody()
			.jsonPath("$.refusal").isEqualTo("http.CONFLICT")
			.jsonPath("$.parameters.property").isEqualTo("email")
	}

	@Test
	fun `should answer a refusal wrapped at any depth with its own status and body`() {
		webTestClient.get().uri("/wrapped-refusal")
			.exchange()
			.expectStatus().isNotFound
			.expectBody()
			.jsonPath("$.refusal").isEqualTo("http.NOT_FOUND")
			.jsonPath("$.parameters.id").isEqualTo("9")
	}

	@Test
	fun `should return 500 for F2Exception, without its message`() {
		webTestClient.get().uri("/f2-exception")
			.exchange()
			.expectStatus().is5xxServerError
			.expectBody()
			.jsonPath("$.refusal").isEqualTo("http.UNEXPECTED")
			.jsonPath("$.message").doesNotExist()
	}

	@Test
	fun `should answer a refusal with its status, code, sentence, parameters and citers`() {
		webTestClient.get().uri("/refusal")
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.CONFLICT)
			.expectBody()
			.jsonPath("$.status").isEqualTo(409)
			.jsonPath("$.refusal").isEqualTo("item.CITED")
			.jsonPath("$.message").isEqualTo("The item is cited: it stays")
			.jsonPath("$.parameters.id").isEqualTo("i-1")
			.jsonPath("$.citers[0]").isEqualTo("A")
			.jsonPath("$.id").doesNotExist()
			.jsonPath("$.code").doesNotExist()
	}

	@Test
	fun `should answer a refusal written by a web filter with the same body`() {
		webTestClient.get().uri("/filtered")
			.exchange()
			.expectStatus().isUnauthorized
			.expectBody()
			.jsonPath("$.path").isEqualTo("/filtered")
			.jsonPath("$.status").isEqualTo(401)
			.jsonPath("$.refusal").isEqualTo("http.UNAUTHENTICATED")
			.jsonPath("$.message").doesNotExist()
	}

	@Test
	fun `should return 500 for generic RuntimeException, without its message`() {
		webTestClient.get().uri("/generic-error")
			.exchange()
			.expectStatus().is5xxServerError
			.expectBody()
			.jsonPath("$.refusal").isEqualTo("http.UNEXPECTED")
			.jsonPath("$.message").doesNotExist()
	}

	@Test
	fun `should return 400 for a raw ResponseStatusException that is not an F2Exception`() {
		webTestClient.get().uri("/raw-status-exception")
			.exchange()
			.expectStatus().isBadRequest
			.expectBody()
			.jsonPath("$.status").isEqualTo(400)
	}

	@Test
	fun `should return 404 for a raw ResponseStatusException carrying a different status`() {
		webTestClient.get().uri("/raw-status-exception-404")
			.exchange()
			.expectStatus().isNotFound
			.expectBody()
			.jsonPath("$.status").isEqualTo(404)
	}

	@Test
	fun `should return 400 for a KotlinInvalidNullException`() {
		webTestClient.post().uri("/kotlin-null-field")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue("""{"name":null}""")
			.exchange()
			.expectStatus().isBadRequest
			.expectBody()
			.jsonPath("$.status").isEqualTo(400)
			.jsonPath("$.refusal").isEqualTo("http.INVALID_PAYLOAD")
			.jsonPath("$.message").isEqualTo("Missing parameter `name`")
			.jsonPath("$.parameters.parameter").isEqualTo("name")
	}

	@Test
	fun `should include path and timestamp in error response`() {
		webTestClient.get().uri("/not-found")
			.exchange()
			.expectBody()
			.jsonPath("$.path").isEqualTo("/not-found")
			.jsonPath("$.timestamp").isNotEmpty
	}
}
