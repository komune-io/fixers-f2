package f2.feature.catalog

import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier
import org.springframework.cloud.function.context.FunctionCatalog
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CatalogFunctionConfig(
    private val catalog: FunctionCatalog,
) {

    @Bean
    fun catalogFunction(): () -> Array<String> = { names(Function::class.java) }

    @Bean
    fun catalogSupplier(): () -> Array<String> = { names(Supplier::class.java) }

    @Bean
    fun catalogConsumer(): () -> Array<String> = { names(Consumer::class.java) }

    private fun names(type: Class<*>): Array<String> {
        val names: Set<String> = catalog.getNames(type)
        return names.filter { !it.startsWith("&") }.toTypedArray()
    }
}
