package f2.bdd.spring.autoconfigure.steps

import f2.bdd.spring.autoconfigure.ApplicationContextRunnerBuilder
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import org.assertj.core.api.Assertions
import org.mockito.BDDMockito

open class F2SpringContextStep: F2SpringStep(), En {

	init {
		prepareSteps()

		Given("The application parameters") { table: DataTable ->
			bag.applicationParameters = table.asMap(String::class.java, String::class.java)
		}

		When("I build a valid spring application context") {
			bag.contextBuilder = ApplicationContextRunnerBuilder()
				.buildContext(bag.applicationParameters)
		}

		Then("Instance is an injectable bean") { table: DataTable ->
			bag.contextBuilder.run { context ->
				table.asCucumberF2SpringDeclaration().forEach { f2 ->
					Assertions.assertThat(context).hasBean(f2.name)
				}
			}
		}

		When("I get an empty spring application context") {
			bag.contextBuilder = ApplicationContextRunnerBuilder().buildContext()
		}

		Then("Instance is not injectable bean") { table: DataTable ->
			bag.contextBuilder.run { context ->
				table.asCucumberF2SpringDeclaration().forEach { f2 ->
					Assertions.assertThat(context).doesNotHaveBean(f2.name)
				}
			}
		}
	}

	private fun DataTable.asCucumberF2SpringDeclaration(): List<CucumberF2SpringDeclaration> {
		return asMaps().map { columns ->
			CucumberF2SpringDeclaration(
				name = columns[CucumberF2SpringDeclaration::name.name]!!.toString(),
			)
		}
	}
}

data class CucumberF2SpringDeclaration(
	val name: String
)
