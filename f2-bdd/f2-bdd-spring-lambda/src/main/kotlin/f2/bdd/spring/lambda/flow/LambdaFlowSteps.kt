package f2.bdd.spring.lambda.flow

import f2.bdd.spring.autoconfigure.steps.LambdaListStepsBase
import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.bdd.spring.lambda.single.LambdaSimple
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En

abstract class LambdaFlowSteps : LambdaListStepsBase<String, String>() {

	fun En.prepareLambdaSteps() {
		prepareLambdaSteps(
			functionName = LambdaFlow::functionFlow.name,
			supplierName = LambdaFlow::supplierFlow.name,
			consumerName = LambdaFlow::consumerFlow.name
		)
	}

	override fun transform(dataTable: DataTable): List<String> {
		return dataTable.asList()
	}

	override fun receiver(): ConsumerReceiver<String> {
		return bag.applicationContext!!.getBean(LambdaSimple::lambdaSingleReceiver.name) as ConsumerReceiver<String>
	}
}
