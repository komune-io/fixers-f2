package f2.bdd.spring.autoconfigure.steps

import io.cucumber.java8.En
import io.cucumber.java8.Scenario

open class F2SpringStep: En {

	companion object {
		const val GLUE = "f2.bdd.spring"
	}

	lateinit var bag: F2SpringContextBag

	@Suppress("LongMethod")
	fun En.prepareSteps() {
		Before { scenario: Scenario ->
			bag = F2SpringContextBag.init(scenario)
		}
	}

}
