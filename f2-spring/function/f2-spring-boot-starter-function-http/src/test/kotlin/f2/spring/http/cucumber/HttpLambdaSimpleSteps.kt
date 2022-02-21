package f2.spring.http.cucumber

import f2.bdd.spring.lambda.single.LambdaSimple
import f2.bdd.spring.lambda.single.LambdaSimpleSteps
import io.cucumber.java8.En

class HttpLambdaSimpleSteps: LambdaSimpleSteps(), En {

	init {
		prepareLambdaSteps()
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
