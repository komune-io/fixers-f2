package f2.bdd.spring.autoconfigure.steps

import f2.bdd.spring.autoconfigure.ApplicationContextBuilder
import f2.bdd.spring.autoconfigure.ApplicationContextRunnerBuilder
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import org.assertj.core.api.Assertions

open class F2SpringContextStep: F2SpringStep(), En {

	companion object {
		private const val SEPARATOR_LINE = "////////////////////////////////////////////////////////"
		private const val PREFERRED_JSON_MAPPER_PROPERTY = "spring.cloud.function.preferred-json-mapper"
		private const val LOCAL_SERVER_PORT_PROPERTY = "local.server.port"
	}

	init {
		prepareSteps()

		Given("The application parameters") { table: DataTable ->
			bag.applicationParameters = table.asMap(String::class.java, String::class.java)
				.entries.associate { (key, value) ->
					requireNotNull(key) to requireNotNull(value) { "Missing value for application parameter '$key'" }
				}
		}

		When("I start a valid spring application context") {
			println(SEPARATOR_LINE)
			println(SEPARATOR_LINE)
			println(System.getProperty(PREFERRED_JSON_MAPPER_PROPERTY))
			println(SEPARATOR_LINE)
			println(SEPARATOR_LINE)
			// Port 0: a port drawn at random can be taken. The server then fails to start, or binds
			// the IPv6 wildcard beside a listener on 127.0.0.1, which answers the client instead.
			val context = ApplicationContextBuilder().create(
				types = arrayOf(ApplicationContextBuilder.SimpleConfiguration::class.java),
				config = mapOf(
					"server.port" to "0",
					PREFERRED_JSON_MAPPER_PROPERTY to System.getProperty(PREFERRED_JSON_MAPPER_PROPERTY)
				)
			)
			bag.applicationContext = context
			bag.httpPort = context.environment.getProperty(LOCAL_SERVER_PORT_PROPERTY, Int::class.java)
		}

		After { _: Scenario ->
			Assertions.assertThat(bag.exceptions)
				.describedAs("F2 exceptions thrown and asserted by no step")
				.isEmpty()
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
				name = requireNotNull(columns[CucumberF2SpringDeclaration::name.name]) {
					"Missing '${CucumberF2SpringDeclaration::name.name}' column"
				},
			)
		}
	}
}

data class CucumberF2SpringDeclaration(
	val name: String
)
