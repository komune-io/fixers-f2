package f2.spring.exception

import org.springframework.http.HttpStatus

class ConflictException(
    val entity: String,
    val property: String,
    val value: String
): F2Exception(
    status = HttpStatus.CONFLICT,
    message = "$entity with $property [$value] already exists",
    cause = null
)
