package f2.spring.vehicle

import f2.spring.step.FunctionCatalogStepsBase
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En

class LambdaF2VehicleSteps : FunctionCatalogStepsBase<Vehicle, Vehicle>("Vehicle: "), En {

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
		return bag.applicationContext!!.getBean(VehicleReceiver::class.java).items
	}
}
