package f2.bdd.spring.lambda.vehicle

import f2.bdd.spring.autoconfigure.steps.FunctionCatalogStepsBase
import io.cucumber.datatable.DataTable

open class LambdaF2VehicleSteps : FunctionCatalogStepsBase<Vehicle, Vehicle>("Vehicle: ") {

    override fun transform(dataTable: DataTable): List<Vehicle> {
        return dataTable.asMaps().map { row ->
            Vehicle(
                name = requireNotNull(row.getValue(Vehicle::name.name)),
                isBroken = row.getValue(Vehicle::isBroken.name).toBoolean()
            )
        }
    }

    @Suppress("MissingUseCall") // the scenario owns the context: F2SpringStep's After hook stops it
    override fun consumerReceiver(): List<Vehicle> =
        bag.requireApplicationContext().getBean(VehicleReceiver::class.java).items
}
