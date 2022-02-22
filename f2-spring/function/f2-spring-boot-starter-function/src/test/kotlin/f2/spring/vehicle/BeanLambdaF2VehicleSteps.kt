package f2.spring.vehicle

import f2.bdd.spring.lambda.vehicle.LambdaF2VehicleSteps
import io.cucumber.java8.En

class BeanLambdaF2VehicleSteps : LambdaF2VehicleSteps(), En {

	init {
		prepareFunctionCatalogSteps()
	}
}
