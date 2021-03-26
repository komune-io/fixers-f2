package f2.sample.http

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.function.context.FunctionCatalog
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.function.Function
import java.util.function.Supplier

//@TestInstance(PER_CLASS)
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration(initializers = [MongoContainerInitializer::class] )
internal class F2SampleHttpAppTest {


	@Autowired
	lateinit var catalog: FunctionCatalog

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