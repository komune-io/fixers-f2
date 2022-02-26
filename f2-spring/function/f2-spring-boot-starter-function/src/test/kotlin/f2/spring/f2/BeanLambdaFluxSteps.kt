package f2.spring.f2

import f2.bdd.spring.lambda.flux.LambdaFlux
import f2.bdd.spring.lambda.flux.LambdaFluxSteps
import io.cucumber.java8.En
import reactor.core.publisher.Flux

class BeanLambdaFluxSteps: LambdaFluxSteps(), En {

	init {
		prepareLambdaSteps()
	}

	override fun function(values: List<String>): List<String> {
		val lambda: (Flux<String>) -> Flux<String> = LambdaFlux::functionFlux.blockingFunctionBean()
		return lambda(Flux.fromIterable(values)).collectList().block()!!
	}

	override fun supplier(): List<String> {
		val lambda: () -> Flux<String> = LambdaFlux::supplierFlux.blockingSupplierBean()
		return lambda().collectList().block()!!
	}

	override fun consumer(values: List<String>) {
		val lambda: (Flux<String>) -> Unit =  LambdaFlux::consumerFlux.blockingConsumerBean()
		val flux = Flux.fromIterable(values)
		lambda(flux)
	}
}
