package f2.ssm.model

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("Ssm")
data class Ssm(
		val name: String,
		val transitions: List<SsmTransition>
)

@JsExport
@JsName("SsmSession")
data class SsmSession(
		val ssm: String,
		var session: String,
		val roles: Map<String, String>,
		val publicMessage: String,
		val privateMessage: Map<String, String>
)


@JsExport
@JsName("SsmSessionState")
data class SsmSessionState(
		val ssm: String,
		var session: String,
		val roles: Map<String, String>,
		val publicMessage: String,
		val privateMessage: Map<String, String>,
		var iteration: Int,
		val current: Int,
		val origin: SsmTransition
)


@JsExport
@JsName("SsmTransition")
data class SsmTransition(
		val from: Int,
		val to: Int,
		val role: String,
		val command: String
)

class SsmContext(
	val session: String?,
	val public: String?,
	val private: Map<String, String>?,
	val iteration: Int?,
)

class InvokeReturn(
	val status: String,
	val info: String,
	val transactionId: String
)