package f2.spring.f2

import f2.bdd.spring.lambda.methodcall.MethodCall
import f2.bdd.spring.lambda.methodcall.MethodCallSteps
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.cucumber.java8.En
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class BeanMethodCallSteps : MethodCallSteps(), En {

	init {
		prepareLambdaSteps()
	}

	override fun function(values: List<String>): List<String> = runBlocking {
		val lambda: F2Function<String, String> = MethodCall::functionMethodCall.functionF2Bean()
		lambda(values.asFlow()).toList()
	}

	override fun supplier(): List<String> = runBlocking {
		val lambda: F2Supplier<String> = MethodCall::supplierMethodCall.supplierF2Bean()
		lambda().toList()
	}

	override fun consumer(values: List<String>) = runBlocking {
		val functionPureKotlin: suspend (Flow<String>) -> Unit = MethodCall::consumerMethodCall.consumerBean()
		functionPureKotlin(values.asFlow())
	}
}
