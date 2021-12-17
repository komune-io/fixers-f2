package f2.spring.single

import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import org.assertj.core.api.Assertions

class LambdaSimpleSteps: F2SpringStep() {

	init {
		prepareSteps()

		@Suppress("UNCHECKED_CAST")
		When("Execute ${LambdaSimple::functionSingle.name} with {string}") { value: String ->
			val functionPureKotlin = bag.applicationContext!!.getBean(LambdaSimple::functionSingle.name) as (String) -> String
			bag.result[LambdaSimple::functionSingle.name] = functionPureKotlin(value)
		}

		@Suppress("UNCHECKED_CAST")
		When("Execute ${LambdaSimple::supplierSingle.name}") {
			val functionPureKotlin = bag.applicationContext!!.getBean(LambdaSimple::supplierSingle.name) as () -> String
			bag.result[LambdaSimple::supplierSingle.name] = functionPureKotlin()
		}

		@Suppress("UNCHECKED_CAST")
		When("Execute ${LambdaSimple::consumerSingle.name} with {string}") { value: String ->
			val functionPureKotlin = bag.applicationContext!!.getBean(LambdaSimple::consumerSingle.name) as (String) -> Void
			functionPureKotlin.invoke(value)
			val receiver = bag.applicationContext!!.getBean(LambdaSimple::lambdaSingleReceiver.name) as LambdaPureKotlinReceiver
			bag.result[LambdaSimple::consumerSingle.name] = receiver.items.first()
		}

		Then("The result for {string} is {string}") {value: String,result: String ->
			Assertions.assertThat(bag.result[value]).isEqualTo(result)
		}
	}
}