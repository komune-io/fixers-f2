package f2.client

import f2.dsl.fnc.F2FunctionDeclaration
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.js.Promise

actual inline fun <reified T, reified R> F2Client.declaration(route: String): F2FunctionDeclaration<T, R> = invokeDeclaration { cmd ->
	this.promise(route, cmd)
}

inline fun <reified T, reified R> F2Client.promise(route: String, cmd: T): Promise<R> {
	val body = Json.encodeToString(cmd)
	val json = invoke(route).invoke(body)
	return json.then{
		jsonToValue(it)
	}
}

fun <E, R> invokeDeclaration(invoke: (e: E) -> Promise<R>) = object : F2FunctionDeclaration<E, R> {
	override fun invoke(cmd: E): Promise<R> {
		return invoke(cmd)
	}
}

fun <T, R> F2FunctionDeclaration<T, R>.execute(cmd: T): Promise<R> {
	return this.invoke(cmd)
}