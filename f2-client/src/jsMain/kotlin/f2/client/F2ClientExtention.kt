package f2.client

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.js.Promise

inline fun <reified T, reified R> F2Client.promise(route: String, cmd: T): Promise<R> = GlobalScope.promise {
	val body = Json.encodeToString(cmd)
	val ret = invoke(route, body)
	jsonToValue(ret)
}