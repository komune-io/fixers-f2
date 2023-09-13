package f2.spring.exception

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.exception.F2Exception
import org.springframework.http.HttpStatus
import java.time.LocalDate
import java.util.UUID

open class F2HttpException(
    val status: HttpStatus,
    code: Int = status.value(),
    message: String,
    cause: Throwable?
): F2Exception(
    error = F2Error(
        id = UUID.randomUUID().toString(),
        timestamp = LocalDate.now().toString(),
        message = message,
        code = code,
    ),
    cause = cause
)
