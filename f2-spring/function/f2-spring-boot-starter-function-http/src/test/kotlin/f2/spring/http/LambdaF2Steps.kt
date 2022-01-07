package f2.spring.http

import f2.bdd.spring.autoconfigure.steps.LambdaListStepsBase
import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class LambdaF2Steps : LambdaListStepsBase<String, String>(), En {

	init {
		prepareLambdaSteps(
			functionName = LambdaF2::functionF2.name,
			supplierName = LambdaF2::supplierF2.name,
			consumerName = LambdaF2::consumerF2.name
		)
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

	override fun transform(dataTable: DataTable): List<String> {
		return dataTable.asList()
	}

	override fun receiver(): ConsumerReceiver<String> {
		return  bag.applicationContext!!.getBean(LambdaF2::receiverF2.name) as ConsumerReceiver<String>
	}
}