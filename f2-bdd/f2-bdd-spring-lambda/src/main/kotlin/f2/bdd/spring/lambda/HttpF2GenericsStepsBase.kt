package f2.bdd.spring.lambda

import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.springframework.cloud.function.context.FunctionCatalog

abstract class HttpF2GenericsStepsBase<P, R>(
	val prefix: String
) : F2SpringStep() {

	open fun En.prepareFunctionCatalogSteps() {
		prepareSteps()

		When("${prefix}Function catalog contains") { table: DataTable ->
			val functionCatalog = bag.applicationContext!!.getBean(FunctionCatalog::class.java)
			table.asList().forEach { name ->
				val result = functionCatalog.lookup<Any>(name)
				Assertions.assertThat(result).isNotNull
			}
		}

		When("${prefix}Execute supplier {string}") { supplierName: String ->
			val result = supplier(supplierName)
			bag.result[supplierName] = result.map(::fromJson)
//			val functionCatalog = bag.applicationContext!!.getBean(FunctionCatalog::class.java)
//			val lambda = functionCatalog.lookup<Any>(supplierName) as SimpleFunctionRegistry.FunctionInvocationWrapper
//			val result = lambda.apply(null) as Flux<String>
//			bag.result[supplierName] = (result).collectList().block()!!
		}

		When("${prefix}Execute function {string} with") { functionName: String, table: DataTable ->
			val result = function(table, functionName)
			bag.result[functionName] = result.map(::fromJson)
//			val functionCatalog = bag.applicationContext!!.getBean(FunctionCatalog::class.java)
//			val lambda = functionCatalog.lookup<Any>(functionName) as SimpleFunctionRegistry.FunctionInvocationWrapper
//			val result = lambda.apply(transform(table)) as Flux<String>
//			bag.result[functionName] = (result).collectList().block()!!
		}

		When("${prefix}Execute consumer {string} with") { consumerName: String, table: DataTable ->
			runBlocking {
				val json = transform(table).asFlow().map(::toJson)
				consumer(json, consumerName)
				delay(timeMillis = 500L)
				bag.result[consumerName] = consumerReceiver()
			}
//			val functionCatalog = bag.applicationContext!!.getBean(FunctionCatalog::class.java)
//			val lambda = functionCatalog.lookup<Any>(consumerName) as SimpleFunctionRegistry.FunctionInvocationWrapper
//			lambda.apply(transform(table))
//			bag.result[consumerName] = consumerReceiver()
		}

		@Suppress("UNCHECKED_CAST")
		Then("${prefix}The result for {string} is") { value: String, dataTable: DataTable ->
			val result = (bag.result[value] as List<P>?)
			val expected = transform(dataTable)
			Assertions.assertThat(result).isEqualTo(expected)
		}
	}

	abstract fun function(table: DataTable, functionName: String): List<String>
	abstract fun consumer(table: Flow<String>, consumerName: String)
	abstract fun supplier(supplierName: String): List<String>

	abstract fun transform(dataTable: DataTable): List<P>
	abstract fun toJson(msg: P): String
	abstract fun fromJson(msg: String): P
	abstract fun consumerReceiver(): List<R>
}
