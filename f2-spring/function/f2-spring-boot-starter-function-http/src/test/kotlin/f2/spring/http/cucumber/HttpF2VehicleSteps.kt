package f2.spring.http.cucumber

import f2.bdd.spring.lambda.HttpF2GenericsSteps
import f2.bdd.spring.lambda.vehicle.Vehicle
import f2.bdd.spring.lambda.vehicle.VehicleReceiver
import f2.client.consumer
import f2.client.function
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.get
import f2.client.supplier
import f2.spring.http.F2SpringHttpCucumberConfig
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
			.get(F2SpringHttpCucumberConfig.urlBase(bag))
			.function<Vehicle, Vehicle>(functionName)
			.invoke(msgs)
			.toList()

	}

	override fun consumer(consumerName: String, msgs: Flow<Vehicle>): Unit = runBlocking {
		F2ClientBuilder
			.get(F2SpringHttpCucumberConfig.urlBase(bag))
			.consumer<Vehicle>(consumerName)
			.invoke(msgs)
	}

	override fun supplier(supplierName: String) = runBlocking {
		F2ClientBuilder
			.get(F2SpringHttpCucumberConfig.urlBase(bag))
			.supplier<Vehicle>(supplierName)
			.invoke()
			.toList()
	}

}
