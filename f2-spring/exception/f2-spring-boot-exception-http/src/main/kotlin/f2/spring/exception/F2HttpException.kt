package f2.spring.exception

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.exception.F2Exception
import java.time.LocalDate
import java.util.UUID
import org.springframework.http.HttpStatus

/**
 * An error answered with [status].
 *
 * With a [refusal], it is a refusal: the request broke the rule that code names, and the caller is told so with
 * [message], [parameters] — the values the message names — and [citers] — the objects whose citing keeps the
 * refused one alive. Without one, it is a failure the caller learns only the status of.
 */
@Suppress("LongParameterList") // the status, then one parameter per field of the error it raises
open class F2HttpException(
    val status: HttpStatus,
    code: Int = status.value(),
    message: String,
    cause: Throwable?,
    refusal: String? = null,
    parameters: Map<String, String> = emptyMap(),
    citers: List<String> = emptyList(),
): F2Exception(
    error = F2Error(
        id = UUID.randomUUID().toString(),
        timestamp = LocalDate.now().toString(),
        message = message,
        code = code,
        refusal = refusal,
        parameters = parameters,
        citers = citers,
    ),
    cause = cause
)
