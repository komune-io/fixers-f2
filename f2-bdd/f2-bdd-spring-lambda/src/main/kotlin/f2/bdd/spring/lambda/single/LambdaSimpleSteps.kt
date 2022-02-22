package f2.bdd.spring.lambda.single

import f2.bdd.spring.autoconfigure.steps.LambdaListStepsBase
import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En

abstract class LambdaSimpleSteps: LambdaListStepsBase<String, String>() {

	fun En.prepareLambdaSteps() {
		prepareLambdaSteps(
			functionName = LambdaSimple::functionSingle.name,
			supplierName = LambdaSimple::supplierSingle.name,
			consumerName = LambdaSimple::consumerSingle.name,
		)
	}

	override fun transform(dataTable: DataTable): List<String> {
		return dataTable.asList()
	}

	override fun receiver(): ConsumerReceiver<String> {
		return bag.applicationContext!!.getBean(LambdaSimple::lambdaSingleReceiver.name) as ConsumerReceiver<String>
	}
}
