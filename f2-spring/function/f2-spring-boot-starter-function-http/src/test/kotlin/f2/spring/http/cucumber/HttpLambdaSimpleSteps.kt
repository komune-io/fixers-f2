package f2.spring.http.cucumber

import f2.bdd.spring.lambda.list.LambdaList
import f2.bdd.spring.lambda.single.LambdaSimple
import f2.bdd.spring.lambda.single.LambdaSimpleSteps
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.get
import io.cucumber.java8.En
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class HttpLambdaSimpleSteps: LambdaSimpleSteps(), En {

	init {
		prepareLambdaSteps()
	}

	override fun function(values: List<String>): List<String> = runBlocking {
		F2ClientBuilder.get(F2SpringHttpCucumberConfig.urlBase(bag)).function(LambdaList::functionList.name).invoke(values.asFlow()).toList()
	}

	override fun supplier(): List<String> = runBlocking {
		F2ClientBuilder.get(F2SpringHttpCucumberConfig.urlBase(bag)).supplier(LambdaList::supplierList.name).invoke().toList()
	}

	override fun consumer(values: List<String>) = runBlocking {
		F2ClientBuilder.get(F2SpringHttpCucumberConfig.urlBase(bag)).consumer(LambdaList::consumerList.name).invoke(values.asFlow())
	}
}
