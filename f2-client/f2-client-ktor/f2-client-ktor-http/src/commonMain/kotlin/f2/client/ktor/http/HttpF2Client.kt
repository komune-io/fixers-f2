package f2.client.ktor.http

import f2.client.F2Client
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier

expect open class HttpF2Client : F2Client {
	override fun get(route: String): F2Supplier<String>
	override fun invoke(route: String): F2Function<String, String>
}
