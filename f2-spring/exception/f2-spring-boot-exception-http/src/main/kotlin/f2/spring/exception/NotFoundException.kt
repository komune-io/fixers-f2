package f2.spring.exception

import org.springframework.http.HttpStatus

class NotFoundException(
    val name: String,
    id: String
): F2HttpException(
    status = HttpStatus.NOT_FOUND,
    message = "$name [$id] not found",
    cause = null
)
