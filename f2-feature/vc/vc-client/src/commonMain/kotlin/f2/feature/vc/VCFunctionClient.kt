package f2.feature.vc

import f2.client.F2Client
import f2.vc.VC
import f2.vc.VCFunction
import f2.vc.VCSignCommand
import f2.vc.VCVerifyCommand
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

open class VCFunctionClient<T> (
	private val client: F2Client
): VCFunction<T>  {

	override suspend fun sign(cmd: VCSignCommand<T>): VC<T> {
		val body = Json.encodeToString(cmd)
		val ret = client.invoke(this::sign.name, body)
		return jsonToValued(ret)
	}

	override suspend fun verify(cmd: VCVerifyCommand<T>): Boolean {
		val body = Json.encodeToString(cmd)
		val ret = client.invoke(this::sign.name, body)
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
