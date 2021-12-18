package f2.spring.list

import f2.spring.step.LambdaListStepsBase
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import org.assertj.core.api.Assertions

class LambdaListSteps : LambdaListStepsBase<String, String>(), En {

	init {
		prepareLambdaSteps(
			functionName = LambdaList::functionList.name,
			supplierName = LambdaList::supplierList.name,
			consumerName = LambdaList::consumerList.name
		)

		@Suppress("UNCHECKED_CAST")
		Then("The result for {string} is") { value: String, dataTable: DataTable ->
			Assertions.assertThat(bag.result[value] as List<String>?).isEqualTo(dataTable.asList())
		}
	}

	override fun function(values: List<String>): List<String> {
		val lambda: (List<String>) -> List<String> = LambdaList::functionList.blockingFunctionBean()
		return lambda(values)
	}

	override fun supplier(): List<String> {
		val lambda:  () -> List<String> = LambdaList::supplierList.blockingSupplierBean()
		return lambda()
	}

	override fun consumer(values: List<String>) {
		val lambda: (List<String>) -> Unit = LambdaList::consumerList.blockingConsumerBean()
		lambda(values)
	}

	override fun transform(dataTable: DataTable): List<String> {
		return dataTable.asList()
	}

}