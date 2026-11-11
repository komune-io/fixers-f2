package f2.spring.exception

import f2.dsl.cqrs.error.F2Refusal
import f2.dsl.cqrs.error.HttpRefusal
import org.springframework.http.HttpStatus

/**
 * The refusal `http.CONFLICT` of an [entity] whose [property] already holds [value], naming the three in its
 * parameters `entity`, `property` and `value`.
 */
class ConflictException(
    val entity: String,
    val property: String,
    val value: String,
): F2HttpException(
    status = HttpStatus.CONFLICT,
    message = "$entity with $property [$value] already exists",
    refusal = F2Refusal(
        code = HttpRefusal.CONFLICT.refusal,
        parameters = mapOf(ENTITY_PARAMETER to entity, PROPERTY_PARAMETER to property, VALUE_PARAMETER to value),
    ),
)
