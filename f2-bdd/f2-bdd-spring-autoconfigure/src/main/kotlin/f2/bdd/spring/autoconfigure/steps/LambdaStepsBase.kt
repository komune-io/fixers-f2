package f2.bdd.spring.autoconfigure.steps

import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

abstract class LambdaStepsBase<P, R> : F2SpringStep() {

	fun En.prepareLambdaSteps(
		functionName: String,
		supplierName: String,
		consumerName: String,
	) {
		prepareSteps()

		When("Execute function $functionName with") { dataTable: DataTable ->
			bag.result[functionName] = function(transform(dataTable)) as Any
		}

		When("Execute supplier $supplierName") {
			bag.result[supplierName] = supplier() as Any
		}

		When("Execute consumer $consumerName with") { dataTable: DataTable ->
			runBlocking {
				consumer(transform(dataTable))
				delay(timeMillis = 1000)
				bag.result[consumerName] = consumedItems()
			}
		}
	}

	protected abstract fun consumedItems(): Any
	abstract fun transform(dataTable: DataTable): P
	abstract fun function(values: P): R
	abstract fun supplier(): R
	abstract fun consumer(values: P)
}
