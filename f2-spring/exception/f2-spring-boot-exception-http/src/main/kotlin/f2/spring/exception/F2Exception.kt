package f2.spring.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

open class F2Exception(
    status: HttpStatus,
    val code: Int = status.value(),
    message: String,
    cause: Throwable?
): ResponseStatusException(status, message, cause)
