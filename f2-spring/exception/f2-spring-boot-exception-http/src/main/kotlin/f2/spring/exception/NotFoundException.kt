package f2.spring.exception

import f2.dsl.cqrs.error.F2Refusal
import f2.dsl.cqrs.error.HttpRefusal
import org.springframework.http.HttpStatus

/** The refusal `http.NOT_FOUND` of the object [name] [id], naming both in its parameters `entity` and `id`. */
class NotFoundException(
    val name: String,
    val id: String,
): F2HttpException(
    status = HttpStatus.NOT_FOUND,
    message = "$name [$id] not found",
    refusal = F2Refusal(
        code = HttpRefusal.NOT_FOUND.refusal,
        parameters = mapOf(ENTITY_PARAMETER to name, ID_PARAMETER to id),
    ),
)
