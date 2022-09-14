package f2.client.ktor.http

import f2.client.F2Client
import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.exception.F2Exception
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive

expect open class HttpF2Client : F2Client {
	override fun supplier(route: String): F2Supplier<String>
	override fun function(route: String): F2Function<String, String>
	override fun consumer(route: String): F2Consumer<String>
}
