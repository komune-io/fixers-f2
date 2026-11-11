package f2.spring.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ConflictExceptionTest {

    @Test
    fun `should format message with entity, property and value`() {
        val exception = ConflictException(entity = "User", property = "email", value = "test@example.com")
        assertThat(exception.message).isEqualTo("User with email [test@example.com] already exists")
    }

    @Test
    fun `should have 409 status code`() {
        val exception = ConflictException(entity = "Order", property = "ref", value = "ORD-001")
        assertThat(exception.error.code).isEqualTo(409)
        assertThat(exception.status.value()).isEqualTo(409)
    }

    @Test
    fun `should expose entity and property`() {
        val exception = ConflictException(entity = "Product", property = "sku", value = "SKU-123")
        assertThat(exception.entity).isEqualTo("Product")
        assertThat(exception.property).isEqualTo("sku")
        assertThat(exception.value).isEqualTo("SKU-123")
    }

    @Test
    fun `is the refusal http CONFLICT naming the entity, the property and the value`() {
        val refusal = ConflictException(entity = "User", property = "email", value = "a@b.c").error.refusal

        assertThat(refusal?.code).isEqualTo("http.CONFLICT")
        assertThat(refusal?.parameters)
            .containsExactlyEntriesOf(mapOf("entity" to "User", "property" to "email", "value" to "a@b.c"))
    }
}
