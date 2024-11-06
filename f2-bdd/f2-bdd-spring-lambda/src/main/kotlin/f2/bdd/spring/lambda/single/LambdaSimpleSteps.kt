package f2.bdd.spring.lambda.single

import f2.bdd.spring.autoconfigure.steps.LambdaSingleStepsBase
import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import org.assertj.core.api.Assertions

abstract class LambdaSimpleSteps: LambdaSingleStepsBase<String, String>() {

	fun En.prepareLambdaSteps() {
		prepareLambdaSteps(
			functionName = LambdaSimple::functionSingle.name,
			supplierName = LambdaSimple::supplierSingle.name,
			consumerName = LambdaSimple::consumerSingle.name,
		)

		@Suppress("UNCHECKED_CAST")
		Then("Single: The result for {string} is") { value: String, dataTable: DataTable ->
			Assertions.assertThat(listOf(bag.result[value] as String)).isEqualTo(dataTable.asList())
		}
	}

	override fun transform(dataTable: DataTable): String {
		return dataTable.asList().first()
	}

	override fun receiver(): ConsumerReceiver<String> {
		return bag.applicationContext!!.getBean(LambdaSimple::lambdaSingleReceiver.name) as ConsumerReceiver<String>
	}
}
