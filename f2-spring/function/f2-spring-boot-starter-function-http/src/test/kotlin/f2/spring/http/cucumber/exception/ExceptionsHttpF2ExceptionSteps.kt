package f2.spring.http.cucumber.exception

import f2.bdd.spring.lambda.single.StringConsumerReceiver
import f2.client.consumerInl
import f2.client.functionInl
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.get
import f2.client.supplierInl
import f2.spring.http.F2SpringHttpCucumberConfig
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class ExceptionsHttpF2ExceptionSteps: ExceptionsHttpF2GenericsStepsBase<MutableMap<String, String>, String>("Exceptions: "), En {

	init {
		prepareFunctionCatalogSteps()
	}
	override fun transform(dataTable: DataTable): List<MutableMap<String, String>> {
		return dataTable.asMaps()
	}

	override fun consumerReceiver(): List<String> {
		val bean = bag.applicationContext!!.getBean(StringConsumerReceiver::class.java)
		return bean.items
	}

	override fun function(functionName: String, msgs: Flow<MutableMap<String, String>>) = runBlocking {
		F2ClientBuilder
			.get(F2SpringHttpCucumberConfig.urlBase(bag))
			.functionInl<MutableMap<String, String>, String>(functionName)
			.invoke(msgs)
			.toList()

	}

	override fun consumer(consumerName: String, msgs: Flow<MutableMap<String, String>>): Unit = runBlocking {
		F2ClientBuilder
			.get(F2SpringHttpCucumberConfig.urlBase(bag))
			.consumerInl<MutableMap<String, String>>(consumerName)
			.invoke(msgs)
	}

	override fun supplier(supplierName: String) = runBlocking {
		F2ClientBuilder.get(F2SpringHttpCucumberConfig.urlBase(bag)).supplierInl<String>(supplierName).invoke().toList()
	}

}
