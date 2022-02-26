package f2.spring.http.cucumber

import f2.bdd.spring.lambda.HttpF2GenericsStepsBase
import f2.bdd.spring.lambda.single.StringConsumerReceiver
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.get
import f2.spring.http.F2SpringRSocketCucumberConfig
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class HttpF2SingleSteps : HttpF2GenericsStepsBase<String, String>("Single: "), En {

	init {
		prepareFunctionCatalogSteps()
	}

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
			.get(F2SpringRSocketCucumberConfig.urlBase(bag))
			.function(functionName)
			.invoke(json)
			.toList()

	}

	override fun consumer(json: Flow<String>, consumerName: String): Unit = runBlocking {
		F2ClientBuilder.get(F2SpringRSocketCucumberConfig.urlBase(bag)).consumer(consumerName).invoke(json)
	}

	override fun supplier(supplierName: String) = runBlocking {
		F2ClientBuilder.get(F2SpringRSocketCucumberConfig.urlBase(bag)).supplier(supplierName).invoke().toList()
	}

	override fun fromJson(msg: String): String {
		return msg
	}

	override fun toJson(msg: String): String {
		return msg
	}
}
