package f2.spring.catalog

import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import f2.spring.f2.LambdaF2
import f2.spring.single.LambdaPureKotlinReceiver
import f2.spring.single.LambdaSimple
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.mockito.BDDMockito
import org.springframework.cloud.function.context.FunctionCatalog
import reactor.core.publisher.Flux
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier

class FunctionCatalogSteps : F2SpringStep(), En {

	init {
		prepareSteps()

		When("Function catalog contains") { table: DataTable ->
			val functionCatalog = bag.applicationContext!!.getBean(FunctionCatalog::class.java)
			table.asList().forEach { name ->
				val result = functionCatalog.lookup<Any>(name)
				Assertions.assertThat(result).isNotNull
			}
		}

		When("Function catalog execute consumer {string} with") { functionName: String, table: DataTable ->
			val functionCatalog = bag.applicationContext!!.getBean(FunctionCatalog::class.java)
			val lambda = functionCatalog.lookup<Consumer<Flux<String>>>(functionName)

			lambda.accept(Flux.just(*table.asList().toTypedArray()))
			val receiver = bag.applicationContext!!.getBean(LambdaPureKotlinReceiver::class.java)
			bag.result[functionName] = receiver.items

		}

		When("Function catalog execute supplier {string}") { functionName: String ->
			val functionCatalog = bag.applicationContext!!.getBean(FunctionCatalog::class.java)
			val lambda1 = functionCatalog.lookup<Any>(functionName)
			val lambda = functionCatalog.lookup<Supplier<Flux<String>>>(functionName)
			println(lambda1)
			val result: List<String> = lambda.get().collectList().block()!!
			bag.result[functionName] = result
		}

		When("Function catalog execute function {string} with") { functionName: String, table: DataTable ->
			val functionCatalog = bag.applicationContext!!.getBean(FunctionCatalog::class.java)
			val lambda = functionCatalog.lookup<Function<Flux<String>, Flux<String>>>(functionName)

			val result: List<String> = lambda.apply(Flux.just(*table.asList().toTypedArray())).collectList().block()!!
			bag.result[functionName] = result
		}

		@Suppress("UNCHECKED_CAST")
		Then("The function catalog result for {string} is") { value: String, dataTable: DataTable ->
			Assertions.assertThat(bag.result[value] as List<String>?).isEqualTo(dataTable.asList())
		}
	}
}