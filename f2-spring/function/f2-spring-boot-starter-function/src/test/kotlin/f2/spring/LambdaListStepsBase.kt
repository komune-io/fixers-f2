package f2.spring

import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import f2.spring.f2.LambdaF2
import f2.spring.single.LambdaPureKotlinReceiver
import f2.spring.single.LambdaSimple
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlin.reflect.KFunction

abstract class LambdaListStepsBase : F2SpringStep() {

	@Suppress("LongMethod")
	fun En.prepareLambdaSteps(
		functionName: String,
		supplierName: String,
		consumerName: String,
	) {
		prepareSteps()

		When("Execute function $functionName with") { dataTable: DataTable ->
			bag.result[functionName] = function(dataTable.asList())
		}

		When("Execute supplier $supplierName") {
			bag.result[supplierName] = supplier()
		}

		When("Execute consumer $consumerName with") { dataTable: DataTable ->
			consumer(dataTable.asList())
			val receiver = bag.applicationContext!!.getBean(LambdaSimple::lambdaSingleReceiver.name) as LambdaPureKotlinReceiver
			bag.result[consumerName] = receiver.items
		}
	}

	abstract fun function(values: List<String>): List<String>
	abstract fun supplier(): List<String>
	abstract fun consumer(values: List<String>)


	fun <P,R> KFunction<*>.blockingFunctionBean(): (P) -> R {
		return bag.getBlockingFunctionBean(this.name)
	}

	fun <R> KFunction<*>.blockingSupplierBean(): () -> R {
		return bag.getBlockingSupplierBean(this.name)
	}

	fun <P> KFunction<*>.blockingConsumerBean(): (P) -> Unit {
		return bag.getBlockingConsumerBean(this.name)
	}

	fun <P,R> KFunction<*>.functionBean(): suspend (P) -> R {
		return bag.getFunctionBean(this.name)
	}

	fun <R> KFunction<*>.supplierBean(): suspend () -> R {
		return bag.getSupplierBean(this.name)
	}

	fun <P> KFunction<*>.consumerBean(): suspend (P) -> Unit {
		return bag.getConsumerBean(this.name)
	}

	@Suppress("UNCHECKED_CAST")
	fun <P,R> KFunction<*>.functionF2Bean(): F2Function<P,R> {
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