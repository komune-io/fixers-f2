package f2.spring.vehicle

import f2.dsl.fnc.*
import f2.spring.single.LambdaPureKotlinReceiver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class LambdaF2Vehicle {

	@Bean
	open fun fixVehicle(): F2Function<Vehicle, Vehicle> = f2Function { value ->
		value.copy(broken = false)
	}

	@Bean
	open fun supplierVehicle(): F2Supplier<Vehicle> = listOf(
		Vehicle(name = "Car", broken = false),
		Vehicle(name = "Moto", broken = true),
		Vehicle(name = "Bike", broken = true),
	).asF2Supplier()

	@Bean
	open fun consumerVehicle(receiver: VehicleReceiver): F2Consumer<Vehicle> = f2Consumer { value ->
		receiver.items.add(value)
	}

	@Bean
	open fun vehicleReceiver(): VehicleReceiver = VehicleReceiver()

}

data class Vehicle(
	val name: String,
	val broken: Boolean
)

data class VehicleReceiver(
	val items: MutableList<Vehicle> = mutableListOf()
)
