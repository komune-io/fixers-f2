package f2.dsl.cqrs.exception

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.F2ErrorDTO
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.datetime.Clock

@JsExport
@JsName("F2Exception")
open class F2Exception(
	val error: F2ErrorDTO,
	cause: Throwable? = null
) : RuntimeException(error.message, cause) {
	companion object {
		operator fun invoke(message: String, id: String = "", requestId: String = "", code: Int = 500, cause: Throwable? = null) = F2Exception(
			error = F2Error(
				message = message,
				requestId = requestId,
				code = code,
				timestamp = Clock.System.now().toString(),
				id = id
			),
			cause = cause
		)
	}
}
