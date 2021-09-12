package f2.client

import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

expect interface F2Client {
	fun get(route: String): F2Supplier<String>

	//     suspend fun accept(route: String, command: String)
	fun invoke(route: String): F2Function<String, String>

}

expect inline fun <reified T, reified R> F2Client.declaration(route: String): F2Function<T, R>

val json = Json {
	ignoreUnknownKeys = true
}

inline fun <reified T> jsonToValue(ret: String): T {
	return try {
		json.decodeFromString<T>(ret)
	} catch (e: Exception) {
		json.decodeFromString<List<T>>(ret).first()
	}
}
