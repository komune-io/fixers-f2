package f2.bdd.spring.lambda.vehicle

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import f2.dsl.fnc.asF2Supplier
import f2.dsl.fnc.f2Consumer
import f2.dsl.fnc.f2Function
import kotlinx.serialization.Serializable
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

@Serializable
data class Vehicle(
	val name: String,
	val broken: Boolean
)

class VehicleReceiver(
	override val items: MutableList<Vehicle> = mutableListOf()
) : ConsumerReceiver<Vehicle>
