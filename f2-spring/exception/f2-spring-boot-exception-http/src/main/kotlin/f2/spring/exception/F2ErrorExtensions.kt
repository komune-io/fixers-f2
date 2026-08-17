package f2.spring.exception

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.F2ErrorDTO
import f2.dsl.cqrs.exception.F2Exception
import java.util.UUID

/**
 * Maps an [F2ErrorDTO] to the attribute map exposed in HTTP error responses.
 */
fun F2ErrorDTO.toAttributeMap(): Map<String, Any?> = mapOf(
    F2Error::id.name to id,
    F2Error::code.name to code,
    F2Error::message.name to message,
    F2Error::timestamp.name to timestamp,
)

/**
 * Finds the [F2Exception] carried by this throwable, either directly or as its immediate cause.
 *
 * Handlers that run behind a framework wrapper (Spring wraps handler failures before they reach the
 * error attributes) only ever see one level of wrapping, so this deliberately does not walk the
 * whole cause chain: a deeper [F2Exception] is an unrelated internal failure, not the error the
 * endpoint meant to report.
 */
fun Throwable?.findF2Exception(): F2Exception? = this as? F2Exception ?: this?.cause as? F2Exception

/**
 * Builds the [F2Error] reported when a required request parameter is missing.
 */
fun missingParameterError(name: String): F2Error = F2Error(
    id = UUID.randomUUID().toString(),
    timestamp = System.currentTimeMillis().toString(),
    message = "Missing parameter `$name`",
    code = 400,
)
