package f2.client

import f2.dsl.fnc.F2FunctionDeclaration
import f2.dsl.fnc.declaration
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

actual inline fun <reified T, reified R> F2Client.declaration(route: String): F2FunctionDeclaration<T, R> = declaration { cmd ->
	println("//////////////////////////////////F2Client.declaration ${T::class.simpleName}")
	val body = Json.encodeToString(cmd)
	println("//////////////////////////////////AfterF2Client.declaration $body")
	val ret = invoke(route).invoke(flow { emit(body) })
	println("//////////////////////////////////AfterF2Client.declaration $ret")
	println("//////////////////////////////////AfterF2Client.declaration ${R::class.simpleName}")
	ret.map { json ->
		jsonToValue<R>(json)
	}.first()
}

suspend inline fun <reified T, reified R> F2Client.executeInvoke(route: String, cmd: T): R {
	val body = Json.encodeToString(cmd)
	println("//////////////////////////////////F2Client.executeInvoke ${body}")
	val ret = invoke(route).invoke(flow { emit(body) })
	return ret.map { json ->
		Json {
			ignoreUnknownKeys = true
		}.decodeFromString<List<R>>(json).first()
	}.first()
}
