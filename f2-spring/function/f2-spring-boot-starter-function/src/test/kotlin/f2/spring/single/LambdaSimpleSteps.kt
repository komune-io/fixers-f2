package f2.spring.single

import f2.spring.LambdaListStepsBase
import io.cucumber.java8.En
import kotlin.reflect.KFunction

class LambdaSimpleSteps: LambdaListStepsBase(), En {

	init {
		prepareLambdaSteps(
			functionName = LambdaSimple::functionSingle.name,
			supplierName = LambdaSimple::supplierSingle.name,
			consumerName = LambdaSimple::consumerSingle.name,
		)
	}

	override fun function(values: List<String>): List<String> {
		val lambda: (String) -> String = LambdaSimple::functionSingle.blockingFunctionBean()
		return values.map(lambda)
	}

	override fun supplier(): List<String> {
		val lambda: () -> String = LambdaSimple::supplierSingle.blockingSupplierBean()
		return listOf(
			lambda()
		)
	}

	override fun consumer(values: List<String>) {
		val lambda: (String) -> Unit = LambdaSimple::consumerSingle.blockingConsumerBean()
		values.map(lambda)
	}
}
