package f2.spring.f2

import f2.bdd.spring.lambda.list.LambdaList
import f2.bdd.spring.lambda.list.LambdaListSteps
import io.cucumber.java8.En

class BeanLambdaListSteps : LambdaListSteps(), En {

	init {
		prepareLambdaSteps()
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
}
