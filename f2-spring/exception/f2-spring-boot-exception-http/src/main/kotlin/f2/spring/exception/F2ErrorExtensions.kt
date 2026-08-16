package f2.spring.exception

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.F2ErrorDTO
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
 * Builds the [F2Error] reported when a required request parameter is missing.
 */
fun missingParameterError(name: String): F2Error = F2Error(
    id = UUID.randomUUID().toString(),
    timestamp = System.currentTimeMillis().toString(),
    message = "Missing parameter `$name`",
    code = 400,
)
