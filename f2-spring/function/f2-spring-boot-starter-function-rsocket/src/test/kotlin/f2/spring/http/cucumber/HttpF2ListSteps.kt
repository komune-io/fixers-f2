package f2.spring.http.cucumber

import f2.bdd.spring.lambda.HttpF2GenericsSteps
import f2.bdd.spring.lambda.single.StringConsumerReceiver
import f2.client.consumerInl
import f2.client.functionInl
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.get
import f2.client.supplierInl
import f2.spring.http.F2SpringRSocketCucumberConfig
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking


class HttpF2ListSteps : HttpF2GenericsSteps<List<String>, List<String>>("List: "), En {

	init {
		prepareFunctionCatalogSteps()
	}

	override fun transform(dataTable: DataTable): List<List<String>> {
		return dataTable.asList().map {
			it.split(",")
		}
	}

	override fun consumerReceiver(): List<List<String>> {
		val bean = bag.applicationContext!!.getBean(StringConsumerReceiver::class.java)
		return listOf(bean.items as List<String>)
	}

	override fun function(functionName: String, msgs: Flow<List<String>>) = runBlocking {
		F2ClientBuilder
			.get(F2SpringRSocketCucumberConfig.urlBase(bag))
			.functionInl<List<String>, List<String>>(functionName)
			.invoke(msgs)
			.toList()

	}

	override fun consumer(consumerName: String, msgs: Flow<List<String>>): Unit = runBlocking {
		F2ClientBuilder
			.get(F2SpringRSocketCucumberConfig.urlBase(bag))
			.consumerInl<List<String>>(consumerName)
			.invoke(msgs)
	}

	override fun supplier(supplierName: String) = runBlocking {
		F2ClientBuilder
			.get(F2SpringRSocketCucumberConfig.urlBase(bag))
			.supplierInl<List<String>>(supplierName)
			.invoke()
			.toList()
	}

}
