package f2.spring.f2

import f2.bdd.spring.lambda.flow.LambdaFlow
import f2.bdd.spring.lambda.flow.LambdaFlowSteps
import io.cucumber.java8.En
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class BeanLambdaFlowSteps : LambdaFlowSteps(), En {

	init {
		prepareLambdaSteps()
	}

	override fun function(values: List<String>): List<String> = runBlocking {
		val lambda: suspend (Flow<String>) -> Flow<String> = LambdaFlow::functionFlow.functionBean()
		lambda(values.asFlow()).toList()
	}

	override fun supplier(): List<String> = runBlocking {
		val functionPureKotlin: suspend () -> Flow<String> = LambdaFlow::supplierFlow.supplierBean()
		functionPureKotlin().toList()
	}

	override fun consumer(values: List<String>) = runBlocking {
		val functionPureKotlin: suspend (Flow<String>) -> Unit = LambdaFlow::consumerFlow.consumerBean()
		functionPureKotlin(values.asFlow())
	}
}
