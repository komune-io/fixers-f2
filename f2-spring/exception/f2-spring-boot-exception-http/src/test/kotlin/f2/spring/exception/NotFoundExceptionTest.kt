package f2.spring.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NotFoundExceptionTest {

    @Test
    fun `should format message with name and id`() {
        val exception = NotFoundException(name = "User", id = "123")
        assertThat(exception.message).isEqualTo("User [123] not found")
    }

    @Test
    fun `should have 404 status code`() {
        val exception = NotFoundException(name = "Order", id = "abc")
        assertThat(exception.error.code).isEqualTo(404)
        assertThat(exception.status.value()).isEqualTo(404)
    }

    @Test
    fun `should expose name property`() {
        val exception = NotFoundException(name = "Invoice", id = "42")
        assertThat(exception.name).isEqualTo("Invoice")
    }

    @Test
    fun `is the refusal http NOT_FOUND naming the entity and its id`() {
        val refusal = NotFoundException(name = "User", id = "123").error.refusal

        assertThat(refusal?.code).isEqualTo("http.NOT_FOUND")
        assertThat(refusal?.parameters).containsExactlyEntriesOf(mapOf("entity" to "User", "id" to "123"))
    }
}
