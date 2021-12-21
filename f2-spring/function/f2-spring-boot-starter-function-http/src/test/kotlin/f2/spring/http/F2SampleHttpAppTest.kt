package f2.spring.http

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.cloud.function.context.FunctionCatalog
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import java.util.function.Function
import java.util.function.Supplier


@SpringBootApplication
open class TestingWebApplication

fun main(args: Array<String>) {
	SpringApplication.run(TestingWebApplication::class.java, *args)
}

inline fun <reified T : Any> typeRef(): ParameterizedTypeReference<T> = object : ParameterizedTypeReference<T>() {}


@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [TestingWebApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class F2SampleHttpAppTest {

	@Autowired
	lateinit var catalog: FunctionCatalog

	@Autowired
	lateinit var webClient: WebTestClient

	@LocalServerPort
	private val port = 0

	@Test
	fun springWebClient(): Unit = runBlocking {
		webClient.post()
			.uri("/functionF2")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue("employee"))
			.exchange()
			.expectStatus().isOk
			.expectBody(typeRef<List<String>>())
			.consumeWith {
				Assertions.assertThat(it.responseBody).contains("eeyolpme")
			}
	}

	@Test
	fun testCatalogue(): Unit = runBlocking {
		webClient.post()
			.uri("/supplierF2")
			.exchange()
			.expectStatus().isOk
			.expectBody(typeRef<List<String>>())
			.consumeWith {
				Assertions.assertThat(it.responseBody).contains("supplierF2Value")
			}
	}
}
