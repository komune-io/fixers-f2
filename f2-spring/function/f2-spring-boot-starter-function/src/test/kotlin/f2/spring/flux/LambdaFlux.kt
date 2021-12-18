package f2.spring.flux

import f2.spring.single.LambdaPureKotlinReceiver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux

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
	open fun consumerFlux(receiver: LambdaPureKotlinReceiver): (Flux<String>) -> Unit = {
		it.map { value ->
			receiver.items.add(value)
		}.collectList().block()
	}
}
