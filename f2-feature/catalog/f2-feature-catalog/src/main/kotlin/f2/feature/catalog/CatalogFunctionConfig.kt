package f2.feature.catalog

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.function.context.FunctionCatalog
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer
import java.util.function.Supplier

@Configuration
class CatalogFunctionConfig {

	@Autowired
	lateinit var catalog: FunctionCatalog

	@Bean
	fun catalogFunction(): () -> Array<String> {
		return {
			val namesFunction = catalog.getNames(Function::class.java)
			namesFunction.filter { !it.startsWith("&") }.toTypedArray()
		}
	}

	@Bean
	fun catalogSupplier(): () -> Array<String> {
		return {
			val namesFunction = catalog.getNames(Supplier::class.java)
			namesFunction.filter { !it.startsWith("&") }.toTypedArray()
		}
	}

	@Bean
	fun catalogConsumer(): () -> Array<String> {
		return {
			val namesFunction = catalog.getNames(Consumer::class.java)
			namesFunction.filter { !it.startsWith("&") }.toTypedArray()
		}
	}
}
