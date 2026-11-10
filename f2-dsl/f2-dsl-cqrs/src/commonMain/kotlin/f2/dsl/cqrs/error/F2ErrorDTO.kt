package f2.dsl.cqrs.error

import f2.dsl.cqrs.Problem
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.serialization.Serializable

/**
 * An error, as a caller receives it.
 *
 * [code] is the HTTP status. [refusal] says whether the error was written for the caller: a refusal names the
 * rule the request broke, and only a refusal discloses its [message], [parameters] and [citers]. Any other error
 * is an internal failure whose message stays on the server.
 */
@JsExport
@JsName("F2ErrorDTO")
interface F2ErrorDTO: Problem {
	val id: String?
	val timestamp: String
	val code: Int
	val requestId: String?
	val message: String

	/**
	 * The rule the request broke, as `<domain>.<NAME>`: a stable code a caller tells two errors apart by, and
	 * never reused for another meaning. `null` when the error is not a refusal — on the server and in a client
	 * alike; [refusalCode] is then the meaning of its status ([HttpRefusal]), the code its error body carries.
	 */
	val refusal: String?

	/**
	 * The values the error names — an alias, a file name, the field a body could not be read at — for a caller to
	 * word it itself. They are the caller's own values, and travel with any error that gives them.
	 */
	val parameters: Map<String, String>

	/** The objects whose citing keeps the refused one alive, by name. */
	val citers: List<String>
}

@Serializable
@JsExport
@JsName("F2Error")
@Suppress("LongParameterList") // one parameter per F2ErrorDTO field
@OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
open class F2Error(
    override val message: String,
    override val id: String? = Uuid.random().toString(),
    override val timestamp: String = Clock.System.now().toString(),
    override val code: Int = 500,
    override val requestId: String? = null,
    override val refusal: String? = null,
    override val parameters: Map<String, String> = emptyMap(),
    override val citers: List<String> = emptyList(),
) : F2ErrorDTO {
	override fun toString(): String {
		return "F2Error(timestamp='$timestamp', code=$code, requestId='$requestId', refusal=$refusal, " +
			"message='$message')"
	}
}
