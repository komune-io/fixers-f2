package f2.bdd.spring.lambda.f2

import f2.bdd.spring.autoconfigure.steps.LambdaListStepsBase
import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.bdd.spring.lambda.single.LambdaSimple
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En

abstract class LambdaF2Steps : LambdaListStepsBase<String, String>() {

	fun En.prepareLambdaSteps() {
		prepareLambdaSteps(
			functionName = LambdaF2::functionF2.name,
			supplierName = LambdaF2::supplierF2.name,
			consumerName = LambdaF2::consumerF2.name
		)
	}

	override fun transform(dataTable: DataTable): List<String> {
		return dataTable.asList()
	}

	override fun receiver(): ConsumerReceiver<String> {
		return bag.applicationContext!!.getBean(LambdaSimple::lambdaSingleReceiver.name) as ConsumerReceiver<String>
	}
}
