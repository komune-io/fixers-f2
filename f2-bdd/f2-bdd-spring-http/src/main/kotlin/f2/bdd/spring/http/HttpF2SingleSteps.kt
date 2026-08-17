package f2.bdd.spring.http

import f2.client.consumerSingle
import f2.client.ktor.F2ClientBuilder
import f2.client.supplierSingle
import io.cucumber.java8.En
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class HttpF2SingleSteps : StringHttpF2Steps("Single: "), En {

	init {
		prepareFunctionCatalogSteps()
	}

	override fun consumer(consumerName: String, msgs: Flow<String>): Unit = runBlocking {
		F2ClientBuilder
			.get(urlBase())
			.consumerSingle<String>(consumerName)
			.invoke(msgs)
	}

	override fun supplier(supplierName: String) = runBlocking {
		F2ClientBuilder
			.get(urlBase())
			.supplierSingle<String>(supplierName)
			.invoke()
			.toList()
	}
}
