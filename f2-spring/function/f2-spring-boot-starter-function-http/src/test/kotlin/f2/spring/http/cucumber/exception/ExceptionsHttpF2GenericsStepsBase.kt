package f2.spring.http.cucumber.exception

import f2.bdd.spring.lambda.HttpF2GenericsStepsBase
import f2.dsl.cqrs.exception.F2Exception
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.springframework.cloud.function.context.FunctionCatalog

abstract class ExceptionsHttpF2GenericsStepsBase<P, R>(prefix: String): HttpF2GenericsStepsBase<P, R>(prefix) {

    override fun En.prepareFunctionCatalogSteps() {
        prepareSteps()

        When("${prefix}Function catalog contains") { table: DataTable ->
            val functionCatalog = bag.applicationContext!!.getBean(FunctionCatalog::class.java)
            table.asList().forEach { name ->
                val result = functionCatalog.lookup<Any>(name)
                Assertions.assertThat(result).isNotNull
            }
        }

        When("${prefix}Execute supplier {string}") { supplierName: String ->
            step(supplierName) {
                val result = supplier(supplierName)
                bag.result[supplierName] = result.map(::fromJson)
            }
        }

        When("${prefix}Execute function {string} with") { functionName: String, table: DataTable ->
            step(functionName) {
                val result = function(table, functionName)
                bag.result[functionName] = result.map(::fromJson)
            }
        }

        When("${prefix}Execute consumer {string} with") { consumerName: String, table: DataTable ->
            step(consumerName) {
                val json = transform(table).asFlow().map(::toJson)
                consumer(json, consumerName)
                delay(timeMillis = 500L)
                bag.result[consumerName] = consumerReceiver()
            }
        }

        Then("${prefix}An exception with code {int} has been thrown for {string}") { code: Int, name: String ->
            val exception = bag.exceptions[name]
            Assertions.assertThat(exception).isInstanceOf(F2Exception::class.java)
            Assertions.assertThat((exception as F2Exception).error.code).isEqualTo(code)
        }
    }

    private fun step(name: String, block: suspend () -> Unit) = runBlocking {
        try {
            block()
        } catch (e: Throwable) {
            bag.exceptions[name] = e
        }
    }
}
