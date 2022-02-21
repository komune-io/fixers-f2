package f2.client.ktor.rsocket

import f2.client.F2Client
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier

expect class RSocketF2Client : F2Client {
	override fun supplier(route: String): F2Supplier<String>
	override fun function(route: String): F2Function<String, String>
	override fun consumer(route: String): F2Consumer<String>
}
