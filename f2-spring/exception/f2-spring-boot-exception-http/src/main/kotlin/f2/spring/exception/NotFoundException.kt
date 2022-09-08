package f2.spring.exception

import org.springframework.http.HttpStatus

class NotFoundException(
    val name: String,
    val id: String
): F2Exception(
    status = HttpStatus.NOT_FOUND,
    message = "$name [$id] not found",
    cause = null
)
