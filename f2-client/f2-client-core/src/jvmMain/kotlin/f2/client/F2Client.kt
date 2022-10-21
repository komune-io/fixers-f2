package f2.client

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.util.reflect.TypeInfo

actual interface F2Client {
	actual fun <RESPONSE> supplierGen(route: String, responseTypeInfo: TypeInfo): F2Supplier<RESPONSE>
	actual fun <QUERY, RESPONSE> functionGen(route: String, queryTypeInfo: TypeInfo, responseTypeInfo: TypeInfo): F2Function<QUERY, RESPONSE>
	actual fun <QUERY> consumerGen(route: String, queryTypeInfo: TypeInfo): F2Consumer<QUERY>

	actual val type: F2ClientType
}
