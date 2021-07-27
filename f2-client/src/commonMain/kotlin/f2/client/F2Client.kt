package f2.client

import f2.dsl.fnc.F2FunctionDeclaration
import f2.dsl.fnc.F2SupplierDeclaration
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("F2Client")
interface F2Client {
	fun get(route: String): F2SupplierDeclaration<String>
	//     suspend fun accept(route: String, command: String)
	fun invoke(route: String): F2FunctionDeclaration<String, String>

}

expect inline fun <reified T, reified R> F2Client.declaration(route: String): F2FunctionDeclaration<T, R>

inline fun <reified T> jsonToValue(ret: String): T {
	return try {
		Json {
			ignoreUnknownKeys = true
		}.decodeFromString<T>(ret)
	} catch (e: Exception) {
		Json {
			ignoreUnknownKeys = true
		}.decodeFromString<List<T>>(ret).first()
	}
}