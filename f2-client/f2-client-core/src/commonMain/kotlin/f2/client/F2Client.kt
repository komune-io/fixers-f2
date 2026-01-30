package f2.client

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.typeInfo

enum class F2ClientType {
	HTTP
}
expect interface F2Client {
	val type: F2ClientType
	fun <RESPONSE> supplier(route: String, responseTypeInfo: TypeInfo): F2Supplier<RESPONSE>
	fun <QUERY, RESPONSE> function(
		route: String, queryTypeInfo: TypeInfo, responseTypeInfo: TypeInfo
	): F2Function<QUERY, RESPONSE>
	fun <QUERY> consumer(route: String, queryTypeInfo: TypeInfo): F2Consumer<QUERY>

}

inline fun <reified RESPONSE> F2Client.supplier(route: String): F2Supplier<RESPONSE> {
	val typeInfo = getTypeInfo<RESPONSE>()
	return supplier(route, typeInfo)
}
inline fun <reified RESPONSE> F2Client.supplierSingle(route: String): F2Supplier<RESPONSE> {
	val typeInfo = typeInfo<RESPONSE>()
	return supplier(route, typeInfo)
}

inline fun <reified QUERY, reified RESPONSE> F2Client.function(route: String): F2Function<QUERY, RESPONSE> {
	val queryTypeInfo = getTypeInfo<QUERY>()
	val responseTypeInfo = getTypeInfo<RESPONSE>()
	return function(route, queryTypeInfo, responseTypeInfo)
}

inline fun <reified QUERY> F2Client.consumer(route: String): F2Consumer<QUERY> {
	val typeInfo = getTypeInfo<QUERY>()
	return consumer(route, typeInfo)
}
inline fun <reified QUERY> F2Client.consumerSingle(route: String): F2Consumer<QUERY> {
	val typeInfo = typeInfo<QUERY>()
	return consumer(route, typeInfo)
}

inline fun <reified DATA> F2Client.getTypeInfo(): TypeInfo {
	return if (type == F2ClientType.HTTP) {
		typeInfo<List<DATA>>()
	} else {
		typeInfo<DATA>()
	}
}
