package f2.bdd.spring.autoconfigure.steps

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlin.reflect.KFunction
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

	fun <P, R> KFunction<*>.blockingFunctionBean(): (P) -> R {
		return bag.getBlockingFunctionBean(this.name)
	}

	fun <R> KFunction<*>.blockingSupplierBean(): () -> R {
		return bag.getBlockingSupplierBean(this.name)
	}

	fun <P> KFunction<*>.blockingConsumerBean(): (P) -> Unit {
		return bag.getBlockingConsumerBean(this.name)
	}

	fun <P, R> KFunction<*>.functionBean(): suspend (P) -> R {
		return bag.getFunctionBean(this.name)
	}

	fun <R> KFunction<*>.supplierBean(): suspend () -> R {
		return bag.getSupplierBean(this.name)
	}

	fun <P> KFunction<*>.consumerBean(): suspend (P) -> Unit {
		return bag.getConsumerBean(this.name)
	}

	@Suppress("UNCHECKED_CAST")
	fun <P, R> KFunction<*>.functionF2Bean(): F2Function<P, R> {
		return bag.applicationContext!!.getBean(name) as F2Function<P, R>
	}

	@Suppress("UNCHECKED_CAST")
	fun <R> KFunction<*>.supplierF2Bean(): F2Supplier<R> {
		return bag.applicationContext!!.getBean(name) as F2Supplier<R>
	}

	@Suppress("UNCHECKED_CAST")
	fun <P> KFunction<*>.consumerF2Bean(): F2Consumer<P> {
		return bag.applicationContext!!.getBean(name) as F2Consumer<P>
	}
}
