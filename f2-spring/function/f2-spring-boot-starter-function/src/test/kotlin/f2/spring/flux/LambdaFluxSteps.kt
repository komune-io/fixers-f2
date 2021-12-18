package f2.spring.flux

import f2.spring.step.LambdaListStepsBase
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import reactor.core.publisher.Flux

class LambdaFluxSteps: LambdaListStepsBase<String, String>(), En {

	init {
		prepareLambdaSteps(
			functionName = LambdaFlux::functionFlux.name,
			supplierName = LambdaFlux::supplierFlux.name,
			consumerName = LambdaFlux::consumerFlux.name
		)
	}

	override fun function(values: List<String>): List<String> {
		val lambda: (Flux<String>) -> Flux<String> = LambdaFlux::functionFlux.blockingFunctionBean()
		return lambda(Flux.fromIterable(values)).collectList().block()!!
	}

	override fun supplier(): List<String> {
		val lambda: () -> Flux<String> = LambdaFlux::supplierFlux.blockingSupplierBean<Flux<String>>()
		return lambda().collectList().block()!!
	}

	override fun consumer(values: List<String>) {
		val lambda: (Flux<String>) -> Unit =  LambdaFlux::consumerFlux.blockingConsumerBean()
		val flux = Flux.fromIterable(values)
		lambda(flux)
	}

	override fun transform(dataTable: DataTable): List<String> {
		return dataTable.asList()
	}
}