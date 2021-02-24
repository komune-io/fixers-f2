package f2.client

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface F2Client {
	suspend fun invoke(route: String, command: String): String
}

suspend inline fun <reified T, reified R> F2Client.execute(route: String, cmd: T): R {
	val body = Json.encodeToString(cmd)
	val ret = invoke(route, body)
	return jsonToValue(ret)
}

inline fun <reified T> jsonToValue(ret: String): T {
	return try {
		Json.decodeFromString(ret)
	} catch (e: Exception) {
		Json.decodeFromString<List<T>>(ret).first()
	}
}