package f2.spring.http.cucumber

import f2.bdd.spring.lambda.flow.LambdaFlow
import f2.bdd.spring.lambda.flow.LambdaFlowSteps
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.get
import io.cucumber.java8.En
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class HttpLambdaFlowSteps : LambdaFlowSteps(), En {

	init {
		prepareLambdaSteps()
	}

	override fun function(values: List<String>): List<String> = runBlocking {
		F2ClientBuilder.get("http://localhost:${bag.httpPort}").function(LambdaFlow::functionFlow.name).invoke(values.asFlow()).toList()
	}

	override fun supplier(): List<String> = runBlocking {
		F2ClientBuilder.get("http://localhost:${bag.httpPort}").supplier(LambdaFlow::supplierFlow.name).invoke().toList()
	}

	override fun consumer(values: List<String>) = runBlocking {
		F2ClientBuilder.get("http://localhost:${bag.httpPort}").consumer(LambdaFlow::consumerFlow.name).invoke(values.asFlow())
	}
}
