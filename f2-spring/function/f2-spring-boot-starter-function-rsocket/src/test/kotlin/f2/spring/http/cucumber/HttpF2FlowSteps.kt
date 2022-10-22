package f2.spring.http.cucumber

import f2.bdd.spring.lambda.HttpF2GenericsSteps
import f2.bdd.spring.lambda.single.StringConsumerReceiver
import f2.client.consumer
import f2.client.function
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.get
import f2.client.supplier
import f2.spring.http.F2SpringRSocketCucumberConfig
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class HttpF2FlowSteps : HttpF2GenericsSteps<String, String>("Flow: "), En {

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

	override fun function(functionName: String, msgs: Flow<String>) = runBlocking {
		F2ClientBuilder
			.get(F2SpringRSocketCucumberConfig.urlBase(bag))
			.function<String, String>(functionName)
			.invoke(msgs)
			.toList()

	}

	override fun consumer(consumerName: String, msgs: Flow<String>): Unit = runBlocking {
		F2ClientBuilder.get(F2SpringRSocketCucumberConfig.urlBase(bag)).consumer<String>(consumerName).invoke(msgs)
	}

	override fun supplier(supplierName: String) = runBlocking {
		F2ClientBuilder.get(F2SpringRSocketCucumberConfig.urlBase(bag)).supplier<String>(supplierName).invoke().toList()
	}

}
