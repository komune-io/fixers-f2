package f2.spring.exception

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpStatus

class MessageConverterException(cause: JsonMappingException): F2Exception(
    status = HttpStatus.BAD_REQUEST,
    message = computeMessage(cause),
    cause = cause
) {
    companion object {
        private fun computeMessage(exception: JsonMappingException): String {
            return when (exception) {
                is MissingKotlinParameterException -> "Missing parameter `${exception.parameter.name}`"
                is MismatchedInputException -> "Cannot convert parameter `${exception.path.first().fieldName}` " +
                        "to type `${exception.targetType.simpleName}`"
                else -> exception.message.orEmpty()
            }
        }
    }
}
