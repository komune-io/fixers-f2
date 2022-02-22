package f2.client

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.js.Promise

actual inline fun <reified T, reified R> F2Client.declaration(route: String): F2Function<T, R> =
	invokeDeclaration { cmd ->
		this.promise(route, cmd)
	}

inline fun <reified T, reified R> F2Client.promise(route: String, cmd: T): Promise<R> {
	val body = Json.encodeToString(cmd)
	val json = function(route).invoke(body)
	return json.then {
		jsonToValue(it)
	}
}

fun <E, R> invokeDeclaration(invoke: (e: E) -> Promise<R>) = object : F2Function<E, R> {
	override fun invoke(cmd: E): Promise<R> {
		return invoke(cmd)
	}
}

fun <T, R> F2Function<T, R>.execute(cmd: T): Promise<R> {
	return this.invoke(cmd)
}

@JsExport
@JsName("F2Client")
actual external interface F2Client {
	actual fun supplier(route: String): F2Supplier<String>
	actual fun function(route: String): F2Function<String, String>
	actual fun consumer(route: String): F2Consumer<String>
}
