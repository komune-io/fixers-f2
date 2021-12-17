package f2.spring.list

import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import f2.spring.single.LambdaPureKotlinReceiver
import f2.spring.single.LambdaSimple
import io.cucumber.datatable.DataTable
import org.assertj.core.api.Assertions

class LambdaListSteps: F2SpringStep() {

	init {
		prepareSteps()

		@Suppress("UNCHECKED_CAST")
		When("Execute ${LambdaList::functionList.name} with") { dataTable: DataTable ->
			val functionPureKotlin = bag.applicationContext!!.getBean(LambdaList::functionList.name) as (List<String>) -> List<String>
			bag.result[LambdaList::functionList.name] = functionPureKotlin(dataTable.asList())
		}

		@Suppress("UNCHECKED_CAST")
		When("Execute ${LambdaList::supplierList.name}") {
			val functionPureKotlin = bag.applicationContext!!.getBean(LambdaList::supplierList.name) as () -> List<String>
			bag.result[LambdaList::supplierList.name] = functionPureKotlin()
		}

		@Suppress("UNCHECKED_CAST")
		When("Execute ${LambdaList::consumerList.name} with") { dataTable: DataTable ->
			val functionPureKotlin = bag.applicationContext!!.getBean(LambdaList::consumerList.name) as (List<String>) -> Void
			functionPureKotlin(dataTable.asList())
			val receiver = bag.applicationContext!!.getBean(LambdaSimple::lambdaSingleReceiver.name) as LambdaPureKotlinReceiver
			bag.result[LambdaSimple::consumerSingle.name] = receiver.items.first()
		}

		@Suppress("UNCHECKED_CAST")
		Then("The list result for {string} is") { value: String, dataTable: DataTable ->
			Assertions.assertThat(bag.result[value] as List<String>?).isEqualTo(dataTable.asList())
		}
	}

}