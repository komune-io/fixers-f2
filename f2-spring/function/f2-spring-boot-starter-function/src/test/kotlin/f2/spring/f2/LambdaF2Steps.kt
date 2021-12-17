package f2.spring.f2

import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import f2.spring.single.LambdaPureKotlinReceiver
import f2.spring.single.LambdaSimple
import io.cucumber.datatable.DataTable
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions

class LambdaF2Steps : F2SpringStep() {

	init {
		prepareSteps()

		@Suppress("UNCHECKED_CAST")
		When("Execute ${LambdaF2::functionF2.name} with") { dataTable: DataTable ->
			runBlocking {
				val functionPureKotlin =
					bag.applicationContext!!.getBean(LambdaF2::functionF2.name) as F2Function<String, String>
				bag.result[LambdaF2::functionF2.name] = functionPureKotlin(dataTable.asList().asFlow()).toList()
			}
		}

		@Suppress("UNCHECKED_CAST")
		When("Execute ${LambdaF2::supplierF2.name}") {
			runBlocking {
				val functionPureKotlin =
					bag.applicationContext!!.getBean(LambdaF2::supplierF2.name) as F2Supplier<String>
				bag.result[LambdaF2::supplierF2.name] = functionPureKotlin().toList()
			}
		}

		@Suppress("UNCHECKED_CAST")
		When("Execute ${LambdaF2::consumerF2.name} with") { dataTable: DataTable ->
			runBlocking {
				val functionPureKotlin =
					bag.applicationContext!!.getBean(LambdaF2::consumerF2.name) as F2Consumer<String>
				val flow  = dataTable.asList().asFlow()
				functionPureKotlin(flow)
				val receiver =
					bag.applicationContext!!.getBean(LambdaSimple::lambdaSingleReceiver.name) as LambdaPureKotlinReceiver
				bag.result[LambdaSimple::consumerSingle.name] = receiver.items.first()
			}
		}

		@Suppress("UNCHECKED_CAST")
		Then("The f2 result for {string} is") { value: String, dataTable: DataTable ->
			Assertions.assertThat(bag.result[value] as List<String>?).isEqualTo(dataTable.asList())
		}
	}

}