package f2.spring.flow

import f2.spring.single.LambdaPureKotlinReceiver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux

@Configuration
open class LambdaFlow {

	@Bean
	open fun functionFlow(functionSimple: (String) -> String): suspend (Flow<String>) -> Flow<String> = { value ->
		value.map { functionSimple(it) }
	}

	@Bean
	open fun supplierFlow(): suspend () -> Flow<String> = {
		listOf("supplierFlow").asFlow()
	}

	@Bean
	open fun consumerFlow(receiver: LambdaPureKotlinReceiver): suspend (Flow<String>) -> Unit = {
		it.map { value ->
			receiver.items.add(value)
		}.toList()
	}

}