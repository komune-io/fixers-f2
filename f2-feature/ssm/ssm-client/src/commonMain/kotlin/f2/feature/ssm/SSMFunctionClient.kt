package f2.feature.ssm

import f2.client.F2Client
import f2.ssm.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

open class SSMFunctionClient<T> (
	private val client: F2Client
): SSMFunction {

	override suspend fun perform(cmd: SsmPerformCommand): SsmPerformedEvent {
		val body = Json.encodeToString(cmd)
		val ret = client.invoke(this::perform.name, body)
		return jsonToValued(ret)
	}

	override suspend fun start(cmd: SsmStartCommand): SsmStartedEvent {
		val body = Json.encodeToString(cmd)
		val ret = client.invoke(this::start.name, body)
		return jsonToValued(ret)
	}

	override suspend fun init(cmd: SsmInitCommand): SsmInitedEvent {
		val body = Json.encodeToString(cmd)
		val ret = client.invoke(this::init.name, body)
		return jsonToValued(ret)
	}

	private inline fun <reified T> jsonToValued(ret: String): T {
		return try {
			Json.decodeFromString(ret)
		} catch (e: Exception) {
			Json.decodeFromString<List<T>>(ret).first()
		}
	}

}
