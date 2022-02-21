package f2.sample.rsocket

import f2.client.executeInvoke
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.WS
import f2.client.ktor.get
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.function.context.FunctionCatalog
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.function.Function
import java.util.function.Supplier

@ExtendWith(SpringExtension::class)
@SpringBootTest
class F2SampleHttpAppTest {

	@Autowired
	lateinit var catalog: FunctionCatalog

	@Test
	fun springRSocketClient() = runTest {
		val client = F2ClientBuilder.get(WS, "localhost", 7000, null)
		val response = client.executeInvoke<String, String>("sha256", "HELLO")
		Assertions.assertEquals(response, "NzPNl3/46xi5hzV+Is7Zn0YJfzHssjnoeK5jdg6D5NU=")
	}

	@Test
	fun springRSocketClientT() = runTest {
		val client = F2ClientBuilder.get("ws://localhost:7000")
		val response = client.executeInvoke<String, String>("sha256", "HELLOD")
		Assertions.assertEquals(response, "PqFKVAeEvmyaVkPw5qpHFEw8YnJQ6SuMQvvvfdIStk0=")
	}

	@Test
	fun testCatalogue() = runBlocking {
		val sha256 = catalog.lookup<Any>("sha256")
		val namesFunction = catalog.getNames(Function::class.java)
		val namesSupplier = catalog.getNames(Supplier::class.java)
		println(sha256)
		println(namesFunction)
		println(namesSupplier)
	}
}
