package f2.client

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.util.reflect.TypeInfo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.js.Promise

@JsExport
@JsName("F2Client")
actual external interface F2Client {
	actual fun <RESPONSE> supplier(route: String, responseTypeInfo: TypeInfo): F2Supplier<RESPONSE>
	actual fun <QUERY, RESPONSE> function(route: String, queryTypeInfo: TypeInfo, responseTypeInfo: TypeInfo): F2Function<QUERY, RESPONSE>
	actual fun <QUERY> consumer(route: String, queryTypeInfo: TypeInfo): F2Consumer<QUERY>

	actual val type: F2ClientType

}
