package f2.feature.catalog

import java.util.function.Consumer
import java.util.function.Supplier
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog

class CatalogFunctionConfigTest {

    private val functionCatalog = object : FunctionCatalog {
        override fun <T> lookup(
            type: Class<*>?,
            functionDefinition: String?,
            vararg expectedOutputMimeTypes: String?
        ): T? {
            return null
        }

        override fun getNames(type: Class<*>?): Set<String> = when (type) {
            Function::class.java -> setOf("myFunction", "&internalFunction")
            Supplier::class.java -> setOf("mySupplier", "&internalSupplier")
            Consumer::class.java -> setOf("myConsumer", "&internalConsumer")
            else -> emptySet()
        }
    }

    private val config = CatalogFunctionConfig().apply {
        catalog = functionCatalog
    }

    @Test
    fun `catalogFunction should list function names without internal ones`() {
        assertThat(config.catalogFunction()()).containsExactly("myFunction")
    }

    @Test
    fun `catalogSupplier should list supplier names without internal ones`() {
        assertThat(config.catalogSupplier()()).containsExactly("mySupplier")
    }

    @Test
    fun `catalogConsumer should list consumer names without internal ones`() {
        assertThat(config.catalogConsumer()()).containsExactly("myConsumer")
    }
}
