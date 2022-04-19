package f2.spring.http.cucumber

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import f2.bdd.spring.lambda.HttpF2GenericsStepsBase
import f2.bdd.spring.lambda.single.StringConsumerReceiver
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.get
import f2.spring.http.F2SpringHttpCucumberConfig
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class HttpF2BasicSteps : HttpF2GenericsStepsBase<String, String>("Basic: "), En {

	init {
		prepareFunctionCatalogSteps()
	}

	private val objectMapper = jacksonObjectMapper()

	override fun transform(dataTable: DataTable): List<String> {
		return dataTable.asList()
	}

	override fun consumerReceiver(): List<String> {
		val bean = bag.applicationContext!!.getBean(StringConsumerReceiver::class.java)
		return bean.items
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

	override fun consumer(values: Flow<String>, consumerName: String): Unit = runBlocking {
		F2ClientBuilder.get(F2SpringHttpCucumberConfig.urlBase(bag)).consumer(consumerName).invoke(values)
	}

	override fun supplier(supplierName: String) = runBlocking {
		F2ClientBuilder.get(F2SpringHttpCucumberConfig.urlBase(bag)).supplier(supplierName).invoke().toList()
	}

	override fun fromJson(msg: String): String {
		return objectMapper.readValue(msg)
	}

	override fun toJson(msg: String): String {
		return msg
	}
}
