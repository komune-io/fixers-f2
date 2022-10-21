package f2.spring.http.cucumber

import f2.bdd.spring.lambda.HttpF2GenericsSteps
import f2.bdd.spring.lambda.vehicle.Vehicle
import f2.bdd.spring.lambda.vehicle.VehicleReceiver
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

class HttpF2VehicleSteps : HttpF2GenericsSteps<Vehicle, Vehicle>("Vehicle: "), En {

	init {
		prepareFunctionCatalogSteps()
	}

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

	override fun function(functionName: String, msgs: Flow<Vehicle>) = runBlocking {
		F2ClientBuilder
			.get(F2SpringRSocketCucumberConfig.urlBase(bag))
			.functionInl<Vehicle, Vehicle>(functionName)
			.invoke(msgs)
			.toList()

	}

	override fun consumer(consumerName: String, msgs: Flow<Vehicle>): Unit = runBlocking {
		F2ClientBuilder.get(F2SpringRSocketCucumberConfig.urlBase(bag)).consumerInl<Vehicle>(consumerName).invoke(msgs)
	}

	override fun supplier(supplierName: String) = runBlocking {
		F2ClientBuilder.get(F2SpringRSocketCucumberConfig.urlBase(bag)).supplierInl<Vehicle>(supplierName).invoke().toList()
	}

}
