package f2.client

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.typeInfo

enum class F2ClientType {
	HTTP, RSOCKET
}
expect interface F2Client {
	val type: F2ClientType
	fun <RESPONSE> supplierGen(route: String, responseTypeInfo: TypeInfo): F2Supplier<RESPONSE>
	fun <QUERY, RESPONSE> functionGen(route: String, queryTypeInfo: TypeInfo, responseTypeInfo: TypeInfo): F2Function<QUERY, RESPONSE>
	fun <QUERY> consumerGen(route: String, queryTypeInfo: TypeInfo): F2Consumer<QUERY>

}

inline fun <reified RESPONSE> F2Client.supplierInl(route: String): F2Supplier<RESPONSE> {
	val typeInfo = if(type == F2ClientType.HTTP) {
		typeInfo<List<RESPONSE>>()
	} else {
		typeInfo<RESPONSE>()
	}
	return supplierGen(route, typeInfo)
}

inline fun <reified QUERY, reified RESPONSE> F2Client.functionInl(route: String): F2Function<QUERY, RESPONSE> {
	val queryTypeInfo =  if(type == F2ClientType.HTTP) {
		typeInfo<List<QUERY>>()
	} else {
		typeInfo<QUERY>()
	}
	val responseTypeInfo = if(type == F2ClientType.HTTP) {
		typeInfo<List<RESPONSE>>()
	} else {
		typeInfo<RESPONSE>()
	}
	return functionGen(route, queryTypeInfo, responseTypeInfo)
}

inline fun <reified QUERY> F2Client.consumerInl(route: String): F2Consumer<QUERY> {
	val typeInfo = if(type == F2ClientType.HTTP) {
		typeInfo<List<QUERY>>()
	} else {
		typeInfo<QUERY>()
	}
	return consumerGen(route, typeInfo)
}
