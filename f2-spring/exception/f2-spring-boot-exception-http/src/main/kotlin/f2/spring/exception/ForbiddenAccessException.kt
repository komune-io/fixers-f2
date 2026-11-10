package f2.spring.exception

import f2.dsl.cqrs.error.F2Refusal
import f2.dsl.cqrs.error.HttpRefusal
import org.springframework.http.HttpStatus

/** The refusal `http.FORBIDDEN`, telling the caller [message]: a sentence written for it. */
class ForbiddenAccessException(
    message: String,
): F2HttpException(
    status = HttpStatus.FORBIDDEN,
    message = message,
    refusal = F2Refusal(code = HttpRefusal.FORBIDDEN.refusal),
)
