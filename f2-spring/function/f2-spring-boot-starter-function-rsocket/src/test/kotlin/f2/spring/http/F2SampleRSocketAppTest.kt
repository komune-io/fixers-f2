package f2.spring.http

import f2.client.ktor.F2ClientBuilder
import io.netty.handler.codec.http.websocketx.WebSocketScheme.WS
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.function.context.FunctionCatalog
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.net.URI


@SpringBootApplication
open class TestingWebApplication

fun main(args: Array<String>) {
	SpringApplication.run(TestingWebApplication::class.java, *args)
}


@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [TestingWebApplication::class])
class F2SampleRSocketAppTest {

	@Autowired
	lateinit var catalog: FunctionCatalog

	@Autowired
	lateinit var rsocketRequesterBuilder: RSocketRequester.Builder

	@Test
	fun rSocketFunctionClient(): Unit = runBlocking {
		val rSocketRequester = rsocketRequesterBuilder.websocket(URI.create("http://localhost:7001"))
		val message = rSocketRequester
			.route("functionF2")
			.data("employee")
			.retrieveMono(String::class.java)
			.block()
		Assertions.assertThat(message).isEqualTo("eeyolpme")

	}

	@Test
	fun rSocketSuplierClient(): Unit = runBlocking {
		val rSocketRequester = rsocketRequesterBuilder.websocket(URI.create("http://localhost:7001"))
		val message = rSocketRequester
			.route("supplierF2")
			.retrieveMono(String::class.java)
			.block()
		Assertions.assertThat(message).isEqualTo("supplierF2Value")

	}
}
