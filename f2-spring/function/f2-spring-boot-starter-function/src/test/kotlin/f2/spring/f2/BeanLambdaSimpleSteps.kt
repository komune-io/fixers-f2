package f2.spring.f2

import f2.bdd.spring.lambda.single.LambdaSimple
import f2.bdd.spring.lambda.single.LambdaSimpleSteps
import io.cucumber.java8.En

class BeanLambdaSimpleSteps: LambdaSimpleSteps(), En {

	init {
		prepareLambdaSteps()
	}

	override fun function(values: String): String {
		val lambda: (String) -> String = LambdaSimple::functionSingle.blockingFunctionBean()
		return lambda(values)
	}

	override fun supplier(): String {
		val lambda: () -> String = LambdaSimple::supplierSingle.blockingSupplierBean()
		return lambda()
	}

	override fun consumer(values: String) {
		val lambda: (String) -> Unit = LambdaSimple::consumerSingle.blockingConsumerBean()
		lambda(values)
	}
}
