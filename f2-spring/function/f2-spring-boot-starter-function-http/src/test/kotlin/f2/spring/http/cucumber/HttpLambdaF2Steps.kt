package f2.spring.http.cucumber

import f2.bdd.spring.lambda.f2.LambdaF2
import f2.bdd.spring.lambda.f2.LambdaF2Steps
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.get
import io.cucumber.java8.En
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class HttpLambdaF2Steps : LambdaF2Steps(), En {

	init {
		prepareLambdaSteps()
	}

	override fun function(values: List<String>): List<String> = runBlocking {
		F2ClientBuilder.get(F2SpringHttpCucumberConfig.urlBase(bag)).function("functionF2").invoke(values.asFlow()).toList()
	}

	override fun supplier(): List<String> = runBlocking {
		F2ClientBuilder.get(F2SpringHttpCucumberConfig.urlBase(bag)).supplier("supplierF2").invoke().toList()
	}

	override fun consumer(values: List<String>) = runBlocking {
		F2ClientBuilder.get(F2SpringHttpCucumberConfig.urlBase(bag)).consumer(LambdaF2::consumerF2.name).invoke(values.asFlow())
	}

}
