package f2.spring.flux

import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import f2.spring.list.LambdaList
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import org.assertj.core.api.Assertions
import reactor.core.publisher.Flux

class LambdaFluxSteps: F2SpringStep() {

	init {
		prepareSteps()

		When("Execute ${LambdaFlux::functionFlux.name} with") { dataTable: DataTable ->
			bag.contextBuilder.run { context ->
				val functionPureKotlin = context.getBean(LambdaFlux::functionFlux.name) as (Flux<String>) -> Flux<String>
				bag.result[LambdaFlux::functionFlux.name] = functionPureKotlin(Flux.fromIterable(dataTable.asList())).collectList().block()!!
			}
		}

		When("Execute ${LambdaFlux::supplierFlux.name}") {
			bag.contextBuilder.run { context ->
				val functionPureKotlin = context.getBean(LambdaFlux::supplierFlux.name) as () -> Flux<String>
				bag.result[LambdaFlux::supplierFlux.name] = functionPureKotlin().collectList().block()!!
			}
		}

		Then("The flux result for {string} is") { value: String, dataTable: DataTable ->
			Assertions.assertThat(bag.result[value] as List<String>?).isEqualTo(dataTable.asList())
		}
	}

}