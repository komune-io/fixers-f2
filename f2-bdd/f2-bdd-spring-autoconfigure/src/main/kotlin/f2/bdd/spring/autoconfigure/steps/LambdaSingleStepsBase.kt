package f2.bdd.spring.autoconfigure.steps

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@Suppress("TooManyFunctions")
abstract class LambdaSingleStepsBase<P, R> : F2SpringStep() {

	@Suppress("LongMethod")
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
				val receiver = receiver()
				bag.result[consumerName] = receiver.items.first() as Any
			}
		}
	}

	abstract fun receiver(): ConsumerReceiver<P>
	abstract fun transform(dataTable: DataTable): P
	abstract fun function(values: P): R
	abstract fun supplier(): R
	abstract fun consumer(values: P)
}
