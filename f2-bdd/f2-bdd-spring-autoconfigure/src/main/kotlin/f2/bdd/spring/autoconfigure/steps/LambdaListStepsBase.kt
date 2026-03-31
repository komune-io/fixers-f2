package f2.bdd.spring.autoconfigure.steps

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@Suppress("TooManyFunctions")
abstract class LambdaListStepsBase<P, R> : F2SpringStep() {

	@Suppress("LongMethod")
	fun En.prepareLambdaSteps(
		functionName: String,
		supplierName: String,
		consumerName: String,
	) {
		prepareSteps()

		When("Execute function $functionName with") { dataTable: DataTable ->
			bag.result[functionName] = function(transform(dataTable))
		}

		When("Execute supplier $supplierName") {
			bag.result[supplierName] = supplier()
		}

		When("Execute consumer $consumerName with") { dataTable: DataTable ->
			runBlocking {
				consumer(transform(dataTable))
				delay(timeMillis = 1000)
				val receiver = receiver()
				bag.result[consumerName] = receiver.items
			}
		}
	}

	abstract fun receiver(): ConsumerReceiver<P>
	abstract fun transform(dataTable: DataTable): List<P>
	abstract fun function(values: List<P>): List<R>
	abstract fun supplier(): List<R>
	abstract fun consumer(values: List<P>)
}
