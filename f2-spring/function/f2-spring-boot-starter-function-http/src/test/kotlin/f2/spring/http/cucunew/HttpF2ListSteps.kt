package f2.spring.http.cucunew

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import f2.bdd.spring.lambda.HttpF2GenericsStepsBase
import f2.bdd.spring.lambda.single.StringConsumerReceiver
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.get
import f2.spring.http.cucumber.F2SpringHttpCucumberConfig
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking


class HttpF2ListSteps : HttpF2GenericsStepsBase<List<String>, List<String>>("List: "), En {

	init {
		prepareFunctionCatalogSteps()
	}

	private val objectMapper = jacksonObjectMapper()

	override fun transform(dataTable: DataTable): List<List<String>> {
		return dataTable.asList().map {
			it.split(",")
		}
	}

	override fun consumerReceiver(): List<List<String>> {
		val bean = bag.applicationContext!!.getBean(StringConsumerReceiver::class.java)
		return listOf(bean.items as List<String>)
	}

	override fun function(table: DataTable, functionName: String) = runBlocking {
		val json = transform(table)
			.asFlow()
			.map(::toJson)

		F2ClientBuilder
			.get(F2SpringHttpCucumberConfig.urlBase(bag))
			.function(functionName)
			.invoke(json)
			.toList()

	}

	override fun consumer(json: Flow<String>, consumerName: String): Unit = runBlocking {
		F2ClientBuilder.get(F2SpringHttpCucumberConfig.urlBase(bag)).consumer(consumerName).invoke(json)
	}

	override fun supplier(supplierName: String) = runBlocking {
		F2ClientBuilder.get(F2SpringHttpCucumberConfig.urlBase(bag)).supplier(supplierName).invoke().toList()
	}

	override fun fromJson(msg: String): List<String> {
		return objectMapper.readValue(msg)
	}

	override fun toJson(msg: List<String>): String {
		return objectMapper.writeValueAsString(msg)
	}
}
