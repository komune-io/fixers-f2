package f2.sample.http

import java.util.function.Function
import java.util.function.Supplier
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.function.context.FunctionCatalog
import org.springframework.test.context.junit.jupiter.SpringExtension

// @TestInstance(PER_CLASS)
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @ContextConfiguration(initializers = [MongoContainerInitializer::class] )
internal class F2SampleHttpAppTest {

    @Autowired
    lateinit var catalog: FunctionCatalog

    @Test
    fun testCatalogue() {
        val sha256 = catalog.lookup<Any>("sha256")
        val namesFunction = catalog.getNames(Function::class.java)
        val namesSupplier = catalog.getNames(Supplier::class.java)
        logger.info("sha256: {}", sha256)
        logger.info("functions: {}", namesFunction)
        logger.info("suppliers: {}", namesSupplier)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(F2SampleHttpAppTest::class.java)
    }
}
