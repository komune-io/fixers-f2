package f2.spring.http.cucumber

import f2.bdd.spring.lambda.single.LambdaSimple
import f2.bdd.spring.lambda.single.LambdaSimpleSteps
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.get
import f2.spring.http.F2SpringRSocketCucumberConfig
import io.cucumber.java8.En
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class HttpLambdaSimpleSteps: LambdaSimpleSteps(), En {

	init {
		prepareLambdaSteps()
	}

	override fun function(values: List<String>): List<String> = runBlocking {
		F2ClientBuilder.get(F2SpringRSocketCucumberConfig.urlBase(bag)).function(LambdaSimple::functionSingle.name).invoke(values.asFlow()).toList()
	}

	override fun supplier(): List<String> = runBlocking {
		F2ClientBuilder.get(F2SpringRSocketCucumberConfig.urlBase(bag)).supplier(LambdaSimple::supplierSingle.name).invoke().toList()
	}

	override fun consumer(values: List<String>) = runBlocking {
		F2ClientBuilder.get(F2SpringRSocketCucumberConfig.urlBase(bag)).consumer(LambdaSimple::consumerSingle.name).invoke(values.asFlow())
	}
}
