package f2.spring.flow

import f2.bdd.spring.autoconfigure.steps.LambdaListStepsBase
import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.spring.single.LambdaSimple
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class LambdaFlowSteps : LambdaListStepsBase<String, String>(), En {

	init {
		prepareLambdaSteps(
			functionName = LambdaFlow::functionFlow.name,
			supplierName = LambdaFlow::supplierFlow.name,
			consumerName = LambdaFlow::consumerFlow.name
		)
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

	override fun transform(dataTable: DataTable): List<String> {
		return dataTable.asList()
	}

	override fun receiver(): ConsumerReceiver<String> {
		return bag.applicationContext!!.getBean(LambdaSimple::lambdaSingleReceiver.name) as ConsumerReceiver<String>
	}
}
