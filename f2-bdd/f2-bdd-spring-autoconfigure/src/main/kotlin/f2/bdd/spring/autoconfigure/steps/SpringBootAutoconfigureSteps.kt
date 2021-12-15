package f2.bdd.spring.autoconfigure.steps

import f2.bdd.spring.autoconfigure.ApplicationContextRunnerBuilder
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner

open class SpringBootAutoconfigureSteps : En {
	companion object {
		const val GLUE = "f2.bdd.spring"
		var applicationParameters: Map<String, String> = emptyMap()
		var contextBuilder: ReactiveWebApplicationContextRunner? = null
	}

	init {
		
		Given("The application parameters") { table: DataTable ->
			applicationParameters = table.asMap(String::class.java, String::class.java)
		}

		When("I build a valid spring application context") {
			contextBuilder = ApplicationContextRunnerBuilder()
				.buildContext(applicationParameters)
		}

		Then("Instance is an injectable bean") { table: DataTable ->
			contextBuilder?.run { context ->
				table.asCucumberF2SpringDeclaration().forEach { f2 ->
					assertThat(context).hasBean(f2.beanName)
				}
			}
		}

		When("I get an empty spring application context") {
			contextBuilder = ApplicationContextRunnerBuilder().buildContext()
		}

		Then("Instance is not injectable bean") { table: DataTable ->
			contextBuilder?.run { context ->
				table.asCucumberF2SpringDeclaration().forEach { f2 ->
					assertThat(context).hasBean(f2.beanName)
				}
			}
		}
	}

	fun DataTable.asCucumberF2SpringDeclaration(): List<CucumberF2SpringDeclaration> {
		return asMaps().map { columns ->
			CucumberF2SpringDeclaration(
				beanName = columns[CucumberF2SpringDeclaration::beanName.name]!!.toString(),
			)
		}
	}
}

data class CucumberF2SpringDeclaration(
	val beanName: String
)
