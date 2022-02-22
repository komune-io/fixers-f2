package f2.spring.f2

import f2.bdd.spring.lambda.f2.LambdaF2
import f2.bdd.spring.lambda.f2.LambdaF2Steps
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.cucumber.java8.En
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class BeanLambdaF2Steps : LambdaF2Steps(), En {

	init {
		prepareLambdaSteps()
	}

	override fun function(values: List<String>): List<String> = runBlocking {
		val lambda: F2Function<String, String> = LambdaF2::functionF2.functionF2Bean()
		lambda(values.asFlow()).toList()
	}

	override fun supplier(): List<String> = runBlocking {
		val lambda: F2Supplier<String> = LambdaF2::supplierF2.supplierF2Bean()
		lambda().toList()
	}

	override fun consumer(values: List<String>) = runBlocking {
		val lambda: F2Consumer<String> = LambdaF2::consumerF2.consumerF2Bean()
		val flow = values.asFlow()
		lambda(flow)
	}
}
