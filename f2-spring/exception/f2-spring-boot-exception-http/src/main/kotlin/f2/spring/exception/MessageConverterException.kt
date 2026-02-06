package f2.spring.exception

import org.springframework.http.HttpStatus
import tools.jackson.databind.DatabindException
import tools.jackson.databind.exc.MismatchedInputException

class MessageConverterException(cause: DatabindException): F2HttpException(
    status = HttpStatus.BAD_REQUEST,
    message = computeMessage(cause),
    cause = cause
) {
    companion object {
        private fun computeMessage(exception: DatabindException): String {
            return when (exception) {
                is MismatchedInputException -> "Cannot convert parameter `${exception.path.first().propertyName}` " +
                        "to type `${exception.targetType.simpleName}`"
                else -> exception.message.orEmpty()
            }
        }
    }
}
