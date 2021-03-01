package f2.client

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface F2Client {
	suspend fun get(route: String): Flow<String>
	suspend fun accept(route: String, command: String)
	suspend fun invoke(route: String, command: String): String
}

suspend inline fun <reified T, reified R> F2Client.executeInvoke(route: String, cmd: T): R {
	val body = Json.encodeToString(cmd)
	val ret = invoke(route, body)
	return jsonToValue(ret)
}

suspend inline fun <reified R> F2Client.executeGet(route: String): Flow<R> {
	val ret = get(route)
	return ret.map { jsonToValue(it) }
}

suspend inline fun <reified T> F2Client.executeAccept(route: String, cmd: T) {
	val body = Json.encodeToString(cmd)
	accept(route, body)
}

inline fun <reified T> jsonToValue(ret: String): T {
	return try {
		Json.decodeFromString(ret)
	} catch (e: Exception) {
		Json.decodeFromString<List<T>>(ret).first()
	}
}