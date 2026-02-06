package f2.bdd.spring.autoconfigure.steps

import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import org.assertj.core.api.Assertions
import org.springframework.cloud.function.context.catalog.SimpleFunctionRegistry
import reactor.core.publisher.Flux

abstract class FunctionCatalogStepsBase<P, R>(
	val prefix: String
) : F2SpringStep() {

	fun En.prepareFunctionCatalogSteps() {
		prepareSteps()

		When("${prefix}Function catalog contains") { table: DataTable ->
			val functionCatalog = bag.getFunctionCatalog()
			table.asList().forEach { name ->
				val result = functionCatalog.lookup<Any>(name)
				Assertions.assertThat(result).isNotNull
			}
		}

		When("${prefix}Function catalog execute {string}") { functionName: String ->
			val functionCatalog =  bag.getFunctionCatalog()
			val lambda = functionCatalog.lookup<Any>(functionName) as SimpleFunctionRegistry.FunctionInvocationWrapper
			val result = lambda.apply(null)
			handleResult(lambda, functionName, result)
		}

		When("${prefix}Function catalog execute {string} with") { functionName: String, table: DataTable ->
			val functionCatalog =  bag.getFunctionCatalog()
			val lambda = functionCatalog.lookup<Any>(functionName) as SimpleFunctionRegistry.FunctionInvocationWrapper
			val result = lambda.apply(transform(table))
			handleResult(lambda, functionName, result)
		}

		@Suppress("UNCHECKED_CAST")
		Then("${prefix}The function catalog result for {string} is") { value: String, dataTable: DataTable ->
			Assertions.assertThat(bag.result[value] as List<Any>?).isEqualTo(transform(dataTable))
		}
	}

	private fun handleResult(
		lambda: SimpleFunctionRegistry.FunctionInvocationWrapper,
		functionName: String,
		result: Any?
	) {
		if (lambda.isFunction || lambda.isSupplier) {
			bag.result[functionName] = (result as Flux<String>).collectList().block()!!
		} else if (lambda.isConsumer) {
			bag.result[functionName] = consumerReceiver()
		}
	}

	abstract fun transform(dataTable: DataTable): List<P>
	abstract fun consumerReceiver(): List<R>
}
