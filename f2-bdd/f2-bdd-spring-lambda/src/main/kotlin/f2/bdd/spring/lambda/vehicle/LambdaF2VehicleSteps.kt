package f2.bdd.spring.lambda.vehicle

import f2.bdd.spring.autoconfigure.steps.FunctionCatalogStepsBase
import io.cucumber.datatable.DataTable

open class LambdaF2VehicleSteps : FunctionCatalogStepsBase<Vehicle, Vehicle>("Vehicle: ") {

	override fun transform(dataTable: DataTable): List<Vehicle> {
		return dataTable.asMaps().map {
			Vehicle(
				name = it[Vehicle::name.name]!!,
				broken = it[Vehicle::broken.name]!!.toBoolean()
			)
		}
	}

	override fun consumerReceiver(): List<Vehicle> {
		return bag.applicationContext!!.getBean(VehicleReceiver::class.java).items
	}
}
