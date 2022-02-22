package f2.client

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import f2.dsl.fnc.f2Function
import f2.dsl.fnc.invoke
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val jsonSerializer = Json {
	ignoreUnknownKeys = true
}

actual inline fun <reified T, reified R> F2Client.declaration(route: String): F2Function<T, R> = f2Function { cmd ->
	val body = jsonSerializer.encodeToString(cmd)
	val ret = function(route).invoke(body)
	jsonToValue(ret)
}

suspend inline fun <reified T, reified R> F2Client.executeInvoke(route: String, cmd: T): R {
	val body = jsonSerializer.encodeToString(cmd)
	val json = function(route).invoke(body)
	return try {
		jsonSerializer.decodeFromString<R>(json)
	} catch (e: Exception) {
		json as R
	}
}

actual interface F2Client {
	actual fun supplier(route: String): F2Supplier<String>
	actual fun function(route: String): F2Function<String, String>
	actual fun consumer(route: String): F2Consumer<String>
}
