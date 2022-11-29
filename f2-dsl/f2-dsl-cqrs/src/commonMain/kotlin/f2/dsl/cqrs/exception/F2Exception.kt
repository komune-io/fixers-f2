package f2.dsl.cqrs.exception

import f2.dsl.cqrs.error.F2Error
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("F2Exception")
open class F2Exception(
	val error: F2Error,
	cause: Throwable? = null
) : RuntimeException(error.message, cause) {
	companion object
}
