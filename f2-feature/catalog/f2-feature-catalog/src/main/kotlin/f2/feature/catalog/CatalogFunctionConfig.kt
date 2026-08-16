package f2.feature.catalog

import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.function.context.FunctionCatalog
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CatalogFunctionConfig {

	@Autowired
	lateinit var catalog: FunctionCatalog

	@Bean
	fun catalogFunction(): () -> Array<String> {
		return { names(Function::class.java) }
	}

	@Bean
	fun catalogSupplier(): () -> Array<String> {
		return { names(Supplier::class.java) }
	}

	@Bean
	fun catalogConsumer(): () -> Array<String> {
		return { names(Consumer::class.java) }
	}

	private fun names(type: Class<*>): Array<String> {
		val names: Set<String> = catalog.getNames(type)
		return names.filter { !it.startsWith("&") }.toTypedArray()
	}
}
