package f2.spring.exception

import org.springframework.http.HttpStatus

class ForbiddenAccessException(
    message: String
): F2HttpException(
    status = HttpStatus.FORBIDDEN,
    message = message,
    cause = null
)
