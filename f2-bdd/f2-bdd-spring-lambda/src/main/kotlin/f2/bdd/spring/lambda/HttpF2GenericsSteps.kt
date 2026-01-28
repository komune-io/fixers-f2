package f2.bdd.spring.lambda

import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import f2.dsl.cqrs.exception.F2Exception
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.springframework.cloud.function.context.FunctionCatalog

abstract class HttpF2GenericsSteps<REQUEST, RESPONSE>(
	private val prefix: String
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
			step(supplierName) {
				val result = supplier(supplierName)
				bag.result[supplierName] = result
			}
		}

		When("${prefix}Execute function {string} with") { functionName: String, table: DataTable ->
			step(functionName) {
				val json = transform(table).asFlow()
				val result = function(functionName, json)
				bag.result[functionName] = result
			}
		}

		When("${prefix}Execute consumer {string} with") { consumerName: String, table: DataTable ->
			step(consumerName) {
				val json = transform(table).asFlow()
				consumer(consumerName, json)
				delay(timeMillis = 500L)
				bag.result[consumerName] = consumerReceiver()
			}
		}

		@Suppress("UNCHECKED_CAST")
		Then("${prefix}The result for {string} is") { value: String, dataTable: DataTable ->
			val result = (bag.result[value] as List<REQUEST>?)
			val expected = transform(dataTable)
			Assertions.assertThat(result).isEqualTo(expected)
		}

		Then("${prefix}An exception with code {int} has been thrown for {string}") { code: Int, name: String ->
			val exception = bag.exceptions[name]
			Assertions.assertThat(exception).isInstanceOf(F2Exception::class.java)
			Assertions.assertThat((exception as F2Exception).error.code).isEqualTo(code)
		}

		Then("${prefix}An exception with code {int} has been thrown for {string} with message {string}")
		{ code: Int, name: String, message: String ->
			val exception = bag.exceptions[name]
			Assertions.assertThat(exception).isInstanceOf(F2Exception::class.java)
			Assertions.assertThat((exception as F2Exception).error.code).isEqualTo(code)
			Assertions.assertThat((exception).message).isEqualTo(message)
		}
	}

	@Suppress("TooGenericExceptionCaught")
	private fun step(name: String, block: suspend () -> Unit) = runBlocking {
		try {
			block()
		} catch (e: Throwable) {
			bag.exceptions[name] = e
		}
	}

	abstract fun function(functionName: String, msgs: Flow<REQUEST>): List<RESPONSE>
	abstract fun consumer(consumerName: String, msgs: Flow<REQUEST>)
	abstract fun supplier(supplierName: String): List<RESPONSE>
	abstract fun transform(dataTable: DataTable): List<REQUEST>
	abstract fun consumerReceiver(): List<RESPONSE>

}
