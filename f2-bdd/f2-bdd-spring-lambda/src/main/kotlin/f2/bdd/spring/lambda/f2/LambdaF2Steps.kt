package f2.bdd.spring.lambda.f2

import f2.bdd.spring.autoconfigure.steps.LambdaListStepsBase
import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.bdd.spring.lambda.single.LambdaSimple
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import org.springframework.core.ParameterizedTypeReference

abstract class LambdaF2Steps : LambdaListStepsBase<String, String>() {

	fun En.prepareLambdaSteps() {
		prepareLambdaSteps(
			functionName = LambdaF2::functionBasic.name,
			supplierName = LambdaF2::supplierBasic.name,
			consumerName = LambdaF2::consumerBasic.name
		)
	}

	override fun transform(dataTable: DataTable): List<String> {
		return dataTable.asList()
	}

	override fun receiver(): ConsumerReceiver<String> {
		return bag.applicationContext!!.getBeanProvider(
            object: ParameterizedTypeReference<ConsumerReceiver<String>>() {}
        ).getObject( LambdaSimple::lambdaSingleReceiver.name)
	}
}
