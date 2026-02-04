package f2.bdd.spring.autoconfigure.steps

import f2.bdd.spring.autoconfigure.ApplicationContextBuilder
import f2.bdd.spring.autoconfigure.ApplicationContextRunnerBuilder
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlin.random.Random
import org.assertj.core.api.Assertions

open class F2SpringContextStep: F2SpringStep(), En {

	companion object {
		private const val SEPARATOR_LINE = "////////////////////////////////////////////////////////"
		private const val PREFERRED_JSON_MAPPER_PROPERTY = "spring.cloud.function.preferred-json-mapper"
	}

	init {
		prepareSteps()

		Given("The application parameters") { table: DataTable ->
			bag.applicationParameters = table.asMap(String::class.java, String::class.java)
		}

		When("I start a valid spring application context") {
			bag.httpPort = Random.nextInt(from = 6000, until = 6999)
			println(SEPARATOR_LINE)
			println(SEPARATOR_LINE)
			println(System.getProperty(PREFERRED_JSON_MAPPER_PROPERTY))
			println(SEPARATOR_LINE)
			println(SEPARATOR_LINE)
			bag.applicationContext = ApplicationContextBuilder().create(
				types = arrayOf(ApplicationContextBuilder.SimpleConfiguration::class.java),
				config = mapOf(
					"server.port" to "${bag.httpPort}",
					PREFERRED_JSON_MAPPER_PROPERTY to System.getProperty(PREFERRED_JSON_MAPPER_PROPERTY)
				)
			)
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
				name = columns[CucumberF2SpringDeclaration::name.name]!!,
			)
		}
	}
}

data class CucumberF2SpringDeclaration(
	val name: String
)
