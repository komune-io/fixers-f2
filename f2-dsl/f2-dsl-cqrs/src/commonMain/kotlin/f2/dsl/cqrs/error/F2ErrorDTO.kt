package f2.dsl.cqrs.error

import f2.dsl.cqrs.Problem
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 * An error, as a caller receives it.
 *
 * [code] is the HTTP status. [refusal] says whether the error was written for the caller: a refusal names the
 * rule the request broke, and only a refusal discloses the [message] and its own values. Any other error is an
 * internal failure whose message stays on the server.
 */
@JsExport
@JsName("F2ErrorDTO")
interface F2ErrorDTO: Problem {
    /** The id the server gave this error, or `null`: an error body carries none, and a client never invents one. */
    val id: String?
    val timestamp: String
    val code: Int
    val requestId: String?
    val message: String

    /**
     * The rule the request broke, or `null` when the error is not a refusal — on the server and in a client alike;
     * [refusalCode] is then the meaning of its status ([HttpRefusal]), the code its error body carries.
     */
    val refusal: F2RefusalDTO?
}

/** The rule a request broke, with the values a caller words it from. */
@JsExport
@JsName("F2RefusalDTO")
interface F2RefusalDTO {
    /**
     * The rule, as `<domain>.<NAME>`: a stable code a caller tells two errors apart by, and never reused for
     * another meaning.
     */
    val code: String

    /**
     * The values the refusal names — an alias, a file name, the field a body could not be read at — for a caller
     * to word it itself. They are the caller's own values.
     */
    val parameters: Map<String, String>

    /** The objects whose citing keeps the refused one alive, by name. */
    val citers: List<String>
}

@JsExport
@JsName("F2Refusal")
data class F2Refusal(
    override val code: String,
    override val parameters: Map<String, String> = emptyMap(),
    override val citers: List<String> = emptyList(),
) : F2RefusalDTO

// Not @Serializable: the wire body is flat (`refusal` is the code beside `message`, `parameters`, `citers`), and
// both ends write and read it by hand — a serializer would write another shape.
@JsExport
@JsName("F2Error")
@OptIn(ExperimentalTime::class)
open class F2Error(
    override val message: String,
    override val id: String? = null,
    override val timestamp: String = Clock.System.now().toString(),
    override val code: Int = HttpRefusal.UNEXPECTED.status,
    override val requestId: String? = null,
    override val refusal: F2Refusal? = null,
) : F2ErrorDTO {
    override fun toString(): String {
        return "F2Error(timestamp='$timestamp', code=$code, requestId='${requestId ?: "null"}', " +
            "refusal=${refusal?.code ?: "null"}, message='$message')"
    }
}
