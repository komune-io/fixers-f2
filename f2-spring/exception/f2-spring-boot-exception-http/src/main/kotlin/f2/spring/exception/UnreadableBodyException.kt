package f2.spring.exception

import f2.dsl.cqrs.exception.F2Exception
import tools.jackson.core.JacksonException

/** A request body the parser could not read: the caller's refusal `http.INVALID_PAYLOAD` ([unreadableBodyError]). */
class UnreadableBodyException(cause: JacksonException) : F2Exception(cause.unreadableBodyError(), cause)
