package f2.spring.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import tools.jackson.databind.DatabindException
import tools.jackson.databind.exc.MismatchedInputException
import tools.jackson.databind.json.JsonMapper

class MessageConverterExceptionTest {

    class IntSample {
        var count: Int = 0
    }

    private class SampleDatabindException(message: String) : DatabindException(message)

    private fun mismatchedInputException(): MismatchedInputException {
        val thrown = runCatching {
            JsonMapper.builder().build().readValue("""{"count": []}""", IntSample::class.java)
        }.exceptionOrNull()
        return thrown as MismatchedInputException
    }

    @Test
    fun `should build a message from a mismatched input exception`() {
        val exception = MessageConverterException(mismatchedInputException())

        assertThat(exception.status).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(exception.error.code).isEqualTo(400)
        assertThat(exception.message)
            .contains("Cannot convert parameter `count`")
            .contains("to type `")
    }

    @Test
    fun `should keep the original message for other databind exceptions`() {
        val exception = MessageConverterException(SampleDatabindException("raw databind failure"))

        assertThat(exception.status).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(exception.message).isEqualTo("raw databind failure")
    }
}
