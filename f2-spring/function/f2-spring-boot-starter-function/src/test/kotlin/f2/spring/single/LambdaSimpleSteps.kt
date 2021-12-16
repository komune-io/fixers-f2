package f2.spring.single

import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import org.assertj.core.api.Assertions

class LambdaSimpleSteps: F2SpringStep() {

	init {
		prepareSteps()

		When("Execute ${LambdaSimple::functionSingle.name} with {string}") { value: String ->
			bag.contextBuilder.run { context ->
				val functionPureKotlin = context.getBean(LambdaSimple::functionSingle.name) as (String) -> String
				bag.result[LambdaSimple::functionSingle.name] = functionPureKotlin(value)
			}
		}

		When("Execute ${LambdaSimple::supplierSingle.name}") {
			bag.contextBuilder.run { context ->
				val functionPureKotlin = context.getBean(LambdaSimple::supplierSingle.name) as () -> String
				bag.result[LambdaSimple::supplierSingle.name] = functionPureKotlin()
			}
		}

		Then("The result for {string} is {string}") {value: String,result: String ->
			Assertions.assertThat(bag.result[value]).isEqualTo(result)
		}
	}
}