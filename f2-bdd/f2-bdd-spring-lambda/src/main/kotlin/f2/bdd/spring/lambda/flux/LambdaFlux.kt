package f2.bdd.spring.lambda.flux

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.collect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux
import java.util.function.Consumer

@Configuration
open class LambdaFlux {

	@Bean
	open fun functionFlux(functionSimple: (String) -> String): (Flux<String>) -> Flux<String> = { value ->
		value.map { functionSimple(it) }
	}

	@Bean
	open fun supplierFlux(): () -> Flux<String> = {
		Flux.just("supplierFlux")
	}

	@Bean
	fun consumerFlux(receiver: ConsumerReceiver<String>): (Flux<String>) -> Unit = {
		it.map { value ->
			receiver.items.add(value)
		}.collectList().block()
	}

}
