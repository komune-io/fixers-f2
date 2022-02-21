//package f2.spring.http
//
//import f2.client.ktor.F2ClientBuilder
//import f2.client.ktor.get
//import f2.dsl.fnc.invoke
//import kotlinx.coroutines.flow.toList
//import kotlinx.coroutines.test.runTest
//import org.assertj.core.api.Assertions
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.web.server.LocalServerPort
//import org.springframework.cloud.function.context.FunctionCatalog
//import org.springframework.context.annotation.Bean
//import org.springframework.core.ParameterizedTypeReference
//import org.springframework.http.MediaType
//import org.springframework.test.context.junit.jupiter.SpringExtension
//import org.springframework.test.web.reactive.server.WebTestClient
//import org.springframework.web.reactive.function.BodyInserters
//
//@SpringBootApplication
//open class TestingWebApplication {
//	@Bean
//	open fun functionF2SampleHttpAppLambda(): (String) -> String = { value ->
//		value.reversed()
//	}
//
//	@Bean
//	open fun supplierF2SampleHttpAppLambda(): () -> List<String> = {
//		listOf("supplierValuePure1", "supplierValuePure2")
//	}
//}
//
//inline fun <reified T : Any> typeRef(): ParameterizedTypeReference<T> = object : ParameterizedTypeReference<T>() {}
//
//@ExtendWith(SpringExtension::class)
//@SpringBootTest(classes = [TestingWebApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class F2SampleHttpAppTest {
//
//	@Autowired
//	lateinit var catalog: FunctionCatalog
//
//	@Autowired
//	lateinit var webClient: WebTestClient
//
//	@LocalServerPort
//	private val port = 0
//
//	@Test
//	fun springWebClient() = runTest {
//		webClient.post()
//			.uri("/functionF2SampleHttpAppLambda")
//			.accept(MediaType.APPLICATION_JSON)
//			.contentType(MediaType.APPLICATION_JSON)
//			.body(BodyInserters.fromValue("employee"))
//			.exchange()
//			.expectStatus().isOk
//			.expectBody(String::class.java)
//			.consumeWith {
//				Assertions.assertThat(it.responseBody).contains("eeyolpme")
//			}
//	}
//
//	@Test
//	fun testCatalogue() = runTest {
//		webClient.post()
//			.uri("/supplierF2SampleHttpAppLambda")
//			.exchange()
//			.expectStatus().isOk
//			.expectBody(typeRef<List<String>>())
//			.consumeWith {
//				Assertions.assertThat(it.responseBody).contains("supplierValuePure1", "supplierValuePure2")
//			}
//	}
//
//	@Test
//	fun `should return the a single response`() = runTest {
//		val response = F2ClientBuilder.get("http://localhost:$port").function("functionF2SampleHttpAppLambda").invoke("employee")
//		Assertions.assertThat(response).isEqualTo("eeyolpme")
//	}
//
//	@Test
//	fun `should return supplierF2 array`() = runTest {
//		val response = F2ClientBuilder.get("http://localhost:$port").supplier("supplierF2SampleHttpAppLambda").invoke().toList()
//		Assertions.assertThat(response).containsExactly("supplierValuePure1", "supplierValuePure2")
//	}
//}
