package f2.spring.catalog

import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import f2.dsl.fnc.F2Function
import f2.spring.f2.LambdaF2
import io.cucumber.datatable.DataTable
import org.assertj.core.api.Assertions
import org.springframework.cloud.function.context.FunctionCatalog

class FunctionCatalogSteps : F2SpringStep() {

	init {
		prepareSteps()

		When("Function catalog contains") { table: DataTable ->
			val functionCatalog = bag.applicationContext!!.getBean(FunctionCatalog::class.java)
			table.asList().forEach { name ->
				val result = functionCatalog.lookup<Any>(name)
				Assertions.assertThat(result).isNotNull
			}
		}

	}

}