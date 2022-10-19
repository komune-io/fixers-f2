package f2.bdd.spring.lambda

import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.springframework.cloud.function.context.FunctionCatalog

abstract class HttpF2GenericsStepsNext<REQUEST, RESPONSE>(
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
			bag.result[supplierName] = result
		}

		When("${prefix}Execute function {string} with") { functionName: String, table: DataTable ->
			val msgs = transform(table)
				.asFlow()

			val result = function(functionName, msgs)
			bag.result[functionName] = result
		}

		When("${prefix}Execute consumer {string} with") { consumerName: String, table: DataTable ->
			runBlocking {
				val msgs = transform(table)
					.asFlow()
				consumer(consumerName, msgs)
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
	}

	abstract fun function(functionName: String, msgs: Flow<REQUEST>): List<RESPONSE>
	abstract fun consumer(consumerName: String, msgs: Flow<REQUEST>)
	abstract fun supplier(supplierName: String): List<RESPONSE>

	abstract fun transform(dataTable: DataTable): List<REQUEST>
	abstract fun consumerReceiver(): List<RESPONSE>

}
