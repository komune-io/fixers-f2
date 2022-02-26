package f2.spring.http.cucumber

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import f2.bdd.spring.lambda.HttpF2GenericsStepsBase
import f2.bdd.spring.lambda.vehicle.Vehicle
import f2.bdd.spring.lambda.vehicle.VehicleReceiver
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


class HttpF2VehicleSteps : HttpF2GenericsStepsBase<Vehicle, Vehicle>("Vehicle: "), En {

	init {
		prepareFunctionCatalogSteps()
	}

	private val objectMapper = jacksonObjectMapper()

	override fun transform(dataTable: DataTable): List<Vehicle> {
		return dataTable.asMaps().map {
			Vehicle(
				name = it[Vehicle::name.name]!!,
				broken = it[Vehicle::broken.name]!!.toBoolean()
			)
		}
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

	override fun fromJson(msg: String): Vehicle {
		return objectMapper.readValue(msg)
	}

	override fun toJson(msg: Vehicle): String {
		return objectMapper.writeValueAsString(msg)
	}
}
