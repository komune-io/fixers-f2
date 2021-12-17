package f2.spring.flow

import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import f2.spring.single.LambdaPureKotlinReceiver
import f2.spring.single.LambdaSimple
import io.cucumber.datatable.DataTable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions

class LambdaFlowSteps : F2SpringStep() {

	init {
		prepareSteps()

		When("Execute ${LambdaFlow::functionFlow.name} with") { dataTable: DataTable ->
			runBlocking {
				val functionPureKotlin =
					bag.applicationContext!!.getBean(LambdaFlow::functionFlow.name) as suspend (Flow<String>) -> Flow<String>
				bag.result[LambdaFlow::functionFlow.name] =
					functionPureKotlin(dataTable.asList().asFlow()).toList()
			}
		}

		When("Execute ${LambdaFlow::supplierFlow.name}") {
			runBlocking {
				val functionPureKotlin =
					bag.applicationContext!!.getBean(LambdaFlow::supplierFlow.name) as suspend () -> Flow<String>
				bag.result[LambdaFlow::supplierFlow.name] = functionPureKotlin().toList()
			}
		}


		When("Execute ${LambdaFlow::consumerFlow.name} with") { dataTable: DataTable ->
			runBlocking {
				val functionPureKotlin =
					bag.applicationContext!!.getBean(LambdaFlow::consumerFlow.name) as suspend (Flow<String>) -> Void
				functionPureKotlin(dataTable.asList().asFlow())
				val receiver =
					bag.applicationContext!!.getBean(LambdaSimple::lambdaSingleReceiver.name) as LambdaPureKotlinReceiver
				bag.result[LambdaSimple::consumerSingle.name] = receiver.items.first()
			}
		}


		Then("The flow result for {string} is") { value: String, dataTable: DataTable ->
			Assertions.assertThat(bag.result[value] as List<String>?).isEqualTo(dataTable.asList())
		}
	}

}