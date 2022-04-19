package f2.client

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

expect interface F2Client {
	fun supplier(route: String): F2Supplier<String>
	fun function(route: String): F2Function<String, String>
	fun consumer(route: String): F2Consumer<String>
}

val jsonF2Config = Json {
	ignoreUnknownKeys = true
}

inline fun <reified T> jsonToValue(ret: String): T {
	return try {
		jsonF2Config.decodeFromString<T>(ret)
	} catch (e: Exception) {
		jsonF2Config.decodeFromString<List<T>>(ret).first()
	}
}
