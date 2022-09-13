package f2.spring.exception.http.cucumber

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import f2.bdd.spring.lambda.vehicle.Vehicle
import f2.bdd.spring.lambda.vehicle.VehicleReceiver
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.get
import f2.spring.exception.http.F2SpringExceptionsHttpCucumberConfig
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class ExceptionsHttpF2ExceptionSteps: ExceptionsHttpF2GenericsStepsBase<Any, Any>("Exceptions: "), En {

	init {
		prepareFunctionCatalogSteps()
	}

	private val objectMapper = jacksonObjectMapper()

	override fun transform(dataTable: DataTable): List<Any> {
		return dataTable.asMaps()
	}

	override fun consumerReceiver(): List<Vehicle> {
		val bean = bag.applicationContext!!.getBean(VehicleReceiver::class.java)
		return bean.items
	}

	override fun function(table: DataTable, functionName: String) = runBlocking {
		val json = transform(table)
			.asFlow()
			.map(::toJson)

		F2ClientBuilder
			.get(F2SpringExceptionsHttpCucumberConfig.urlBase(bag))
			.function(functionName)
			.invoke(json)
			.toList()

	}

	override fun consumer(json: Flow<String>, consumerName: String): Unit = runBlocking {
		F2ClientBuilder.get(F2SpringExceptionsHttpCucumberConfig.urlBase(bag)).consumer(consumerName).invoke(json)
	}

	override fun supplier(supplierName: String) = runBlocking {
		F2ClientBuilder.get(F2SpringExceptionsHttpCucumberConfig.urlBase(bag)).supplier(supplierName).invoke().toList()
	}

	override fun fromJson(msg: String): Vehicle {
		return objectMapper.readValue(msg)
	}

	override fun toJson(msg: Any): String {
		return objectMapper.writeValueAsString(msg)
	}
}
