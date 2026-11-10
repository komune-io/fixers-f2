package f2.spring.exception

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.F2Refusal
import f2.dsl.cqrs.exception.F2Exception
import java.time.Instant
import java.util.UUID
import org.springframework.http.HttpStatus

/**
 * An error answered with [status].
 *
 * With a [refusal], it is a refusal: the request broke the rule it names, and the caller is told so with [message]
 * and the refusal's values. Without one, it is a failure the caller learns only the status of.
 */
open class F2HttpException(
    status: HttpStatus,
    message: String,
    cause: Throwable? = null,
    refusal: F2Refusal? = null,
): F2Exception(
    error = F2Error(
        id = UUID.randomUUID().toString(),
        timestamp = Instant.now().toString(),
        message = message,
        code = status.value(),
        refusal = refusal,
    ),
    cause = cause
) {
    /** The status this error answers, the one its error's code names. */
    val status: HttpStatus get() = error.httpStatus()
}
