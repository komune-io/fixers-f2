package f2.client

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier

actual interface F2Client {
	actual fun supplier(route: String): F2Supplier<String>
	actual fun function(route: String): F2Function<String, String>
	actual fun consumer(route: String): F2Consumer<String>
}
