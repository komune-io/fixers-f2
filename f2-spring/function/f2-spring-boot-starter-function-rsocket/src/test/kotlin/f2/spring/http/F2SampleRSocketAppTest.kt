//package f2.spring.http
//
//import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
//import f2.client.ktor.F2ClientBuilder
//import f2.client.ktor.get
//import f2.dsl.fnc.*
//import kotlinx.coroutines.flow.flowOf
//import kotlinx.coroutines.flow.toList
//import kotlinx.coroutines.reactive.asFlow
//import kotlinx.coroutines.runBlocking
//import kotlinx.coroutines.test.runTest
//import org.assertj.core.api.Assertions
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.SpringApplication
//import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.cloud.function.context.FunctionCatalog
//import org.springframework.context.annotation.Bean
//import org.springframework.messaging.rsocket.RSocketRequester
//import org.springframework.test.context.junit.jupiter.SpringExtension
//import java.net.URI
//
//
//@SpringBootApplication
//open class TestingWebApplication {
//	@Bean
//	open fun receiverF2(): ConsumerReceiver<String> = StringConsumerReceiver()
//
//	@Bean
//	open fun functionF2(): F2Function<String, String> = f2Function { value ->
//		value.reversed()
//	}
//
//	@Bean
//	open fun supplierF2(): F2Supplier<String> = f2Supplier {
//		flowOf("supplierF2Value", "supplierF2Value1")
//	}
//
//	@Bean
//	open fun consumerF2(receiver: ConsumerReceiver<String>): F2Consumer<String> = f2Consumer { value ->
//		receiver.items.add(value)
//	}
//
//
//	class StringConsumerReceiver(
//		override val items: MutableList<String> = mutableListOf()
//	) : ConsumerReceiver<String>
//
//}
//
//fun main(args: Array<String>) {
//	SpringApplication.run(TestingWebApplication::class.java, *args)
//}
//
//
//@ExtendWith(SpringExtension::class)
//@SpringBootTest(classes = [TestingWebApplication::class])
//class F2SampleRSocketAppTest {
//
//	@Autowired
//	lateinit var catalog: FunctionCatalog
//
//	@Autowired
//	lateinit var rsocketRequesterBuilder: RSocketRequester.Builder
//
//	val port = 7001
//
//	@Test
//	fun rSocketFunctionClient(): Unit = runBlocking {
//		val rSocketRequester = rsocketRequesterBuilder.websocket(URI.create("http://localhost:$port"))
//		val message = rSocketRequester
//			.route("functionF2")
//			.data("employee")
//			.retrieveMono(String::class.java)
//			.block()
//		Assertions.assertThat(message).isEqualTo("eeyolpme")
//
//	}
//
//	@Test
//	fun rSocketSuplierClient(): Unit = runBlocking {
//		val rSocketRequester = rsocketRequesterBuilder.websocket(URI.create("http://localhost:$port"))
//		val message = rSocketRequester
//			.route("supplierF2")
//			.retrieveFlux(String::class.java)
//			.asFlow()
//			.toList()
//		Assertions.assertThat(message).containsExactly("supplierF2Value", "supplierF2Value1")
//	}
//
//	@Test
//	fun `should return the a single response`() = runTest {
//		val response = F2ClientBuilder.get("ws://localhost:$port").function("functionF2").invoke("employee")
//		Assertions.assertThat(response).isEqualTo("eeyolpme")
//	}
//
//	@Test
//	fun `should return supplierF2 array`(): Unit = runBlocking {
//		val response = F2ClientBuilder.get("ws://localhost:$port").supplier("supplierF2").invoke().toList()
//		Assertions.assertThat(response).containsExactly("supplierF2Value", "supplierF2Value1")
//	}
//}
