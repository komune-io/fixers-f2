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
class LambdaF2Vehicle {

    @Bean
    fun fixVehicle(): F2Function<Vehicle, Vehicle> = f2Function { value ->
        value.copy(isBroken = false)
    }

    @Bean
    fun supplierVehicle(): F2Supplier<Vehicle> = listOf(
        Vehicle(name = "Car", isBroken = false),
        Vehicle(name = "Moto", isBroken = true),
        Vehicle(name = "Bike", isBroken = true),
    ).asF2Supplier()

    @Bean
    fun consumerVehicle(receiver: VehicleReceiver): F2Consumer<Vehicle> = f2Consumer { value ->
        receiver.items.add(value)
    }

    @Bean
    fun vehicleReceiver(): VehicleReceiver = VehicleReceiver()

}

@Serializable
data class Vehicle(
    val name: String,
    val isBroken: Boolean
)

class VehicleReceiver(
    override val items: MutableList<Vehicle> = mutableListOf()
) : ConsumerReceiver<Vehicle>
